package lexical;

import handler.ErrorHandler;
import lexical.parser.AbstractParserFactory;
import lexical.parser.Parser;
import lexical.parser.ParserNames;
import loader.FileLoader;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

// @TODO: Padronizar estrutura de erros
public class LexicalAnalyzer {

    private static final char TOKEN_TERMINATOR = ';';
    private static final char TOKEN_RIGHT_PARENTHESIS = ')';
    private static final char TOKEN_LEFT_PARENTHESIS = '(';
    private static final char TOKEN_ARITHMETIC_PLUS = '+';
    private static final char TOKEN_ARITHMETIC_MINUS = '-';
    private static final char TOKEN_ARITHMETIC_MULTIPLY = '*';
    private static final char TOKEN_ARITHMETIC_DIVIDE = '/';
    private static final char TOKEN_ASSIGNMENT = '<';
    private static final char TOKEN_RELATIONAL_OPERATION = '$';
    private static final char TOKEN_QUOTE = '"';
    private static final char TOKEN_DASH = '-';

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
                    }
                    catch (Exception e) {
                        ErrorHandler.getInstance().addError(e.getMessage());
                    }
                }
            }
            catch (EOFException e) {
                return createToken(TokenType.EOF, EOF_LEXEME);
            }
        }
    }

    private Token getToken(char character) throws Exception {
        switch (character) {
            case TOKEN_TERMINATOR:
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

            // @TODO: Mover para parser
            case TOKEN_ASSIGNMENT:
                return isAssign(fileLoader);

            // @TODO: Mover para Parser
            case TOKEN_RELATIONAL_OPERATION:
                return isRelop(fileLoader);

            // @TODO: Mover para Parser
            case TOKEN_QUOTE:
                return isLiteral(fileLoader);

            default:
                if (Character.isDigit(character)) {
                    return useParser(ParserNames.NUMERIC);
                }
                else if (Character.isLetter(character)) {
                    return isLetter(fileLoader, character);
                }
                else {
                    throw new Exception("Unexpected token: " + character);
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

    // @Todo: Mover para parser
    private Token isLiteral(FileLoader fileLoader) {
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
            while (character != TOKEN_QUOTE);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return createToken(TokenType.LITERAL, lexeme.toString());
    }

    // @TODO: Mover para parser
    private Token isAssign(FileLoader fileLoader) {
        try {
            char character = fileLoader.getNextChar();

            if (character == TOKEN_DASH) {
                return createToken(TokenType.ASSIGN, "<-");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
