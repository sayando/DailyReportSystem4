package com.techacademy.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;
import com.techacademy.service.UserDetail;

@Controller
public class LoginController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/")
    public String getIndex(@AuthenticationPrincipal UserDetail user, Model model) {
        Report report = new Report();
        Employee employee = user.getEmployee();
        List<Report> employeeReports = reportRepository.findByEmployee(employee);
        model.addAttribute("employee", employee);
        model.addAttribute("reports", employeeReports);
        model.addAttribute("report", report);
        return "employee/top";
    }

}
