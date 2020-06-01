package com.winllc.acme.common.util;

import org.apache.commons.lang3.RandomStringUtils;

public class SecurityUtil {
    public static String generateRandomString(int length) {
        boolean useLetters = true;
        boolean useNumbers = true;

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
