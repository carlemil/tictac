package se.kjellstrand.mcts1;

import se.kjellstrand.tictac.GameState;
import se.kjellstrand.tictac.Position;
import se.kjellstrand.tictac.TicTac;

public class MCTS1 {

	private int[][] madeMovesBoard = new int[3][3];
	private int[][] winMovesBoard = new int[3][3];
	private int[][] possibleMovesMappingBoard = new int[3][3];

	private GameState iAmPlayer;

	public GameState makeNextMove(TicTac tt) {
		System.out.println("");

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				madeMovesBoard[x][y] = 0;
				winMovesBoard[x][y] = 0;
			}
		}

		GameState gs = null;
		long time = System.currentTimeMillis();
		while (time + 100 > System.currentTimeMillis()) {
			TicTac ttClone = tt.clone();

			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					possibleMovesMappingBoard[x][y] = -2;
				}
			}
			int possibleMoves = ttClone.getNumberOfPossibleMoves();
			for (int i = 0; i < possibleMoves; i++) {
				Position p = tt.getPosForIndex(i);
				possibleMovesMappingBoard[p.x][p.y] = i;
			}
			int possibleMoveIndex = (int) (Math.random() * possibleMoves);
			Position pos = ttClone.getPosForIndex(possibleMoveIndex);
			madeMovesBoard[pos.x][pos.y]++;
			gs = ttClone.makeMove(possibleMoveIndex);

			// playout random play
			while (gs == GameState.P1S_TURN || gs == GameState.P2S_TURN) {
				possibleMoves = ttClone.getNumberOfPossibleMoves();
				int nextMoveIndex = (int) (Math.random() * possibleMoves);
				gs = ttClone.makeMove(nextMoveIndex);
			}
			if (gs.equals(iAmPlayer == GameState.P1S_TURN ? GameState.P1_WINS : GameState.P2_WINS)) {
				winMovesBoard[pos.x][pos.y]++;
			}
		}

		int bestScoreIndex = getResults(tt);

		gs = tt.makeMove(bestScoreIndex);

		return gs;
	}

	private int getResults(TicTac tt) {
		// print result board
		System.out.println("MCTS1");
		float bestScore = -1;
		int bestScoreX = -1;
		int bestScoreY = -1;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				float mm = madeMovesBoard[x][y];
				float wm = winMovesBoard[x][y];
				if (mm != 0 && wm != 0) {
					float score = wm / mm;
					if (score > bestScore) {
						bestScore = score;
						bestScoreX = x;
						bestScoreY = y;
					}
					System.out.print(String.format("%.2f", score) + " ");
				} else {
					System.out.print("0.00 ");
				}
			}
			System.out.println("");
		}

		int bestScoreIndex = possibleMovesMappingBoard[bestScoreX][bestScoreY];
		System.out.println("\nBest score: " + bestScore + " index: " + bestScoreIndex);
		return bestScoreIndex;
	}

	public GameState getIAmPlayer() {
		return iAmPlayer;
	}

	public void setIAmPlayer(GameState iAmPlayer) {
		this.iAmPlayer = iAmPlayer;
	}

}
