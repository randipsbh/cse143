import java.io.*; // for PrintStream
import java.util.*; // for input

public class QuestionTree {
    //fields
    private Scanner console;
    private QuestionNode root;

    public QuestionTree() {
        root = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    public void read(Scanner input) {
        root = readHelper(input);
    }

    private QuestionNode readHelper(Scanner input) {
        String quesAns = input.nextLine();
        String data = input.nextLine();
        QuestionNode root = new QuestionNode(data);
        if (quesAns.contains("Q:")) {
            root.yes = readHelper(input);
            root.no = readHelper(input);
        }
        return root;
    }

    public void write(PrintStream output) {
        writeHelper(output, root);
    }

    private void writeHelper(PrintStream output, QuestionNode node) {
        // if node is an answer need (leaf node)
        if (node.yes == null && node.no == null) {
            output.println("A:");
            output.println(node.data);
        // if node is a question node
        } else {
            output.println("Q:");
            output.println(node.data);
            writeHelper(output, node.yes);
            writeHelper(output, node.no);
        }
    }

    public void askQuestions() {
        root = askQuestionHelper(root);
    }

    private QuestionNode askQuestionHelper(QuestionNode node) {
        if (node.yes == null && node.no == null) {
            if (yesTo("Would your object happen to be " + node.data)) {
                System.out.println("Great, I got it right!");
            } else {
                System.out.print("What is the name of your object? ");
                QuestionNode answer = new QuestionNode(console.nextLine());
                System.out.println("Please give me a yes/no question that");
                System.out.println("distinguishes between your object");
                System.out.print("and mine--> ");
                String question = console.nextLine();
                if (yesTo("And what is the answer for your object")) {
                    node = new QuestionNode(question, answer, node);
                } else  {
                    node = new QuestionNode(question, node, answer);
                }
            }
        } else {
            if (yesTo(node.data)) {
                node.yes = askQuestionHelper(node.yes);
            } else {
                node.no = askQuestionHelper(node.no);
            }
        }
        return node;
    }

   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
    System.out.print(prompt + " (y/n)? ");
    String response = console.nextLine().trim().toLowerCase();
    while (!response.equals("y") && !response.equals("n")) {
       System.out.println("Please answer y or n.");
       System.out.print(prompt + " (y/n)? ");
       response = console.nextLine().trim().toLowerCase();
    }
    return response.equals("y");
 }
}
