package com.invoice_components.listener;

import com.dao.InventoryStateDao;
import com.entity.InventoryState;
import com.invoice_components.message.MessageProvider;
import com.invoice_components.table_model.ReportTableModel;
import org.jdesktop.swingx.JXDatePicker;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
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
    private ReportTableModel reportTableModel;
    private JXDatePicker jxDatePicker;
    public static final String SHOW_REPORT_BUTTON_LISTENER_BEAN = "showReportButtonListener";
    private MessageProvider messageProvider = new MessageProvider();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jxDatePicker.getDate() == null) {
            messageProvider.showMessage("You enter wrong date " + jxDatePicker.getDate());
            return;
        }
        Date inputDate = jxDatePicker.getDate();
        List<InventoryState> inventoryStates = inventoryStateDao.
                getActualInventoryStateByDate(LocalDateTime.ofInstant(inputDate.toInstant(), ZoneId.systemDefault()));
        reportTableModel.setInventoryStates(inventoryStates);
        reportTableModel.fireTableDataChanged();
    }

    public void setReportTableModel(ReportTableModel reportTableModel) {
        this.reportTableModel = reportTableModel;
    }

    public void setJxDatePicker(JXDatePicker jxDatePicker) {
        this.jxDatePicker = jxDatePicker;
    }
}
