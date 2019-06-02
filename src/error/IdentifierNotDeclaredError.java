package error;

public class IdentifierNotDeclaredError extends Error {

    public IdentifierNotDeclaredError(String identifier) {
        super(new StringBuilder(identifier).append(" is not declared").toString());
    }
}
