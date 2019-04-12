/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package error.handler;

import error.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class ErrorHandler {

    private static ErrorHandler instance;

    private List<Error> listError;

    private ErrorHandler() {
        listError = new ArrayList<>();
    }

    public void addError(Error error) {
        this.listError.add(error);
    }

    public String errorReport() {
        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("### ERROR HANDLER REPORT ###");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        for (Error error : listError) {
            stringJoiner.add(error.toString());
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
