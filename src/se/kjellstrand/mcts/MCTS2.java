package se.kjellstrand.mcts;

import java.util.ArrayList;
import java.util.HashMap;

import se.kjellstrand.boardgame.BoardGame;
import se.kjellstrand.boardgame.BoardGame.Player;
import se.kjellstrand.boardgame.BoardGame.State;
import se.kjellstrand.boardgame.Position;

public class MCTS2 {

	private ArrayList<Position> possibleMoves;

	private Node root = new Node();

	private Player iAmPlayer;

	private class Node {
		int totalPlays = 0;
		int totalWins = 0;
		HashMap<Position, MCTS2.Node> children = new HashMap<Position, MCTS2.Node>();
		Position move = null;
	}

	public void setIAmPlayer(Player iAmPlayer) {
		this.iAmPlayer = iAmPlayer;
	}

	public State makeNextMove(BoardGame bg, Player p) {

		State gs = null;

		long time = System.currentTimeMillis();
		//while (time + 100 > System.currentTimeMillis()) {

			//BoardGame bgClone = bg.clone();

			//while (gs == State.ONGOING) {
				setPossibleMoves((ArrayList<Position>) bg.getPossibleMoves());
				gs = bg.makeMove((int) (Math.random() * possibleMoves.size()));
				//return gs;
			//}
		//}

		return gs;
	}

	public void setPossibleMoves(ArrayList<Position> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

}
