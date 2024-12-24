package com.springboot.demo8.controller;

import com.springboot.demo8.entity.Employee;
import com.springboot.demo8.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Use constructor injection here
    //  (this is preferred over field injection for various reasons,
    //   one of them is the easiness of unit/sliced testing)
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/findByName")
    public ResponseEntity<PublicResponse<?>> findByName(@RequestParam String name) {
        List<Employee> employees = employeeService.findByName(name);
        PublicResponse<List<Employee>> response;
        if (employees != null && employees.size() > 0) {
            response = new PublicResponse<>("00", "ok", employees);
        } else {
            response = new PublicResponse<>("01", "not found", employees);
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/insert")
    public ResponseEntity<PublicResponse<?>> insert(@Valid @RequestBody Employee employee) {
        // Business logic omitted
        return ResponseEntity.ok(new PublicResponse<>("00", "ok", null));
    }
}
