package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/list")
    public String getList(@AuthenticationPrincipal UserDetail user, Model model) {
        model.addAttribute("employee", user.getEmployee());
        model.addAttribute("reportlist", reportService.getReportList());
        return "report/list";
    }

    @GetMapping(value = { "/detail", "/detail/{id}/" })
    public String getReport(@PathVariable(name = "id", required = false) Integer id, Model model,@AuthenticationPrincipal UserDetail user) {
        Report report = id != null ? reportService.getReport(id) : new Report();
        model.addAttribute("employee", user.getEmployee());
        model.addAttribute("report", report);
        return "report/detail";
    }


    @GetMapping("/register")
    public String getRegister(@AuthenticationPrincipal UserDetail user, Model model) {
        Report report = new Report();
        model.addAttribute("employee", user.getEmployee());
        model.addAttribute("report", report);
        return "report/register";
    }

    @PostMapping("/register")
    public String postReport(@ModelAttribute Report report, Model model) {
        reportService.saveReport(report);
        Employee employee = report.getEmployee();
        model.addAttribute("employee", employee);
        return "redirect:/report/list";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetail user, Model model) {
        Report report = reportService.getReport(id);
        model.addAttribute("employee", user.getEmployee());
        model.addAttribute("report", report);
        return "report/update";
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable("id") Integer id, @ModelAttribute Report report) {
        report.setId(id);
        reportService.saveReport(report);
        return "redirect:/report/list";
    }
}
