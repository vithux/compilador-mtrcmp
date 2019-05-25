/**
 * @author Matheus Rogge Cocia de Oliveira
 * @author Rafael Bená Cineis
 * @author Vitor Rodrigues de Marques
 * @author Vitor Augusto da Silva Brandão
 */
package compiler;

import error.handler.ErrorHandler;
import symbol.SymbolTable;
import syntactic.SyntacticAnalyser;

import java.io.IOException;

public class Compiler {

    /**
     * Método principal da aplicação que recebe o arquivo a ser compilado e inicia o processo
     * do compilador.
     *
     * @param  args - Recebe o path do arquivo a ser compilado.
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException("File path is required");
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
        System.out.println(SymbolTable.getInstance().getReport());
    }
}
