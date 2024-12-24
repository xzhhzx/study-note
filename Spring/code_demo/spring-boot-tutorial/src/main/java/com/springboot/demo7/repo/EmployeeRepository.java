package com.springboot.demo7.repo;

import com.springboot.demo7.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/* Accessing database with Spring Data JPA, extending CrudRepository */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByNameContains(String str);

    // Complex query example
    String sql = "" +
            "SELECT * " +
            "FROM employee " +
            "WHERE employee.name = :str " +
            "ORDER BY id " +
            "LIMIT 1";
    @Query(value = sql, nativeQuery = true)
    Optional<Employee> findFirstNameEqualsStr(@Param("str") String str);

}
