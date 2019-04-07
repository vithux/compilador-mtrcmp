package token.builders;

import token.TokenBuilder;
import token.TokenType;

public class AssignTokenBuilder extends TokenBuilder {

    private static final String ASSIGN_LEXEME = "<-";

    public AssignTokenBuilder() {
        this.setTokenType(TokenType.ASSIGN);
        this.setLexeme(ASSIGN_LEXEME);
    }
}
