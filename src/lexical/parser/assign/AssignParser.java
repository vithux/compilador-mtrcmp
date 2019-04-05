package lexical.parser.assign;

import handler.ErrorHandler;
import handler.ErrorType;
import lexical.parser.Parser;
import loader.FileLoader;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import static utils.Constants.*;

public class AssignParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) {
        try {
            char character = fileLoader.getNextChar();

            switch (character) {
                case TOKEN_ASSIGNMENT:
                    character = fileLoader.getNextChar();
                    if (character == TOKEN_DASH) {
                        return new TokenBuilder()
                                .setTokenType(TokenType.ASSIGN)
                                .setCursorLocation(fileLoader)
                                .setLexeme(ASSIGN_LEXEME)
                                .build();
                    } else {
                        ErrorHandler.getInstance()
                                .addError(ErrorType.UNEXPECTED_TOKEN, "<", character, fileLoader.getLine(), fileLoader.getColumn());
                    }

                default:
                    return null;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
