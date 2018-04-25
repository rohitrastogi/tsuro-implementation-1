package assignment3;

import java.util.Arrays;

public class Tile {
	private Path[] paths;
	// How many times this tile has been rotated
	// Rotations are always clockwise, and go to a maximum of 3 
	private int rotation;
	
	// The number of positions around the edge of a tile 
	private static final int NUM_POSITIONS = 8; 
	public static final int NUM_SIDES = 4;
	
	public Tile(Path[] paths, int rotation){
		this.paths = paths;
		this.rotation = rotation;
	}
	
	public Tile(Path[] paths){
		this.paths = paths;
		this.rotation = 0;
	}
	
	public Tile rotate(int r){
		return new Tile(this.paths, r);
	}
	
	// Gets the output point of a Tile, given an input point 
	// Points are values 0-7 that represent the eight possible positions a token can occupy on a tile 
	public int getOutPath(int inPath){
		// Input points are always in what we call "World Coordinates", that is, they always correspond to 
		// the positions on a tile as such: 
//		     0  1
//		   --------
//		 7 |      | 2
//		   |      |
//		 6 |      | 3
//		   --------
//		     5  4
		// This means that the first step is to translate these "World" positions into the proper position for 
		// our possibly rotated tile. 
		int translatedInput = inPath - ((NUM_POSITIONS / NUM_SIDES) * rotation); 
		if (translatedInput < 0) {
			translatedInput += NUM_POSITIONS; 
		}
		
		// Now that we've translated our coordinates, find the endpoint of the corresponding path
		int output = -1; 
		for (Path path : paths){
			if (path.contains(translatedInput)){
				output = path.getEndpoint(translatedInput);
			}
		}
		// If output is still -1, something went wrong 
		if (output < 0) return output; 
		
		// Finally, translate the output point into world coordinates and return it 
		int translatedOutput = output + ((NUM_POSITIONS / NUM_SIDES) * rotation); 
		if (translatedOutput > (NUM_POSITIONS - 1)) {
			translatedOutput -= NUM_POSITIONS; 
		}
		return translatedOutput;
	}
	
	@Override 
	public boolean equals(Object obj) {
		Tile t = (Tile) obj; 
		
		return (Arrays.equals(t.paths, this.paths) && (t.rotation == this.rotation));
	}
	
	// Getters and Setters 
	public Path[] getPaths() {
		return paths; 
	}
}
