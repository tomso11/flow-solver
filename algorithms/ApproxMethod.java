package algorithms;

import java.util.LinkedList;

public class ApproxMethod {
	
	public ApproxMethod(){
		
	}
	
	public void approxSolution(TableroControl tab, long time, boolean progress){
		TableroControl tabClimb;
		TableroControl tabClimbMax = null;
		long timeBest=0;
		Lee lee=new Lee();
		int[][] grid;
		int[][] best;
		long took=0;
		tab=lee.getSolution(tab,time,progress);
		long end;
		long start=System.currentTimeMillis();
		long auxT1 = 0, auxT2 = 0;
		while(time > took && !tab.isSolved() ){
			System.out.println("enter");
			// Escala hacia la solucion
			// Falta agregarle el progress y time
			auxT1 = System.currentTimeMillis();
			tabClimb=HillClimb.climbSolution(tab.getTablero(), tab.getX(), tab.getY(), tab.getSinks(), progress);
			auxT2 = System.currentTimeMillis();
			// Me dijo se el metodo climbSolution me tomo mas tiempo del permitido dentro del ciclo
			if(auxT2 - auxT1 + took >= time) {
				break;
			}
			// Si algun camino no estan conectado corre el Lee de nuevo para ver si puede conectar los que falta 
			if(!tabClimb.isPathsConected()) {
				auxT1 = System.currentTimeMillis();
				tabClimb=lee.getSolution(tabClimb,time,progress);
				auxT2 = System.currentTimeMillis();
				// Me fijo si el metodo getSolution me tomo mas tiempo del permitido dentro del ciclo
				if(auxT2 - auxT1 + took >= time) {
					break;
				}
				tabClimbMax=tabClimb;
			// Si estan todos conectados pregunta si el puntaje de la ultima escalada es mejor que el de maximo registrado
			} else {
				if(tabClimb.getScore() >= tabClimbMax.getScore()) {
					tabClimbMax = tabClimb;
					timeBest=took;
				}
			}

			end=System.currentTimeMillis();
			took=end-start;
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
		System.out.println("\nThe algorithm found the exact solution in " + timeBest/60000 + " minutes," + (timeBest%60000)/1000 + " seconds and " + (timeBest%1000) +" milliseconds.");
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
