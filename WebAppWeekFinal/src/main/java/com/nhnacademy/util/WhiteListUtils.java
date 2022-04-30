package com.nhnacademy.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WhiteListUtils {
    private WhiteListUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> toList(String whitelist) {
        return Arrays.stream(whitelist.split("\n"))
                     .map(String::trim)
                     .collect(Collectors.toList());
    }

}
