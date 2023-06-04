package com.techacademy.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /** 一覧画面を表示 */
    @GetMapping("/list")
    public String getList(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeelist", service.getEmployeeList());
        // templatesフォルダにあるemployeeフォルダのlist.htmlを参照
        return "employee/list";
    }

    // ----- 詳細画面 -----
    @GetMapping(value = { "/detail", "/detail/{id}/" })
    public String getEmployee(@PathVariable(name = "id", required = false) Integer id, Model model) {
        // idが指定されていたら検索結果、無ければ空のクラスを設定
        Employee employee = id != null ? service.getEmployee(id) : new Employee();
        // Modelに登録
        model.addAttribute("employee", employee);
        // employee/detail.htmlに画面遷移
        return "employee/detail";
    }

    /* ----- 更新（追加） -----
    @PostMapping("/detail")
    public String postEmployee(@RequestParam("id") Integer id, @RequestParam("name") String name,
            @RequestParam("dalete_flag") int delete_flag, Model model) {
        // 更新（追加）
        service.updateEmployee(id, name, delete_flag);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }*/

    /** User削除処理 */
    @GetMapping(value = { "/delete", "/delete/{id}/" })
    public String deleteEmployee(@PathVariable(name = "id", required = false) Integer id, Model model) {
        if (id != null) {
            // サービスから従業員情報を取得
            Employee employee = service.getEmployee(id);
            // delete_flagを1に設定
            employee.setDeleteFlag(1);
            // 更新した従業員情報を保存
            service.saveEmployee(employee);
        }
        // 詳細ページのビュー名を返す
        return "employee/detail";
    }
    /** User登録画面を表示 */
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee) {
        
        // Employee登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理 */
    @PostMapping("/register")
    public String postEmployee(@ModelAttribute Employee employee, Model model) {
        // 現在の日時を取得
        LocalDateTime now = LocalDateTime.now();
        // 従業員情報のセット
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        // AuthenticationのEmployeeに関連付ける
        employee.getAuthentication().setEmployee(employee);
        // 更新（追加）
        service.saveEmployee(employee);
        System.out.println(service.getEmployeeList().get(0).getName());
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /* Employee更新画面を表示 */
    @GetMapping("/update/{id}")
    public String getUser(@PathVariable("id")  Integer id, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        // Employee更新画面に遷移
        return "employee/update";
    }

    /* Employee更新処理 */
    @PostMapping("/update/{id}/")
    public String postUpdate(Employee employee) {
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
}