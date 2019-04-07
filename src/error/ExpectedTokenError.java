package error;

import exceptions.ExpectedTokenException;

public class ExpectedTokenError extends Error {

    private ExpectedTokenError(char token, String lexeme, long line, long column) {
        super(
                new StringBuilder("Expected token ").append(token).append(" in ").append(lexeme).toString(),
                line,
                column
        );
    }

    public static ExpectedTokenError from(ExpectedTokenException e) {
        return new ExpectedTokenError(e.getToken(), e.getLexeme(), e.getLine(), e.getColumn());
    }
}
