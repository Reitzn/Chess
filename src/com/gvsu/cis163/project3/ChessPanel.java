package com.gvsu.cis163.project3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/***********************************************************************
 * ChessPanel 
 * Class CIS 163-03
 * Project 3
 * 
 * Chess panel. According to the Model View Controller software pattern,
 * this class controls the model (game). One exception being the chess
 * piece classes contain image icons which are used in the panel class
 * to emphasize polymorphism.
 * 
 * @author Michael Baldwin , Douglas Money, Nick Reitz
 * @version Winter 2014
 * 
 **********************************************************************/
public class ChessPanel extends JPanel {

	/** JButton 2-D array for game board */
	private JButton[][] board;

	/** Game Model */
	private ChessModel model;

	/** boolean array for highlighting */
	private static boolean[][] isChecked;

	/** JFrame panel */
	private JPanel center;

	/** JFrame panel */
	private JPanel right;

	/** JFrame panel */
	private JPanel top;

	/** JFrame panel */
	private JPanel bottom;

	/** Move passed into game engine */
	private Move move;

	/** Button Listener */
	private ButtonListener listener;

	/** Mouse Listener */
	private MouseListen mouseListener;

	/** JLabel for updating current players turn */
	private JLabel turnLabel;

	/** JMenuBar */
	private JMenuBar menuBar;

	/** JMenu */
	private JMenu file;

	/** JMenu */
	private JMenu quickMates;

	/** JMenuItem */
	private JMenuItem save;

	/** JMenuItem */
	private JMenuItem load;

	/** JMenuItem */
	private JMenuItem newGame;

	/** JMenuItem */
	private JMenuItem exitGame;

	/** JMenuItem */
	private JMenuItem hippoCheck;

	/** JMenuItem */
	private JMenuItem legallsCheck;

	/*******************************************************************
	 * Default constructor used for Chess Game GUI. Sets up properties
	 * such as JMenuBars and JPanels and instantiates new game. Also
	 * sets up initial game board 
	 ******************************************************************/
	public ChessPanel() {

		// Set Panels
		right = new JPanel();
		top = new JPanel();
		center = new JPanel();
		bottom = new JPanel();

		// Sets Layouts
		this.setLayout(new BorderLayout());
		top.setLayout(new FlowLayout());
		bottom.setLayout(new FlowLayout());
		right.setLayout(new BorderLayout());

		// Add Panel
		add(center, BorderLayout.CENTER);
		add(top, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);
		add(right, BorderLayout.EAST);

		// Setup JMenus
		menuBar = new JMenuBar();
		file = new JMenu("File");
		quickMates = new JMenu("Quick Mates");
		save = new JMenuItem("Save Game");
		load = new JMenuItem("Load Game");
		exitGame = new JMenuItem("Exit Game");
		newGame = new JMenuItem("New Game");
		hippoCheck = new JMenuItem("Hippo Check");
		legallsCheck = new JMenuItem("Legall's Check");

		// add(menuBar);
		menuBar.add(file);
		menuBar.add(quickMates);

		file.add(newGame);
		file.add(save);
		file.add(load);
		file.add(exitGame);

		quickMates.add(hippoCheck);
		quickMates.add(legallsCheck);

		// board,model, and move instantiation

		// blackGraveBoard = new JButton[][];
		board = new JButton[8][8];
		model = new ChessModel();
		move = new Move();

		turnLabel = new JLabel();
		top.add(turnLabel);
		turnLabel.setText(model.currentPlayer() + " Player's Turn");

		// Listeners
		listener = new ButtonListener();
		mouseListener = new MouseListen();

		legallsCheck.addActionListener(listener);
		hippoCheck.addActionListener(listener);

		save.addActionListener(listener);
		load.addActionListener(listener);
		newGame.addActionListener(listener);
		exitGame.addActionListener(listener);

		isChecked = new boolean[8][8];

		// Initial Board Setup
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board.length; col++) {
				board[row][col] = new JButton("");
				board[row][col].addActionListener(listener);
				board[row][col].addMouseListener(mouseListener);
			}

		displayBoard();

	}

	/*******************************************************************
	 * Visual representation of Chess that handles set up of Board.
	 * Handles square colors, and piece Icon setup. This method will
	 * update the piece ImageIcons via Polymorphism, but in theory will
	 * violate MVC. For purposes of this project we feel it works
	 ******************************************************************/
	private void displayBoard() {

		center.removeAll();
		center.revalidate();
		center.repaint();

		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board.length; col++) {

				// resets and sets boarders
				board[row][col].setBorder(null);
				board[row][col].setBorder(BorderFactory
						.createMatteBorder(1, 1, 1, 1, Color.BLACK));

				// handles square colors
				if ((row % 2 == 0 && col % 2 == 0)
						|| (col % 2 == 1 && row % 2 == 1)) {

					board[row][col].setBackground(Color.GRAY);
					board[row][col].setOpaque(true);

				} else {
					board[row][col].setBackground(Color.WHITE);
					board[row][col].setOpaque(true);
				}

				// handles pieceIcons
				if (model.hasPiece(row, col)
						&& model.pieceAt(row, col).player()
								.equals(Player.WHITE)) {

					board[row][col].setIcon(model.pieceAt(row, col)
							.whiteIcon());
					board[row][col].setText(null);
				}

				else if (model.hasPiece(row, col)
						&& model.pieceAt(row, col).player()
								.equals(Player.BLACK)) {

					board[row][col].setIcon(model.pieceAt(row, col)
							.blackIcon());
					board[row][col].setText(null);

				}

				else {
					board[row][col].setIcon(null);
					board[row][col].setText(null);

				}

				highLight();

				// re-add panel after reset and update current player
				// turn label
				center.add(board[row][col]);
				center.add(board[row][col]);
				turnLabel.setText(model.currentPlayer()
						+ " Player's Turn");
				center.setLayout(new GridLayout(8, 8));
				center.setBorder(new EmptyBorder(15, 70, 50, 50));
				top.setBorder(new EmptyBorder(15, 10, 10, 10));

			}

	}

	/*******************************************************************
	 * Changes surrounding boarder of the "clicked" cell and also legal
	 * moves that the specific piece can move to. Called in
	 * displayBoard()
	 ******************************************************************/

	private void highLight() {

		// makes every valid move on the board and changes the boarder
		// color
		// if it is a valid move
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {

				// avoids null pointer
				if (board[row][col] != null
						&& model.pieceAt(row, col) != null) {

					// boolean array is updated in mouse event
					// controller
					if (isChecked[row][col]
							&& model.pieceAt(row, col).player()
									.equals(model.currentPlayer())) {

						board[row][col].setBorder(BorderFactory
								.createMatteBorder(4, 4, 4, 4,
										Color.red));

						for (int r = 0; r < board.length; r++)
							for (int c = 0; c < board.length; c++) {

								// sets up temporary move
							Move tempMove = new Move(row, col, r, c);

								// valid move results in highlighted
								// squares
								if (model.isValidMove(tempMove)) {

									board[r][c].setBorder(BorderFactory
											.createMatteBorder(4, 4, 4,
													4, Color.red));

								}

							}

					}

				}
			}
		}
	}

	/*******************************************************************
	 * Method that saves the current game state to a text document, used
	 * in JMenu 
	 ******************************************************************/
	private void saveGame() {

		PrintWriter out = null;

		try {

			out = new PrintWriter(new BufferedWriter(new FileWriter(

			"src/resources/files/game/pieces.txt")));

		} catch (IOException e) {

			e.printStackTrace();

			System.out.println("Can't save game.");

		}

		// prints in the document
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board.length; col++) {

				if (model.hasPiece(row, col)) {

					out.println(model.pieceAt(row, col).type());

				} else {

					out.println("*");

				}
			}

		out.println(model.currentPlayer());

		// closes the document
		out.close();

		try {

			out = new PrintWriter(new BufferedWriter(new FileWriter(

			"src/resources/files/game/player.txt")));

		} catch (IOException e) {

			e.printStackTrace();

			System.out.println("sory");

		}

		// prints in the document
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board.length; col++) {

				if (model.hasPiece(row, col)) {

					out.println(model.pieceAt(row, col).player());

				} else {

					out.println("*");

				}

			}

		// closes the document
		out.close();

		try {

			// writes or "saves" current players turn to .txt
			out = new PrintWriter(new BufferedWriter(new FileWriter(

			"src/resources/files/game/turn.txt")));

		} catch (IOException e) {

			e.printStackTrace();

			System.out.println("sorry");

		}

		out.print(model.currentPlayer());
		out.close();

	}

	/*******************************************************************
	 * Method that loads the saved game state from a text document, used
	 * in JMenu 
	 ******************************************************************/

	private void loadGame() {

		String[][] type = new String[8][8];

		String[][] who = new String[8][8];

		String turn = "";

		Scanner fileReader;

		try {

			fileReader = new Scanner(new File("src/resources/files/game/pieces.txt"));

			for (int row = 0; row < board.length; row++)

				for (int col = 0; col < board.length; col++) {

					type[row][col] = fileReader.nextLine();

				}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null,
					"No Games Saved");

		}

		try {

			fileReader = new Scanner(new File("src/resources/files/game/player.txt"));

			for (int row = 0; row < board.length; row++)

				for (int col = 0; col < board.length; col++) {

					who[row][col] = fileReader.nextLine();

				}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		try {

			fileReader = new Scanner(new File("src/resources/files/game/turn.txt"));

			turn = fileReader.nextLine();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		model = new ChessModel(type, who, turn);

		displayBoard();

	}

	/*******************************************************************
	 * Method that loads pre-made game states to a text document, used
	 * in JMenu
	 ******************************************************************/

	private void loadHippoMate() {

		String[][] type = new String[8][8];

		String[][] who = new String[8][8];

		String turn = "";

		Scanner fileReader;

		try {

			fileReader = new Scanner(new File("src/resources/files/quickmates/hippoPieces.txt"));

			for (int row = 0; row < board.length; row++)

				for (int col = 0; col < board.length; col++) {

					type[row][col] = fileReader.nextLine();

					// System.out.print(type[row][col]);

				}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		try {

			fileReader = new Scanner(new File("src/resources/files/quickmates/hippoPlayer.txt"));

			for (int row = 0; row < board.length; row++)

				for (int col = 0; col < board.length; col++) {

					who[row][col] = fileReader.nextLine();

					// System.out.print(type[row][col]);

				}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		try {

			fileReader = new Scanner(new File("src/resources/files/quickmates/hippoTurn.txt"));

			turn = fileReader.nextLine();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		model = new ChessModel(type, who, turn);

		displayBoard();

	}

	/*******************************************************************
	 * Method that loads pre-made game states to a text document, used
	 * in JMenu
	 ******************************************************************/

	private void loadLegallsMate() {

		String[][] type = new String[8][8];

		String[][] who = new String[8][8];

		String turn = "";

		Scanner fileReader;

		try {

			fileReader = new Scanner(new File("src/resources/files/quickmates/legallsPieces.txt"));

			for (int row = 0; row < board.length; row++)

				for (int col = 0; col < board.length; col++) {

					type[row][col] = fileReader.nextLine();

					// System.out.print(type[row][col]);

				}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		try {

			fileReader = new Scanner(new File("src/resources/files/quickmates/legallsPlayers.txt"));

			for (int row = 0; row < board.length; row++)

				for (int col = 0; col < board.length; col++) {

					who[row][col] = fileReader.nextLine();

					// System.out.print(type[row][col]);

				}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {

			fileReader = new Scanner(new File("src/resources/files/quickmates/legallsTurn.txt"));

			turn = fileReader.nextLine();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		model = new ChessModel(type, who, turn);

		displayBoard();

	}

	/*******************************************************************
	 * Method that creates new game of chess
	 ******************************************************************/

	private void newGame() {

		model = new ChessModel();

		displayBoard();

	}

	/*******************************************************************
	 * Getter Method called in ChessGUI.java for placing bar accordingly
	 * 
	 * @return menuBar returns JMenuBar 
	 ******************************************************************/

	public JMenuBar getJMenuBar() {
		return menuBar;

	}

	/*******************************************************************
	 * Action Listener class that controls JMenu options i.e saveGame,
	 * loadGame, newGame
	 ******************************************************************/

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == save) {
				saveGame();
			}
			if (event.getSource() == load) {
				loadGame();
			}
			if (event.getSource() == newGame) {
				newGame();
			}

			if (event.getSource() == exitGame) {
				System.exit(0);
			}

			if (event.getSource() == hippoCheck) {
				loadHippoMate();
			}
			if (event.getSource() == legallsCheck) {
				loadLegallsMate();
			}

		}

	}

	/*******************************************************************
	 * Mouse Listener class that controls setting up moves to be passed
	 * into the Chess Game Engine. If a Player goes into check JDialog
	 * will let you know, also if game is over JDiloag will let you know
	 ******************************************************************/
	private class MouseListen implements MouseListener {

		// "click counter" needed for creating piece moves
		boolean secondClick = true;

		// move from row
		int fromRow;

		// move from column
		int fromCol;

		// move to row
		int toRow;

		// move to column
		int toCol;

		/***************************************************************
		 * Method not used
		 * @param event not used
		 **************************************************************/

		public void mouseClicked(MouseEvent event) {

		}

		/***************************************************************
		 * Method not used
		 * @param event not used
		 **************************************************************/

		public void mousePressed(MouseEvent event) {

		}

		/***************************************************************
		 * Sets up new move for use in game engine. Then passes move
		 * into game.Updates isChecked[][] used for saving and loading
		 * game states. Uses JDialog boxes to warn user of Check and
		 * CheckMate situations.
		 * @param event gets "clicked" location on board
		 **************************************************************/

		public void mouseReleased(MouseEvent event) {

			for (int r = 0; r < board.length; r++)
				for (int c = 0; c < board.length; c++) {

					// boolean array used for highlighting
					isChecked[r][c] = false;

					// sets up move and sends it to game
					if (board[r][c] == event.getSource()
							&& !secondClick) {

						toRow = r;
						toCol = c;

						move = new Move(fromRow, fromCol, toRow, toCol);

						model.move(move);

						// is king in check
						if (model.inCheck(model.currentPlayer())) {
							JOptionPane.showMessageDialog(null,
									model.currentPlayer()
											+ " king in check");
						}

						// is game over? it is? ok.. I hope you won
						// create new game
						if (model.getGameStatus()) {

							JOptionPane.showMessageDialog(null,
									"Checkmate "
											+ model.currentPlayer()
													.next()
											+ " has won!! "
											+ "Please select New "
											+ "Game from file menu");
							// newGame();
						}

						secondClick = true;

						// sets up move
					} else if (board[r][c] == event.getSource()
							&& model.hasPiece(r, c)) {

						isChecked[r][c] = true;
						fromRow = r;
						fromCol = c;
						secondClick = false;
					}

				}

			displayBoard();

		}

		/***************************************************************
		 * Method not used
		 * @param event not used
		 **************************************************************/

		public void mouseEntered(MouseEvent arg0) {

		}

		/***************************************************************
		 * Method not used
		 * @param event  not used
		 **************************************************************/

		public void mouseExited(MouseEvent arg0) {

		}

	}

}
