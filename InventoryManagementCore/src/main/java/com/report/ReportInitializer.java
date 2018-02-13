package com.report;

import com.constant.ReportFormat;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository("reportInitializer")
public class ReportInitializer {

  @Resource(name = "initializerReport")
  private Map<ReportFormat, ReportProcess> reportProcessMap;

  /**
   * This method initialize ReportProcess,which needed depend on format type.
   *
   * @param reportFormat - format of report
   *
   * @return object,which needed depend on format type
   */
  public ReportProcess initializeReport(ReportFormat reportFormat) {
    ReportProcess reportProcess = reportProcessMap.get(reportFormat);
    if (reportProcess == null) {
      throw new IllegalArgumentException("Inserted wrong format of report" + reportFormat);
    }
    return reportProcess;
  }
}
