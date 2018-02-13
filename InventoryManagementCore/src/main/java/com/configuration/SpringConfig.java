package com.configuration;

import com.constant.ReportFormat;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.report.ReportProcess;
import com.report.ReportProcessCsv;
import com.report.ReportProcessPdf;
import com.tacitknowledge.util.migration.jdbc.AutoPatchService;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com")
@PropertySources(
    {@PropertySource("classpath:autoPatch.properties"),
        @PropertySource("classpath:db.properties"),
        @PropertySource("classpath:email.properties")}
)
@EnableTransactionManagement
@EnableScheduling
/**
 * This is spring configuration class
 *
 * @author Alexandr
 */
public class SpringConfig {

  @Autowired
  private Environment env;

  private static final String DATA_SOURCE = "dataSource";
  private static final String AUTO_PATCH = "autoPatch";

  /**
   * Configure and return a TaskScheduler to start job on timer.
   *
   * @return TaskScheduler configured
   */
  @Bean
  public TaskScheduler taskScheduler() {
    return new ConcurrentTaskScheduler();
  }

  /**
   * Configure and return a ComboPooledDataSource to database connection.
   *
   * @return ComboPooledDataSource configured from injected properties
   *
   * @throws PropertyVetoException if an unexpected error occurs
   */
  @Bean(name = DATA_SOURCE)
  public ComboPooledDataSource getDataSource() throws PropertyVetoException {
    ComboPooledDataSource cpds = new ComboPooledDataSource();
    cpds.setDriverClass(env.getProperty("driverClass"));
    cpds.setJdbcUrl(env.getProperty("url"));
    cpds.setUser(env.getProperty("userName"));
    cpds.setPassword(env.getProperty("password"));
    return cpds;
  }

  /**
   * Configure and return a AutoPatchService to create database schema.
   *
   * @return AutoPatchService configured from injected properties
   *
   * @throws PropertyVetoException if an unexpected error occurs
   */
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

  /**
   * Configure and return a LocalSessionFactoryBean to create session.
   *
   * @return LocalSessionFactoryBean configured from injected properties
   *
   * @throws PropertyVetoException if an unexpected error occurs
   *
   * @throws IOException if an unexpected error occurs
   */
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

  /**
   * Configure and return a HibernateTransactionManager to create transaction.
   *
   * @return HibernateTransactionManager configured from injected properties
   *
   * @throws PropertyVetoException if an unexpected error occurs
   *
   * @throws IOException if an unexpected error occurs
   */
  @Bean
  public HibernateTransactionManager getTransaction() throws PropertyVetoException, IOException {
    HibernateTransactionManager htm = new HibernateTransactionManager();
    htm.setSessionFactory(getSessionFactory().getObject());
    htm.setNestedTransactionAllowed(true);
    return htm;
  }

  /**
   * Configure and return a JavaMailSender to send email.
   *
   * @return JavaMailSender configured from injected properties
   */
  @Bean
  public JavaMailSender getJavaMailSender() {
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

  /**
   * Configure and return a Map which contain initialized objects for create report.
   *
   * @param reportProcessCsv for create report in format CSV
   *
   * @param reportProcessPdf for create report in format PDF
   *
   * @return Configure and return a Map which contain initialized objects
   */
  @Bean(name = "initializerReport")
  public Map<ReportFormat, ReportProcess> initializeReport(
      @Qualifier(value = "reportProcessCSV") ReportProcessCsv reportProcessCsv,
      @Qualifier(value = "reportProcessPDF") ReportProcessPdf reportProcessPdf) {
    Map<ReportFormat, ReportProcess> reportProcessMap = new HashMap<>();
    reportProcessMap.put(ReportFormat.CSV, reportProcessCsv);
    reportProcessMap.put(ReportFormat.PDF, reportProcessPdf);
    return reportProcessMap;
  }
}
