import java.io.*;
import java.util.*;

public class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree(int[] count) {
        Queue<HuffmanNode> frequencyCounter = new PriorityQueue<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                HuffmanNode huffNode = new HuffmanNode(count[i], i);
                frequencyCounter.add(huffNode);
            }
        }

        // eof havng value of 1 greather than highest value in count
        HuffmanNode eof = new HuffmanNode(1, count.length);
        frequencyCounter.add(eof);
        while (frequencyCounter.size() != 1) {
            HuffmanNode first = frequencyCounter.remove();
            HuffmanNode second = frequencyCounter.remove();
            HuffmanNode sumHuffNode = new HuffmanNode(first.frequency + second.frequency, -1, first, second);
            frequencyCounter.add(sumHuffNode);
        }
        root = frequencyCounter.remove();
    }

    public HuffmanTree(Scanner input) {
        while (input.hasNextLine()) {
            int letterVal = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            root = HuffmanTreeConst(root, letterVal, code);
        }
    }

    public HuffmanNode HuffmanTreeConst(HuffmanNode root, int letterVal, String code) {
        if (root == null) {
            root = new HuffmanNode(0, -1);
        }
        if (code.length() == 1) {
            if (code.charAt(0) == '0') {
                root.left = new HuffmanNode(0, letterVal);
            } else {
                root.right = new HuffmanNode(0, letterVal);
            }
        } else {
            char bit = code.charAt(0);
            code = code.substring(1);
            if (bit == '0') {
                root.left = HuffmanTreeConst(root.left, letterVal, code);
            } else {
                root.right = HuffmanTreeConst(root.right, letterVal, code);
            }
        }
        return root;
    }

    public void write(PrintStream output) {
        if (root != null) {
            writeHelper(output, root, "");
        }
    }

    public void writeHelper(PrintStream output, HuffmanNode root, String code) {
        if (root.left == null && root.right == null) {
            output.println(root.val);
            output.println(code);
         } else {
             writeHelper(output, root.left, code + "0");
             writeHelper(output, root.right, code + "1");
         }
    }

    public void decode(BitInputStream input, PrintStream output, int eof) {
        int bit = input.readBit();
        while (bit != -1) {
            bit = decodeHelper(root, bit, input, output, eof);
        }
    }

    public int decodeHelper(HuffmanNode root, int bit, BitInputStream input, PrintStream output, int eof) {
        if (root.left == null && root.right == null && root.val != eof) {
            output.write(root.val);
            return bit;
        } else if (bit == 0 && root.val != eof) {
            return decodeHelper(root.left, input.readBit(), input, output, eof);
        } else if (root.val != eof) {
            return decodeHelper(root.right, input.readBit(), input, output, eof);
        }

        return -1;
    }
}
