package com.CaseStudy.AutocompleteAPI;

import java.util.regex.Pattern;

/**
 * Class Helper contains methods that assist the program but are not the main goal of the application.
 **/

public class Helper {

    /**compares to strings without any case-sensitivity**/
    public static boolean containsIgnoreCase(String source, String target) {
        if (source == null || target == null) {
            return false;
        }
        return source.toLowerCase().contains(target.toLowerCase());
    }

    /**checks if a string has any digits with help of regex**/
    public static boolean containsDigits(String input) {
        boolean containsNumbers = Pattern.compile(".*\\d.*").matcher(input).matches();
        return containsNumbers;
    }
}
