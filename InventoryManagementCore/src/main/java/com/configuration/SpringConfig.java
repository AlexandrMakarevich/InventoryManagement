package com.configuration;

import com.constant.ReportFormat;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.report.ReportProcess;
import com.report.ReportProcessCSV;
import com.report.ReportProcessPDF;
import com.tacitknowledge.util.migration.jdbc.AutoPatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com")
@PropertySources({@PropertySource("classpath:autoPatch.properties"),
                   @PropertySource("classpath:db.properties"),
                    @PropertySource("classpath:email.properties")})
@EnableTransactionManagement
@EnableScheduling
public class SpringConfig {

    @Autowired
    private Environment env;

    private final String DATA_SOURCE = "dataSource";
    private final String AUTO_PATCH = "autoPatch";

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler(); //single threaded by default
    }

    @Bean(name = DATA_SOURCE)
    public ComboPooledDataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(env.getProperty("driverClass"));
        cpds.setJdbcUrl(env.getProperty("url"));
        cpds.setUser(env.getProperty("userName"));
        cpds.setPassword(env.getProperty("password"));
        return cpds;
    }

    @DependsOn(DATA_SOURCE)
    @Bean(name = AUTO_PATCH, initMethod = "patch")
    public AutoPatchService initAutoPatch() throws PropertyVetoException {
        AutoPatchService autoPatchService = new AutoPatchService();
        autoPatchService.setDataSource(getDataSource());
        autoPatchService.setPatchPath(env.getProperty("patchPath"));
        autoPatchService.setSystemName(env.getProperty("systemName"));
        autoPatchService.setDatabaseType(env.getProperty("databaseType"));
        return autoPatchService;
    }

    @DependsOn(AUTO_PATCH)
    @Bean
    public LocalSessionFactoryBean getSessionFactory() throws PropertyVetoException, IOException {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(getDataSource());
        lsfb.setPackagesToScan("com.entity");
        InputStream inputStream = getClass().getResourceAsStream("/hibernate-application.properties");
        Properties hibernateProperties = new Properties();
        hibernateProperties.load(inputStream);
        lsfb.setHibernateProperties(hibernateProperties);
        return lsfb;
    }

    @Bean
    public HibernateTransactionManager getTransaction() throws PropertyVetoException, IOException {
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(getSessionFactory().getObject());
        htm.setNestedTransactionAllowed(true);
        return htm;
    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(env.getProperty("userEmailAddress"));
        mailSender.setPassword(env.getProperty("userEmailPassword"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean(name = "initializerReport")
    public Map<ReportFormat, ReportProcess> initializeReport(
            @Qualifier(value = "reportProcessCSV") ReportProcessCSV reportProcessCSV,
            @Qualifier(value = "reportProcessPDF") ReportProcessPDF reportProcessPDF) {
        Map<ReportFormat, ReportProcess> reportProcessMap = new HashMap<>();
        reportProcessMap.put(ReportFormat.CSV, reportProcessCSV);
        reportProcessMap.put(ReportFormat.PDF, reportProcessPDF);
        return reportProcessMap;
    }
}
