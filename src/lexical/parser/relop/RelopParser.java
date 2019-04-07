package lexical.parser.relop;

import exceptions.ExpectedTokenException;
import exceptions.IllegalTokenException;
import lexical.parser.Parser;
import loader.FileLoader;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import java.io.EOFException;
import java.io.IOException;

import static utils.Constants.TOKEN_RELATIONAL_OPERATION;

public class RelopParser implements Parser {

    private char getToken(FileLoader fileLoader) throws IOException {
        return fileLoader.getNextChar();
    }

    private Token generateToken(FileLoader fileLoader, String lexeme) {
        return new TokenBuilder().setLexeme(lexeme).setTokenType(TokenType.RELOP).withCursorLocation(fileLoader);
    }

    private Token greaterThanOperation(FileLoader fileLoader, StringBuilder lexeme) throws IllegalTokenException, IOException {
        char token = getToken(fileLoader);
        lexeme.append(token);

        if (token != 't' && token != 'e') {
            throw new IllegalTokenException(token, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
        }

        return generateToken(fileLoader, lexeme.toString());
    }

    private Token lessThanOperation(FileLoader fileLoader, StringBuilder lexeme) throws IllegalTokenException, IOException {
        char token = getToken(fileLoader);
        lexeme.append(token);

        if (token != 't' && token != 'e') {
            throw new IllegalTokenException(token, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
        }

        return generateToken(fileLoader, lexeme.toString());
    }

    private Token equalOperation(FileLoader fileLoader, StringBuilder lexeme) throws IllegalTokenException, IOException {
        char token = getToken(fileLoader);
        lexeme.append(token);

        if (token != 'q') {
            throw new IllegalTokenException(token, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
        }

        return generateToken(fileLoader, lexeme.toString());
    }

    private Token differentOperation(FileLoader fileLoader, StringBuilder lexeme) throws IllegalTokenException, IOException {
        char token = getToken(fileLoader);
        lexeme.append(token);

        if (token == 'f') {
            return generateToken(fileLoader, lexeme.toString());
        }

        throw new IllegalTokenException(token, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
    }

    @Override
    public Token parse(FileLoader fileLoader) throws IOException, IllegalTokenException, ExpectedTokenException {
        char token;
        StringBuilder lexeme = new StringBuilder();

        try {
            token = getToken(fileLoader);
            lexeme.append(token);

            if (token != TOKEN_RELATIONAL_OPERATION) {
                throw new IllegalTokenException(token, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
            }

            token = getToken(fileLoader);
            lexeme.append(token);

            switch (token) {
                case 'g': {
                    return greaterThanOperation(fileLoader, lexeme);
                }

                case 'l': {
                    return lessThanOperation(fileLoader, lexeme);
                }

                case 'e': {
                    return equalOperation(fileLoader, lexeme);
                }

                case 'd': {
                    return differentOperation(fileLoader, lexeme);
                }

                default: {
                    throw new IllegalTokenException(token, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
                }
            }
        }
        catch (EOFException e) {
            throw new ExpectedTokenException('g', lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
        }
    }
}
