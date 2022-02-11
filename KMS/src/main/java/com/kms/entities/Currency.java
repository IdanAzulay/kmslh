package com.kms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


/**
* Currency is the main entity we'll be using in our application.
* 
* @author Idan Azulay
* 
*/
@Entity
public class Currency {
	

	/**
	 * Unique ID for each entity.
	 * Increament by 1 each creational.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * The public name of the Currency.
	 */
	@NotBlank
	private String name;

	/**
	 * Number of BankNotes of value 1.
	 */
	private int bankNoteOf_1;
	
	/**
	 * Number of BankNotes of value 10.
	 */
	private int bankNoteOf_10;

	/**
	 * Number of BankNotes of value 100.
	 */
	private int bankNoteOf_100;

	/**
	 * Number of BankNotes of value 1000.
	 */
	private int bankNoteOf_1000;

	/**
	 * Number of BankNotes of value 5.
	 */
	private int bankNoteOf_5;

	/**
	 * Number of BankNotes of value 50.
	 */
	private int bankNoteOf_50;

	/**
	 * Number of BankNotes of value 500.
	 */
	private int bankNoteOf_500;

	/**
	 * Number of BankNotes of value 5000.
	 */
	private int bankNoteOf_5000;
	
	/**
	 * Total amount of currency's value
	 */
	private int totalCash;
	
	/**
	 * Defalut Constructor
	 */
	public Currency() {}


	/**
	 * Constructor full arguments
	 */
	public Currency(@NotBlank String name, int bankNoteOf_1, int bankNoteOf_10, int bankNoteOf_100, int bankNoteOf_1000,
			int bankNoteOf_5, int bankNoteOf_50, int bankNoteOf_500, int bankNoteOf_5000, int totalCash) {
		super();
		this.name = name;
		this.bankNoteOf_1 = bankNoteOf_1;
		this.bankNoteOf_10 = bankNoteOf_10;
		this.bankNoteOf_100 = bankNoteOf_100;
		this.bankNoteOf_1000 = bankNoteOf_1000;
		this.bankNoteOf_5 = bankNoteOf_5;
		this.bankNoteOf_50 = bankNoteOf_50;
		this.bankNoteOf_500 = bankNoteOf_500;
		this.bankNoteOf_5000 = bankNoteOf_5000;
		this.totalCash = totalCash;
	}


	/**
	 * Getters / Setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBankNoteOf_1() {
		return bankNoteOf_1;
	}

	public void setBankNoteOf_1(int bankNoteOf_1) {
		this.bankNoteOf_1 = bankNoteOf_1;
	}

	public int getBankNoteOf_10() {
		return bankNoteOf_10;
	}

	public void setBankNoteOf_10(int bankNoteOf_10) {
		this.bankNoteOf_10 = bankNoteOf_10;
	}

	public int getBankNoteOf_100() {
		return bankNoteOf_100;
	}

	public void setBankNoteOf_100(int bankNoteOf_100) {
		this.bankNoteOf_100 = bankNoteOf_100;
	}

	public int getBankNoteOf_1000() {
		return bankNoteOf_1000;
	}

	public void setBankNoteOf_1000(int bankNoteOf_1000) {
		this.bankNoteOf_1000 = bankNoteOf_1000;
	}

	public int getBankNoteOf_5() {
		return bankNoteOf_5;
	}

	public void setBankNoteOf_5(int bankNoteOf_5) {
		this.bankNoteOf_5 = bankNoteOf_5;
	}

	public int getBankNoteOf_50() {
		return bankNoteOf_50;
	}

	public void setBankNoteOf_50(int bankNoteOf_50) {
		this.bankNoteOf_50 = bankNoteOf_50;
	}

	public int getBankNoteOf_500() {
		return bankNoteOf_500;
	}

	public void setBankNoteOf_500(int bankNoteOf_500) {
		this.bankNoteOf_500 = bankNoteOf_500;
	}

	public int getBankNoteOf_5000() {
		return bankNoteOf_5000;
	}

	public void setBankNoteOf_5000(int bankNoteOf_5000) {
		this.bankNoteOf_5000 = bankNoteOf_5000;
	}
	

	public int getTotalCash() {
		return totalCash;
	}


	public void setTotalCash(int totalCash) {
		this.totalCash = totalCash;
	}


}
