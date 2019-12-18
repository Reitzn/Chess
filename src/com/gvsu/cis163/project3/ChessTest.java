package com.gvsu.cis163.project3;

import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************
 * ChessTest Class CIS 163-03 Project 3
 * 
 * Junit Tests to test the accuracy and validity of the overall chess
 * game model, including mainly the ChessModel class, but also other
 * classes involved in the game, such as Pawn class, Rook class, King
 * class, Queen class, and Knight class, and Bishop class.
 * 
 * @author Douglas Money, Nick Reitz, Michael Baldwin
 * @version Winter 2014
 *******************************************************************/
public class ChessTest {

	/** Two dimensional array of IChessPiece objects for test board */
	private IChessPiece[][] board;

	/** Move object for use in test methods */
	private Move move;

	/** ChessModel object for use in test methods */
	private ChessModel model;

	/*******************************************************************
	 * Default constructor for ChessTest class. Instantiates a new two
	 * dimensional array board of IChessPiece objects and a new
	 * ChessModel object.
	 ******************************************************************/
	public ChessTest() {

		// creates two dimensional array board of IChessPiece objects
		board = new IChessPiece[8][8];

		// instantiates a new ChessModel object
		model = new ChessModel();
	}

	/*******************************************************************
	 * Method that resets the board of pieces. Instantiates a new a two
	 * dimensional array board of IChessPiece objects.
	 ******************************************************************/
	public void resetBoard() {

		// creates a new 2-D array (board) of IChessPiece objects
		board = new IChessPiece[8][8];
	}

	/*******************************************************************
	 * Test method that tests the Pawn class. Tests the valid and
	 * invalid movements of a Pawn object in the chess game.
	 ******************************************************************/
	@Test
	public void testPawnMoves() {

		// reset board first
		resetBoard();

		// instantiate Pawn object belonging to white player
		Pawn p = new Pawn(Player.WHITE);

		// put White pawn into place to test valid and invalid moves
		board[6][4] = p;

		// create Move object, tell White Pawn to move onto self
		// (invalid move) because can't move from and to same location,
		// assert move is invalid / false
		move = new Move(6, 4, 6, 4);
		assertFalse(p.isValidMove(move, board));

		// drop another white pawn in front of White Pawn and tell white
		// pawn move two forward (invalid move - because can't jump
		// over piece), assert move is invalid / false
		Pawn blockingPawn = new Pawn(Player.WHITE);
		board[5][4] = blockingPawn;
		move = new Move(6, 4, 4, 4);
		assertFalse(p.isValidMove(move, board));

		// first remove blocking pawn from board
		// tell White pawn two forward (valid move)
		// assert that move is valid / true
		board[5][4] = null;
		move = new Move(6, 4, 4, 4);
		assertTrue(p.isValidMove(move, board));

		// Remove White pawn from old location, move pawn to new
		// location
		// White pawn to move one backward (invalid move)
		// assert that move is invalid / false
		board[6][4] = null;
		board[4][4] = p;
		move = new Move(4, 4, 5, 4);
		assertFalse(p.isValidMove(move, board));

		// White pawn to move sideways to the left (invalid move)
		// assert that move is invalid / false
		move = new Move(4, 4, 4, 3);
		assertFalse(p.isValidMove(move, board));

		// White pawn to move sideways to the right (invalid move)
		// assert that move is invalid / false
		move = new Move(4, 4, 4, 5);
		assertFalse(p.isValidMove(move, board));

		// White pawn to move forwards two (invalid move - because
		// pawn already moved once), assert that move is invalid / false
		move = new Move(4, 4, 2, 4);
		assertFalse(p.isValidMove(move, board));

		// White pawn to move forwards one (valid move)
		// assert that move is valid / true
		move = new Move(4, 4, 3, 4);
		assertTrue(p.isValidMove(move, board));

		// drop another White pawn onto board diagonally of White pawn
		// White pawn to take diagonally second white pawn (invalid move
		// - because can't take own player's pieces
		board[4][4] = null;
		board[3][4] = p;
		Pawn whitePawn = new Pawn(Player.WHITE);
		board[2][4] = whitePawn;
		move = new Move(3, 4, 2, 4);
		assertFalse(p.isValidMove(move, board));

		// drop Black pawn onto board in front of White Pawn
		// White pawn to move forwards one (invalid move - because
		// Black pawn blocking move), assert that move is invalid /
		// false
		Pawn blackPawn = new Pawn(Player.BLACK);
		board[2][4] = blackPawn;
		move = new Move(3, 4, 2, 4);
		assertFalse(p.isValidMove(move, board));

		// drop Black pawn onto board diagonally front left of White
		// Pawn
		// White pawn to move diagonally forwards to the left (valid
		// move), assert that move is valid / true
		board[2][3] = blackPawn;
		move = new Move(3, 4, 2, 3);
		assertTrue(p.isValidMove(move, board));

		// drop Black pawn onto board diagonally front right of White
		// White pawn to move diagonally forwards to the right (valid
		// move), assert that move is valid / true
		board[3][4] = null;
		board[2][3] = p;
		board[1][4] = blackPawn;
		move = new Move(2, 3, 1, 4);
		assertTrue(p.isValidMove(move, board));
	}

	/*******************************************************************
	 * Test method that tests the Rook class. Tests the valid and
	 * invalid movements of a Rook object in the chess game.
	 ******************************************************************/
	@Test
	public void testRookMoves() {

		// reset board first
		resetBoard();

		// instantiate Rook object belonging to white player
		Rook r = new Rook(Player.WHITE);

		// put White rook into place to test valid and invalid moves
		board[7][0] = r;

		// create Move object, tell White Rook to move onto self
		// (invalid move - because can't move from and to same location,
		// assert move is invalid / false
		move = new Move(7, 0, 7, 0);
		assertFalse(r.isValidMove(move, board));

		// drop White Pawn onto board in front of White Rook, tell rook
		// to move to pawn (invalid move - because can't take own
		// color's
		// pieces), assert move is invalid / false
		Pawn whitePawn = new Pawn(Player.WHITE);
		board[6][0] = whitePawn;
		move = new Move(7, 0, 6, 0);
		assertFalse(r.isValidMove(move, board));

		// White Rook to move past / jump over white pawn in the way,
		// (invalid move - because shouldn't be able to jump over),
		// assert move is invalid / false
		move = new Move(7, 0, 5, 0);
		assertFalse(r.isValidMove(move, board));

		// White Rook to move diagonally up to the right (invalid move -
		// because rook can't move on diagonals), assert invalid / false
		move = new Move(7, 0, 6, 1);
		assertFalse(r.isValidMove(move, board));

		// drop black Pawn onto board to the right of White Rook, tell
		// rook to move to black pawn (valid move), assert valid / true
		Pawn blackPawn = new Pawn(Player.BLACK);
		board[7][1] = blackPawn;
		move = new Move(7, 0, 7, 1);
		assertTrue(r.isValidMove(move, board));

		// White Rook to move six spaces right (valid move), assert
		// move is valid / true
		board[7][0] = null;
		board[7][1] = r;
		move = new Move(7, 1, 7, 7);
		assertTrue(r.isValidMove(move, board));

		// White Rook to move three spaces left (valid move), assert
		// move
		// is valid / true
		board[7][1] = null;
		board[7][7] = r;
		move = new Move(7, 7, 7, 4);
		assertTrue(r.isValidMove(move, board));

		// White Rook to move two forward (valid move), assert true
		board[7][7] = null;
		board[7][4] = r;
		move = new Move(7, 4, 5, 4);
		assertTrue(r.isValidMove(move, board));

		// White Rook to move one backwards (valid move), assert true
		board[7][4] = null;
		board[5][4] = r;
		move = new Move(5, 4, 6, 4);
		assertTrue(r.isValidMove(move, board));

		// move white Rook diagonally down to the left (invalid move -
		// because Rook can't move diagonally), assert invalid / false
		board[5][4] = null;
		board[6][4] = r;
		move = new Move(6, 4, 7, 3);
		assertFalse(r.isValidMove(move, board));

		// drop white pawn two ahead of white Rook and drop black pawn
		// two more ahead of white pawn, move white Rook to black pawn
		// (invalid move - because can't jump pieces), assert false
		board[4][4] = whitePawn;
		board[2][4] = blackPawn;
		move = new Move(6, 4, 2, 4);
		assertFalse(r.isValidMove(move, board));

		// remove white pawn blocking, move white Rook to black pawn
		// (valid move), assert move is valid / true
		board[4][4] = null;
		move = new Move(6, 4, 2, 4);
		assertTrue(r.isValidMove(move, board));
	}

	/*******************************************************************
	 * Test method that tests the King class. Tests the valid and
	 * invalid movements of a King object in the chess game.
	 ******************************************************************/
	@Test
	public void testKingMoves() {

		// reset board first
		resetBoard();

		// instantiate King object belonging to white player
		King k = new King(Player.WHITE);

		// put White king into place to test valid and invalid moves
		board[7][4] = k;

		// create Move object, tell White King to move onto self
		// (invalid move - because can't move from and to same location,
		// assert move is invalid / false
		move = new Move(7, 4, 7, 4);
		assertFalse(k.isValidMove(move, board));

		// drop White Pawn onto board in front of White king, tell king
		// to move to pawn (invalid move - because can't take own
		// color's
		// pieces), assert move is invalid / false
		Pawn whitePawn = new Pawn(Player.WHITE);
		board[6][4] = whitePawn;
		move = new Move(7, 4, 6, 4);
		assertFalse(k.isValidMove(move, board));

		// remove white Pawn and move white king one forward (valid
		// move)
		// , assert move is valid / true
		board[6][4] = null;
		move = new Move(7, 4, 6, 4);
		assertTrue(k.isValidMove(move, board));

		// move white king one sideways to the right (valid move),
		// assert
		// move is valid / true
		board[7][4] = null;
		board[6][4] = k;
		move = new Move(6, 4, 6, 5);
		assertTrue(k.isValidMove(move, board));

		// move white king one sideways to the left (valid move), assert
		// move is valid / true
		board[6][4] = null;
		board[6][5] = k;
		move = new Move(6, 5, 6, 4);
		assertTrue(k.isValidMove(move, board));

		// move white king one backwards (valid move), assert move is
		// valid / true
		board[6][5] = null;
		board[6][4] = k;
		move = new Move(6, 4, 7, 4);
		assertTrue(k.isValidMove(move, board));

		// move white king one upwards right diagonally (valid move),
		// assert move is valid / true
		board[6][4] = null;
		board[7][4] = k;
		move = new Move(7, 4, 6, 3);
		assertTrue(k.isValidMove(move, board));

		// move white king one backwards left diagonally (valid move),
		// assert move is valid / true
		board[7][4] = null;
		board[6][3] = k;
		move = new Move(6, 3, 7, 2);
		assertTrue(k.isValidMove(move, board));

		// move white king two forwards (invalid move - because king can
		// only ever move one space any way), assert move invalid /
		// false
		move = new Move(6, 3, 4, 3);
		assertFalse(k.isValidMove(move, board));

		// move white king two sideways left (invalid move - because
		// king
		// can move one space only), assert move invalid / false
		move = new Move(6, 3, 6, 1);
		assertFalse(k.isValidMove(move, board));

		// move white king two sideways right (invalid move - because
		// king can move one space only), assert move invalid / false
		move = new Move(6, 3, 6, 5);
		assertFalse(k.isValidMove(move, board));

		// move white king two diagonal up-left (invalid move - because
		// king can move one space only), assert move invalid / false
		move = new Move(6, 3, 4, 1);
		assertFalse(k.isValidMove(move, board));

		// move white king two diagonal up-right (invalid move - because
		// king can move one space only), assert move invalid / false
		move = new Move(6, 3, 4, 5);
		assertFalse(k.isValidMove(move, board));

		// drop Black Pawn onto board in diagonal left-down behind White
		// king, move king to pawn (valid move), assert valid / true
		Pawn blackPawn = new Pawn(Player.BLACK);
		board[7][2] = blackPawn;
		move = new Move(6, 3, 7, 2);
		assertTrue(k.isValidMove(move, board));
	}

	/*******************************************************************
	 * Test method that tests the Queen class. Tests the valid and
	 * invalid movements of a Queen object in the chess game.
	 ******************************************************************/
	@Test
	public void testQueenMoves() {

		// reset board first
		resetBoard();

		// instantiate King object belonging to white player
		Queen q = new Queen(Player.WHITE);

		// put White king into place to test valid and invalid moves
		board[7][3] = q;

		// create Move object, tell White Queen to move onto self
		// (invalid move - because can't move from and to same location,
		// assert move is invalid / false
		move = new Move(7, 3, 7, 3);
		assertFalse(q.isValidMove(move, board));

		// drop White Pawn onto board in front of White Queen, queen
		// to move to pawn (invalid move - because can't take own
		// color's
		// pieces), assert move is invalid / false
		Pawn whitePawn = new Pawn(Player.WHITE);
		board[6][3] = whitePawn;
		move = new Move(7, 3, 6, 3);
		assertFalse(q.isValidMove(move, board));

		// remove white Pawn and move Queen one space forward (valid
		// move), assert move is valid / true
		board[6][3] = null;
		move = new Move(7, 3, 6, 3);
		assertTrue(q.isValidMove(move, board));

		// move white Queen three spaces forward (valid move), assert
		// move is valid / true
		board[7][3] = null;
		board[6][3] = q;
		move = new Move(6, 3, 3, 3);
		assertTrue(q.isValidMove(move, board));

		// move white Queen two spaces backwards (valid move), assert
		// move is valid / true
		board[6][3] = null;
		board[3][3] = q;
		move = new Move(3, 3, 5, 3);
		assertTrue(q.isValidMove(move, board));

		// move white Queen four spaces right (valid move), assert
		// move is valid / true
		board[3][3] = null;
		board[5][3] = q;
		move = new Move(5, 3, 5, 7);
		assertTrue(q.isValidMove(move, board));

		// move white Queen five spaces left (valid move), assert
		// move is valid / true
		board[5][3] = null;
		board[5][7] = q;
		move = new Move(5, 7, 5, 2);
		assertTrue(q.isValidMove(move, board));

		// move white Queen two spaces diagonally up-right (valid move),
		// assert move is valid / true
		board[5][7] = null;
		board[5][2] = q;
		move = new Move(5, 2, 3, 4);
		assertTrue(q.isValidMove(move, board));

		// move white Queen three spaces diagonally down-left (valid
		// move), assert move is valid / true
		board[5][2] = null;
		board[3][4] = q;
		move = new Move(3, 4, 6, 1);
		assertTrue(q.isValidMove(move, board));

		// move white Queen two spaces forward, one space right (invalid
		// move - because like a knight), assert move is invalid / false
		board[3][4] = null;
		board[6][1] = q;
		move = new Move(6, 1, 4, 2);
		assertFalse(q.isValidMove(move, board));

		// move white Queen one space forward, two spaces right (invalid
		// move - because like a knight), assert move is invalid / false
		move = new Move(6, 1, 5, 3);
		assertFalse(q.isValidMove(move, board));

		// drop black Pawn onto board four spaces diagonally up-right,
		// and drop white Pawn onto board blocking move, move Queen to
		// black pawn (invalid move - because can't jump pieces), assert
		// move is invalid / false
		Pawn blackPawn = new Pawn(Player.BLACK);
		board[2][5] = blackPawn;
		board[4][3] = whitePawn;
		move = new Move(6, 1, 2, 5);
		assertFalse(q.isValidMove(move, board));

		// remove white Pawn from board, move Queen to black pawn (valid
		// move), assert move is valid / true
		board[4][3] = null;
		move = new Move(6, 1, 2, 5);
		assertTrue(q.isValidMove(move, board));
	}

	/*******************************************************************
	 * Test method that tests the Knight class. Tests the valid and
	 * invalid movements of a Knight object in the chess game.
	 ******************************************************************/
	@Test
	public void testKnightMoves() {

		// reset board first
		resetBoard();

		// instantiate Knight object belonging to white player
		Knight k = new Knight(Player.WHITE);

		// put White knight into place to test valid and invalid moves
		board[7][1] = k;

		// create Move object, tell White Knight to move onto self
		// (invalid move - because can't move from and to same location,
		// assert move is invalid / false
		move = new Move(7, 1, 7, 1);
		assertFalse(k.isValidMove(move, board));

		// drop White Pawn onto board, knight to move to white pawn
		// (invalid move - because can't take own color's pieces),
		// assert
		// move is invalid / false
		Pawn whitePawn = new Pawn(Player.WHITE);
		board[5][2] = whitePawn;
		move = new Move(7, 1, 5, 2);
		assertFalse(k.isValidMove(move, board));

		// drop Black pawn onto board, White knight to move to black
		// pawn
		// (valid move - because can move two forward, one sideways - in
		// an L-shaped pattern), assert move is valid / true
		Pawn blackPawn = new Pawn(Player.BLACK);
		board[5][0] = blackPawn;
		move = new Move(7, 1, 5, 0);
		assertTrue(k.isValidMove(move, board));

		// White knight to move to open space over white pawn (valid
		// move
		// - because knight can jump over pieces), assert move is true
		board[7][1] = null;
		board[5][0] = k;
		move = new Move(5, 0, 4, 2);
		assertTrue(k.isValidMove(move, board));

		// White knight to move too far in an L-shaped pattern, move two
		// forward, two sideways (invalid move), assert invalid / false
		board[5][0] = null;
		board[5][2] = null;
		move = new Move(4, 2, 2, 4);
		assertFalse(k.isValidMove(move, board));

		// White knight to move up one, left two (valid move), assert
		// move is valid / true
		board[4][2] = k;
		move = new Move(4, 2, 3, 0);
		assertTrue(k.isValidMove(move, board));

		// White knight to move down one, left two (valid move), assert
		// move is valid / true
		move = new Move(4, 2, 5, 0);
		assertTrue(k.isValidMove(move, board));

		// White knight to move up two, left one (valid move), assert
		// move is valid / true
		move = new Move(4, 2, 2, 1);
		assertTrue(k.isValidMove(move, board));

		// White knight to move down two, left one (valid move), assert
		// move is valid / true
		move = new Move(4, 2, 6, 1);
		assertTrue(k.isValidMove(move, board));

		// White knight to move down two, right one (valid move), assert
		// move is valid / true
		move = new Move(4, 2, 6, 3);
		assertTrue(k.isValidMove(move, board));

		// White knight to move down one, right two (valid move), assert
		// move is valid / true
		move = new Move(4, 2, 5, 4);
		assertTrue(k.isValidMove(move, board));

		// White knight to move up one, right two (valid move), assert
		// move is valid / true
		move = new Move(4, 2, 3, 4);
		assertTrue(k.isValidMove(move, board));

		// White knight to move up two, right one (valid move), assert
		// move is valid / true
		move = new Move(4, 2, 2, 3);
		assertTrue(k.isValidMove(move, board));

	}

	/*******************************************************************
	 * Test method that tests the Bishop class. Tests the valid and
	 * invalid movements of a Bishop object in the chess game.
	 ******************************************************************/
	@Test
	public void testBishopMoves() {

		// reset board first
		resetBoard();

		// instantiate Bishop object belonging to white player
		Bishop b = new Bishop(Player.WHITE);

		// put White Bishop into place to test valid and invalid moves
		board[7][2] = b;

		// create Move object, tell White Bishop to move onto self
		// (invalid move - because can't move from and to same location,
		// assert move is invalid / false
		move = new Move(7, 2, 7, 2);
		assertFalse(b.isValidMove(move, board));

		// drop White Pawn onto board in front of White Bishop, bishop
		// to move to pawn (invalid move - because can't take own
		// color's
		// pieces), assert move is invalid / false
		Pawn whitePawn = new Pawn(Player.WHITE);
		board[6][3] = whitePawn;
		move = new Move(7, 2, 6, 3);
		assertFalse(b.isValidMove(move, board));

		// drop Black Pawn onto board in place of White pawn, White
		// Bishop to black pawn (valid move), assert valid / true
		Pawn blackPawn = new Pawn(Player.BLACK);
		board[6][3] = blackPawn;
		move = new Move(7, 2, 6, 3);
		assertTrue(b.isValidMove(move, board));

		// White Bishop to move sideways left (invalid move - bishop can
		// only move diagonally), assert move is invalid / false
		board[7][2] = null;
		board[6][3] = b;
		move = new Move(6, 3, 6, 2);
		assertFalse(b.isValidMove(move, board));

		// White Bishop to move sideways right (invalid move - bishop
		// can
		// only move diagonally), assert move is invalid / false
		move = new Move(6, 3, 6, 4);
		assertFalse(b.isValidMove(move, board));

		// White Bishop to move up straight (invalid move - bishop can
		// only move diagonally), assert move is invalid / false
		move = new Move(6, 3, 7, 3);
		assertFalse(b.isValidMove(move, board));

		// White Bishop to move down straight (invalid move - bishop can
		// only move diagonally), assert move is invalid / false
		move = new Move(6, 3, 5, 3);
		assertFalse(b.isValidMove(move, board));

		// White Bishop to move down-right diagonally (valid move),
		// assert move is valid / true
		move = new Move(6, 3, 7, 4);
		assertTrue(b.isValidMove(move, board));

		// White Bishop to move up-left diagonally (valid move),
		// assert move is valid / true
		move = new Move(6, 3, 3, 0);
		assertTrue(b.isValidMove(move, board));

		// White Bishop to move up-right diagonally (valid move),
		// assert move is valid / true
		move = new Move(6, 3, 2, 7);
		assertTrue(b.isValidMove(move, board));

		// White Bishop to move down-left diagonally (valid move),
		// assert move is valid / true
		move = new Move(6, 3, 4, 5);
		assertTrue(b.isValidMove(move, board));

		// drop white pawn blocking White bishop from taking black pawn,
		// white bishop to black pawn (invalid move), assert false
		board[4][5] = whitePawn;
		board[2][7] = blackPawn;
		move = new Move(6, 3, 2, 7);
		assertFalse(b.isValidMove(move, board));

		// remove white pawn and move bishop to black pawn (valid move),
		// assert move is valid / true
		board[4][5] = null;
		move = new Move(6, 3, 2, 7);
		assertTrue(b.isValidMove(move, board));
	}

	/*******************************************************************
	 * Test method that tests the inCheck method of the Model class.
	 * Tests to see if once a piece is threatening an opponent's king if
	 * the inCheck method works properly.
	 ******************************************************************/
	@Test
	public void testInCheck() {

		// reset the model and set board according to the model class
		model = new ChessModel();
		board = model.getBoard();

		// move white pawn forward two
		move = new Move(6, 3, 4, 3);
		model.move(move);

		// move black pawn forward one
		move = new Move(1, 2, 2, 2);
		model.move(move);

		// assert that white king is in not in check yet
		assertFalse(model.inCheck(model.currentPlayer()));

		// move white pawn forward one
		move = new Move(6, 7, 5, 7);
		model.move(move);

		// move black queen diagonally, putting white king in check
		move = new Move(0, 3, 3, 0);
		model.move(move);

		// assert that white king is in check
		assertTrue(model.inCheck(model.currentPlayer()));

		// move bishop up to block black queen putting king in check
		move = new Move(7, 2, 6, 3);
		model.move(move);

		// assert that white king is no longer in check
		assertFalse(model.inCheck(model.currentPlayer()));

		// move black queen to take white bishop, putting king in check
		move = new Move(3, 0, 6, 3);
		model.move(move);

		// assert that white king is back in check
		assertTrue(model.inCheck(model.currentPlayer()));

		// move white queen to take black queen checking king
		move = new Move(7, 3, 6, 3);
		model.move(move);

		// assert that white king is no longer in check again
		assertFalse(model.inCheck(model.currentPlayer()));
	}

	/*******************************************************************
	 * Test method that tests that once a player's king is put in check
	 * whether that player can move certain pieces to certain locations.
	 * While king is in check, player should only be able to move pieces
	 * that will result in king not being in check, unless player is in
	 * checkmate, which results in game being over.
	 ******************************************************************/
	@Test
	public void testMoveWhileInCheck() {

		// reset the model and set board according to the model class
		model = new ChessModel();
		board = model.getBoard();

		// move white pawn forward two
		move = new Move(6, 3, 4, 3);
		model.move(move);

		// move black pawn forward one
		move = new Move(1, 2, 2, 2);
		model.move(move);

		// move white pawn forward one
		move = new Move(6, 7, 5, 7);
		model.move(move);

		// move black queen diagonally, putting white king in check
		move = new Move(0, 3, 3, 0);
		model.move(move);

		// assert that white king is in check
		assertTrue(model.inCheck(model.currentPlayer()));

		// assert that moving pawn up one (not blocking check) is an
		// invalid move (can't move if king remains in check)
		move = new Move(6, 5, 5, 5);
		assertFalse(model.isValidMove(move));

		// assert that moving bishop up to block black queen that is
		// putting king in check is a valid move (king does not remain
		// in check)
		move = new Move(7, 2, 6, 3);
		assertTrue(model.isValidMove(move));

		// make move and assert that king is no longer in check
		model.move(move);
		assertFalse(model.inCheck(model.currentPlayer()));
	}

	/*******************************************************************
	 * Test method that tests the isComplete method of the Model class.
	 * If player is in checkmate, then game complete. Otherwise, if
	 * player can make a move that results in his or her king not being
	 * in check after move is made, then game is not complete (player is
	 * not in checkmate).
	 ******************************************************************/
	@Test
	public void testIsComplete() {

		// reset the model and set board according to the model class
		model = new ChessModel();
		board = model.getBoard();

		// move white pawn forward one
		move = new Move(6, 5, 5, 5);
		model.move(move);

		// move black pawn forward two
		move = new Move(1, 4, 3, 4);
		model.move(move);

		// assert that game is not complete
		assertFalse(model.getGameStatus());

		// move white pawn two forward
		move = new Move(6, 6, 4, 6);
		model.move(move);

		// move black queen to put white king in checkmate
		move = new Move(0, 3, 4, 7);
		model.move(move);

		// assert that game is complete
		assertTrue(model.getGameStatus());

		// reset the model and set board according to the model class
		model = new ChessModel();
		board = model.getBoard();

		// move white pawn forward two
		move = new Move(6, 4, 4, 4);
		model.move(move);

		// move black pawn forward two
		move = new Move(1, 6, 3, 6);
		model.move(move);

		// move white knight out
		move = new Move(7, 1, 5, 2);
		model.move(move);

		// assert that game is not complete
		assertFalse(model.getGameStatus());

		// move black pawn forward two
		move = new Move(1, 5, 3, 5);
		model.move(move);

		// move white queen out to check black king (checkmate)
		move = new Move(7, 3, 3, 7);
		model.move(move);

		// assert that game is now complete
		assertTrue(model.getGameStatus());
	}

}
