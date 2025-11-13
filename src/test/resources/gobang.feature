Feature: Gobang Game

  As a player
  I want to play Gobang game
  So that I can enjoy the game and win by placing five pieces in a row

  Background:
    Given a new Gobang game is started
    And the game board is empty

  Scenario: Start a new game
    Then current player should be "Black"
    And the game should not be over

  Scenario: Black player makes first move
    When Black player places a piece at position (7, 7)
    Then the piece should be placed successfully
    And the position (7, 7) should have a "Black" piece
    And current player should be "White"

  Scenario: Players alternate turns
    When Black player places a piece at position (7, 7)
    And White player places a piece at position (7, 8)
    Then current player should be "Black"
    And the position (7, 7) should have a "Black" piece
    And the position (7, 8) should have a "White" piece

  Scenario: Cannot place piece on occupied position
    When Black player places a piece at position (5, 5)
    And player tries to place a piece at position (5, 5)
    Then the move should be rejected
    And the position (5, 5) should have a "Black" piece

  Scenario: Cannot place piece outside board
    When player tries to place a piece at position (-1, 5)
    Then the move should be rejected
    When player tries to place a piece at position (5, 20)
    Then the move should be rejected

  Scenario: Black wins with horizontal five in a row
    When Black completes five in a row horizontally at row 7
    Then the game should be over
    And "Black" should be the winner

  Scenario: White wins with vertical five in a row
    When White completes five in a row vertically at column 7
    Then the game should be over
    And "White" should be the winner

  Scenario: Black wins with diagonal five in a row
    When Black completes five in a row diagonally from (5, 5)
    Then the game should be over
    And "Black" should be the winner

  Scenario: Complex game with multiple moves
    When Black player places a piece at position (7, 7)
    And White player places a piece at position (7, 8)
    And Black player places a piece at position (8, 7)
    And White player places a piece at position (8, 8)
    And Black player places a piece at position (9, 7)
    And White player places a piece at position (9, 8)
    Then the game should not be over
    And current player should be "Black"

  Scenario: Cannot play after game is over
    When Black completes five in a row horizontally at row 5
    And player tries to place a piece at position (10, 10)
    Then the move should be rejected
    And the game should be over
