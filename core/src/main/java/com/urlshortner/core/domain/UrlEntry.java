package com.urlshortner.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UrlEntry {
    String longUrl;
    String id;
}
