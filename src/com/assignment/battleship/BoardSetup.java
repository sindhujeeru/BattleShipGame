package com.assignment.battleship;

import java.util.Scanner;

import com.assignment.battleship.ships.Ship;

public class BoardSetup {
	
	
	private Board board = new Board();
	private Player player1;
	private Player player2;
	
	private boolean player1Playing = true;
	
	Scanner scan = new Scanner(System.in);
	
	public void displayInstructions() {
		System.out.println("Welcome Players Lets Setup the Game Board.........");
		
		System.out.println("Board set up Instructions.......\n"
				+ " 1. The board is 10X10 grid \n"
				+ " 2. The player will have 5 ships to be placed on the grid \n"
				+ " 3. Each player is allowed to give the location and direction to place the ship ");
	}
	
	public void setupPlayerBoards() {
		if(player1 == null) {
			player1 = setupPlayer("1");
			setUpShips(player1);
			
		}
		if(player2 == null) {
			player2 = setupPlayer("2");
			setUpShips(player2);
		}
	}

	private void setUpShips(Player player) {
		Ship[] ships= player.getShips();
		
		printShips(ships);
		String[][] board = player.getBoard().getBoard();
		for(int i=1;i<=ships.length;i++) {
			System.out.println(ships[i-1].getName()+" "+ships[i-1].getLength());
			System.out.println("At what location you want to place the ship?");
			String location = scan.next();
			board = player.getBoard().placeShipOnBoard(board, location, Integer.parseInt(ships[i-1].getLength()));
			
		}
		printBoard(board);
		
	}

	private void printShips(Ship[] ships) {
		System.out.println("Available ships.......");
		for(Ship ship:ships) {
			System.out.println("  "+ship.getName()+" of length "+ship.getLength());
		}
		
	}

	private Player setupPlayer(String playerNumber) {
		System.out.println("Setting up board for Player "+playerNumber);
		System.out.println("Enter your name");
		String name = scan.next();
		
		
		Player player = new Player(name);
		Board playerBoard = player.getBoard();
		
		String[][] board = playerBoard.getBoard();
		
		printBoard(board);
		
		return new Player(name);
		
	}

	public void  play() {
		System.out.println("==================================================");
		System.out.println("==================================================");
		System.out.println("============== Let's begin game ==================");
		System.out.println("=============="+player1.getName().toUpperCase() + " vs " + player2.getName().toUpperCase()+"===================");
		System.out.println("==================================================");
		System.out.println("==================================================");
		
		while(gameOngoing()) {
			play(player1,player2);
			if(player1.getPlayerShipsStatus()<=0 || player2.getPlayerShipsStatus()<=0) {
				break;
			}
		}
		
		if(player1.getPlayerShipsStatus() == 0) {
			congratulateWinner(player1);
		}else {
			congratulateWinner(player2);
		}
		
	}
	
	private void play(Player player1, Player player2) {
		if(player1Playing) {
			System.out.println("=============================================");
			printBoard(player1.getEnemyBoard().getBoard());
			System.out.println(player1.getName()+" At what location you want to fire");
			String firePos = scan.next();
			firePos = checkPosBoundaries(firePos);
			int y=Character.toUpperCase(firePos.charAt(0)) - 65; //row
			int x=Integer.parseInt(firePos.substring(1)) - 1; //column
			if(checkForHit(x,y,player2)) {
				if(player1.getEnemyBoard().getBoard()[y][x] != "X ") {
					System.out.println("Wow it's a HIT");
					player1.setPlayerShipsStatus(player1.getPlayerShipsStatus()-1);
				}
				player1.getEnemyBoard().getBoard()[y][x] = "X ";
				
			}else {
				System.out.println("Sorry it's a MISS, better luck next time");
				player1.getEnemyBoard().getBoard()[y][x] = "O ";
			}
			printBoard(player1.getEnemyBoard().getBoard());
			
		}else{
			System.out.println("=============================================");
			printBoard(player2.getEnemyBoard().getBoard());
			System.out.println(player2.getName()+" At what location you want to fire");
			String firePos = scan.next();
			firePos = checkPosBoundaries(firePos);
			int y=Character.toUpperCase(firePos.charAt(0)) - 65; //row
			int x=Integer.parseInt(firePos.substring(1)) - 1; //column
			if(checkForHit(x,y,player1)) {
				if(player2.getEnemyBoard().getBoard()[y][x]!="X ") {
					System.out.println("Wow it's a HIT");
					player2.setPlayerShipsStatus(player2.getPlayerShipsStatus()-1);
				}
				player2.getEnemyBoard().getBoard()[y][x] = "X ";
				
			}else {
				System.out.println("Sorry it's a MISS, better luck next time");
				player2.getEnemyBoard().getBoard()[y][x] = "O ";
			}
			printBoard(player1.getEnemyBoard().getBoard());
		}
		
		//System.out.println(player1.getPlayerShipsStatus()+" "+player2.getPlayerShipsStatus());
		
		player1Playing = !player1Playing;
		
	}
	
	

	private boolean checkForHit(int x, int y, Player player) {
		String[][] playerBoard = player.getBoard().getBoard();
		return playerBoard[y][x].equals("S ");
		
	}

	private String checkPosBoundaries(String firePos) {
		int y=Character.toUpperCase(firePos.charAt(0)) - 65; //row
		int x=Integer.parseInt(firePos.substring(1)) - 1; //column
		
		if(x>=10 || y>=10 || x<0 || y<0) {
			System.out.println("Position out of boundaries enter new one");
			firePos = scan.next();
			checkPosBoundaries(firePos);
			return firePos;
		}
		return firePos;
		
	}

	private boolean gameOngoing() {
		if(player1.getTotalShipsLenght()>0 && player2.getTotalShipsLenght()>0) {
			return true;
		}
		return false;
	}

	/*private void printBoardTest(String[][] board) {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		
	}*/
	
	private void printBoard(String[][] board) {
		for(int i=0;i<board.length+1;i++) {
			for(int j=0;j<board.length+1;j++) {
				if(i==0 && j==0) {
					System.out.print("   ");
				}else if(i==0 && j>0) {
					System.out.print(j+"  ");
				}else if(j==0 && i>0) {
					System.out.print((char)(65+(i-1))+"  ");
				}else {
					System.out.print(board[i-1][j-1]+" ");
				}
				//System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	private void congratulateWinner(Player player) {
		System.out.println("==================================================");
		System.out.println("==================================================");
		System.out.println("============== End of the game==================");
		System.out.println("============== "+player.getName().toUpperCase()+" won the game ===================");
		System.out.println("==================================================");
		System.out.println("==================================================");
		
	}
}

		
		
		
		
