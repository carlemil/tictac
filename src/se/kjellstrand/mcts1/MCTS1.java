package se.kjellstrand.mcts1;

import se.kjellstrand.tictac.GameState;
import se.kjellstrand.tictac.Position;
import se.kjellstrand.tictac.TicTac;

public class MCTS1 {

	private int[][] madeMovesBoard = new int[3][3];
	private int[][] winMovesBoard = new int[3][3];

	public GameState makeNextMove(TicTac tt) {
		GameState gs = null;
		int possibleMoveIndex = -1;
		long time = System.currentTimeMillis();
		while (time + 100 > System.currentTimeMillis()) {
			TicTac ttClone = tt.clone();
			int possibleMoves = ttClone.getNumberOfPossibleMoves();
			possibleMoveIndex = (int) (Math.random() * possibleMoves);
			Position pos = ttClone.getPosForIndex(possibleMoveIndex);
			madeMovesBoard[pos.x][pos.y]++;
			gs = ttClone.makeMove(possibleMoveIndex);

			// playout random play
			while (gs == GameState.P1S_TURN || gs == GameState.P2S_TURN) {
				possibleMoves = ttClone.getNumberOfPossibleMoves();
				int nextMoveIndex = (int) (Math.random() * possibleMoves);
				gs = ttClone.makeMove(nextMoveIndex);
			}
			if (gs == GameState.P1_WINS) {
				winMovesBoard[pos.x][pos.y]++;
			}
		}

		// print result board
		System.out.println("total tries");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				System.out.print(madeMovesBoard[x][y] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
		// print result board
		System.out.println("total wins");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				System.out.print(winMovesBoard[x][y] + " ");
			}
			System.out.println("");

		}
		System.out.println("");
		// select best move and make it

		// print result board
		System.out.println("total wins");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				System.out.print(winMovesBoard[x][y] + " ");
			}
			System.out.println("");
		}

		// select best move and make it

		gs = tt.makeMove(possibleMoveIndex);

		return gs;
	}

}
