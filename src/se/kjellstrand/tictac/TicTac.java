package se.kjellstrand.tictac;


public class TicTac extends TwoPlayerBoardGame {

	public TicTac() {
		super(3,3);
		
	}

	@Override
	protected GameState getGameState(Position pos) {
		if(super.getNumberOfPossibleMoves() == 0){
			return GameState.DRAW;
		}
		return GameState.P1S_TURN;
	}


}