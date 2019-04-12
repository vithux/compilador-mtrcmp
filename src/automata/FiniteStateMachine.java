package automata;

import exceptions.NoSuchTransitionException;

/**
 * Abstração de um AFD.
 *
 * Baseado em: https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/automata/FiniteStateMachine.java
 *
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
public class FiniteStateMachine {

    private final State state;

    public FiniteStateMachine(State state) {
        this.state = state;
    }

    /**
     * Consome token, transicionando a máquina (se possivel) para o seu próximo estado.
     *
     * @param token - O token a ser consumido pela máquina.
     * @return {FiniteStateMachine} Uma nova máquina a partir do resultado da transição possivel a partir do token dado.
     * @throws NoSuchTransitionException - Em transições não definidas
     */
    public FiniteStateMachine consumeToken(char token) throws NoSuchTransitionException {
        return new FiniteStateMachine(this.state.makeTransition(token));
    }

    /**
     * Obtém o estado atual da máquina.
     *
     * @return {State} Referencia ao estado da máquina.
     */
    public State getState() {
        return this.state;
    }

    /**
     * Retorna se o estado atual da máquina é final.
     *
     * @return {Boolean} Caso o estado seja final, retorna true.
     */
    public boolean isFinal() {
        return state.isFinal();
    }
}
