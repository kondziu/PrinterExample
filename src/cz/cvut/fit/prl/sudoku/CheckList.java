package cz.cvut.fit.prl.sudoku;

public class CheckList {
    int[] checks = new int[10];
    Board board;

    public CheckList(Board board) {
        this.board = board;
    }

    public void mark(int i) {
        checks[i] = checks[i] + 1;
    }

    public boolean isGood() {
        boolean good = true;
        for (int i = 1; i < 10; i++) {
            good = good && checks[i] <= 1;
        }
        return good;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[[ ");
        for (int i = 0; i < 10; i++) {
            builder.append(checks[i]);
            builder.append(" ");
        }
        builder.append("]]");

        return builder.toString();
    }
}
