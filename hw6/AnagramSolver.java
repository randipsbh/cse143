import java.lang.reflect.Array;
import java.util.*;

public class AnagramSolver {
    private List<String> dictionary;
    private Map<String, LetterInventory> mapInventory;

    public AnagramSolver(List<String> list) {
        createMap(list);
        dictionary = list;
    }

    public void print(String s, int max) {
        LetterInventory inventory = new LetterInventory(s);
        ArrayList<String> anagramList = new ArrayList<>();
        ArrayList<String> newList = optimize(inventory);
        printAnagrams(inventory, max, anagramList, newList);
    }

    private ArrayList<String> optimize(LetterInventory inventory) {
        ArrayList<String> newList = new ArrayList<>();
        for (String word : dictionary) {
            if (inventory.subtract(mapInventory.get(word)) != null) {
                newList.add(word);
            }
        }
        return newList;
    }
    private void createMap(List<String> list) {
        mapInventory = new HashMap<>();
        for (String word : list) {
            mapInventory.put(word, new LetterInventory(word));
        }
    }


    private void printAnagrams(LetterInventory inventory, int max, ArrayList<String> anagramList, ArrayList<String> newList) {
        if (inventory.isEmpty()) {
            System.out.println(anagramList);
        } 
        if (max == 0 || max != anagramList.size()) {
            for (String word : newList) {
                LetterInventory newInvent = inventory.subtract(mapInventory.get(word));
                if (newInvent != null) {
                    anagramList.add(word);
                    printAnagrams(newInvent, max, anagramList, newList);
                    anagramList.remove(word);
                }
            }
        }
    }
}
