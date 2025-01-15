package org.cronparser.parser;

public class RangeParserStrategy implements ParserStrategy{
    @Override
    public String parse(String input, int low, int high) {
        return rangeParser(input, low, high);
    }
}
