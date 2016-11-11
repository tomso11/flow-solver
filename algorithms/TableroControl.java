package algorithms;

import java.util.LinkedList;

public class TableroControl {
	
	private int[][] tablero;
	private int x;
	private int y;
	private int[] colors;
	private LinkedList<Sink> sinks;
	private int emptyCells;
	private int score;
	

	public TableroControl(int[][] tablero, int x, int y, int[] colors, LinkedList<Sink> sinks) {
		this.tablero = tablero;
		this.x = x;
		this.y = y;
		this.colors = colors;
		this.sinks = sinks;
		calcEmptyCells();
	}

	public TableroControl(int[][] tablero,int x, int y, int[] colors, LinkedList<Sink> sinks, int score) {
		this.tablero = tablero;
		this.colors = colors;
		this.sinks = sinks;
		calcEmptyCells();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public int[] getColors() {
		return colors;
	}

	public void setColors(int[] colors) {
		this.colors = colors;
	}

	public LinkedList<Sink> getSinks() {
		return sinks;
	}

	public void setSinks(LinkedList<Sink> sinks) {
		this.sinks = sinks;
	}
	
	public int getEmptyCells() {
		return emptyCells;
	}

	public boolean isSolved() {
		return emptyCells == 0;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore() {
		int completePath=0;
		int emptyCells=getEmptyCells();
		for(int i=0; i<getSinks().size(); i++){
			if(getSinks().get(i).getPathLength() > 0){
				completePath++;
			}
		}
		score=completePath*10-emptyCells;
	}
	
	public void calcEmptyCells(){
		int count = 0;
		for(int i=0; i<x; i++){
			for(int j=0; j<y ; j++){
				if(tablero[i][j] == -1){
					count++;
				}
			}
		}
		this.emptyCells=count;
	}
	
	public void printTab(){
		System.out.println("Tab print:");
		for(int i=0; i<getX(); i++){
			for(int j=0; j<getY(); j++){
				System.out.print(tablero[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Print end");
		
	}
	
	public void printMatrix(){
			for (int x=0; x < getX(); x++) {
				System.out.print("|");
				for (int y=0; y < getY() ; y++) {
					System.out.print (getTablero()[x][y]);
					if (y!=getY()) System.out.print("\t");
				}
				System.out.println("|");
			}	
	}
	
//	@SuppressWarnings("null")
//	public TableroControl[] findChildren( Cell c ) {
//		int i=c.getX(),j=c.getY(), index=0;
//		TableroControl[] ans = null;
//		int forced=0;
//		
//		if ( i != y && tablero[i+1][j] == -1 ){ // no se pasa del limite y es un casillero vacio
//			int[][] tabClone=tablero;
//			tabClone[i+1][j]=c.getColor();
//			ans[index++]=new TableroControl(tabClone, x, y, colors, sinks, children, costToCome);
//		}
//		if ( i != 0 && tablero[i-1][j] == -1 ){ // no se pasa del limite y es un casillero vacio
//			if(ans.length != 0){
//				forced= 1;
//			}
//			int[][] tabClone=tablero;
//			tabClone[i-1][j]=c.getColor();
//			ans[index++]=new TableroControl(tabClone, x, y, colors, sinks, children, costToCome);
//		}
//		if ( j != x && tablero[i][j+1] == -1 ){ // no se pasa del limite y es un casillero vacio
//			if(ans.length != 0){
//				forced= 1;
//			}
//			int[][] tabClone=tablero;
//			tabClone[i][j+1]=c.getColor();
//			ans[index++]=new TableroControl(tabClone, x, y, colors, sinks, children, costToCome);
//		}
//		if ( j!= 0 && tablero[i][j-1] == -1 ){ // no se pasa del limite y es un casillero vacio
//			if(ans.length != 0){
//				forced= 1;
//			}
//			int[][] tabClone=tablero;
//			tabClone[i][j-1]=c.getColor();
//			ans[index++]=new TableroControl(tabClone, x, y, colors, sinks, children, costToCome);
//		}
//		
//		for( int k=0 ; k<index ; k++){
//			ans[k].setScore(forced);
//		}
//		
//		return ans;
//		
//	}
//	
//	public void setScore( int forced ){
//		
//		this.score=emptyCells+forced+costToCome;
//		
//	}
//	
//	public int getScore(){
//		return score;
//	}
	
//	public boolean isColorComplete (Cell c){
//		
//		for(int i=0; i<sinks.size(); i++ ){
//			if( sinks[i].getColor() == c.getColor() ){ 
//				if( sinks[i].getFirstX() == c.getX() && sinks[i].getFirstY() == c.getY() || sinks[i].getSecX() == c.getX() && sinks[i].getSecX() == c.getX() ) {
//					if( (c.getY() != y && tablero[c.getY()+1][c.getX()] == c.getColor()) || (c.getY() != 0 && tablero[c.getY()-1][c.getX()] == c.getColor()) || (c.getX() != x && tablero[c.getY()][c.getX()+1] == c.getColor()) || (c.getX()!= 0 && tablero[c.getY()][c.getX()-1] == c.getColor()) )
//						return true;
//				}
//			}
//		}
//		return false;
//	}

	
}
