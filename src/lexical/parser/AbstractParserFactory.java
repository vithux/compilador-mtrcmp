/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package lexical.parser;

import lexical.parser.assign.AssignParser;
import lexical.parser.comment.CommentParser;
import lexical.parser.identifier.IdentifierParser;
import lexical.parser.literal.LiteralParser;
import lexical.parser.numeric.NumericParser;
import lexical.parser.relop.RelopParser;

import java.util.HashMap;
import java.util.Map;

public class AbstractParserFactory {

    private static final Map<String, Parser> PARSERS = new HashMap<>();

    static {
        PARSERS.put(ParserNames.ASSIGN, new AssignParser());
        PARSERS.put(ParserNames.COMMENT, new CommentParser());
        PARSERS.put(ParserNames.IDENTIFIER, new IdentifierParser());
        PARSERS.put(ParserNames.LITERAL, new LiteralParser());
        PARSERS.put(ParserNames.NUMERIC, new NumericParser());
        PARSERS.put(ParserNames.RELOP, new RelopParser());
    }

    public static Parser getByName(String parserName) {
        return PARSERS.get(parserName);
    }
}
