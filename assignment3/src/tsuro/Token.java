package tsuro;

public class Token {
	private Position posn;
	private Color color;
	
	public Token(Color color){
		this.color = color;
	}
	
	public Position getPosition(){
		return posn;
	}
	
	public void setPosition(Position posn){
		this.posn = posn;
	}
	
	public Color getColor(){
		return color;
	}

}
