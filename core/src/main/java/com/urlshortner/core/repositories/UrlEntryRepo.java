package com.urlshortner.core.repositories;

import com.urlshortner.core.domain.UrlEntry;

public interface UrlEntryRepo {
    UrlEntry createUrl(UrlEntry urlEntry);
    void deleteUrl(String key);
    UrlEntry findById(String id);
    boolean existsById(String id);
}
