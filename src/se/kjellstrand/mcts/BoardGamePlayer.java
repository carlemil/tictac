package se.kjellstrand.mcts;

import se.kjellstrand.boardgame.BoardGame;
import se.kjellstrand.boardgame.BoardGame.Player;
import se.kjellstrand.boardgame.BoardGame.State;

public abstract class BoardGamePlayer {

	public abstract State makeNextMove(BoardGame bg, Player p);

}
