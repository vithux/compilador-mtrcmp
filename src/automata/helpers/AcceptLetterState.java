package automata.helpers;

import automata.State;
import automata.Transition;

/**
 * Classe que define um estado que aceita caracteres somente.
 *
 * Podendo transicionar-se para si mesmo, e, para outro estado.
 *
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
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
