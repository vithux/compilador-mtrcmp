package token;

import com.sun.istack.internal.NotNull;
import loader.FileLoader;

public class TokenBuilder {

    @NotNull
    private TokenType tokenType;

    @NotNull
    private String lexeme = "";

    @NotNull
    private long col = 0L;

    @NotNull
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

    public TokenBuilder setCol(long col) {
        this.col = col;
        return this;
    }

    public TokenBuilder setLine(long line) {
        this.line = line;
        return this;
    }

    public TokenBuilder setCursorLocation(FileLoader fileLoader) {
        return setLine(fileLoader.getLine()).setCol(fileLoader.getColumn());
    }

    public Token build() {
        return new Token(tokenType, lexeme, col, line);
    }
}