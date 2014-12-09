package se.kjellstrand.tictac;

import java.util.ArrayList;

public class TicTac extends Game implements Cloneable {

	private static final int P1 = 1;
	private static final int P2 = 2;

	private ArrayList<Position> possibleMoves = new ArrayList<Position>();

	private int[][] board = new int[3][3];

	private GameState gs = GameState.NEXT;

	private Player currentPlayer = Player.PLAYER_1;

	private Position lastPosition = new Position(0, 0);

	public void init() {
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
		clone.currentPlayer = currentPlayer;
		clone.gs = gs;
		clone.lastPosition = lastPosition;
		return clone;
	}

	public Position getPosForIndex(int possibleMoveIndex) {
		return possibleMoves.get(possibleMoveIndex);
	}

	public GameState makeMove(int nextMove) {
		Position pos = possibleMoves.remove(nextMove);
		lastPosition = pos;
		board[pos.x][pos.y] = currentPlayer == Player.PLAYER_1 ? P1 : P2;
		GameState gs = getGameState();
		swapPlayer(gs);
		//System.out.println("Swap player: " + currentPlayer);
		return gs;
	}

	private void swapPlayer(GameState gs) {
		if (gs == GameState.NEXT) {
			currentPlayer = currentPlayer == Player.PLAYER_1 ? Player.PLAYER_2 : Player.PLAYER_1;
		}
	}

	GameState getGameState() {
		int p = currentPlayer == Player.PLAYER_1 ? P1 : P2;
		if (board[lastPosition.x][0] == p && // Horizontal check
				board[lastPosition.x][1] == p && //
				board[lastPosition.x][2] == p || //
				board[0][lastPosition.y] == p && // Vertical check
				board[1][lastPosition.y] == p && //
				board[2][lastPosition.y] == p || //
				board[0][0] == p && // Cross, up left to down right
				board[1][1] == p && //
				board[2][2] == p || //
				board[2][0] == p && // Cross, up right to down left
				board[1][1] == p && //
				board[0][2] == p) {
			return GameState.WIN;
		}

		if (getNumberOfPossibleMoves() == 0) {
			return GameState.DRAW;
		}
		return GameState.NEXT;
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
				if (p == P1) {
					System.out.print("O");
				} else if (p == P2) {
					System.out.print("X");
				} else {
					System.out.print("·");
				}
			}
			System.out.println();
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

}