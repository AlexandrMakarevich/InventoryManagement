package com.report;

import com.entity.InventoryState;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import static org.apache.commons.lang.CharEncoding.UTF_8;

public class ReportProcessHTML {

    private String fileName = "test.html";
    private MustacheFactory mf;
    private Mustache mustache;

    public ReportProcessHTML() {
        mf =  new DefaultMustacheFactory();
        mustache = mf.compile("mustache.template");
    }

    public File generateHTMLReport(List<InventoryState> inventoryStates) throws IOException {
        File htmlFile = new File(fileName);
        BigDecimal totalPrice = countTotalPrice(inventoryStates);
        Map<String, Object> context = ImmutableMap.of("items", inventoryStates,
                "price", totalPrice);
        mustache.execute(new PrintWriter(htmlFile, UTF_8), context).flush();
        return htmlFile;
    }

    public BigDecimal countTotalPrice(List<InventoryState> inventoryStates) {
        BigDecimal countPrice = new BigDecimal(0);
        for (InventoryState inventoryState : inventoryStates) {
            countPrice = countPrice.add(inventoryState.calculateItemCost());
        }
        return countPrice;
    }
}
