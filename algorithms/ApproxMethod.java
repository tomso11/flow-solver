package algorithms;

import java.util.LinkedList;

public class ApproxMethod {
	
	public ApproxMethod(){
		
	}
	
	public void approxSolution(TableroControl tab, long time, boolean progress){
		
		Lee lee=new Lee();
		HillClimb hc=new HillClimb();
		int[][] grid;
		int[][] best;
		long took=0;
		System.out.println(time + "   " + took+ "   "+ tab.isSolved() );
		while(time-took > 0 && !tab.isSolved() ){
				System.out.println("enter");
				long start=System.currentTimeMillis();
				tab=lee.getSolution(tab,time,progress);
				grid=hc.climbSolution(tab.getTablero(), tab.getX(), tab.getY(), tab.getSinks());
				tab.setTablero(grid);
				lee.printMatrix(grid,tab.getX(), tab.getY());
				long end=System.currentTimeMillis();
				took=end-start;
				System.out.println("Current time: " +time);
		}
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
		long time=300000 * 10000000;
		app.approxSolution(tab, time, false);
	}
}
