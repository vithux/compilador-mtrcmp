/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package automata.helpers;

import java.util.ArrayList;
import java.util.List;

public class NumberRules {

    private static final int NUMBERS_ASCII_TABLE_OFFSET = 0x30;

    public static List<Character> getRules() {
        ArrayList<Character> transitions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            transitions.add((char) (NUMBERS_ASCII_TABLE_OFFSET + i));
        }

        return transitions;
    }
}
