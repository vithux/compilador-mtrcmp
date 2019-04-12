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
     * @param  args - Recebe o path do aquivo a ser compilado.
     */
    public static void main(String[] args) {
        /*string define o nome do arquivo processado pelo compilador,
          no nosso caso o exemple_input já possui um arquivo de teste com
          as variações de tokens e alguns erros já tratados pelo sistema */
        String fileName = "example_input";

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
