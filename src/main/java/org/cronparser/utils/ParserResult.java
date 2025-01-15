package org.cronparser.utils;

import static org.cronparser.utils.Constants.*;

public class ParserResult {
    private final String minute;
    private final String hour;
    private final String dayOfMonth;
    private final String month;
    private final String dayOfWeek;
    private final String command;

    public ParserResult(String minute, String hour, String dayOfMonth, String month, String dayOfWeek, String command) {
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.command = command;
    }

    @Override
    public String toString() {
        return MINUTE + " " + minute +
                "\n" + HOUR + " " + hour +
                "\n" + DAY_OF_MONTH + " " + dayOfMonth +
                "\n" + MONTH + " " + month +
                "\n" + DAY_OF_WEEK + " " + dayOfWeek +
                "\n" + COMMAND + " " + command;
    }
}