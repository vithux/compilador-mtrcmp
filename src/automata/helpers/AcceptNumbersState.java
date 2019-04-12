/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package automata.helpers;

import automata.State;
import automata.Transition;

public class AcceptNumbersState extends State {

    public AcceptNumbersState() {
       super(true);
       addRules(this);
    }

    public AcceptNumbersState(State nextState) {
        addRules(nextState);
    }

    private void addRules(State nextState) {
        for (char rule : NumberRules.getRules()) {
            addTransition(new Transition(rule, nextState));
        }
    }
}
