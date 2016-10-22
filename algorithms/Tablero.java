package algorithms;

public class Tablero {
	
	private int[][] tablero;
	private int[] colors;
	private Sink[] sinks;
	private Tablero[] children;
	private int emptyCells;
	
	
	public Tablero(int[][] tablero, int[] colors, Sink[] sinks, Tablero[] children) {
		super();
		this.tablero = tablero;
		this.colors = colors;
		this.sinks = sinks;
		this.children = children;
		this.emptyCells=tablero.length - sinks.length;
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

	public Sink[] getSinks() {
		return sinks;
	}

	public void setSinks(Sink[] sinks) {
		this.sinks = sinks;
	}
	
	public Tablero[] getChildren() {
		return children;
	}

	public void setChildren(Tablero[] children) {
		this.children = children;
	}

	public int getEmptyCells() {
		return emptyCells;
	}

	public void setEmptyCells(int emptyCells) {
		this.emptyCells = emptyCells;
	}

	public boolean isSolved() {
		return emptyCells == 0;
	}

	public Tablero[] findChildren(int currColor) {
		
		
		
	}

	
}
