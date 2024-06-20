package com.urlshortner.core.service;

import com.urlshortner.core.domain.CreateUrlEntry;
import com.urlshortner.core.domain.UrlEntry;
import com.urlshortner.core.exceptions.NotFoundException;

public interface UrlService {
    UrlEntry create(CreateUrlEntry createUrlEntry);
    void delete(String key);
    String getLongUrl(String key) throws NotFoundException;;
}
