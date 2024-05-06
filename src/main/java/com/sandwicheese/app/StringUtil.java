package com.sandwicheese.app;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtil {
    public static String convertToTitleCaseSplitting(String text, String separator) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        return Arrays
                .stream(text.split(separator))
                .map(word -> word.isEmpty()
                        ? word
                        : Character.toTitleCase(word.charAt(0)) + word
                                .substring(1)
                                .toLowerCase())
                .collect(Collectors.joining(separator));
    }
}
