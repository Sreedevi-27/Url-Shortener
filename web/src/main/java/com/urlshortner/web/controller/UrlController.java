package com.urlshortner.web.controller;

import com.urlshortner.core.domain.CreateUrlEntry;
import com.urlshortner.core.exceptions.NotFoundException;
import com.urlshortner.web.domain.RestCreateUrlEntry;
import com.urlshortner.web.domain.RestUrlEntry;
import com.urlshortner.core.domain.UrlEntry;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;
import com.urlshortner.core.service.UrlService;


@RestController
@RequestMapping("/")
public class UrlController {
    private final UrlService urlService;
    Logger logger = LoggerFactory.getLogger(UrlController.class);
    private final String SHORT_URL="localhost:8080/";

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/")
    public RestUrlEntry createUrlEntry(@RequestBody RestCreateUrlEntry restCreateUrlEntry) {
        logger.info("Creating a short url for the long url : {}", restCreateUrlEntry.getLongUrl());
        CreateUrlEntry createUrlEntry = new CreateUrlEntry(restCreateUrlEntry.getLongUrl()); // rest -> core
        UrlEntry urlEntry = urlService.create(createUrlEntry); // core
        logger.info("Short url created successfully - {}", SHORT_URL+urlEntry.getId());
        return new RestUrlEntry(urlEntry.getLongUrl(), SHORT_URL + urlEntry.getId()); // rest
    }

    @DeleteMapping("/{key}")
    public void deleteUrlEntry(@PathVariable("key") String key) {
        logger.info("Delete the short url key - {}",key);
        urlService.delete(key);
        logger.info("Short url key {} deleted successfully",key);
    }

    @GetMapping("/{key}")
    public RedirectView redirectView(@PathVariable("key") String key) {
        try {
            logger.info("Redirecting the long url");
            return new RedirectView(urlService.getLongUrl(key));
        } catch (NotFoundException notFoundException) {
            logger.error("Key {} not found", key);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}