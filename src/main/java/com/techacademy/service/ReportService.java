package com.techacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    
    /** 全件を検索して返す */
    public List<Report> getReportList() {
        return reportRepository.findAll();
    }

    /** 指定されたIDに基づいて日報を取得する */
    public Report getReport(Integer id) {
        return reportRepository.findById(id).orElse(null);
    }
    
    /** 日報の登録を行う */
    @Transactional
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
}
