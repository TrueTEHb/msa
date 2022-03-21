package ru.lesson4.demo.service;

import ru.lesson4.demo.model.Greeting;

import java.util.List;

public interface GreetingService {

    Greeting save(Greeting greeting);

    Greeting findById(Long id);

    List<Greeting> findAll();

    void delete(Long id);
}
