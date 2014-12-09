package se.kjellstrand.tictac;

import java.util.HashMap;
import java.util.Set;

public class MCTS2 <T> {

	private Set<T> possibleMoves;
	
	private Node root = new Node();

	private class Node {
		int totalPlays = 0;
		int totalWins = 0;
		HashMap<T, MCTS2.Node> children = new HashMap<T, MCTS2.Node>();
		T move = null;
	}

	public MCTS2(Set<T> possibleMoves){
		this.possibleMoves = possibleMoves;
	}
	
	public T getNextMove() {
		
		
		return null;
	}

	
}
