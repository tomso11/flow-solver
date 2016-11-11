package algorithms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class MainFrame extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private TableroView mTablero = new TableroView();
    
    MainFrame(int filas, int columnas, int[][] tablero) {
    	addKeyListener(this);
    	setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    	try {
            jbInit(filas,columnas,tablero);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit(int filas, int columnas, int[][] tablero) throws Exception {
        
        this.setLayout(new BorderLayout());
        this.setSize( new Dimension(650,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Free Flow Game Solver:");
        this.add(mTablero, BorderLayout.CENTER );
        mTablero.inicializar(tablero,filas,columnas);
                
    }

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_SPACE){
			System.out.println("Program Closed!");
			dispose();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
