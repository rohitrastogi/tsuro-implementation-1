package assignment3;

import java.util.ArrayList;

enum Color
{
	BLUE, RED, YELLOW, ORANGE, GREEN, PINK, PURPLE, BLACK;	
}

public class SPlayer {
	private ArrayList<Tile> myTiles;
	private Color color;
	private Position posn;
	
	public SPlayer(ArrayList<Tile> myTiles, Color color, Position posn){
		this.myTiles = myTiles;
		this.color = color;
		this.posn = posn;
	}
	
	
	// Getters and Setters 
	public ArrayList<Tile> getTiles() {
		return myTiles; 
	}
	
	public Position getPosn(){
		return posn;
	}
	
	public void addTile(Tile t){
		myTiles.add(t);
	}
}
