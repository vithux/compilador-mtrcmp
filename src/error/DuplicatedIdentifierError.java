/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */

package error;

public class DuplicatedIdentifierError extends Error {

    public DuplicatedIdentifierError(String identifier) {
        super(new StringBuilder(identifier).append(" is already defined").toString());
    }
}
