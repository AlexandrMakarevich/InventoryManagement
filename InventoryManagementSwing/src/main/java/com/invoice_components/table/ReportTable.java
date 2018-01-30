package com.invoice_components.table;

import com.entity.InventoryState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.invoice_components.table.ReportTable.REPORT_TABLE_BEAN;

@Component(REPORT_TABLE_BEAN)
public class ReportTable extends JTable {

    public static final String REPORT_TABLE_BEAN = "reportTable";
    private ReportTableModel reportTableModel;
    private List<InventoryState> inventoryStates = new ArrayList<>();

    public void refreshTable() {
        reportTableModel.setInventoryStates(inventoryStates);
        reportTableModel.fireTableDataChanged();
    }

    @Autowired
    public void setReportTableModel(ReportTableModel reportTableModel) {
        this.reportTableModel = reportTableModel;
    }

    public void setInventoryStates(List<InventoryState> inventoryStates) {
        this.inventoryStates = inventoryStates;
    }

    public List<InventoryState> getInventoryStates() {
        return inventoryStates;
    }
}
