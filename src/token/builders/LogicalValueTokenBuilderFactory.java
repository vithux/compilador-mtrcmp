/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package token.builders;

import token.TokenBuilder;
import token.TokenType;
import utils.Constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LogicalValueTokenBuilderFactory {

    private static final Map<String, TokenBuilder> logicalValues = new HashMap<>();

    static {
        for (String value : Arrays.asList(Constants.TRUE, Constants.FALSE)) {
            logicalValues.put(value, new TokenBuilder().setTokenType(TokenType.LOGIC_VAL).setLexeme(value));
        }
    }

    public static TokenBuilder getTokenBuilder(String logicalValue) {
        return logicalValues.get(logicalValue);
    }
}
