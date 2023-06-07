package com.techacademy.repository;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByEmployee(Employee employee);
}
