package token;

public class Token {

    private TokenType tokenType;
    private String lexeme;
    private long column;
    private long line;

    public Token(TokenType tokenType, String lexeme, long column, long line) {
        this.setTokenType(tokenType);
        this.setLexeme(lexeme);
        this.setColumn(column);
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

    public long getColumn() {
        return column;
    }

    public void setColumn(long column) {
        this.column = column;
    }

    public long getLine() {
        return line;
    }

    public void setLine(long line) {
        this.line = line;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Token{");

        sb.append("tokenType=").append(tokenType);
        sb.append(", lexeme='").append(lexeme).append('\'');
        sb.append(", column=").append(column);
        sb.append(", line=").append(line);
        sb.append('}');

        return sb.toString();
    }
}
