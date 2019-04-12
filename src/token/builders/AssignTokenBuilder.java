/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
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
