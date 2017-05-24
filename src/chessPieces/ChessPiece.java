package chessPieces;

/**
 * Group 105
 * @author Arifur Rahman	
 * @author Monique Gordon
 *
 */

public class ChessPiece {
	
	public String currPos;
	public String color;
	
	public int[] posStringToArray(String pos){		
		int[] orderedPair = new int[2];		
		orderedPair[0] = -1;		
		orderedPair[1] = -1;		
		 			
		pos = pos.toLowerCase();	
		 			
		if (pos.length() != 2) return orderedPair;		
		
		int letterToInteger = pos.charAt(0)-'a'+1;		
		orderedPair[0] = letterToInteger;		
		orderedPair[1] = Integer.parseInt(pos.charAt(1)+"");		
		 			
		return orderedPair;				
	}
	
	
	public boolean pathValid(String dest){
		return false;
	}
	
	
	public void setCurrentPosition(String pos){
		currPos = pos;
	}
	
	
	public void setColor(String color){
		this.color = color;
	}
	
	
	public boolean validCoordinateCheck(int[] orderedPair){
		
		if(orderedPair[0] < 1 || orderedPair[0] > 8 || orderedPair[1] < 1 || orderedPair[1] > 8){
			
			
			return false;
		}
		return true;
	}
	
	
}