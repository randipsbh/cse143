import java.util.Arrays;

public class LetterInventory {
    private int[] inventory;
    private int size;

    // constructs a count of the letters in the input string
    public LetterInventory(String data) {
        inventory = new int[26];
        for (int i = 0; i < data.length(); i++) {
            char c = Character.toLowerCase(data.charAt(i));
            if (c >= 'a' && c <= 'z') {
                int n = c - 'a';
                inventory[n]++;
                size++;
            }
        }
    }

    // returns the tally of the input letter from the inventory
    public int get(char letter) {
        return inventory[Character.toLowerCase(letter) - 'a'];
    }

    // sets the count of the input letter to the value in the inventory
    public void set(char letter, int value) {
        if (value == 0) {
            size -= get(letter);
        } else {
            size += value - get(letter);
        }
        inventory[Character.toLowerCase(letter) - 'a'] = value;
    }

    // returns size of the inventory
    public int size() {
        return size;
    }

    // returns whether inventory is empty
    public boolean isEmpty() {
        return (size == 0) ? true : false;
    }

    // returns a string of the letters in the inventory, sorted
    public String toString() {
        String s = "[";
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i]; j++) {
                s += (char) ('a' + i);
            }
        }
        return s + "]";
    }

    // returns LetterInventory object that combines the sum of 2 inventories
    public LetterInventory add(LetterInventory other) {
        return new LetterInventory(this.toString() + other.toString());
    }

    // returns LetterInventory object that combines the difference of 2 inventories
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory inventorySub = new LetterInventory("");
        for (int i = 0; i < inventory.length; i++) {
            int value = inventory[i] - other.get((char) ('a' + i));
            if (value < 0) {
                return null;
            }
            inventorySub.set((char) ('a' + i), value);

        }
        return inventorySub;
    }
}
