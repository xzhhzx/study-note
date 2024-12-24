package com.springboot.demo8;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// MOCK(Default) : Loads a web ApplicationContext and provides a mock web environment.
// Embedded server (Tomcat) is not started
@SpringBootTest
@AutoConfigureMockMvc
class Demo8ApplicationIntegrationTests_withoutTomcat {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void exampleTest() throws Exception {
        mockMvc.perform(
                        get("/employee/findByName")
                                .contentType("application/json")
                                .param("name", "zihan")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is("00")))
                .andExpect(jsonPath("$.desc", is("ok")));
    }
}
