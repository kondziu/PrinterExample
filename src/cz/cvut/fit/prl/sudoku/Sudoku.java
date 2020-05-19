package cz.cvut.fit.prl.sudoku;

import com.printer.SPrinter;

import java.io.IOException;

public class Sudoku {

    public static void main(String[] args) throws IOException, InterruptedException {
        Board[] boards = preparePuzzles();
        SPrinter printer = new SPrinter();

        for (int i = 0; i < 200; i++) {
            long start = System.currentTimeMillis();
            long timeAfter = System.currentTimeMillis();

            Board board = boards[i % 3];

            System.out.println("=== Given Puzzle ===");
            System.out.println(board);

            /*
             * TODO wishlist:
             *   - send the board to a section called "puzzle" which should be visible at the same time as the other
             *     sections specified below
             *   - have the board be displayed as a matrix
             *   - should append to the previous boards, but show the last n boards alongside their solutions
             */
            printer.print(board.toCSV(), "string");
            //printer.print("puzzle", board.toCSV(), "csv");

            Solver solver = new Solver(board);
            Board solved = solver.solve();
            Thread.sleep(1000);  // A spacer to see results changing.

            System.out.println("=== Solution ===");
            System.out.println(solved);

            /*
             * TODO wishlist:
             *   - send the board to a section called "solution" which should be visible at the same time as the other
             *     sections specified
             *   - have the board be displayed as a matrix
             *   - should append to the previous boards, but show the last n solutions along side their boards
             */
            printer.print(solved.toCSV(), "string");
            //printer.print("solution", solved.toCSV(), "csv");

            long stop = System.currentTimeMillis();

            /*
             * TODO wishlist:
             *   - send the board to two sections simultaneously called "timing" and "log" which should be visible at
             *     the same time as the other sections specified above
             *   - have the data sent to timing as a graph
             *   - should append to previous timings/logs
             *   - have the data sent to log display a log of all results in some pretty format, eg.:
             *
             *        puzzle      timing      unit
             *             0         352        ms
             *             1         133        ms
             *             2         297        ms
             *             3         381        ms
             *
             */
            System.out.println("Puzzle " + i + " solved in " + (stop - start) + "ms");
            String json = "{\"puzzle\": " + i + ", \"timing\": " + (stop - start) + ", \"unit\": \"ms\"}";
            printer.print(json, "string");
            //printer.print(new String[]{"log", "timing"}, json, "json");
        }
    }

    private static Board[] preparePuzzles() {
        Board[] board = { new Board(), new Board(), new Board() };

        board[0].set2D(0,0,8);
        board[0].set2D(0,1,0);
        board[0].set2D(0,2,0);
        board[0].set2D(0,3,1);
        board[0].set2D(0,4,0);
        board[0].set2D(0,5,3);
        board[0].set2D(0,6,4);
        board[0].set2D(0,7,0);
        board[0].set2D(0,8,0);

        board[0].set2D(1,0,0);
        board[0].set2D(1,1,3);
        board[0].set2D(1,2,5);
        board[0].set2D(1,3,7);
        board[0].set2D(1,4,8);
        board[0].set2D(1,5,0);
        board[0].set2D(1,6,0);
        board[0].set2D(1,7,6);
        board[0].set2D(1,8,2);

        board[0].set2D(2,0,4);
        board[0].set2D(2,1,7);
        board[0].set2D(2,2,0);
        board[0].set2D(2,3,0);
        board[0].set2D(2,4,0);
        board[0].set2D(2,5,6);
        board[0].set2D(2,6,0);
        board[0].set2D(2,7,9);
        board[0].set2D(2,8,0);

        board[0].set2D(3,0,0);
        board[0].set2D(3,1,0);
        board[0].set2D(3,2,0);
        board[0].set2D(3,3,0);
        board[0].set2D(3,4,0);
        board[0].set2D(3,5,0);
        board[0].set2D(3,6,0);
        board[0].set2D(3,7,2);
        board[0].set2D(3,8,4);

        board[0].set2D(4,0,0);
        board[0].set2D(4,1,1);
        board[0].set2D(4,2,0);
        board[0].set2D(4,3,3);
        board[0].set2D(4,4,0);
        board[0].set2D(4,5,5);
        board[0].set2D(4,6,0);
        board[0].set2D(4,7,8);
        board[0].set2D(4,8,0);

        board[0].set2D(5,0,2);
        board[0].set2D(5,1,8);
        board[0].set2D(5,2,0);
        board[0].set2D(5,3,0);
        board[0].set2D(5,4,0);
        board[0].set2D(5,5,0);
        board[0].set2D(5,6,0);
        board[0].set2D(5,7,0);
        board[0].set2D(5,8,0);

        board[0].set2D(6,0,0);
        board[0].set2D(6,1,2);
        board[0].set2D(6,2,0);
        board[0].set2D(6,3,6);
        board[0].set2D(6,4,0);
        board[0].set2D(6,5,0);
        board[0].set2D(6,6,0);
        board[0].set2D(6,7,3);
        board[0].set2D(6,8,9);

        board[0].set2D(7,0,1);
        board[0].set2D(7,1,9);
        board[0].set2D(7,2,0);
        board[0].set2D(7,3,0);
        board[0].set2D(7,4,7);
        board[0].set2D(7,5,2);
        board[0].set2D(7,6,6);
        board[0].set2D(7,7,4);
        board[0].set2D(7,8,0);

        board[0].set2D(8,0,0);
        board[0].set2D(8,1,0);
        board[0].set2D(8,2,8);
        board[0].set2D(8,3,5);
        board[0].set2D(8,4,0);
        board[0].set2D(8,5,9);
        board[0].set2D(8,6,0);
        board[0].set2D(8,7,0);
        board[0].set2D(8,8,1);

        board[1].set2D(0,0,0);
        board[1].set2D(0,1,0);
        board[1].set2D(0,2,1);
        board[1].set2D(0,3,0);
        board[1].set2D(0,4,0);
        board[1].set2D(0,5,6);
        board[1].set2D(0,6,4);
        board[1].set2D(0,7,3);
        board[1].set2D(0,8,5);

        board[1].set2D(1,0,0);
        board[1].set2D(1,1,0);
        board[1].set2D(1,2,0);
        board[1].set2D(1,3,0);
        board[1].set2D(1,4,0);
        board[1].set2D(1,5,1);
        board[1].set2D(1,6,0);
        board[1].set2D(1,7,0);
        board[1].set2D(1,8,0);

        board[1].set2D(2,0,0);
        board[1].set2D(2,1,5);
        board[1].set2D(2,2,0);
        board[1].set2D(2,3,4);
        board[1].set2D(2,4,7);
        board[1].set2D(2,5,0);
        board[1].set2D(2,6,9);
        board[1].set2D(2,7,8);
        board[1].set2D(2,8,0);

        board[1].set2D(3,0,0);
        board[1].set2D(3,1,0);
        board[1].set2D(3,2,2);
        board[1].set2D(3,3,0);
        board[1].set2D(3,4,8);
        board[1].set2D(3,5,0);
        board[1].set2D(3,6,7);
        board[1].set2D(3,7,0);
        board[1].set2D(3,8,9);

        board[1].set2D(4,0,0);
        board[1].set2D(4,1,0);
        board[1].set2D(4,2,8);
        board[1].set2D(4,3,7);
        board[1].set2D(4,4,0);
        board[1].set2D(4,5,0);
        board[1].set2D(4,6,6);
        board[1].set2D(4,7,1);
        board[1].set2D(4,8,2);

        board[1].set2D(5,0,0);
        board[1].set2D(5,1,6);
        board[1].set2D(5,2,4);
        board[1].set2D(5,3,0);
        board[1].set2D(5,4,1);
        board[1].set2D(5,5,0);
        board[1].set2D(5,6,0);
        board[1].set2D(5,7,5);
        board[1].set2D(5,8,0);

        board[1].set2D(6,0,9);
        board[1].set2D(6,1,1);
        board[1].set2D(6,2,0);
        board[1].set2D(6,3,3);
        board[1].set2D(6,4,4);
        board[1].set2D(6,5,2);
        board[1].set2D(6,6,8);
        board[1].set2D(6,7,0);
        board[1].set2D(6,8,7);

        board[1].set2D(7,0,0);
        board[1].set2D(7,1,2);
        board[1].set2D(7,2,7);
        board[1].set2D(7,3,0);
        board[1].set2D(7,4,0);
        board[1].set2D(7,5,0);
        board[1].set2D(7,6,0);
        board[1].set2D(7,7,9);
        board[1].set2D(7,8,0);

        board[1].set2D(8,0,6);
        board[1].set2D(8,1,0);
        board[1].set2D(8,2,3);
        board[1].set2D(8,3,0);
        board[1].set2D(8,4,9);
        board[1].set2D(8,5,0);
        board[1].set2D(8,6,5);
        board[1].set2D(8,7,0);
        board[1].set2D(8,8,0);

        board[2].set2D(0,0,0);
        board[2].set2D(0,1,5);
        board[2].set2D(0,2,1);
        board[2].set2D(0,3,2);
        board[2].set2D(0,4,7);
        board[2].set2D(0,5,0);
        board[2].set2D(0,6,0);
        board[2].set2D(0,7,0);
        board[2].set2D(0,8,4);

        board[2].set2D(1,0,0);
        board[2].set2D(1,1,0);
        board[2].set2D(1,2,0);
        board[2].set2D(1,3,0);
        board[2].set2D(1,4,0);
        board[2].set2D(1,5,3);
        board[2].set2D(1,6,0);
        board[2].set2D(1,7,0);
        board[2].set2D(1,8,5);

        board[2].set2D(2,0,0);
        board[2].set2D(2,1,4);
        board[2].set2D(2,2,0);
        board[2].set2D(2,3,0);
        board[2].set2D(2,4,0);
        board[2].set2D(2,5,0);
        board[2].set2D(2,6,0);
        board[2].set2D(2,7,8);
        board[2].set2D(2,8,0);

        board[2].set2D(3,0,0);
        board[2].set2D(3,1,0);
        board[2].set2D(3,2,0);
        board[2].set2D(3,3,0);
        board[2].set2D(3,4,8);
        board[2].set2D(3,5,0);
        board[2].set2D(3,6,0);
        board[2].set2D(3,7,1);
        board[2].set2D(3,8,6);

        board[2].set2D(4,0,0);
        board[2].set2D(4,1,0);
        board[2].set2D(4,2,2);
        board[2].set2D(4,3,5);
        board[2].set2D(4,4,0);
        board[2].set2D(4,5,4);
        board[2].set2D(4,6,7);
        board[2].set2D(4,7,0);
        board[2].set2D(4,8,0);

        board[2].set2D(5,0,0);
        board[2].set2D(5,1,0);
        board[2].set2D(5,2,0);
        board[2].set2D(5,3,0);
        board[2].set2D(5,4,8);
        board[2].set2D(5,5,0);
        board[2].set2D(5,6,0);
        board[2].set2D(5,7,1);
        board[2].set2D(5,8,6);

        board[2].set2D(6,0,0);
        board[2].set2D(6,1,0);
        board[2].set2D(6,2,2);
        board[2].set2D(6,3,5);
        board[2].set2D(6,4,0);
        board[2].set2D(6,5,4);
        board[2].set2D(6,6,7);
        board[2].set2D(6,7,0);
        board[2].set2D(6,8,0);

        board[2].set2D(7,0,0);
        board[2].set2D(7,1,0);
        board[2].set2D(7,2,0);
        board[2].set2D(7,3,0);
        board[2].set2D(7,4,0);
        board[2].set2D(7,5,9);
        board[2].set2D(7,6,0);
        board[2].set2D(7,7,5);
        board[2].set2D(7,8,0);

        board[2].set2D(8,0,8);
        board[2].set2D(8,1,0);
        board[2].set2D(8,2,5);
        board[2].set2D(8,3,3);
        board[2].set2D(8,4,6);
        board[2].set2D(8,5,0);
        board[2].set2D(8,6,4);
        board[2].set2D(8,7,2);
        board[2].set2D(8,8,0);

        return board;
    }
}
