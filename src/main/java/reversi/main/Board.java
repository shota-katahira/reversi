package reversi.main;

public class Board {
    private char[][] board;

    public Board() {
        this.board = new char[8][8];
        init();
    }

    public void init() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '-';
            }
        }

        board[3][3] = 'W';
        board[4][4] = 'W';
        board[3][4] = 'B';
        board[4][3] = 'B';
    }

    public void print() {
        System.out.println("  a b c d e f g h");

        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < 8; j++) {
                System.out.print(board[j][i] + " ");
            }

            System.out.println((i + 1));
        }

        System.out.println("  a b c d e f g h");
    }

    public boolean isEmpty(int x, int y) {
        if (board[x][y] == '-') {
            return true;
        }

        return false;
    }

    public boolean isValidMove(int x, int y, char color) {
//        // すでに石が置かれている場合は不正な手
//        if (!isEmpty(x, y)) {
//            return false;
//        }

        // 隣接するますに相手の石が置かれている場合、その方向に相手の石がある限り石を裏返せる
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int dx = i;
                int dy = j;
                boolean foundOpponent = false;

                while (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8 && board[x + dx][y + dy] != '-') {
                    if (board[x + dx][y + dy] == getOpponentColor(color)) {
                        foundOpponent = true;
                    } else if (foundOpponent && board[x + dx][y + dy] == color) {
                        return true;
                    } else {
                        break;
                    }
                    dx += i;
                    dy += j;
                }
            }
        }

        return false;
    }

    // 相手の色を取得するユーティリティメソッド
    private char getOpponentColor(char color) {
        return color == 'B' ? 'W' : 'B';
    }
    
    public void putStone(int x, int y, char color) {
        board[x][y] = color;
    }

    public void reverseStones(int x, int y, char color) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int dx = i;
                int dy = j;

                while (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8 && board[x + dx][y + dy] != '-' && board[x + dx][y + dy] != color) {
                    dx += i;
                    dy += j;
                }

                if (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8 && board[x + dx][y + dy] == color) {
                    dx = i;
                    dy = j;

                    while (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8 && board[x + dx][y + dy] != '-' && board[x + dx][y + dy] != color) {
                        board[x + dx][y + dy] = color;
                        dx += i;
                        dy += j;
                    }
                }
            }
        }
    }

    public int countStones(char color) {
        int count = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == color) {
                    count++;
                }
            }
        }

        return count;
    }
}
