import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicTacToeTest {
    private Game game;
    private Player player;
    private Player secondPlayer;

    @Before
    public void setUp() {
        player = new Player();
        secondPlayer = new Player();
        game = new Game(player, secondPlayer);
    }

    @Test
    public void testThatNewGameIsNotOver() {
        assertFalse(game.isOver());
    }

    @Test
    public void testThatPlayerCanMakeMove() {
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
    public void testThatMakeTheSameMoveTwiceNotPossibleInTheGameEvenForDifferentPlayers() {
        player.makeMove(2, 3);
        secondPlayer.makeMove(2, 3);
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheMoveWhereRowIsLessThanMinRowIsNotPossible() {
        player.makeMove(Game.MIN_ROW - 1, 1);
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheMoveWhereRowIsGreaterThanMaxRowIsNotPossible() {
        player.makeMove(Game.MAX_ROW + 1, 1);
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheMoveWhereColumnIsLessThanMinColumnIsNotPossible() {
        player.makeMove(1, Game.MIN_COLUMN - 1);
    }

    @Test (expected = IllegalMoveException.class)
    public void testThatMakeTheMoveWhereColumnIsGreaterThanMaxColumnIsNotPossible() {
        player.makeMove(1, Game.MAX_COLUMN + 1);
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
        Player firstPlayer = new Player();
        Player secondPlayer = new Player();
        Game game = new Game(firstPlayer, secondPlayer);

        firstPlayer.makeMove(1, columnIndex);
        secondPlayer.makeMove(1, columnIndex % Game.MAX_COLUMN + 1);

        firstPlayer.makeMove(2, columnIndex);
        secondPlayer.makeMove(2, columnIndex % Game.MAX_COLUMN + 1);

        firstPlayer.makeMove(3, columnIndex);
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
        Player firstPlayer = new Player();
        Player secondPlayer = new Player();
        Game game = new Game(firstPlayer, secondPlayer);

        firstPlayer.makeMove(row, 1);
        secondPlayer.makeMove(row % Game.MAX_ROW + 1, 1);

        firstPlayer.makeMove(row, 2);
        secondPlayer.makeMove(row % Game.MAX_ROW + 1, 2);

        firstPlayer.makeMove(row, 3);
        return game;
    }

    @Test
    public void testThatGameIsOverWhenNoPossibleMovesLeft() {
        player.makeMove(1, 1);
        secondPlayer.makeMove(2, 2);

        player.makeMove(3, 1);
        secondPlayer.makeMove(2, 1);

        player.makeMove(2, 3);
        secondPlayer.makeMove(3, 3);

        player.makeMove(1, 3);
        secondPlayer.makeMove(1, 2);

        player.makeMove(3, 2);

        assertTrue(game.isOver());
    }

    @Test
    public void testThatGamePlayersMustNotBeTheSame() {
        assertFalse(player.equals(secondPlayer));
    }

    @Test (expected = PlayerIsNotInTheGameException.class)
    public void testThatPlayerCanNotMakeAnyMoveBeforeAssociatedWithGame() {
        Player newPlayer = new Player();
        newPlayer.makeMove(1, 1);
    }
}