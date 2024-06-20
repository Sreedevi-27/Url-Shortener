package com.urlshortner.repo.database;

import com.urlshortner.repo.domain.MysqlCounter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlCounterRepoCrudRepository extends CrudRepository<MysqlCounter, Long> {
    @Query(value = "SELECT * FROM counter c WHERE c.used = false LIMIT 1", nativeQuery = true)
    MysqlCounter findAvailableCounter();
}
