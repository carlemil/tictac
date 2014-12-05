package se.kjellstrand.tictac;

import java.util.ArrayList;

public class TicTac implements Cloneable {

	private static final int PLAYER_1 = 1;
	private static final int PLAYER_2 = 2;

	private ArrayList<Position> possibleMoves = new ArrayList<Position>();

	private int[][] board;

	private int currentPlayer = PLAYER_1;
	private Position lastPosition;

	public TicTac() {
		board = new int[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				possibleMoves.add(new Position(x, y));
			}
		}
	}

	public TicTac clone() {
		TicTac clone = new TicTac();
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				clone.board[x][y] = board[x][y];
			}
		}
		for (int i = 0; i < possibleMoves.size(); i++) {
			clone.possibleMoves.add(i, possibleMoves.get(i));
		}
		return clone;
	}
	
	public Position getPosForIndex(int possibleMoveIndex){
		return possibleMoves.get(possibleMoveIndex);
	}

	public GameState makeMove(int possibleMoveIndex) {
		Position pos = possibleMoves.remove(possibleMoveIndex);
		lastPosition = pos;
		board[pos.x][pos.y] = currentPlayer;
		GameState gs = getGameState();
		if (currentPlayer == PLAYER_1) {
			currentPlayer = PLAYER_2;
		} else {
			currentPlayer = PLAYER_1;
		}
		return gs;
	}

	private GameState getGameState() {
		if (getNumberOfPossibleMoves() == 0) {
			return GameState.DRAW;
		}

		if (board[lastPosition.x][0] == currentPlayer && // Horizontal check
				board[lastPosition.x][1] == currentPlayer && //
				board[lastPosition.x][2] == currentPlayer || //
				board[0][lastPosition.y] == currentPlayer && // Vertical check
				board[1][lastPosition.y] == currentPlayer && //
				board[2][lastPosition.y] == currentPlayer || //
				board[0][0] == currentPlayer && // Cross, up left to down right
				board[1][1] == currentPlayer && //
				board[2][2] == currentPlayer || //
				board[2][0] == currentPlayer && // Cross, up right to down left
				board[1][1] == currentPlayer && //
				board[0][2] == currentPlayer) {
			if (currentPlayer == PLAYER_1) {
				return GameState.P1_WINS;
			} else {
				return GameState.P2_WINS;
			}
		}
		if (currentPlayer == PLAYER_1) {
			return GameState.P2S_TURN;
		} else {
			return GameState.P1S_TURN;
		}
	}

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