package com.archetype.api.util;


public class StringUtil {
    private StringUtil() {}
    public static boolean isEmpty(String value) {
        return value == null || value.isBlank() || value.isEmpty();
    }

}
