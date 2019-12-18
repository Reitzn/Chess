package com.gvsu.cis163.project3;

import javax.swing.ImageIcon;

/***********************************************************************
 * Rook Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * A Rook piece that extends ChessPiece. It holds the rules to how the
 * rook will be able to interact with the board it its laws.
 * 
 * @author Douglas Money, Nick Reitz, Michael Baldwin
 * @version Winter 2014
 **********************************************************************/
public class Rook extends ChessPiece {

	/*******************************************************************
	 * Default constructor for the Rook class that creates a rook piece.
	 * 
	 * @param player
	 *            Who's piece it is
	 ******************************************************************/

	protected Rook(Player player) {
		super(player);
	}

	/*******************************************************************
	 * Method for the Rook class that returns that it is a rook in the
	 * form of a string.
	 * 
	 * @return String type of piece
	 ******************************************************************/

	public String type() {
		return "Rook";
	}

	/*******************************************************************
	 * Method for the Rook class that assigns a white rook image to an
	 * imageIcon and returns it. Violates MVC but great example of poly
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/

	public ImageIcon whiteIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/white_rook.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Rook class that assigns a black rook image to an
	 * imageIcon and returns it. Violates MVC but great example of poly
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/

	public ImageIcon blackIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/black_rook.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Rook class that Checks to make sure that the move
	 * a player has made is a valid move.
	 * 
	 * @param move
	 *            accepts a game Move
	 * 
	 * @param board
	 *            accepts a board of chess pieces
	 * 
	 * @return boolean true if the move is legal
	 * 
	 ******************************************************************/

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		int countPiece = -1;
		// int steps = Math.abs(move.toRow - move.fromRow);

		// up and down
		if (move.fromColumn == move.toColumn
				&& super.isValidMove(move, board)) {

			// south to north movement
			for (int i = move.fromRow; i >= move.toRow; i--) {

				IChessPiece x = board[i][move.toColumn];

				if (x != null) {
					countPiece++;
				}

			}

			// north to south movement
			for (int i = move.fromRow; i <= move.toRow; i++) {

				IChessPiece x = board[i][move.toColumn];

				if (x != null)
					countPiece++;

			}
		}

		// side to side
		if (move.fromRow == move.toRow
				&& super.isValidMove(move, board)) {

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
	 * Method for the Rook class that returns what color and type of
	 * piece it is.
	 * 
	 * @return String toString override for current piece type
	 ******************************************************************/

	public String toString() {

		// if it is a black piece
		if (super.player() == Player.BLACK)

			// returns string of black and rook
			return "Black Rook";

		// if it is a white piece
		if (super.player() == Player.WHITE)

			// returns string of white and rook
			return "White Rook";

		// if it is neither
		else

			// returns blink string
			return "";

	}

}
