/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package symbol;

import token.TokenBuilder;

public class Symbol {

    private String name;

    private SymbolType type;

    private TokenBuilder token;

    public Symbol(String name, SymbolType type, TokenBuilder token) {
        this.name = name;
        this.type = type;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public SymbolType getType() {
        return type;
    }

    public TokenBuilder getToken() {
        return token;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Symbol{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
