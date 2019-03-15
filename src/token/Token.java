package token;

public class Token {
    private TokenType tokenType;
    private String lexeme;
    private int col;
    private int line;

    public Token(TokenType tokenType, String lexeme, int col, int line) {
        this.setTokenType(tokenType);
        this.setLexeme(lexeme);
        this.setCol(col);
        this.setLine(line);
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
