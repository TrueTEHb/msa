package ru.lesson7.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lesson7.demo.exception.NotFoundException;
import ru.lesson7.demo.model.Greeting;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
class ContentRepositoryTest {

    @Autowired
    private ContentRepository repository;

    private List<Greeting> getGreetings() {
        Greeting first = new Greeting(1L, "First test message");
        Greeting second = new Greeting(2L, "Second test message");
        return Arrays.asList(first, second);
    }

    @Test
    void findById() {
        Greeting greeting = new Greeting(1L, "First test message");
        Greeting found = repository.findById(greeting.getId()).orElseThrow(NotFoundException::new);
        assertThat(greeting.getContent()).isEqualTo(found.getContent());
    }

    @Test
    void findAll() {
        assertThat(repository.findAll().size()).isEqualTo(getGreetings().size());
        assertThat(repository.findAll().get(1).getContent()).isEqualTo(getGreetings().get(1).getContent());
    }

    @Test
    void crete() {
        Greeting greeting = new Greeting(3L, "Third message");
        assertThat(repository.save(greeting)).isEqualTo(repository.findById(greeting.getId()).orElseThrow());
    }

    @Test
    void remove() {
        repository.delete(getGreetings().get(1));
        assertThrows(NotFoundException.class, () -> repository.findById(2L).orElseThrow(NotFoundException::new));
        assertThat(repository.findAll().size()).isNotEqualTo(getGreetings().size());
    }
}