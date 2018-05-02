package tsuro;

public class Path {
	int val1;
	int val2;
	
	public Path(int val1, int val2){
		this.val1 = val1;
		this.val2 = val2;
	}
	
	public int getVal1(){
		return val1;
	}
	
	public int getVal2(){
		return val2;
	}
	
	public boolean contains(int value){
		return (val1 == value || val2 == value);
	}
	
	public int getEndpoint(int value){
		if (value == val1)
			return val2;
		else if (value == val2)
			return val1;
		else  //treat as error code
			return -1;
	}
	
	// Tuples are considered equal if both of their values are equivalent (and in the same position) 
	@Override 
	public boolean equals(Object obj) {
		Path t = (Path) obj; 
		
		return ((t.val1 == this.val1) && (t.val2 == this.val2));
	}
	
	@Override
	public String toString(){
		return "(" + this.val1 + ", " + this.val2 + ")";
	}
}
