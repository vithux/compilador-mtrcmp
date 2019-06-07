/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */

package symbol;

import token.TokenType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static symbol.NonTerminal.*;
import static token.TokenType.*;

public class First {

    private static final Map<NonTerminal, List<TokenType>> symbols = new HashMap<>();

    static {
        symbols.put(S, singletonList(PROGRAM));
        symbols.put(CMDS, asList(DECLARE, IF, FOR, WHILE, ID));
        symbols.put(CMD, asList(DECLARE, IF, FOR, WHILE, ID));
        symbols.put(DECL, singletonList(DECLARE));
        symbols.put(COND, singletonList(IF));
        symbols.put(CNDB, singletonList(ELSE));
        symbols.put(ATRIB, singletonList(ID));
        symbols.put(EXP, asList(LOGIC_VAL, ID, NUM_INT, NUM_FLOAT, L_PAR, LITERAL));
        symbols.put(FID, asList(LOGIC_OP, ARIT_AS, ARIT_MD));
        symbols.put(FOPNUM, asList(L_PAR, NUM_INT, NUM_FLOAT));
        symbols.put(FOPNUM_1, asList(L_PAR, ID, NUM_INT, NUM_FLOAT));
        symbols.put(FOPNUM_2, asList(L_PAR, ID, NUM_INT, NUM_FLOAT));
        symbols.put(FLPAR, asList(L_PAR, ID, NUM_INT, NUM_FLOAT));
        symbols.put(FEXPNUM, singletonList(R_PAR));
        symbols.put(FRPAR, singletonList(RELOP));
        symbols.put(FID_1, asList(RELOP, LOGIC_OP, ARIT_AS, ARIT_MD));
        symbols.put(FVALLOG, singletonList(LOGIC_OP));
        symbols.put(XEXPNUM, asList(ARIT_AS, ARIT_MD));
        symbols.put(OPNUM, asList(ARIT_AS, ARIT_MD));
        symbols.put(VAL, asList(ID, NUM_INT, NUM_FLOAT));
        symbols.put(REP, asList(FOR, WHILE));
        symbols.put(REPF, singletonList(FOR));
        symbols.put(EXPNUM, asList(L_PAR, ID, NUM_FLOAT, NUM_INT));
        symbols.put(REPW, singletonList(WHILE));
        symbols.put(EXPLO, asList(LOGIC_VAL, ID, NUM_INT, NUM_FLOAT, L_PAR));
        symbols.put(BLOCO, asList(BEGIN, DECLARE, IF, ID, FOR, WHILE));
    }

    public static Map<NonTerminal, List<TokenType>> getSymbols() {
        return symbols;
    }
}
