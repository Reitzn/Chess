package com.gvsu.cis163.project3;

import javax.swing.ImageIcon;

/***********************************************************************
 * King Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * A King piece that extends ChessPiece. Essentially the rules to how
 * the king will be able to interact with the board it its laws.
 * 
 * @author Douglas Money, Nick Reitz, Michael Baldwin
 * @version Winter 2014
 **********************************************************************/

public class King extends ChessPiece {

	/*******************************************************************
	 * Default constructor for the King class that creates a king piece.
	 * 
	 * @param player
	 *            Who's piece it is
	 ******************************************************************/

	protected King(Player player) {
		super(player);
	}

	/*******************************************************************
	 * Method for the King class that returns that it is a king in the
	 * form of a string.
	 * 
	 * @return String type of piece
	 ******************************************************************/

	public String type() {
		return "King";
	}

	/*******************************************************************
	 * Method for the King class that assigns a white king image to a
	 * imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/

	public ImageIcon whiteIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/white_king.png"));
		return Icon;

	}

	/*******************************************************************
	 * Method for the King class that assigns a black king image to a
	 * imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/

	public ImageIcon blackIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/black_king.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the King class that Checks to make sure that the move
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

		// Determines if the move is valid according to ChessPiece class
		if (super.isValidMove(move, board)) {

			// if spot is one to the spot is one up the board.
			if ((move.toRow == move.fromRow - 1)
					&& (move.fromColumn == move.toColumn)) {
				return true;
			}

			// if the spot is one down the board
			if ((move.toRow == move.fromRow + 1)
					&& (move.fromColumn == move.toColumn)) {
				return true;
			}

			// if the spot is one to the right
			if ((move.toColumn == move.fromColumn + 1)
					&& (move.fromRow == move.toRow)) {
				return true;
			}

			// if the spot is one to the left
			if ((move.toColumn == move.fromColumn - 1)
					&& (move.fromRow == move.toRow)) {
				return true;
			}

			// if the spot is in the upper right corner
			if ((move.toColumn == move.fromColumn + 1)
					&& (move.toRow == move.fromRow - 1)) {
				return true;
			}

			// if the spot is in the upper left corner
			if ((move.toColumn == move.fromColumn - 1)
					&& (move.toRow == move.fromRow - 1)) {
				return true;
			}

			// if the spot is in the lower left corner
			if ((move.toColumn == move.fromColumn - 1)
					&& (move.toRow == move.fromRow + 1)) {
				return true;
			}

			// if the spot is in the lower right corner
			if ((move.toColumn == move.fromColumn + 1)
					&& (move.toRow == move.fromRow + 1)) {
				return true;
			}
		}

		// returns false if the spot is none of thies options
		return false;
	}


}
