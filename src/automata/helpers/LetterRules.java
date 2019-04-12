package automata.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Regras de transição para caracteres.
 *
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
public class LetterRules {

    private static final int UPPER_CASE_CHARACTERS_ASCII_TABLE_OFFSET = 0x41;

    private static final int LOWER_CASE_CHARACTERS_ASCII_TABLE_OFFSET = 0x61;

    public static List<Character> getRules() {
        ArrayList<Character> transitions = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            transitions.add((char) (UPPER_CASE_CHARACTERS_ASCII_TABLE_OFFSET + i));
            transitions.add((char) (LOWER_CASE_CHARACTERS_ASCII_TABLE_OFFSET + i));
        }

        return transitions;
    }
}
