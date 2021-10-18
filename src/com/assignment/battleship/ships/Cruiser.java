package com.assignment.battleship.ships;

public class Cruiser extends Ship{
	
	private final String name = "Cruiser";
	private final String length = "3";
	
	@Override
	public String getName() {
		
		return name;
	}

	@Override
	public String getLength() {
		
		return length;
	}


}
