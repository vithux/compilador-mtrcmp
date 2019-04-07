package automata.helpers;

import java.util.ArrayList;
import java.util.List;

public class LetterRules {

    private static final int UPPER_CASE_CHARACTERS_ASCII_TABLE_OFFSET = 0x41;

    private static final int LOWER_CASE_CHARACTERS_ASCII_TABLE_OFFSET = 0x61;

    private static List<Character> getUpperCaseCharacterRules() {
        ArrayList<Character> transitions = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            transitions.add((char) (UPPER_CASE_CHARACTERS_ASCII_TABLE_OFFSET + i));
        }

        return transitions;
    }

    private static List<Character> getLowerCaseCharacterRules() {
        ArrayList<Character> transitions = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            transitions.add((char) (LOWER_CASE_CHARACTERS_ASCII_TABLE_OFFSET + i));
        }

        return transitions;
    }

    public static List<Character> getRules() {
        ArrayList<Character> transitions = new ArrayList<>();

        transitions.addAll(getUpperCaseCharacterRules());
        transitions.addAll(getLowerCaseCharacterRules());

        return transitions;
    }
}
