package algorithms;


// falta el cmp
public class Cell {
	private int x, y, color, priority;
	
	public Cell(int x, int y, int color){
		this.x=x;
		this.y=y;
		this.color=color;
	}
	
	public Cell(int x, int y, int color, int priority){
		this.x=x;
		this.y=y;
		this.color=color;
		this.priority=priority;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (color != other.color)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
