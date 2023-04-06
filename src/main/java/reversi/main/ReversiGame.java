package reversi.main;

import java.util.Scanner;

public class ReversiGame {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;

    public ReversiGame() {
        this.board = new Board();
        this.players = new Player[2];
        this.players[0] = new Player('B');
        this.players[1] = new Player('W');
        this.currentPlayerIndex = 0;
    }

    public void start() {
        System.out.println("Reversi Game Start!");
        board.print();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = players[currentPlayerIndex];

        System.out.print(currentPlayer.getColor() + " Turn. Input coordinate (ex. a1): ");
        String input = scanner.nextLine();

        if (!isValidInput(input)) {
            System.out.println("Invalid input. Please input coordinate again.");
            play();
            return;
        }

        int x = input.charAt(0) - 'a';
        int y = Integer.parseInt(input.substring(1)) - 1;

        if (!board.isEmpty(x, y)) {
            System.out.println("This coordinate is already occupied. Please input coordinate again.");
            play();
            return;
        }
        
        if (!board.isValidMove(x, y, currentPlayer.getColor())) {
            System.out.println("There are no stones that can be reverse. Please input coordinate again.");
            play();
            return;
        }

        board.putStone(x, y, currentPlayer.getColor());
        board.reverseStones(x, y, currentPlayer.getColor());

        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        board.print();
    }

    public boolean isGameOver() {
        int blackStones = board.countStones('B');
        int whiteStones = board.countStones('W');

        if (blackStones == 0 || whiteStones == 0) {
            return true;
        }

        return false;
    }

    public void printResult() {
        int blackStones = board.countStones('B');
        int whiteStones = board.countStones('W');

        if (blackStones > whiteStones) {
            System.out.println("Black wins! (B: " + blackStones + ", W: " + whiteStones + ")");
        } else if (blackStones < whiteStones) {
            System.out.println("White wins! (B: " + blackStones + ", W: " + whiteStones + ")");
        } else {
            System.out.println("Draw! (B: " + blackStones + ", W: " + whiteStones + ")");
        }
    }

    private boolean isValidInput(String input) {
        if (input.length() != 2) {
            return false;
        }

        int x = input.charAt(0) - 'a';
        int y = Integer.parseInt(input.substring(1)) - 1;

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        return true;
    }
}
