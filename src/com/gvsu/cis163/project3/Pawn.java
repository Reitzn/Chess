package com.gvsu.cis163.project3;

import javax.swing.ImageIcon;

/***********************************************************************
 * Pawn Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * A Pawn piece that extends ChessPiece. It holds the rules to how the
 * pawn will be able to interact with the board it its laws.
 * 
 * @author Douglas Money, Nick Reitz, Michael Baldwin
 * @version Winter 2014
 **********************************************************************/
public class Pawn extends ChessPiece {

	/*******************************************************************
	 * Default constructor for the Pawn class that creates a pawn piece.
	 * 
	 * @param player
	 *            Who's piece it is
	 ******************************************************************/
	protected Pawn(Player player) {
		super(player);
	}

	/*******************************************************************
	 * Method for the Pawn class that returns that it is a pawn in the
	 * form of a string.
	 * 
	 * @return String type of piece
	 ******************************************************************/
	public String type() {
		return "Pawn";
	}

	/*******************************************************************
	 * Method for the Pawn class that assigns a white pawn image to a
	 * imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/
	public ImageIcon whiteIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/white_pawn.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Pawn class that assigns a black pawn image to an
	 * imageIcon and returns it.
	 * 
	 * @return Icon image icon for chess piece
	 ******************************************************************/
	public ImageIcon blackIcon() {
		ImageIcon Icon = new ImageIcon(getClass().getResource("/resources/icons/black_pawn.png"));
		return Icon;
	}

	/*******************************************************************
	 * Method for the Pawn class that Checks to make sure that the move
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

		// check WHITE pieces
		if ((super.player() == Player.WHITE)) {

			int i = 0;

			// loops through the board
			while (i < 8) {

				IChessPiece temp = board[5][i];

				// checks to make sure there is not a piece in front of
				// it
				if (super.isValidMove(move, board)
					&& ((move.fromRow == 6) && (move.fromColumn == i))
					&& (((move.toRow == 4) && (move.toColumn == i)) 
					|| ((move.toRow == 5) && (move.toColumn == i)))
					&& temp != null)

					// returns false if there is
					return false;

				// make sure there is an black piece in a corner spot to
				// take it
				if (super.isValidMove(move, board)
						&& ((move.fromRow <= 6) 
								&& (move.fromColumn == i))
						&& (move.toRow == move.fromRow - 1)
						&& ((move.toColumn == move.fromColumn + 1) 
						|| (move.toColumn == move.fromColumn - 1))
						&& board[move.toRow][move.toColumn] != null
						&& (board[move.toRow][move.toColumn].player()
								.equals(Player.BLACK))) {

					// returns true if there is
					return true;
				}

				// make sure first move - two spaces or one is what the
				// user has
				// picked
				else if (super.isValidMove(move, board)
					&& ((move.fromRow == 6) && (move.fromColumn == i))
					&& (((move.toRow == 4) && (move.toColumn == i)) 
					|| ((move.toRow == 5) && (move.toColumn == i)))
					&& board[move.toRow][move.toColumn] == null)

					// returns true if it is
					return true;

				// move one space with no capture and not first move
				else if (super.isValidMove(move, board)
					&& ((move.fromRow <= 5) && (move.fromColumn == i))
					&& (move.toRow == move.fromRow - 1)
					&& (move.fromColumn == move.toColumn)
					&& board[move.toRow][move.toColumn] == null)

					// returns true if it is
					return true;

				i++;
			}

		}

		// check BLACK pieces
		else if ((super.player() == Player.BLACK)) {

			int i = 0;

			// loops through the board
			while (i < 8) {

				IChessPiece temp = board[2][i];

				// checks to make sure there is not a piece in front of
				// it
				if (super.isValidMove(move, board)
					&& ((move.fromRow == 1) && (move.fromColumn == i))
					&& (((move.toRow == 3) && (move.toColumn == i)) 
					|| ((move.toRow == 2) && (move.toColumn == i)))
					&& temp != null)

					// returns false if there is
					return false;

				// make sure there is an white piece in a corner spot to
				// take it
				if (super.isValidMove(move, board)
					&& ((move.fromRow >= 1) && (move.fromColumn == i))
						&& (move.toRow == move.fromRow + 1)
						&& ((move.toColumn == move.fromColumn + 1) 
						|| (move.toColumn == move.fromColumn - 1))
						&& board[move.toRow][move.toColumn] != null
						&& (board[move.toRow][move.toColumn].player()
								.equals(Player.WHITE))) {

					// returns true if there is
					return true;
				}

				// make sure first move - two spaces or one is what the
				// user has
				// picked
				else if (super.isValidMove(move, board)
					&& ((move.fromRow == 1) && (move.fromColumn == i))
					&& (((move.toRow == 3) && (move.toColumn == i)) 
					|| ((move.toRow == 2) && (move.toColumn == i)))
					&& board[move.toRow][move.toColumn] == null)

					// returns true if there is
					return true;

				// move one space with no capture and not first move
				else if (super.isValidMove(move, board)
					&& ((move.fromRow >= 2) && (move.fromColumn == i))
					&& (move.toRow == move.fromRow + 1)
					&& (move.fromColumn == move.toColumn)
					&& board[move.toRow][move.toColumn] == null)

					// returns true if there is
					return true;

				i++;

			}

		}

		// returns false if nothing is true
		return false;
	}

	/*******************************************************************
	 * Method for the Pawn class that returns what color and type of
	 * piece it is.
	 * 
	 * @return String toString override for current piece type
	 ******************************************************************/
	public String toString() {

		// if it is a black piece
		if (super.player() == Player.BLACK)

			// returns string of black and pawn
			return "Black Pawn";

		// if it is a white piece
		if (super.player() == Player.WHITE)

			// returns string of white and pawn
			return "White Pawn";

		// if it is neither
		else

			// returns a blank string
			return "";

	}

}
