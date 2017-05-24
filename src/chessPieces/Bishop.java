package chessPieces;

/**
 * Group 105
 * @author Arifur Rahman
 * @author Monique Gordon
 **
 */
public class Bishop extends ChessPiece {
	
	
	public Bishop(String pos, String pieceColor){
		currPos = pos;
		color = pieceColor;
	}
	
	public String toString(){
		if (color.equals("white")) return "wB";
		return "bB";
	}

	
	/** Bishop path checker*/
	
	public boolean pathValid(String dest){
		
		//bishop can move any amount of spots diagonally
		
		int[] orderedPairCurrent = posStringToArray(this.currPos);
		int[] orderedPair = posStringToArray(dest);
		if(!validCoordinateCheck(orderedPair)){
			return false;
		}
		
		
		//so number and letter need to change for a diagonal movement
		
		if((orderedPair[0] == orderedPairCurrent[0]) || (orderedPair[1] == orderedPairCurrent[1])){
			return false;
		}
		else if((Math.abs(orderedPair[0]-orderedPairCurrent[0])) == (Math.abs(orderedPair[1]-orderedPairCurrent[1]))){
			return true;
		}
		
		return false;
	}
	
	
	
}