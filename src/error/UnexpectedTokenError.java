package error;

import exceptions.UnexpectedTokenException;

public class UnexpectedTokenError extends Error {

    private UnexpectedTokenError(char token, long line, long column) {
        super(new StringBuilder("Unexpected token \'").append(token).append("\'").toString(), line, column);
    }

    public static UnexpectedTokenError from(UnexpectedTokenException e) {
        return new UnexpectedTokenError(e.getToken(), e.getLine(), e.getColumn());
    }
}
