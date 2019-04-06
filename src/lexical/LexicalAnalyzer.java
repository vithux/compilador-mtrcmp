package lexical;

import handler.ErrorHandler;
import handler.ErrorType;
import lexical.parser.AbstractParserFactory;
import lexical.parser.Parser;
import lexical.parser.ParserNames;
import loader.FileLoader;
import token.Token;
import token.TokenBuilder;
import token.TokenType;
import utils.Constants;

import javax.management.StringValueExp;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static utils.Constants.*;

public class LexicalAnalyzer {
    private static final String EOF_LEXEME = "EOF";

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
                    } catch (Exception e) {
                        ErrorHandler.getInstance().addError(ErrorType.UNEXPECTED_TOKEN,null,character,fileLoader.getLine(),fileLoader.getColumn());
                    }
                }
            } catch (EOFException e) {
                return createToken(TokenType.EOF, EOF_LEXEME);
            }
        }
    }

    private Token getToken(char character) throws Exception {
        switch (character) {
            case Constants.TOKEN_TERMINATOR:
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

            // @TODO: Mover para Parser
            case TOKEN_RELATIONAL_OPERATION:
                return useParser(ParserNames.RELOP);

            case TOKEN_COMMENT_START:
                return useParser(ParserNames.COMMENT);

            case TOKEN_QUOTE:
                return useParser(ParserNames.LITERAL);

            default:
                if (Character.isDigit(character)) {
                    return useParser(ParserNames.NUMERIC);
                } else if (Character.isLetter(character)) {
                    return isLetter(fileLoader, character);
                } else {
                    throw new Exception(String.valueOf(character));
                }
        }
    }

    private Token useParser(String parserName) throws IOException {
        fileLoader.resetLastChar();

        return AbstractParserFactory
                .getByName(parserName)
                .parse(fileLoader);
    }

    private Token createToken(TokenType tokenType, char lexeme) {
        return createToken(tokenType, Character.toString(lexeme));
    }

    private Token createToken(TokenType tokenType, String lexeme) {
        return new TokenBuilder()
                .setTokenType(tokenType)
                .setCursorLocation(fileLoader)
                .setLexeme(lexeme)
                .build();
    }

    // @TODO: Implementar metodo
    // @TODO: Mover para parser
    private Token isRelop(FileLoader fileLoader) {
        return null;
    }

    // @TODO: Implementar metodo
    // @TODO: Mover para parser
    private Token isLetter(FileLoader fileLoader, char c) {
        return null;
    }
}
