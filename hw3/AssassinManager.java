import java.util.*;

public class AssassinManager {
    private AssassinNode killRingFront;
    private AssassinNode graveyardFront;
    
    public AssassinManager(List<String> names) { 
        if (names.isEmpty()) {
            throw new IllegalArgumentException();
        }
        killRingFront = new AssassinNode(names.get(0));
        AssassinNode curr = killRingFront;
        for (int i = 1; i < names.size(); i++) {
            curr.next = new AssassinNode(names.get(i));
            curr = curr.next;
        }
    }

    public void printKillRing() {
        AssassinNode curr = killRingFront;
        while (curr.next != null) {
            System.out.printf("%s is stalking %s\n", curr.name, curr.next.name);
            curr = curr.next;
        }
        System.out.printf("%s is stalking %s\n", curr.name, killRingFront.name);
    }

    public void printGraveyard() {
        AssassinNode curr = graveyardFront;
        while (curr != null) {
            System.out.printf("%s was killed by %s\n", curr.name, curr.killer);
            curr = curr.next;
        }
    }

    public boolean killRingContains(String name) {
        AssassinNode curr = killRingFront;
        while (curr != null) {
            if (curr.name.toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean graveyardContains(String name) {
        AssassinNode curr = graveyardFront;
        while (curr != null) {
            if (curr.name.equalsIgnoreCase(name)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean gameOver() {
        return (killRingFront.next == null) ? true : false;
    }

    public String winner() {
        return (gameOver()) ? killRingFront.name : null;
    }

    public void kill(String name) {
        if (!killRingContains(name)) {
            throw new IllegalArgumentException();
        } else if (gameOver()) {
            throw new IllegalStateException();
        }

        AssassinNode killed = killRingFront;
        AssassinNode assassin = killRingFront;

        // if the given name is the first name in the kill ring
        if (killRingFront.name.equalsIgnoreCase(name)) {
            while (assassin.next != null) {
                assassin = assassin.next;
            }
            killed = killRingFront;
            killRingFront = killRingFront.next;
        // if the given name is not the first name in the kill ring
        } else {
            // loop until given name matches the next name
            while (!assassin.next.name.equalsIgnoreCase(name)) {
                assassin = assassin.next;
            }
            killed = assassin.next;
            // skip the person that just got killed by reassigning
            // the assassin's pointer
            assassin.next = assassin.next.next;
        }
        killed.killer = assassin.name;
        digUpGrave(killed);
        killed.next = null;
    }

    // Add the killed person to the graveyard
    public void digUpGrave(AssassinNode killed) {
        if (graveyardFront == null) {
            graveyardFront = killed;
        } else {
            AssassinNode currG = graveyardFront;
            while (currG.next != null) {
                currG = currG.next;
            }
            currG.next = killed;
        }
        
    }
}