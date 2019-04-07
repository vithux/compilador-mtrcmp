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
