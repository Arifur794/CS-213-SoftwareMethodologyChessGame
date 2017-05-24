package chess;

/**
 * Group 105
 * @author Arifur Rahman
 * @author Monique Gordon
 **
 */

import chessPieces.*;

//Chess Board set up

public class ChessBoard {

	ChessPiece[][] chessBoard;

	int[] wKLoc;// specifying each kings loc (game is completely based around
				// kings)

	int[] bKLoc;

	public ChessBoard() {

		chessBoard = new ChessPiece[8][8]; // standard chess board size for
											// two-dimensional array

		initializeChessBoard();

		wKLoc = new int[2];
		bKLoc = new int[2];

		wKLoc[0] = 7;
		wKLoc[1] = 4;
		bKLoc[0] = 0;
		bKLoc[1] = 4;

	}

	public void movePiece(int[] orig, int[] dest) {
		chessBoard[orig[0]][orig[1]] = chessBoard[dest[0]][dest[1]];
	}

	public void clear(int[] loc) {
		chessBoard[loc[0]][loc[1]] = null;
	}

	/**
	 * Default board setup:
	 * 
	 * bR bN bB bQ bK bB bN bR 8 bp bp bp bp bp bp bp bp 7 ## ## ## ## 6 ## ##
	 * ## ## 5 ## ## ## ## 4 ## ## ## ## 3 wp wp wp wp wp wp wp wp 2 wR wN wB wQ
	 * wK wB wN wR 1 a b c d e f g h
	 * 
	 */

	public void initializeChessBoard() { /** 8x8 board (index 0 to 7) */

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chessBoard[i][j] = null;
			}
		}

		for (int i = 0; i < 8; i++) {
			Pawn newBlackPawn = new Pawn(Chess.intToChar(i) + "7", "black");
			Pawn newWhitePawn = new Pawn(Chess.intToChar(i) + "2", "white");
			newBlackPawn.setMove1Boolean(true);
			newWhitePawn.setMove1Boolean(true);
			chessBoard[1][i] = newBlackPawn;
			chessBoard[6][i] = newWhitePawn;
		}

		Rook blackRook1 = new Rook("a8", "black");
		Rook blackRook2 = new Rook("h8", "black");
		Rook whiteRook1 = new Rook("a1", "white");
		Rook whiteRook2 = new Rook("h1", "white");
		chessBoard[0][0] = blackRook1;
		chessBoard[0][7] = blackRook2;
		chessBoard[7][0] = whiteRook1;
		chessBoard[7][7] = whiteRook2;

		Knight blackKnight1 = new Knight("b8", "black");
		Knight blackKnight2 = new Knight("g8", "black");
		Knight whiteKnight1 = new Knight("b1", "white");
		Knight whiteKnight2 = new Knight("g1", "white");
		chessBoard[0][1] = blackKnight1;
		chessBoard[0][6] = blackKnight2;
		chessBoard[7][1] = whiteKnight1;
		chessBoard[7][6] = whiteKnight2;

		Bishop blackBishop1 = new Bishop("c8", "black");
		Bishop blackBishop2 = new Bishop("f8", "black");
		Bishop whiteBishop1 = new Bishop("c1", "white");
		Bishop whiteBishop2 = new Bishop("f1", "white");
		chessBoard[0][2] = blackBishop1;
		chessBoard[0][5] = blackBishop2;
		chessBoard[7][2] = whiteBishop1;
		chessBoard[7][5] = whiteBishop2;

		Queen blackQueen = new Queen("d8", "black");
		Queen whiteQueen = new Queen("d1", "white");
		chessBoard[0][3] = blackQueen;
		chessBoard[7][3] = whiteQueen;

		King blackKing = new King("e8", "black");
		King whiteKing = new King("e1", "white");
		chessBoard[0][4] = blackKing;
		chessBoard[7][4] = whiteKing;
	}

	/** printing the chess board with manipulated array when necessary */

	public void printChessBoard() {

		String[][] printingBoard = new String[9][9];

		for (int i = 0; i < 8; i++)
			printingBoard[i][8] = Integer.toString(8 - i);
		for (int i = 0; i < 8; i++)
			printingBoard[8][i] = " ".concat(Character.toString(Chess.intToChar(i)));

		printingBoard[8][8] = "  ";

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (chessBoard[i][j] != null) {
					printingBoard[i][j] = chessBoard[i][j].toString();
				} else {

					if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1)) {
						printingBoard[i][j] = "##";
					} else {
						printingBoard[i][j] = "  ";
					}
				}

			}
		}

		for (int i = 0; i < 9; i++) {
			String currLine = "";
			for (int j = 0; j < 9; j++) {
				if (j != 9 || j != 0)
					currLine = currLine.concat(" ");
				currLine = currLine.concat(printingBoard[i][j]);
			}
			System.out.println(currLine);
		}

	}

	/** boolean method for check detection */
	public boolean checkDetection(String color) {

		getKings();
		int a = (int) 'a';

		int letB = bKLoc[1] + a;
		char letterB = (char) letB;
		int mB = 8 - bKLoc[0];
		String numberB = Integer.toString(mB);
		String blackKing = letterB + numberB;

		int letW = wKLoc[1] + a;
		char letterW = (char) letW;
		int mW = 8 - wKLoc[0];
		String numberW = Integer.toString(mW);
		String whiteKing = letterW + numberW;

		// when piece is white and path to king is both valid and clear, then
		// the black king is in check
		if (color.equals("white")) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (chessBoard[i][j] != null) {
						if ((chessBoard[i][j].color.equals("white")) && (chessBoard[i][j].pathValid(blackKing))
								&& (clearPath(chessBoard[i][j], blackKing))) {
							return true;
						}
					}

				}
			}
		}

		// when piece is black and path to the king is valid and clear, then the
		// white king will be in check
		if (color.equals("black")) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (chessBoard[i][j] != null) {
						if ((chessBoard[i][j].color.equals("black")) && (chessBoard[i][j].pathValid(whiteKing))
								&& (clearPath(chessBoard[i][j], whiteKing))) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public boolean checkmateCheck() {

		boolean whiteThreatened = checkDetection("black");
		boolean blackThreatened = checkDetection("white");

		System.out.println("whiteThreatened = " + whiteThreatened);
		System.out.println("blackThreatened = " + blackThreatened);
		getKings();
		int a = (int) 'a';

		// when player white threatened
		if (whiteThreatened) {

			int letW = wKLoc[1] + a;
			char letterW = (char) letW;
			int mW = 8 - wKLoc[0];
			String numberW = Integer.toString(mW);
			String whiteKing = letterW + numberW;
			int i, j = 10;
			int[][] potentialAttackers = new int[16][2];
			int countAttackers = 0;

			// locating harmful pieces that are attacking
			for (i = 0; i < 8; i++) {
				for (j = 0; j < 8; j++) {
					if (chessBoard[i][j] != null) {
						if ((chessBoard[i][j].color.equals("black")) && (chessBoard[i][j].pathValid(whiteKing))
								&& (clearPath(chessBoard[i][j], whiteKing))) {
							potentialAttackers[countAttackers][0] = i;
							potentialAttackers[countAttackers][1] = j;
							countAttackers++;
							System.out.println("attacker at [i][j] = [" + i + "][" + j + "]");
						}
					}
				}
			}

			// checking piece by piece of potential attackers
			if (countAttackers == 1) {
				j = potentialAttackers[0][1];
				i = potentialAttackers[0][0];
				int letMean = j + a;
				char letterMean = (char) letMean;
				int mMean = 8 - i;
				String numberMean = Integer.toString(mMean);
				String meanGuy = letterMean + numberMean;

				for (i = 0; i < 8; i++) {
					for (j = 0; j < 8; j++) {
						if (chessBoard[i][j] != null) {
							if ((chessBoard[i][j].color.equals("white")) && (chessBoard[i][j].pathValid(meanGuy))
									&& (clearPath(chessBoard[i][j], meanGuy))) {

								ChessPiece[][] tmpPtr = new ChessPiece[8][8];
								ChessBoard temp = new ChessBoard();
								temp.chessBoard = tmpPtr;

								for (int m = 0; m < 8; m++) {
									for (int n = 0; n < 8; n++) {
										tmpPtr[m][n] = (ChessPiece) chessBoard[m][n];
									}
								}

								int letSave = j + a;
								char letterSave = (char) letSave;
								int mSave = 8 - i;
								String numberSave = Integer.toString(mSave);
								String savior = letterSave + numberSave;

								temp.movePiece(savior, meanGuy, 'x');

								if (temp.checkDetection("black")) {
									System.out.println("Savior at failed at m,n = " + i + "," + j);
									continue;
								} else {

									// saving piece
									System.out.println("Savior at success at m,n = " + i + "," + j);
									return false;
								}
							}
						}
					}
				}
			}

			// no saving piece so try to move king's loc

			return !canMoveKing("white");

		}

		// when black is threatened
		else if (blackThreatened) {

			int letB = bKLoc[1] + a;
			char letterB = (char) letB;
			int mB = 8 - bKLoc[0];
			String numberB = Integer.toString(mB);
			String blackKing = letterB + numberB;
			int i, j = 10;
			int[][] potentialAttackers = new int[16][2];
			int countOfAttackers = 0;

			// potential harmful pieces to black
			for (i = 0; i < 8; i++) {
				for (j = 0; j < 8; j++) {
					if (chessBoard[i][j] != null) {
						if ((chessBoard[i][j].color.equals("white")) && (chessBoard[i][j].pathValid(blackKing))
								&& (clearPath(chessBoard[i][j], blackKing))) {
							potentialAttackers[countOfAttackers][0] = i;
							potentialAttackers[countOfAttackers][1] = j;
							countOfAttackers++;
						}
					}
				}
			}

			// checking potential attackers piece by piece
			if (countOfAttackers == 1) {
				j = potentialAttackers[0][1];
				i = potentialAttackers[0][0];
				int letAttk = j + a;
				char letterAttk = (char) letAttk;
				int mAttk = 8 - i;
				String numberAttk = Integer.toString(mAttk);
				String attkr = letterAttk + numberAttk;

				for (i = 0; i < 8; i++) {
					for (j = 0; j < 8; j++) {
						if (chessBoard[i][j] != null) {
							if ((chessBoard[i][j].color.equals("black")) && (chessBoard[i][j].pathValid(attkr))
									&& (clearPath(chessBoard[i][j], attkr))) {

								ChessPiece[][] tmpPtr = new ChessPiece[8][8];
								ChessBoard temp = new ChessBoard();
								temp.chessBoard = tmpPtr;

								for (int m = 0; m < 8; m++) {
									for (int n = 0; n < 8; n++) {
										tmpPtr[m][n] = (ChessPiece) chessBoard[m][n];
									}
								}

								int letSavior = j + a;
								char letterSavior = (char) letSavior;
								int mSavior = 8 - i;
								String numberSavior = Integer.toString(mSavior);
								String savior = letterSavior + numberSavior;

								temp.movePiece(savior, attkr, 'x');

								if (temp.checkDetection("white")) {
									continue;
								} else {
									return false;
								}
							}
						}
					}
				}
			}

			// if no saving piece found, try to move king's loc

			return !canMoveKing("black");

		} else {
			return false;
		}

	}

	/**
	 * @param chessPiece-
	 *            check occurs for this piece, whether path is clear or not
	 * @return true - when path is clear
	 */

	public boolean clearPath(ChessPiece chessPiece, String dest) {

		if (!chessPiece.pathValid(dest))
			return false;

		String destLetter = dest.substring(0, 1);
		String destNumber = dest.substring(1, 2);

		String startingLetter = chessPiece.currPos.substring(0, 1);
		String startingNumber = chessPiece.currPos.substring(1, 2);

		int destI = 8 - Integer.parseInt(destNumber);
		int destJ = destLetter.charAt(0) - 'a';

		int startI = 8 - Integer.parseInt(startingNumber);
		int startJ = startingLetter.charAt(0) - 'a';

		// moving horizontally
		if (destI == startI) {
			if (destJ < startJ) {
				for (int j = destJ + 1; j < startJ; j++) {
					if (chessBoard[startI][j] != null) {
						return false;
					}
				}

				return true;
			}

			else if (destJ > startJ) {
				for (int j = startJ + 1; j < destJ; j++) {
					if (chessBoard[startI][j] != null) {
						return false;
					}
				}
				return true;
			} else {

				// illegal move to same position
				return false;
			}
		}

		// moving vertically
		if (destJ == startJ) {
			if (destI < startI) {
				for (int i = destI + 1; i < startI; i++) {
					if (chessBoard[i][startJ] != null) {
						return false;
					}
				}

				return true;
			}

			else if (destI > startI) {
				for (int i = startI + 1; i < destI; i++) {
					if (chessBoard[i][startJ] != null) {
						return false;
					}
				}

				return true;
			}

			else {
				// invalid move to same position
				return false;
			}
		}

		// moving diagonally
		if (Math.abs(destJ - startJ) == Math.abs(destI - startI)) { // Slope is
																	// 1.

			if (Math.abs(destJ - startJ) == 1)
				return true;

			int j;

			if (destJ > startJ) {
				j = startJ;
			} else {
				j = destJ;
			}

			if (destI > startI) {
				for (int i = startI + 1; i < destI; i++) {
					j++;
					if (chessBoard[i][j] != null) {
						return false;
					}
				}
			} else {
				for (int i = destI + 1; i < startI; i++) {
					j++;
					if (chessBoard[i][j] != null) {
						return false;
					}
				}
			}

			return true;
		}

		// knight movement

		return true;

	}

	public boolean validityCheck(String loc) {
		if (loc.length() != 2)
			return false;

		String letterReq = loc.substring(0, 1);
		String numReq = loc.substring(1, 2);
		if (!(letterReq.matches("[a-zA-Z]")) || !(numReq.matches("[1-8]"))) {
			return false;
		}

		return true;
	}

	public ChessPiece getPieceFromloc(String loc) {

		if (loc.length() != 2)
			return null;

		String letterReq = loc.substring(0, 1);
		String numReq = loc.substring(1, 2);

		int requestI = 8 - Integer.parseInt(numReq);
		int requestJ = letterReq.charAt(0) - 'a';
		if (requestI > 8 || requestI < 0 || requestJ > 8 || requestJ < 0)
			return null;
		return chessBoard[requestI][requestJ];

	}

	public boolean movePiece(String orig, String dest, char promotion) {

		ChessPiece[][] tmpPtr = new ChessPiece[8][8];
		ChessBoard temp = new ChessBoard();
		temp.chessBoard = tmpPtr;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tmpPtr[i][j] = (ChessPiece) chessBoard[i][j];
			}
		}

		if (orig.length() != 2 || dest.length() != 2) {
			return false;
		}

		String letterReq = orig.substring(0, 1);
		String numReq = orig.substring(1, 2);

		int requestI = 8 - Integer.parseInt(numReq);
		int requestJ = letterReq.charAt(0) - 'a';
		if (requestI > 8 || requestI < 0 || requestJ > 8 || requestJ < 0)
			return false;

		String letterReq2 = dest.substring(0, 1);
		String numReq2 = dest.substring(1, 2);

		int requestK = 8 - Integer.parseInt(numReq2);
		int requestL = letterReq2.charAt(0) - 'a';

		if (requestK > 8 || requestK < 0 || requestL > 8 || requestL < 0)
			return false;

		switch (promotion) {

		// promotion cases
		case 'Q':
			Queen queenPromo = new Queen(dest, chessBoard[requestI][requestJ].color);
			tmpPtr[requestK][requestL] = queenPromo;
			tmpPtr[requestI][requestJ] = null;
			if (chessBoard[requestI][requestJ].color.equals("white")) {
				if (temp.checkDetection("black"))
					return false;
				this.chessBoard = tmpPtr;
				return true;
			} else {
				if (temp.checkDetection("white"))
					return false;
				this.chessBoard = tmpPtr;
				return true;
			}
		case 'N':
			Knight knightPromo = new Knight(dest, chessBoard[requestI][requestJ].color);
			tmpPtr[requestK][requestL] = knightPromo;
			tmpPtr[requestI][requestJ] = null;
			if (chessBoard[requestI][requestJ].color.equals("white")) {
				if (temp.checkDetection("black"))
					return false;
				this.chessBoard = tmpPtr;
				return true;
			} else {
				if (temp.checkDetection("white"))
					return false;
				this.chessBoard = tmpPtr;
				return true;
			}
		case 'B':
			Bishop bishopPromo = new Bishop(dest, chessBoard[requestI][requestJ].color);
			tmpPtr[requestK][requestL] = bishopPromo;
			tmpPtr[requestI][requestJ] = null;
			if (chessBoard[requestI][requestJ].color.equals("white")) {
				if (temp.checkDetection("black"))
					return false;
				this.chessBoard = tmpPtr;
				return true;
			} else {
				if (temp.checkDetection("white"))
					return false;
				this.chessBoard = tmpPtr;
				return true;
			}
		case 'R':
			Rook rookPromo = new Rook(dest, chessBoard[requestI][requestJ].color);
			tmpPtr[requestK][requestL] = rookPromo;
			tmpPtr[requestI][requestJ] = null;
			if (chessBoard[requestI][requestJ].color.equals("white")) {
				if (temp.checkDetection("black"))
					return false;
				this.chessBoard = tmpPtr;
				return true;
			} else {
				if (temp.checkDetection("white"))
					return false;
				this.chessBoard = tmpPtr;
				return true;
			}
		default:
			if (tmpPtr[requestI][requestJ] instanceof Pawn) {
				Pawn tempPawn = (Pawn) tmpPtr[requestI][requestJ];
				tempPawn.move1 = false;
				if (Math.abs(requestJ - requestL) == 2) {
					tempPawn.movedTwo = true;
				} else {
					tempPawn.movedTwo = false;
				}
				tmpPtr[requestI][requestJ] = tempPawn;
			} else if (tmpPtr[requestI][requestJ] instanceof King) {
				King tempKing = (King) tmpPtr[requestI][requestJ];
				tempKing.move1 = false;
				tmpPtr[requestI][requestJ] = tempKing;
			} else if (tmpPtr[requestI][requestJ] instanceof Rook) {
				Rook tempRook = (Rook) tmpPtr[requestI][requestJ];
				tempRook.move1 = false;
				tmpPtr[requestI][requestJ] = tempRook;
			}

			tmpPtr[requestK][requestL] = tmpPtr[requestI][requestJ];
			tmpPtr[requestK][requestL].currPos = dest;
			tmpPtr[requestI][requestJ] = null;

			if (this.chessBoard[requestI][requestJ].color.equals("white")) {
				if (temp.checkDetection("black")) {
					System.out.println("disregarded check!");
					return false;
				}
				System.out.println("here 456");
				this.chessBoard = tmpPtr;
				return true;
			} else {
				if (temp.checkDetection("white")) {
					System.out.println("disregarded check!");
					return false;
				}
				System.out.println("here 464");
				this.chessBoard = tmpPtr;
				return true;
			}
		}
	}

	public ChessPiece[][] enPassant(String orig, String dest, String toRemove) {
		if (orig.length() != 2 || dest.length() != 2) {
			return null;
		}
		String letterReq = toRemove.substring(0, 1);
		String numReq = toRemove.substring(1, 2);

		int requestI = 8 - Integer.parseInt(numReq);
		int requestJ = letterReq.charAt(0) - 'a';
		if (requestI > 8 || requestI < 0 || requestJ > 8 || requestJ < 0)
			return null;

		chessBoard[requestI][requestJ] = null;
		movePiece(orig, dest, 'x');

		return chessBoard;
	}

	public void getKings() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessBoard[i][j] != null) {
					if (chessBoard[i][j].toString().equals("bK")) {
						bKLoc[0] = i;
						bKLoc[1] = j;
					}

					else if (chessBoard[i][j].toString().equals("wK")) {
						wKLoc[0] = i;
						wKLoc[1] = j;
					}
				}
			}
		}
	}

	public void isStaleMate(String dest) {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessBoard[i][j] != null) {
					if (checkDetection("black") == false 
							&& checkDetection("white") == false
							&& chessBoard[i][j].pathValid(dest)) {
						 boolean staleMate;
						 staleMate=true;
					}

				}
			}
		}
	}

	/**
	 * boolean method for trying to save king
	 * 
	 * @param color
	 *            - color for king in danger
	 * @return true - when the moving king is done with check, if not, then
	 *         false
	 */
	public boolean canMoveKing(String color) {

		ChessPiece[][] tmpPtr = new ChessPiece[8][8];
		ChessBoard temp = new ChessBoard();
		temp.chessBoard = tmpPtr;

		for (int m = 0; m < 8; m++) {
			for (int n = 0; m < 8; m++) {
				tmpPtr[m][n] = (ChessPiece) chessBoard[m][n];
			}
		}

		temp.getKings();

		if (color.equals("white")) {
			int kingI = temp.wKLoc[0];
			int kingJ = temp.wKLoc[1];
			int orig[] = new int[2];
			int dest[] = new int[2];
			orig[0] = kingI;
			orig[1] = kingJ;

			if (kingI + 1 <= 7) {

				if (tmpPtr[kingI + 1][kingJ] == null) {
					dest[0] = kingI + 1;
					dest[1] = kingJ;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("black"))
						return true;
				}

				if (kingJ + 1 <= 7 && tmpPtr[kingI + 1][kingJ + 1] == null) {
					dest[0] = kingI + 1;
					dest[1] = kingJ + 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("black"))
						return true;
				}
				if (kingJ + 1 <= 7 && tmpPtr[kingI][kingJ + 1] == null) {
					dest[0] = kingI;
					dest[1] = kingJ + 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("black"))
						return true;
				}
				if (kingJ - 1 >= 0 && tmpPtr[kingI + 1][kingJ - 1] == null) {
					dest[0] = kingI + 1;
					dest[1] = kingJ - 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("black"))
						return true;
				}

				if (kingJ - 1 >= 0 && tmpPtr[kingI][kingJ - 1] == null) {
					dest[0] = kingI;
					dest[1] = kingJ - 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("black"))
						return true;
				}
			}

			if (kingI - 1 >= 0) {
				if (tmpPtr[kingI - 1][kingJ] == null) {
					dest[0] = kingI - 1;
					dest[1] = kingJ;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("black"))
						return true;
				}

				if (kingJ + 1 <= 7 && tmpPtr[kingI - 1][kingJ + 1] == null) {
					dest[0] = kingI - 1;
					dest[1] = kingJ + 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("black"))
						return true;
				}

				if (kingJ - 1 >= 0 && tmpPtr[kingI - 1][kingJ - 1] == null) {
					dest[0] = kingI - 1;
					dest[1] = kingJ - 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("black"))
						return true;
				}
			}

			return false;
		}

		else {

			int kingI = temp.bKLoc[0];
			int kingJ = temp.bKLoc[1];
			int orig[] = new int[2];
			int dest[] = new int[2];
			orig[0] = kingI;
			orig[1] = kingJ;

			if (kingI + 1 <= 7) {
				if (tmpPtr[kingI + 1][kingJ] == null) {
					dest[0] = kingI + 1;
					dest[1] = kingJ;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("white"))
						return true;
				}

				if (kingJ + 1 <= 7 && tmpPtr[kingI + 1][kingJ + 1] == null) {
					dest[0] = kingI + 1;
					dest[1] = kingJ + 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("white"))
						return true;
				}

				if (kingJ + 1 <= 7 && tmpPtr[kingI][kingJ + 1] == null) {
					dest[0] = kingI;
					dest[1] = kingJ + 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("white"))
						return true;
				}

				if (kingJ - 1 >= 0 && tmpPtr[kingI + 1][kingJ - 1] == null) {
					dest[0] = kingI + 1;
					dest[1] = kingJ - 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("white"))
						return true;
				}

				if (kingJ - 1 >= 0 && tmpPtr[kingI][kingJ - 1] == null) {
					dest[0] = kingI;
					dest[1] = kingJ - 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("white"))
						return true;
				}
			}

			if (kingI - 1 >= 0) {
				if (tmpPtr[kingI - 1][kingJ] == null) {
					dest[0] = kingI - 1;
					dest[1] = kingJ;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("white"))
						return true;
				}

				if (kingJ + 1 <= 7 && tmpPtr[kingI - 1][kingJ + 1] == null) {
					dest[0] = kingI - 1;
					dest[1] = kingJ + 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("white"))
						return true;
				}

				if (kingJ - 1 >= 0 && tmpPtr[kingI - 1][kingJ - 1] == null) {
					dest[0] = kingI - 1;
					dest[1] = kingJ - 1;
					temp.movePiece(orig, dest);
					if (!temp.checkDetection("white"))
						return true;
				}
			}

			return false;
		}

	}

}