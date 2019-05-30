package symbol;

import token.TokenType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static token.TokenType.*;
import static utils.Constants.*;

public class Follow {

    private static final Map<String, List<TokenType>> symbols = new HashMap<>();

    static {
        symbols.put(S, singletonList(EOF));
        symbols.put(CMDS, singletonList(END));
        symbols.put(CMD, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG));
        symbols.put(DECL, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG));
        symbols.put(COND, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG));
        symbols.put(CNDB, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG));
        symbols.put(ATRIB, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG));
        symbols.put(EXP, singletonList(TERM));
        symbols.put(FID, singletonList(TERM));
        symbols.put(FOPNUM, singletonList(TERM));
        symbols.put(FEXPNUM_1, singletonList(TERM));
        symbols.put(FNUMINT, singletonList(TERM));
        symbols.put(FOPNUM_1, singletonList(TERM));
        symbols.put(FEXPNUM_2, singletonList(TERM));
        symbols.put(FNUMFLOAT, singletonList(TERM));
        symbols.put(FOPNUM_2, singletonList(TERM));
        symbols.put(FEXPNUM_3, singletonList(TERM));
        symbols.put(FLPAR, singletonList(TERM));
        symbols.put(FEXPNUM, singletonList(TERM));
        symbols.put(FRPAR, singletonList(TERM));
        symbols.put(FID_1, asList(TERM, R_PAR));
        symbols.put(FVALLOG, asList(TERM, R_PAR));
        symbols.put(XEXPNUM, asList(RELOP, TO, BEGIN, DECLARE, IF, ID, FOR, WHILE, TERM, R_PAR));
        symbols.put(OPNUM, asList(L_PAR, ID, NUM_INT, NUM_FLOAT));
        symbols.put(VAL, asList(ARIT_AS, ARIT_MD, RELOP, TO, BEGIN, DECLARE, IF, ID, FOR, WHILE, TERM, R_PAR));
        symbols.put(REP, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG, ELSE));
        symbols.put(REPF, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG, ELSE));
        symbols.put(EXPNUM, asList(RELOP, TO, BEGIN, DECLARE, IF, ID, FOR, WHILE, TERM, R_PAR));
        symbols.put(REPW, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG, ELSE));
        symbols.put(EXPLO, asList(TERM, R_PAR));
        symbols.put(BLOCO, asList(DECLARE, IF, FOR, WHILE, ID, END, END_PROG, ELSE));
    }

    public static Map<String, List<TokenType>> getSymbols() {
        return symbols;
    }
}
