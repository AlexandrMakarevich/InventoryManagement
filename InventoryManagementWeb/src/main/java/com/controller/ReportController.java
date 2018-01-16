package com.controller;

import com.constant.ReportFormat;
import com.dao.InventoryStateDao;
import com.entity.InventoryState;
import com.report.ReportService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    @Resource(name = "reportService")
    private ReportService reportService;

    @Resource(name = "inventoryStateDaoImpl")
    private InventoryStateDao inventoryStateDao;

    private static final Logger LOGGER = Logger.getLogger(ReportController.class);

    @RequestMapping("/report")
    public String reportPage(Model model,@RequestParam(value = "reportDate", required = false) String localDateTime) {
        List<InventoryState> stateList = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        if(localDateTime != null){
           stateList = inventoryStateDao.getActualInventoryStateByDate(LocalDateTime.parse(localDateTime));
           totalPrice = countTotalPrice(stateList);
        }
        model.addAttribute("inventoryStates", stateList);
        model.addAttribute("totalPrice", totalPrice);
        return "report";
    }

    @RequestMapping(value="/save_report")
    public void getLogFile(@RequestParam(value = "reportDate", required = false) String date,
                           HttpServletResponse response) throws Exception {
        String filePathToBeSaved = "temp/ReportPdf.pdf";
        reportService.generateReport(LocalDateTime.parse(date), ReportFormat.PDF, filePathToBeSaved);
        try {
                    File fileToDownload = new File(filePathToBeSaved);
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename = Report.pdf");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception e){
            LOGGER.warn("Report could not be saved at this moment. Please try again later.");
            e.printStackTrace();
        }
    }

    public BigDecimal countTotalPrice(List<InventoryState> inventoryStates) {
        BigDecimal countPrice = new BigDecimal(0);
        for (InventoryState inventoryState : inventoryStates) {
            countPrice = countPrice.add(inventoryState.calculateItemCost());
        }
        return countPrice;
    }
}
