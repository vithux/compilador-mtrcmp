package automata;

// Based on: https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/automata/RtTransition.java
public class Transition {

    private char rule;
    private State nextState;

    public Transition(char rule, State next) {
        this.rule = rule;
        this.nextState = next;
    }

    public State state() {
        return this.nextState;
    }

    public boolean isPossible(char token) {
        return rule == token;
    }

    public char getRule() {
        return rule;
    }
}
