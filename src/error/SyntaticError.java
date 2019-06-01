package error;

public class SyntaticError extends Error {

    public SyntaticError(String expected, String received) {
        super(new StringBuilder("Expected ").append(expected).append(" received ").append(expected).toString());
    }

}
