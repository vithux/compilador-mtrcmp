package automata;

/**
 * Abstração sobre transições entre estados de um autômato.
 *
 * Baseado em: https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/automata/RtTransition.java
 *
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
public class Transition {

    private char rule;
    private State nextState;

    public Transition(char rule, State next) {
        this.rule = rule;
        this.nextState = next;
    }

    /**
     * Retorna uma refêrencia ao próximo estado após a transição.
     *
     * @return {State} Proximo estado
     */
    public State getNextState() {
        return this.nextState;
    }

    /**
     * Verifica se a transição é possivel, baseada na regra de transição e o caractere (token) dado.
     *
     * @param token - Token de entrada
     * @return {Boolean} Verdadeiro caso a transição entre estados seja possivel
     */
    public boolean isPossible(char token) {
        return rule == token;
    }
}
