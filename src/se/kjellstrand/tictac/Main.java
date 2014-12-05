package se.kjellstrand.tictac;

import se.kjellstrand.mcts1.MCTS1;

public class Main {

	public static void main(String[] args) {
		MCTS1 mcts1 = new MCTS1();
		TicTac tt = new TicTac();
		GameState gs;
		for(int i=0;i<5;i++){
			int possibleMoves = tt.getNumberOfPossibleMoves();
			gs = tt.makeMove((int)(Math.random()*possibleMoves));
			tt.printBoard();
			System.out.println("state: "+gs.toString());
		}
		System.out.println("evaluate next move");
		gs = mcts1.makeNextMove(tt);
		//tt.printBoard();
		//System.out.println("state: "+gs.toString());
//		TicTac tt2 = tt.clone();
//		
//		tt2.printBoard();
	}

}
