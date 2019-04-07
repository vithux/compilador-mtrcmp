package token.builders;

import token.TokenBuilder;
import token.TokenType;

public class LogicalOperationTokenBuilder extends TokenBuilder {

    public LogicalOperationTokenBuilder() {
        this.setTokenType(TokenType.LOGIC_OP);
    }
}
