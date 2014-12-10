package se.kjellstrand.boardgame;

import se.kjellstrand.boardgame.BoardGame.Player;
import se.kjellstrand.boardgame.BoardGame.State;

public class GameResult {

	public Player player;
	public State state;

	public GameResult(Player p, State s) {
		player = p;
		state = s;
	}
}
