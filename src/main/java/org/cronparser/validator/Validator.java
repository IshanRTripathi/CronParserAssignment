package org.cronparser.validator;

import org.cronparser.exception.ValidatorException;

public interface Validator {
    void validate(String[] expression) throws ValidatorException;
}
