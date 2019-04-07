package token.builders;

import token.TokenBuilder;
import token.TokenType;

public class TypeTokenBuilder extends TokenBuilder {

    public TypeTokenBuilder() {
        this.setTokenType(TokenType.TYPE);
    }
}
