package lexical;

import token.Token;
import token.TokenType;

public class LexicalAnalyzer {

    public static Token nextToken() {


        return new Token(TokenType.END, "end", 1, 1);
    }

}
