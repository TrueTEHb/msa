package ru.lesson5.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lesson5.demo.model.Greeting;

import java.util.List;

public interface ContentRepository extends CrudRepository<Greeting, Long> {

    List<Greeting> findAll();
}
