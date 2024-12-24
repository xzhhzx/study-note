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

// RANDOM_PORT: Loads a WebServerApplicationContext and provides a real web environment.
// Embedded servers are started and listen on a random port.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class Demo8ApplicationIntegrationTests_withTomcat {

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
