package chessPieces;

/**
 * Group 105
 * @author Arifur Rahman
 * @author Monique Gordon
 *
 */
public class King extends ChessPiece {
	
	public boolean move1;
	
	public King(String pos, String pieceColor){
		move1 = true;
		currPos = pos;
		color = pieceColor;
	}
	
	public String toString(){
		if (color.equals("white")) return "wK";
		return "bK";
	}
	
	/** king path checker*/
	public boolean pathValid(String dest){

		int[] orderedPairCurrent = posStringToArray(this.currPos);
		int[] orderedPair = posStringToArray(dest);
		int same = orderedPairCurrent[1];
		
		///king can move one spot at a time in either direction
		int plusOne = orderedPairCurrent[1]+1;
		int minusOne = orderedPairCurrent[1]-1;
		
		
		if(!validCoordinateCheck(orderedPair)){
			return false;
		}
		
		
		
		if(orderedPair[0] == orderedPairCurrent[0]+1 || orderedPair[0] == orderedPairCurrent[0]-1){
			if(orderedPair[1] == same || orderedPair[1] == plusOne || orderedPair[1] == minusOne){
				return true;
			}
		}
		
		else if(orderedPair[0] == orderedPairCurrent[0]){
			if(orderedPair[1] == orderedPairCurrent[1]){ // king movement to same spot is not valid
				return false;
			}
			else if(orderedPair[1] == plusOne || orderedPair[1] == minusOne){
				return true;
			}
		}
		
		return false;
	}
	
	public void setMove1Boolean(boolean move1Value){
		this.move1 = move1Value;
	}
	
	
	
}