/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package syntactic;

import lexical.LexicalAnalyzer;
import token.Token;
import token.TokenType;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SyntacticAnalyser {

    private final LexicalAnalyzer lexicalAnalyzer;

    public SyntacticAnalyser(String filePath) throws FileNotFoundException {
        this.lexicalAnalyzer = new LexicalAnalyzer(filePath);
    }

    public void process() throws IOException {
        Token token;

        do {
            token = lexicalAnalyzer.nextToken();
            System.out.println(token.toString());
        }
        while (!TokenType.EOF.equals(token.getTokenType()));
    }
}
