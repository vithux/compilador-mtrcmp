import syntactic.SyntacticalAnalyzer;

public class Compiler {

    public static void main(String[] args) {
        String fileName = args[0];

        if (fileName == null) {
            System.out.println("File name param is required");
            return;
        }

        SyntacticalAnalyzer syntacticalAnalyzer = new SyntacticalAnalyzer(fileName);
        syntacticalAnalyzer.process();
    }
}
