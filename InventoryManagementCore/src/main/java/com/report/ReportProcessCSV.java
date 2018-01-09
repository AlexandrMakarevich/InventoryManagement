package com.report;

import com.entity.InventoryState;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import static org.apache.commons.lang.CharEncoding.UTF_8;

@Service("reportProcessCSV")
public class ReportProcessCSV implements ReportProcess {

    private static final String TAB_CHAR = "\t";

    @Override
    public void writeData(List<InventoryState> inventoryStates, File file) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pwt = new PrintWriter(file, UTF_8);
        int rowNumber = 1;
        for (InventoryState inventoryState : inventoryStates) {
            StringBuilder report = new StringBuilder()
                    .append(rowNumber++)
                    .append(TAB_CHAR)
                    .append(inventoryState.getInventoryStatePK().getProduct().getProductName())
                    .append(TAB_CHAR)
                    .append(inventoryState.getQuantity())
                    .append(TAB_CHAR)
                    .append(inventoryState.getInventoryStatePK().getStateDate())
                    .append(TAB_CHAR)
                    .append(inventoryState.calculateItemCost())
                    .append("\n");
            pwt.format(report.toString());
        }
        pwt.flush();
        pwt.close();
    }
}
