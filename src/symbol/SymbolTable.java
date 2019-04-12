/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package symbol;

import token.Token;
import token.TokenBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public final class SymbolTable {

    private final Map<String, Symbol> symbols;

    private static SymbolTable instance;

    private SymbolTable() {
        symbols = new HashMap<>();
        symbols.putAll(ReservedSymbols.getSymbols());
    }

    public void registerSymbol(String identifier, TokenBuilder token) {
        symbols.put(identifier, new Symbol(identifier, SymbolType.IDENTIFIER, token));
    }

    public boolean isReservedKeyword(String identifier) {
        return ReservedSymbols.getSymbols().containsKey(identifier);
    }

    public boolean isIdentifierAlreadyDefined(String id) {
        return getSymbol(id) != null;
    }

    public Symbol getSymbol(String id) {
        return symbols.get(id);
    }

    public String getReport() {
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("### SYMBOL TABLE REPORT ###");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        for (Symbol symbol : symbols.values()) {
            stringJoiner.add(symbol.toString());
        }

        stringBuilder.append(stringJoiner.toString());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        return stringBuilder.toString();
    }

    public static SymbolTable getInstance() {
        if (instance == null) {
            instance = new SymbolTable();
        }

        return instance;
    }
}
