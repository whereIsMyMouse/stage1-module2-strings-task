package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> results = new ArrayList<>();
        int counter = 0;
        for (String delimiter : delimiters) {
            if (counter == 0) {
                Collections.addAll(results, source.split(delimiter));
            } else {
                List<String> temp = new ArrayList<>();
                for (String result : results){
                    Collections.addAll(temp, result.split(delimiter));
                }
                results.clear();
                results.addAll(temp);
            }
            counter++;
        }
        results.removeAll(Arrays.asList("", null));
        return results;
}
}
