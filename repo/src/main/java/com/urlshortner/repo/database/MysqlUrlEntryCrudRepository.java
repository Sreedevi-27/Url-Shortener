package com.urlshortner.repo.database;

import com.urlshortner.repo.domain.MysqlUrlEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlUrlEntryCrudRepository extends CrudRepository<MysqlUrlEntry, String> {
}
