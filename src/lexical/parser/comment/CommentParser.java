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

import static utils.Constants.*;


public class CommentParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) {
        char character;
        StringBuilder lexeme = new StringBuilder();

        try {
            fileLoader.resetLastChar();
            character = fileLoader.getNextChar();
            lexeme.append(character);

            character = fileLoader.getNextChar();
            if (character == TOKEN_HASH) {
                lexeme.append(character);

                boolean end = false;
                do {
                    character = fileLoader.getNextChar();
                    lexeme.append(character);

                    if (character == TOKEN_HASH) {
                        character = fileLoader.getNextChar();
                        lexeme.append(character);

                        if (character == TOKEN_COMMENT_END) {
                            end = true;
                        }
                    }
                } while (!end);

            } else {
                noticeError(lexeme, fileLoader, TOKEN_HASH);
            }

        } catch (EOFException e) {
            char lastChar = lexeme.charAt(lexeme.length() - 1);
            char illegalChar;

            if ((lexeme.length() <= 2 && (lastChar == TOKEN_HASH || lastChar == TOKEN_COMMENT_START)) ||
                lastChar != TOKEN_HASH) {
                illegalChar = TOKEN_HASH;
            } else {
                illegalChar = TOKEN_COMMENT_END;
            }

            noticeError(lexeme, fileLoader, illegalChar);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    void noticeError(StringBuilder lexeme, FileLoader fileLoader, char character) {
        ErrorHandler.getInstance()
                .addError(ErrorType.EXPECTED_TOKEN, lexeme.toString(), character, fileLoader.getLine(), fileLoader.getColumn());
    }

}
