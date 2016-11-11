package algorithms;

import java.util.LinkedList;

public class ApproxMethod {
	
	public ApproxMethod(){
		
	}
	
		public void approxSolution(TableroControl tab, long time, boolean progress){
		TableroControl tabClimb= tab;
		TableroControl tabClimbMax = tab;
		long timeBest=0;
		Lee lee=new Lee();
		long took=0;
		AtomicInteger t = new AtomicInteger((int)time);
		tabClimb=lee.getSolution(tabClimb,t,progress);
		long end;
		long start=System.nanoTime();
		long auxT1 = 0, auxT2 = 0;
		
		while(time > took && !tab.isSolved() ){
			System.out.println(took);
			// Escala hacia la solucion
			auxT2 = System.nanoTime(); 
			tabClimb=HillClimb.climbSolution(tabClimb.getTablero(), tabClimb.getX(), tabClimb.getY(), tabClimb.getSinks(), progress);
			// Si algun camino no estan conectado corre el Lee de nuevo para ver si puede conectar los que falta 
			if(!tabClimb.isPathsConected()) {
				System.out.println(timeBest);
				t.set((int)(time - took));
				tabClimb=lee.getSolution(tabClimb,t,progress);
				took+=t.get();
				tabClimbMax=tabClimb;
			// Si estan todos conectados pregunta si el puntaje de la ultima escalada es mejor que el de maximo registrado
			} else {
				System.out.println("sfsagas");
				if(timeBest == 0) {
					timeBest=took;
				}
				auxT1 = System.nanoTime();
				if((auxT2 - auxT1)/1000000 + took >= time) {
					break;
				}
				if(tabClimb.getScore() >= tabClimbMax.getScore()) {
					tabClimbMax = tabClimb;
				}
			}
			if(progress){
				MainFrame draw = new MainFrame(tabClimb.getX(),tabClimb.getY(),tabClimb.getTablero());
				draw.setVisible(true);
				try{
					Thread.sleep(200);
				}catch(InterruptedException ex){
					Thread.currentThread().interrupt();
				}
				draw.dispose();
			}
			end= System.nanoTime();
			took=(end-start)/1000000;
		}
		MainFrame draw = new MainFrame(tabClimbMax.getX(),tabClimbMax.getY(),tabClimbMax.getTablero());
		draw.setVisible(true);
		System.out.println("Approx Solution GameBoard:\n");
		for (int x=0; x < tab.getX(); x++) {
			System.out.print("|");
			for (int y=0; y < tab.getY(); y++) {
				System.out.print (tabClimbMax.getTablero()[x][y]);
				if (y!=tab.getY()) System.out.print("\t");
			}
			System.out.println("|");
		}
		if (timeBest != 0) {
			System.out.println("\nThe algorithm found a solution in " + timeBest/60000 + " minutes," + (timeBest%60000)/1000 + " seconds and " + (timeBest%1000) +" milliseconds.");			
		}
	}


	
}
