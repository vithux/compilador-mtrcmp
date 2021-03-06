/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package error;

import exceptions.IllegalTokenException;

public class IllegalTokenError extends Error {

    private IllegalTokenError(char token, String lexeme, long line, long column) {
        super(
                new StringBuilder("Illegal token \'").append(token).append("\' encountered in ").append(lexeme).toString(),
                line,
                column
        );
    }

    public static IllegalTokenError from(IllegalTokenException e) {
        return new IllegalTokenError(e.getToken(), e.getLexeme(), e.getLine(), e.getColumn());
    }
}
