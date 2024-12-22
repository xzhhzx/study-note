package com.springboot.demo8.repo;

import com.springboot.demo8.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/* Accessing database with Spring Data JPA, extending CrudRepository */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);
}
