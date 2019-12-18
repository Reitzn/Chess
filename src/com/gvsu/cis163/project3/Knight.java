package com.gvsu.cis163.project3;

import javax.swing.ImageIcon;

/***********************************************************************
 * Knight Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * A Knight piece that extends ChessPiece. It holds the rules to how the
 * knight will be able to interact with the board it its laws.
 * 
 * @author Douglas Money, Nick Reitz, Michael Baldwin
 * @version Winter 2014
 **********************************************************************/
public class Knight extends ChessPiece {

	/*******************************************************************
	 * Default constructor for the Knight class that creates a knight
	 * piece.
	 * 
	 * @param player
	 *            Who's piece it is
	 ******************************************************************/

	protected Knight(Player player) {
		super(player);
	}

	/*******************************************************************
	 * Method for the Knight class that returns that it is a knight in
	 * the form of a string.
	 * 
	 * @return String type of piece
	 ******************************************************************/

	public String type() {
		return "Knight";
	}

	/*******************************************************************
	 * Method for the Knight class that assigns a white knight image to
	 * a imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/

	public ImageIcon whiteIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/white_knight.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Knight class that assigns a black knight image to
	 * an imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/

	public ImageIcon blackIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/black_knight.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Knight class that Checks to make sure that the
	 * move a player has made is a valid move.
	 * 
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

		// checks to make sure the move is a valid move
		if (super.isValidMove(move, board)) {

			// if the spot is two down and one to the left
			if ((move.fromColumn + 2 == move.toColumn)
					&& (move.fromRow - 1 == move.toRow)) {

				// returns true if it is
				return true;
			}

			// if the spot is two up and one to the left
			if ((move.fromColumn - 2 == move.toColumn)
					&& (move.fromRow - 1 == move.toRow)) {
				return true;
			}

			// if the spot it two down and one to the right
			if ((move.fromColumn + 2 == move.toColumn)
					&& (move.fromRow + 1 == move.toRow)) {
				return true;
			}

			// if the spot is two up and one to the right
			if ((move.fromColumn - 2 == move.toColumn)
					&& (move.fromRow + 1 == move.toRow)) {
				return true;
			}

			// if the spot is one down and two to the left
			if ((move.fromColumn + 1 == move.toColumn)
					&& (move.fromRow - 2 == move.toRow)) {
				return true;
			}

			// if the spot is one up and two to the left
			if ((move.fromColumn - 1 == move.toColumn)
					&& (move.fromRow - 2 == move.toRow)) {
				return true;
			}

			// if the spot is one down and two to the right
			if ((move.fromColumn + 1 == move.toColumn)
					&& (move.fromRow + 2 == move.toRow)) {
				return true;
			}

			// if the spot is one up and two to the right
			if ((move.fromColumn - 1 == move.toColumn)
					&& (move.fromRow + 2 == move.toRow)) {
				return true;
			}
		}

		// returns false if none of them are the spot the user has
		// chosen
		return false;
	}

	/*******************************************************************
	 * Method for the Knight class that returns what color and type of
	 * piece it is.
	 * 
	 * @return String toString override for current piece type
	 ******************************************************************/
	public String toString() {

		// if it is a black piece
		if (super.player() == Player.BLACK)

			// returns string of black and knight
			return "Black Knight";

		// if it is a white piece
		if (super.player() == Player.WHITE)

			// returns string of white and knight
			return "White Knight";

		// if it is neither
		else

			// returns a blink piece
			return "";

	}

}
