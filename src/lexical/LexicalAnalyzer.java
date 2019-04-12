/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package lexical;

import error.DuplicatedIdentifierError;
import error.ExpectedTokenError;
import error.IllegalTokenError;
import error.UnexpectedTokenError;
import error.handler.ErrorHandler;
import exceptions.*;
import lexical.parser.AbstractParserFactory;
import lexical.parser.ParserNames;
import loader.FileLoader;
import symbol.SymbolTable;
import token.Token;
import token.TokenBuilder;
import token.TokenType;
import token.builders.EOFTokenBuilder;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static utils.Constants.*;

public class LexicalAnalyzer {

    private FileLoader fileLoader;

    public LexicalAnalyzer(String fileName) throws FileNotFoundException {
        this.fileLoader = new FileLoader(fileName);
    }

    public Token nextToken() throws IOException {
        while (true) {
            try {
                char character = fileLoader.getNextChar();

                if (!Character.isWhitespace(character)) {
                    try {
                        Token token = getToken(character);

                        if (token != null) {
                            return token;
                        }
                    }
                    catch (UnexpectedTokenException e) {
                        ErrorHandler.getInstance().addError(UnexpectedTokenError.from(e));
                    }
                }
            }
            catch (EOFException e) {
                return new EOFTokenBuilder().withCursorLocation(fileLoader);
            }
        }
    }

    private Token getToken(char character) throws UnexpectedTokenException, IOException {
        switch (character) {
            case TOKEN_TERMINATOR:
                return createToken(TokenType.TERM, character);

            case TOKEN_RIGHT_PARENTHESIS:
                return createToken(TokenType.R_PAR, character);

            case TOKEN_LEFT_PARENTHESIS:
                return createToken(TokenType.L_PAR, character);

            case TOKEN_ARITHMETIC_PLUS:
            case TOKEN_ARITHMETIC_MINUS:
                return createToken(TokenType.ARIT_AS, character);

            case TOKEN_ARITHMETIC_MULTIPLY:
            case TOKEN_ARITHMETIC_DIVIDE:
                return createToken(TokenType.ARIT_MD, character);

            case TOKEN_ASSIGNMENT:
                return useParser(ParserNames.ASSIGN);

            case TOKEN_RELATIONAL_OPERATION:
                return useParser(ParserNames.RELOP);

            case TOKEN_COMMENT_START:
                return useParser(ParserNames.COMMENT);

            case TOKEN_QUOTE:
                return useParser(ParserNames.LITERAL);

            default:
                if (Character.isDigit(character)) {
                    return useParser(ParserNames.NUMERIC);
                }

                if (Character.isLetter(character)) {
                    return useParser(ParserNames.IDENTIFIER);
                }

                throw new UnexpectedTokenException(character, fileLoader.getLine(), fileLoader.getColumn());
        }
    }

    private Token useParser(String parserName) throws IOException {
        fileLoader.resetLastChar();

        try {
            return AbstractParserFactory.getByName(parserName).parse(fileLoader);
        }
        catch (IllegalTokenException e) {
            ErrorHandler.getInstance().addError(IllegalTokenError.from(e));
            return null;
        }
        catch (ExpectedTokenException e) {
            ErrorHandler.getInstance().addError(ExpectedTokenError.from(e));
            return null;
        }
        catch (DuplicatedIdentifierException e) {
            ErrorHandler.getInstance().addError(DuplicatedIdentifierError.from(e));
            return null;
        }
        catch (ReservedIdentifierException e) {
            return SymbolTable
                    .getInstance()
                    .getSymbol(e.getIdentifier())
                    .getToken()
                    .setLine(e.getLine())
                    .setColumn(e.getColumn() - e.getIdentifier().length())
                    .build();
        }
    }

    private Token createToken(TokenType tokenType, char lexeme) {
        return createToken(tokenType, Character.toString(lexeme));
    }

    private Token createToken(TokenType tokenType, String lexeme) {
        return new TokenBuilder().setTokenType(tokenType).setLexeme(lexeme).withCursorLocation(fileLoader);
    }
}
