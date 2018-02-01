package com.invoice_components.table_model;

import com.entity.InventoryState;
import org.springframework.stereotype.Component;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.invoice_components.table_model.ReportTableModel.REPORT_TABLE_MODEL_BEAN;

@Component(REPORT_TABLE_MODEL_BEAN)
public class ReportTableModel extends AbstractTableModel {

    public static final String REPORT_TABLE_MODEL_BEAN = "reportTableModel";
    private List<InventoryState> inventoryStates = new ArrayList<>();
    public List<String> columnName = Arrays.asList("PRODUCT_ID",
            "PRODUCT_NAME", "PRODUCT_PRICE", "QUANTITY", "DATE");

    @Override
    public int getRowCount() {
        return inventoryStates.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InventoryState inventoryState = inventoryStates.get(rowIndex);
        if (columnIndex == 0) {
            return inventoryState.getInventoryStatePK().getProduct().getId();
        }
        if (columnIndex == 1) {
            return inventoryState.getInventoryStatePK().getProduct().getProductName();
        }
        if (columnIndex == 2) {
            return inventoryState.getInventoryStatePK().getProduct().getPrice();
        }
        if (columnIndex == 3) {
            return inventoryState.getQuantity();
        }
        if (columnIndex == 4) {
            return inventoryState.getInventoryStatePK().getStateDate();
        }
        throw new IllegalArgumentException("Wrong column index" + columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0) {
            return columnName.get(0);
        }
        if(column == 1) {
            return columnName.get(1);
        }
        if(column == 2) {
            return columnName.get(2);
        }
        if(column == 3) {
            return columnName.get(3);
        }
        if(column == 4) {
            return columnName.get(4);
        }
        throw new IllegalArgumentException("Wrong column index " + column);
    }

    public void setInventoryStates(List<InventoryState> inventoryStates) {
        this.inventoryStates = inventoryStates;
    }
}
