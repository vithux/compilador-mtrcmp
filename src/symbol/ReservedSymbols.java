package symbol;

import token.Token;
import token.TokenBuilder;
import token.TokenType;
import token.builders.LogicalOperationTokenBuilder;
import token.builders.LogicalValueTokenBuilder;
import token.builders.TypeTokenBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ReservedSymbols {

    private static final Map<String, Symbol> symbols = new HashMap<>();

    static {
        Map<TokenType, String> map = new HashMap<>();

        map.put(TokenType.IF, "if");
        map.put(TokenType.TO, "to");
        map.put(TokenType.FOR, "for");
        map.put(TokenType.END, "end");
        map.put(TokenType.ELSE, "else");
        map.put(TokenType.THEN, "then");
        map.put(TokenType.WHILE, "while");
        map.put(TokenType.BEGIN, "begin");
        map.put(TokenType.DECLARE, "declare");
        map.put(TokenType.PROGRAM, "program");
        map.put(TokenType.END_PROG, "end_prog");

        for (String lexeme : Arrays.asList("true", "false")) {
            symbols.put(lexeme, new Symbol(lexeme, SymbolType.KEYWORD, new LogicalValueTokenBuilder().setLexeme(lexeme)));
        }

        for (String lexeme : Arrays.asList("and", "not", "or")) {
            symbols.put(lexeme, new Symbol(lexeme, SymbolType.KEYWORD, new LogicalOperationTokenBuilder().setLexeme(lexeme)));
        }

        for (String lexeme : Arrays.asList("bool", "text", "int", "float")) {
            if ("float".equals(lexeme )) {
                symbols.put(lexeme, new Symbol(lexeme, SymbolType.KEYWORD, new TypeTokenBuilder().setLexeme("int")));
            } else {
                symbols.put(lexeme, new Symbol(lexeme, SymbolType.KEYWORD, new TypeTokenBuilder().setLexeme(lexeme)));
            }
        }

        for (Map.Entry<TokenType, String> entry : map.entrySet()) {
            symbols.put(entry.getValue(), new Symbol(entry.getValue(), SymbolType.KEYWORD, new TokenBuilder().setLexeme(entry.getValue()).setTokenType(entry.getKey())));
        }
    }

    public static Map<String, Symbol> getSymbols() {
       return symbols;
    }
}