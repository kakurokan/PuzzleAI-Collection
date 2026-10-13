package puzzles;

import core.Ilayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements Ilayout, Cloneable {
    private static final int dim = 3;
    private int[][] board;

    public Board() {
        board = new int[dim][dim];
    }

    public Board(String str) throws IllegalStateException {
        if (str.length() != dim * dim)
            throw new IllegalStateException("Invalid arg in Board constructor");
        board = new int[dim][dim];
        int si = 0;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++) {
                board[i][j] = Character.getNumericValue(str.charAt(si++));
            }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int i : row) {
                if (i != 0)
                    sb.append(i);
                else {
                    sb.append(" ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o instanceof Board board1) {
            return Arrays.deepEquals(board1.board, this.board);
        }
        return false;
    }

    public int hashCode() {
        return Arrays.deepHashCode(this.board);
    }

    public List<Ilayout> children() {
        int blankRow = -1;
        int blankCol = -1;
        List<Ilayout> children = new ArrayList<>();

        loop1:
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break loop1;
                }
            }
        }
        int[][] moves = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        for (int[] move : moves) {
            int newRow = blankRow + move[0];
            int newCol = blankCol + move[1];

            if (validPosition(newRow, newCol)) {
                Board child = this.clone();
                child.board[blankRow][blankCol] = child.board[newRow][newCol];
                child.board[newRow][newCol] = 0;
                children.add(child);
            }
        }

        return children;
    }

    public boolean isGoal(Ilayout l) {
        return this.equals(l);
    }

    public double getK() {
        return 1;
    }

    @Override
    public double getHeuristic(Ilayout goal) {
        return 0;
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            clone.board = new int[dim][dim];

            for (int i = 0; i < dim; i++)
                System.arraycopy(this.board[i], 0, clone.board[i], 0, dim);

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private boolean validPosition(int i, int j) {
        return i >= 0 && i < dim && j >= 0 && j < dim;
    }
}
