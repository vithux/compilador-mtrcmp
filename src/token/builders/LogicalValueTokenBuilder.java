package token.builders;

import token.TokenBuilder;
import token.TokenType;

public class LogicalValueTokenBuilder extends TokenBuilder {

    public LogicalValueTokenBuilder() {
        this.setTokenType(TokenType.LOGIC_VAL);
    }
}
