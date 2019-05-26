/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package token.builders;

import token.TokenBuilder;
import token.TokenType;

public class AssignTokenBuilderSingleton {

    private static final String ASSIGN_LEXEME = "<-";

    private static TokenBuilder instance;

    public static TokenBuilder getInstance() {
        if (instance == null) {
            instance = new TokenBuilder().setTokenType(TokenType.ASSIGN).setLexeme(ASSIGN_LEXEME);
        }

        return instance;
    }
}
