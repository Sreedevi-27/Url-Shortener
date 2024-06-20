package com.urlshortner.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
    static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static String base62Encode(long value) {
        logger.info("Generating base62 code for the value {}", value);
        int base = 62;
        String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append(base62.charAt((int)(value % base)));
            value /= base;
        }
        logger.info("Base62 code for the value {} is {}", value, sb);
        return sb.toString();
    }

    public static String addLeftPadding(String s, int len) {
        logger.info("Base62 code before Left Padding is {}", s);
        StringBuilder stringBuilder = new StringBuilder(s);
        while (stringBuilder.length() < len) {
            stringBuilder.append('0');
        }

        String leftPaddedString = stringBuilder.reverse().toString();
        logger.info("Base62 code after Left Padding is {}", leftPaddedString);
        return leftPaddedString;
    }
}
