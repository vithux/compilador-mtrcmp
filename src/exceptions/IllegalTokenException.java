package exceptions;

public class IllegalTokenException extends Exception {

    private char token;

    private String lexeme;

    private long line;

    private long column;

    public IllegalTokenException(char token, String lexeme, long line, long column) {
        this.token = token;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public char getToken() {
        return token;
    }

    public String getLexeme() {
        return lexeme;
    }

    public long getLine() {
        return line;
    }

    public long getColumn() {
        return column;
    }
}
