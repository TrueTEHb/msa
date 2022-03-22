package ru.lesson6.demo.service;

import ru.lesson6.demo.model.Greeting;

import java.util.List;

public interface GreetingService {

    Greeting save(Greeting greeting);

    Greeting findById(Long id);

    List<Greeting> findAll();

    void delete(Long id);
}
