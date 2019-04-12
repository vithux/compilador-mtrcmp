package automata;

import exceptions.NoSuchTransitionException;

import java.util.*;

/**
 * Abstração sobre o estado de um autômato.
 *
 * Baseado em: https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-1/src/main/java/com/baeldung/algorithms/automata/RtState.java
 *
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
public class State {

    private List<Transition> transitions;
    private boolean isFinal;

    /**
     * Construtor base.
     *
     * OBS: Por padrão estados não são finais.
     */
    public State() {
        this(false);
    }

    /**
     * Construtor para estados finais.
     *
     * @param isFinal - Flag que descreve se o estado é final.
     */
    public State(boolean isFinal) {
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    /**
     * Transiciona estado para o proximo, utilizando a primeira regra possivel encontrada.
     *
     * @param token - Caractere (token) de entrada
     * @return {State} Novo estado
     *
     * @throws NoSuchTransitionException - Nenhuma regra de transição definida para o token dado
     */
    public State makeTransition(char token) throws NoSuchTransitionException {
        for (Transition transition : transitions) {
            if (transition.isPossible(token)) {
                return transition.getNextState();
            }
        }

        throw new NoSuchTransitionException(token);
    }

    /**
     * Retorna se o estado é final.
     *
     * @return {Boolean} Verdadeiro caso o estado seja final
     */
    public boolean isFinal() {
        return this.isFinal;
    }

    /**
     * Adiciona transições ao estado.
     * @param transition - Referencia á um objeto de transição
     */
    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }
}
