package lexical.parser.numeric;

import automata.FiniteStateMachine;
import handler.ErrorHandler;
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
        String errorMessage;

        StringBuilder lexeme = new StringBuilder();
        FiniteStateMachine stateMachine = NumberParserStateMachine.getInstance();

        while (true) {
            try {
                char token = fileLoader.getNextChar();

                stateMachine = stateMachine.consumeToken(token);
                lexeme.append(token);
            }
            catch (IllegalArgumentException e) {
                errorMessage = e.getMessage();
                fileLoader.resetLastChar();
                break;
            }
            catch (EOFException e) {
                errorMessage = "Unexpected end of file";
                fileLoader.resetLastChar();
                break;
            }
        }

        if (!stateMachine.isFinal()) {
            ErrorHandler.getInstance().addError(errorMessage);

            return new TokenBuilder()
                    .setTokenType(TokenType.ERROR)
                    .setCursorLocation(fileLoader)
                    .setLexeme(lexeme)
                    .build();
        }

        NumericType numericType = NumberParserStateMachine.getNumericTypeFromFinalState(stateMachine);
        TokenBuilder tokenBuilder = NumericTokenBuilderFactory.get(numericType);

        return tokenBuilder
                .setCursorLocation(fileLoader)
                .setLexeme(lexeme)
                .build();
    }
}
