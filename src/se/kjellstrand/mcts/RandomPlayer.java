package se.kjellstrand.mcts;

import se.kjellstrand.boardgame.BoardGame.Player;
import se.kjellstrand.boardgame.BoardGame.State;
import se.kjellstrand.boardgame.BoardGame;

public class RandomPlayer extends BoardGamePlayer {

	public State makeNextMove(BoardGame bg, Player p) {
		int possibleMoves = bg.getPossibleMoves().size();
		int possibleMoveIndex = (int) (Math.random() * possibleMoves);
		return bg.makeMove(possibleMoveIndex);
	}
}