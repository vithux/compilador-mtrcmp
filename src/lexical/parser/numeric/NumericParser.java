package lexical.parser.numeric;

import automata.FiniteStateMachine;
import handler.ErrorHandler;
import handler.ErrorType;
import lexical.parser.Parser;
import loader.FileLoader;
import token.Token;
import token.TokenBuilder;
import token.TokenType;

import java.io.EOFException;
import java.io.IOException;

public class NumericParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) throws IOException {
        StringBuilder lexeme = new StringBuilder();
        FiniteStateMachine stateMachine = NumberParserStateMachine.getInstance();

        char currentChar = ' ';

        while (true) {
            try {
                currentChar = fileLoader.getNextChar();

                stateMachine = stateMachine.consumeToken(currentChar);
                lexeme.append(currentChar);
            } catch (IllegalArgumentException e) {
                break;
            } catch (EOFException e) {
                return new TokenBuilder()
                        .setTokenType(TokenType.EOF)
                        .setLexeme("EOF")
                        .setCursorLocation(fileLoader)
                        .build();
            }
        }

        if (!stateMachine.isFinal()) {
            noticeError(lexeme.toString(), currentChar, fileLoader);
            return null;
        }

        NumericType numericType = NumberParserStateMachine.getNumericTypeFromFinalState(stateMachine);
        TokenBuilder tokenBuilder = NumericTokenBuilderFactory.get(numericType);

        fileLoader.resetLastChar();
        return tokenBuilder
                .setCursorLocation(fileLoader)
                .setLexeme(lexeme)
                .build();
    }

    void noticeError(String lexeme, char illegalCharacter, FileLoader fileLoader) {
        ErrorHandler.getInstance()
                .addError(ErrorType.ILLEGAL_CHARACTER, lexeme, illegalCharacter, fileLoader.getLine(), fileLoader.getColumn());
    }
}
