package algorithms;

import java.awt.Point;

public class Tablero {
	private int[][] tablero;
	private int fils;
	private int cols;
	private int[] colours;
	private int numberOfColours;
	private Point[] sinks;
	
	public Tablero(int[][] tablero,int fils, int cols, int[] colours, Point[] sinks) {
		this.tablero = tablero;
		this.setFils(fils);
		this.setCols(cols);
		this.colours = colours;
		this.setColours(colours.length);
		this.sinks = sinks;
	}
	
	public Tablero clone(){
		final int[][] clonedTablero = new int[fils][cols];
		for (int x=0; x < this.getFils(); x++) {
				for (int y=0; y < this.getCols() ; y++) {
					clonedTablero[x][y] = tablero[x][y];
		    }
		}
		final Tablero tab = new Tablero(clonedTablero,fils,cols,colours,sinks);
		return tab;
	}

	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public int getColour(int i) {
		return colours[i];
	}

	public void setColour(int[] colours) {
		this.colours = colours;
	}

	public Point[] getSinks() {
		return sinks;
	}

	public void setSinks(Point[] sinks) {
		this.sinks = sinks;
	}

	public int getColours() {
		return numberOfColours;
	}

	public void setColours(int numberOfColours) {
		this.numberOfColours = numberOfColours;
	}
	
	public int getCell(Point cell){
		return tablero[(int) cell.getX()][(int) cell.getY()];
	}
	
	public boolean cellIsEmpty(Point cell){
		if (cell.getX() >= 0 && cell.getY() >= 0 && cell.getX() < this.getFils() && cell.getY() < this.getCols() ){
			if (tablero[(int) cell.getX()][(int) cell.getY()] == -1)
				return true;
		}
		return false;
	}

	public int getFils() {
		return fils;
	}

	public void setFils(int fils) {
		this.fils = fils;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
	
	public void paintCell(Point cell,int colour){
		tablero[(int) cell.getX()][(int) cell.getY()] = colour;
		return;
	}
	
	public int cellsEmpty(){
		int empty = 0;
		for (int i = 0; i < fils ; i++){
			for ( int j = 0 ; j < cols ; j++)
			if (tablero[i][j] == -1)
				empty++;
		}
		return empty;
	}
	
	public boolean equals (Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		for (int x=0; x < this.getFils(); x++) {
			for (int y=0; y < this.getCols() ; y++) {
				if(this.getTablero()[x][y] != other.getTablero()[x][y])
					return false;
			}
		}
		return true;
		}
	
	public int hashcode(){
		return this.getFils()+this.getCols();
	}
}
