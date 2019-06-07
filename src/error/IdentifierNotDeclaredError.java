/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */

package error;

public class IdentifierNotDeclaredError extends Error {

    public IdentifierNotDeclaredError(String identifier) {
        super(new StringBuilder(identifier).append(" is not declared").toString());
    }
}
