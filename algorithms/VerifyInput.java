package algorithms;

import java.awt.Point;
import java.util.LinkedList;

public class VerifyInput {
	 private static int fils;
	 private static int cols;
	 private static int [][] table;
	 private static int [] colours;
	 private static int nColours;
	 
	 public VerifyInput(){
	 }
	 
	 public boolean input (String ent){
		int entry = 0, i = 0;
		while (entry != 2){
			if ( Character.isDigit(ent.charAt(i))){
				if (entry == 0){
							fils=fils*10+ ent.charAt(i) - '0';
				}
				else {
							cols=cols*10+ ent.charAt(i) - '0';
				}
			}
			else {
				if (entry == 0){
						if (ent.charAt(i) != ',') {
							System.out.println("El formato del archivo es incorrecto!");
							return false;
						}
						System.out.println("Filas: " + fils);
						entry = 1;
				}
				else{
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
		table = new int [fils][cols];
		for ( int j = 0 ; j < fils; j++ ){
			for ( int k = 0 ; k < cols ; k++ ) {
				if (Character.isDigit(ent.charAt(i))){
					int num = ent.charAt(i)-'0';
					table[j][k] = num;
					colours[num]++;
					nColours++;
				}
				else if(ent.charAt(i) == ' '){
					table[j][k] = -1;
				}
				else{
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
		nColours /= 2;
		return true;
	}
		
	public int[] getColours(){
		int [] gColours = new int[nColours];
		int j = 0;
		for (int i = 0; i < 10 ; i++){
			if (colours[i] == 2)
				gColours[j++] = i;
		}
		return gColours;
	}
	
	public int getNumberOfColours(){
		return nColours;
	}
	
	public LinkedList<Sink> getSinksInstance(){
		LinkedList<Sink> sinks = new LinkedList<Sink>();
		int[] colors = getColours();
		int[][] tab=getTable();
		boolean firstSink=true,secSink=false;
		for(int k=0; k < colors.length ; k++){ // habra un sink por cada color 
			sinks.add( new Sink(-1, -1, -1, -1, colors[k]) ); // creamos un sink "general" que luego modificaremos
			for( int i=0 ; i< getFils() ; i++){ //recorremos la matriz por cada color
				for( int j=0 ; j< getCols() ; j++){
					// si logramos matchear el color con un casillero, lo agregaremos a la primera o segunda coordenada del sink segun corresponda
					if (colors[k] == tab[i][j]){ 
						if(firstSink){
							sinks.get(k).setFirstX(i);
							sinks.get(k).setFirstY(j);
							firstSink=false;
							secSink=true;
						}
						else{
							sinks.get(k).setSecX(i);
							sinks.get(k).setSecY(j);
						}
					}
				}
			}
			firstSink=true; // reiniciamos las variables para el proximo color
			secSink=false;
		}
		return sinks;	
	}
	
	public TableroControl getTableroInstance(){
		return new TableroControl( getTable(), getFils(), getCols(), getColours(), getSinksInstance() );
	}
	public Point[] getSinks(){
		Point[] sinks = new Point[this.getNumberOfColours()*2];
		int [] colours = this.getColours();
		int l = 0;
		for (int k = 0 ; k < this.getNumberOfColours() ; k++){
			for (int x = 0 ; x < this.getFils() ; x++){
				for (int y = 0 ; y < this.getCols() ; y++){
					if (colours[k] == table[x][y]){
						sinks[l++]= new Point(x,y);
					}
						
				}
			}
		}
		return sinks;
		
		
	}

	public int getFils() {
		return fils;
	}

	public int getCols() {
			return cols;
	}

	public static int[][] getTable() {
		return table;
	}
}
