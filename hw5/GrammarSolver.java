import java.util.*;

public class GrammarSolver {
    private SortedMap<String, ArrayList<String[]>> grammarMap;

    // stores contents of list in a sorted map
    public GrammarSolver(List<String> grammar) {
        if (grammar.isEmpty()) {
            throw new IllegalArgumentException();
        }

        grammarMap = new TreeMap<>();
        for (String s : grammar) {
            String[] line = s.trim().split("::=");
            if (grammarMap.containsKey(line[0])) {
                throw new IllegalArgumentException();
            }
            String[] terminals = line[1].split("[|]");
            ArrayList<String[]> rules = new ArrayList<>();
            for (int i = 0; i < terminals.length; i++) {
                rules.add(terminals[i].trim().split("[ \t]+"));
    
            }
            grammarMap.put(line[0], rules);
        }
    }

    // if the given symbol which is a non terminal is in the grammar map
    public boolean grammarContains(String symbol) {
        for (String nonTerm : grammarMap.keySet()) {
            if (symbol.equals(nonTerm)) {
                return true;
            }
        }
        return false;
    }

    public String[] generate(String symbol, int times) {
        if (!grammarContains(symbol) || times < 0) {
            throw new IllegalArgumentException();
        }
        String[] sentence = new String[times];
        for (int i = 0; i < times; i++) {
            sentence[i] = generateHelper(symbol);
        }
        return sentence;
    }

    private String generateHelper(String symbol) {
        Random rand = new Random();
        String ret = "";
        if (!grammarContains(symbol)) {
            return symbol;
        } else {
            ArrayList<String[]> termList = grammarMap.get(symbol);
            int randomIndex = rand.nextInt(termList.size());
            String term[] = termList.get(randomIndex);
            for (int i = 0; i < termList.get(randomIndex).length; i++) {
                ret += generateHelper(term[i]) + " ";

            }
        }
        return ret.trim();
    }

    public String getSymbols() {
        return grammarMap.keySet().toString();
    }


 }