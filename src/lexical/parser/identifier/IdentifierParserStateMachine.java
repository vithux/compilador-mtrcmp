/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package lexical.parser.identifier;

import automata.FiniteStateMachine;
import automata.State;
import automata.Transition;
import automata.helpers.AcceptAlphaNumericState;
import automata.helpers.AcceptLetterState;

public class IdentifierParserStateMachine {

    private static FiniteStateMachine instance;

    private static final State INITIAL_STATE;

    private static final State VALID_ID_START_STATE;

    static {
        VALID_ID_START_STATE = new AcceptAlphaNumericState();
        INITIAL_STATE = new AcceptLetterState(VALID_ID_START_STATE);

        INITIAL_STATE.addTransition(new Transition('_', VALID_ID_START_STATE));
        VALID_ID_START_STATE.addTransition(new Transition('_', VALID_ID_START_STATE));
    }

    public static FiniteStateMachine getInstance() {
        if (instance == null) {
            instance = new FiniteStateMachine(INITIAL_STATE);
        }

        return instance;
    }
}
