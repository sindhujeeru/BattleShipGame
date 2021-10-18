package com.assignment.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.assignment.battleship.ships.Battleship;
import com.assignment.battleship.ships.Carrier;
import com.assignment.battleship.ships.Cruiser;
import com.assignment.battleship.ships.Destroyer;
import com.assignment.battleship.ships.Ship;
import com.assignment.battleship.ships.ShipType;
import com.assignment.battleship.ships.Submarine;

public class Board {
	
	private final int Size = 10;
	private String[][] board = new String[Size][Size];
	private Ship[] ships; 
	
	Scanner scan = new Scanner(System.in);
	
	
	public Board() {
		getGrid();
		makeShips();
	}
	
	public String[][] getBoard() {
		return board;
	}
	public void setBoard(String[][] board) {
		this.board = board;
	}
	
	public Ship[] getShips() {
		return ships;
	}

	public void setShips(Ship[] ships) {
		this.ships = ships;
	}

	private void getGrid() {
		for(int i=0;i<Size;i++) {
			for(int j=0;j<Size;j++) {
				board[i][j]="__";
			}
		}
	}
	
	private void makeShips() {
		ships = new Ship[] {
				createShip(ShipType.Carrier),
				createShip(ShipType.Battleship),
				createShip(ShipType.Cruiser),
				createShip(ShipType.Submarine),
				createShip(ShipType.Destroyer),
				
		};
		
	}
	
	private Ship createShip(ShipType type) {
		switch(type) {
			case Carrier:
		        return new Carrier();
		    case Battleship:
		        return new Battleship();
		    case Submarine:
		        return new Submarine();
		    case Destroyer:
		        return new Destroyer();
		    case Cruiser:
		        return new Cruiser();
		    default:
		    	return null;
		}
	
		
	}

	public String[][] placeShipOnBoard(String[][] board,String location, int shipLen) {
		int y=Character.toUpperCase(location.charAt(0)) - 65; //row
		int x=Integer.parseInt(location.substring(1)) - 1; //column
		int length = location.length();
		
		if(x>=10 || y>=10) {
			System.out.println("Position out of bound give another position");
			location = scan.next();
			placeShipOnBoard(board,location,shipLen);
			return board;
		}
		
		//check if position is occupied
		boolean occupied = positionOccupied(board,y,x);
		if(occupied) {
			System.out.println("Position occupied by another ship enter different position");
			location = scan.next();
			placeShipOnBoard(board,location,shipLen);
			return board;
		}
		
		//get and directions based on location
		List<String> directions = getDirections(x,y,board,shipLen);
		
		for(String direction:directions) {
			System.out.println(direction);
		}
		//Enter direction
		System.out.println("Direction to place the ship");
		String direction = scan.next().toUpperCase();
		board = placeShipInDirection(y,x,board,direction,directions,shipLen);
		
		return board;
		
	}

	private String[][] placeShipInDirection(int y, int x, String[][] board, String direction, List<String> directions,int shipLen) {
		if(!directions.contains(direction)){
			System.out.println("There are ships on the way enter direction from the list provided");
			direction = scan.next().toUpperCase();
			placeShipInDirection(y,x,board,direction,directions,shipLen);
			return board;
		}else {
			
			if(direction.equals("RIGHT")) {
				for(int i=x;i<x+shipLen;i++) {
					board[y][i] = "S ";
				}
			}else if(direction.equals("LEFT")) {
				for(int i=x-shipLen+1;i<=x;i++) {
					board[y][i]="S ";
				}
			}else if(direction.equals("DOWN")) {
				for(int i=y;i<y+shipLen;i++) {
					board[i][x] = "S ";
				}
			}else if(direction.equals("UP")){
				for(int i=y-shipLen+1;i<=y;i++) {
					board[i][x]="S ";
				}
			}
			
		}
		return board;
		
	}
	
	
	private boolean shipPresentAlongX(String[][]board,int y,int x,int shipLen){
		for(int i=x;i<x+shipLen;i++) {
			if(board[y][i].equals("S ")) {
				return true;
			}
		}
		return false;
	}
	
	private boolean shipPresentAlongY(String[][]board,int y,int x,int shipLen){
		for(int i=y;i<y+shipLen;i++) {
			if(board[i][x].equals("S ")) {
				return true;
			}
		}
		return false;
	}
	
	private List<String> getDirections(int x,int y,String[][] board,int shipLen){
		List<String> directions = new ArrayList<>();
		if(x+shipLen-1<Size) {
			if(!shipPresentAlongX(board,y,x,shipLen)){
				directions.add("RIGHT");
			}
		}
		if(x-shipLen+1>=0){
			if(!shipPresentAlongX(board,y,x-shipLen+1,shipLen)){
				directions.add("LEFT");
			}
			
		}
		if(y+shipLen-1<Size){
			if(!shipPresentAlongY(board,y,x,shipLen)){
				directions.add("DOWN");
			}
		}
		if(y-shipLen+1>=0) {
			if(!shipPresentAlongY(board,y-shipLen+1,x,shipLen)){
				directions.add("UP");
			}
		}
		return directions;
	}

	private boolean positionOccupied(String[][] board, int y, int x) {
		
		if(board[y][x].equals("S ")) {
			return true;
		}
		return false;
	}
	
}


/*
 		for(int j=0;j<Size+1;j++) {
				if(i==0 && j==0) {
					board[i][j]="   ";
				}else if(i==0 && j>0) {
					board[i][j] = j+"  ";
				}else if(j==0 && i>0) {
					board[i][j] = (char)(65+(i-1))+"  ";
				}else {
					board[i][j] = "__ ";
				}
			}
*/
 