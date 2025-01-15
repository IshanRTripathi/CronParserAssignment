package org.cronparser;

import org.cronparser.exception.ValidatorException;
import org.cronparser.parser.Parser;
import org.cronparser.utils.ParserResult;
import org.cronparser.validator.ExpressionValidator;
import org.cronparser.validator.Validator;

import java.util.Arrays;

public class CronParserApplication {

    public static void main(String[] args) throws ValidatorException {
        CronParserApplication application = new CronParserApplication();

        if (args.length == 0 || args[0].isEmpty()) {
            throw new IllegalArgumentException("Program expected a cron expression as arguments");
        }

        System.out.println("Input\n"+args[0]);
        System.out.println("Output\n"+application.processInputs(args[0]));
    }


    public ParserResult processInputs(String input) throws ValidatorException {
        String[] expressionParts = splitInput(input);

        validateExpression(expressionParts);

        return parseExpression(expressionParts);
    }

    private String[] splitInput(String input) throws ValidatorException {
        if (input == null || input.trim().isEmpty()) {
            throw new ValidatorException("Input cannot be null or empty");
        }
        return input.split("\\s+");
    }

    private void validateExpression(String[] expression) throws ValidatorException {
        Validator validator = new ExpressionValidator();
        validator.validate(expression);
    }

    private ParserResult parseExpression(String[] expression) {
        Parser parser = new Parser();
        return parser.parse(expression);
    }

}