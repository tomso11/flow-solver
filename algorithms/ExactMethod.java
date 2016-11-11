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
		
		time = System.currentTimeMillis() - time;
		
		if (bestSolution.equals(tab)){
			System.out.println("\nThe entered Gameboard has no solution!");
		}else {
			System.out.println("Exact Solution GameBoard:\n");
			for (int x=0; x < tab.getFils(); x++) {
				System.out.print("|");
				for (int y=0; y < tab.getCols(); y++) {
					System.out.print (bestSolution.getTablero()[x][y]);
					if (y!=tab.getCols()) System.out.print("\t");
				}
				System.out.println("|");
			}
			
			MainFrame draw = new MainFrame(bestSolution.getFils(),bestSolution.getCols(),bestSolution.getTablero());
			draw.setVisible(true);
			try{
			Thread.sleep(200);
			}catch(InterruptedException ex){
			Thread.currentThread().interrupt();
			}
		}
					
		System.out.println("\nThe algorithm found the exact solution in " + time/60000 + " minutes, " + (time%60000)/1000 + " seconds and " + (time%1000) + " milliseconds.");
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
