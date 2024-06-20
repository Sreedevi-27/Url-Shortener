package com.urlshortner.core.repositories;

import com.urlshortner.core.domain.UrlEntry;

public class DummyUrlEntryRepo implements UrlEntryRepo{
    @Override
    public UrlEntry createUrl(UrlEntry urlEntry) {
        return new UrlEntry(urlEntry.getLongUrl(), urlEntry.getId());
    }

    @Override
    public void deleteUrl(String key) {

    }

    @Override
    public UrlEntry findById(String id) {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
