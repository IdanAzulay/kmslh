package com.kms.services;

public interface ATMCommands {
	
	void printCash();
	
	void addNotes(String currency , int value , int number);
	
	void getCash(String currency , int amount);

}
