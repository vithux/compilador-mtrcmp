/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package symbol;

import token.TokenBuilder;
import utils.Report;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public final class SymbolTable extends Report {

    private final Map<String, Symbol> symbols;

    private static SymbolTable instance;

    private SymbolTable() {
        symbols = new HashMap<>();
        symbols.putAll(ReservedSymbols.getSymbols());
    }

    @Override
    public String getReportName() {
        return "SYMBOL TABLE REPORT";
    }

    @Override
    public String getReportContent() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());

        for (Symbol symbol : symbols.values()) {
            stringJoiner.add(symbol.toString());
        }

        return stringJoiner.toString();
    }

    public void registerSymbol(String identifier, TokenBuilder token) {
        symbols.put(identifier, new Symbol(identifier, SymbolType.IDENTIFIER, token));
    }

    public boolean isReservedKeyword(String identifier) {
        return ReservedSymbols.getSymbols().containsKey(identifier);
    }

    public Symbol getSymbol(String id) {
        return symbols.get(id);
    }

    public static SymbolTable getInstance() {
        if (instance == null) {
            instance = new SymbolTable();
        }

        return instance;
    }
}
