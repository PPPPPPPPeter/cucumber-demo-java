package game;

public class GobangGame {
    private int[][] board;
    private int currentPlayer;
    private boolean gameOver;
    private String winner;
    private static final int EMPTY = 0;
    private static final int BLACK = 1;
    private static final int WHITE = 2;
    private static final int BOARD_SIZE = 15;

    public GobangGame() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = BLACK;
        gameOver = false;
        winner = null;
    }

    public void startNewGame() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = BLACK;
        gameOver = false;
        winner = null;
    }

    public boolean placePiece(int row, int col) {
        if (gameOver) {
            return false;
        }

        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }

        if (board[row][col] != EMPTY) {
            return false;
        }

        board[row][col] = currentPlayer;

        if (checkWin(row, col)) {
            gameOver = true;
            winner = currentPlayer == BLACK ? "Black" : "White";
        }

        currentPlayer = (currentPlayer == BLACK) ? WHITE : BLACK;
        return true;
    }

    private boolean checkWin(int row, int col) {
        int player = board[row][col];

        // Check horizontal
        if (countDirection(row, col, 0, 1, player) + countDirection(row, col, 0, -1, player) >= 4) {
            return true;
        }

        // Check vertical
        if (countDirection(row, col, 1, 0, player) + countDirection(row, col, -1, 0, player) >= 4) {
            return true;
        }

        // Check diagonal \
        if (countDirection(row, col, 1, 1, player) + countDirection(row, col, -1, -1, player) >= 4) {
            return true;
        }

        // Check diagonal /
        if (countDirection(row, col, 1, -1, player) + countDirection(row, col, -1, 1, player) >= 4) {
            return true;
        }

        return false;
    }

    private int countDirection(int row, int col, int dRow, int dCol, int player) {
        int count = 0;
        int r = row + dRow;
        int c = col + dCol;

        while (r >= 0 && r < BOARD_SIZE && c >= 0 && c < BOARD_SIZE && board[r][c] == player) {
            count++;
            r += dRow;
            c += dCol;
        }

        return count;
    }

    public int getPiece(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return -1;
        }
        return board[row][col];
    }

    public String getCurrentPlayer() {
        return currentPlayer == BLACK ? "Black" : "White";
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getWinner() {
        return winner;
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    public boolean isEmptyPosition(int row, int col) {
        return isValidPosition(row, col) && board[row][col] == EMPTY;
    }
}