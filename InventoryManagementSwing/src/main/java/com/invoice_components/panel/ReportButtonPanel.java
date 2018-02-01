package com.invoice_components.panel;

import com.invoice_components.listener.SaveReportButtonListener;
import com.invoice_components.listener.ShowReportButtonListener;
import com.invoice_components.table_model.ReportTableModel;
import org.jdesktop.swingx.JXDatePicker;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static com.invoice_components.listener.SaveReportButtonListener.SAVE_REPORT_BUTTON_LISTENER_BEAN;
import static com.invoice_components.listener.ShowReportButtonListener.SHOW_REPORT_BUTTON_LISTENER_BEAN;
import static com.invoice_components.table_model.ReportTableModel.REPORT_TABLE_MODEL_BEAN;

public class ReportButtonPanel extends JPanel {

    private FlowLayout flowLayout = new FlowLayout();
    private JLabel dateLabel = new JLabel("Choose date :");
    private JXDatePicker datePicker = new JXDatePicker();
    private JButton showReportButton = new JButton("Show report");
    private JButton saveReportButton = new JButton("Save report");
    private ShowReportButtonListener showReportButtonListener;
    private SaveReportButtonListener saveReportButtonListener;

    public ReportButtonPanel(ApplicationContext app) {
        setLayout(flowLayout);
        add(dateLabel);
        datePicker.setDate(Calendar.getInstance().getTime());
        datePicker.setFormats(new SimpleDateFormat("dd.MM.yyyy HH:mm"));
        add(datePicker);
        showReportButtonListener = (ShowReportButtonListener) app.getBean(SHOW_REPORT_BUTTON_LISTENER_BEAN);
        showReportButtonListener.setJxDatePicker(datePicker);
        showReportButtonListener.setReportTableModel((ReportTableModel) app.getBean(REPORT_TABLE_MODEL_BEAN));
        showReportButton.addActionListener(showReportButtonListener);
        add(showReportButton);
        saveReportButtonListener = (SaveReportButtonListener) app.getBean(SAVE_REPORT_BUTTON_LISTENER_BEAN);
        saveReportButtonListener.setJxDatePicker(datePicker);
        saveReportButton.addActionListener(saveReportButtonListener);
        add(saveReportButton);
    }
}
