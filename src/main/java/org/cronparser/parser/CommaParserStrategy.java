package org.cronparser.parser;

import static org.cronparser.utils.Constants.COMMA_DELIMITER;
import static org.cronparser.utils.Constants.RANGE_DELIMITER;

public class CommaParserStrategy implements ParserStrategy{
    @Override
    public String parse(String input, int low, int high) {
        StringBuilder result = new StringBuilder();
        String[] parts = input.split(COMMA_DELIMITER);

        for (String part : parts) {
            if (part.contains(RANGE_DELIMITER)) {
                result.append(rangeParser(part, low, high)).append(" ");
            } else {
                result.append(part).append(" ");
            }
        }

        return result.toString().trim();
    }
}
