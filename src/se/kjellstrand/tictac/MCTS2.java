package se.kjellstrand.tictac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

		GameState gs = null;

		long time = System.currentTimeMillis();
		while (time + 100 > System.currentTimeMillis()) {

			TicTac ttClone = tt.clone();

			while (gs == GameState.P1S_TURN || gs == GameState.P2S_TURN) {
				possibleMoves = (ArrayList<T>) ttClone.getPossibleMoves();
				//T nextMove = possibleMoves.get(((int) (Math.random() * possibleMoves.size())));
				//gs = ttClone.makeMove(nextMove);
			}
		}

		T nextMove;

		return 1;//nextMove;
	}

}
