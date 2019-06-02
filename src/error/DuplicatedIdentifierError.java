package error;

public class DuplicatedIdentifierError extends Error {

    public DuplicatedIdentifierError(String identifier) {
        super(new StringBuilder(identifier).append(" is already defined").toString());
    }
}
