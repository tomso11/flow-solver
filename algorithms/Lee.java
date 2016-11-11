package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lee {

	public Lee(){
	
	}
	

	public int[][] clearGrid(int[][] grid, int x, int y) {
		for(int i=0; i<x; i++){
			for(int j=0 ; j<y; j++){
				if(grid[i][j]>= 10){
					grid[i][j]=-1;
				}
			}
		}
		return grid;
	}

	
	public void printMatrix(int[][] grid, int x, int y){
		System.out.println("Matrix print:");
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Print end");
		
	}
	
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		int[][] grid ={{1, 0, -1, -1, -1},{-1, -1, -1, -1, -1},{-1, -1, -1, -1, -1}, {-1,-1,-1,-1,-1} , {-1,-1,-1,-1,-1}, {2,1,-1,2,0}};
		Sink sink=new Sink(0,1,5,4,0);
		Sink sink2=new Sink(0,0,5,1,1);
		Sink sink3=new Sink(5,0,5,3,2);
		LinkedList<Sink> sinks=new LinkedList<Sink>();
		sinks.add(sink);
		sinks.add(sink2);
		sinks.add(sink3);
		int[] colors={0,1,2};
		TableroControl tab=new TableroControl(grid, 6, 5, colors, sinks);
		Lee input=new Lee();
		long time=3000*10000;
		//input.quickSolution();
		input.getSolution(tab,time,true);
		long endTime=System.currentTimeMillis();
		System.out.println("El proceso tomo : "+ (endTime-startTime) +" ms");
		
	}
	
	public TableroControl getSolution(TableroControl tab, long time, boolean progress){
		long start=System.currentTimeMillis(); // tiempo en el que empieza
		LinkedList<Cell> queue = new LinkedList<Cell>();
		int[][] grid = tab.getTablero();
		boolean found=false;
		List<Cell> neighbors=new ArrayList<Cell>();
		for(int i=0; i< tab.getSinks().size() && time > 0 ; i++){ 
			
			if(tab.getSinks().get(i).getPathLength() == 0){
				Cell c=new Cell(tab.getSinks().get(i).getFirstX(),tab.getSinks().get(i).getFirstY(),tab.getSinks().get(i).getColor() );
				Cell tgt=new Cell(tab.getSinks().get(i).getSecX(),tab.getSinks().get(i).getSecY(),tab.getSinks().get(i).getColor() );
				System.out.println("las coordenadas de tgt son x: "+tgt.getX()+ " y: "+tgt.getY() );
				queue.add(c);
				while(!(queue.isEmpty()) ){
					System.out.println("vamos otra vez");
					Cell current= queue.poll();
					System.out.println("busco vecinos de  x: "+current.getX()+ " y: "+current.getY() );
					neighbors=getNeighbors(current, grid, tgt, tab.getX(), tab.getY() );
					if(!(neighbors.isEmpty()) ){
						for(int j=0; j< neighbors.size() && !found; j++){
							System.out.println("las coordenadas de este neigh son x: "+neighbors.get(j).getX()+ " y: "+neighbors.get(j).getY() );
							if((neighbors.get(j)).equals(tgt)){
								grid=markPath(tab, grid, tgt, c, progress);
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
				grid=clearGrid( grid, tab.getX(), tab.getY());
				System.out.println("wot");
				queue=emptyQ(queue);
				printMatrix(grid,tab.getX(),tab.getY());
				found=false;
			}
			long end = System.currentTimeMillis(); // para calcular el tiempo
			time=time-(end-start);
			if(progress){
				MainFrame draw = new MainFrame(tab.getX(),tab.getY(),grid);
				draw.setVisible(true);
				draw.setVisible(true);
				try{
					Thread.sleep(200);
				}catch(InterruptedException ex){
					Thread.currentThread().interrupt();
				}
				draw.dispose();
			}
		}
		tab.setTablero(grid);
		return tab;
	}

	private List<Cell> getNeighbors(Cell current, int[][] grid, Cell tgt, int x, int y) {
		List<Cell> arr = new ArrayList<Cell>();
		int x1=current.getX(),y1=current.getY();
		int x2=tgt.getX(), y2=tgt.getY();
		if( x1 != 0 && grid[x1-1][y1] == -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1-1][y1]=(current.getColor() >= 10)? (current.getColor()+1):(10); // si la anterior tiene numero le agrego peso, sino, comienzo el camino 
			arr.add(new Cell(x1-1,y1,(current.getColor() >= 10)? (current.getColor()+1):(10)));
			printMatrix(grid,x,y);
		}
		if( x1 != x-1 && grid[x1+1][y1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1+1][y1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr.add(new Cell(x1+1,y1,(current.getColor() >= 10)? (current.getColor()+1):(10)));
			printMatrix(grid,x,y);
		}
		if( y1 != 0 && grid[x1][y1-1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1][y1-1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr.add(new Cell(x1,y1-1,(current.getColor() >= 10)? (current.getColor()+1):(10)));
			printMatrix(grid,x,y);
		}
		if( y1 != y-1 && grid[x1][y1+1]== -1){
			//System.out.println("el color es "+current.getColor());
			grid[x1][y1+1]=(current.getColor() >= 10)? (current.getColor()+1):(10);
			arr.add(new Cell(x1,y1+1,(current.getColor() >= 10)? (current.getColor()+1):(10)));
			printMatrix(grid,x,y);
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

	private int[][] markPath(TableroControl tab, int[][] grid, Cell tgt, Cell source, boolean progress) {
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
			printMatrix(grid,x,y);
			System.out.println(tgt.getColor());
			grid[min.getX()][min.getY()]=tgt.getColor(); // como los colores van de 0 a 9 cuando llegue a 10 y lo pinte cortara la iteracion
			x=min.getX();
			y=min.getY();
			path.add(new Cell(x,y,tgt.getColor())); // agregamos la celda al camino
			System.out.println("las coordenadas de current son x: "+min.getX()+ " y: "+min.getY() +" y su color "+min.getColor());
			printMatrix(grid,x,y);
			pathLength++;
			System.out.println("largo del camino: "+ pathLength);
			check=!( (min.getX() == x2) && (min.getY() == y2) );
			// hacer el print en consola
			if(progress){
				MainFrame draw = new MainFrame(tab.getX(),tab.getY(),grid);
				draw.setVisible(true);
				draw.setVisible(true);
				try{
					Thread.sleep(200);
				}catch(InterruptedException ex){
					Thread.currentThread().interrupt();
				}
				draw.dispose();
			}
			
		}
		System.out.println("largo del camino: "+ pathLength);
		if(pathLength > 0){
			addPath(tab, source, pathLength, path); // agrega la longitud y el camino al objeto
		}
		
		return grid;
		
	}

	private void addPath(TableroControl tab,Cell source, int pathLength, LinkedList<Cell> path) {
		int x=source.getX(), y=source.getY();
		for(int i=0; i<tab.getSinks().size(); i++){
			if( ( x==tab.getSinks().get(i).getFirstX() && y==tab.getSinks().get(i).getFirstY() ) || ( x==tab.getSinks().get(i).getSecX() && y==tab.getSinks().get(i).getSecY() ) ){
				tab.getSinks().get(i).setPathLength(pathLength);
				tab.getSinks().get(i).setPath(path);
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
//			int x1=sinks.get(i).getFirstX();
//			int y1=sinks.get(i).getFirstY();
//			int x2=sinks.get(i).getSecX();
//			int y2=sinks.get(i).getSecY();
//			grid=markMatrix(x1,y1,x2,y2,grid,sinks.get(i).getColor());
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
