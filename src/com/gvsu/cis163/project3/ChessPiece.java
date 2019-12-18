package com.gvsu.cis163.project3;

import javax.swing.ImageIcon;

/***********************************************************************
 * ChessPiece Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * Chess Piece. According to the Model View Controller software pattern,
 * this class controls the Chess Pieces. One exception being the chess
 * piece classes contain image icons which are used in the panel class
 * to emphasize polymorphism.
 * 
 * @author Michael Baldwin , Douglas Money, Nick Reitz
 * @version Winter 2014
 * 
 **********************************************************************/

public abstract class ChessPiece implements IChessPiece {

	/** Player "black" or "white" */
	protected Player owner;

	/*******************************************************************
	 * Constructor for ChessPiece that updates piece owner
	 * 
	 * @param player
	 *            sets this piece to color "BLACK" or "WHITE"
	 ******************************************************************/

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	/*******************************************************************
	 * Abstract method for piece type i.e rook, bishop, pawn , etc
	 ******************************************************************/

	public abstract String type();

	/*******************************************************************
	 * Essentially a getter method that returns this pieces type
	 * 
	 * @return owner return "Player" as in "Black" or "White" piece
	 ******************************************************************/

	public Player player() {

		return owner;

	}

	/*******************************************************************
	 * Abstract method for white players Image Icons
	 ******************************************************************/

	public abstract ImageIcon whiteIcon();

	/*******************************************************************
	 * Abstract method for black players Image Icons
	 ******************************************************************/

	public abstract ImageIcon blackIcon();

	/*******************************************************************
	 * Verify that the starting and ending locations are different.
	 * 
	 * Verify that this piece is located at [move.fromRow,
	 * move.fromColumn] on the board.
	 * 
	 * Verify that[move.toRow,move.toColumn] does not contain a piece
	 * belonging to the same player.
	 * 
	 * @return boolean true or false based on cases stated above
	 ******************************************************************/

	public boolean isValidMove(Move move, IChessPiece[][] board) {

		IChessPiece x = board[move.fromRow][move.fromColumn];
		IChessPiece y = board[move.toRow][move.toColumn];

		// not the same location
		if (((move.fromRow == move.toRow) 
				&& (move.fromColumn == move.toColumn)))
			return false;

		// is this piece located at fromRow fromCol on board
		if (!(this.equals(x))) {

			return false;
		}

		// are players different?
		if (y != null && this.owner.equals(y.player()))
			return false;

		else
			return true;

	}
}
