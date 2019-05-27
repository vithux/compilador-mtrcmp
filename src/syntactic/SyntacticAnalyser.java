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

        if (token.getTokenType() != TokenType.PROGRAM) {
            // log erro
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.ID) {
            //log erro
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() == TokenType.TERM) {
            derivativeBLOCO();
        } else {
            //log erro
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.END_PROG) {
            // log erro
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TERM) {
            //log erro
        }
    }


    private void derivativeBLOCO() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.BEGIN) {

            derivativeCMDS();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.END) {
                //log erro
            }

        } else if (token.getTokenType() == TokenType.DECLARE) {

            lexicalAnalyzer.storeToken(token);
            derivativeCMD();

        } else {
            //log erro
        }
    }

    private void derivativeCMDS() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.DECLARE) {

            lexicalAnalyzer.storeToken(token);
            derivativeDECL();
            derivativeCMDS();

        } else if (token.getTokenType() == TokenType.IF) {

            lexicalAnalyzer.storeToken(token);
            derivativeCOND();
            derivativeCMDS();

        } else if (token.getTokenType() == TokenType.FOR) {

            lexicalAnalyzer.storeToken(token);
            derivativeREPF();
            derivativeCMDS();

        } else if (token.getTokenType() == TokenType.WHILE) {

            lexicalAnalyzer.storeToken(token);
            derivativeREPW();
            derivativeCMDS();

        } else if (token.getTokenType() == TokenType.ID) {

            lexicalAnalyzer.storeToken(token);
            derivativeATRIB();
            derivativeCMDS();

        } else if (token.getTokenType() == TokenType.END) { // follow de CMDS

            lexicalAnalyzer.storeToken(token);

        } else {
            //log erro
        }
    }

    private void derivativeCMD() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.DECLARE) {

            lexicalAnalyzer.storeToken(token);
            derivativeDECL();

        } else if (token.getTokenType() == TokenType.IF) {

            lexicalAnalyzer.storeToken(token);
            derivativeCOND();

        } else if (token.getTokenType() == TokenType.FOR || token.getTokenType() == TokenType.WHILE) { // é mesmo a lista completa de first?

            lexicalAnalyzer.storeToken(token);
            derivativeREP();

        } else if (token.getTokenType() == TokenType.ID) {

            lexicalAnalyzer.storeToken(token);
            derivativeATRIB();

        }
    }

    private void derivativeDECL() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.DECLARE) {
            //LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.ID) {
            //LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TYPE) {
            //LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TERM) {
            //LOG ERRO
        }

        //aqui valida se a variavel já foi declarada
    }

    private void derivativeCOND() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.IF) {
            //LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.L_PAR) {
            //LOG ERRO
        }

        derivativeEXPLO();

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.R_PAR) {
            //LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.THEN) {
            //LOG ERRO
        }

        derivativeBLOCO();
        derivativeCNDB();
    }

    private void derivativeCNDB() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ELSE) {

            derivativeBLOCO();

        } else {

            lexicalAnalyzer.storeToken(token);

        }
    }

    private void derivativeATRIB() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.ID) {
            //LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.ASSIGN) {
            //LOG ERRO
        }

        derivativeEXP();

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TERM) {
            //LOG ERRO
        }
    }

    private void derivativeEXP() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_VAL) {

            derivativeFVALLOG();

        } else if (token.getTokenType() == TokenType.ID) {

            derivativeFID();

        } else if (token.getTokenType() == TokenType.NUM_INT) {

            derivativeFNUMINT();

        } else if (token.getTokenType() == TokenType.NUM_FLOAT) {

            derivativeFNUMFLOAT();

        } else if (token.getTokenType() == TokenType.L_PAR) {

            derivativeFLPAR();

        } else if (token.getTokenType() == TokenType.LITERAL) {
            // faz alguma coisa
        } else {
            //LOG ERRO
        }
    }

    private void derivativeFID() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_OP) {

            lexicalAnalyzer.storeToken(token);
            derivativeFVALLOG();

        } else if (token.getTokenType() == TokenType.ARIT_AS || token.getTokenType() == TokenType.ARIT_MD) { // first opnum aqui

            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeFOPNUM();

        } else {
            //LOG ERRO
        }
    }

    private void derivativeFOPNUM() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.L_PAR || token.getTokenType() == TokenType.ID || token.getTokenType() == TokenType.NUM_INT || token.getTokenType() == TokenType.NUM_FLOAT) { // first de EXPNUM

            lexicalAnalyzer.storeToken(token);
            derivativeEXPNUM();
            derivativeFEXPNUM1();

        } else {
            //LOG ERRO
        }
    }

    private void derivativeFEXPNUM1() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.RELOP) {

            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.TERM) { // devo mesmo armazenar nese caso?

            lexicalAnalyzer.storeToken(token);

        } else {
            //LOG ERRO
        }
    }

    private void derivativeFNUMINT() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ARIT_AS || token.getTokenType() == TokenType.ARIT_MD) { // first de OPNUM

            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeFOPNUM1();

        } else {
            //LOG ERRO
        }

    }

    private void derivativeFOPNUM1() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.L_PAR || token.getTokenType() == TokenType.ID || token.getTokenType() == TokenType.NUM_INT || token.getTokenType() == TokenType.NUM_FLOAT) { //first EXPNUM

            lexicalAnalyzer.storeToken(token);
            derivativeEXPNUM();
            derivativeFEXPNUM2();

        } else {
            //LOG ERRO
        }
    }

    private void derivativeFEXPNUM2() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.RELOP) {

            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.TERM) { //follow fexpnum2

            lexicalAnalyzer.storeToken(token); // devo mesmo armazenar esse token?

        } else {
            //LOG ERRO
        }
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

    private void derivativeFIDI1() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_OP) {

            lexicalAnalyzer.storeToken(token);
            derivativeFVALLOG();

        } else if (token.getTokenType() == TokenType.ARIT_AS || token.getTokenType() == TokenType.ARIT_MD) {

            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeEXPNUM();

            if (token.getTokenType() == TokenType.RELOP) {
                derivativeEXPNUM();
            } else {
                // log erro
            }

        } else if (token.getTokenType() == TokenType.END) { //fazer um hashmap do follow dessa regra
            lexicalAnalyzer.storeToken(token);
        } else {
            //log erro
        }
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
