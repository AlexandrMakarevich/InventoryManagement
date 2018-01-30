package com.invoice_components.listener;

import com.dao.InventoryStateDao;
import com.entity.InventoryState;
import com.invoice_components.table.ReportTable;
import org.jdesktop.swingx.JXDatePicker;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import static com.invoice_components.listener.ShowReportButtonListener.SHOW_REPORT_BUTTON_LISTENER_BEAN;

@Component(SHOW_REPORT_BUTTON_LISTENER_BEAN)
public class ShowReportButtonListener implements ActionListener {

    @Resource(name = "inventoryStateDaoImpl")
    private InventoryStateDao inventoryStateDao;
    private ReportTable reportTable;
    private JXDatePicker jxDatePicker;
    public static final String SHOW_REPORT_BUTTON_LISTENER_BEAN = "showReportButtonListener";

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Date inputDate = validateDate();
            List<InventoryState> inventoryStates = inventoryStateDao.
                    getActualInventoryStateByDate(LocalDateTime.ofInstant(inputDate.toInstant(), ZoneId.systemDefault()));
            reportTable.setInventoryStates(inventoryStates);
            reportTable.refreshTable();
        } catch (IllegalArgumentException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    public Date validateDate() {
        if (jxDatePicker.getDate() == null) {
            throw new IllegalArgumentException("You enter wrong date " + jxDatePicker.getDate());
        }
        return jxDatePicker.getDate();
    }

    public void setReportTable(ReportTable reportTable) {
        this.reportTable = reportTable;
    }

    public void setJxDatePicker(JXDatePicker jxDatePicker) {
        this.jxDatePicker = jxDatePicker;
    }
}
