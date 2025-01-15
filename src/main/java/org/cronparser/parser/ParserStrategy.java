package org.cronparser.parser;

import org.cronparser.exception.ValidatorException;

import static org.cronparser.utils.Constants.RANGE_DELIMITER;

public interface ParserStrategy {
    String parse(String input, int low, int high);
    default String rangeParser(String input, int low, int high) {
        try {
            StringBuilder result = new StringBuilder();
            String[] rangeParts = input.split(RANGE_DELIMITER);
            int start = Integer.parseInt(rangeParts[0]);
            int end = Integer.parseInt(rangeParts[1]);
            if(start > end || start<low || end>high)
                throw new ValidatorException("Invalid start, end ranges for range parser");
            for (int i = start; i <= end; i++) {
                result.append(i).append(" ");
            }

            return result.toString().trim();
        } catch (Exception e) {
            throw new ValidatorException(e.getMessage());
        }
    }
}
