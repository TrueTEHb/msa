package ru.lesson7.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lesson7.demo.model.Greeting;

import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<Greeting, Long> {

    List<Greeting> findAll();
}
