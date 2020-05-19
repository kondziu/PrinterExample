package cz.cvut.fit.prl.sudoku;

public class Board {
    int[] array = new int[9*9]; // initially zero-filled

    int get1D(int i) {    // FIXME probably unnecessary
        return array[i];
    }

    void set1D(int i, int value) {     // FIXME probably unnecessary
        array[i] = value;
    }

    int get2D(int row, int column) {
        return array[row * 9 + column];
    }

    void set2D(int row, int column, int value) {
        array[row * 9 + column] = value;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (column > 0) {
                    builder.append(" ");
                }
                int value = array[row * 9 + column];
                if (value == 0) {
                    builder.append("_");
                } else {
                    builder.append(value);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public String toCSV() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (column > 0) {
                    builder.append(", ");
                }
                int value = array[row * 9 + column];
                if (value == 0) {
                    builder.append(" ");
                } else {
                    builder.append(value);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
