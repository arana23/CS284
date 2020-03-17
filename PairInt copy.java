package general;

public class PairInt {
	private int x;
	private int y;
	
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
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
	
	public boolean equals(Object p)
	{
		if (p.getClass() == this.getClass()){
            return this.x == ((PairInt)p).getX() && this.y == ((PairInt)p).getY();
        }
        return false;

	}
	
	public String toString() {
		 return "(" + this.x + "," + this.y + ")";
	}
	
	public PairInt copy() {
		PairInt temp=new PairInt(this.x,this.y);
		return temp;
	}
	
}
