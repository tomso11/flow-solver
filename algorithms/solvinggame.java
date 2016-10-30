package TPE;

public class SolvingGame {
	 private static int fils;
	 private static int cols;
	 private static char [][] table;
	 private static int [] colours;
	 
	 public SolvingGame(){
	 }

	public boolean input (String ent){
		int entry = 0, i = 0;
		while (entry != 2){
			if ( Character.isDigit(ent.charAt(i))){
				if (entry == 0){
							fils=fils*10+ ent.charAt(i) - '0';
				}else {
							cols=cols*10+ ent.charAt(i) - '0';
				}
			}else {
				if (entry == 0){
						if (ent.charAt(i) != ',') {
							System.out.println("El formato del archivo es incorrecto!");
							return false;
						}
				System.out.println("Filas: " + fils);
				entry = 1;
				}else{
					if (entry == 1){
						if (ent.charAt(i) != '*') {
							System.out.println("El formato del archivo es incorrecto!");
							return false;
						}
						entry = 2;
						System.out.println("Columnas: " + cols);
					}
				}
			}
		i++;
		}
		colours = new int [10];
		table = new char [fils][cols];
		for ( int j = 0 ; j < fils; j++ ){
			for ( int k = 0 ; k < cols ; k++ ) {
				if (Character.isDigit(ent.charAt(i)) || ent.charAt(i) == ' ' ){
					table[j][k] = ent.charAt(i);
					if (Character.isDigit(ent.charAt(i)))
					colours[ent.charAt(i)-'0']++;
				}else{
					System.out.println("El formato del archivo es incorrecto!");
					return false;
				}
				i++;
			}
			if (ent.charAt(i)!='*'){
				System.out.println("El formato del archivo es incorrecto! No se respeta la dimension del tablero.");
				return false;
			}
			i++;
		}
		for ( int j=0 ; j< 10 ; j++){
			if (colours[j] != 0 && colours[j] != 2){
				System.out.println("El formato del archivo es incorrecto! Revisar los colores!");
				return false;
			}
		}
		return true;
	}

	public int getFils() {
		return fils;
	}

	public int getCols() {
		return cols;
	}

	public static char[][] getTable() {
		return table;
	}
}
