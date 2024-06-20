package com.urlshortner.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Counter {
    long id;
    long startValue;
    long endValue;
    boolean used;
}
