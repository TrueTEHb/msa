package ru.lesson4.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lesson4.demo.exception.NotFoundException;
import ru.lesson4.demo.model.Greeting;
import ru.lesson4.demo.repository.ContentRepository;
import ru.lesson4.demo.service.GreetingService;

import java.util.List;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Greeting save(Greeting greeting) {
        return contentRepository.save(greeting);
    }

    @Override
    public Greeting findById(Long id) {
        return contentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Greeting> findAll() {
        return contentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        contentRepository.deleteById(id);
    }
}