package automata.helpers;

import automata.State;
import automata.Transition;

/**
 * Classe que define um estado que aceita caracteres alfa númericos.
 *
 * Podendo transicionar-se para si mesmo, e, para outro estado.
 *
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
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
