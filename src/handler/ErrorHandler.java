package handler;

import java.util.ArrayList;
import java.util.List;

public final class ErrorHandler {
    private static ErrorHandler instance;
    private List<String> listError;

    private ErrorHandler(){
        listError = new ArrayList<>();
    }

    public void addError(String err){
        listError.add(err);
    }

    public List<String> errorReport(){
        return listError;
    }

    public static ErrorHandler getInstance(){
        if(instance == null){
            instance = new ErrorHandler();
        }
        return instance;
    }
}
