/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package automata;

import exceptions.NoSuchTransitionException;

// https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/automata/FiniteStateMachine.java
public class FiniteStateMachine {

    private final State state;

    public FiniteStateMachine(State state) {
        this.state = state;
    }

    public FiniteStateMachine consumeToken(char token) throws NoSuchTransitionException {
        return new FiniteStateMachine(this.state.makeTransition(token));
    }

    public State getState() {
        return this.state;
    }

    public boolean isFinal() {
        return state.isFinal();
    }
}
