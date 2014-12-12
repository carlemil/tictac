package se.kjellstrand.mcts;

import java.util.ArrayList;
import java.util.HashMap;

import se.kjellstrand.boardgame.BoardGame;
import se.kjellstrand.boardgame.BoardGame.Player;
import se.kjellstrand.boardgame.BoardGame.State;
import se.kjellstrand.boardgame.Position;

public class MCTS2 {

	private Node root = new Node();

	private Player iAmPlayer;

	private class Node {
		int totalPlays = 0;
		int totalWins = 0;
		HashMap<Position, MCTS2.Node> children = null;
		// Position position = null;
		Node parent = null;
		BoardGame bg = null;
		ArrayList<Position> possibleMoves = null;
	}

	public MCTS2(Player player) {
		this.iAmPlayer = player;
	}

	public State makeNextMove(BoardGame bg, Player p) {

		if (bg != null) {
			makeRandomMove(bg);
			return bg.getGameState();
		}

		State gs = null;
		Node expandedNode = null;

		long time = System.currentTimeMillis();
		int a = 3;
		// while (time + 100 > System.currentTimeMillis()) {
		while (a-- != 0) {
			// 1. Selection
			Node selectedNode = selection(root);

			// 2. Expansion
			expandedNode = expansion(selectedNode);

			// 3. Simulation
			State state = simulation(expandedNode);

			// 4. Backpropagation
			backpropagation(expandedNode, state);
		}

		finalSelection(expandedNode);

		return gs;
	}

	private void makeRandomMove(BoardGame bg) {
		int numberOfPossibleMoves = bg.getPossibleMoves().size();
		int nextMoveIndex = (int) (Math.random() * numberOfPossibleMoves);
		bg.makeMove(nextMoveIndex);
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
		expandedNode.bg = node.bg.clone();
		int move = (int) (Math.random() * expandedNode.possibleMoves.size());
		expandedNode.bg.makeMove(move);
		// makeMove will also remove the move from possibleMoves, so no need to
		// do this manually.
		return expandedNode;
	}

	private State simulation(Node node) {
		// Run a simulated playout from 'expandedNode' until a result is
		// achieved.

		// playout random play
		State gs = State.ONGOING;
		BoardGame bg = node.bg.clone();
		while (gs == State.ONGOING) {
			int numberOfPossibleMoves = bg.getPossibleMoves().size();
			int nextMoveIndex = (int) (Math.random() * numberOfPossibleMoves);
			gs = bg.makeMove(nextMoveIndex);
		}
		if (gs.equals(State.DRAW)) {
			return gs;
		}
		// TODO return should depend on player
		if (bg.getCurrentPlayer() == iAmPlayer) {
			if (gs.equals(State.WIN)) {
				return State.WIN;
			} else {
				return State.LOSS;
			}
		} else {
			if (gs.equals(State.WIN)) {
				return State.LOSS;
			} else {
				return State.WIN;
			}
		}
	}

	private void backpropagation(Node node, State state) {
		// Update the current move sequence with the simulation result.
		if (node != null) {
			node.totalPlays++;
			if (state == State.WIN) {
				node.totalWins++;
			}
			backpropagation(node.parent, state);
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
}