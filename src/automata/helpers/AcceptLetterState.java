/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package automata.helpers;

import automata.State;
import automata.Transition;

public class AcceptLetterState extends State {

    public AcceptLetterState() {
        super(true);
        addRules(this);
    }

    public AcceptLetterState(State nextState) {
        addRules(nextState);
    }

    private void addRules(State nextState) {
        for (char rule : LetterRules.getRules()) {
            addTransition(new Transition(rule, nextState));
        }
    }
}
