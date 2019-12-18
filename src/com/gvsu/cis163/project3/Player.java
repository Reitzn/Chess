package com.gvsu.cis163.project3;

/*****************************************************************
 * Player Class
 * 
 * CIS 163-03
 * 
 * Project 3
 * 
 * Enumerated Type Color Representation for Chess Pieces
 * 
 * @author Michael Baldwin , Douglas Money, Nick Reitz
 * 
 * @version Winter 2014
 ******************************************************************/
public enum Player {
	BLACK, WHITE;

	/******************************************************************
	 * Return the {@code Player} whose turn is next.
	 * 
	 * @return the {@code Player} whose turn is next
	 ******************************************************************/
	public Player next() {
		return this == BLACK ? WHITE : BLACK;
	}
}