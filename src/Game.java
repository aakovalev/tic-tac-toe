import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 3;

    public static final int MIN_COLUMN = 1;
    public static final int MAX_COLUMN = 3;

    List<Move> moves = new ArrayList<Move>();

    public boolean hasWinner() {
        return isAnyColumnFilled() || isAnyRowFilled();
    }

    private boolean rowIsFilled(int row) {
        boolean rowIsFilled = true;
        for (int column = 1; column <= MAX_COLUMN; column++) {
            rowIsFilled = rowIsFilled && moves.contains(new Move(row, column));
        }
        return rowIsFilled;
    }

    private boolean columnIsFilled(int column) {
        boolean columnIsFilled = true;
        for (int row = 1; row <= MAX_ROW; row++) {
            columnIsFilled = columnIsFilled && moves.contains(new Move(row, column));
        }
        return columnIsFilled;
    }

    private boolean isAnyRowFilled() {
        for (int row = 1; row <= MAX_ROW; row++) {
            if (rowIsFilled(row)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyColumnFilled() {
        for (int column = 1; column <= MAX_COLUMN; column++) {
            if (columnIsFilled(column)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOver() {
        return moves().size() == maxMoves() ? true : false;
    }

    public Move makeMove(int row, int column) throws IllegalMoveException {
        Move move = new Move(row, column);
        if (move.canBeMadeIn(this /* game */)) {
            moves.add(move);
            return move;
        }
        throw new IllegalMoveException();
    }

    public List<Move> moves() {
        return Collections.unmodifiableList(moves);
    }

    private int maxMoves() {
        return MAX_ROW * MAX_COLUMN;
    }

}
