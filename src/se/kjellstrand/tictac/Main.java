package se.kjellstrand.tictac;

import se.kjellstrand.mcts1.MCTS1;
import se.kjellstrand.tictac.Game.GameState;
import se.kjellstrand.tictac.Game.Player;

public class Main {

	
	public static void main(String[] args) {
		MCTS1 mcts1 = new MCTS1();
		//MCTS2 mcts2;

		TicTac tt = new TicTac();
		tt.init(Player.PLAYER_1);

		GameState gs = tt.getGameState();
		Player p = tt.getCurrentPlayer();
		mcts1.setIAmPlayer(p);

		while (gs == GameState.NEXT) {
			p = tt.getCurrentPlayer();
			System.out.println("------------- " + p + " -------------");
			if (p == Player.PLAYER_1) {
				mcts1.makeNextMove(tt, p);
				gs = tt.getGameState();
				System.out.println("state: " + gs.toString());
				tt.printBoard();
			} else {
				int possibleMoves = tt.getNumberOfPossibleMoves();
				gs = tt.makeMove((int) (Math.random() * possibleMoves));
				System.out.println("state: " + gs.toString());
				tt.printBoard();
			}
		}

	}

}
