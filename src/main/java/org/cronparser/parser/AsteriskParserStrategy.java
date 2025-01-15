package org.cronparser.parser;

public class AsteriskParserStrategy implements ParserStrategy{
    @Override
    public String parse(String input, int low, int high) {
        StringBuilder result = new StringBuilder();
        for (int i = low; i <= high; i++) {
            result.append(i).append(" ");
        }
        return result.toString().trim();
    }
}
