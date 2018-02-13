package com.report;

import com.constant.ReportFormat;
import com.dao.InventoryStateDao;
import com.entity.InventoryState;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("reportService")
public class ReportService {

  @Resource(name = "inventoryStateDaoImpl")
  private InventoryStateDao inventoryStateDao;

  @Resource(name = "reportInitializer")
  private ReportInitializer reportInitializer;

  /**
   * This method generate report from injected properties.
   *
   * @param localDateTime - date on which the report is needed
   *
   * @param reportFormat - report format on which the report is needed
   *
   * @param url - where save file
   *
   * @throws IOException if an unexpected error occurs
   *
   * @throws DocumentException if an unexpected error occurs
   */
  public void generateReport(
      LocalDateTime localDateTime,
      ReportFormat reportFormat,
      String url) throws IOException, DocumentException {
    List<InventoryState> inventoryStates =
        inventoryStateDao.getActualInventoryStateByDate(localDateTime);
    ReportProcess reportProcess = reportInitializer.initializeReport(reportFormat);
    File file = new File(url);
    reportProcess.writeData(inventoryStates, file);
  }
}
