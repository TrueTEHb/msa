package ru.lesson7.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.lesson7.demo.model.Greeting;
import ru.lesson7.demo.service.impl.GreetingServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAll() throws Exception {
        mvc.perform(get("/greeting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(content().string(startsWith("[{\"id\":1,\"content\":\"First test message\"},{\"id\":2,\"content\":\"Second test message\"}")));
    }

    private List<Greeting> getGreetings() {
        Greeting first = new Greeting(1L, "First test message");
        Greeting second = new Greeting(2L, "Second test message");
        return Arrays.asList(first, second);
    }

    @Test
    void getOne() throws Exception {
        Greeting greeting = getGreetings().get(0);
        mvc.perform(get("/greeting/{id}", greeting.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is(greeting.getContent())));
    }

    @Test
    void create() throws Exception {
        Greeting greeting = new Greeting(3L, "Create test third content");
        mvc.perform(post("/greeting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(greeting)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        Greeting greeting = getGreetings().get(0);
        greeting.setContent("Update second message");
        mvc.perform(put("/greeting/{id}", greeting.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(greeting)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is(greeting.getContent())));
    }

    @Test
    void remove() throws Exception {
        mvc.perform(delete("/greeting/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}