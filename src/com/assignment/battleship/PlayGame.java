package com.assignment.battleship;

public class PlayGame {

	public static void main(String[] args) {
		
		BoardSetup boardSetUp = new BoardSetup();
		boardSetUp.displayInstructions();
		boardSetUp.setupPlayerBoards();
		boardSetUp.play();
		

	}

}
