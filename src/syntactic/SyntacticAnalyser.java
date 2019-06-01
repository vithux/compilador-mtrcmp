/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package syntactic;

import error.Error;
import error.SyntaticError;
import error.handler.ErrorHandler;
import lexical.LexicalAnalyzer;
import symbol.Symbol;
import symbol.SymbolTable;
import token.Token;
import token.TokenType;

import java.io.FileNotFoundException;
import java.io.IOException;


    /*
    OTIMIZAÇÕES NA GRAMATICA

    FEXPNUM1, FEXPNUM2, FEXPNUM3 e FRPAR estavam duplicados e foram removidos todas foram subistituidas pela FRPAR
    FOPNUM, FOPNUM1 e FOPNUM2 estavam duplicadas e foram subistituidas pela FOPNUM
    FNUMINT e FNUMFLOAT estavam duplicadas e foram subistituidas por FNUM
    REPW E COND possuiam as mesmas regras internas que foi extraida para a regra derivativeINTRENAL
     */

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

        //aqui valida se a variavel já foi declarada
        if (SymbolTable.getInstance().getSymbol(token.getLexeme()) != null) {
            // LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TERM) {
            //LOG ERRO
        }
    }

    private void derivativeCOND() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.IF) {
            //LOG ERRO
        }

        derivativeINTRENAL();

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

            derivativeFNUM();

        } else if (token.getTokenType() == TokenType.NUM_FLOAT) {

            derivativeFNUM();

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
            derivativeFRPAR();

        } else {
            //LOG ERRO
        }
    }

    private void derivativeFNUM() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ARIT_AS || token.getTokenType() == TokenType.ARIT_MD) { // first de OPNUM

            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeFOPNUM();

        } else {
            //LOG ERRO
        }
    }


    private void derivativeFLPAR() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.L_PAR || token.getTokenType() == TokenType.ID || token.getTokenType() == TokenType.NUM_INT || token.getTokenType() == TokenType.NUM_FLOAT) { //first EXPNUM

            lexicalAnalyzer.storeToken(token);
            derivativeEXPNUM();
            derivativeFEXPNUM();

        } else {
            //LOG ERRO
        }
    }

    private void derivativeFEXPNUM() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.R_PAR) {

            derivativeFRPAR();

        } else {
            //LOG ERRO
        }
    }

    private void derivativeFRPAR() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.RELOP) {

            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.TERM) { // follow FRPAR

            lexicalAnalyzer.storeToken(token);

        } else {
            //LOG ERRO
        }
    }

    private void derivativeEXPLO() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_VAL) {

            derivativeFVALLOG();

        } else if (token.getTokenType() == TokenType.ID) {

            derivativeFIDI1();

        } else if (token.getTokenType() == TokenType.NUM_INT) {

            derivativeOPNUM();
            derivativeEXPNUM();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.RELOP) {
                //LOG ERRO
            }

            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.NUM_FLOAT) {

            derivativeOPNUM();
            derivativeEXPNUM();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.RELOP) {
                //LOG ERRO
            }

            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.L_PAR) {

            derivativeEXPNUM();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.R_PAR) {
                //LOG ERRO
            }

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.RELOP) {
                //LOG ERRO
            }

            derivativeEXPNUM();

        } else {
            //LOG ERRO
        }
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

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.RELOP) {
                // log erro
            }

            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.TERM || token.getTokenType() == TokenType.R_PAR) { //fazer um hashmap do follow dessa regra

            lexicalAnalyzer.storeToken(token);

        } else {
            //log erro
        }
    }

    private void derivativeFVALLOG() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_OP) {

            derivativeEXPLO();

        } else if (token.getTokenType() == TokenType.TERM || token.getTokenType() == TokenType.R_PAR) { // follow fvallog

            lexicalAnalyzer.storeToken(token);

        } else {
            //LOG ERRO
        }
    }

    private void derivativeEXPNUM() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ID || token.getTokenType() == TokenType.NUM_INT || token.getTokenType() == TokenType.NUM_FLOAT) { // first de val

            lexicalAnalyzer.storeToken(token);
            derivativeVAL();
            derivativeXEXPNUM();

        } else if (token.getTokenType() == TokenType.L_PAR) {

            derivativeEXPNUM();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.R_PAR) {
                //LOG ERRO
            }

        } else {
            //LOG ERRO
        }
    }

    private void derivativeXEXPNUM() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ARIT_AS || token.getTokenType() == TokenType.ARIT_MD) { //first opnum

            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.RELOP) { //adicionar o follow de XEXPNUM

            lexicalAnalyzer.storeToken(token);

        } else {
            //LOG ERRO
        }
    }

    private void derivativeOPNUM() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ARIT_AS) {
            //sum number
        } else if (token.getTokenType() == TokenType.ARIT_MD) {
            //md number
        } else {
            //LOG ERRO
        }
    }

    private void derivativeVAL() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ID) {

        } else if (token.getTokenType() == TokenType.NUM_INT) {

        } else if (token.getTokenType() == TokenType.NUM_FLOAT) {

        } else {
            //LOG ERRO
        }
    }

    private void derivativeREP() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.FOR){

            lexicalAnalyzer.storeToken(token);
            derivativeREPF();

        } else  if (token.getTokenType() == TokenType.WHILE){

            lexicalAnalyzer.storeToken(token);
            derivativeREPW();

        } else {
            //LOG ERRO
        }
    }

    private void derivativeREPF() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.FOR){
            //LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if(token.getTokenType() != TokenType.ID){
            //LOG ERRO
        }

        token = lexicalAnalyzer.nextToken();
        if(token.getTokenType() != TokenType.ASSIGN){
            //LOG ERRO
        }

        derivativeEXPNUM();

        token = lexicalAnalyzer.nextToken();
        if(token.getTokenType() != TokenType.TO){
            //LOG ERRO
        }

        derivativeEXPNUM();
        derivativeBLOCO();
    }

    private void derivativeREPW() throws IOException {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.WHILE){
            //LOG ERRO
        }

        derivativeINTRENAL();
        derivativeBLOCO();
    }

    private void derivativeINTRENAL() throws IOException {

        Token token = lexicalAnalyzer.nextToken();
        if(token.getTokenType() != TokenType.L_PAR){
            //LOG ERRO
        }

        derivativeEXPLO();

        token = lexicalAnalyzer.nextToken();
        if(token.getTokenType() != TokenType.R_PAR){
            //LOG ERRO
        }
    }

    private void noticeError(String actual, String given) {
        ErrorHandler.getInstance().addError(new SyntaticError(actual, given));
    }

    //FID1 esta duplicado na gramatica
}
