import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicTacToeTest {
    private Game game;
    private Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = new Player();
    }

    @Test
    public void testThatNewGameIsNotOver() {
        assertFalse(game.isOver());
    }

    @Test
    public void testThatPlayerCanMakeMove() {
        Player player = new Player();
        Move move = player.makeMove(1, 1);
        assertTrue("Move was made", player.moves().contains(move));
    }

    @Test
    public void testThatMoveWasNotMade() {
        Move move = new Move(1, 2);
        assertFalse("Move was not made", player.moves().contains(move));
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheSameMoveTwiceNotPossible() {
        player.makeMove(1, 1);
        player.makeMove(1, 1);
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheMoveWhereRowIsLessThanMinRowIsNotPossible() {
        game.makeMove(Game.MIN_ROW - 1, 1);
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheMoveWhereRowIsGreaterThanMaxRowIsNotPossible() {
        game.makeMove(Game.MAX_ROW + 1, 1);
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheMoveWhereColumnIsLessThanMinColumnIsNotPossible() {
        game.makeMove(1, Game.MIN_COLUMN - 1);
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheMoveWhereColumnIsGreaterThanMaxColumnIsNotPossible() {
        game.makeMove(1, Game.MAX_COLUMN + 1);
    }

    @Test
    public void testThatNoWinnerWhenGameStarts() {
        assertFalse(game.hasWinner());
    }

    @Test
    public void testThatThereIsWinnerInGameOncePlayerFillsWholeColumnWithWhatever() {
        for (int columnIndex = 1; columnIndex <= Game.MAX_COLUMN; columnIndex++ ) {
            game = setUpGameStateWhenOnePlayerFilledWholeColumn(columnIndex);
            assertTrue(game.hasWinner());
        }
    }

    private Game setUpGameStateWhenOnePlayerFilledWholeColumn(int columnIndex) {
        Game game = new Game();
        game.makeMove(1, columnIndex);
        game.makeMove(1, columnIndex % Game.MAX_COLUMN + 1);

        game.makeMove(2, columnIndex);
        game.makeMove(2, columnIndex % Game.MAX_COLUMN + 1);

        game.makeMove(3, columnIndex);
        return game;
    }

    @Test
    public void testThatThereIsWinnerInGameOncePlayerFillsWholeRowWithWhatever() {
        for (int rowIndex = 1; rowIndex <= Game.MAX_ROW; rowIndex++) {
            game = setUpGameStateWhenOnePlayerFilledWholeRow(rowIndex);
            assertTrue(game.hasWinner());
        }
    }

    private Game setUpGameStateWhenOnePlayerFilledWholeRow(int row) {
        Game game = new Game();
        game.makeMove(row, 1);
        game.makeMove(row % Game.MAX_ROW + 1, 1);

        game.makeMove(row, 2);
        game.makeMove(row % Game.MAX_ROW + 1, 2);

        game.makeMove(row, 3);
        return game;
    }

    @Test
    public void testThatGameIsOverWhenNoPossibleMovesLeft() {
        Game game = new Game();
        game.makeMove(1, 1);
        game.makeMove(2, 2);
        game.makeMove(3, 1);
        game.makeMove(2, 1);
        game.makeMove(2, 3);

        game.makeMove(3, 3);
        game.makeMove(1, 3);
        game.makeMove(1, 2);
        game.makeMove(3, 2);
        assertTrue(game.isOver());
    }
}