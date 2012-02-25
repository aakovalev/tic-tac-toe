public class Move {
    private final int row;
    private final int column;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    boolean notYetMadeIn(Game game) {
        return !game.moves().contains(this);
    }

    boolean isWithinGameBoundaries() {
        return row <= Game.MAX_ROW && row >= Game.MIN_ROW &&
               column <= Game.MAX_COLUMN && column >= Game.MIN_COLUMN;
    }

    public boolean canBeMadeIn(Game game) {
        return isWithinGameBoundaries() && notYetMadeIn(game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Move)) {
            return false;
        }

        Move thatMove = (Move) o;
        return thatMove.row == this.row && thatMove.column == this.column;
    }

    @Override
    public int hashCode() {
        int result = 31 + row;
        result = result * 17 + column;
        return result;
    }
}
