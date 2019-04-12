/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package token.builders;

import token.TokenBuilder;
import token.TokenType;

public class LogicalValueTokenBuilder extends TokenBuilder {

    public LogicalValueTokenBuilder() {
        this.setTokenType(TokenType.LOGIC_VAL);
    }
}
