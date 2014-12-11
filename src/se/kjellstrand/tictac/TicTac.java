package se.kjellstrand.tictac;

import java.util.ArrayList;

import se.kjellstrand.boardgame.BoardGame;
import se.kjellstrand.boardgame.Position;

public class TicTac extends BoardGame {

	private static final byte P1 = 1;
	private static final byte P2 = 2;

	private ArrayList<Position> possibleMoves = new ArrayList<Position>();

	private byte[][] board = new byte[3][3];

	private State gs = State.ONGOING;

	private Player currentPlayer = Player.ONE;

	private Position lastPosition = new Position(0, 0);

	public void init(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
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

	public State makeMove(int nextMove) {
		Position pos = possibleMoves.remove(nextMove);
		lastPosition = pos;
		board[pos.x][pos.y] = currentPlayer == Player.ONE ? P1 : P2;
		State gs = getGameState();
		swapPlayer(gs);
		// System.out.println("Swap player: " + currentPlayer);
		return gs;
	}

	private void swapPlayer(State gs) {
		if (gs == State.ONGOING) {
			currentPlayer = currentPlayer == Player.ONE ? Player.TWO : Player.ONE;
		}
	}

	public State getGameState() {
		if (board[lastPosition.x][0] == P1 && // Horizontal check
				board[lastPosition.x][1] == P1 && //
				board[lastPosition.x][2] == P1 || //
				board[0][lastPosition.y] == P1 && // Vertical check
				board[1][lastPosition.y] == P1 && //
				board[2][lastPosition.y] == P1 || //
				board[0][0] == P1 && // Cross, up left to down right
				board[1][1] == P1 && //
				board[2][2] == P1 || //
				board[2][0] == P1 && // Cross, up right to down left
				board[1][1] == P1 && //
				board[0][2] == P1) {
			return currentPlayer == Player.ONE ? State.WIN : State.LOSS;
		} else if (board[lastPosition.x][0] == P2 && // Horizontal check
				board[lastPosition.x][1] == P2 && //
				board[lastPosition.x][2] == P2 || //
				board[0][lastPosition.y] == P2 && // Vertical check
				board[1][lastPosition.y] == P2 && //
				board[2][lastPosition.y] == P2 || //
				board[0][0] == P2 && // Cross, up left to down right
				board[1][1] == P2 && //
				board[2][2] == P2 || //
				board[2][0] == P2 && // Cross, up right to down left
				board[1][1] == P2 && //
				board[0][2] == P2) {
			return currentPlayer == Player.TWO ? State.WIN : State.LOSS;
		} else

		if (getNumberOfPossibleMoves() == 0) {
			return State.DRAW;
		}
		return State.ONGOING;
	}

	public ArrayList<Position> getPossibleMoves() {
		return possibleMoves;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
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

}