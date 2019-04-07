package error;

import exceptions.DuplicatedIdentifierException;

public class DuplicatedIdentifierError extends Error {

    private DuplicatedIdentifierError(String identifier, long line, long column) {
        super(
                new StringBuilder(identifier).append(" is already defined").toString(),
                line,
                column
        );
    }

    public static DuplicatedIdentifierError from(DuplicatedIdentifierException e) {
        return new DuplicatedIdentifierError(e.getIdentifier(), e.getLine(), e.getColumn());
    }
}
