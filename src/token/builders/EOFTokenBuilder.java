/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package token.builders;

import token.TokenBuilder;
import token.TokenType;

public class EOFTokenBuilder extends TokenBuilder {

    private static final String EOF_LEXEME = "EOF";

    public EOFTokenBuilder() {
        this.setLexeme(EOF_LEXEME);
        this.setTokenType(TokenType.EOF);
    }
}
