/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package exceptions;

public class UnexpectedTokenException extends Exception {

    private char token;

    private long line;

    private long column;

    public UnexpectedTokenException(char token, long line, long column) {
        this.token = token;
        this.line = line;
        this.column = column;
    }

    public char getToken() {
        return token;
    }

    public long getLine() {
        return line;
    }

    public long getColumn() {
        return column;
    }
}
