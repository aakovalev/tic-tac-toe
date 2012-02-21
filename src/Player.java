import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    List<Move> moves = new ArrayList<Move>();

    public Move makeMove(int row, int column) throws IllegalMoveException {
        final Move move = new Move(row, column);
        if (moves().contains(move)) {
            throw new IllegalMoveException();
        }
        moves.add(move);
        return move;
    }

    public List<Move> moves() {
        return Collections.unmodifiableList(moves);
    }
}