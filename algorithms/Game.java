package algorithms;

import java.awt.Point;

public class Game {
	public static void main(String[] args) {
		String exact= "exact";
		String approx= "approx";
		String progress= "progress";
		Reader a = new Reader();
		VerifyInput game = new VerifyInput();
		String text = a.readTxt("C:\\Users\\favarela\\Desktop\\flow-solver\\algorithms\\Tablero.txt");
		if (game.input(text)){
		int[][] table = VerifyInput.getTable();
		System.out.println("Free Flow Game Solver!");
		System.out.println("Beggining GameBoard:");
		
		for (int x=0; x < game.getFils(); x++) {
			  System.out.print("|");
			  for (int y=0; y < game.getCols() ; y++) {
			    System.out.print (table[x][y]);
			    if (y!=5) System.out.print("\t");
			  }
			  System.out.println("|");
			}
		
		MainFrame window0 = new MainFrame(game.getFils(),game.getCols(),table);
        window0.setVisible(true);
		Tablero tablero = new Tablero(table ,game.getFils(),game.getCols(),game.getColours(),game.getSinks());
		ExactMethod solution = new ExactMethod();
		solution.ExactSolution(tablero, false);
        MainFrame window = new MainFrame(game.getFils(),game.getCols(),table);
        window.setVisible(true);
        	
/*
* 		if (args[1]!=null && args[1].equals(exact)){
*
*			if (args[2]!=null && args[2].equals(progress)){
*				s.solExact(true);
*			}else if (args[2]==null){
*				s.solExact(false);
*			}else throw new  IllegalArgumentException();
*		}
*		else if (args[1]!=null && args[1].equals(approx)){
*			Integer time;
*			if (args[2]!=null && isInt(args[2])){
*				time=Integer.parseInt(args[2]);
*				if(args[3]!=null && args[3].equals(progress)){
*					s.solAprox(true, time);
*				}else if(args[3]==null){
*					s.solAprox(false, time);
*				}else throw new  IllegalArgumentException();
*			}else throw new  IllegalArgumentException();
*			
*		}else throw new IllegalArgumentException();
*/		}else{
			return;
		}
}

		public static boolean isInt(String str) {
		    try {
		        Integer.parseInt(str);
		        return true;
		    } catch (NumberFormatException e) {
		        return false;
		    }
		}
}
