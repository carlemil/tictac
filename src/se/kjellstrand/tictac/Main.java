package se.kjellstrand.tictac;

import se.kjellstrand.mcts1.MCTS1;

public class Main {

	public static void main(String[] args) {
		MCTS1 mcts1 = new MCTS1();
		MCTS2 mcts2;

		TicTac tt = new TicTac();
		tt.init();

		// TODO skapa random vs mcts1 game å testkör.

		GameState gs = tt.getGameState();
		mcts1.setIAmPlayer(gs);

		while (gs == GameState.P1S_TURN || gs == GameState.P2S_TURN) {
			System.out.println("------------- " + gs + " -------------");
			if (gs == GameState.P1S_TURN) {
				gs = mcts1.makeNextMove(tt);
				System.out.println("state: " + gs.toString());
				tt.printBoard();
			} else {
				int possibleMoves = tt.getNumberOfPossibleMoves();
				gs = tt.makeMove((int) (Math.random() * possibleMoves));
				System.out.println("state: " + gs.toString());
				tt.printBoard();
			}
			tt.swapPlayer();
		}

		/*
		 * for (int i = 0; i < 2; i++) { int possibleMoves =
		 * tt.getNumberOfPossibleMoves(); gs = tt.makeMove((int) (Math.random()
		 * * possibleMoves)); tt.printBoard(); System.out.println("state: " +
		 * gs.toString()); } System.out.println("evaluate next move"); gs =
		 * mcts1.makeNextMove(tt);
		 */
		// tt.printBoard();
		// System.out.println("state: "+gs.toString());
		// TicTac tt2 = tt.clone();
		//
		// tt2.printBoard();
	}

}
