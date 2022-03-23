package ru.lesson7.demo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
class ContentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContentRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
    }
}