package algorithms;

public class HCInput {

	private TableroControl tab;
	private Sink[] sinks;
	
	public HCInput(TableroControl tab, Sink[] sinks){
		this.tab= tab;
		this.sinks=sinks;
	}
	
	//Lee's Algorithm
	public void quickSolution(){
		int[][] grid=tab.getTablero();
		for(int i=0; i<sinks.length ; i++){
			int x1=sinks[i].getFirstX();
			int y1=sinks[i].getFirstY();
			int x2=sinks[i].getSecX();
			int y2=sinks[i].getSecY();
			grid=markMatrix(x1,y1,x2,y2,grid,sinks[i].getColor());
			// llamar hill climbing
		}
		//al terminar este for lo enviamos al hill climbing
	}

	private int[][] markMatrix(int x1, int y1, int x2, int y2, int[][] grid,int color) {
		if(x1==x2 && y1==y2){
			grid=markPath(x1,y1,x2,y2,grid,color);
			grid=clearGrid(grid);
			return grid;
		}
		 // solo marco si esta sin color
		if( x1 != 0 && grid[x1-1][y1] == -1){
			grid[x1-1][y1]=(grid[x1][y1]>10)? (grid[x1][y1]+1):(10); // si la anterior tiene numero le agrego peso, sino, comienzo el camino
			grid=markMatrix(x1-1,y1,x2,y2,grid, color);
		}
		if( x1 != tab.getX() && grid[x1+1][y1]== -1){
			grid[x1+1][y1]=(grid[x1][y1] > 10)? (grid[x1][y1]+1):(10);
			grid=markMatrix(x1+1,y1,x2,y2,grid, color);
		}
		if( y1 != 0 && grid[x1][y1-1]== -1){
			grid[x1][y1-1]=(grid[x1][y1] > 10)? (grid[x1][y1]+1):(10);
			grid=markMatrix(x1,y1-1,x2,y2,grid, color);
		}
		if( y1 != tab.getY() && grid[x1][y1+1]== -1){
			grid[x1][y1+1]=(grid[x1][y1]>10)? (grid[x1][y1]+1):(10);
			grid=markMatrix(x1,y1+1,x2,y2,grid, color);
		}
		return grid;
		
		// aca habria que mandarlo directo al hill climbing
	}

	private int[][] clearGrid(int[][] grid) {
		for(int i=0; i<tab.getY(); i++){
			for(int j=0 ; j<tab.getX(); j++){
				if(grid[i][j]>= 10){
					grid[i][j]=-1;
				}
			}
		}
		return grid;
	}

	private int[][] markPath(int x1, int y1, int x2, int y2, int[][] grid, int color) {
		int x=x2,y=y2;
		Cell min = new Cell(-1,-1,tab.getX() * tab.getY()); // celda a la que nos vamos a mover
		while( grid[x][y] >= 10 ){
			if( x != 0 && grid[x-1][y]>=10 ){
				if( grid[x-1][y] < min.getColor() ){
					min.setX(x-1);
					min.setY(y);
					min.setColor(grid[x-1][y]);
				}
			}
			if( x != tab.getX() && grid[x+1][y]>=10 ){
				if( grid[x+1][y] < min.getColor() ){
					min.setX(x+1);
					min.setY(y);
					min.setColor(grid[x+1][y]);
				}
			}
			if( y != 0 && grid[x][y-1]>=10 ){
				if( grid[x][y-1] < min.getColor() ){
					min.setX(x);
					min.setY(y-1);
					min.setColor(grid[x][y-1]);
				}
			}
			if( y != tab.getY() && grid[x][y+1]>=10 ){
				if( grid[x][y+1] < min.getColor() ){
					min.setX(x);
					min.setY(y+1);
					min.setColor(grid[x][y+1]);
				}
			}
			grid[x][y]=color; // como los colores van de 0 a 9 cuando llegue a 10 y lo pinte cortara la iteracion
			x=min.getX();
			y=min.getY();
		}
		return grid;
		
	}
	
	
	
}
