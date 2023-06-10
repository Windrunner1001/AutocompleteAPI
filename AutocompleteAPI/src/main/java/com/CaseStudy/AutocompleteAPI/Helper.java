package com.CaseStudy.AutocompleteAPI;

import java.util.regex.Pattern;

/**
 * Class Helper contains methods that assist the program but are not the main goal of the application.
 **/

public class Helper {

    /**
     * compares to strings without any case-sensitivity
     * @param source contains the first compare string
     * @param target contains the second string, the first one needs to be compared too
     * @return - boolean value - if true, both strings match, if false not
     **/
    public static boolean containsIgnoreCase(String source, String target) {
        if (source == null || target == null) {
            return false;
        }
        return source.toLowerCase().contains(target.toLowerCase());
    }

    /**
     * checks if a string has any digits with help of regex
     * @param input contains the string which should be checked
     * @return containsNumbers - true if string contains digits, false if not
     **/
    public static boolean containsDigits(String input) {
        boolean containsNumbers = Pattern.compile(".*\\d.*").matcher(input).matches();
        return containsNumbers;
    }
}
