package com.urlshortner.core.impl;

import com.urlshortner.core.domain.CreateUrlEntry;
import com.urlshortner.core.domain.UrlEntry;
import com.urlshortner.core.exceptions.NotFoundException;
import com.urlshortner.core.repositories.UrlEntryRepo;
import com.urlshortner.core.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.urlshortner.core.service.UrlService;

import static com.urlshortner.core.utils.StringUtils.*;

@Service
public class UrlServiceImpl implements UrlService {
    private static final int KEY_LENGTH = 6;
    private final UrlEntryRepo urlEntryRepo;
    private final CounterService counterService;
    Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

    @Autowired
    public UrlServiceImpl(UrlEntryRepo urlEntryRepo, CounterService counterService) {
        this.urlEntryRepo = urlEntryRepo;
        this.counterService = counterService;
    }

    @Override
    public UrlEntry create(CreateUrlEntry createUrlEntry) {
        logger.info("Generating key for the long url {}", createUrlEntry.getLongUrl());
        long count = counterService.getCount();
        String key = addLeftPadding(base62Encode(count), KEY_LENGTH);
        logger.info("Key Generated successfully for the long url {}", createUrlEntry.getLongUrl());
        UrlEntry urlEntry = new UrlEntry(createUrlEntry.getLongUrl(), key);
        return urlEntryRepo.createUrl(urlEntry);
    }

    @Override
    public String getLongUrl(String key) throws NotFoundException {
        logger.info("Getting the long url for the key {}, used for redirecting", key);
        if (urlEntryRepo.existsById(key)) {
            UrlEntry urlEntry = urlEntryRepo.findById(key);
            return urlEntry.getLongUrl();
        }
        logger.info("Key {} not found", key);
        throw new NotFoundException();
    }

    @Override
    public void delete(String key) {
        urlEntryRepo.deleteUrl(key);
    }
}
