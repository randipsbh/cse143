// skeleton version of the class

public class Guitar37 implements Guitar {
   private static final double concertA = 440.0;
   private GuitarString[] piano;
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout

   public Guitar37() {
       piano = new GuitarString[KEYBOARD.length()];
       for (int i = 0; i < KEYBOARD.length(); i++) {
         piano[i] = new GuitarString(concertA * Math.pow(2.0, (i-24.0)/12.0));
      }
   }
    public void playNote(int pitch) {
       int index = pitch + 24;
       if (index >= 0 && index < KEYBOARD.length()) {
         piano[index].pluck();
       }
   }

    public boolean hasString(char string) {
       for (int i = 0; i < KEYBOARD.length(); i++) {
          if (KEYBOARD.charAt(i) == string) {
             return true;
          }
       }
        return false;
    }
    
    public void pluck(char string) {
       if (!hasString(string)) {
          throw new IllegalArgumentException();
       }
       piano[KEYBOARD.indexOf(string)].pluck();
    }

    public double sample() {
      double sum = 0.0;
      for (int i = 0; i < KEYBOARD.length(); i++) {
         sum += piano[i].sample();
      }  
      return sum;
    }

    public void tic() {
      for (int i = 0; i < KEYBOARD.length(); i++) {
         piano[i].tic();
      }
    }

    public int time() {
        return -1;  // not implemented
    }
  
}
