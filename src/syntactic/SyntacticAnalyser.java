package syntactic;

import lexical.LexicalAnalyzer;
import token.Token;
import token.TokenType;

public class SyntacticAnalyser {

    private final LexicalAnalyzer lexicalAnalyzer;

    public SyntacticAnalyser(String filePath) {
        this.lexicalAnalyzer = new LexicalAnalyzer(filePath);
    }

    public void process() {
        Token token;

        do {
            token = lexicalAnalyzer.nextToken();
            System.out.println(token.toString());
        }
        while (TokenType.EOF.equals(token.getTokenType()));
    }
}
