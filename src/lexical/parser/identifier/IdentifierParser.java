/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package lexical.parser.identifier;

import automata.FiniteStateMachine;
import exceptions.IllegalTokenException;
import exceptions.NoSuchTransitionException;
import exceptions.ReservedIdentifierException;
import lexical.parser.Parser;
import loader.FileLoader;
import symbol.SymbolTable;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import java.io.EOFException;
import java.io.IOException;

public class IdentifierParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) throws IOException, ReservedIdentifierException, IllegalTokenException {
        StringBuilder lexeme = new StringBuilder();
        FiniteStateMachine stateMachine = IdentifierParserStateMachine.getInstance();

        while (true) {
            try {
                char currentChar = fileLoader.getNextChar();

                stateMachine = stateMachine.consumeToken(currentChar);
                lexeme.append(currentChar);
            }
            catch (NoSuchTransitionException e) {
                if (!stateMachine.isFinal()) {
                    fileLoader.resetLastChar();
                    throw new IllegalTokenException(e.getToken(), lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
                }

                break;
            }
            catch (EOFException e) {
                break;
            }
        }

        String identifier = lexeme.toString();

        if (SymbolTable.getInstance().isReservedKeyword(identifier)) {
            throw new ReservedIdentifierException(identifier, fileLoader.getLine(), fileLoader.getColumn() - identifier.length());
        }

        TokenBuilder tokenBuilder = new TokenBuilder().setTokenType(TokenType.ID).setLexeme(lexeme).setCursorLocation(fileLoader);
        SymbolTable.getInstance().registerSymbol(identifier, tokenBuilder);

        return tokenBuilder.build();
    }
}
