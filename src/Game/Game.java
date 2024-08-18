package Game;

import Board.Board;
import Dice.Dice;
import Player.Player;
import Cell.Cell;
import java.util.ArrayDeque;
import java.util.Deque;

public class Game {
    Board board;
    Dice dice;
    Deque<Player> players;
    Player winner;

    public Game() {
        initializeGame();
    }

    public void initializeGame() {
        board = new Board(10,6,6);
        players = new ArrayDeque<Player>();
        players.add(new Player("Ishaan",0));
        players.add(new Player("Sudha",0));

        dice = new Dice(1,6);
        winner = null;
    }

    public void playGame() {

        while (winner == null) {
            Player currentPlayer = players.removeFirst();


            int value = dice.rollDice();

            int tempPosition = currentPlayer.playerPosition;

            System.out.println("Position of "+currentPlayer.playerName+ " is "+ tempPosition+" and dice is "+ value);

            if (board.getCell(tempPosition + value) != null) {
                tempPosition = tempPosition + value;
                Cell cell = board.getCell(tempPosition);
                if(cell.jump != null && cell.jump.start == tempPosition) {
                    String jumpBy = (cell.jump.start < cell.jump.end)? "ladder" : "snake";
                    System.out.println("jump done by: " + jumpBy);
                    currentPlayer.playerPosition = cell.jump.end;
                }
                else {
                    currentPlayer.playerPosition = tempPosition;
                }

               if(currentPlayer.playerPosition == board.board.length * board.board.length-1){
                   winner = currentPlayer;
               }
            }
            players.addLast(currentPlayer);
        }

        System.out.println("Winner is " + winner.playerName);

    }
}
