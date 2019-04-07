package lexical.parser.comment;

import exceptions.ExpectedTokenException;
import lexical.parser.Parser;
import loader.FileLoader;
import token.Token;

import java.io.EOFException;
import java.io.IOException;

import static utils.Constants.*;

public class CommentParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) throws IOException, ExpectedTokenException {
        char character;
        StringBuilder lexeme = new StringBuilder();

        try {
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
                }
                while (!end);
            }
            else {
                throw new ExpectedTokenException(TOKEN_HASH, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
            }
        }
        catch (EOFException e) {
            char lastChar = lexeme.charAt(lexeme.length() - 1);
            char illegalChar;

            if ((lexeme.length() <= 2 && (lastChar == TOKEN_HASH || lastChar == TOKEN_COMMENT_START)) || lastChar != TOKEN_HASH) {
                illegalChar = TOKEN_HASH;
            }
            else {
                illegalChar = TOKEN_COMMENT_END;
            }

            throw new ExpectedTokenException(illegalChar, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
        }

        return null;
    }
}
