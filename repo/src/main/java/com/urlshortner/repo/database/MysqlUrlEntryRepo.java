package com.urlshortner.repo.database;

import com.urlshortner.core.domain.UrlEntry;
import com.urlshortner.core.repositories.UrlEntryRepo;
import com.urlshortner.repo.domain.MysqlUrlEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MysqlUrlEntryRepo implements UrlEntryRepo {
    private final MysqlUrlEntryCrudRepository mysqlUrlEntryCrudRepository;

    @Autowired
    public MysqlUrlEntryRepo(MysqlUrlEntryCrudRepository mysqlUrlEntryCrudRepository) {
        this.mysqlUrlEntryCrudRepository = mysqlUrlEntryCrudRepository;
    }

    @Override
    public UrlEntry createUrl(UrlEntry urlEntry) {
        MysqlUrlEntry mysqlUrlEntry = new MysqlUrlEntry(urlEntry.getId(), urlEntry.getLongUrl());
        MysqlUrlEntry saved = mysqlUrlEntryCrudRepository.save(mysqlUrlEntry);
        return new UrlEntry(saved.getLongUrl(), saved.getId());
    }

    @Override
    public void deleteUrl(String key) {
        mysqlUrlEntryCrudRepository.deleteById(key);
    }

    @Override
    public UrlEntry findById(String id) {
        Optional<MysqlUrlEntry> mysqlUrlEntryOptional = mysqlUrlEntryCrudRepository.findById(id);
        return mysqlUrlEntryOptional
                .map(mysqlUrlEntry -> new UrlEntry(mysqlUrlEntry.getLongUrl(), mysqlUrlEntry.getId()))
                .orElse(null);
    }

    @Override
    public boolean existsById(String id) {
        return mysqlUrlEntryCrudRepository.existsById(id);
    }
}
