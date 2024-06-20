package com.urlshortner.core.repositories;

import com.urlshortner.core.service.CounterService;

public class DummyCounterService implements CounterService {
    @Override
    public long getCount() {
        return 1;
    }
}
