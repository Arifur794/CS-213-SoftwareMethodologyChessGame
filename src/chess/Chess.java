package chess;

/**
 * Group 105
 * @author Arifur Rahman	
 * @author Monique Gordon
 *
 */


import java.util.Scanner;


import chessPieces.*;

public class Chess {

	// integer to character conversion for ASCII
	public static char intToChar(int i){
		return (char) (i+97);
	}
	
	

	
	public static void main(String[] args) {
		
		ChessBoard chessBoard = new ChessBoard();
		
		String turn = "White's move:";
		String winner = ""; // for later use at the end of a match
		
		//All boolean vals set to false
		Boolean drawReq = false;
		Boolean drawAccept = false;
		Boolean legalMove = false;
		Boolean resign = false;// a player may resign by entering "resign"
		Boolean checkMate = false;
		Boolean staleMate = false; //10 point extra credit
		Boolean checkDetection = false;
		String dest;
		//chessBoard.isStaleMate(dest);
	
		
		
		
		
		
		Scanner scanner = new Scanner (System.in);
		
		while (!drawAccept && !resign && !checkMate && !staleMate) {
			
			System.out.println("");
			chessBoard.printChessBoard();
			System.out.println("");
			
			
			if(checkDetection){ 
				if (chessBoard.checkmateCheck()){
					checkMate = true;
					break;
				}
				System.out.println("Check");
			}
			System.out.print(turn);
			String moveReq = scanner.nextLine();
				
			
			
	
			
			/**Checking to see if the user is requesting a draw.
			 * When no draw requested, then saying 'draw' will result in an illegal move.
			 * then draw requested, game ends in a draw.
			 * When resign, the game ends.
			*/
			
			
			if (drawReq && moveReq.equals("draw")){
				drawAccept = true; 
				break;
			} 
			
			else if (!drawReq && moveReq.equals("draw")){
				System.out.println("Illegal move, try again");
				legalMove = false;
				continue;
			} 
			
			else if (moveReq.equals("resign")){
				resign = true;
				if(turn.equals("White's move:")){
					winner = "Black wins";
				} 
				
				else {
					winner = "White wins";
				}
				
				break;
			}
			
			String[] moveReqArr = moveReq.split("\\s+"); //split move by spaces
			
		
			/**checking array for right size
			 * should be of length 2 or 3
			 * 3 when for example "a5 e3 N" for the promotion case
			 * 2 when for example "a5 e3"
			 */
			if(moveReqArr.length != 2){
				if(moveReqArr.length != 3){
					System.out.println("Illegal move, try again");
					legalMove = false;
					continue;
				}
			}
			
			// locating dest of piece player wants to move
			if(!(chessBoard.validityCheck(moveReqArr[0])) || !(chessBoard.validityCheck(moveReqArr[1]))){
				System.out.println("Illegal move, try again");
				continue;
			}
			ChessPiece movingPiece = chessBoard.getPieceFromloc(moveReqArr[0]);
			 dest = moveReqArr[1];
			
			
			/**player trying to move a piece that doesn't exist in the given location or the form
			 * for entering a piece location is wrong
			 */
			if (movingPiece == null) {
				System.out.println("Illegal move, try again");
				legalMove = false;
				continue;
			}
			
			
			// check to see if piece is being moved or not
			if(turn.equals("White's move:")){
				if (movingPiece.color.equals("black")) {
					System.out.println("Illegal move, try again");
					legalMove = false;
					continue;
				}
			} 
			
			else {
				if (movingPiece.color.equals("white")) {
					System.out.println("Illegal move, try again");
					legalMove = false;
					continue;
				}
			}
			
			// check if piece can travel along path and check if path is clear or not
			if (!movingPiece.pathValid(dest) || !chessBoard.clearPath(movingPiece, dest)){
			
				  /** 
				   * Castling
				   */
				 
				if((movingPiece instanceof King)){
					King tempKingCheck = (King) movingPiece;
					if(tempKingCheck.move1 != true){
						System.out.println("Illegal move, try again");
						continue;
					}
					if(movingPiece.color.equals("white")){
						switch(dest){
						case "g1":
							if((chessBoard.getPieceFromloc("f1") != null) || (chessBoard.getPieceFromloc("g1") != null) ||(chessBoard.getPieceFromloc("h1") == null)){
								System.out.println("Illegal move, try again");
								continue;
							}
							if((chessBoard.movePiece("e1", "f1", 'x') == false) || (chessBoard.movePiece("e1", "g1", 'x') == false)){
								System.out.println("Illegal move, try again");
								continue;
							}
							Rook rookCheck = (Rook) chessBoard.getPieceFromloc("h1");
							if(rookCheck.move1 != true){
								System.out.println("Illegal move, try again");
								continue;
							}
							else{
								chessBoard.movePiece("e1", dest, 'x');
								chessBoard.movePiece("h1", "f1", 'x');
							}
						case "c1":
							if((chessBoard.getPieceFromloc("d1") != null) || (chessBoard.getPieceFromloc("c1") != null) || (chessBoard.getPieceFromloc("b1") != null) || (chessBoard.getPieceFromloc("a1") == null)){
								System.out.println("Illegal move, try again");
								continue;
							}
							if((chessBoard.movePiece("e1", "d1", 'x') == false) || (chessBoard.movePiece("e1", "c1", 'x') == false)){
								System.out.println("Illegal move, try again");
								continue;
							}
							Rook rookCheck2 = (Rook) chessBoard.getPieceFromloc("a1");
							if(rookCheck2.move1 != true){
								System.out.println("Illegal move, try again");
								continue;
							}
							else{
								chessBoard.movePiece("e1", dest, 'x');
								chessBoard.movePiece("a1", "d1", 'x');
							}
						default:
							System.out.println("Illegal move, try again");
							continue;
						}
					}
					else{
						switch(dest){
						case "g8":
							if((chessBoard.getPieceFromloc("f8") != null) || (chessBoard.getPieceFromloc("g8") != null) ||(chessBoard.getPieceFromloc("h8") == null)){
								System.out.println("Illegal move, try again");
								continue;
							}
							if((chessBoard.movePiece("e8", "f8", 'x') == false) || (chessBoard.movePiece("e8", "g8", 'x') == false)){
								System.out.println("Illegal move, try again");
								continue;
							}
							Rook tempRookCheck = (Rook) chessBoard.getPieceFromloc("h8");
							if(tempRookCheck.move1 != true){
								System.out.println("Illegal move, try again");
								continue;
							}
							else{
								chessBoard.movePiece("e8", dest, 'x');
								chessBoard.movePiece("h8", "f8", 'x');
							}
						case "c8":
							if((chessBoard.getPieceFromloc("d8") != null) || (chessBoard.getPieceFromloc("c8") != null) || (chessBoard.getPieceFromloc("b8") != null) || (chessBoard.getPieceFromloc("a8") == null)){
								System.out.println("Illegal move, try again");
								continue;
							}
							if((chessBoard.movePiece("e8", "d8", 'x') == false) || (chessBoard.movePiece("e8", "c8", 'x') == false)){
								System.out.println("Illegal move, try again");
								continue;
							}
							Rook rookCheck2 = (Rook) chessBoard.getPieceFromloc("a8");
							if(rookCheck2.move1 != true){
								System.out.println("Illegal move, try again");
								continue;
							}
							else{
								chessBoard.movePiece("e8", dest, 'x');
								chessBoard.movePiece("a8", "d8", 'x');
							}
						default:
							System.out.println("Illegal move, try again");
							continue;
						}
					}	
				}
				
				// when piece isn't pawn or king, move must be illegal
				if(!(movingPiece instanceof Pawn)){
					System.out.println("Illegal move, try again");
					legalMove = false;
					continue;
				} else {
					
					if(chessBoard.getPieceFromloc(dest) == null){
						
						int test;
						if(movingPiece.color.equals("white")){
							test = (Character.getNumericValue(dest.charAt(1)))-1;
						}
						else{
							test = (Character.getNumericValue(dest.charAt(1)))+1;
						}
						
						String newDest = dest.charAt(0)+(Integer.toString(test));

						if((chessBoard.getPieceFromloc(newDest) != null) && ((chessBoard.getPieceFromloc(newDest) instanceof Pawn))){
							Pawn tmpPawn = (Pawn) (chessBoard.getPieceFromloc(newDest));
							if(tmpPawn.movedTwo == true){
								chessBoard.enPassant(moveReqArr[0], dest, newDest);
								continue;
							}
						}
						
						System.out.println("Illegal move, try again");
						legalMove = false;
						continue;
					}
					
					ChessPiece destPiece = chessBoard.getPieceFromloc(dest);
					
					int[] moveOP = movingPiece.posStringToArray(movingPiece.currPos);
					int[] destOrderedPair = destPiece.posStringToArray(dest);
					
					
					// pawn diagonal checker
					if(Math.abs(moveOP[0]-destOrderedPair[0])!=1 ||
							Math.abs(moveOP[1]-destOrderedPair[1])!=1){
						System.out.println("Illegal move, try again");
						legalMove = false;
						continue;
					}
					
				}
			}
			
			
			// making sure that a player cannot capture member of their own team
			if(turn.equals("White's move:")){
				if((chessBoard.getPieceFromloc(dest) != null) && (chessBoard.getPieceFromloc(dest)).color.equals("white")){
					System.out.println("Illegal move, try again");
					legalMove = false;
					continue;
				}
			} else{
				if(((chessBoard.getPieceFromloc(dest) != null) && (chessBoard.getPieceFromloc(dest)).color.equals("black"))){
					System.out.println("Illegal move, try again");
					legalMove = false;
					continue;
				}	
			}
			
			
			
			 /**PROMOTION (when pawn is promoted to queen )*/
			 
			if((movingPiece instanceof Pawn) && (dest.charAt(1) == '8' || dest.charAt(1) == '1') && (moveReqArr.length != 3)){
				if(!chessBoard.movePiece(moveReqArr[0], moveReqArr[1], 'Q')) System.out.println("Moved without regard to check, Illegal move, try again");
				legalMove = false;
				continue;
			}
			
			
			
			
			  /**PROMOTION ( when player chooses the desired promotion)*/
			
			if(moveReqArr.length == 3){
				if(moveReqArr[2].equals("draw?")){
					drawReq = true;
				} 
				
				//making sure the pawn did in fact reach the end of the board 
				
				else if((movingPiece instanceof Pawn) && (dest.charAt(1) == '8' || dest.charAt(1) == '1')){
					switch(moveReqArr[2]){
					
					
					// case check for desired promotion
					case "Q":
						boolean returnVal = chessBoard.movePiece(moveReqArr[0], moveReqArr[1], 'Q');
						if(!returnVal) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					case "N":
						if(!chessBoard.movePiece(moveReqArr[0], moveReqArr[1], 'N')) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					case "K":
						if(!chessBoard.movePiece(moveReqArr[0], moveReqArr[1], 'K')) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					case "B":
						if(!chessBoard.movePiece(moveReqArr[0], moveReqArr[1], 'B')) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					case "R":
						if(!chessBoard.movePiece(moveReqArr[0], moveReqArr[1], 'R')) System.out.println("Moved without regard to check, Illegal move, try again");
						continue;
					default:
						System.out.println("Illegal move, try again");
						continue;
					}
				}
				else {
					System.out.println("Illegal move, try again");
					continue;
				}
			}
			legalMove = true;
			if (legalMove) {
				boolean returnVal = chessBoard.movePiece(moveReqArr[0], moveReqArr[1], 'x');
				if(!returnVal){ 
					System.out.println("Illegal move, try again");
					continue;
				}
				if(turn.equals("White's move:")){
					if(chessBoard.checkDetection("white")){
						checkDetection = true;
					} else {
						checkDetection = false;
					}
					turn = "Black's move:";
				} else {
					if(chessBoard.checkDetection("black")){
						checkDetection = true;
					} else {
						checkDetection = false;
					}
					turn = "White's move:";
				}
			}

			legalMove = false;
		}
		
		if (drawAccept || staleMate) {   // ending game for draw or stalemate
			System.out.println("Draw");
		} else {
			System.out.println(winner);
		}
		scanner.close();
		
	}
	


}

