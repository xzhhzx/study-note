package com.springboot.demo7.service;

import com.springboot.demo7.entity.Employee;
import com.springboot.demo7.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
//    EmployeeMapper employeeMapper;    // This example doesn't use MyBatis and uses JPA instead
    EmployeeRepository employeeRepository;

    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> findNameContains(String str) {
        return employeeRepository.findByNameContains(str);
    }

    public Employee findFirstNameEqualsStr(String str) {
        return employeeRepository.findFirstNameEqualsStr(str).orElse(null);
    }
}
