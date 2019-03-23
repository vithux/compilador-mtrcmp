package lexical;

import loader.FileLoader;
import token.Token;
import token.TokenType;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    private FileLoader fileLoader;

    public LexicalAnalyzer(String fileName) {
        try {
            this.fileLoader = new FileLoader(fileName);
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found =(");
        }
    }

    public Token nextToken() {
        char character;
        String lexeme;

        //Percorre o arquivo eliminado WhiteSpace
        while (true) {
            try {
                character = fileLoader.getNextChar();

                if (!Character.isWhitespace(character)) {
                    lexeme = Character.toString(character);
                    break;
                }
            }
            catch (EOFException e) {
                return createToken(TokenType.EOF, "EOF");
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        switch (character) {
            case TOKEN_TERMINATOR:
                return createToken(TokenType.TERM, lexeme);

            case TOKEN_RIGHT_PARENTHESIS:
                return createToken(TokenType.R_PAR, lexeme);

            case TOKEN_LEFT_PARENTHESIS:
                return createToken(TokenType.L_PAR, lexeme);

            case TOKEN_ARITHMETIC_PLUS:
            case TOKEN_ARITHMETIC_MINUS:
                return createToken(TokenType.ARIT_AS, lexeme);

            case TOKEN_ARITHMETIC_MULTIPLY:
            case TOKEN_ARITHMETIC_DIVIDE:
                return createToken(TokenType.ARIT_MD, lexeme);

            case TOKEN_ASSIGNMENT:
                return isAssign(fileLoader);

            case TOKEN_RELATIONAL_OPERATION:
                return isRelop(fileLoader);

            case TOKEN_QUOTE:
                return isLiteral(fileLoader);

            default:
                if (Character.isDigit(character)) {
                    return isDigit(fileLoader, character);
                }
                else if (Character.isLetter(character)) {
                    return isLetter(fileLoader, character);
                }

                return null;
        }
    }

    private Token createToken(TokenType tokenType, String lexeme) {
        return new Token(tokenType, lexeme, fileLoader.getColumn(), fileLoader.getLine());
    }

    private Token isRelop(FileLoader fileLoader) {
        return null;
    }

    private Token isDigit(FileLoader fileLoader, char c) {
        return null;
    }

    private Token isLetter(FileLoader fileLoader, char c) {
        return null;
    }

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
            } while (character != TOKEN_QUOTE);

        }
        catch (Exception e){
            e.printStackTrace();
            // retorna erro
        }

        return createToken(TokenType.LITERAL, lexeme.toString());
    }

    private Token isAssign(FileLoader fileLoader) {
        try {
            char character = fileLoader.getNextChar();

            if (character == TOKEN_DASH) {
                return createToken(TokenType.ASSIGN, "<-");
            }
            else {
                // retornar erro
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null; // remover após o erro
    }
}
