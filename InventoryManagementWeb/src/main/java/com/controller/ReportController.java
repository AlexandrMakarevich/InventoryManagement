package com.controller;

import com.dao.InventoryStateDao;
import com.entity.InventoryState;
import com.report.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReportController {

    @Resource(name = "reportService")
    private ReportService reportService;

    @Resource(name = "inventoryStateDaoImpl")
    private InventoryStateDao inventoryStateDao;

    @RequestMapping("/main")
    public String mainPage(Model model) {
        return "main";
    }

    @RequestMapping("/report")
    public String reportPage(Model model) {
        List<InventoryState> stateList = inventoryStateDao.getActualInventoryStateByDate(LocalDateTime.now());
        model.addAttribute("inventoryStates", stateList);
        return "report";
    }
}
