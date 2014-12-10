package se.kjellstrand.mcts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import se.kjellstrand.boardgame.BoardGame;
import se.kjellstrand.boardgame.BoardGame.State;
import se.kjellstrand.tictac.TicTac;

public class MCTS2<T> {

	private ArrayList<T> possibleMoves;

	private Node root = new Node();

	private class Node {
		int totalPlays = 0;
		int totalWins = 0;
		HashMap<T, MCTS2.Node> children = new HashMap<T, MCTS2.Node>();
		T move = null;
	}

	public MCTS2(ArrayList<T> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	public int getNextMove(TicTac tt) {

		State gs = null;

		long time = System.currentTimeMillis();
		while (time + 100 > System.currentTimeMillis()) {

			TicTac ttClone = tt.clone();

			while (gs == State.ONGOING) {
				//TODO Players turn?
				possibleMoves = (ArrayList<T>) ttClone.getPossibleMoves();
				// T nextMove = possibleMoves.get(((int) (Math.random() *
				// possibleMoves.size())));
				// gs = ttClone.makeMove(nextMove);
			}
		}

		T nextMove;

		return 1;// nextMove;
	}

}
