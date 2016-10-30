package algorithms;

public class TableroControl {
	
	private int[][] tablero;
	private int x;
	private int y;
	private int[] colors;
	private Sink[] sinks;
	private Tablero[] children;
	private int emptyCells;
	private int score;
	private int costoToCome; // la cantidad de movimientos hasta llegar hasta aca
	
	
	public TableroControl(int[][] tablero,int x, int y, int[] colors, Sink[] sinks, Tablero[] children, int cost) {
		this.tablero = tablero;
		this.colors = colors;
		this.sinks = sinks;
		this.children = children;
		calcEmptyCells();
		this.costToCome=cost;
		

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

	public TableroControl[] findChildren( Cell c ) {
		int i=c.getX(),j=c.getY(), index=0;
		TableroControl[] ans;
		int forced=0;
		
		if ( i != y && tablero[i+1][j] == -1 ){ // no se pasa del limite y es un casillero vacio
			int[][] tabClone=tablero;
			tabClone[i+1][j]=c.getColor();
			ans[index++]=new Tablero(tabClone, x, y, colors, sinks, children, cost);
		}
		if ( i != 0 && tablero[i-1][j] == -1 ){ // no se pasa del limite y es un casillero vacio
			if(ans.lenght != 0){
				forced= 1;
			}
			int[][] tabClone=tablero;
			tabClone[i-1][j]=c.getColor();
			ans[index++]=new Tablero(tabClone, x, y, colors, sinks, children, cost);
		}
		if ( j != x && tablero[i][j+1] == -1 ){ // no se pasa del limite y es un casillero vacio
			if(ans.lenght != 0){
				forced= 1;
			}
			int[][] tabClone=tablero;
			tabClone[i][j+1]=c.getColor();
			ans[index++]=new Tablero(tabClone, x, y, colors, sinks, children, cost);
		}
		if ( j!= 0 && tablero[i][j-1] == -1 ){ // no se pasa del limite y es un casillero vacio
			if(ans.lenght != 0){
				forced= 1;
			}
			int[][] tabClone=tablero;
			tabClone[i][j-1]=c.getColor();
			ans[index++]=new Tablero(tabClone, x, y, colors, sinks, children, cost);
		}
		
		for( int k=0 ; k<index ; k++){
			ans[k].setScore(forced);
		}
		
		return ans;
		
	}
	
	public void calcEmptyCells(){
		int count = 0;
		for(int i=0; i<y; i++){
			for(int j=0; j<x ; j++){
				if(tablero[i][j] == -1){
					count++;
				}
			}
		}
		this.emptyCells=count;
	}
	
	public void setScore( int forced ){
		
		this.score=emptyCells+forced+cost;
		
	}
	
	public int getScore(){
		return score;
	}
	
	public boolean isColorComplete (Cell c){
		
		for(int i=0; i<sinks.length; i++ ){
			if( sinks[i].getColor() == c.getColor() ){ 
				if( sinks[i].getX() == c.getX() && sinks[i].getY() == c.getY() ) {
					if( (i != y && tablero[i+1][j] == c.getColor()) || (i != 0 && tablero[i-1][j] == c.getColor()) || (j != x && tablero[i][j+1] == c.getColor()) || (j!= 0 && tablero[i][j-1] == c.getColor()) )
						return true;
				}
			}
		}
		return false;
	}

	
}