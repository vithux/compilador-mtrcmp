/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package syntactic;

import error.SyntaticError;
import error.handler.ErrorHandler;
import lexical.LexicalAnalyzer;
import symbol.First;
import symbol.Follow;
import symbol.NonTerminal;
import symbol.SymbolTable;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

import static java.util.Collections.singletonList;
import static symbol.NonTerminal.*;

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

    public void process() {
        derivativeS();
    }

    private void derivativeS() {

        Token token = lexicalAnalyzer.nextToken();
        boolean erro;

        if (token.getTokenType() != TokenType.PROGRAM) {
            noticeError(TokenType.PROGRAM, token);
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.ID) {
            noticeError(TokenType.ID, token);
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TERM) {
            noticeError(TokenType.TERM, token);
            erro = true;
        } else {
            erro = false;
        }

        /*
            Tratamento básico de erro, caso erro no inicio do programa
            o analizador tenta sincronizar procurando o proximo token válido
            no caso ele espera um TERM.
         */

        if (erro) {
            while (true) {
                token = lexicalAnalyzer.nextToken();
                if (token.getTokenType() != TokenType.TERM) {
                    return;
                }
            }
        }

        derivativeBLOCO();

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.END_PROG) {
            noticeError(TokenType.END_PROG, token);
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TERM) {
            noticeError(TokenType.TERM, token);
        }
    }


    private void derivativeBLOCO() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.BEGIN) {

            derivativeCMDS();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.END) {
                noticeError(TokenType.END, token);
            }

        } else if (token.getTokenType() == TokenType.DECLARE) {

            lexicalAnalyzer.storeToken(token);
            derivativeCMD();

        } else {
            noticeError(asList(TokenType.DECLARE, TokenType.BEGIN), token);
        }
    }

    private void derivativeCMDS() {

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

        } else if (containsFollow(CMDS, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(asList(TokenType.DECLARE, TokenType.IF, TokenType.FOR, TokenType.WHILE, TokenType.ID), token);
        }
    }

    private void derivativeCMD() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.DECLARE) {

            lexicalAnalyzer.storeToken(token);
            derivativeDECL();

        } else if (token.getTokenType() == TokenType.IF) {

            lexicalAnalyzer.storeToken(token);
            derivativeCOND();

        } else if (containsFirst(REP, token)) {

            lexicalAnalyzer.storeToken(token);
            derivativeREP();

        } else if (token.getTokenType() == TokenType.ID) {

            lexicalAnalyzer.storeToken(token);
            derivativeATRIB();

        } else {
            noticeError(asList(TokenType.DECLARE, TokenType.IF, TokenType.ID), token);
        }
    }

    private void derivativeDECL() {
        Token token = lexicalAnalyzer.nextToken();
        Token idToken = null;

        if (token.getTokenType() != TokenType.DECLARE) {
            noticeError(TokenType.DECLARE, token);
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.ID) {
            noticeError(TokenType.ID, token);
        } else {
            idToken = token;
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TYPE) {
            noticeError(TokenType.TYPE, token);
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TERM) {
            noticeError(TokenType.TERM, token);
        }

        if (idToken != null) {
            //aqui valida se a variavel já foi declarada
            if (SymbolTable.getInstance().getSymbol(token.getLexeme()) != null) {
                // LOG ERRO
            } else {
                SymbolTable.getInstance().registerSymbol(idToken.getLexeme(), new TokenBuilder().copyOf(token));
            }
        }
    }

    private void derivativeCOND() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.IF) {
            noticeError(TokenType.IF, token);
        }

        derivativeINTRENAL();

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.THEN) {
            noticeError(TokenType.THEN, token);
        }

        derivativeBLOCO();
        derivativeCNDB();
    }

    private void derivativeCNDB() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ELSE) {

            derivativeBLOCO();

        } else if (containsFollow(CNDB, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(TokenType.ELSE, token);
        }
    }

    private void derivativeATRIB() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.ID) {
            noticeError(TokenType.ID, token);
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.ASSIGN) {
            noticeError(TokenType.ASSIGN, token);
        }

        derivativeEXP();

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TERM) {
            noticeError(TokenType.TERM, token);
        }
    }

    private void derivativeEXP() {

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

        } else {
            noticeError(asList(TokenType.LOGIC_VAL, TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT, TokenType.L_PAR, TokenType.LITERAL), token);
        }
    }

    private void derivativeFID() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_OP) {

            lexicalAnalyzer.storeToken(token);
            derivativeFVALLOG();

        } else if (containsFirst(OPNUM, token)) {

            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeFOPNUM();

        } else if (containsFollow(FID, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(TokenType.LOGIC_OP, token);
        }
    }

    private void derivativeFOPNUM() {

        Token token = lexicalAnalyzer.nextToken();

        if (containsFirst(EXPNUM, token)) {

            lexicalAnalyzer.storeToken(token);
            derivativeEXPNUM();
            derivativeFRPAR();

        } else {
            noticeError(TokenType.L_PAR, token);
        }
    }

    private void derivativeFNUM() {

        Token token = lexicalAnalyzer.nextToken();

        if (containsFirst(OPNUM, token)) {

            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeFOPNUM();

        } else if (containsFollow(FNUM, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(createListSymbols(asList(First.getSymbols().get(OPNUM), Follow.getSymbols().get(FNUM))), token);
        }
    }


    private void derivativeFLPAR() {

        Token token = lexicalAnalyzer.nextToken();

        if (containsFirst(EXPNUM, token)) {

            lexicalAnalyzer.storeToken(token);
            derivativeEXPNUM();
            derivativeFEXPNUM();

        } else {
            noticeError(First.getSymbols().get(EXPNUM), token);
        }
    }

    private void derivativeFEXPNUM() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.R_PAR) {

            derivativeFRPAR();

        } else if (containsFollow(FEXPNUM, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(createListSymbols(asList(Follow.getSymbols().get(FEXPNUM), singletonList(TokenType.R_PAR))), token);
        }
    }

    private void derivativeFRPAR() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.RELOP) {

            derivativeEXPNUM();

        } else if (containsFollow(FRPAR, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(createListSymbols(asList(Follow.getSymbols().get(FRPAR), singletonList(TokenType.RELOP))), token);
        }
    }

    private void derivativeEXPLO() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_VAL) {

            derivativeFVALLOG();

        } else if (token.getTokenType() == TokenType.ID) {

            derivativeFIDI1();

        } else if (token.getTokenType() == TokenType.NUM_INT || token.getTokenType() == TokenType.NUM_FLOAT) {

            derivativeOPNUM();
            derivativeEXPNUM();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.RELOP) {
                noticeError(TokenType.RELOP, token);
            }

            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.L_PAR) {

            derivativeEXPNUM();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.R_PAR) {
                noticeError(TokenType.R_PAR, token);
            }

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.RELOP) {
                noticeError(TokenType.RELOP, token);
            }

            derivativeEXPNUM();

        } else {
            noticeError(asList(TokenType.LOGIC_VAL, TokenType.ID, TokenType.NUM_INT, TokenType.RELOP, TokenType.NUM_FLOAT, TokenType.L_PAR), token);
        }
    }

    private void derivativeFIDI1() {

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
                noticeError(TokenType.RELOP, token);
            }

            derivativeEXPNUM();

        } else if (token.getTokenType() == TokenType.RELOP) {

            derivativeEXPNUM();

        } else if (containsFollow(FID_1, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(createListSymbols(asList(Follow.getSymbols().get(FID_1), asList(TokenType.LOGIC_OP, TokenType.ARIT_AS, TokenType.ARIT_MD, TokenType.RELOP))), token);
        }
    }

    private void derivativeFVALLOG() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.LOGIC_OP) {

            derivativeEXPLO();

        } else if (containsFollow(FVALLOG, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(createListSymbols(asList(Follow.getSymbols().get(FVALLOG), singletonList(TokenType.LOGIC_OP))), token);
        }
    }

    private void derivativeEXPNUM() {

        Token token = lexicalAnalyzer.nextToken();

        if (containsFirst(VAL, token)) {

            lexicalAnalyzer.storeToken(token);
            derivativeVAL();
            derivativeXEXPNUM();

        } else if (token.getTokenType() == TokenType.L_PAR) {

            derivativeEXPNUM();

            token = lexicalAnalyzer.nextToken();
            if (token.getTokenType() != TokenType.R_PAR) {
                noticeError(TokenType.R_PAR, token);
            }

        } else {
            noticeError(createListSymbols(asList(First.getSymbols().get(VAL), singletonList(TokenType.L_PAR))), token);
        }
    }

    private void derivativeXEXPNUM() {

        Token token = lexicalAnalyzer.nextToken();

        if (containsFirst(OPNUM, token)) {

            lexicalAnalyzer.storeToken(token);
            derivativeOPNUM();
            derivativeEXPNUM();

        } else if (containsFollow(XEXPNUM, token)) {

            lexicalAnalyzer.storeToken(token);

        } else {
            noticeError(createListSymbols(asList(First.getSymbols().get(OPNUM), Follow.getSymbols().get(XEXPNUM))), token);
        }
    }

    private void derivativeOPNUM() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ARIT_AS) {
            //sum number
        } else if (token.getTokenType() == TokenType.ARIT_MD) {
            //md number
        } else {
            noticeError(asList(TokenType.ARIT_AS, TokenType.ARIT_MD), token);
        }
    }

    private void derivativeVAL() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.ID) {

        } else if (token.getTokenType() == TokenType.NUM_INT) {

        } else if (token.getTokenType() == TokenType.NUM_FLOAT) {

        } else {
            noticeError(asList(TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT), token);
        }
    }

    private void derivativeREP() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() == TokenType.FOR) {

            lexicalAnalyzer.storeToken(token);
            derivativeREPF();

        } else if (token.getTokenType() == TokenType.WHILE) {

            lexicalAnalyzer.storeToken(token);
            derivativeREPW();

        } else {
            noticeError(asList(TokenType.FOR, TokenType.WHILE), token);
        }
    }

    private void derivativeREPF() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.FOR) {
            noticeError(TokenType.FOR, token);
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.ID) {
            noticeError(TokenType.ID, token);
        }

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.ASSIGN) {
            noticeError(TokenType.ASSIGN, token);
        }

        derivativeEXPNUM();

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.TO) {
            noticeError(TokenType.TO, token);
        }

        derivativeEXPNUM();
        derivativeBLOCO();
    }

    private void derivativeREPW() {

        Token token = lexicalAnalyzer.nextToken();

        if (token.getTokenType() != TokenType.WHILE) {
            noticeError(TokenType.WHILE, token);
        }

        derivativeINTRENAL();
        derivativeBLOCO();
    }

    private void derivativeINTRENAL() {

        Token token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.L_PAR) {
            noticeError(TokenType.L_PAR, token);
        }

        derivativeEXPLO();

        token = lexicalAnalyzer.nextToken();
        if (token.getTokenType() != TokenType.R_PAR) {
            noticeError(TokenType.R_PAR, token);
        }
    }

    private void noticeError(List<TokenType> expectedToken, Token receivedToken) {
        StringBuilder expected = new StringBuilder();


        for (TokenType t : expectedToken) {
            expected.append(t.toString());
            expected.append(", ");
        }

        noticeError(expected.toString(), receivedToken.getLexeme());
    }

    private void noticeError(TokenType expectedToken, Token receivedToken) {
        noticeError(expectedToken.toString(), receivedToken.getLexeme());
    }

    private void noticeError(String expected, String received) {
        ErrorHandler.getInstance().addError(new SyntaticError(expected, received));
    }

    private boolean containsFirst(NonTerminal type, Token token) {
        return First.getSymbols().get(type).contains(token.getTokenType());
    }

    private boolean containsFollow(NonTerminal type, Token token) {
        return Follow.getSymbols().get(type).contains(token.getTokenType());
    }

    private List<TokenType> createListSymbols(List<List<TokenType>> lists) {
        List<TokenType> list = new ArrayList<>();
        for (List<TokenType> l : lists) {
            list.addAll(l);
        }
        return list;
    }
}
