package algorithms;

import java.util.Collections;
import java.util.LinkedList;

import climb.Cell;
import climb.Sink;


public class HillClimb {
	private static int fillPath(int x, int y, int color, Cell target, int i, LinkedList<Cell> path) {
		if (x == target.getX()) {
			
			if (y < target.getY()) {
				for (int l = y; l < target.getY(); l++) {
					++i;
					path.add(i, new Cell(x, l, color));
				}
					
			}
			
			if (y > target.getY()) {
				for (int l = y; l > target.getY(); l--) {
					++i;
					path.add(i, new Cell(x, l, color));
				}
					
			}
		}
		
		
		if (y == target.getY()) {
			if (x < target.getX()) {
				for (int l = x; l < target.getX(); l++) {
					++i;
					path.add(i, new Cell(l, y, color));
				}	
			}
			if (y > target.getX()) {
				for (int l = x; l > target.getX(); l--) {
					++i;
					path.add(i, new Cell(l, y, color));
				}		
			}
		}
		
		return i;
	}
	
	
	private static int[][] removeRemainPath(LinkedList<Cell> cells, LinkedList<Cell> path, int tablero[][], int fils, int cols, int color, boolean remove) {

		LinkedList<Cell> subPath = new LinkedList<Cell>();

		
		Cell intersecA = cells.get(1);
		Cell intersecB = cells.get(2);
		Cell targetA = cells.get(0);
		Cell targetB = null;
		if (cells.size() == 4) {
			targetB = cells.get(3);
		}
		

		
		for (Cell current: path) {
			
			if (remove) {
				tablero[current.getX()][current.getY()] = -1;
				subPath.add(new Cell(current.getX(), current.getY(), color));
			}
			
			if (current.getX() == cells.get(1).getX() && current.getY() == cells.get(1).getY() || current.getX() == cells.get(2).getX() && current.getY() == cells.get(2).getY()) {
				if (remove){
					remove = false;
					tablero[current.getX()][current.getY()] = color;
					subPath.removeLast();
				} else {
					remove = true;
				}
				
			}
		}
		
		path.removeAll(subPath);
		

		
		boolean stop = false, limits = false, conditionA = false, conditionB = false, conditionInterA = false, conditionInterB = false;
		int x = 0, y = 0;

		
		for (int i = 0; i < path.size() && !stop; i++) {
			
			if (intersecA != null) {
				conditionInterA = path.get(i).getX() == intersecA.getX() && path.get(i).getY() == intersecA.getY();
			} else {
				conditionInterA = false;
			}
			
			if (intersecB != null) {
				conditionInterB = path.get(i).getX() == intersecB.getX() && path.get(i).getY() == intersecB.getY();
			} else {
				conditionInterB = false;
			}
			
			if (conditionInterA || conditionInterB) {
				int dirs[][] = {{1,0},{-1,0},{0,-1},{0,1}};
				int xAux = path.get(i).getX();
				int yAux = path.get(i).getY();
				
				if (conditionInterA) {
					intersecA = null;
				} else {
					intersecB = null;
				}
				
				for(int k = 0; k < dirs.length; k++) {	
					x = path.get(i).getX() + dirs[k][0];
					y = path.get(i).getY() + dirs[k][1];
					limits = x < fils && x >= 0 && y < cols && y >= 0;
					conditionA = x == targetA.getX() || y == targetA.getY();
					
					if (targetB != null) {
						conditionB = x == targetB.getX() || y == targetB.getY();
					}
					
					Cell aux = new Cell(x, y, color);
					
					if (limits && tablero[x][y] == color && conditionA && !path.contains(aux)) {	
//						if (targetB != null) {
//							if (conditionA) {
//								i = fillPath(x, y, color, targetA, i, path);
//								targetA = null;
//							} else {
//								i = fillPath(x, y, color, targetB, i, path);
//								targetB = null;
//							}
//						}
						
						i = fillPath(x, y, color, targetA, i, path);
						
						int a = cells.get(0).getX();
						int b = cells.get(0).getY();
						
						path.add(++i, new Cell(a, b, color));
						
						conditionA = false;
						conditionB = false;
						
						for(k = 0; k < dirs.length; k++) {	
							x = a + dirs[k][0];
							y = b + dirs[k][1];

							limits = x < fils && x >= 0 && y < cols && y >= 0;
							Cell target = intersecA == null ? intersecB : intersecA;
							conditionA = x == target.getX() || y == target.getY();
//							if (cells.size() == 3) {
//								condition = x == cells.get(2).getX() || y == cells.get(2).getY();
//							}
							aux = new Cell(x, y, color);
							conditionA = conditionA && !path.contains(aux);
							
							if (limits && tablero[x][y] == color && conditionA) {
								System.out.println("asfasf");
								i = fillPath(x, y, color, target, i, path);							
								
							}
														
						}
							
						
						stop = true;
						
					}
				
				
				}
				
			}
			
		}
		
		return tablero;
		
	}

	
	
	public static int[][] climbSolution(int[][] tablero, int fils, int cols, LinkedList<Sink> sinks) {
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
									
									if (dirs.size() == 1 && sinksCompleted) {
										if (!sideFound) {
											int way = -1;
											// buscar a la izquierda
											int l = i;
											while (l >= 0 && l < fils && !sideFound) {

												for (int m = j - 1; m >= 0 && l != i && !sideFound; m--) {
													if (tableroAux[i][m] != sinks.get(k).getColor() && tableroAux[i][l] != -1) {
														break;
													} else if (tableroAux[l][m] == sinks.get(k).getColor()) {
														cells.add(new Cell(l,m,sinks.get(k).getColor()));
														for (m = m + 1; m < j; m++) {
															tableroAux[l][m] = sinks.get(k).getColor();	
														}
														for (int t = i; t != l; t = t + way) {
															tableroAux[t][m] = sinks.get(k).getColor();
														}
														sideFound = true;
														dirs.pop();
														break;
													}
												}
												
												if (l == fils - 1) {
													way = 1;
													l = i;
												} 
												if (way == 1) {
													l--;
												} else {
													l++;
												}													
												
											}
																					
										}
									}
									
									
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
									
									Cell aux = null;
									
									if (dirs.size() == 1 && sinksCompleted) {
										System.out.println("asfsa");
										if (!sideFound) {
											int way = -1;
											// buscar a la derecha
											int l = i;
											while (l >= 0 && l < fils && !sideFound) {

												for (int m = j + 1; m < cols && l != i && !sideFound; m++) {
													if (tableroAux[i][m] != sinks.get(k).getColor() && tableroAux[i][l] != -1) {
														break;
													} else if (tableroAux[l][m] == sinks.get(k).getColor()) {
														System.out.println("aca andamo");
														cells.add(new Cell(l,m,sinks.get(k).getColor()));
														for (m = m - 1; m > j; m--) {
															tableroAux[l][m] = sinks.get(k).getColor();
														}
														System.out.println(l + " " + m);
														aux = new Cell(l,m,sinks.get(k).getColor());
//														for (int t = i; t != l; t = t + way) {
//															tableroAux[t][m] = sinks.get(k).getColor();
//														}
														//tableroAux[1][1] = sinks.get(k).getColor();
														sideFound = true;
														dirs.pop();
														break;
													}
												}
												
												if (l == fils - 1) {
													way = 1;
													l = i;
												} 
												if (way == 1) {
													l--;
												} else {
													l++;
												}													
												
											}
																					
										}
									}
									
									for (int l = j; l < cols; l++) {
										if (tableroAux[i][l] == sinks.get(k).getColor() && l != j) {
											cells.add(new Cell(i,l,sinks.get(k).getColor()));
											break;
										} else {
											tableroAux[i][l] = sinks.get(k).getColor();
										}
									}
									if (aux != null) {
										cells.add(aux);
									}
								}
							}
								
							removeRemainPath(cells, sinks.get(k).getPath(), tableroAux, fils, cols, sinks.get(k).getColor(), false);

							//salgo de ambos for y avanzo hacia el siguiente sink
							sameSink = false;
						}
					}	
				
				}
			}
		}
		return tableroAux;
	}
	
}
