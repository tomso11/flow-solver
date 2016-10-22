package algorithms;

import java.util.PriorityQueue;

//Para la solucion aproximada pense en generar un arbol que parte de un tablero vacío, elige un color y comienza a avanzar sobre ese color
//cada "step" del algoritmo sera un nodo distinto, en cada nodo, el color seleccionado avanzara de a un casillero a la vez.
//De esta manera
public class ApproxSolution {
	
	private int fils, cols;
	private int[] colors;
	private int[][] tablero;
	private PriorityQueue<Cell> order;
	
	public ApproxSolution(int fils, int cols, int[] colors, int[][] tablero) {
		this.fils = fils;
		this.cols = cols;
		this.colors = colors;
		this.tablero = tablero;
	}
	
	private static class Cell{ // faltaria el compare aca
		int x, y, color, priority;
		
		Cell(int x, int y, int color, int priority){
			this.x=x;
			this.y=y;
			this.color=color;
			this.priority=priority;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (color != other.color)
				return false;
			if (priority != other.priority)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		
	}
	
	// el metodo busca obtener el nodo/ color que tenga una menor variedad de movimientos
	
	public PriorityQueue<Cell> pickLessAmbiguous(){
		
		PriorityQueue<Cell> order = new PriorityQueue<Cell>(colors.length);
		int priority=0;
		for(int i = 0; i<fils; i++){
			for(int j=0; j<cols; j++){
				if(tablero[i][j] != 0){
					if( i != 0 ){
						if(tablero[i-1][j] == 0)
							priority++;
					}
					if( i != fils ){
						if(tablero[i+1][j] == 0)
							priority++;
					}
					if( j != 0 ){
						if(tablero[i][j-1] == 0)
							priority++;
					}
					if( j != cols ){
						if(tablero[i][j+1] == 0)
							priority++;
					}
					Cell c =new Cell(i, j, tablero[i][j], priority);
					priority=0;
					order.offer(c);
				}
			}
		}
		return order;
	}
	
	

}
