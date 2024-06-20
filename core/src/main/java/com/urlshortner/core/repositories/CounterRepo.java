package com.urlshortner.core.repositories;

import com.urlshortner.core.domain.Counter;

public interface CounterRepo {
    Counter getCounter();
    Counter update(Counter counter);
    Counter save(Counter counter);
}
