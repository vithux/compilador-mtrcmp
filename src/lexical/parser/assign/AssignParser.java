package lexical.parser.assign;

import exceptions.ExpectedTokenException;
import lexical.parser.Parser;
import loader.FileLoader;
import token.builders.AssignTokenBuilder;
import token.Token;

import java.io.IOException;

import static utils.Constants.TOKEN_ASSIGNMENT;
import static utils.Constants.TOKEN_DASH;

public class AssignParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) throws IOException, ExpectedTokenException {
        char character = fileLoader.getNextChar();

        if (character == TOKEN_ASSIGNMENT) {
            character = fileLoader.getNextChar();

            if (character == TOKEN_DASH) {
                return new AssignTokenBuilder().withCursorLocation(fileLoader);
            }

            throw new ExpectedTokenException(
                    character,
                    Character.toString(TOKEN_ASSIGNMENT),
                    fileLoader.getLine(),
                    fileLoader.getColumn()
            );
        }

        return null;
    }
}
