package chessPieces;

/**
 * Group 105
 * @author Arifur Rahman
 * @author Monique Gordon
 **
 */

public class Knight extends ChessPiece {
	
	public Knight(String pos, String pieceColor){
		currPos = pos;
		color = pieceColor;
	}
	
	
	public String toString(){
		if (color.equals("white")) return "wN";
		return "bN";
	}
	
	/** knight path checker*/
	public boolean pathValid(String dest){
		
		int[] orderedPairCurrent = posStringToArray(this.currPos);
		int[] orderedPair = posStringToArray(dest);
		if(!validCoordinateCheck(orderedPair)){
			return false;
		}
		
		
		if(orderedPairCurrent[0] == orderedPair[0]){
			if(orderedPairCurrent[1] == orderedPair[1]){//Same spot is not invalid
				return false;
			}
		}
		
		
		
		
		//eight valid moves for knight
		//each of the following four cases have two possibilities
		
		
		//1) two spots to the right, one spot forward or backward
		if(orderedPair[0] == orderedPairCurrent[0]+2){
			if((orderedPair[1] == orderedPairCurrent[1]+1) || (orderedPair[1] == orderedPairCurrent[1]-1)){
				return true;
			}
			
		}
		
		
		//2)two spots to the left, one spot forward or backward
		if(orderedPair[0] == orderedPairCurrent[0]-2){
			if((orderedPair[1] == orderedPairCurrent[1]+1) || (orderedPair[1] == orderedPairCurrent[1]-1)){
				return true;
			}
			
		}
		
		//3) two spots forward or backward and one spot to the right
		if(orderedPair[0] == orderedPairCurrent[0]+1){
			if((orderedPair[1] == orderedPairCurrent[1]+2) || (orderedPair[1] == orderedPairCurrent[1]-2)){
				return true;
			}
			
		}
		
		//4) two spots forward or backward and one spot to the left
		if(orderedPair[0] == orderedPairCurrent[0]-1){
			if((orderedPair[1] == orderedPairCurrent[1]+2) || (orderedPair[1] == orderedPairCurrent[1]-2)){
				return true;
			}
			
		}
		
		return false;
	}
	
}