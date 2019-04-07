package lexical.parser.literal;

import exceptions.ExpectedTokenException;
import lexical.parser.Parser;
import loader.FileLoader;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import java.io.EOFException;
import java.io.IOException;

import static utils.Constants.TOKEN_QUOTE;

public class LiteralParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) throws IOException, ExpectedTokenException {
        StringBuilder lexeme = new StringBuilder();

        try {
            char character = fileLoader.getNextChar();
            lexeme.append(character);

            do {
                character = fileLoader.getNextChar();
                lexeme.append(character);
            }
            while (character != TOKEN_QUOTE);

            return new TokenBuilder().setLexeme(lexeme).setTokenType(TokenType.LITERAL).withCursorLocation(fileLoader);
        }
        catch (EOFException e) {
            throw new ExpectedTokenException(TOKEN_QUOTE, lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
        }
    }
}
