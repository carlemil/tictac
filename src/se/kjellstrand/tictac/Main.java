package se.kjellstrand.tictac;

import se.kjellstrand.boardgame.BoardGame;
import se.kjellstrand.boardgame.BoardGame.Player;
import se.kjellstrand.boardgame.BoardGame.State;
import se.kjellstrand.boardgame.GameResult;
import se.kjellstrand.mcts.MCTS1;
import se.kjellstrand.mcts.MCTS2;
import se.kjellstrand.mcts.RandomPlayer;

public class Main {

	public static void main(String[] args) {
		int win = 0;
		int games = 0;
		for (int i = 0; i < 1; i++) {
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
		MCTS1 mcts1 = new MCTS1(Player.ONE);
		MCTS2 mcts2 = new MCTS2(Player.ONE);
		RandomPlayer rp = new RandomPlayer();

		BoardGame bg = new TicTac();
		// Set the starting player
		bg.init(startingPlayer);

		State gs = bg.getGameState();

		Player p = null;
		while (gs == State.ONGOING) {
			p = bg.getCurrentPlayer();
			System.out.println("------------- " + p + " -------------");
			if (p == Player.ONE) {
				//mcts1.makeNextMove(tt, p);
				mcts2.makeNextMove(bg, p);
			} else {
				rp.makeNextMove(bg, p);
			}
			gs = bg.getGameState();
			System.out.println("state: " + gs.toString());
			bg.printBoard();
		}
		return new GameResult(p, gs);
	}

}
