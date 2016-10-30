package TPE;

public class Game {
	public static void main(String[] args) {
		String exact= "exact";
		String approx= "approx";
		String progress= "progress";
		Reader a = new Reader();
		Solver s=new Solver();
		String texto = a.readTxt("C:\\Users\\flor\\Desktop\\Facu\\Eclipse\\TPE\\src\\TPEprototype\\"+args[0]);
		SolvingGame juego = new SolvingGame();
		juego.input(texto);
		for ( int j = 0 ; j < 6; j++ ){
				char[][] table = SolvingGame.getTable();
				System.out.println(table[j]);
			}
		System.out.println("Free Flow Game Solver!");
        MainFrame f = new MainFrame(juego.getFils(),juego.getCols(),SolvingGame.getTable());
        f.setVisible(true);
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
