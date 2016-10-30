package TPE;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private Tablero mTablero = new Tablero();
    
    MainFrame(int filas, int columnas, char[][] tablero) {
        try {
            jbInit(filas,columnas,tablero);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit(int filas, int columnas, char[][] tablero) throws Exception {
        
        this.setLayout(new BorderLayout());
        this.setSize( new Dimension(650,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Free Flow Game Solver:");
        this.add(mTablero, BorderLayout.CENTER );
        mTablero.setNumeroDeColumnas(columnas);
        mTablero.setNumeroDeFilas(filas);
        mTablero.inicializar(tablero);
                
    }
}
