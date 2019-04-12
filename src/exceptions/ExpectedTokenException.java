/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package exceptions;

public class ExpectedTokenException extends Exception {

    private char token;

    private String lexeme;

    private long line;

    private long column;

    public ExpectedTokenException(char token, String lexeme, long line, long column) {
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
