package ru.lesson6.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lesson6.demo.model.Greeting;

import java.util.List;

public interface ContentRepository extends CrudRepository<Greeting, Long> {

    List<Greeting> findAll();
}
