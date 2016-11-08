package algorithms;

public class Sink {
	
	private int x1, y1, x2, y2, color,pathLength;

	public Sink(int x, int y, int x2, int y2, int color) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.pathLength=0;
	}
	
	public Sink(int x, int y, int x2, int y2, int color, int pathLength) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.pathLength=pathLength;
	}

	public int getFirstX() {
		return x1;
	}

	public void setFirstX(int x1) {
		this.x1 = x1;
	}

	public int getFirstY() {
		return y1;
	}

	public void setFirstY(int y1) {
		this.y1 = y1;
	}

	public int getSecX() {
		return x2;
	}

	public void setSecX(int x2) {
		this.x2 = x2;
	}

	public int getSecY() {
		return y2;
	}

	public void setSecY(int y2) {
		this.y2 = y2;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	
	public int getPathLength() {
		return pathLength;
	}
	
	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}
	

}
