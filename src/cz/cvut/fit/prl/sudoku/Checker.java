package cz.cvut.fit.prl.sudoku;

public class Checker {

    static boolean areRowsGood(final Board board) {
        boolean good = true;

        for (int row = 0; row < 9; row++) {
            CheckList checker = new CheckList(board);

            for(int column = 0; column < 9; column++) {
                checker.mark(board.get2D(row, column));
            }

            good = good && checker.isGood();
        }

        return good;
    }

    static boolean areColumnsGood(final Board board) {
        boolean good = true;

        for(int column = 0; column < 9; column++) {
            CheckList checker = new CheckList(board);

            for (int row = 0; row < 9; row++) {
                checker.mark(board.get2D(row, column));
            }

            good = good && checker.isGood();
        }

        return good;
    }

    static boolean areCellsGood(final Board board) {
        boolean good = true;

        for (int cell = 0; cell < 9; cell++) {

            CheckList checker = new CheckList(board);
            int row = (cell / 3) * 3;
            int column = (cell % 3) * 3;

            for (int r = row; r < row + 3; r++) {
                for (int c = column; c < column + 3; c++) {
                    checker.mark(board.get2D(r, c));
                }
            }

            good = good && checker.isGood();
        }

        return good;
    }

    public static boolean isGood(final Board board) {
        return areRowsGood(board) && areColumnsGood(board) && areCellsGood(board);
    }
}
