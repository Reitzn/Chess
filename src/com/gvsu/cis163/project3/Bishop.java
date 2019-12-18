package com.gvsu.cis163.project3;

import javax.swing.ImageIcon;

/***********************************************************************
 * Bishop Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * A Bishop piece that extends ChessPiece. It holds the rules to how the
 * bishop will be able to interact with the board it its laws.
 * 
 * @author Douglas Money, Nick Reitz, Michael Baldwin
 * @version Winter 2014
 **********************************************************************/
public class Bishop extends ChessPiece {

	/** Top to Bottom in a positive direction(TBPos) */
	private int countPieceTBPos;

	/** Top to Bottom in a negative direction(TBNeg) */
	private int countPieceTBNeg;

	/** Bottom to Top in a positive direction(BTPos) */
	private int countPieceBTPos;

	/** Bottom to Top in a negative direction(BTNeg) */
	private int countPieceBTNeg;

	/*******************************************************************
	 * Default constructor for the Bishop class that creates a bishop
	 * piece.
	 * 
	 * @param player
	 *            Who's piece it is
	 ******************************************************************/

	protected Bishop(Player player) {
		super(player);
	}

	/*******************************************************************
	 * Method for the Bishop class that returns that it is a bishop in
	 * the form of a string.
	 * 
	 * @return String type of piece
	 ******************************************************************/

	public String type() {
		return "Bishop";
	}

	/*******************************************************************
	 * Method for the Bishop class that assigns a white bishop image to
	 * a imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/

	public ImageIcon whiteIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/white_bishop.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Bishop class that assigns a black bishop image to
	 * a imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/

	public ImageIcon blackIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/black_bishop.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Bishop class that Checks to make sure that the
	 * move a player has made is a valid move.
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
	 * Method for the Bishop class that Checks to make sure that the
	 * move a player has made is a clear path to the move location
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
	 * Method for the Bishop class that returns what color and type of
	 * piece it is.
	 * 
	 * @return String toString overide for current piece type
	 ******************************************************************/

	public String toString() {

		if (super.player() == Player.BLACK)
			return "Black Bishop";
		if (super.player() == Player.WHITE)
			return "White Bishop";
		else
			return "";

	}

}
