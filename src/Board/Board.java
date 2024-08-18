package Board;
import Cell.Cell;
import Jump.Jump;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    public Cell[][] board;

    public Board(int boardSize, int numberOfSnakes, int numberOfLadders) {
         this.initializeBoard(boardSize);
         this.addLadders(numberOfLadders);
         this.addSnakes(numberOfSnakes);
    }

    public void initializeBoard(int boardSize) {
        board = new Cell[boardSize][boardSize];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Cell newCell = new Cell();
                board[row][col] = newCell;
            }
        }
    }

    public void addSnakes(int numberOfSnakes) {

        while(numberOfSnakes > 0) {
            int snakeHead = ThreadLocalRandom.current().nextInt(1,this.board.length);
            int snakeTail = ThreadLocalRandom.current().nextInt(1,this.board.length);

            if(snakeTail >= snakeHead) {
                continue;
            }

            Jump jump = new Jump();
            jump.end = snakeTail;
            jump.start = snakeHead;

            Cell cell = this.getCell(snakeHead);
            cell.jump = jump;

            numberOfSnakes--;
        }
    }

    public void addLadders(int numberOfLadders) {
        while(numberOfLadders > 0) {
            int ladderHead = ThreadLocalRandom.current().nextInt(1,this.board.length);
            int ladderTail = ThreadLocalRandom.current().nextInt(1,this.board.length);
            if(ladderTail <= ladderHead) {
                continue;
            }

            Jump jump = new Jump();
            jump.end = ladderTail;
            jump.start = ladderHead;
            Cell cell = this.getCell(ladderHead);
            cell.jump = jump;

            numberOfLadders--;
        }
    }

    public Cell getCell(int position) {
        int row = position / board.length;
        int col = position % board.length;
        if(row >= board.length || col >= board[row].length) {
            return null;
        }
        return board[row][col];
    }
}
