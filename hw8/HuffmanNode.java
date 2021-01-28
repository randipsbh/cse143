public class HuffmanNode implements Comparable<HuffmanNode> {
    public int frequency;
    public int val;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(int frequency, int val) {
        this.frequency = frequency;
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(int frequency, int val, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int compareTo(HuffmanNode otherNode) {
        return this.frequency - otherNode.frequency;
    }
}
