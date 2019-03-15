package lexical;

import loader.FileLoader;
import token.Token;
import token.TokenType;

import java.io.File;
import java.io.FileNotFoundException;

public class LexicalAnalyzer {

    private FileLoader fileLoader;

    public LexicalAnalyzer(File file) {
        try {
            this.fileLoader = new FileLoader(file);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found =(");
        }
    }

    public Token nextToken() {
        char c = ' ';

        //Percorre o arquivo eliminado WhiteSpace
        while (true) {
            try {
                c = fileLoader.getNextChar();
                if (!Character.isWhitespace(c)) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        switch (c) {
            case ';':
                return new Token(TokenType.TERM, ";", fileLoader.getColumn(), fileLoader.getLine());
            case ')':
                return new Token(TokenType.R_PAR, ")", fileLoader.getColumn(), fileLoader.getLine());
            case '(':
                return new Token(TokenType.L_PAR, "(", fileLoader.getColumn(), fileLoader.getLine());
            case '+':
            case '-':
                return new Token(TokenType.ARIT_AS, Character.toString(c), fileLoader.getColumn(), fileLoader.getLine());
            case '*':
            case '/':
                return new Token(TokenType.ARIT_MD, Character.toString(c), fileLoader.getColumn(), fileLoader.getLine());
            case '<':
                return isAssign(fileLoader);

        }


        return new Token(null, null, 0, 0);
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
