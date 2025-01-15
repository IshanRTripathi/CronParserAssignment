package org.cronparser.parser;

import org.cronparser.utils.ParserResult;

import static org.cronparser.utils.Constants.*;

public class Parser {
    public ParserResult parse(String[] expression) {
        String minute = parsePart(expression[0], MINUTES_MIN, MINUTES_MAX);
        String hour = parsePart(expression[1], HOURS_MIN, HOURS_MAX);
        String dayOfMonth = parsePart(expression[2], DAY_OF_MONTH_MIN, DAY_OF_MONTH_MAX);
        String month = parsePart(expression[3], MONTHS_MIN, MONTHS_MAX);
        String dayOfWeek = parsePart(expression[4], DAY_OF_WEEK_MIN, DAY_OF_WEEK_MAX);

        return new ParserResult(minute, hour, dayOfMonth, month, dayOfWeek, expression[5]);
    }

    public String parsePart(String input, int min, int max) {
        String result;
        if (input.contains(COMMA_DELIMITER)) {
            result = new CommaParserStrategy().parse(input, min, max);
        } else if (input.contains(INCREMENT_DELIMITER)) {
            result = new StepParserStrategy().parse(input, min, max);
        } else if (input.contains(RANGE_DELIMITER)) {
            result = new RangeParserStrategy().parse(input, min, max);
        } else if (input.equals(ALL_DELIMITER)) {
            result = new AsteriskParserStrategy().parse("", min, max);
        } else {
            result = input;
        }
        return result;
    }
}

