import syntactic.SyntacticAnalyser;

public class Compiler {

    public static void main(String[] args) {
        String fileName = args[0];

        if (fileName == null) {
            System.out.println("File name param is required");
            return;
        }

        SyntacticAnalyser syntacticAnalyser = new SyntacticAnalyser(fileName);
        syntacticAnalyser.process();
    }
}
