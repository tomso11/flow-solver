package algorithms;

import java.util.PriorityQueue;

//Para la solucion aproximada pense en generar un arbol que parte de un tablero vac�o, elige un color y comienza a avanzar sobre ese color
//cada "step" del algoritmo sera un nodo distinto, en cada nodo, el color seleccionado avanzara de a un casillero a la vez.
//De esta manera
public class ApproxSolution {
	
	private int fils, cols;
	private int[] colors;
	private int[][] tablero;
	private PriorityQueue<Cell> order;
	private Sink[] sinks;
	
	public ApproxSolution(int fils, int cols, int[] colors, int[][] tablero, Sink[] sinks) {
		this.fils = fils;
		this.cols = cols;
		this.colors = colors;
		this.tablero = tablero;
		this.sinks= sinks;
	}
	
	// el metodo busca obtener el nodo/ color que tenga una menor variedad de movimientos
	// habria que arreglar que solo agarre el mejor de cada color, pero no se si el chequeo es worth
	
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
	// esta solucion itera siempre sobre el mismo color, ordenados segun la cantidad de movimientos posibles al principio
	// una forma mas eficiente quiza seria recorrer en todos los pasos la matriz y tomar el color con menor
	// cantidad de movimientos posibles y mover ese.
	
	public TableroControl searchSolution(double time){
		TableroControl t=new TableroControl(tablero, cols, fils, colors, sinks, null, 0);
		TableroControl[] children;
		TableroControl bestChild;
		TableroControl best=t;
		// esto no esta bien, el current deberia ser una propiedad del estado/tablero
		Cell current= new Cell(order.peek().getX(), order.peek().getY(), order.peek().getColor());
		while (time > 0){
			children=t.findChildren( current );
			if ( children == null ){ // se trabo o no tiene solucion
				
			}
			sortByScore(children);
			// aca podriamos chequear si el mejor queda de alguna manera estancado
			// chequeando dead-ends o bottle necks o colores inalcanzables
			bestChild=children[0];
			// aca habria que cambiar de current
			if( bestChild.isColorComplete(current) ){
				order.poll();
				current=new Cell(order.peek().getX(), order.peek().getY(), order.peek().getColor());
				// seleccionamos el proximo color
			}
			if ( bestChild.getScore() > best.getScore() ){
				best=bestChild;
			}
			if( bestChild.isSolved() ){
				return bestChild;
			}else if( bestChild.getEmptyCells() < best.getEmptyCells() ){
				best=bestChild;
			}	
		}
		return best;
	}
	
	public void sortByScore( TableroControl[] children ){ //ordenar eficientemente
		
	}
	
	public void climbSolution() {
		boolean flag;
		List<Integer> dir = new LinkedList<Integer>();
		int[][] tableroAux = tablero;
		String dir = "";
		boolean up, down, left, right;
		for(int k = 0; k < sinks.length; k ++) {
			for(int i = 0; i < fils; i ++) {
				for(int j = 0; j < cols; j ++) {
					flag = tableroAux[i][j] == -1 && j != sinks[k].getFirstX() && j != sinks[k].getFirstX() && i != sinks[k].getFirstY() && i != sinks[k].getFirstY();
					
					if(flag) {
						for (int l = i - 1; l > 0; l--) {
							if (tableroAux[i][j] != sinks[k].getColor() && tableroAux[i][j] != -1) {
								break;
							} else {
								up = true;
							}	
						}
						
						for (int l = i + 1; l < fils; l++) {
							if (tableroAux[i][j] != sinks[k].getColor() && tableroAux[i][j] != -1) {
								break;
							} else {
								down = true;
							}
						}

						for (int m = j - 1; m > 0; m--) {
							if (tableroAux[i][j] != sinks[k].getColor() && tableroAux[i][j] != -1) {
								break;
							} else {
								left = true;
							}
						}
						
						for (int m = j + 1; m < cols; m++) {
							if (tableroAux[i][j] != sinks[k].getColor() && tableroAux[i][j] != -1) {
								break;
							} else {
								right = true;
							}
						}
						
						dir.clean();
						
						if(up) {
							dir.add(1);
						}
						if(down) {
							dir.add(2);
						}
						if(left) {
							dir.add(3);
						}
						if(right) {
							dir.add(4);
						}
						
						if(dir.length >= 2) {
							dir.shuffle();
							
						}
					}
					
				
				}
					

}
