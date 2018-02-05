package com.invoice_components.listener;

import com.constant.ReportFormat;
import com.invoice_components.message.MessageProvider;
import com.itextpdf.text.DocumentException;
import com.report.ReportService;
import org.jdesktop.swingx.JXDatePicker;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import static com.invoice_components.listener.SaveReportButtonListener.SAVE_REPORT_BUTTON_LISTENER_BEAN;

@Component(SAVE_REPORT_BUTTON_LISTENER_BEAN)
public class SaveReportButtonListener implements ActionListener {

    private JFileChooser jfc;
    private JXDatePicker jxDatePicker;
    @Resource(name = "reportService")
    private ReportService reportService;
    public static final String SAVE_REPORT_BUTTON_LISTENER_BEAN = "saveReportButtonListener";
    private MessageProvider messageProvider = new MessageProvider();

    @Override
    public void actionPerformed(ActionEvent e) {
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your report: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                LocalDateTime inputDate = LocalDateTime.ofInstant(jxDatePicker.getDate().toInstant(), ZoneId.systemDefault());
                try {
                    reportService.generateReport(inputDate, ReportFormat.PDF, jfc.getSelectedFile().getAbsolutePath() + "/ReportPdf.pdf");
                } catch (IOException | DocumentException e1) {
                    e1.printStackTrace();
                    messageProvider.showMessage(e1.getMessage());
                }
            }
        }
    }

    public void setJxDatePicker(JXDatePicker jxDatePicker) {
        this.jxDatePicker = jxDatePicker;
    }
}
