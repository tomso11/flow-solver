package algorithms;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

public class ExactSolution {
	
	private int fils, cols;
	private int[] colors;
	private int[][] tablero;
	private Sink[] sinks; // endpoints con color y localizacion
	
	public ExactSolution(int fils, int cols, int[] colors, int[][] tablero) {
		this.fils = fils;
		this.cols = cols;
		this.colors = colors;
		this.tablero = tablero;
	}

	Model model = new Model( "exact "); // creamos el modelo
	IntVar[][] cellColor = model.intVarMatrix(fils, cols, 0, colors.length); // color de cada una de las celdas
	IntVar[][] cellDir = model.intVarMatrix(fils, cols, 0, 7); // todas las celdas tienen direccion menos los sinks
	
	
	

}
