package ru.lesson6.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lesson6.demo.model.Greeting;
import ru.lesson6.demo.service.impl.GreetingServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    @Autowired
    private GreetingServiceImpl service;

    @GetMapping
    public List<Greeting> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Greeting get(@PathVariable String id) {
        return service.findById(Long.valueOf(id));
    }

    @PostMapping
    public Greeting create(@RequestBody Map<String, String> params) {
        Greeting greeting = new Greeting();
        greeting.setContent(params.get("content"));
        return service.save(greeting);
    }

    @PutMapping("{id}")
    public Greeting update(@PathVariable String id, @RequestBody Map<String, String> params) {
        Greeting greeting = service.findById(Long.valueOf(id));
        greeting.setContent(params.get("content"));
        return service.save(greeting);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.delete(Long.valueOf(id));
    }
}