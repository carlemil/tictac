package se.kjellstrand.mcts1;

import se.kjellstrand.tictac.GameState;
import se.kjellstrand.tictac.Position;
import se.kjellstrand.tictac.TicTac;

public class MCTS1 {

	private int[][] madeMovesBoard = new int[3][3];
	private int[][] winMovesBoard = new int[3][3];

	public GameState makeNextMove(TicTac tt) {
		long time = System.currentTimeMillis();
		// iterate for x times to find a good move
		while (time + 100 < System.currentTimeMillis()) {
			TicTac ttClone = tt.clone();
			int possibleMoves = ttClone.getNumberOfPossibleMoves();
			int possibleMoveIndex = (int) (Math.random() * possibleMoves);
			Position pos = ttClone.getPosForIndex(possibleMoveIndex);
			madeMovesBoard[pos.x][pos.y]++;
			ttClone.makeMove(possibleMoveIndex);
		}

		// make the best move

		return null;
	}

}
