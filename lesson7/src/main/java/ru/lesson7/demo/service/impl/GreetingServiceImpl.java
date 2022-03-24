package ru.lesson7.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.lesson7.demo.exception.NotFoundException;
import ru.lesson7.demo.model.Greeting;
import ru.lesson7.demo.repository.ContentRepository;
import ru.lesson7.demo.service.GreetingService;

import java.util.List;

@Service
@EnableTransactionManagement
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Greeting save(Greeting greeting) {
        return contentRepository.save(greeting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Greeting findById(Long id) {
        return contentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Greeting> findAll() {
        return contentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        contentRepository.deleteById(id);
    }
}