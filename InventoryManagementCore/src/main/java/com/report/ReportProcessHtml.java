package com.report;

import static org.apache.commons.lang.CharEncoding.UTF_8;

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

public class ReportProcessHtml {

  private String fileName = "test.html";
  private MustacheFactory mf;
  private Mustache mustache;

  public ReportProcessHtml() {
    mf = new DefaultMustacheFactory();
    mustache = mf.compile("mustache.template");
  }

  /**
   * This method generate report in HTML format.
   *
   * @param inventoryStates - state of inventory on certain date
   *
   * @return file with report
   *
   * @throws IOException if an unexpected error occurs
   */
  public File generateHtmlReport(List<InventoryState> inventoryStates) throws IOException {
    File htmlFile = new File(fileName);
    BigDecimal totalPrice = countTotalPrice(inventoryStates);
    Map<String, Object> context = ImmutableMap.of("items", inventoryStates,
        "price", totalPrice);
    mustache.execute(new PrintWriter(htmlFile, UTF_8), context).flush();
    return htmlFile;
  }

  /**
   * This method considers the total cost of the entire product.
   *
   * @param inventoryStates - state of inventory on certain date
   *
   * @return total cost
   */
  public BigDecimal countTotalPrice(List<InventoryState> inventoryStates) {
    BigDecimal countPrice = new BigDecimal(0);
    for (InventoryState inventoryState : inventoryStates) {
      countPrice = countPrice.add(inventoryState.calculateItemCost());
    }
    return countPrice;
  }
}
