package chessPieces;

/**
 * Group 105
 * @author Arifur Rahman
 * @author Monique Gordon
 **
 */

public class Queen extends ChessPiece {
	
	public Queen(String pos, String pieceColor){
		currPos = pos;
		color = pieceColor;
	}
	
	public String toString(){
		if (color.equals("white")) return "wQ";
		return "bQ";
	}

	
	/** Queen path checker*/
	public boolean pathValid(String dest){
		
		int[] orderedPairCurrent = posStringToArray(this.currPos);
		int[] orderedPair = posStringToArray(dest);
		if(!validCoordinateCheck(orderedPair)){
			return false;
		}
		if(orderedPairCurrent[0] == orderedPair[0]){
			if(orderedPairCurrent[1] == orderedPair[1]){//Same spot will not be valid
				return false;
			}
			
			
			//however if letter is the same and number is different (forward/backward movement) then valid
			else return true;
		}
		
		
		if(orderedPairCurrent[1] == orderedPair[1]){
			//movement from side to side
			return true;	
			}
		
		
		else if((Math.abs(orderedPair[0]-orderedPairCurrent[0])) == (Math.abs(orderedPair[1]-orderedPairCurrent[1]))){
			//diagonal movement
			return true;
		}
		
		return false;
	}
	
}