package org.koet;

import java.util.regex.Pattern;

public class RegexPattern {


    static public Pattern PATTERN_BRACET_NUMBER() {
        return Pattern.compile("\\[\\d+\\]");
    }

}
