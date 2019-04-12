/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package automata.helpers;

import automata.State;
import automata.Transition;

public class AcceptAlphaNumericState extends State {

    public AcceptAlphaNumericState() {
        super(true);
        addRules(this);
    }

    public AcceptAlphaNumericState(State nextState) {
        addRules(nextState);
    }

    private void addRules(State nextState) {
        for (char rule : NumberRules.getRules()) {
            addTransition(new Transition(rule, nextState));
        }

        for (char rule : LetterRules.getRules()) {
            addTransition(new Transition(rule, nextState));
        }
    }
}
