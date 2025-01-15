package org.cronparser.utils;

import java.util.List;

public final class Constants {
    public static final String ALL_DELIMITER = "*";
    public static final String RANGE_DELIMITER = "-";
    public static final String INCREMENT_DELIMITER = "/";
    public static final String COMMA_DELIMITER = ",";

    // Constants for minutes field
    public static final int MINUTES_MIN = 0;
    public static final int MINUTES_MAX = 59;

    // Constants for hours field
    public static final int HOURS_MIN = 0;
    public static final int HOURS_MAX = 23;

    // Constants for day of month field
    public static final int DAY_OF_MONTH_MIN = 1;
    public static final int DAY_OF_MONTH_MAX = 31;

    // Constants for months field
    public static final int MONTHS_MIN = 1;
    public static final int MONTHS_MAX = 12;

    // Constants for day of week field
    public static final int DAY_OF_WEEK_MIN = 0;
    public static final int DAY_OF_WEEK_MAX = 7;

    public static String MINUTE = "minute";
    public static String HOUR = "hour";
    public static String DAY_OF_MONTH = "day of month";
    public static String MONTH = "month";
    public static String DAY_OF_WEEK = "day of week";
    public static String COMMAND = "command";
    public static final List<String> CRON_FIELD_NAMES = List.of(
            MINUTE,
            HOUR,
            DAY_OF_MONTH,
            MONTH,
            DAY_OF_WEEK,
            COMMAND
    );
}
