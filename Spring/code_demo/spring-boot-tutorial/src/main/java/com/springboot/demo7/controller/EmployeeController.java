package com.springboot.demo7.controller;

import com.springboot.demo7.entity.Employee;
import com.springboot.demo7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/findByName")
    public ResponseEntity<PublicResponse<?>> findByName(@RequestParam String name) {
        List<Employee> employees = employeeService.findByName(name);
        PublicResponse<List<Employee>> response;
        if (employees != null) {
            response = new PublicResponse<>("00", "ok", employees);
        } else {
            response = new PublicResponse<>("01", "not found", employees);
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/findNameContains")
    public ResponseEntity<PublicResponse<?>> findNameContains(@RequestParam String str) {
        List<Employee> employees = employeeService.findNameContains(str);
        PublicResponse<List<Employee>> response;
        if (employees != null) {
            response = new PublicResponse<>("00", "ok", employees);
        } else {
            response = new PublicResponse<>("01", "not found", employees);
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/findFirstNameEqualsStr")
    public ResponseEntity<PublicResponse<Employee>> findFirstNameEqualsStr(@RequestParam String name) {
        Employee employee = employeeService.findFirstNameEqualsStr(name);
        PublicResponse<Employee> response;
        if (employee != null) {
            response = new PublicResponse<Employee>("00", "ok", employee);
        } else {
            response = new PublicResponse<Employee>("01", "not found", employee);
        }
        return ResponseEntity.ok(response);
    }

}
