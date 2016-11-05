package algorithms;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TableroView extends JPanel implements ComponentListener , ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel mTitle = null;
	private JButton[][] mCasillas = null ;
	private JButton mBusEx = null;
	private JButton mBusAp = null;
    	private int mNumeroDeFilas = 6 ;
    	private int mNumeroDeColumnas = 6 ;
    	private int mSeparacion = 2 ;
    	private int mAlturaBotones = 50 ;
    	private int mAlturaInicio = 30 ;
    
    public TableroView() {        
        this.setBackground(Color.BLACK);
        this.setLayout(null);              
    }
    
    
    public void inicializar(int[][] tablero) {
        mCasillas = new JButton[mNumeroDeFilas][mNumeroDeColumnas];
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
        	for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
        		JButton temp = new JButton();
        		temp.addActionListener(this);
        		temp.setText("[" + fila + "," + columna + "]");                            
        		mCasillas[fila][columna] = temp;                        
                this.add(temp);
                mCasillas[fila][columna].setOpaque(true);
                this.pintarCasilla(fila, columna, tablero[fila][columna]);
            }
        }
        mCasillas[3][3].setOpaque(true);
        mCasillas[3][3].setBackground(Color.YELLOW);
        mTitle = new JLabel ("'Free Flow Game Solver'");
        mTitle.setForeground(Color.WHITE);
        add(mTitle);

        mBusEx = new JButton("Busqueda Exacta");
        add(mBusEx);
        
        mBusAp = new JButton("Busqueda Aproximada");
        add(mBusAp);
    }
    public void acomodar() {
        
        int ancho = this.getWidth();
        int alto = this.getHeight()- mAlturaInicio * 3 - mAlturaBotones;
        int dimensionMenor = Math.min( ancho , alto );
        int altoTitle = alto/15;
        int altoBotones = alto/12;
        int anchoBotones = ancho/3;
        int anchoDeCasilla = dimensionMenor / mNumeroDeColumnas ; 
        int altoDeCasilla = dimensionMenor / mNumeroDeFilas ;
        int xOffset = (ancho - dimensionMenor) / 2 ; 
        int yOffset = (alto - dimensionMenor) / 2 ; 
        
        JLabel title = mTitle;
        title.setBounds(0,0,ancho,altoTitle);
        title.setFont(new java.awt.Font("Tahoma", 0, dimensionMenor/15)); 
        
        JButton busEx = mBusEx;
        busEx.setBounds(0, altoTitle*2, anchoBotones, altoBotones);
        busEx.setFont(new java.awt.Font("Tahoma", 0, dimensionMenor/30));
        
        JButton busAp = mBusAp;
        busAp.setBounds(anchoBotones*2, altoTitle*2, anchoBotones, altoBotones);
        busAp.setFont(new java.awt.Font("Tahoma", 0, dimensionMenor/30));
   
        for( int fila = 0 ; fila < mNumeroDeFilas ; fila ++ ) {
               for( int columna = 0 ; columna < mNumeroDeColumnas ; columna ++ ) {
                  JButton temp = mCasillas[fila][columna] ;
                  temp.setBackground(Color.BLACK);
                  temp.setBounds(xOffset + columna * anchoDeCasilla , yOffset + fila * altoDeCasilla +  altoBotones + altoTitle * 3 , anchoDeCasilla - mSeparacion, altoDeCasilla - mSeparacion );
               }
        }
    }

    public void componentResized(ComponentEvent e) {
        this.acomodar();
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
    		case '0': 
                mCasillas[fila][columna].setForeground(Color.WHITE);
                break;
    		case '1': 
                mCasillas[fila][columna].setForeground(Color.YELLOW);
                break;
    		case '2': 
                mCasillas[fila][columna].setForeground(Color.ORANGE);
                break;
    		case '3': 
                mCasillas[fila][columna].setForeground(Color.RED);
                break;
    		case '4': 
                mCasillas[fila][columna].setForeground(Color.MAGENTA);
                break;
    		case '5': 
                mCasillas[fila][columna].setForeground(Color.BLUE);
                break;
    		case '6': 
                mCasillas[fila][columna].setForeground(Color.CYAN);
                break;
    		case '7': 
                mCasillas[fila][columna].setForeground(Color.GREEN);
                break;
    		case '8': 
                mCasillas[fila][columna].setForeground(Color.GRAY);
                break;
    		case '9': 
                mCasillas[fila][columna].setForeground(Color.PINK);
                break;
    	}
    }

	public void actionPerformed(ActionEvent arg0) {}

	public void componentHidden(ComponentEvent arg0) {}

	public void componentMoved(ComponentEvent arg0) {}

	public void componentShown(ComponentEvent arg0) {}
}
