package lexical.parser.numeric;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import token.TokenBuilder;
import token.TokenType;

class NumericTokenBuilderFactory {

    private static TokenBuilder INTEGER_TOKEN_BUILDER = new TokenBuilder().setTokenType(TokenType.NUM_INT);

    private static TokenBuilder FLOAT_TOKEN_BUILDER = new TokenBuilder().setTokenType(TokenType.NUM_FLOAT);

    static TokenBuilder get(NumericType numericType) {
        switch (numericType) {
            case INTEGER: {
                return INTEGER_TOKEN_BUILDER;
            }

            case FLOAT: {
                return FLOAT_TOKEN_BUILDER;
            }

            default:
                throw new NotImplementedException();
        }
    }
}
