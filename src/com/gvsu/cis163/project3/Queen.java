package com.gvsu.cis163.project3;

import javax.swing.ImageIcon;

/***********************************************************************
 * Queen Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * A Queen piece that extends ChessPiece. It holds the rules to how the
 * queen will be able to interact with the board it its laws.
 * 
 * @author Douglas Money, Nick Reitz, Michael Baldwin
 * @version Winter 2014
 **********************************************************************/
public class Queen extends ChessPiece {

	/** Top to Bottom in a positive direction(TBPos) */
	private int countPieceTBPos;

	/** Top to Bottom in a negative direction(TBNeg) */
	private int countPieceTBNeg;

	/** Bottom to Top in a positive direction(BTPos) */
	private int countPieceBTPos;

	/** Bottom to Top in a negative direction(BTNeg) */
	private int countPieceBTNeg;

	/*******************************************************************
	 * Default constructor for the Queen class that creates a queen
	 * piece.
	 * 
	 * @param player
	 *            Who's piece it is
	 ******************************************************************/
	protected Queen(Player player) {
		super(player);
	}

	/*******************************************************************
	 * Method for the Queen class that returns that it is a queen in the
	 * form of a string.
	 * 
	 * @return String type of piece
	 ******************************************************************/
	public String type() {
		return "Queen";
	}

	/*******************************************************************
	 * Method for the Queen class that assigns a white queen image to a
	 * imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/
	public ImageIcon whiteIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/white_queen.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Queen class that assigns a black queen image to an
	 * imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/
	public ImageIcon blackIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/black_queen.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Queen class that Checks to make sure that the move
	 * a player has made is a valid move.
	 * 
	 * @param move
	 *            accepts a game Move
	 * 
	 * @param board
	 *            accepts a board of chess pieces
	 * 
	 * @return boolean true if the move is legal
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		// if the move is vertical or horizontal
		if ((move.fromColumn == move.toColumn)
				|| (move.fromRow == move.toRow)) {

			// sends it to the rook movement
			return checkRookMovement(move, board);

			// if not vertical or horizontal
		} else

			// sends it to the bishop movement
			return checkBishopMovement(move, board);

	}

	/*******************************************************************
	 * Method for the Queen class that Checks to make sure that the
	 * queen is able to move like a rook.
	 * 
	 * @param move
	 *            accepts a game Move
	 * 
	 * @param board
	 *            accepts a board of chess pieces
	 * 
	 * @return boolean true if the move is legal
	 ******************************************************************/
	public boolean checkRookMovement(Move move, IChessPiece[][] board) {

		int countPiece = -1;

		// make sure it is a valid move according to chessPiece
		if (super.isValidMove(move, board)) {

			// make sure move is in the same column
			if (move.fromColumn == move.toColumn) {

				//
				for (int i = move.fromRow; i >= move.toRow; i--) {
					IChessPiece x = board[i][move.toColumn];

					if (x != null)
						countPiece++;

				}

				// north to south movement
				for (int i = move.fromRow; i <= move.toRow; i++) {
					IChessPiece x = board[i][move.toColumn];

					if (x != null)
						countPiece++;

				}
			}

			// side to side
			if (move.fromRow == move.toRow) {
				// west to east movement
				for (int i = move.fromColumn; i <= move.toColumn; i++) {
					IChessPiece x = board[move.toRow][i];

					if (x != null)
						countPiece++;
				}
				// east to west movement
				for (int i = move.fromColumn; i >= move.toColumn; i--) {
					IChessPiece x = board[move.toRow][i];

					if (x != null)
						countPiece++;
				}


			}

		}

		if (countPiece == 1)
			// try take piece
			if ((move.fromRow == move.toRow 
						|| move.fromColumn == move.toColumn)
					&& board[move.toRow][move.toColumn] != null
					&& board[move.toRow][move.toColumn].player()
							.equals(player().next()))
				return true;

		// if path is clear
		if (countPiece == 0)
			return true;

		else
			return false;

	}

	/*******************************************************************
	 * Method for the Queen class that Checks to make sure that the
	 * queen is able to move like a bishop.
	 * 
	 * @param move
	 *            accepts a game Move
	 * 
	 * @param board
	 *            accepts a board of chess pieces
	 * 
	 * @return boolean true if the move is legal
	 ******************************************************************/
	public boolean checkBishopMovement(Move move,IChessPiece[][] board){

		checkForClearPath(move, board);

		for (int i = 0; i < 8; i++) {

			// Check for valid move

			if ((move.fromColumn + i == move.toColumn)
					&& (move.fromRow + i == move.toRow)
					&& countPieceTBPos == 0
					&& super.isValidMove(move, board)) {

				return true;
			}

			else if ((move.fromColumn - i == move.toColumn)
					&& (move.fromRow + i == move.toRow)
					&& countPieceTBNeg == 0
					&& super.isValidMove(move, board)) {

				return true;
			}

			else if ((move.fromColumn + i == move.toColumn)
					&& (move.fromRow - i == move.toRow)
					&& countPieceBTPos == 0
					&& super.isValidMove(move, board)) {
				return true;
			}

			else if ((move.fromColumn - i == move.toColumn)
					&& (move.fromRow - i == move.toRow)
					&& countPieceBTNeg == 0
					&& super.isValidMove(move, board)) {
				return true;
			}

			// Check Capture

			// Top to Bottom
			// Left to Right

			if ((move.fromColumn + i == move.toColumn)
					&& (move.fromRow + i == move.toRow)
					&& countPieceTBPos == 1
					&& super.isValidMove(move, board)) {

				if (board[move.toRow][move.toColumn] != null
						&& board[move.toRow][move.toColumn].player()
								.equals(player().next())) {
					return true;
				}
			}

			// Check Capture

			// Top to Bottom
			// Right to Left

			if (((move.fromColumn - i == move.toColumn)
					&& (move.fromRow + i == move.toRow)
					&& countPieceTBNeg == 1 && super.isValidMove(move,
					board))) {

				if (board[move.toRow][move.toColumn] != null
						&& board[move.toRow][move.toColumn].player()
								.equals(player().next())) {
					return true;
				}
			}

			// Check Capture

			// Bottom to Top
			// Left to Right

			if ((move.fromColumn + i == move.toColumn)
					&& (move.fromRow - i == move.toRow)
					&& countPieceBTPos == 1
					&& super.isValidMove(move, board)) {

				if (board[move.toRow][move.toColumn] != null
						&& board[move.toRow][move.toColumn].player()
								.equals(player().next())) {
					return true;
				}
			}

			// Check Capture

			// Bottom to Top
			// Right to Left

			if ((move.fromColumn - i == move.toColumn)
					&& (move.fromRow - i == move.toRow)
					&& countPieceBTNeg == 1
					&& super.isValidMove(move, board)) {

				if (board[move.toRow][move.toColumn] != null
						&& board[move.toRow][move.toColumn].player()
								.equals(player().next())) {
					return true;
				}
			}

		}

		return false;

	}

	/*******************************************************************
	 * Method for the Queen class that Checks to make sure that the move
	 * a player has made is a clear path to the move location. Primarily
	 * the bishop like movement
	 * 
	 * @param move
	 *            accepts a game Move
	 * 
	 * @param board
	 *            accepts a board of chess pieces
	 * 
	 * @return boolean true if the move is legal
	 ******************************************************************/

	public void checkForClearPath(Move move, IChessPiece[][] board) {

		// Top to Bottom in a positive direction(TBPos)
		countPieceTBPos = 0;
		countPieceTBNeg = 0;
		countPieceBTPos = 0;
		countPieceBTNeg = 0;


		// Top to Bottom
		// Left to Right
		if (super.isValidMove(move, board)) {

			int tempRow = move.fromRow;
			int tempCol = move.fromColumn;

			for (int i = move.fromRow; i < move.toRow; i++) {
				tempRow += 1;
				tempCol += 1;
				if ((tempRow < 8 && tempCol < 8)
						&& (tempRow >= 0 && tempCol >= 0)) {

					IChessPiece x = board[tempRow][tempCol];

					if (x != null)
						countPieceTBPos++;
				}
			}
		}

		// Top to Bottom
		// Right to Left
		if (super.isValidMove(move, board)) {
			int tempRow = move.fromRow;
			int tempCol = move.fromColumn;

			for (int i = move.fromRow; i < move.toRow; i++) {
				tempRow += 1;
				tempCol -= 1;
				if ((tempRow < 8 && tempCol < 8)
						&& (tempRow >= 0 && tempCol >= 0)) {

					IChessPiece x = board[tempRow][tempCol];

					if (x != null)
						countPieceTBNeg++;

				}
			}
		}

		// Bottom to Top
		// Left to Right

		if (super.isValidMove(move, board)) {
			int tempRow = move.fromRow;
			int tempCol = move.fromColumn;

			for (int i = move.fromRow; i > move.toRow; i--) {
				tempRow -= 1;
				tempCol += 1;

				if ((tempRow < 8 && tempCol < 8)
						&& (tempRow >= 0 && tempCol >= 0)) {

					IChessPiece x = board[tempRow][tempCol];

					if (x != null)
						countPieceBTPos++;

				}
			}
		}

		// Bottom to Top
		// Right to Left
		if (super.isValidMove(move, board)) {
			int tempRow = move.fromRow;
			int tempCol = move.fromColumn;

			for (int i = move.fromRow; i > move.toRow; i--) {
				tempRow -= 1;
				tempCol -= 1;

				if ((tempRow < 8 && tempCol < 8)
						&& (tempRow >= 0 && tempCol >= 0)) {

					IChessPiece x = board[tempRow][tempCol];

					if (x != null)
						countPieceBTNeg++;

				}
			}
		}

	}

	/*******************************************************************
	 * Method for the Queen class that returns what color and type of
	 * piece it is.
	 * 
	 * @return String toString overide for current piece type
	 ******************************************************************/

	public String toString() {

		// if it is a black piece
		if (super.player() == Player.BLACK)

			// returns string of black and queen
			return "Black Queen";

		// if it is a white piece
		if (super.player() == Player.WHITE)

			// returns string of white and queen
			return "White Queen";

		// if it is neither
		else

			// returns blink string
			return "";

	}

}
