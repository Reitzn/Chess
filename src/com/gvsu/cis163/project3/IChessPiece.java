package com.gvsu.cis163.project3;

import javax.swing.ImageIcon;

/***********************************************************************
 * IChessPiece Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * Interface Chess Piece. According to the Model View Controller
 * software pattern, this class controls the Chess Pieces. One exception
 * being the chess piece classes contain image icons which are used in
 * the panel class to emphasize polymorphism.
 * 
 * @author Michael Baldwin , Douglas Money, Nick Reitz
 * @version Winter 2014
 * 
 **********************************************************************/

public interface IChessPiece {

	/**
	 * Return the player that owns this piece.
	 * 
	 * @return the player that owns this piece.
	 */
	Player player();

	ImageIcon whiteIcon();

	ImageIcon blackIcon();

	/**
	 * Return the type of this piece ("King", "Queen", "Rook", etc.).
	 * Note: In this case "type" refers to the game of chess, not the
	 * type of the Java class.
	 * 
	 * @return the type of this piece
	 */
	String type();

	/**
	 * Returns whether the piece at location
	 * {@code [move.fromRow, move.fromColumn]} is allowed to move to
	 * location {@code [move.fromRow, move.fromColumn]}.
	 * 
	 * Note: Pieces don't store their own location (because doing so
	 * would be redundant). Therefore, the
	 * {@code [move.fromRow, move.fromColumn]} component of {@code move}
	 * is necessary. {@code this} object must be the piece at location
	 * {@code [move.fromRow, move.fromColumn]}. (This method makes no
	 * sense otherwise.)
	 * 
	 * @param move
	 *            a {@link com.gvsu.cis163.project3.Move} object describing the move
	 *            to be made.
	 * @param board
	 *            the {@link com.gvsu.cis163.project3.IChessModel} in which this
	 *            piece resides.
	 * @return {@code true} if the proposed move is valid, {@code false}
	 *         otherwise.
	 * @throws IndexOutOfBoundsException
	 *             if either {@code [move.fromRow, move.fromColumn]} or
	 *             {@code [move.toRow,
	 *                                   move.toColumn]} don't represent
	 *             valid locations on the board.
	 * @throws IllegalArgumentException
	 *             if {@code this} object isn't the piece at location
	 *             {@code [move.fromRow, move.fromColumn]}.
	 */
	boolean isValidMove(Move move, IChessPiece[][] board);

}