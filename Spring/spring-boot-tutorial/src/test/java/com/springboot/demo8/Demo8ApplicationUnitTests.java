package com.springboot.demo8;

import com.springboot.demo8.controller.EmployeeController;
import com.springboot.demo8.controller.PublicResponse;
import com.springboot.demo8.entity.Employee;
import com.springboot.demo8.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class Demo8ApplicationUnitTests {

    // An instance of the class for testing
    private EmployeeController employeeControllerTestObj;

    // A mock that is used by EmployeeController
    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    private void init() {
        // Call the constructor. See how constructor injection is better here.
        // If with field constructor, the Controller cannot be instantiated with a mock Service,
        // making it impossible to test unless starting the servlet.
        employeeControllerTestObj = new EmployeeController(employeeService);
    }

    @Test
    public void findByNameTest() {
        final String testName = "zihan";
        List<Employee> employeeServiceRet = new ArrayList<>();
        employeeServiceRet.add(new Employee(123, testName));
//		doReturn(employeeServiceRet).when(employeeService).findByName(testName);
        when(employeeService.findByName(eq(testName))).thenReturn(employeeServiceRet);

        // positive test case
        PublicResponse<?> responseBody = employeeControllerTestObj.findByName(testName).getBody();
        assertEquals(responseBody.getCode(), "00");
        assertEquals(responseBody.getDesc(), "ok");
        assertEquals(((List) responseBody.getObj()).get(0), new Employee(123, "zihan"));

        // negative test case
        responseBody = employeeControllerTestObj.findByName("RANDOM_NAME").getBody();
        assertEquals(responseBody.getCode(), "01");
        assertEquals(responseBody.getDesc(), "not found");
    }

    @Test
    public void insertTest() {
        Employee employee = new Employee(123, "zihan");

        // positive test case
        PublicResponse<?> responseBody = employeeControllerTestObj.insert(employee).getBody();
        assertEquals(responseBody.getCode(), "00");
    }

    // This test method shows how unit test is not enough for testing bean/input validation.
    // See how slice test (Demo8ApplicationSliceTests.insertInvalidControllerTest) can achieve this.
    @Test
    public void insertInvalidTest() {
        Employee employeeWithInvalidName = new Employee(123, "thisnameistoolong");

        // negative test case
        // Note here: this test actually SHOULD fail, because the input is invalid.
        // However, unit test is not enough for testing input validation, and we
        // need to move on to slice test.
        PublicResponse<?> responseBody = employeeControllerTestObj.insert(employeeWithInvalidName).getBody();
        assertEquals(responseBody.getCode(), "00");
    }
}

