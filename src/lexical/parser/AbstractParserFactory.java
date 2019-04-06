package lexical.parser;

import lexical.parser.assign.AssignParser;
import lexical.parser.comment.CommentParser;
import lexical.parser.literal.LiteralParser;
import lexical.parser.numeric.NumericParser;

import java.util.HashMap;
import java.util.Map;

public class AbstractParserFactory {

    private static final Map<String, Parser> PARSERS = new HashMap<>();

    static {
        PARSERS.put(ParserNames.NUMERIC, new NumericParser());
        PARSERS.put(ParserNames.LITERAL, new LiteralParser());
        PARSERS.put(ParserNames.ASSIGN, new AssignParser());
        PARSERS.put(ParserNames.COMMENT, new CommentParser());
    }

    public static Parser getByName(String parserName) {
        return PARSERS.get(parserName);
    }
}
