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
		HashMap<Position, MCTS2.Node> children = null;
		// Position position = null;
		Node parent = null;
	}

	private enum Result {
		WIN, LOSS, DRAW
	}

	public void setIAmPlayer(Player iAmPlayer) {
		this.iAmPlayer = iAmPlayer;
	}

	public State makeNextMove(BoardGame bg, Player p) {

		State gs = null;
		Node expandedNode = null;

		long time = System.currentTimeMillis();
		while (time + 100 > System.currentTimeMillis()) {
			// 1. Selection
			Node selectedNode = selection(root);

			// 2. Expansion
			expandedNode = expansion(selectedNode);

			// 3. Simulation
			Result result = simulation(expandedNode);

			// 4. Backpropagation
			backpropagation(expandedNode, result);
		}

		finalSelection(expandedNode);

		return gs;
	}

	private Node selection(Node selectedNode) {
		// Starting at root node R, recursively select optimal child nodes
		// (explained below) until a leaf node 'selectedNode' is reached.
		HashMap<Position, Node> children = selectedNode.children;
		if (children != null && children.size() > 0) {
			float highestNodeEval = 0f;
			for (Position key : children.keySet()) {
				Node child = children.get(key);
				float eval = eval(child);
				if (eval > highestNodeEval) {
					highestNodeEval = eval;
					selectedNode = child;
				}
			}
		} else {
			// found leaf node, return it.
			return selectedNode;
		}
		// look for leaf node.
		return selection(selectedNode);
	}

	private float eval(Node n) {
		// wi / ni + c * sqrt( ln(t) / ni )
		float wi = n.totalWins;
		float ni = n.totalPlays;
		// float t = n.parent != null ? n.parent.totalPlays : 1f;
		float c = 1.4f;
		
		// TODO make propper eval
		
		return (float) Math.random();
	}

	private Node expansion(Node node) {
		// If 'node' is a not a terminal node (i.e. it does not end
		// the game) then create one or more child nodes and select one
		// 'expandedNode'.
		Node expandedNode = new Node();
		expandedNode.parent = node;
		expandedNode.children = new HashMap<Position, MCTS2.Node>(4);
		return expandedNode;
	}

	private Result simulation(Node node) {
		// Run a simulated playout from 'expandedNode' until a result is
		// achieved.
		
		// BoardGame bgClone = bg.clone();
				// while (gs == State.ONGOING) {
				// setPossibleMoves((ArrayList<Position>) bg.getPossibleMoves());
				// gs = bg.makeMove((int) (Math.random() * possibleMoves.size()));
				// return gs;
		
		return Result.DRAW;
	}

	private void backpropagation(Node node, Result result) {
		// Update the current move sequence with the simulation result.
		if (node != null) {
			node.totalPlays++;
			if (result == Result.WIN) {
				node.totalWins++;
			}
			backpropagation(node.parent, result);
		}
	}

	private Node finalSelection(Node selectedNode) {
		// Starting at root node selectedNode, select optimal child node.
		float highestNodeEval = 0f;
		for (Position key : root.children.keySet()) {
			Node child = root.children.get(key);
			float eval = eval(child);
			if (eval > highestNodeEval) {
				highestNodeEval = eval;
				selectedNode = child;
			}
		}
		return selectedNode;
	}

	public void setPossibleMoves(ArrayList<Position> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

}
