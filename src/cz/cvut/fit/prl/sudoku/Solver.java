package cz.cvut.fit.prl.sudoku;

public class Solver {

    final Board board;

    public Solver(Board board) {
        this.board = board;
    }

    boolean innerSolve(int i) {
        if (i == 81)
            return true;

        if (board.get1D(i) != 0)
            return innerSolve(i + 1);

        boolean solved = false;
        for (int n = 1; n <= 9; n++) if (!solved) {
            board.set1D(i, n);
            if (Checker.isGood(board)) {
                solved = solved || innerSolve(i + 1);
            }
        }

        if (solved)
            return true;

        board.set1D(i, 0);
        return false;
    }

    public Board solve() {
        if (innerSolve(0)) {
            return board;
        } else {
            return null;
        }
    }
}
