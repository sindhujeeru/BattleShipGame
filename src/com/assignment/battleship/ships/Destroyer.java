package com.assignment.battleship.ships;

public class Destroyer extends Ship{
	
	private final String name = "Destroyer";
	private final String length = "2";

	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public String getLength() {
		
		return length;
	}



}
