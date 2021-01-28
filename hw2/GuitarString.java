import java.util.*;

public class GuitarString {
    private Queue<Double> ringBuffer;
    private int N;
    public static final double DECAY = 0.996;

    public GuitarString(double frequency) {
        N = (int) Math.round(StdAudio.SAMPLE_RATE/frequency);
        if (frequency <= 0 || N < 2) {
            throw new IllegalArgumentException();
        }
        ringBuffer = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            ringBuffer.add(0.0);
        }
    }

    public GuitarString(double[] init) {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }
        ringBuffer = new LinkedList<>();
        for (int i = 0; i < init.length; i++) {
            ringBuffer.add(init[i]);
        }
    }

    public void pluck() {
        Random rand = new Random();
        for (int i = 0; i < ringBuffer.size(); i++) {
            ringBuffer.remove();
            ringBuffer.add(rand.nextDouble() - 0.5);
        }
    }

    public void tic() {
        double avg = ((ringBuffer.remove() + ringBuffer.peek())/2) * 0.996;
        ringBuffer.add(avg);
    }

    public double sample() {
        return ringBuffer.peek();
    }
}
