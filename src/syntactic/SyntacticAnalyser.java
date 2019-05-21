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

    private void derivativeS() throws IOException {
        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.PROGRAM) {
            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() == TokenType.ID) {
                token = lexicalAnalyzer.nextToken();
                if (token.getTokenType() == TokenType.TERM) {
                    derivativeBLOCO();
                    token = lexicalAnalyzer.nextToken();
                    if (token.getTokenType() == TokenType.END_PROG) {
                        token = lexicalAnalyzer.nextToken();
                        if (token.getTokenType() == TokenType.TERM) {
                            // finaliza programa
                        } else {
                            //retorna erro
                        }
                    } else {
                        // retorna erro
                    }
                } else {
                    //retorna erro
                }
            } else {
                //retorna erro
            }
        } else {
            // retorna erro
        }
    }

    private void derivativeFIDI1() throws IOException {
        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_OP) {
            lexicalAnalyzer.storeToken(token);
            // derivativeFVALLOG();
        } else if (token.getTokenType() == TokenType.ARIT_AS || token.getTokenType() == TokenType.ARIT_MD) {
            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeEXPNUM();
            if (token.getTokenType() == TokenType.RELOP) {
                derivativeEXPNUM();
            } else {
                // retorna erro
            }
        } else if (token.getTokenType() == TokenType.END) { //fazer um hashmap do follow dessa regra
            lexicalAnalyzer.storeToken(token);
        } else {
            //retorna erro
        }
    }

    private void derivativeBLOCO() throws IOException {
        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.BEGIN) {
            derivativeCMDS();
            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() == TokenType.END) {
                // finaliza execuão
            } else {
                //retorna erro
            }
        } else {
            //retorna erro
        }


    }

    private void derivativeCMDS() throws IOException {

    }

    private void derivativeCMD() throws IOException {

    }

    private void derivativeDECL() throws IOException {

    }

    private void derivativeCOND() throws IOException {

    }

    private void derivativeCNDB() throws IOException {

    }

    private void derivativeATRIB() throws IOException {

    }

    private void derivativeEXP() throws IOException {

    }

    private void derivativeFID() throws IOException {

    }

    private void derivativeFOPNUM() throws IOException {

    }

    private void derivativeFEXPNUM1() throws IOException {

    }

    private void derivativeFNUMINT() throws IOException {

    }

    private void derivativeFOPNUM1() throws IOException {

    }

    private void derivativeFEXPNUM2() throws IOException {

    }

    private void derivativeFNUMFLOAT() throws IOException {

    }

    private void derivativeFOPNUM2() throws IOException {

    }

    private void derivativeFEXPNUM3() throws IOException {

    }

    private void derivativeFLPAR() throws IOException {

    }

    private void derivativeFEXPNUM() throws IOException {

    }

    private void derivativeFRPAR() throws IOException {

    }

    private void derivativeEXPLO() throws IOException {

    }

    private void derivativeFVALLOG() throws IOException {

    }

    private void derivativeEXPNUM() throws IOException {

    }

    private void derivativeXEXPNUM() throws IOException {

    }

    private void derivativeOPNUM() throws IOException {

    }

    private void derivativeVAL() throws IOException {

    }

    private void derivativeREP() throws IOException {

    }

    private void derivativeREPF() throws IOException {

    }

    private void derivativeREPW() throws IOException {

    }


}
