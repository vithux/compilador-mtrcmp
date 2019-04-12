/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package token.builders;

import token.TokenBuilder;
import token.TokenType;

public class LogicalOperationTokenBuilder extends TokenBuilder {

    public LogicalOperationTokenBuilder() {
        this.setTokenType(TokenType.LOGIC_OP);
    }
}
