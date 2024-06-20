package com.urlshortner.repo.database;

import com.urlshortner.core.domain.Counter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MysqlCounterRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MysqlCounterRepo mysqlCounterRepo;

    @Test
    void shouldGetUnusedCounter() {
        // run
        Counter counter = mysqlCounterRepo.getCounter();

        // verify
        assertEquals(1, counter.getId());
        assertEquals(1, counter.getStartValue());
        assertEquals(10000, counter.getEndValue());
        assertEquals(false, counter.isUsed());
    }


    @Test
    void update(){
        // setup
        Counter counter = mysqlCounterRepo.getCounter();
        counter.setUsed(true);
        counter.setStartValue(2);
        counter.setEndValue(3);

        // run
        counter = mysqlCounterRepo.update(counter);

        // verify
        assertEquals(true, counter.isUsed());
        assertEquals(2, counter.getStartValue());
        assertEquals(3, counter.getEndValue());
    }
}
