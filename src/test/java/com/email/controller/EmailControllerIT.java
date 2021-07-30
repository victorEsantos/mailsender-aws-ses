package com.email.controller;

import com.email.model.Email;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Email - Solicitações via REST API.")
@SpringBootTest
@AutoConfigureMockMvc
public class EmailControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllEmailsTest() throws Exception {
        mockMvc.perform(get("/emails"))
                .andExpect(status().isOk());
    }

    @Test
    public void emailSaveTest() throws Exception {
        var email = Email.builder()
                .ownerRef("OwnerRef")
                .emailFrom("victor.eduardo@gmail.com")
                .emailTo("Email2@gmail.com")
                .subject("Subject")
                .text("Text")
                .build();

        mockMvc.perform(post("/send-email")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(email)))
            .andExpect(status().isOk());

    }
}
