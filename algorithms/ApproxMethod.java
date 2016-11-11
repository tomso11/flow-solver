package algorithms;

import java.util.LinkedList;

public class ApproxMethod {
	
	public ApproxMethod(){
		
	}
	
	public void approxSolution(TableroControl tab, long time, boolean progress){
		TableroControl tabClimb;
		TableroControl tabClimbMax = null;
		Lee lee=new Lee();
		int[][] grid;
		int[][] best;
		long took=0;
		while(time> 0 && !tab.isSolved() ){
			System.out.println("enter");
			long start=System.currentTimeMillis();
			// Escala hacia la solucion
			// Falta agregarle el progress y time
			tabClimb=HillClimb.climbSolution(tab.getTablero(), tab.getX(), tab.getY(), tab.getSinks());
			
			// Si algun camino no estan conectado corre el Lee de nuevo para ver si puede conectar los que falta 
			if(!tabClimb.isPathsConected()) {
				tabClimb=lee.getSolution(tabClimb,time,progress);
				tabClimbMax=tabClimb;
			// Si estan todos conectados pregunta si el puntaje de la ultima escalada es mejor que el de maximo registrado
			} else {
				if(tabClimb.getScore() >= tabClimbMax.getScore()) {
					tabClimbMax = tabClimb;
				}
			}
	    	System.out.println();
			for(int p = 0; p < tabClimb.getX(); p++) {
				for(int o = 0; o < tabClimb.getY(); o++) {
					if(tabClimb.getTablero()[p][o] == -1) {
						System.out.print(" " + " ");
					} else {
						System.out.print(tabClimb.getTablero()[p][o] + " ");
					}
				}
				System.out.println();
			}
			System.out.println();
//			lee.printMatrix(auxTab.getTablero(),tab.getX(), tab.getY());
			long end=System.currentTimeMillis();
			took=end-start;
			System.out.println("Current time: " +time);
		}
		System.out.println("Approx Solution GameBoard:\n");
		for (int x=0; x < tab.getX(); x++) {
			System.out.print("|");
			for (int y=0; y < tab.getY(); y++) {
				System.out.print (tabClimbMax.getTablero()[x][y]);
				if (y!=tab.getY()) System.out.print("\t");
			}
			System.out.println("|");
		}
		System.out.println("\nThe algorithm found the exact solution in " + time/60000 + " minutes," + (time%60000)/1000 + " seconds and " + (time%1000) +" milliseconds.");
	}

	public static void main(String[] args) {
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
		ApproxMethod app = new ApproxMethod();
		long time=30 * 10000;
		app.approxSolution(tab, time, false);
	}
}
