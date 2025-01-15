package org.cronparser.validator;

import org.cronparser.exception.ValidatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionValidatorTest {

    @Test
    public void shouldThrowValidatorException_whenInvalidIncrementValue() {
        String[] expression = new String[]{"*", "*", "*/-5", "*", "*", "*"};

        ExpressionValidator validator = new ExpressionValidator();

        ValidatorException exception = assertThrows(ValidatorException.class, () -> {
            validator.validate(expression);
        });

        assertTrue(exception.getMessage().contains("Increment must be greater than 0 in expression: '*/-5'"));
    }
}