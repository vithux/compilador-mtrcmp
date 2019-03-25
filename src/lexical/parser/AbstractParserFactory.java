package lexical.parser;

import lexical.parser.numeric.NumericParser;

import java.util.HashMap;
import java.util.Map;

public class AbstractParserFactory {

    private static final Map<String, Parser> PARSERS = new HashMap<>();

    static {
        PARSERS.put(ParserNames.NUMERIC, new NumericParser());
    }

    public static Parser getByName(String parserName) {
        return PARSERS.get(parserName);
    }
}
