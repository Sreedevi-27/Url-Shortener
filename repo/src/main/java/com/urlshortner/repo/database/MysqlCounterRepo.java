package com.urlshortner.repo.database;

import com.urlshortner.core.domain.Counter;
import com.urlshortner.core.repositories.CounterRepo;
import com.urlshortner.repo.domain.MysqlCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlCounterRepo implements CounterRepo {
    MysqlCounterRepoCrudRepository mysqlCounterRepoCrudRepository;

    @Autowired
    public MysqlCounterRepo(MysqlCounterRepoCrudRepository mysqlCounterRepoCrudRepository) {
        this.mysqlCounterRepoCrudRepository = mysqlCounterRepoCrudRepository;
    }

    @Override
    public Counter getCounter() {
        MysqlCounter mysqlCounter = mysqlCounterRepoCrudRepository.findAvailableCounter();
        if (mysqlCounter == null) {
            mysqlCounter = new MysqlCounter(0, 1, 10000, false);
            mysqlCounter = mysqlCounterRepoCrudRepository.save(mysqlCounter);
        }
        return new Counter(
                mysqlCounter.getId(),
                mysqlCounter.getStartValue(),
                mysqlCounter.getEndValue(),
                mysqlCounter.isUsed()
        );
    }

    @Override
    public Counter update(Counter counter) {
        return this.mysqlCounterRepoCrudRepository.findById(counter.getId()).map(mysqlCounter -> {
                    mysqlCounter.setUsed(counter.isUsed());
                    mysqlCounter.setStartValue(counter.getStartValue());
                    mysqlCounter.setEndValue(counter.getEndValue());
                    MysqlCounter saved = this.mysqlCounterRepoCrudRepository.save(mysqlCounter);
                    return new Counter(saved.getId(), saved.getStartValue(), saved.getEndValue(), saved.isUsed());
                })
                .orElse(null);
    }

    @Override
    public Counter save(Counter counter) {
        MysqlCounter mysqlCounter = new MysqlCounter(
                0, counter.getStartValue(), counter.getEndValue(), counter.isUsed());
        MysqlCounter saved = this.mysqlCounterRepoCrudRepository.save(mysqlCounter);
        return new Counter(saved.getId(), saved.getStartValue(), saved.getEndValue(), saved.isUsed());
    }
}
