package se.kjellstrand.tictac;

import java.util.ArrayList;

public abstract class TwoPlayerBoardGame {

	private static final int PLAYER_1 = 1;
	private static final int PLAYER_2 = 2;

	private ArrayList<Position> possibleMoves = new ArrayList<Position>();

	private int[][] board;

	private int currentPlayer = PLAYER_1;

	public TwoPlayerBoardGame(int boardWidth, int boardHeight) {
		board = new int[boardWidth][boardHeight];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				possibleMoves.add(new Position(x, y));
			}
		}
	}

	public void makeMove(int possibleMoveIndex) {
		Position pos = possibleMoves.remove(possibleMoveIndex);
		board[pos.x][pos.y] = currentPlayer;
		if (currentPlayer == PLAYER_1) {
			currentPlayer = PLAYER_2;
		} else {
			currentPlayer = PLAYER_1;
		}
	}

	protected abstract GameState getGameState(Position pos);

	protected ArrayList<Position> getPossibleMoves() {
		return possibleMoves;
	}

	public int getNumberOfPossibleMoves() {
		return possibleMoves.size();
	}

	public void printBoard() {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				int p = board[x][y];
				if (p == PLAYER_1) {
					System.out.print("O");
				} else if (p == PLAYER_2) {
					System.out.print("X");
				} else {
					System.out.print("·");
				}
			}
			System.out.println();
		}
	}
}
