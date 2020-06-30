package com.epam.github.util;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RandomStringUtils {

    public static String getRandomName() {
        return randomAlphabetic(10);
    }
}
