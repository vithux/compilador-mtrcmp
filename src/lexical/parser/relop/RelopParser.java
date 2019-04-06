package lexical.parser.relop;

import handler.ErrorHandler;
import handler.ErrorType;
import lexical.parser.Parser;
import loader.FileLoader;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import java.io.EOFException;
import java.io.IOException;

import static utils.Constants.*;

public class RelopParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) throws IOException {

        char character;
        StringBuilder lexeme = new StringBuilder();

        try {
            character = getCharacter(fileLoader, lexeme);

            switch (character) {
                case TOKEN_RELATIONAL_OPERATION:
                    while (true) {
                        character = getCharacter(fileLoader, lexeme);
                        switch (character) {
                            case 'g':
                                character = getCharacter(fileLoader, lexeme);
                                if (character == 't' || character == 'e') {
                                    return generateToken(fileLoader, lexeme.toString());
                                } else {
                                    noticeError(lexeme, character, fileLoader);
                                    return null;
                                }

                            case 'l':
                                character = getCharacter(fileLoader, lexeme);
                                if (character == 't' || character == 'e') {
                                    return generateToken(fileLoader, lexeme.toString());
                                } else {
                                    noticeError(lexeme, character, fileLoader);
                                    return null;
                                }

                            case 'e':
                                character = getCharacter(fileLoader, lexeme);
                                if (character == 'q') {
                                    return generateToken(fileLoader, lexeme.toString());
                                } else {
                                    noticeError(lexeme, character, fileLoader);
                                    return null;
                                }

                            case 'd':
                                character = getCharacter(fileLoader, lexeme);
                                if (character == 'f') {
                                    return generateToken(fileLoader, lexeme.toString());
                                } else {
                                    noticeError(lexeme, character, fileLoader);
                                    return null;
                                }
                        }
                    }
                default:
                    noticeError(lexeme, character, fileLoader);
                    return null;
            }


        } catch (EOFException e) {
            ErrorHandler.getInstance()
                    .addError(ErrorType.EXPECTED_TOKEN, lexeme.toString(), 'g', fileLoader.getLine(), fileLoader.getColumn());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private char getCharacter(FileLoader fileLoader, StringBuilder lexeme) throws IOException {
        char character;
        character = fileLoader.getNextChar();
        lexeme.append(character);
        return character;
    }

    void noticeError(StringBuilder lexeme, char charactere, FileLoader fileLoader) {
        ErrorHandler.getInstance()
                .addError(ErrorType.ILLEGAL_CHARACTER, lexeme.toString(), charactere, fileLoader.getLine(), fileLoader.getColumn());
    }

    private Token generateToken(FileLoader fileLoader, String lexeme) {
        return new TokenBuilder()
                .setTokenType(TokenType.RELOP)
                .setCursorLocation(fileLoader)
                .setLexeme(lexeme)
                .build();
    }


}
