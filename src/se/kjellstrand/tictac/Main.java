package se.kjellstrand.tictac;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TicTac tt = new TicTac();

		for(int i=0;i<8;i++){
			int possibleMoves = tt.getNumberOfPossibleMoves();
			GameState gs = tt.makeMove((int)(Math.random()*possibleMoves));
			tt.printBoard();
			System.out.println("state: "+gs.toString());
		}
		
		
//		TicTac tt2 = tt.clone();
//		
//		tt2.printBoard();
	}

}
