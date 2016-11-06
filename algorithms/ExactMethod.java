package algorithms;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class ExactMethod {
	
	public ExactMethod() {
	}

	public void ExactSolution(Tablero tab, boolean print){
		long time = System.currentTimeMillis();
		Set<Tablero> solutions = new HashSet<Tablero>();
		Set<Tablero> parcialSolutions = new HashSet<Tablero>();
		solutions.add(tab);
		Tablero bestSolution = tab;
		int bestSolutionEmpty = (tab.getFils()*tab.getCols())-(tab.getColours()*2);
		Point[] sink = tab.getSinks();
		
		for (int i = 0 ; i < tab.getColours(); i+=2 ){
			for (Tablero each: solutions){
				parcialSolutions.addAll(findPath(each, sink[i], sink[i+1],tab.getColour(i/2),print));
			}
			solutions= parcialSolutions;
			parcialSolutions.removeAll(parcialSolutions);
		}
		
		for (Tablero each : solutions ){
			int eachEmpty = each.cellsEmpty();
			if ( eachEmpty < bestSolutionEmpty){
				bestSolution = each;
				bestSolutionEmpty = eachEmpty;
			}
		}

		System.out.println("Solution GameBoard:");
		//for (Tablero each : solutions){
			for (int x=0; x < 6; x++) {
				System.out.print("|");
				for (int y=0; y < 6 ; y++) {
					System.out.print (bestSolution.getTablero()[x][y]);
					if (y!=5) System.out.print("\t");
				}
				System.out.println("|");
			}
		//}

		
		time = System.currentTimeMillis() - time;
		System.out.println("El algoritmo encontro la solucion exacta en " + time/60000 " minutos," + (time%60000)/1000 + " segundos y " (time%1000) +" milisegundos.");
		System.out.println
		return;
	}

	public Set<Tablero> findPath(Tablero tablero,Point origin, Point destiny, int colour, boolean print){
		Set<Tablero> solutions = new HashSet<Tablero>();
		int [] movement = {-1,0,0,1,1,0,0,-1};		
		if (origin == destiny){
			solutions.add(tablero);
			return solutions;
		}
		
		for (int i = 0 ; i < 8 ; i+=2){
			Point cell = new Point ((int)origin.getX(),(int)origin.getY());
			cell.translate(movement[i], movement[i+1]);
				if( tablero.cellIsEmpty(cell)){
					if (print){
						MainFrame draw = new MainFrame(tablero.getFils(),tablero.getCols(),tablero.getTablero());
						draw.setVisible(true);
						try{
							Thread.sleep(100);
						}catch(InterruptedException ex){
							Thread.currentThread().interrupt();
						}
					}
					tablero.paintCell(cell,colour);
					solutions.addAll(findPath(tablero,cell,destiny,colour,print));
			}
		}
		return solutions;
	}
}
