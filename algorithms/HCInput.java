package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HCInput {

	private TableroControl tab;
	private Sink[] sinks;
	
	public HCInput(TableroControl tab, Sink[] sinks){
		this.tab= tab;
		this.sinks=sinks;
	}
	

	private int[][] clearGrid(int[][] grid) {
		for(int i=0; i<tab.getX(); i++){
			for(int j=0 ; j<tab.getY(); j++){
				if(grid[i][j]>= 10){
					grid[i][j]=-1;
				}
			}
		}
		return grid;
	}

	
	public void printMatrix(int[][] grid){
		System.out.println("Matrix print:");
		for(int i=0; i<tab.getX(); i++){
			for(int j=0; j<tab.getY(); j++){
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Print end");
		
	}
	
	public static void main(String[] args) {
		int[][] grid ={{1, 0, -1, -1, -1},{-1, -1, -1, -1, -1},{-1, -1, -1, -1, -1}, {-1,-1,-1,-1,-1} , {-1,-1,-1,-1,-1}, {2,1,-1,2,0}};
		Sink sink=new Sink(0,1,5,4,0);
		Sink sink2=new Sink(0,0,5,1,1);
		Sink sink3=new Sink(5,0,5,3,2);
		Sink[] sinks={sink,sink2,sink3};
		int[] colors={0,1,2};
		TableroControl tab=new TableroControl(grid, 6, 5, colors, sinks);
		HCInput input=new HCInput(tab,sinks);
		//input.quickSolution();
		input.queueSolution();
		
	}
	
	public int[][] queueSolution(){
		LinkedList<Cell> queue = new LinkedList<Cell>();
		int[][] grid = tab.getTablero();
		boolean found=false;
		List<Cell> neighbors=new ArrayList<Cell>();
		for(int i=0; i< sinks.length ; i++){ 
			if(sinks[i].getPathLength() == 0){
				Cell c=new Cell(sinks[i].getFirstX(),sinks[i].getFirstY(),sinks[i].getColor() );
				Cell tgt=new Cell(sinks[i].getSecX(),sinks[i].getSecY(),sinks[i].getColor() );
				System.out.println("las coordenadas de tgt son x: "+tgt.getX()+ " y: "+tgt.getY() );
				queue.add(c);
				while(!(queue.isEmpty()) ){
					System.out.println("vamos otra vez");
					Cell current= queue.poll();
					System.out.println("busco vecinos de  x: "+current.getX()+ " y: "+current.getY() );
					neighbors=getNeighbors(current, grid, tgt);
					if(!(neighbors.isEmpty()) ){
						for(int j=0; j< neighbors.size() && !found; j++){
							System.out.println("las coordenadas de este neigh son x: "+neighbors.get(j).getX()+ " y: "+neighbors.get(j).getY() );
							if((neighbors.get(j)).equals(tgt)){
								grid=markPath(grid, tgt, c);
								found=true;
								queue=emptyQ(queue);
							}
							else{
								queue.add(neighbors.get(j));
								System.out.println("encolo");
							}
						}
					} // falta un else ?
				}
				grid=clearGrid(grid);
				System.out.println("wot");
				queue=emptyQ(queue);
				printMatrix(grid);
				found=false;
			}
		}
		return grid;
	}

	private List<Cell> getNeighbors(Cell current, int[][] grid, Cell tgt) {
		List<Cell> arr = new ArrayList<Cell>();
		int x1=current.getX(),y1=current.getY();
		int x2=tgt.getX(), y2=tgt.getY();
		if( x1 != 0 && grid[x1-1][y1] == -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1-1][y1]=(current.getColor() >= 10)? (current.getColor()+1):(10); // si la anterior tiene numero le agrego peso, sino, comienzo el camino 
			arr.add(new Cell(x1-1,y1,(current.getColor() >= 10)? (current.getColor()+1):(10)));
			printMatrix(grid);
		}
		if( x1 != tab.getX()-1 && grid[x1+1][y1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1+1][y1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr.add(new Cell(x1+1,y1,(current.getColor() >= 10)? (current.getColor()+1):(10)));
			printMatrix(grid);
		}
		if( y1 != 0 && grid[x1][y1-1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1][y1-1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr.add(new Cell(x1,y1-1,(current.getColor() >= 10)? (current.getColor()+1):(10)));
			printMatrix(grid);
		}
		if( y1 != tab.getY()-1 && grid[x1][y1+1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1][y1+1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr.add(new Cell(x1,y1+1,(current.getColor() >= 10)? (current.getColor()+1):(10)));
			printMatrix(grid);
		}
		if ( (x1-1== x2 && y1 == y2) || (x1+1== x2 && y1 == y2) || (x1== x2 && y1-1 == y2) || (x1== x2 && y1+1 == y2)){
			System.out.println("found tgt!");
			arr.add(new Cell(x2,y2,tgt.getColor()));
		}
		for(Cell c:arr){
			System.out.println("coordenadas x: "+c.getX()+" y : "+c.getY());
		}
		return arr;
	}

	private int[][] markPath(int[][] grid, Cell tgt, Cell source) {
		int x=tgt.getX(),y=tgt.getY();
		int x2=source.getX(), y2=source.getY();
		int pathLength=0;
		LinkedList<Cell> path=new LinkedList<Cell>();
		path.add(tgt); // agregamos la celda por la que empezamos
		Cell min = new Cell(-1,-1,tab.getX() * tab.getY()+10); // celda a la que nos vamos a mover
		boolean check=!( (min.getX() == x2) && (min.getY() == y2) );
		System.out.println("las coordenadas de este tgt son x: "+tgt.getX()+ " y: "+tgt.getY() );
		while( check ){ //grid[x][y] >= 10 || ( x==tgt.getX() && y==tgt.getY() ) 
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
			if ( (x-1== x2 && y == y2) || (x+1== x2 && y == y2) || (x== x2 && y-1 == y2) || (x== x2 && y+1 == y2)){
				System.out.println("found tgt!");
				min.setX(x2);
				min.setY(y2);
				min.setColor(tgt.getColor());
			}
			printMatrix(grid);
			System.out.println(tgt.getColor());
			grid[min.getX()][min.getY()]=tgt.getColor(); // como los colores van de 0 a 9 cuando llegue a 10 y lo pinte cortara la iteracion
			x=min.getX();
			y=min.getY();
			path.add(new Cell(x,y,tgt.getColor())); // agregamos la celda al camino
			System.out.println("las coordenadas de current son x: "+min.getX()+ " y: "+min.getY() +" y su color "+min.getColor());
			printMatrix(grid);
			pathLength++;
			System.out.println("largo del camino: "+ pathLength);
			check=!( (min.getX() == x2) && (min.getY() == y2) );
		}
		System.out.println("largo del camino: "+ pathLength);
		if(pathLength > 0){
			addPath(source, pathLength, path); // agrega la longitud y el camino al objeto
		}
		
		
		return grid;
		
	}

	private void addPath(Cell source, int pathLength, LinkedList<Cell> path) {
		int x=source.getX(), y=source.getY();
		for(int i=0; i<sinks.length; i++){
			if( ( x==sinks[i].getFirstX() && y==sinks[i].getFirstY() ) || ( x==sinks[i].getSecX() && y==sinks[i].getSecY() ) ){
				sinks[i].setPathLength(pathLength);
				sinks[i].setPath(path);
			}
		}
		
	}


	private LinkedList<Cell> emptyQ(LinkedList<Cell> queue) {
		while(! queue.isEmpty() ){
			queue.remove();
		}
		return queue;
	}
//	//Lee's Algorithm
//	public void quickSolution(){
//		int[][] grid=tab.getTablero();
//		for(int i=0; i<sinks.length ; i++){
//			int x1=sinks[i].getFirstX();
//			int y1=sinks[i].getFirstY();
//			int x2=sinks[i].getSecX();
//			int y2=sinks[i].getSecY();
//			grid=markMatrix(x1,y1,x2,y2,grid,sinks[i].getColor());
//			// llamar hill climbing
//		}
//		//al terminar este for lo enviamos al hill climbing
//	}
	
//	private int[][] markMatrix(int x1, int y1, int x2, int y2, int[][] grid,int color) {
//		if( (x1+1==x2 && y1==y2) || (x1-1==x2 && y1==y2) || (x1==x2 && y1-1==y2) || (x1==x2 && y1+1==y2) ){
//			grid=markPath(x1,y1,x2,y2,grid,color);
//			grid=clearGrid(grid);
//			System.out.println("THIS PRINT");
//			printMatrix(grid);
//			return grid;
//		}
//		 // solo marco si esta sin color
//		if( x1 != 0 && grid[x1-1][y1] == -1){
//			grid[x1-1][y1]=(grid[x1][y1] >= 10)? (grid[x1][y1]+1):(10); // si la anterior tiene numero le agrego peso, sino, comienzo el camino 
//			printMatrix(grid);
//			grid=markMatrix(x1-1,y1,x2,y2,grid, color);
//			printMatrix(grid);
//		}
//		if( x1 != tab.getX()-1 && grid[x1+1][y1]== -1){
//			grid[x1+1][y1]=(grid[x1][y1] >= 10)? (grid[x1][y1]+1):(10);
//			printMatrix(grid);
//			grid=markMatrix(x1+1,y1,x2,y2,grid, color);
//			printMatrix(grid);
//		}printMatrix(grid);
//		if( y1 != 0 && grid[x1][y1-1]== -1){
//			grid[x1][y1-1]=(grid[x1][y1] >= 10)? (grid[x1][y1]+1):(10);
//			printMatrix(grid);
//			grid=markMatrix(x1,y1-1,x2,y2,grid, color);
//			printMatrix(grid);
//		}
//		if( y1 != tab.getY()-1 && grid[x1][y1+1]== -1){
//			grid[x1][y1+1]=(grid[x1][y1] >= 10)? (grid[x1][y1]+1):(10);
//			printMatrix(grid);
//			grid=markMatrix(x1,y1+1,x2,y2,grid, color);
//			printMatrix(grid);
//		}
//
//		printMatrix(grid);
//		return grid; // provisorio
//
//		// aca habria que mandarlo directo al hill climbing
//	}
	
//	private int[][] markPath(int x1, int y1, int x2, int y2, int[][] grid, int color) {
//		int x=x2,y=y2;
//		Cell min = new Cell(-1,-1,tab.getX() * tab.getY()); // celda a la que nos vamos a mover
//		while( grid[x][y] >= 10 ){
//			if( x != 0 && grid[x-1][y]>=10 ){
//				if( grid[x-1][y] < min.getColor() ){
//					min.setX(x-1);
//					min.setY(y);
//					min.setColor(grid[x-1][y]);
//				}
//			}
//			if( x != tab.getX()-1 && grid[x+1][y]>=10 ){
//				if( grid[x+1][y] < min.getColor() ){
//					min.setX(x+1);
//					min.setY(y);
//					min.setColor(grid[x+1][y]);
//				}
//			}
//			if( y != 0 && grid[x][y-1]>=10 ){
//				if( grid[x][y-1] < min.getColor() ){
//					min.setX(x);
//					min.setY(y-1);
//					min.setColor(grid[x][y-1]);
//				}
//			}
//			if( y != tab.getY()-1 && grid[x][y+1]>=10 ){
//				if( grid[x][y+1] < min.getColor() ){
//					min.setX(x);
//					min.setY(y+1);
//					min.setColor(grid[x][y+1]);
//				}
//			}
//			printMatrix(grid);
//			grid[x][y]=color; // como los colores van de 0 a 9 cuando llegue a 10 y lo pinte cortara la iteracion
//			x=min.getX();
//			y=min.getY();
//		}
//		return grid;
//		
//	}
}
