package java.tests;

import puzzles.BallSortLayout;
import puzzles.Board;
import search.BestFirst;

import java.util.Iterator;

public class TestRunner {
    public static void main(String[] args) {
        int failed = 0;
        try {
            // BallSort tests
            BallSortLayout bl = new BallSortLayout("A A\nA A\n");
            if (!bl.isGoal(null)) {
                System.out.println("BallSort isGoal failed");
                failed++;
            }
            BallSortLayout bl2 = bl.clone();
            if (!bl.equals(bl2)) {
                System.out.println("BallSort clone/equals failed");
                failed++;
            }

            // Board tests
            Board b = new Board("123456780");
            Board b2 = b.clone();
            if (!b.equals(b2)) {
                System.out.println("Board clone/equals failed");
                failed++;
            }
            if (b.children().size() == 0) {
                System.out.println("Board children empty");
                failed++;
            }

            // BestFirst tests
            BestFirst s = new BestFirst();
            Iterator<BestFirst.State> it = s.solve(b, b);
            if (it == null) {
                System.out.println("BestFirst solve returned null");
                failed++;
            } else {
                int count = 0;
                double g = -1;
                while (it.hasNext()) {
                    BestFirst.State st = it.next();
                    count++;
                    g = st.getG();
                }
                if (count != 1) {
                    System.out.println("BestFirst path length != 1: " + count);
                    failed++;
                }
                if ((int) g != 0) {
                    System.out.println("BestFirst g != 0: " + g);
                    failed++;
                }
            }

        } catch (Exception e) {
            System.out.println("Exception during tests:");
            e.printStackTrace();
            failed++;
        }

        if (failed == 0) System.out.println("ALL TESTS PASSED");
        else System.out.println(failed + " TESTS FAILED");
        System.exit(failed);
    }
}
