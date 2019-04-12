/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package token;

import com.sun.istack.internal.NotNull;
import loader.FileLoader;

public class TokenBuilder {

    @NotNull
    private TokenType tokenType;

    @NotNull
    private String lexeme = "";

    private long column = 0L;

    private long line = 0L;

    public TokenBuilder setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public TokenBuilder setLexeme(String lexeme) {
        this.lexeme = lexeme;
        return this;
    }

    public TokenBuilder setLexeme(StringBuilder lexeme) {
        return setLexeme(lexeme.toString());
    }

    public TokenBuilder setColumn(long column) {
        this.column = column;
        return this;
    }

    public TokenBuilder setLine(long line) {
        this.line = line;
        return this;
    }

    public TokenBuilder setCursorLocation(FileLoader fileLoader) {
        return setLine(fileLoader.getLine()).setColumn(fileLoader.getColumn());
    }

    public Token withCursorLocation(FileLoader fileLoader) {
        return setCursorLocation(fileLoader).build();
    }

    public Token build() {
        return new Token(tokenType, lexeme, column, line);
    }
}