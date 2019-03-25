package compiler;

import handler.ErrorHandler;
import syntactic.SyntacticAnalyser;

import java.io.IOException;

public class Compiler {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Missing file name argument");
            return;
        }

        String fileName = args[0];

        try {
            SyntacticAnalyser syntacticAnalyser = new SyntacticAnalyser(fileName);
            syntacticAnalyser.process();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ErrorHandler.getInstance().errorReport());
    }
}
