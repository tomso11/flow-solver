package algorithms;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TableroView extends JPanel implements ComponentListener , ActionListener {
    
	private static final long serialVersionUID = 1L;
	private JButton mTitle = null;
	private JButton[][] mCasillas = null ;
    private int mNumeroDeFilas;
    private int mNumeroDeColumnas;
    private int mSeparacion = 2 ;
    private int mAlturaInicio = 30 ;
    
    public void inicializar(int[][] tablero,int fils, int cols) {
    	mNumeroDeFilas = fils;
    	mNumeroDeColumnas = cols;
        mCasillas = new JButton[mNumeroDeFilas][mNumeroDeColumnas];
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
        	for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
        		JButton temp = new JButton();
        		temp.addActionListener(this);
        		temp.setText("");                            
        		mCasillas[fila][columna] = temp;                        
                this.add(temp);
                mCasillas[fila][columna].setOpaque(true);
                this.pintarCasilla(fila, columna, tablero[fila][columna]);
            }
        }
        mTitle = new JButton ("Free Flow Game Solver:");
        mTitle.setForeground(Color.BLACK);
        add(mTitle);

    }
    
    public void acomodar() {
        
        int ancho = this.getWidth();
        int alto = this.getHeight()- mAlturaInicio * 3 - 50;
        int dimensionMenor = Math.min( ancho , alto );
        int altoTitle = alto/10;
        int anchoDeCasilla = dimensionMenor / mNumeroDeColumnas ; 
        int altoDeCasilla = dimensionMenor / mNumeroDeFilas ;
        int xOffset = (ancho - dimensionMenor) / 2 ; 
        int yOffset = (alto - dimensionMenor) / 2 ; 
        
        JButton title = mTitle;
        title.setBounds(0,0,ancho,altoTitle);
        title.setFont(new java.awt.Font("Tahoma", 0, dimensionMenor/15)); 
   
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
               for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                  JButton temp = mCasillas[fila][columna] ;
                  temp.setBounds(xOffset + columna * anchoDeCasilla , yOffset + fila * altoDeCasilla + altoTitle * 2 , anchoDeCasilla - mSeparacion, altoDeCasilla - mSeparacion );
               }
        }
    }
    
    public TableroView() {        
        this.setBackground(Color.BLACK);
        this.addComponentListener(this);
        this.setLayout(null);              
    }

    public void setNumeroDeFilas(int mNumeroDeFilas) {
        this.mNumeroDeFilas = mNumeroDeFilas;
    }

    public int getNumeroDeFilas() {
        return mNumeroDeFilas;
    }

    public void setNumeroDeColumnas(int mNumeroDeColumnas) {
        this.mNumeroDeColumnas = mNumeroDeColumnas;
    }

    public int getNumeroDeColumnas() {
        return mNumeroDeColumnas;
    }
    
    public void pintarCasilla(int fila, int columna, int color){
    	switch(color){
    		
    		case 0: 
                mCasillas[fila][columna].setBackground(Color.WHITE);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 1: 
                mCasillas[fila][columna].setBackground(Color.YELLOW);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 2: 
                mCasillas[fila][columna].setBackground(Color.DARK_GRAY);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 3: 
                mCasillas[fila][columna].setBackground(Color.RED);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 4: 
                mCasillas[fila][columna].setBackground(Color.MAGENTA);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 5: 
                mCasillas[fila][columna].setBackground(Color.BLUE);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 6: 
                mCasillas[fila][columna].setBackground(Color.CYAN);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 7: 
                mCasillas[fila][columna].setBackground(Color.GREEN);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 8: 
                mCasillas[fila][columna].setBackground(Color.GRAY);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		case 9: 
                mCasillas[fila][columna].setBackground(Color.PINK);
                mCasillas[fila][columna].setOpaque(true);
                break;
    		default: 
    			mCasillas[fila][columna].setBackground(Color.BLACK);
                mCasillas[fila][columna].setOpaque(true);
                break;
    	}
    }
    
    public void componentResized(ComponentEvent e) {
        this.acomodar();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }
    public void actionPerformed(ActionEvent e) {        
    }
}
