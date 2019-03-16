package lexical;

import loader.FileLoader;
import token.Token;
import token.TokenType;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LexicalAnalyzer {

    private FileLoader fileLoader;

    public LexicalAnalyzer(String fileName) {
        try {
            this.fileLoader = new FileLoader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found =(");
        }
    }

    public Token nextToken() {
        char c;

        //Percorre o arquivo eliminado WhiteSpace
        while (true) {
            try {
                c = fileLoader.getNextChar();
                if (!Character.isWhitespace(c)) {
                    break;
                }
            } catch (EOFException e) {
                return new Token(TokenType.EOF, "EOF", fileLoader.getColumn(), fileLoader.getLine());
            } catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }

        switch (c) {
            case ';':
                return new Token(TokenType.TERM, Character.toString(c), fileLoader.getColumn(), fileLoader.getLine());
            case ')':
                return new Token(TokenType.R_PAR, Character.toString(c), fileLoader.getColumn(), fileLoader.getLine());
            case '(':
                return new Token(TokenType.L_PAR, Character.toString(c), fileLoader.getColumn(), fileLoader.getLine());
            case '+':
            case '-':
                return new Token(TokenType.ARIT_AS, Character.toString(c), fileLoader.getColumn(), fileLoader.getLine());
            case '*':
            case '/':
                return new Token(TokenType.ARIT_MD, Character.toString(c), fileLoader.getColumn(), fileLoader.getLine());
            case '<':
                return isAssign(fileLoader);
            case '$':
                return isRelop(fileLoader);
            case '"':
                return isLiteral(fileLoader);
            default:
                if (Character.isDigit(c)) {
                    return isDigit(fileLoader, c);
                } else {
                    return isLetter(fileLoader, c);
                }
        }
    }

    public Token isRelop(FileLoader fileLoader) {
        return null;
    }

    public Token isDigit(FileLoader fileLoader, char c) {
        return null;
    }

    public Token isLetter(FileLoader fileLoader, char c) {
        return null;
    }

    public Token isLiteral(FileLoader fileLoader) {
        StringBuilder lexeme = new StringBuilder();
        char c;

        try {
            c = fileLoader.getNextChar();

            while (c != '"') {
                c = fileLoader.getNextChar();
                lexeme.append(c);
            }
        }catch (Exception e){
            e.printStackTrace();
            // retorna erro
        }

        return new Token(TokenType.LITERAL, lexeme.toString(), fileLoader.getColumn(), fileLoader.getLine());
    }

    public Token isAssign(FileLoader fileLoader) {
        try {
            char c = fileLoader.getNextChar();
            if (c == '-') {
                return new Token(TokenType.ASSIGN, "<-", fileLoader.getColumn(), fileLoader.getLine());
            } else {
                // retornar erro
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // remover após o erro
    }
}
