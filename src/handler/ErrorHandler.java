package handler;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class ErrorHandler {
    private static ErrorHandler instance;
    private List<String> listError;

    private ErrorHandler() {
        listError = new ArrayList<>();
    }

    public void addError(String err) {
        listError.add(err);
    }

    public void addError(ErrorType errorType, String lexeme, Character character, Long line, Long column) {

        StringBuilder error = new StringBuilder();

        switch (errorType) {
            case UNEXPECTED_TOKEN:
                error.append("Unexpected token: ")
                        .append(character)
                        .append(" at line ")
                        .append(line)
                        .append(", column ")
                        .append(column);
                break;

            case ILLEGAL_CHARACTER:
                error.append("Illegal character ")
                        .append(character)
                        .append(" encountered in: ")
                        .append(lexeme)
                        .append(" at line ")
                        .append(line)
                        .append(", column ")
                        .append(column);
                break;

            case EXPECTED_TOKEN:
                error.append("Expected token ")
                        .append(character)
                        .append(" on lexeme ")
                        .append(lexeme)
                        .append(" at line ")
                        .append(line)
                        .append(", column ")
                        .append(column);
        }

        listError.add(error.toString());
    }

    public String errorReport() {
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("### ERROR HANDLER REPORT ###");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        for (String error : listError) {
            stringJoiner.add(error);
        }

        stringBuilder.append(stringJoiner.toString());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        return stringBuilder.toString();
    }

    public static ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }

        return instance;
    }
}
