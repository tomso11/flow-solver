package algorithms;

public class Game {
	public static void main(String[] args) {
		String exact= "exact";
		String approx= "approx";
		String progress= "progress";
		Reader a = new Reader();
		Solver s = new Solver();
		String text = a.readTxt(args[0]);
		VerifyInput game = new VerifyInput();
		game.input(text);
		int[][] table = VerifyInput.getTable();
		System.out.println("Free Flow Game Solver!");
		System.out.println("Beggining GameBoard: \n");
		for ( int j = 0 ; j < 6; j++ ){
				System.out.println(table[j]);
			}
        	MainFrame window = new MainFrame(game.getFils(),game.getCols(),VerifyInput.getTable());
        	window.setVisible(true);
		if (args[1]!=null && args[1].equals(exact)){
			if (args[2]!=null && args[2].equals(progress)){
				s.solExact(true);
			}else if (args[2]==null){
				s.solExact(false);
			}else throw new  IllegalArgumentException();
		}
		else if (args[1]!=null && args[1].equals(approx)){
			Integer time;
			if (args[2]!=null && isInt(args[2])){
				time=Integer.parseInt(args[2]);
				if(args[3]!=null && args[3].equals(progress)){
					s.solAprox(true, time);
				}else if(args[3]==null){
					s.solAprox(false, time);
				}else throw new  IllegalArgumentException();
			}else throw new  IllegalArgumentException();
			
		}else throw new IllegalArgumentException();
		
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
