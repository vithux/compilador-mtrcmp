/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package lexical.parser.numeric;

import automata.FiniteStateMachine;
import automata.State;
import automata.Transition;
import automata.helpers.AcceptNumbersState;

class NumberParserStateMachine {

    private static final char FLOAT_SEPARATOR = '.';
    private static final char EXPONENTIATION_MINUS_SIGN = '-';
    private static final char EXPONENTIATION_PLUS_SIGN = '+';
    private static final char BASE_TEN_EXPONENTIATION_TOKEN = 'E';
    private static final char BASE_TEN_EXPONENTIATION_TOKEN_ALT = 'e';

    private static final State INITIAL_STATE;

    private static final State FLOAT_FINAL_STATE;
    private static final State INTEGER_FINAL_STATE;

    private static final State FLOAT_SCIENTIFIC_NOTATION_STATE;
    private static final State INTEGER_SCIENTIFIC_NOTATION_STATE;

    private static final State FLOAT_BASE_TEN_EXPONENTIATION_STATE;
    private static final State INTEGER_BASE_TEN_EXPONENTIATION_STATE;

    private static final State FLOAT_START_STATE;

    private static final State FLOAT_EXPONENTIATION_SIGN_STATE;
    private static final State INTEGER_EXPONENTIATION_SIGN_STATE;

    private static FiniteStateMachine instance;

    static {
        FLOAT_BASE_TEN_EXPONENTIATION_STATE = new State();
        INTEGER_BASE_TEN_EXPONENTIATION_STATE = new State();

        FLOAT_SCIENTIFIC_NOTATION_STATE = new AcceptNumbersState();
        INTEGER_SCIENTIFIC_NOTATION_STATE = new AcceptNumbersState();

        FLOAT_EXPONENTIATION_SIGN_STATE = new AcceptNumbersState(FLOAT_SCIENTIFIC_NOTATION_STATE);
        INTEGER_EXPONENTIATION_SIGN_STATE = new AcceptNumbersState(INTEGER_SCIENTIFIC_NOTATION_STATE);

        FLOAT_FINAL_STATE = new AcceptNumbersState();
        FLOAT_START_STATE = new AcceptNumbersState(FLOAT_FINAL_STATE);

        INTEGER_FINAL_STATE = new AcceptNumbersState();

        INITIAL_STATE = new AcceptNumbersState(INTEGER_FINAL_STATE);

        // Transitions

        FLOAT_FINAL_STATE.addTransition(new Transition(BASE_TEN_EXPONENTIATION_TOKEN, FLOAT_BASE_TEN_EXPONENTIATION_STATE));
        FLOAT_FINAL_STATE.addTransition(new Transition(BASE_TEN_EXPONENTIATION_TOKEN_ALT, FLOAT_BASE_TEN_EXPONENTIATION_STATE));

        FLOAT_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_PLUS_SIGN, FLOAT_EXPONENTIATION_SIGN_STATE));
        FLOAT_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_PLUS_SIGN, FLOAT_EXPONENTIATION_SIGN_STATE));

        FLOAT_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_MINUS_SIGN, FLOAT_EXPONENTIATION_SIGN_STATE));
        FLOAT_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_MINUS_SIGN, FLOAT_EXPONENTIATION_SIGN_STATE));

        INTEGER_FINAL_STATE.addTransition(new Transition(FLOAT_SEPARATOR, FLOAT_START_STATE));

        INTEGER_FINAL_STATE.addTransition(new Transition(BASE_TEN_EXPONENTIATION_TOKEN, INTEGER_BASE_TEN_EXPONENTIATION_STATE));
        INTEGER_FINAL_STATE.addTransition(new Transition(BASE_TEN_EXPONENTIATION_TOKEN_ALT, INTEGER_BASE_TEN_EXPONENTIATION_STATE));

        INTEGER_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_PLUS_SIGN, INTEGER_EXPONENTIATION_SIGN_STATE));
        INTEGER_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_PLUS_SIGN, INTEGER_EXPONENTIATION_SIGN_STATE));

        INTEGER_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_MINUS_SIGN, INTEGER_EXPONENTIATION_SIGN_STATE));
        INTEGER_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_MINUS_SIGN, INTEGER_EXPONENTIATION_SIGN_STATE));
    }

    public static NumericType getNumericTypeFromFinalState(FiniteStateMachine stateMachine) {
        State state = stateMachine.getState();

        if (state == FLOAT_FINAL_STATE || state == FLOAT_SCIENTIFIC_NOTATION_STATE) {
            return NumericType.FLOAT;
        }

        return NumericType.INTEGER;
    }

    public static FiniteStateMachine getInstance() {
        if (instance == null) {
            instance = new FiniteStateMachine(INITIAL_STATE);
        }

        return instance;
    }
}
