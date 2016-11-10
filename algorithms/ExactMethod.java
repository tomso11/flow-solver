package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ExactMethod {
	
	public ExactMethod() {
	}

	public void ExactSolution(Tablero tab, boolean print){
		long time = System.currentTimeMillis();
		List<Tablero> solutions = new ArrayList<Tablero>();
		List<Tablero> finalSolution = new ArrayList<Tablero>();
		Point[] sink = tab.getSinks();
		Tablero bestSolution = tab;
		int bestSolutionEmpty = (tab.getFils()*tab.getCols())-(tab.getColours()*2);
		solutions.add(tab);
		
		for (int i = 0 ; i < tab.getColours()*2; i+=2 ){
			System.out.println(tab.getColour(i/2));
			System.out.println(solutions.size());
			System.out.println(finalSolution.size());
			
			//if (tab.getColour(i/2) == 3){
			//	solutions.get(2);
			//for (Tablero each : solutions){
			//	for (int x=0; x < 6; x++) {
			//		System.out.print("|");
			//		for (int y=0; y < 6 ; y++) {
			//			System.out.print (each.getTablero()[x][y]);
			//			if (y!=5) System.out.print("\t");
			//		}
			//		System.out.println("|");
			//	}
			//}
			//}
			
			for (Tablero each: solutions){
				findPath(each, sink[i],null ,sink[i+1],tab.getColour(i/2),print,finalSolution);
			}
			solutions.clear();
			solutions.addAll(finalSolution);
			finalSolution.clear();;
		}
		for (Tablero each : solutions ){
			int eachEmpty = each.cellsEmpty();
			if ( eachEmpty < bestSolutionEmpty){
				bestSolution = each;
				bestSolutionEmpty = eachEmpty;
			}
		}

		System.out.println("Solution GameBoard:");
			for (int x=0; x < 6; x++) {
				System.out.print("|");
				for (int y=0; y < 6 ; y++) {
					System.out.print (bestSolution.getTablero()[x][y]);
					if (y!=5) System.out.print("\t");
				}
				System.out.println("|");
			}
					
		time = System.currentTimeMillis() - time;
		System.out.println("El algoritmo encontro la solucion exacta en " + time/60000 + " minutos," + (time%60000)/1000 + " segundos y " + (time%1000) +" milisegundos.");
		return;
	}

	public void findPath(Tablero tablero,Point origin,Point current, Point destiny, int colour, boolean print,List<Tablero> finalSolution){
		int [] movement = {-1,0,0,1,1,0,0,-1};		
		Point cell = new Point();
		if (current == null){
			current = origin;
		}
		
		for (int i = 0 ; i < 8 ; i+=2){
			cell = new Point ((int)current.getX(),(int)current.getY());
			cell.translate(movement[i], movement[i+1]);
				if( tablero.cellIsEmpty(cell)){
					if (print){
						MainFrame draw = new MainFrame(tablero.getFils(),tablero.getCols(),tablero.getTablero());
						draw.setVisible(true);
						try{
							Thread.sleep(200);
						}catch(InterruptedException ex){
							Thread.currentThread().interrupt();
						}
						draw.dispose();
					}
					tablero.paintCell(cell,colour);
					findPath(tablero,origin,cell,destiny,colour,print,finalSolution);
				}else{
					if (cell.equals(destiny) ){
							//MainFrame draw = new MainFrame(tablero.getFils(),tablero.getCols(),tablero.getTablero());
							//draw.setVisible(true);
							//draw.setVisible(true);
							//try{
							//	Thread.sleep(100);
							//}catch(InterruptedException ex){
							//	Thread.currentThread().interrupt();
							//}
							//draw.dispose();	
						Tablero oneSolution = tablero.clone();
						finalSolution.add(oneSolution);
						if(!origin.equals(current))
							tablero.paintCell(current, -1);
						return;
					}
					
				}
		}
		if (!origin.equals(current) && !destiny.equals(current))
			tablero.paintCell(current, -1);
		return;
	}
}


//Abrir ventana tablero!
//MainFrame draw = new MainFrame(tablero.getFils(),tablero.getCols(),tablero.getTablero());
//draw.setVisible(true);
//draw.setVisible(true);
//try{
//	Thread.sleep(200);
//}catch(InterruptedException ex){
//	Thread.currentThread().interrupt();
//}
//draw.dispose();

//Imprimir tablero en consola.
//for (Tablero each : solutions){
//	for (int x=0; x < 6; x++) {
//		System.out.print("|");
//		for (int y=0; y < 6 ; y++) {
//			System.out.print (each.getTablero()[x][y]);
//			if (y!=5) System.out.print("\t");
//		}
//		System.out.println("|");
//	}
//}
