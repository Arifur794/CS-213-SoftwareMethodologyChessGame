package chessPieces;

/**
 * Group 105
 * @author Arifur Rahman	
 * @author Monique Gordon
 *
 */

public class Rook extends ChessPiece {
	
	public boolean move1;
	
	public Rook(String pos, String pieceColor){
		move1 = true;
		currPos = pos;
		color = pieceColor;
	}
	
	public String toString(){
		if (color.equals("white")) return "wR";
		return "bR";
	}
	
	/** rook path checker*/
	public boolean pathValid(String dest){
		//rook can move any amount of spots but only forward, backward, left, and right(no diagonal movements)
		
		int[] orderedPairCurrent = posStringToArray(this.currPos);
		int[] orderedPair = posStringToArray(dest);
		if(!validCoordinateCheck(orderedPair)){
			return false;
		}
		if(orderedPairCurrent[0] == orderedPair[0]){
			if(orderedPairCurrent[1] == orderedPair[1]){//same spot will not be valid
				return false;
			}
			
			
			
			//letter is the same and the number is different(forward or backward movement)
			else return true;
		}
		
		
		if(orderedPairCurrent[1] == orderedPair[1]){
		return true;	
		}
		
		return false;
	}
	
	public void setMove1Boolean(boolean move1Value){
		this.move1 = move1Value;
	}
	
	
}