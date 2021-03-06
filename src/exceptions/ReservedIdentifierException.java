/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package exceptions;

public class ReservedIdentifierException extends Exception {

    private String identifier;

    private long line;

    private long column;

    public ReservedIdentifierException(String identifier, long line, long column) {
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
