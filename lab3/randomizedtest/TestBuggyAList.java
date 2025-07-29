package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> lstNoResizing = new AListNoResizing<>();
        BuggyAList<Integer> lstBuggy = new BuggyAList<>();

        for (int i = 4; i < 7; i++) {
            lstNoResizing.addLast(i);
            lstBuggy.addLast(i);
        }

        assertEquals(lstNoResizing.size(), lstBuggy.size());

        for (int i = 0; i < 3; i++) {
            int lastNoResizing = lstNoResizing.removeLast();
            int lastBuggy = lstBuggy.removeLast();

            assertEquals(lastNoResizing, lastBuggy);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggyL = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
                assertEquals(L.size(), buggyL.size());
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
                assertEquals(L.size(), buggyL.size());
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() == 0) { continue; }
                int val = L.getLast();
                int buggyVal = buggyL.getLast();
                System.out.println("last:" + val);
                assertEquals(val, buggyVal);
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() == 0) { continue; }
                int val = L.removeLast();
                int buggyVal = buggyL.removeLast();
                System.out.println("removeLast: " + val);
                assertEquals(val, buggyVal);
            }
        }
    }
}
