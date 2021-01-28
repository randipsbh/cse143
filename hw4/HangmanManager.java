import java.util.*;

public class HangmanManager {
    private TreeSet<String> wordSet;
    private TreeSet<Character> setOfLetters;
    private int guessCount;
    private String currentLetters;

    public HangmanManager(Collection<String> dictionary, int length, int max) {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException();
        }
        guessCount = max;
        setOfLetters = new TreeSet<>();
        wordSet = new TreeSet<>();
        for (String s : dictionary) {
            if (s.length() == length) {
                wordSet.add(s);
            }
        }
        currentLetters = "";
        for (int i = 0; i < length; i++) {
            currentLetters += "- ";
        }
    }

    // returns current set of all words being considered
    public Set<String> words() {
        return wordSet;
    }

    // return number of guesses left
    public int guessesLeft() {
        return guessCount;
    }

    // returns the letters that have been guessed
    public Set<Character> guesses() {
        return setOfLetters;
    }

    // returns letters guessed, example: "-oo-"
    public String pattern() {
        if (wordSet.isEmpty()) {
            throw new IllegalStateException();
        }
        return currentLetters;
    }

    // returns numnber of times the given letter occurs in the word(s) being considered
    public int record(char guess) {
        TreeMap<String, TreeSet<String>> mapOfFamilies = new TreeMap<>();
        // create keys of the map which are different strings patters, example: -e-- | e--e
        for (String word : wordSet) {
            String pattern = generatePattern(guess, word);
            TreeSet<String> set = new TreeSet<>();
            if (!mapOfFamilies.containsKey(word)) {
                mapOfFamilies.put(pattern, set);
            }
            mapOfFamilies.get(pattern).add(word);
        
        }

        // determine pattern
        int max = 0;
        for (String pattern : mapOfFamilies.keySet()) {
            if (mapOfFamilies.get(pattern).size() > max) {
                wordSet.clear();
                wordSet.addAll(mapOfFamilies.get(pattern));
                currentLetters = pattern;
                max = mapOfFamilies.get(pattern).size();
            }
        }

        int occurences = getCount(guess);
        return occurences;
    }

    public int getCount(char guess) {
        int occurences = 0;
        for (int i = 0; i < currentLetters.length(); i++) {
            if (currentLetters.charAt(i) == guess) {
                occurences++;
            }
        }
        setOfLetters.add(guess);
        if (occurences == 0) {
            guessCount--;
        }
        return occurences;
    }

    // forms/updates current pattern being used
    public String generatePattern(char guess, String word) {
        String s = "";
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                s += guess + " ";
            } else {
                s += currentLetters.substring(index, index + 2);
            }
            index += 2;
        }
        return s;
    }

}