package com.gvsu.cis163.project3;


/*******************************************************************
 * ChessModel Class CIS 163-03 Project 3
 * 
 * Chess game. According to Model View Controller software pattern, this
 * class controls the model (game). One exception being the chess pieces
 * contain image icons that violate this pattern, however this
 * implementation is a functional example of polymorphism.
 * 
 * @author Michael Baldwin, Douglas Money, Nick Reitz
 * @version Winter 2014
 *******************************************************************/
public class ChessModel implements IChessModel {

	/** Constant integer determining size of game board */
	private final int BOARD_SIZE = 8;

	/** Two dimensional array of IChessPiece objects for game board */
	private IChessPiece[][] board;

	/** Player object for determining the current player for game */
	private Player player;

	/** Integer storing row number King object is located at */
	private int kingRow;

	/** Integer storing column number King object is located at */
	private int kingCol;

	/** Integer storing row number temporary King object is found */
	private int tempKingRow;

	/** Integer storing column number temporary King object is found */
	private int tempKingCol;

	/** Boolean determining whether game is complete or not */
	private boolean gameComplete;

	/*******************************************************************
	 * Default constructor for ChessModel game, creates two dimensional
	 * array (board) of IChessPiece objects according to constant for
	 * board size. Then instantiates Pawn, Rook, Knight, Bishop, King,
	 * and Queen objects in proper locations on board. Sets current
	 * player to White (all Chess games start with White player). Also
	 * creates a new ArrayList of type IChessPiece objects to store
	 * chess pieces captured during the game.
	 ******************************************************************/
	public ChessModel() {

		// creates new board of IChessPiece objects
		board = new IChessPiece[BOARD_SIZE][BOARD_SIZE];

		// loops through the board to create 16 Pawns in proper
		// locations
		int i = 0;
		while (i < board.length) {

			// creates a new Pawn object belonging to Black player and
			// a new Pawn object belonging to White player each loop
			board[1][i] = new Pawn(Player.BLACK);
			board[6][i] = new Pawn(Player.WHITE);
			i++;
		}

		// creates 2 new Rook objects belonging to Black player and
		// 2 new Rook objects belonging to White player
		board[0][0] = new Rook(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		board[7][0] = new Rook(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);

		// creates 2 new Knight objects belonging to Black player and
		// 2 new Knight objects belonging to White player
		board[0][1] = new Knight(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[7][1] = new Knight(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);

		// creates 2 new Bishop objects belonging to Black player and
		// 2 new Bishop objects belonging to White player
		board[0][2] = new Bishop(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);

		// creates 2 new King objects belonging to Black player and
		// 2 new King objects belonging to White player
		board[0][4] = new King(Player.BLACK);
		board[7][4] = new King(Player.WHITE);

		// creates 2 new Queen objects belonging to Black player and
		// 2 new Queen objects belonging to White player
		board[0][3] = new Queen(Player.BLACK);
		board[7][3] = new Queen(Player.WHITE);

		// sets current player instance variable to white
		// Chess game rules dictate that white always goes first
		this.player = Player.WHITE;

	}

	/*******************************************************************
	 * Constructor for ChessModel game that accepts a pair of
	 * two-dimensional arrays of type String and a String variable as
	 * parameters. Creates two dimensional array (board) of IChessPiece
	 * objects according to constant for board size. Then instantiates
	 * Pawn, Rook, Knight, Bishop, King, and Queen objects in proper
	 * locations on board according to parameters passed which "load" a
	 * board that was "saved." Sets current player to White (all Chess
	 * games start with White player).
	 * 
	 * @param type
	 *            Two-dimensional array of String type determining what
	 *            type of IChessPiece object.
	 * @param who
	 *            Two-dimensional array of String type determining what
	 *            player owns each chess piece.
	 * @param turn
	 *            String determining which player's turn it is.
	 ******************************************************************/
	public ChessModel(String[][] type, String[][] who, String turn) {

		// creates new board of chess pieces using constant for size
		board = new IChessPiece[BOARD_SIZE][BOARD_SIZE];

		// loop through the board
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {

				// if type is King and player is Black create new
				// King object belonging to Black player at that
				// location
				if (type[row][col].equals("King")
						&& who[row][col].equals("BLACK"))
					board[row][col] = new King(Player.BLACK);

				// else if type King and player White, new White King
				else if (type[row][col].equals("King")
						&& who[row][col].equals("WHITE"))
					board[row][col] = new King(Player.WHITE);

				// else if type Queen and player Black, new Black Queen
				else if (type[row][col].equals("Queen")
						&& who[row][col].equals("BLACK"))
					board[row][col] = new Queen(Player.BLACK);

				// else if type Queen and player White, new White Queen
				else if (type[row][col].equals("Queen")
						&& who[row][col].equals("WHITE"))
					board[row][col] = new Queen(Player.WHITE);

				// else if type Bishop and player Black, new Black
				// Bishop
				else if (type[row][col].equals("Bishop")
						&& who[row][col].equals("BLACK"))
					board[row][col] = new Bishop(Player.BLACK);

				// else if type Bishop and player White, new White
				// Bishop
				else if (type[row][col].equals("Bishop")
						&& who[row][col].equals("WHITE"))
					board[row][col] = new Bishop(Player.WHITE);

				// else if type Knight and player Black, new Black
				// Knight
				else if (type[row][col].equals("Knight")
						&& who[row][col].equals("BLACK"))
					board[row][col] = new Knight(Player.BLACK);

				// else if type Knight and player White, new White
				// Knight
				else if (type[row][col].equals("Knight")
						&& who[row][col].equals("WHITE"))
					board[row][col] = new Knight(Player.WHITE);

				// else if type Rook and player Black, new Black Rook
				else if (type[row][col].equals("Rook")
						&& who[row][col].equals("BLACK"))
					board[row][col] = new Rook(Player.BLACK);

				// else if type Rook and player White, new White Rook
				else if (type[row][col].equals("Rook")
						&& who[row][col].equals("WHITE"))
					board[row][col] = new Rook(Player.WHITE);

				// else if type Pawn and player Black, new Black Pawn
				else if (type[row][col].equals("Pawn")
						&& who[row][col].equals("BLACK"))
					board[row][col] = new Pawn(Player.BLACK);

				// else if type Pawn and player White, new White Pawn
				else if (type[row][col].equals("Pawn")
						&& who[row][col].equals("WHITE"))
					board[row][col] = new Pawn(Player.WHITE);

				// else if type "*" and player "*" (no piece or player),
				// set piece to null since no ChessPiece object there
				else if (type[row][col].equals("*")
						&& who[row][col].equals("*")) {
					board[row][col] = null;
				}
			}
		}

		// if turn is white, sets current player to white
		// else if turn is black sets current player to black
		if (turn.equals("WHITE"))
			player = Player.WHITE;
		else if (turn.equals("BLACK"))
			player = Player.BLACK;
	}

	/*******************************************************************
	 * Public method that returns the board object instance variable
	 * (two-dimensional array of IChessPiece objects).
	 * 
	 * @return IChessPiece[][] two-dimensional array of IChessPiece
	 *         objects holding the pieces on the board.
	 ******************************************************************/
	public IChessPiece[][] getBoard() {

		// return board (two-dimensional array of IChessPiece objects)
		return board;
	}

	/*******************************************************************
	 * Public method that returns the gameComplete instance variable
	 * telling whether the game is complete or not (a player is in
	 * checkmate or not).
	 * 
	 * @return Boolean game status of whether game is complete or not
	 ******************************************************************/
	public boolean getGameStatus() {

		// return whether game is complete or not
		return gameComplete;
	}

	/*******************************************************************
	 * Public method that returns true if the game is complete or false
	 * if the game is not complete. If a player is in checkmate (there
	 * are no more valid moves they can make to get his or her king out
	 * of check), then game is complete. If player can make one or more
	 * valid moves to get out of check, then game is not complete.
	 * 
	 * @return true if complete, false otherwise.
	 ******************************************************************/
	public boolean isComplete() {

		// game is complete unless a valid move is found in this method
		// that results in king not being in check, flag will be
		// returned
		boolean flag = true;

		// loop through the board
		for (int row = 0; row < numRows(); row++) {
			for (int col = 0; col < numColumns(); col++) {

				// if there is a piece at that location and that piece
				// belongs to the current player create temporary piece
				if (board[row][col] != null
						&& board[row][col].player() == this.player) {
					IChessPiece piece = board[row][col];

					// create a new move to each spot on board for that
					// piece
					for (int r = 0; r < numRows(); r++) {
						for (int c = 0; c < numColumns(); c++) {
							Move m = new Move(row, col, r, c);

							// if that piece's move is valid on board
							if (piece.isValidMove(m, board)) {

								// if that potential move does not leave
								// own player's king in check, set
								// returned to false
								if (!ownKingCheck(m, this.player))
									flag = false;
							}

						}
					}
				}
			}
		}

		// if get to this point, return true
		return flag;
	}

	/*******************************************************************
	 * Public method that returns true if the Move object is able to be
	 * performed. Otherwise, returns false if the move is not able to be
	 * carried out according to the game and chess pieces rules.
	 * 
	 * @param move
	 *            a Move object describing the move to be made.
	 * @return Boolean whether move is valid or not
	 * @throws IndexOutOfBoundsException
	 *             if either [move.fromRow, move.fromColumn] or
	 *             [move.toRow, move.toColumn]} don't represent valid
	 *             locations on the board.
	 ******************************************************************/
	public boolean isValidMove(Move move) {

		// if either [move.fromRow][move.fromColumn] or
		// [move.toRow][move.toColumn] don't represent valid
		// locations on board, throw exception
		if ((move.fromRow > numRows() || move.fromRow < 0)
				|| (move.fromColumn > numColumns() 
						|| move.fromColumn < 0)
				|| (move.toRow > numRows() || move.toRow < 0)
				|| (move.toColumn > numColumns() 
						|| move.toColumn < 0)) {

			// throw new IndexOutOfBoundsException
			throw new IndexOutOfBoundsException();
		}

		// if own move leaves own king in check, then can't make move
		if (!ownKingCheck(move, player)) {

			// only create temporary piece at location moving from if
			// a piece exists at that location
			if (board[move.fromRow][move.fromColumn] != null) {
				IChessPiece piece = 
						board[move.fromRow][move.fromColumn];

				// if temporary piece makes valid move, return true
				if (piece.isValidMove(move, board))
					return true;
			}
		}

		// otherwise, if get to this point, return false
		return false;
	}

	/*******************************************************************
	 * Moves the piece from location [move.fromRow, move.fromColumn] to
	 * location [move.fromRow, move.fromColumn].
	 * 
	 * @param move
	 *            a Move object describing the move to be made.
	 * @throws IndexOutOfBoundsException
	 *             if either [move.fromRow, move.fromColumn] or
	 *             [move.toRow, move.toColumn] don't represent valid
	 *             locations on the board.
	 ******************************************************************/
	public void move(Move move) {

		// if either [move.fromRow][move.fromColumn] or
		// [move.toRow][move.toColumn] don't represent valid
		// locations on board, throw exception
		if ((move.fromRow > numRows() || move.fromRow < 0)
				|| (move.fromColumn > numColumns() 
				|| move.fromColumn < 0)
				|| (move.toRow > numRows() || move.toRow < 0)
			    || (move.toColumn > numColumns() 
			    		|| move.toColumn < 0)) {

			// throw new IndexOutOfBoundsException
			throw new IndexOutOfBoundsException();
		}

		// create temporary piece from location moving from
		IChessPiece piece = board[move.fromRow][move.fromColumn];

		// if the Move object is valid and the player who owns the piece
		// is the current player, then move the piece
		if (isValidMove(move) && piece.player() == player) {

			// copy piece from location moving to captured piece
			// location
			board[move.toRow][move.toColumn] = 
					board[move.fromRow][move.fromColumn];

			// remove original piece moving
			board[move.fromRow][move.fromColumn] = null;

			// now switch player's turn
			this.player = player.next();

			// check to see if opponent of player who just moved is in
			// check. If true, ask if game is complete.
			if (inCheck(this.player)) {
				gameComplete = isComplete();
			}
		}

	}

	/*******************************************************************
	 * Private helper method that determines whether it is a valid move
	 * from location [move.fromRow, move.fromColumn] to location
	 * [move.fromRow, move.fromColumn].
	 * 
	 * @param move
	 *            a Move object describing the move to be made.
	 * @return true if valid move, false otherwise.
	 ******************************************************************/
	private boolean isValidMoveKing(Move move) {

		// only create temporary piece at from location if exists
		if (board[move.fromRow][move.fromColumn] != null) {

			IChessPiece piece = board[move.fromRow][move.fromColumn];

			// return true if piece moving is valid
			if (piece.isValidMove(move, board))
				return true;
		}

		// otherwise, return false
		return false;
	}

	/*******************************************************************
	 * Private helper method that determines where a player's king is
	 * located at based on which player is passed as parameter.
	 * 
	 * @param p
	 *            a Player object determining which king to find.
	 ******************************************************************/
	private void findKing(Player p) {

		// loop through board
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {

				// only create temporary piece if exists
				if (board[row][col] != null) {
					IChessPiece piece = board[row][col];

					// if that piece is a king and of current player
					// then put location into instance variables
					if (piece.type().equals("King")
							&& piece.player() == p) {
						kingRow = row;
						kingCol = col;
					}
				}
			}
		}
	}

	/*******************************************************************
	 * Public method that determines whether the player passed in as
	 * parameter is currently in check (player's king is in check).
	 * 
	 * @param p
	 *            a Player object determining which king to ask if
	 *            inCheck
	 * @return true if king is in check, false otherwise
	 ******************************************************************/
	public boolean inCheck(Player p) {

		// finds king of player passed in as parameter
		findKing(p);

		// loop through board
		for (int row = 0; row < numRows(); row++) {
			for (int col = 0; col < numColumns(); col++) {

				// only create temporary move if piece exists at
				// location
				if (pieceAt(row, col) != null) {
					Move tempMove = 
							new Move(row, col, kingRow, kingCol);

					// if valid move to take king with piece, return
					// true
					if (isValidMoveKing(tempMove))
						return true;
				}
			}
		}

		// otherwise, return false
		return false;
	}

	/*******************************************************************
	 * Private helper method that determines where a player's king is
	 * located at based on which player is passed as parameter.
	 * 
	 * @param temp
	 *            Temporary board (IChess[][]) of chess pieces.
	 * @param move
	 *            a Move object describing the move to be made.
	 * @return true if move is valid, otherwise false
	 ******************************************************************/
	private boolean isValidTempMove(IChessPiece[][] temp, Move move) {

		// only create temporary piece if exists at from location
		if (temp[move.fromRow][move.fromColumn] != null) {
			IChessPiece piece = temp[move.fromRow][move.fromColumn];

			// if move is valid on temporary board, return true
			if (piece.isValidMove(move, temp))
				return true;

		}

		// otherwise, return false
		return false;
	}

	/*******************************************************************
	 * Private helper method that determines where a player's king is
	 * located at on a temporary board based on which player is passed
	 * as parameter.
	 * 
	 * @param temp
	 *            Temporary board (IChess[][]) of chess pieces.
	 * @param p
	 *            a Player object determining which temporary king to
	 *            find
	 ******************************************************************/
	private void findTempKing(IChessPiece[][] temp, Player p) {

		// loop through temporary board
		for (int row = 0; row < temp.length; row++) {
			for (int col = 0; col < temp.length; col++) {

				// only create temporary piece if exists at location
				if (temp[row][col] != null) {
					IChessPiece piece = temp[row][col];

					// if that piece is a king and of current player,
					// put location into instance variables
					if (piece.type().equals("King")
							&& piece.player() == p) {
						tempKingRow = row;
						tempKingCol = col;
					}
				}
			}
		}
	}

	/*******************************************************************
	 * Private helper method that determines whether a potential move
	 * puts that player's own king in check based on results of move.
	 * Creates a temporary board to assess results and returns boolean
	 * based on what found.
	 * 
	 * @param p
	 *            a Player object determining which king to check
	 * @param move
	 *            a Move object describing the move to be made.
	 * @return true if move puts own king into check, otherwise false
	 ******************************************************************/
	private boolean ownKingCheck(Move move, Player p) {

		// set variable to return to false
		boolean flag = false;

		// create a temporary chess board and fill with pieces from
		// actual board of game
		IChessPiece[][] temp = new IChessPiece[BOARD_SIZE][BOARD_SIZE];
		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numColumns(); c++) {
				temp[r][c] = pieceAt(r, c);
			}
		}

		// make the temporary move, moving piece from into to place
		temp[move.toRow][move.toColumn] = 
				temp[move.fromRow][move.fromColumn];
		temp[move.fromRow][move.fromColumn] = null;

		// find where the temporary king of player is on temporary board
		findTempKing(temp, p);

		// now check if any pieces can take that color's king by
		// looping through temporary board
		for (int row = 0; row < numRows(); row++) {
			for (int col = 0; col < numColumns(); col++) {

				// only create a move if location holds a chess piece
				if (temp[row][col] != null) {
					Move attackMove = new Move(row, col, tempKingRow,
							tempKingCol);

					// if there are valid moves to attack king after
					// moving temporary piece, return true
					if (isValidTempMove(temp, attackMove))
						flag = true;
				}
			}
		}

		// otherwise return false
		return flag;
	}

	/*******************************************************************
	 * A method that returns the Player object instance variable, which
	 * will return the current player in the game.
	 * 
	 * @return the current player.
	 ******************************************************************/
	public Player currentPlayer() {

		// returns current player in game
		return player;
	}

	/*******************************************************************
	 * A method that returns the number of rows in the game, which is
	 * equal to the constant instance variable for board size.
	 * 
	 * @return Integer representing number of rows on board
	 ******************************************************************/
	public int numRows() {

		// returns the number of rows, which is equal to the constant
		// instance variable for board size
		return BOARD_SIZE;
	}

	/*******************************************************************
	 * A method that returns the number of columns in the game, which is
	 * equal to the constant instance variable for board size.
	 * 
	 * @return Integer representing number of columns on board
	 ******************************************************************/
	public int numColumns() {

		// returns the number of columns, which is equal to the constant
		// instance variable for board size
		return BOARD_SIZE;
	}

	/*******************************************************************
	 * A method that gets the IChessPiece object at location on board
	 * using the values passed as parameters to determine the indices of
	 * the two dimensional array for the board.
	 * 
	 * @param row
	 *            The row numbered 0 through numRows -1
	 * @param col
	 *            The column numbered 0 through numColumns -1
	 * @return the ChessPiece object at location [row, column].
	 * @throws IndexOutOfBoundsException
	 *             if [row, column] is not a valid location on the
	 *             board.
	 ******************************************************************/
	public IChessPiece pieceAt(int row, int column) {

		// if row is less than zero or greater than the number of rows,
		// or if col is less than zero or greater than the number of
		// columns, throw an IndexOutOfBoundsException, else return
		// IChessPiece object located at position on board
		if (row < 0 || column < 0 || row > numRows()
				|| column > numColumns()) {
			throw new IndexOutOfBoundsException();
		} else
			return board[row][column];
	}

	/*******************************************************************
	 * A method that returns a boolean based on whether there is an
	 * IChessPiece object at location on board using the values passed
	 * as parameters to determine the indices of the two dimensional
	 * array for the board. Invokes pieceAt(int row, int column) method
	 * 
	 * @param row
	 *            Index for row that piece is located at on board
	 * @param col
	 *            Index for column that piece is located at on board
	 * @return Boolean whether there is a piece at location or not
	 ******************************************************************/
	public boolean hasPiece(int row, int column) {

		// if there is a IChessPiece object at location on board,
		// return true, else return false
		if (pieceAt(row, column) != null)
			return true;
		else
			return false;

	}



}
