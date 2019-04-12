/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package automata;

import exceptions.NoSuchTransitionException;

import java.util.*;

// Based on: https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/automata/RtState.java
public class State {

    private List<Transition> transitions;
    private boolean isFinal;

    public State() {
        this(false);
    }

    public State(boolean isFinal) {
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    public State makeTransition(char token) throws NoSuchTransitionException {
        for (Transition transition : transitions) {
            if (transition.isPossible(token)) {
                return transition.state();
            }
        }

        throw new NoSuchTransitionException(token);
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }
}
