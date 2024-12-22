package com.springboot.demo8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo8.controller.EmployeeController;
import com.springboot.demo8.entity.Employee;
import com.springboot.demo8.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EmployeeController.class)
//@AutoConfigureMockMvc		// This is not needed since @WebMvcTest already includes this
class Demo8ApplicationSliceTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void findByNameControllerTest() throws Exception {
		mockMvc.perform(
					get("/employee/findByName")
						.contentType("application/json")
						.param("name", "zihan")
				)
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.code", is("01")))		// TODO: mock underlying layer
					.andExpect(jsonPath("$.desc", is("not found")));
	}

	@Test
	public void insertControllerTest() throws Exception {
		Employee employee = new Employee(123, "zihan");

		mockMvc.perform(
						post("/employee/insert")
								.contentType("application/json")
								.content(new ObjectMapper().writeValueAsString(employee))
				)
				.andExpect(status().isOk());
	}

	@Test
	public void insertInvalidControllerTest() throws Exception {
		Employee employee = new Employee(123, "thisnameistoolong");

		mockMvc.perform(
						post("/employee/insert")
								.contentType("application/json")
								.content(new ObjectMapper().writeValueAsString(employee))
				)
				.andExpect(status().isBadRequest());	// If the name is too long/short, the controller will block input and return 400 code.
	}
}
