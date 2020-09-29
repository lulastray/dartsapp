package com.luciaastray.dartsapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected void assertResponse(
            String endpoint,
            HttpMethod method,
            String requestBody,
            HttpStatus expectedStatusCode,
            String expectedResponse
    ) throws Exception {
        ResultMatcher response = expectedResponse.isEmpty()
                ? content().string("")
                : content().json(expectedResponse);

        mockMvc.perform(request(method, "/api" + endpoint)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(expectedStatusCode.value()))
                .andExpect(response);
    }

    protected String bodyAsJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
