package lexical.parser.numeric;

import automata.FiniteStateMachine;
import exceptions.IllegalTokenException;
import exceptions.NoSuchTransitionException;
import lexical.parser.Parser;
import loader.FileLoader;
import token.builders.EOFTokenBuilder;
import token.Token;
import token.TokenBuilder;

import java.io.EOFException;
import java.io.IOException;

public class NumericParser implements Parser {

    @Override
    public Token parse(FileLoader fileLoader) throws IOException, IllegalTokenException {
        StringBuilder lexeme = new StringBuilder();
        FiniteStateMachine stateMachine = NumberParserStateMachine.getInstance();

        while (true) {
            try {
                char currentChar = fileLoader.getNextChar();

                stateMachine = stateMachine.consumeToken(currentChar);
                lexeme.append(currentChar);
            }
            catch (NoSuchTransitionException e) {
                if (!stateMachine.isFinal()) {
                    throw new IllegalTokenException(e.getToken(), lexeme.toString(), fileLoader.getLine(), fileLoader.getColumn());
                }

                NumericType numericType = NumberParserStateMachine.getNumericTypeFromFinalState(stateMachine);
                TokenBuilder tokenBuilder = NumericTokenBuilderFactory.get(numericType);

                fileLoader.resetLastChar();

                return tokenBuilder
                        .setCursorLocation(fileLoader)
                        .setLexeme(lexeme)
                        .build();
            }
            catch (EOFException e) {
                return new EOFTokenBuilder().withCursorLocation(fileLoader);
            }
        }
    }
}
