import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final List<Move> moves = new ArrayList<Move>();
    private Game game;

    public Move makeMove(int row, int column) throws IllegalMoveException {
        final Move move = new Move(row, column);

        if (game == null) {
            throw new PlayerIsNotInTheGameException();
        }

        if (move.canBeMadeIn(game)) {
            moves.add(move);
            return move;
        }

        throw new IllegalMoveException();
    }

    public List<Move> moves() {
        return Collections.unmodifiableList(moves);
    }

    void associateWithGame(Game game) {
        this.game = game;
    }
}
