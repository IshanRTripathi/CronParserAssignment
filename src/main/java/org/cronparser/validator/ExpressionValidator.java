package org.cronparser.validator;

import org.cronparser.exception.ValidatorException;
import org.cronparser.enums.TimeComponent;

import static org.cronparser.utils.Constants.*;

public class ExpressionValidator implements Validator {

    @Override
    public void validate(String[] parts) throws ValidatorException {
        if (parts == null || parts.length != CRON_FIELD_NAMES.size()) {
            throw new ValidatorException("Expected 6 space-separated arguments, example: */15 9-19/3 12-28/4 * * /usr/bin/find");
        }

        for (int i = 0; i < parts.length - 1; i++) {
            validateIndividualTime(parts[i], i);
        }
        validateCommand(parts[5]);
    }

    private void validateCommand(String command) {
        if(command == null || command.isEmpty()) {
            throw new ValidatorException("Command should not be empty!");
        }
    }

    private void validateIndividualTime(String part, int index) throws ValidatorException {
        String[] components = part.split(COMMA_DELIMITER);
        int[] bounds = TimeComponent.values()[index].getBounds();

        for (String component : components) {
            if (component.trim().isEmpty()) {
                throw new ValidatorException("Empty value in expression part at index: " + index);
            }

            try {
                if (!isValidValue(component, bounds)) {
                    throw new ValidatorException("Invalid bounds or value in expression part: '" + component + "' at index: " + index);
                }
            } catch (NumberFormatException e) {
                throw new ValidatorException("Invalid numeric value in expression part: '" + component + "' at index: " + index + " - " + e.getMessage());
            }
        }
    }


    private boolean isValidValue(String value, int[] bounds) {
        if (value.equals(ALL_DELIMITER)) {
            return true;
        } else if (value.contains(INCREMENT_DELIMITER)) {
            return validateIncrementExpression(value, bounds);
        } else if (value.contains(RANGE_DELIMITER)) {
            return validateRangeExpression(value, bounds);
        } else {
            return validateSingleValue(value, bounds);
        }
    }

    private boolean validateIncrementExpression(String value, int[] bounds) {
        try {
            String[] parts = value.split(INCREMENT_DELIMITER);
            if (parts.length != 2 || parts[1].isEmpty()) {
                throw new ValidatorException("Invalid increment value in expression: '" + value + "'");
            }

            String rangePart = parts[0];
            int increment = Integer.parseInt(parts[1]);

            int rangeStart = bounds[0];
            int rangeEnd = bounds[1];

            if (!rangePart.equals(ALL_DELIMITER)) {
                if (rangePart.contains(RANGE_DELIMITER)) {
                    String[] rangeBounds = rangePart.split(RANGE_DELIMITER);
                    rangeStart = Integer.parseInt(rangeBounds[0]);
                    rangeEnd = Integer.parseInt(rangeBounds[1]);

                    if (rangeStart < bounds[0] || rangeEnd > bounds[1] || rangeStart > rangeEnd) {
                        return false;
                    }
                } else {
                    rangeStart = Integer.parseInt(rangePart);
                    rangeEnd = rangeStart;

                    if (rangeStart < bounds[0] || rangeStart > bounds[1]) {
                        return false;
                    }
                }
            }

            if (increment <= 0) {
                throw new ValidatorException("Increment must be greater than 0 in expression: '" + value + "'");
            }

            for (int i = rangeStart; i <= rangeEnd; i += increment) {
                if (i >= bounds[0] && i <= bounds[1]) {
                    return true;
                }
            }

            return false;
        } catch (NumberFormatException e) {
            throw new ValidatorException("Invalid number format in increment expression: '" + value +" , " +e.getMessage());
        }
    }


    private boolean validateRangeExpression(String value, int[] bounds) {
        try {
            String[] parts = value.split(RANGE_DELIMITER);
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            return start >= bounds[0] && start <= end && end <= bounds[1];
        } catch (NumberFormatException e) {
            throw new ValidatorException("Invalid range value in expression: '" + value + " , " + e.getMessage());
        }
    }

    private boolean validateSingleValue(String value, int[] bounds) {
        try {
            int intValue = Integer.parseInt(value);
            return intValue >= bounds[0] && intValue <= bounds[1];
        } catch (NumberFormatException e) {
            throw new ValidatorException("Invalid single value in expression: " + value + ", " + e.getMessage());
        }
    }
}
