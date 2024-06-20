package com.urlshortner.core.impl;

import com.urlshortner.core.domain.Counter;
import com.urlshortner.core.repositories.CounterRepo;
import com.urlshortner.core.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CounterServiceImpl implements CounterService {
    private final CounterRepo counterRepo;
    private AtomicLong count;
    private long endValue;
    private boolean isSet;

    Logger logger = LoggerFactory.getLogger(CounterServiceImpl.class);

    @Autowired
    public CounterServiceImpl(CounterRepo counterRepo) {
        this.counterRepo = counterRepo;
        this.isSet = false;
    }

    public long getCount() {
        if (!isSet) {
            setCount();
        }
        long val = count.getAndIncrement();
        if (val == endValue) {
            setCount();
        }
        logger.info("Count is {}", val);
        return val;
    }

    @Transactional
    private void setCount() {
        Counter counter = counterRepo.getCounter();
        long startValue = counter.getStartValue();
        this.endValue = counter.getEndValue();
        this.count = new AtomicLong(startValue);
        counter.setUsed(true);
        counterRepo.update(counter);
        isSet = true;
        this.counterRepo.save(
                new Counter(0, this.endValue + 1, this.endValue + 10000, false)
        );
    }
}
