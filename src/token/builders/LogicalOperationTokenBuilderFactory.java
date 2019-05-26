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

public class LogicalOperationTokenBuilderFactory {

    private static final Map<String, TokenBuilder> logicalOperations = new HashMap<>();

    static {
        for (String operation : Arrays.asList(Constants.AND, Constants.NOT, Constants.OR)) {
            logicalOperations.put(operation, new TokenBuilder().setTokenType(TokenType.LOGIC_VAL).setLexeme(operation));
        }
    }

    public static TokenBuilder getTokenBuilder(String logicalOperation) {
        return logicalOperations.get(logicalOperation);
    }
}
