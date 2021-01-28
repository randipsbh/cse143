import java.util.*;

public class Hello {
    public static void main(String[] args) {
        String s = "OP::=    +   |      -    |   *       |   %	 |	  /	 ";
        String[] line = s.trim().split("::=");
        String[] term = line[1].split("[|]");
        String[] test = s.split("[ \t]+");
        ArrayList<String[]> list = new ArrayList<>();
        for (int i = 0; i < term.length; i++) {
            term[i] = term[i].trim();
            list.add(term[i].split("[ \t]+"));
        }
    }
    
}
