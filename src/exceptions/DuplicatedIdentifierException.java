package exceptions;

public class DuplicatedIdentifierException extends Exception {

    private String identifier;

    private long line;

    private long column;

    public DuplicatedIdentifierException(String identifier, long line, long column) {
        this.identifier = identifier;
        this.line = line;
        this.column = column;
    }

    public String getIdentifier() {
        return identifier;
    }

    public long getLine() {
        return line;
    }

    public long getColumn() {
        return column;
    }
}
