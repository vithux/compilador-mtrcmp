/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package exceptions;

public class NoSuchTransitionException extends Exception {

    private char token;

    public NoSuchTransitionException(char token) {
        this.token = token;
    }

    public char getToken() {
        return token;
    }
}
