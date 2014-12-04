package se.kjellstrand.tictac;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TwoPlayerBoardGame tt = new TicTac();

		for(int i=0;i<4;i++){
			int possibleMoves = tt.getNumberOfPossibleMoves();
			tt.makeMove((int)(Math.random()*possibleMoves));
		}
		
		tt.printBoard();
		
	}

}
