package se.kjellstrand.boardgame;

import java.util.ArrayList;


public abstract class BoardGame implements Cloneable {

	public static enum State {
		WIN, LOSS, DRAW, ONGOING
	}

	public static enum Player {
		ONE, TWO
	}
	
	public abstract BoardGame clone();
	
	public abstract ArrayList<Position> getPossibleMoves();
	
	public abstract State getGameState();
	
	public abstract Player getCurrentPlayer();
	
	public abstract State makeMove(int nextMove);
}
