package com.gvsu.cis163.project3;

import java.awt.Dimension;

import javax.swing.JFrame;

/*******************************************************************
 * ChessGUI Class CIS 163-03 Project 3
 * 
 * ChessGUI initializer for Chess Game
 * 
 * @author Douglas Money, Nick Reitz, Michael Baldwin
 * @version Winter 2014
 *******************************************************************/
public class ChessGUI {

	/*******************************************************************
	 * Main Method for the Chess GUI, Method sets up ChessPanel and
	 * brings in bars
	 * 
	 * @param args
	 *            main args
	 ******************************************************************/
	public static void main(String[] args) {
		JFrame frame = new JFrame("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//initialize panel
		ChessPanel panel = new ChessPanel();
		frame.setJMenuBar(panel.getJMenuBar());
		frame.getContentPane().add(panel);
		frame.setSize(new Dimension(800, 800));
		
		//center frame on screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
