package com.gvsu.cis163.project3;

/*****************************************************************
 * Move Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * Packages the four components of a move into a single object.
 * (Instance variables are public because this object is a simple
 * container.)
 * 
 * @author Michael Baldwin , Douglas Money, Nick Reitz
 * @version Winter 2014
 ******************************************************************/

public class Move {

	/** move from row */
	public int fromRow;

	/** move from column */
	public int fromColumn;

	/** move to row */
	public int toRow;

	/** move from column */
	public int toColumn;

	/*******************************************************************
	 * Default constructor for Move class.
	 ******************************************************************/
	public Move() {
	}

	/*******************************************************************
	 * Constructor for Move class. Sets variables for object container
	 * 
	 * @param fromRow
	 *            accepts move from row
	 * 
	 * @param fromColumn
	 *            accepts move from column
	 * 
	 * @param toRow
	 *            accepts move to row
	 * 
	 * @param toColumn
	 *            accepts move to column
	 * 
	 ******************************************************************/

	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}
}
