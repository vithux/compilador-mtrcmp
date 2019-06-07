/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */

package error;

public class SyntaticError extends Error {

    public SyntaticError(String expected, String received) {
        super(new StringBuilder("Expected ").append(expected).append(" received ").append(received).toString());
    }

}
