package se.kjellstrand.mcts;

import javax.management.RuntimeErrorException;

import se.kjellstrand.boardgame.Position;
import se.kjellstrand.boardgame.BoardGame.Player;
import se.kjellstrand.boardgame.BoardGame.State;
import se.kjellstrand.tictac.TicTac;

public class MCTS1 {

	private int[][] madeMovesBoard = new int[3][3];
	private int[][] winMovesBoard = new int[3][3];
	private int[][] possibleMovesMappingBoard = new int[3][3];

	private Player iAmPlayer;

	public State makeNextMove(TicTac tt, Player p) {

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				madeMovesBoard[x][y] = 0;
				winMovesBoard[x][y] = 0;
			}
		}

		State gs = null;
		long time = System.currentTimeMillis();
		while (time + 10 > System.currentTimeMillis()) {
			TicTac ttClone = tt.clone();

			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					possibleMovesMappingBoard[x][y] = -2;
				}
			}
			int possibleMoves = ttClone.getNumberOfPossibleMoves();

			// System.out.println("possiblemoves "+possibleMoves);

			for (int i = 0; i < possibleMoves; i++) {
				Position pos = tt.getPosForIndex(i);
				possibleMovesMappingBoard[pos.x][pos.y] = i;
			}
			int possibleMoveIndex = (int) (Math.random() * possibleMoves);
			Position pos = ttClone.getPosForIndex(possibleMoveIndex);
			madeMovesBoard[pos.x][pos.y]++;
			gs = ttClone.makeMove(possibleMoveIndex);

			// playout random play
			while (gs == State.ONGOING) {
				possibleMoves = ttClone.getNumberOfPossibleMoves();
				int nextMoveIndex = (int) (Math.random() * possibleMoves);
				gs = ttClone.makeMove(nextMoveIndex);
			}
			if (gs == State.WIN) {
				if (iAmPlayer.equals(ttClone.getCurrentPlayer())) {
					winMovesBoard[pos.x][pos.y]++;
				}
			}
		}

		int bestScoreIndex = getResults(tt);

		gs = tt.makeMove(bestScoreIndex);

		return gs;
	}

	private int getResults(TicTac tt) {
		// print result board
		//System.out.println("MCTS1");

//		System.out.println("madeMovesBoard");
//		for (int x = 0; x < 3; x++) {
//			for (int y = 0; y < 3; y++) {
//				System.out.print(madeMovesBoard[x][y] + " ");
//			}
//			System.out.println("");
//		}
//		System.out.println("winMovesBoard");
//		for (int x = 0; x < 3; x++) {
//			for (int y = 0; y < 3; y++) {
//				System.out.print(winMovesBoard[x][y] + " ");
//			}
//			System.out.println("");
//		}

		float bestScore = 0;
		int bestScoreX = 0;
		int bestScoreY = 0;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				float mm = madeMovesBoard[x][y];
				float wm = winMovesBoard[x][y];
				if (mm != 0) {
					if (wm != 0) {

						float score = wm / mm;
						if (score > bestScore) {
							bestScore = score;
							bestScoreX = x;
							bestScoreY = y;
						}
						//System.out.print(String.format("%.2f", score) + " ");
					} else {
						//System.out.print("0.01 ");
						bestScoreX = x;
						bestScoreY = y;
					}
				} else {
					//System.out.print("0.00 ");
				}
			}
			//System.out.println("");
		}

		int bestScoreIndex = possibleMovesMappingBoard[bestScoreX][bestScoreY];
		//System.out.println("\nBest score: " + bestScore + " index: " + bestScoreIndex);
		return bestScoreIndex;
	}

	public Player getIAmPlayer() {
		return iAmPlayer;
	}

	public void setIAmPlayer(Player iAmPlayer) {
		this.iAmPlayer = iAmPlayer;
	}

}
