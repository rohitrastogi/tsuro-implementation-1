package tsuro;

import java.util.*;

public class Tile {
	private Path[] paths;
	// How many times this tile has been rotated
	// Rotations are always clockwise, and go to a maximum of 3 
	private int rotation;
	
	// The number of positions around the edge of a tile 
	private static final int NUM_POSITIONS = 8; 
	public static final int NUM_SIDES = 4;
	
	public Tile(Path[] paths, int rotation){
		//TODO where do we catch the illegalArgumentException?
		//check path length (must have 4 input/output pairs)
		if (paths.length != 4){
			throw new IllegalArgumentException("Tile does not have 4 Paths!");
		}
		//checks if each input/output point is in the set [0-7] and appears only once
		for (int i = 0; i< NUM_POSITIONS; i++){
			boolean seen = false;
			for (Path path: paths){
				if (path.contains(i)){
					seen = true;
				}
			}
			if (!seen){
				throw new IllegalArgumentException("Tile has invalid input/output paths!");
			}
		}
		this.paths = paths;
		this.rotation = rotation;
	}
	
	public Tile(Path[] paths){
		this(paths, 0);
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
	
	@Override
	public String toString(){
		return "Rotation: " + this.rotation + ", " + "Paths: " + paths[0].toString() + ", " + paths[1].toString() + ", " 
				+ paths[2].toString() + ", " + paths[3].toString();
	}
	
	// returns how many functionally different rotations this tile has
	public int getSymmetry() {
		// go through each rotation, check its first position and see if it changes 
		Set<String> symmetry = new HashSet<String>(); 
		for (int i = 0; i < NUM_SIDES; i++) {
			Tile rotated = this.rotate(i);
			int output1 = rotated.getOutPath(0);  // just get the out path for the first position 
			int output2 = rotated.getOutPath(1);
			String output = Integer.toString(output1) + Integer.toString(output2);
			symmetry.add(output);
		}
		return symmetry.size(); 
	}
	
	// Getters and Setters 
	public Path[] getPaths() {
		return paths; 
	}
}
