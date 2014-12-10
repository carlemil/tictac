package se.kjellstrand.tictac;

import javax.swing.text.Position;

import se.kjellstrand.boardgame.BoardGame.Player;
import se.kjellstrand.boardgame.BoardGame.State;
import se.kjellstrand.boardgame.GameResult;
import se.kjellstrand.mcts.MCTS1;
import se.kjellstrand.mcts.MCTS2;

public class Main {

	public static void main(String[] args) {
		int win = 0;
		int games = 0;
		for (int i = 0; i < 50; i++) {
			GameResult res;
			if (i % 2 == 0) {
				res = playOneGame(Player.ONE);
			} else {
				res = playOneGame(Player.TWO);
			}
			games++;
			if (res.player == Player.ONE) {
				win++;
			}
		}
		System.out.println("Games: " + games + " wins: " + win);
	}

	private static GameResult playOneGame(Player startingPlayer) {
		MCTS1 mcts1 = new MCTS1();
		MCTS2 mcts2 = new MCTS2();

		TicTac tt = new TicTac();
		// Set the starting player
		tt.init(startingPlayer);

		State gs = tt.getGameState();

		// Set who the ai plays for
		mcts1.setIAmPlayer(Player.ONE);
		mcts2.setIAmPlayer(Player.TWO);

		Player p = null;
		while (gs == State.ONGOING) {
			p = tt.getCurrentPlayer();
			System.out.println("------------- " + p + " -------------");
			if (p == Player.ONE) {
				mcts1.makeNextMove(tt, p);
				gs = tt.getGameState();
			} else {
				mcts2.makeNextMove(tt, p);
				gs = tt.getGameState();
			}
			System.out.println("state: " + gs.toString());
			tt.printBoard();
		}
		return new GameResult(p, gs);
	}

}
