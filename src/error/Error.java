/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package error;

public class Error {

    private String description;

    private long line;

    private long column;

    public Error(String description, long line, long column) {
        this.line = line;
        this.column = column;
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Error{");
        sb.append("description=\'").append(description).append('\'');
        sb.append(", line=").append(line);
        sb.append(", column=").append(column);
        sb.append('}');
        return sb.toString();
    }
}
