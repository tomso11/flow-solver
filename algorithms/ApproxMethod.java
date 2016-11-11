package algorithms;

import java.util.LinkedList;

public class ApproxMethod {
	
	public ApproxMethod(){
		
	}
	
	public void approxSolution(TableroControl tab, long time, boolean progress){
		TableroControl bestTab=null;
		TableroControl auxTab;
		Lee lee=new Lee();
		HillClimb hc=new HillClimb();
		int[][] grid;
		int[][] best;
		long took=0;
		while(time> 0 && !tab.isSolved() ){
				System.out.println("enter");
				long start=System.currentTimeMillis();
				tab=lee.getSolution(tab,time,progress);
				auxTab=hc.climbSolution(tab.getTablero(), tab.getX(), tab.getY(), tab.getSinks());
				tab.setTablero(auxTab.getTablero());
				lee.printMatrix(auxTab.getTablero(),tab.getX(), tab.getY());

				long end=System.currentTimeMillis();
				took=end-start;
				System.out.println("Current time: " +time);
				time=time-took;
		}
		System.out.println("Approx Solution GameBoard:\n");
		for (int x=0; x < tab.getX(); x++) {
			System.out.print("|");
			for (int y=0; y < tab.getY(); y++) {
				System.out.print (bestTab.getTablero()[x][y]);
				if (y!=tab.getY()) System.out.print("\t");
			}
			System.out.println("|");
		}
		System.out.println("\nEl algoritmo encontro la solucion exacta en " + time/60000 + " minutos," + (time%60000)/1000 + " segundos y " + (time%1000) +" milisegundos.");
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
