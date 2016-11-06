package algorithms;

import java.util.LinkedList;

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
		if( (x1+1==x2 && y1==y2) || (x1-1==x2 && y1==y2) || (x1==x2 && y1-1==y2) || (x1==x2 && y1+1==y2) ){
			grid=markPath(x1,y1,x2,y2,grid,color);
			grid=clearGrid(grid);
			System.out.println("THIS PRINT");
			printMatrix(grid);
			return grid;
		}
		 // solo marco si esta sin color
		if( x1 != 0 && grid[x1-1][y1] == -1){
			grid[x1-1][y1]=(grid[x1][y1] >= 10)? (grid[x1][y1]+1):(10); // si la anterior tiene numero le agrego peso, sino, comienzo el camino 
			printMatrix(grid);
			grid=markMatrix(x1-1,y1,x2,y2,grid, color);
			printMatrix(grid);
		}
		if( x1 != tab.getX()-1 && grid[x1+1][y1]== -1){
			grid[x1+1][y1]=(grid[x1][y1] >= 10)? (grid[x1][y1]+1):(10);
			printMatrix(grid);
			grid=markMatrix(x1+1,y1,x2,y2,grid, color);
			printMatrix(grid);
		}printMatrix(grid);
		if( y1 != 0 && grid[x1][y1-1]== -1){
			grid[x1][y1-1]=(grid[x1][y1] >= 10)? (grid[x1][y1]+1):(10);
			printMatrix(grid);
			grid=markMatrix(x1,y1-1,x2,y2,grid, color);
			printMatrix(grid);
		}
		if( y1 != tab.getY()-1 && grid[x1][y1+1]== -1){
			grid[x1][y1+1]=(grid[x1][y1] >= 10)? (grid[x1][y1]+1):(10);
			printMatrix(grid);
			grid=markMatrix(x1,y1+1,x2,y2,grid, color);
			printMatrix(grid);
		}

		printMatrix(grid);
		return grid; // provisorio

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
			if( x != tab.getX()-1 && grid[x+1][y]>=10 ){
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
			if( y != tab.getY()-1 && grid[x][y+1]>=10 ){
				if( grid[x][y+1] < min.getColor() ){
					min.setX(x);
					min.setY(y+1);
					min.setColor(grid[x][y+1]);
				}
			}
			printMatrix(grid);
			grid[x][y]=color; // como los colores van de 0 a 9 cuando llegue a 10 y lo pinte cortara la iteracion
			x=min.getX();
			y=min.getY();
		}
		return grid;
		
	}
	
	public void printMatrix(int[][] grid){
		System.out.println("Matrix print:");
		for(int i=0; i<tab.getY(); i++){
			for(int j=0; j<tab.getX(); j++){
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Print end");
		
	}
	
	public static void main(String[] args) {
		int[][] grid ={{-1, 0, -1},{-1, -1, -1},{-1, -1, 0}};
		Sink sink=new Sink(0,1,2,2,0);
		Sink[] sinks={sink};
		int[] colors={0};
		TableroControl tab=new TableroControl(grid, 3, 3, colors, sinks);
		HCInput input=new HCInput(tab,sinks);
		//input.quickSolution();
		input.queueSolution();
		
	}
	
	public int[][] queueSolution(){
		LinkedList<Cell> queue = new LinkedList<Cell>();
		int[][] grid = tab.getTablero();
		boolean found=false;
		for(int i=0; i< sinks.length ; i++){ 
			Cell c=new Cell(sinks[i].getFirstX(),sinks[i].getFirstY(),sinks[i].getColor() );
			Cell tgt=new Cell(sinks[i].getSecX(),sinks[i].getSecY(),sinks[i].getColor() );
			System.out.println("las coordenadas de tgt son x: "+tgt.getX()+ " y: "+tgt.getY() );
			queue.add(c);
			while(!(queue.isEmpty()) ){
				System.out.println("vamos otra vez");
				Cell current= queue.poll();
				Cell[] neighbors=getNeighbors(current, grid, tgt);
				if(neighbors[0] != null){
					for(int j=0; j< neighbors.length && !found; j++){
						System.out.println("las coordenadas de este neigh son x: "+neighbors[j].getX()+ " y: "+neighbors[j].getY() );
						if(neighbors[j].equals(tgt)){
							grid=markPath(grid, tgt, c);
							found=true;
							queue=emptyQ(queue);
						}
						else{
							queue.add(neighbors[j]);
							System.out.println("encolo");
						}
					}
				} // falta un else ?
			}
			grid=clearGrid(grid);
			queue=emptyQ(queue);
			printMatrix(grid);
		}
		return grid;
	}

	private Cell[] getNeighbors(Cell current, int[][] grid, Cell tgt) {
		Cell[] arr = new Cell[3];
		int n=0;
		int x1=current.getX(),y1=current.getY();
		int x2=tgt.getX(), y2=tgt.getY();
		if( x1 != 0 && grid[x1-1][y1] == -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1-1][y1]=(current.getColor() >= 10)? (current.getColor()+1):(10); // si la anterior tiene numero le agrego peso, sino, comienzo el camino 
			arr[n++]=new Cell(x1-1,y1,(current.getColor() >= 10)? (current.getColor()+1):(10));
			printMatrix(grid);
		}
		if( x1 != tab.getX()-1 && grid[x1+1][y1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1+1][y1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr[n++]=new Cell(x1+1,y1,(current.getColor() >= 10)? (current.getColor()+1):(10));
			printMatrix(grid);
		}
		if( y1 != 0 && grid[x1][y1-1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1][y1-1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr[n++]=new Cell(x1,y1-1,(current.getColor() >= 10)? (current.getColor()+1):(10));
			printMatrix(grid);
		}
		if( y1 != tab.getY()-1 && grid[x1][y1+1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1][y1+1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr[n++]=new Cell(x1,y1+1,(current.getColor() >= 10)? (current.getColor()+1):(10));
			printMatrix(grid);
		}
		if ( (x1-1== x2 && y1 == y2) || (x1+1== x2 && y1 == y2) || (x1== x2 && y1-1 == y2) || (x1== x2 && y1+1 == y2)){
			System.out.println("found tgt!");
			arr[n++]=new Cell(x2,y2,tgt.getColor());
		}
		return arr;
	}

	private int[][] markPath(int[][] grid, Cell tgt, Cell source) {
		int x=tgt.getX(),y=tgt.getY();
		int x2=source.getX(), y2=source.getY();
		int pathLength=0;
		Cell min = new Cell(-1,-1,tab.getX() * tab.getY()+10); // celda a la que nos vamos a mover
		System.out.println("las coordenadas de este tgt son x: "+tgt.getX()+ " y: "+tgt.getY() );
		while( min.getX() != x2 && min.getY() != y2){ //grid[x][y] >= 10 || ( x==tgt.getX() && y==tgt.getY() ) 
			System.out.println("dentro del path");
			if( x != 0 && grid[x-1][y]>=10 ){
				if( grid[x-1][y] < min.getColor() ){
					min.setX(x-1);
					min.setY(y);
					min.setColor(grid[x-1][y]);
				}
			}
			if( x != tab.getX()-1 && grid[x+1][y]>=10 ){
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
			if( y != tab.getY()-1 && grid[x][y+1]>=10 ){
				if( grid[x][y+1] < min.getColor() ){
					min.setX(x);
					min.setY(y+1);
					min.setColor(grid[x][y+1]);
				}
			}
			printMatrix(grid);
			System.out.println(tgt.getColor());
			grid[min.getX()][min.getY()]=tgt.getColor(); // como los colores van de 0 a 9 cuando llegue a 10 y lo pinte cortara la iteracion
			x=min.getX();
			y=min.getY();
			System.out.println("las coordenadas de current son x: "+min.getX()+ " y: "+min.getY() +" y su color "+min.getColor());
			printMatrix(grid);
			pathLength++;
		}
		System.out.println("largo del camino: "+ pathLength);
		return grid;
		
	}

	private LinkedList<Cell> emptyQ(LinkedList<Cell> queue) {
		while(! queue.isEmpty() ){
			queue.remove();
		}
		return queue;
	}
}
