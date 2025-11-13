package stepdefinitions;

import game.GobangGame;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.junit.Assert.*;

public class GobangSteps {
    private GobangGame game;
    private boolean lastMoveResult;
    private String errorMessage;

    @Given("a new Gobang game is started")
    public void aNewGobangGameIsStarted() {
        game = new GobangGame();
        System.out.println("New game started");
    }

    @Given("the game board is empty")
    public void theGameBoardIsEmpty() {
        game.startNewGame();
        for (int row = 0; row < game.getBoardSize(); row++) {
            for (int col = 0; col < game.getBoardSize(); col++) {
                assertEquals(0, game.getPiece(row, col));
            }
        }
        System.out.println("Board is empty");
    }

    @When("Black player places a piece at position \\({int}, {int}\\)")
    public void blackPlayerPlacesAPieceAtPosition(int row, int col) {
        lastMoveResult = game.placePiece(row, col);
        System.out.println("Black placed piece at (" + row + ", " + col + ")");
    }

    @When("White player places a piece at position \\({int}, {int}\\)")
    public void whitePlayerPlacesAPieceAtPosition(int row, int col) {
        lastMoveResult = game.placePiece(row, col);
        System.out.println("White placed piece at (" + row + ", " + col + ")");
    }

    @When("player tries to place a piece at position \\({int}, {int}\\)")
    public void playerTriesToPlaceAPieceAtPosition(int row, int col) {
        lastMoveResult = game.placePiece(row, col);
        if (!lastMoveResult) {
            errorMessage = "Invalid move";
        }
        System.out.println("Player tried to place at (" + row + ", " + col + ")");
    }

    @Then("the piece should be placed successfully")
    public void thePieceShouldBePlacedSuccessfully() {
        assertTrue("Piece should be placed successfully", lastMoveResult);
        System.out.println("Piece placed successfully");
    }

    @Then("the move should be rejected")
    public void theMoveShouldBeRejected() {
        assertFalse("Move should be rejected", lastMoveResult);
        System.out.println("Move was rejected");
    }

    @Then("current player should be {string}")
    public void currentPlayerShouldBe(String player) {
        assertEquals(player, game.getCurrentPlayer());
        System.out.println("Current player is: " + player);
    }

    @Then("the position \\({int}, {int}\\) should have a {string} piece")
    public void thePositionShouldHaveAPiece(int row, int col, String color) {
        int expected = color.equals("Black") ? 1 : 2;
        assertEquals("Position (" + row + ", " + col + ") should have " + color + " piece",
                expected, game.getPiece(row, col));
        System.out.println("Position (" + row + ", " + col + ") has " + color + " piece");
    }

    @Then("the position \\({int}, {int}\\) should be empty")
    public void thePositionShouldBeEmpty(int row, int col) {
        assertEquals("Position should be empty", 0, game.getPiece(row, col));
        System.out.println("Position (" + row + ", " + col + ") is empty");
    }

    @And("the game should not be over")
    public void theGameShouldNotBeOver() {
        assertFalse("Game should not be over", game.isGameOver());
        System.out.println("Game is not over");
    }

    @And("the game should be over")
    public void theGameShouldBeOver() {
        assertTrue("Game should be over", game.isGameOver());
        System.out.println("Game is over");
    }

    @And("{string} should be the winner")
    public void shouldBeTheWinner(String player) {
        assertTrue("Game should be over", game.isGameOver());
        assertEquals(player, game.getWinner());
        System.out.println(player + " is the winner!");
    }

    @Given("Black has pieces at positions:")
    public void blackHasPiecesAtPositions(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.List<Integer>> positions = dataTable.asLists(Integer.class);
        for (java.util.List<Integer> position : positions) {
            game.placePiece(position.get(0), position.get(1));
            if (!game.getCurrentPlayer().equals("Black")) {
                game.placePiece(14, 14); // Dummy move for white
            }
        }
        System.out.println("Black pieces placed at multiple positions");
    }

    @Given("White has pieces at positions:")
    public void whiteHasPiecesAtPositions(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.List<Integer>> positions = dataTable.asLists(Integer.class);
        game.placePiece(0, 0); // Ensure white's turn
        for (java.util.List<Integer> position : positions) {
            game.placePiece(position.get(0), position.get(1));
            if (!game.getCurrentPlayer().equals("White")) {
                game.placePiece(14, 13); // Dummy move for black
            }
        }
        System.out.println("White pieces placed at multiple positions");
    }

    @When("Black completes five in a row horizontally at row {int}")
    public void blackCompletesFiveInARowHorizontallyAtRow(int row) {
        for (int col = 0; col < 5; col++) {
            game.placePiece(row, col);
            if (col < 4) {
                game.placePiece(13, col); // White's dummy move
            }
        }
        System.out.println("Black completed five in a row at row " + row);
    }

    @When("White completes five in a row vertically at column {int}")
    public void whiteCompletesFiveInARowVerticallyAtColumn(int col) {
        game.placePiece(0, 0); // Black's first move
        for (int row = 0; row < 5; row++) {
            game.placePiece(row, col);
            if (row < 4) {
                game.placePiece(13, row); // Black's dummy move
            }
        }
        System.out.println("White completed five in a row at column " + col);
    }

    @When("Black completes five in a row diagonally from \\({int}, {int}\\)")
    public void blackCompletesFiveInARowDiagonallyFrom(int startRow, int startCol) {
        for (int i = 0; i < 5; i++) {
            game.placePiece(startRow + i, startCol + i);
            if (i < 4) {
                game.placePiece(13, i); // White's dummy move
            }
        }
        System.out.println("Black completed five diagonally from (" + startRow + ", " + startCol + ")");
    }
}