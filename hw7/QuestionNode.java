public class QuestionNode {
    //fields
    public String data;
    public QuestionNode yes;
    public QuestionNode no;
    
    public QuestionNode(String data, QuestionNode yes, QuestionNode no) {
        this.yes = yes;
        this.no = no;
        this.data = data;
    }

    public QuestionNode(String data) {
        this.yes = null;
        this.no = null;
        this.data = data;
    }
}
