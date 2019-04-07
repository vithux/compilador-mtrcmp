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

    @Override
    public Token parse(FileLoader fileLoader) throws IOException, IllegalTokenException, ExpectedTokenException {
        char character;
        StringBuilder lexeme = new StringBuilder();

        try {
            character = getCharacter(fileLoader, lexeme);

            if (character != TOKEN_RELATIONAL_OPERATION) {
                throw new IllegalTokenException(character, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
            }

            while (true) {
                character = getCharacter(fileLoader, lexeme);

                switch (character) {
                    case 'g': {
                        character = getCharacter(fileLoader, lexeme);

                        if (character == 't' || character == 'e') {
                            return generateToken(fileLoader, lexeme.toString());
                        }

                        throw new IllegalTokenException(character, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
                    }

                    case 'l': {
                        character = getCharacter(fileLoader, lexeme);

                        if (character == 't' || character == 'e') {
                            return generateToken(fileLoader, lexeme.toString());
                        }

                        throw new IllegalTokenException(character, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
                    }

                    case 'e': {
                        character = getCharacter(fileLoader, lexeme);

                        if (character == 'q') {
                            return generateToken(fileLoader, lexeme.toString());
                        }

                        throw new IllegalTokenException(character, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
                    }

                    case 'd':
                        character = getCharacter(fileLoader, lexeme);

                        if (character == 'f') {
                            return generateToken(fileLoader, lexeme.toString());
                        }

                        throw new IllegalTokenException(character, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
                }
            }
        }
        catch (EOFException e) {
            throw new ExpectedTokenException('g', lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
        }
    }

    private char getCharacter(FileLoader fileLoader, StringBuilder lexeme) throws IOException {
        char character;
        character = fileLoader.getNextChar();
        lexeme.append(character);
        return character;
    }

    private Token generateToken(FileLoader fileLoader, String lexeme) {
        return new TokenBuilder()
                .setTokenType(TokenType.RELOP)
                .setCursorLocation(fileLoader)
                .setLexeme(lexeme)
                .build();
    }


}
