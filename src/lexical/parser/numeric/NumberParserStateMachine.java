package lexical.parser.numeric;

import automata.FinalState;
import automata.FiniteStateMachine;
import automata.State;
import automata.Transition;

import java.util.ArrayList;

class NumberParserStateMachine {

    private static final int NUMBERS_ASCII_TABLE_OFFSET = 48;

    private static final char FLOAT_SEPARATOR = '.';
    private static final char EXPONENTIATION_PLUS_SIGN = '+';
    private static final char BASE_TEN_EXPONENTIATION_TOKEN = 'E';

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

        FLOAT_SCIENTIFIC_NOTATION_STATE = numberFinalState();
        INTEGER_SCIENTIFIC_NOTATION_STATE = numberFinalState();

        FLOAT_EXPONENTIATION_SIGN_STATE = stateWithNumberTransitions(FLOAT_SCIENTIFIC_NOTATION_STATE);
        INTEGER_EXPONENTIATION_SIGN_STATE = stateWithNumberTransitions(INTEGER_SCIENTIFIC_NOTATION_STATE);

        FLOAT_FINAL_STATE = numberFinalState();
        FLOAT_START_STATE = stateWithNumberTransitions(FLOAT_FINAL_STATE);

        INTEGER_FINAL_STATE = numberFinalState();

        INITIAL_STATE = stateWithNumberTransitions(INTEGER_FINAL_STATE);

        // Transitions

        FLOAT_FINAL_STATE.addTransition(baseTenExponentiationTransition(FLOAT_BASE_TEN_EXPONENTIATION_STATE));

        FLOAT_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_PLUS_SIGN, FLOAT_EXPONENTIATION_SIGN_STATE));
        FLOAT_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_PLUS_SIGN, FLOAT_EXPONENTIATION_SIGN_STATE));

        INTEGER_FINAL_STATE.addTransition(new Transition(FLOAT_SEPARATOR, FLOAT_START_STATE));
        INTEGER_FINAL_STATE.addTransition(baseTenExponentiationTransition(INTEGER_BASE_TEN_EXPONENTIATION_STATE));

        INTEGER_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_PLUS_SIGN, INTEGER_EXPONENTIATION_SIGN_STATE));
        INTEGER_BASE_TEN_EXPONENTIATION_STATE.addTransition(new Transition(EXPONENTIATION_PLUS_SIGN, INTEGER_EXPONENTIATION_SIGN_STATE));
    }

    private static FinalState numberFinalState() {
        FinalState state = new FinalState();

        for (char rule : numberTransitionRules()) {
            state.addTransition(new Transition(rule, state));
        }

        return state;
    }

    private static ArrayList<Character> numberTransitionRules() {
        ArrayList<Character> transitions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            transitions.add((char) (NUMBERS_ASCII_TABLE_OFFSET + i));
        }

        return transitions;
    }

    private static State stateWithNumberTransitions(State nextState) {
        State state = new State();

        for (char rule : numberTransitionRules()) {
            state.addTransition(new Transition(rule, nextState));
        }

        return state;
    }

    private static Transition baseTenExponentiationTransition(State nextState) {
        return new Transition(BASE_TEN_EXPONENTIATION_TOKEN, nextState);
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
