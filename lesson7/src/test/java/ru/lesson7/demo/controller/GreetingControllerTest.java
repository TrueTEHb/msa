package ru.lesson7.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.lesson7.demo.model.Greeting;
import ru.lesson7.demo.service.impl.GreetingServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private GreetingServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAll() {
        Greeting greeting = new Greeting(1L, "test content");
        
    }

    @Test
    void get() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}