package ru.lesson4.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.lesson4.demo.model.Greeting;

import java.util.List;

public interface ContentRepository extends CrudRepository<Greeting, Long> {

    List<Greeting> findAll();
}
