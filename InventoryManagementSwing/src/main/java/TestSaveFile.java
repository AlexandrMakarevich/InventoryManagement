import com.configuration.SpringConfig;
import com.constant.ReportFormat;
import com.itextpdf.text.DocumentException;
import com.report.ReportService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.time.LocalDateTime;

public class TestSaveFile {

    public static void main(String[] args) throws IOException, DocumentException {
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);
        ReportService reportService = (ReportService) app.getBean("reportService");

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your file: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                System.out.println("You selected the directory: " + jfc.getSelectedFile());
                reportService.generateReport(LocalDateTime.now(), ReportFormat.PDF,
                        jfc.getSelectedFile().getAbsolutePath() + "/ReportPdf.pdf");
            }
        }
    }
}
