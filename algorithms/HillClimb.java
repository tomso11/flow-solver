package algorithms;

import java.util.Collections;
import java.util.LinkedList;




public class HillClimb {
	
	
	public static int[][] removeRemainPath(LinkedList<Cell> cells, Cell current, Cell prev, int tablero[][], int fils, int cols, int color, boolean remove) {

		if(remove) {
			tablero[current.getX()][current.getY()] = -1;
		}
			
		if(current.getX() == cells.get(1).getX() && current.getY() == cells.get(1).getY() || current.getX() == cells.get(2).getX() && current.getY() == cells.get(2).getY()) {
			if(remove){
				remove = false;
				tablero[current.getX()][current.getY()] = color;
			} else {
				remove = true;
			}
			
		}
		
		int dirs[][] = {{1,0},{-1,0},{0,-1},{0,1}};
		
		for(int i = 0; i < dirs.length; i++) {
			int x = current.getX() + dirs[i][0];
			int y = current.getY() + dirs[i][1];
			boolean limits = x < fils && x >= 0 && y < cols && y >= 0;
			if(limits && tablero[x][y] == color) {
				if(prev == null || (prev.getX() != x || prev.getY() != y)) {
					if(remove){
						boolean condition = x != cells.get(0).getX() && y != cells.get(0).getY();
						if (cells.size() == 4) {
							condition = condition && x != cells.get(3).getX() && y != cells.get(3).getY();
						}
						if(condition) {
							return removeRemainPath(cells, new Cell(x,y,-1), current, tablero, fils, cols, color, remove);
						}
					} else {
						return removeRemainPath(cells, new Cell(x,y,-1), current, tablero, fils, cols, color, remove);
					}

				}
			}
		}
		
		return tablero;
	}

	
	
	public static TableroControl climbSolution(int[][] tablero, int fils, int cols, LinkedList<Sink> sinks) {
		boolean sinksCompleted = false;
		LinkedList<String> dirs = new LinkedList<String>();
		LinkedList<Cell> cells = new LinkedList<Cell>();
		boolean sameSink = true;
		int[][] tableroAux = tablero;
		boolean up = false, down = false, left = false, right = false;
		
		for (int k = 0; k < sinks.size(); k++) {
			sameSink = true;
			for (int i = 0; i < fils && sameSink; i++) {
				for (int j = 0; j < cols && sameSink; j++) {		
					if (tableroAux[i][j] == -1) {
						for (int l = i - 1; l >= 0; l--) {
							if (tableroAux[l][j] != sinks.get(k).getColor() && tableroAux[l][j] != -1) {
								break;
							} else if(tableroAux[l][j] == sinks.get(k).getColor()) {
								up = true;
								break;
							}	
						}
						
						for (int l = i + 1; l < fils; l++) {
							if (tableroAux[l][j] != sinks.get(k).getColor() && tableroAux[l][j] != -1) {
								break;
							} else if(tableroAux[l][j] == sinks.get(k).getColor()){
								down = true;
								break;
							}
						}

						for (int l = j - 1; l >= 0; l--) {
							if (tableroAux[i][l] != sinks.get(k).getColor() && tableroAux[i][l] != -1) {
								break;
							} else if(tableroAux[i][l] == sinks.get(k).getColor()){
								left = true;
								break;
							}
						}
						
						for (int l = j + 1; l < cols; l++) {
							if (tableroAux[i][l] != sinks.get(k).getColor() && tableroAux[i][l] != -1) {
								break;
							} else if(tableroAux[i][l] == sinks.get(k).getColor()){
								right = true;
								break;
							}
						}
						
						dirs.clear();
						
						if (up) {
							dirs.push("up");
						}
						if (down) {
							dirs.push("down");
						}
						if (left) {
							dirs.push("left");
						}
						if (right) {
							dirs.push("right");
						}
						
						//Me quedo con 2 direcciones de todas las que tuve y las mezclo (tengo que elegir 2 al azar de todas las que encontre)
						if (dirs.size() >= 2) {
							cells.clear();
							cells.add(new Cell(i,j,sinks.get(k).getColor()));
							Collections.shuffle(dirs);
							while(dirs.size() > 2) {
								dirs.remove(2);
							}
							String dir;
							boolean sideFound = false;
							while(!dirs.isEmpty()) {
								dir = dirs.pop();
								
								if (dir == "up") {
									for (int l = i; l >= 0; l--) {
										if (tableroAux[l][j] == sinks.get(k).getColor() && l != i) {
											cells.add(new Cell(l,j,sinks.get(k).getColor()));
											break;
										} else {
											tableroAux[l][j] = sinks.get(k).getColor();
										}
									}
										
								}
								
								if (dir == "down") {
									for (int l = i; l < fils; l++) {
										if (tableroAux[l][j] == sinks.get(k).getColor() && l != i) {
											cells.add(new Cell(l,j,sinks.get(k).getColor()));
											break;
										} else {
											tableroAux[l][j] = sinks.get(k).getColor();
										}
									}
										
								}
								
								if (dir == "left") {									
									for (int l = j; l >= 0; l--) {
										if (tableroAux[i][l] == sinks.get(k).getColor() && l != j) {
											cells.add(new Cell(i,l,sinks.get(k).getColor()));
											break;
										} else {
											tableroAux[i][l] = sinks.get(k).getColor();
										}
									}
										
								}
								
								if (dir == "right") {
									for (int l = j; l < cols; l++) {
										if (tableroAux[i][l] == sinks.get(k).getColor() && l != j) {
											cells.add(new Cell(i,l,sinks.get(k).getColor()));
											break;
										} else {
											tableroAux[i][l] = sinks.get(k).getColor();
										}
									}
								}
							}
							removeRemainPath(cells, new Cell(sinks.get(k).getFirstX(), sinks.get(k).getFirstY(), sinks.get(k).getColor()), null, tableroAux, fils, cols, sinks.get(k).getColor(), false);

							//salgo de ambos for y avanzo hacia el siguiente sink
							sameSink = false;

						}
						up = false;
						down = false;
						right = false;
						left = false;
					}	
				
				}
			}
		}
	

		return new TableroControl(tableroAux, fils, cols, null, sinks);
	}
	
}
