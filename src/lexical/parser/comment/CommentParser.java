package lexical.parser.comment;

import handler.ErrorHandler;
import handler.ErrorType;
import lexical.parser.Parser;
import loader.FileLoader;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import java.io.EOFException;
import java.io.IOException;

import static utils.Constants.TOKEN_COMMENT_END;

// @TODO: Implementar metodo
public class CommentParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) {
        char character;
        StringBuilder lexeme = new StringBuilder();

        try {
            fileLoader.resetLastChar();
            character = fileLoader.getNextChar();
            lexeme.append(character);

            do {
                character = fileLoader.getNextChar();
                lexeme.append(character);
            }
            while (character != TOKEN_COMMENT_END);
        } catch (EOFException e) {
            noticeError(lexeme, fileLoader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    void noticeError(StringBuilder lexeme, FileLoader fileLoader) {
        ErrorHandler.getInstance()
                .addError(ErrorType.EXPECTED_TOKEN, lexeme.toString(), TOKEN_COMMENT_END, fileLoader.getLine(), fileLoader.getColumn());
    }

}
