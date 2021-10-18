package com.assignment.battleship;

import com.assignment.battleship.ships.Ship;

public class Player {
	
	private String name;
	private Board board;
	private Board enemyBoard;
	
	private int playerShipsStatus;


	public Player(String name) {
		this.name = name;
		this.board = new Board();
		this.enemyBoard = new Board();
		this.playerShipsStatus = getTotalShipsLenght();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	
	
	public Board getEnemyBoard() {
		return enemyBoard;
	}


	public void setEnemyBoard(Board enemyBoard) {
		this.enemyBoard = enemyBoard;
	}
	
	


	public int getPlayerShipsStatus() {
		return playerShipsStatus;
	}


	public void setPlayerShipsStatus(int playerShipsStatus) {
		this.playerShipsStatus = playerShipsStatus;
	}


	public Ship[] getShips() {
		return board.getShips();
	}
	
	public int getTotalShipsLenght() {
		Ship[] ships = getShips();
		int totShipLen = 0;
		
		for(Ship ship:ships) {
			totShipLen = totShipLen + Integer.parseInt(ship.getLength());
		}
		
		return totShipLen;
	}
	
	
	


}
