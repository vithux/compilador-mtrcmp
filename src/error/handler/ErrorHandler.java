/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package error.handler;

import error.Error;
import utils.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class ErrorHandler extends Report {

    private static ErrorHandler instance;

    private final List<Error> listError = new ArrayList<>();

    @Override
    public String getReportName() {
        return "ERROR HANDLER REPORT";
    }

    @Override
    public String getReportContent() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());

        for (Error error : listError) {
            stringJoiner.add(error.toString());
        }

        return stringJoiner.toString();
    }

    public void addError(Error error) {
        listError.add(error);
    }

    public static ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }

        return instance;
    }
}
