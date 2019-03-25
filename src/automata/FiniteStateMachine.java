package automata;

// https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/automata/FiniteStateMachine.java
public class FiniteStateMachine {

    private final State state;

    public FiniteStateMachine(State state) {
        this.state = state;
    }

    public FiniteStateMachine consumeToken(char token) throws IllegalArgumentException {
        return new FiniteStateMachine(this.state.makeTransition(token));
    }

    public State getState() {
        return this.state;
    }

    public boolean isFinal() {
        return state.isFinal();
    }
}
