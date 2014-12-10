package se.kjellstrand.boardgame;

import java.util.ArrayList;


public abstract class BoardGame {

	public static enum State {
		WIN, DRAW, ONGOING
	}

	public static enum Player {
		ONE, TWO
	}
	
	
	
	public abstract ArrayList<Position> getPossibleMoves();
	
	public abstract State getGameState();
	
	public abstract Player getCurrentPlayer();
}
