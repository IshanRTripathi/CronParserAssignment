package org.cronparser.parser;

public class StepParserStrategy implements ParserStrategy{
    @Override
    public String parse(String input, int low, int high) {
        StringBuilder result = new StringBuilder();
        String[] stepParts = input.split("/");
        String rangePart = stepParts[0].equals("*") ? low + "-" + high : stepParts[0];

        String[] range = rangePart.split("-");
        int start = Integer.parseInt(range[0]);
        int end = Integer.parseInt(range[1]);
        int step = Integer.parseInt(stepParts[1]);

        for (int i = start; i <= end; i += step) {
            result.append(i).append(" ");
        }

        return result.toString().trim();
    }
}
