package com.springboot.demo8.service;

import com.springboot.demo8.entity.Employee;
//import com.springboot.demo8.mapper.EmployeeMapper;
import com.springboot.demo8.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }
}
