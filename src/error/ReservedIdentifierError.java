package error;

import exceptions.ReservedIdentifierException;

public class ReservedIdentifierError extends Error {

    private ReservedIdentifierError(String identifier, long line, long column) {
        super(new StringBuilder("Identifier ").append(identifier).append(" is a reserved keyword").toString(), line, column);
    }

    public static ReservedIdentifierError from(ReservedIdentifierException e) {
        return new ReservedIdentifierError(e.getIdentifier(), e.getLine(), e.getColumn());
    }
}
