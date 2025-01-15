package org.cronparser;

import org.cronparser.exception.ValidatorException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CronParserApplicationTest {

    @Test
    public void shouldReturnValidResult_whenAllFieldsAreAsterisk() throws ValidatorException {
        CronParserApplication app = new CronParserApplication();

        String input = "* * * * * /usr/bin/find";
        String expectedOutput = "minute 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59\n" +
                "hour 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23\n" +
                "day of month 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31\n" +
                "month 1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week 0 1 2 3 4 5 6 7\n" +
                "command /usr/bin/find";
        Assertions.assertEquals(expectedOutput, app.processInputs(input).toString());
    }

    @Test
    public void shouldReturnValidResult_whenAllRangesIsSpecified() throws ValidatorException {
        CronParserApplication app = new CronParserApplication();

        String input = "0-1 9-17 5-6 1-2 1-2 /usr/bin/find";
        String expectedOutput = "minute 0 1\n" +
                "hour 9 10 11 12 13 14 15 16 17\n" +
                "day of month 5 6\n" +
                "month 1 2\n" +
                "day of week 1 2\n" +
                "command /usr/bin/find";
        Assertions.assertEquals(expectedOutput, app.processInputs(input).toString());
    }

    @Test
    public void shouldReturnValidResult_whenAllStepsIsSpecified() throws ValidatorException {
        CronParserApplication app = new CronParserApplication();

        String input = "*/10 */2 */2 */3 */1 /usr/bin/find";
        String expectedOutput = "minute 0 10 20 30 40 50\n" +
                "hour 0 2 4 6 8 10 12 14 16 18 20 22\n" +
                "day of month 1 3 5 7 9 11 13 15 17 19 21 23 25 27 29 31\n" +
                "month 1 4 7 10\n" +
                "day of week 0 1 2 3 4 5 6 7\n" +
                "command /usr/bin/find";
        Assertions.assertEquals(expectedOutput, app.processInputs(input).toString());
    }

    @Test
    public void shouldReturnValidResult_whenAllNumversIsSpecified() throws ValidatorException {
        CronParserApplication app = new CronParserApplication();

        String input = "10 2 2 3 1 /usr/bin/find";
        String expectedOutput = "minute 10\n" +
                "hour 2\n" +
                "day of month 2\n" +
                "month 3\n" +
                "day of week 1\n" +
                "command /usr/bin/find";
        Assertions.assertEquals(expectedOutput, app.processInputs(input).toString());
    }

    @Test
    public void shouldReturnValidResult_whenSpecificTimeIsProvided() throws ValidatorException {
        CronParserApplication app = new CronParserApplication();

        String input = "30 8 * * * /usr/bin/backup.sh";
        String expectedOutput = "minute 30\n" +
                "hour 8\n" +
                "day of month 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31\n" +
                "month 1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week 0 1 2 3 4 5 6 7\n" +
                "command /usr/bin/backup.sh";
        Assertions.assertEquals(expectedOutput, app.processInputs(input).toString());
    }

    @Test
    public void shouldReturnValidResult_whenDayOfMonthIsSpecified() throws ValidatorException {
        CronParserApplication app = new CronParserApplication();

        String input = "0 0 1 * * /usr/bin/cleanup.sh";
        String expectedOutput = "minute 0\n" +
                "hour 0\n" +
                "day of month 1\n" +
                "month 1 2 3 4 5 6 7 8 9 10 11 12\n" +
                "day of week 0 1 2 3 4 5 6 7\n" +
                "command /usr/bin/cleanup.sh";
        Assertions.assertEquals(expectedOutput, app.processInputs(input).toString());
    }

    @Test
    public void shouldReturnValidResult_whenMonthRangeIsSpecified() throws ValidatorException {
        CronParserApplication app = new CronParserApplication();

        String input = "0 */2 */3 1-6 * /usr/bin/schedule-task.sh";
        String expectedOutput = "minute 0\n" +
                "hour 0 2 4 6 8 10 12 14 16 18 20 22\n" +
                "day of month 1 4 7 10 13 16 19 22 25 28 31\n" +
                "month 1 2 3 4 5 6\n" +
                "day of week 0 1 2 3 4 5 6 7\n" +
                "command /usr/bin/schedule-task.sh";
        Assertions.assertEquals(expectedOutput, app.processInputs(input).toString());
    }

    @Test
    public void shouldReturnValidResult_whenHourRangeIsSpecified() throws ValidatorException {
        CronParserApplication app = new CronParserApplication();

        String input = "0 9-17 * * * /usr/bin/find";
        String expectedOutput = "minute 0\n" +
                    "hour 9 10 11 12 13 14 15 16 17\n" +
                    "day of month 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31\n" +
                    "month 1 2 3 4 5 6 7 8 9 10 11 12\n" +
                    "day of week 0 1 2 3 4 5 6 7\n" +
                    "command /usr/bin/find";
        Assertions.assertEquals(expectedOutput, app.processInputs(input).toString());
    }

    @Test
    public void shouldReturnValidResult_whenInvalidMinuteValueIsProvided() {
        CronParserApplication app = new CronParserApplication();

        String input = "60 * * * * /usr/bin/find";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }

    @Test
    public void shouldThrowException_whenMinuteOverflow() {
        CronParserApplication app = new CronParserApplication();

        String input = "61 * * * * /usr/bin/find";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }

    @Test
    public void shouldThrowException_whenHourOverflow() {
        CronParserApplication app = new CronParserApplication();

        String input = "0 24 * * * /usr/bin/find";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }

    @Test
    public void shouldThrowException_whenDayOfMonthInvalid() {
        CronParserApplication app = new CronParserApplication();

        String input = "0 0 32 * * /usr/bin/find";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }

    @Test
    public void shouldThrowException_whenMonthOverflow() {
        CronParserApplication app = new CronParserApplication();

        String input = "0 0 1 13 * /usr/bin/find";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }

    @Test
    public void shouldThrowException_whenDayOfWeekInvalid() {
        CronParserApplication app = new CronParserApplication();

        String input = "0 0 1 * 8 /usr/bin/find";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }

    @Test
    public void shouldThrowException_whenMissingCommand() {
        CronParserApplication app = new CronParserApplication();

        String input = "* * * * *";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }

    @Test
    public void shouldThrowException_whenInvalidCharacterInField() {
        CronParserApplication app = new CronParserApplication();

        String input = "* * * A * /usr/bin/find";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }

    @Test
    public void shouldThrowException_whenAllZeroes() {
        CronParserApplication app = new CronParserApplication();

        String input = "0 0 0 0 0 /usr/bin/find";
        Assertions.assertThrows(ValidatorException.class, () -> {
            app.processInputs(input);
        });
    }
}