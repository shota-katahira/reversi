package reversi.main;

public class Main {
    public static void main(String[] args) {
        ReversiGame game = new ReversiGame();
        game.start();

        while (!game.isGameOver()) {
            game.play();
        }

        game.printResult();
    }
}
