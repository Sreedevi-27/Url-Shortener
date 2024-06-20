package com.urlshortner.web.controller;

import com.urlshortner.core.domain.UrlEntry;
import com.urlshortner.core.service.UrlService;
import com.urlshortner.web.UrlApplication;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = UrlApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class UrlControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UrlService urlService;

    @Test
    public void shouldCreateUrlEntry() throws Exception {
        // setup
        String longUrl = "https://www.google.com";
        Mockito.when(urlService.create(Mockito.any()))
                .thenReturn(new UrlEntry(longUrl, "000001"));
        String requestBody = """
                {
                     "longUrl": "{longUrl}"
                 }
                """;

        // run and verify
        mvc.perform(post("/")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.longUrl").value(longUrl))
                .andExpect(jsonPath("$.shortUrl").value("localhost:8080/000001"));
    }

    @Test
    public void shouldDeleteKey() throws Exception {
        mvc.perform(delete("/000004")).andExpect(status().isOk());
    }
}