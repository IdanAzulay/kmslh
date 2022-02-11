package com.kms.services;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kms.entities.Currency;
import com.kms.repositories.CurrencyRepository;

@Service
public class ATMCommandsImpl implements ATMCommands {


	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private Logger logger;

	/**
	 * Responsible for printing all the currencies's notes which available in the
	 * ATM machine.
	 */
	@Override
	public void printCash() {

		List<Currency> currencies = currencyRepository.findAll();
		for (Currency currency : currencies) {
			if (currency.getBankNoteOf_1() > 0) {
				logger.info("" + currency.getName() + " 1 " + currency.getBankNoteOf_1() + " ");
			}
			if (currency.getBankNoteOf_10() > 0) {
				logger.info("" + currency.getName() + " 10 " + currency.getBankNoteOf_10() + " ");
			}
			if (currency.getBankNoteOf_100() > 0) {
				logger.info("" + currency.getName() + " 100 " + currency.getBankNoteOf_100() + " ");
			}
			if (currency.getBankNoteOf_1000() > 0) {
				logger.info("" + currency.getName() + " 1000 " + currency.getBankNoteOf_1000() + " ");
			}
			if (currency.getBankNoteOf_5() > 0) {
				logger.info("" + currency.getName() + " 5 " + currency.getBankNoteOf_5() + " ");
			}
			if (currency.getBankNoteOf_50() > 0) {
				logger.info("" + currency.getName() + " 50 " + currency.getBankNoteOf_50() + " ");
			}
			if (currency.getBankNoteOf_500() > 0) {
				logger.info("" + currency.getName() + " 500 " + currency.getBankNoteOf_500() + " ");
			}
			if (currency.getBankNoteOf_5000() > 0) {
				logger.info("" + currency.getName() + " 5000 " + currency.getBankNoteOf_5000() + " ");
			}
		}
		logger.info("OK ");

	}

	/**
	 * Responsible for Cash deposit into the ATM machine.
	 * 
	 * @param currencyName the name of the currency.
	 * @param value        of Cash to withdrawal
	 * @param number       of notes per deposit
	 */
	@Override
	public void addNotes(String currencyName, int value, int number) {


		if (currencyName.length() == 3 && checkString(currencyName)
				&& validateValues(value, number)) {

			if (!currencyRepository.existsByName(currencyName)) {
				Currency currency = new Currency();
				currency.setName(currencyName);
				updateDBAddNotes(currency, value, number);
				currency.setTotalCash(currency.getTotalCash() + (value * number));
				currencyRepository.save(currency);
				logger.info("+ " + currencyName + " " + value + " " + number);
				logger.info("OK");
			} else if (currencyRepository.existsByName(currencyName)) {

				Currency currency = currencyRepository.findByName(currencyName);
				updateDBAddNotes(currency, value, number);
				currency.setTotalCash(currency.getTotalCash() + (value * number));
				currencyRepository.save(currency);
				logger.info("+ " + currencyName + " " + value + " " + number);
				logger.info("OK");

			}

		} else {
			logger.info("+ " + currencyName + " " + value + " " + number);
			logger.info("ERROR");
		}

	}

	/**
	 * Responsible for Cash withdrawal from the ATM machine.
	 * 
	 * @param currencyName the name of the currency.
	 * @param amount       of Cash to withdrawal
	 */
	@Override
	public void getCash(String currencyName, int amount) {
		int newAmount = amount;
		Currency currency = currencyRepository.findByName(currencyName);

		if (currency.getTotalCash() >= amount) {
			try {
				logger.info("- " + currencyName + " " + amount);
				while (newAmount != 0) {

					if (newAmount >= currency.getBankNoteOf_5000() * 5000 && currency.getBankNoteOf_5000() > 1) {
						int notesOf_5000 = currency.getBankNoteOf_5000();
						newAmount -= notesOf_5000 * 5000;
						updateDBCashWithdrawal(currency, notesOf_5000, 5000);
						currency.setTotalCash(currency.getTotalCash() - (notesOf_5000 * 5000));
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("5000 " + notesOf_5000);

					}
					if (newAmount >= 5000 && currency.getBankNoteOf_5000() > 0) {
						int notes = 0;
						int notesOf_5000 = currency.getBankNoteOf_5000();
						for (int n = 1; n <= notesOf_5000; n++) {
							if (newAmount >= n * 5000) {
								notes++;
							}
						}
						updateDBCashWithdrawal(currency, notes, 5000);
						currency.setTotalCash(currency.getTotalCash() - (notes * 5000));
						newAmount -= notes * 5000;
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("5000 " + notes);

					}

					if (newAmount >= currency.getBankNoteOf_500() * 500 && currency.getBankNoteOf_500() > 1) {
						int notesOf_500 = currency.getBankNoteOf_500();
						newAmount -= notesOf_500 * 500;
						updateDBCashWithdrawal(currency, notesOf_500, 500);
						currency.setTotalCash(currency.getTotalCash() - (notesOf_500 * 500));
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("500 " + notesOf_500);

					}
					if (newAmount >= 500 && currency.getBankNoteOf_500() > 0) {
						int notes = 0;
						int notesOf_500 = currency.getBankNoteOf_500();
						for (int n = 1; n <= notesOf_500; n++) {
							if (newAmount >= n * 500) {
								notes++;
							}
						}
						updateDBCashWithdrawal(currency, notes, 500);
						currency.setTotalCash(currency.getTotalCash() - (notes * 500));
						newAmount -= notes * 500;
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("500 " + notes);

					}
					if (newAmount >= currency.getBankNoteOf_50() * 50 && currency.getBankNoteOf_50() > 1) {
						int notesOf_50 = currency.getBankNoteOf_50();
						newAmount -= notesOf_50 * 50;
						updateDBCashWithdrawal(currency, notesOf_50, 50);
						currency.setTotalCash(currency.getTotalCash() - (notesOf_50 * 50));
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("50 " + notesOf_50);

					}
					if (newAmount >= 50 && currency.getBankNoteOf_50() > 0) {
						int notes = 0;
						int notesOf_50 = currency.getBankNoteOf_50();
						for (int n = 1; n <= notesOf_50; n++) {
							if (newAmount >= n * 50) {
								notes++;
							}
						}
						updateDBCashWithdrawal(currency, notes, 50);
						currency.setTotalCash(currency.getTotalCash() - (notes * 50));
						newAmount -= notes * 50;
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("50 " + notes);

					}
					if (newAmount >= currency.getBankNoteOf_5() * 5 && currency.getBankNoteOf_5() > 1) {
						int notesOf_5 = currency.getBankNoteOf_5();
						newAmount -= notesOf_5 * 5;
						updateDBCashWithdrawal(currency, notesOf_5, 5);
						currency.setTotalCash(currency.getTotalCash() - (notesOf_5 * 5));
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("5 " + notesOf_5);

					}
					if (newAmount >= 5 && currency.getBankNoteOf_5() > 0) {
						int notes = 0;
						int notesOf_5 = currency.getBankNoteOf_5();
						for (int n = 1; n <= notesOf_5; n++) {
							if (newAmount >= n * 5) {
								notes++;
							}
						}
						updateDBCashWithdrawal(currency, notes, 5);
						currency.setTotalCash(currency.getTotalCash() - (notes * 5));
						newAmount -= notes * 5;
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("5 " + notes);

					}
					if (newAmount >= currency.getBankNoteOf_1000() * 1000 && currency.getBankNoteOf_1000() > 1) {
						int notesOf_1000 = currency.getBankNoteOf_1000();
						newAmount -= notesOf_1000 * 1000;
						updateDBCashWithdrawal(currency, notesOf_1000, 1000);
						currency.setTotalCash(currency.getTotalCash() - (notesOf_1000 * 1000));
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("1000 " + notesOf_1000);

					}
					if (newAmount >= 1000 && currency.getBankNoteOf_1000() > 0) {
						int notes = 0;
						int notesOf_1000 = currency.getBankNoteOf_1000();
						for (int n = 1; n <= notesOf_1000; n++) {
							if (newAmount >= n * 1000) {
								notes++;
							}
						}
						updateDBCashWithdrawal(currency, notes, 1000);
						currency.setTotalCash(currency.getTotalCash() - (notes * 1000));
						newAmount -= notes * 1000;
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("1000 " + notes);

					}
					if (newAmount >= currency.getBankNoteOf_100() * 100 && currency.getBankNoteOf_100() > 1) {
						int notesOf_100 = currency.getBankNoteOf_100();
						newAmount -= notesOf_100 * 100;
						updateDBCashWithdrawal(currency, notesOf_100, 100);
						currency.setTotalCash(currency.getTotalCash() - (notesOf_100 * 100));
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("100 " + notesOf_100);

					}
					if (newAmount >= 100 && currency.getBankNoteOf_100() > 0) {
						int notes = 0;
						int notesOf_100 = currency.getBankNoteOf_100();
						for (int n = 1; n <= notesOf_100; n++) {
							if (newAmount >= n * 100) {
								notes++;
							}
						}
						updateDBCashWithdrawal(currency, notes, 100);
						currency.setTotalCash(currency.getTotalCash() - (notes * 100));
						newAmount -= notes * 100;
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("100 " + notes);

					}
					if (newAmount >= currency.getBankNoteOf_10() * 10 && currency.getBankNoteOf_10() > 1) {
						int notesOf_10 = currency.getBankNoteOf_10();
						newAmount -= notesOf_10 * 10;
						updateDBCashWithdrawal(currency, notesOf_10, 10);
						currency.setTotalCash(currency.getTotalCash() - (notesOf_10 * 10));
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("10 " + notesOf_10);

					}
					if (newAmount >= 10 && currency.getBankNoteOf_10() > 0) {
						int notes = 0;
						int notesOf_10 = currency.getBankNoteOf_10();
						for (int n = 1; n <= notesOf_10; n++) {
							if (newAmount >= n * 10) {
								notes++;
							}
						}
						updateDBCashWithdrawal(currency, notes, 10);
						currency.setTotalCash(currency.getTotalCash() - (notes * 10));
						newAmount -= notes * 10;
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("10 " + notes);

					}
					if (newAmount >= currency.getBankNoteOf_1() * 1 && currency.getBankNoteOf_1() > 1) {
						int notesOf_1 = currency.getBankNoteOf_1();
						newAmount -= notesOf_1 * 1;
						updateDBCashWithdrawal(currency, notesOf_1, 1);
						currency.setTotalCash(currency.getTotalCash() - (notesOf_1 * 1));
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("1 " + notesOf_1);

					}
					if (newAmount >= 1 && currency.getBankNoteOf_1() > 0) {
						int notes = 0;
						int notesOf_1 = currency.getBankNoteOf_1();
						for (int n = 1; n <= notesOf_1; n++) {
							if (newAmount >= n * 1) {
								notes++;
							}
						}
						updateDBCashWithdrawal(currency, notes, 1);
						currency.setTotalCash(currency.getTotalCash() - (notes * 1));
						newAmount -= notes * 1;
						currencyRepository.save(currency);
						currency = currencyRepository.findByName(currencyName);
						logger.info("1 " + notes);
					}

				}

				logger.info("OK");

			} catch (Exception e) {
				logger.info("- " + currencyName + " " + amount);
				logger.info("ERROR");
			}

		} else {
			logger.info("- " + currencyName + " " + amount);
			logger.info("ERROR");
		}
	}

	/**
	 * Responsible for Cash withdrawal from the ATM machine.
	 * 
	 * @param value  validate the value of notes according the rule : 10n and 5*10n,
	 *               0<=n<=3
	 * @param number of validate positive and Integer.
	 */
	private boolean validateValues(int value, int number) {

		if (number > 0 && number % 1 == 0) {

			for (int n = 0; n <= 3; n++) {

				if (Math.pow(10, n) == value || (Math.pow(10, n) * 5) == value) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Responsible for udate currency's deposit to the ATM machine.
	 * 
	 * @param currency - currency name to update.
	 * @param value    - value of note.
	 * @param number   - number of note.
	 */
	private void updateDBAddNotes(Currency currency, int value, int number) {

		if (value == 1) {
			currency.setBankNoteOf_1(currency.getBankNoteOf_1() + number);
		} else if (value == 10) {
			currency.setBankNoteOf_10(currency.getBankNoteOf_10() + number);
		} else if (value == 100) {
			currency.setBankNoteOf_100(currency.getBankNoteOf_100() + number);
		} else if (value == 1000) {
			currency.setBankNoteOf_1000(currency.getBankNoteOf_1000() + number);
		} else if (value == 5) {
			currency.setBankNoteOf_5(currency.getBankNoteOf_5() + number);
		} else if (value == 50) {
			currency.setBankNoteOf_50(currency.getBankNoteOf_50() + number);
		} else if (value == 500) {
			currency.setBankNoteOf_500(currency.getBankNoteOf_500() + number);
		} else {
			currency.setBankNoteOf_5000(currency.getBankNoteOf_5000() + number);
		}

	}

	private void updateDBCashWithdrawal(Currency currency, int number, int typeOfNotes) {

		if (typeOfNotes == 5000) {
			currency.setBankNoteOf_5000(currency.getBankNoteOf_5000() - number);

		}
		if (typeOfNotes == 500) {
			currency.setBankNoteOf_500(currency.getBankNoteOf_500() - number);
		}
		if (typeOfNotes == 50) {
			currency.setBankNoteOf_50(currency.getBankNoteOf_50() - number);
		}
		if (typeOfNotes == 5) {
			currency.setBankNoteOf_5(currency.getBankNoteOf_5() - number);
		}
		if (typeOfNotes == 1000) {
			currency.setBankNoteOf_1000(currency.getBankNoteOf_1000() - number);
		}
		if (typeOfNotes == 100) {
			currency.setBankNoteOf_100(currency.getBankNoteOf_100() - number);
		}
		if (typeOfNotes == 10) {
			currency.setBankNoteOf_10(currency.getBankNoteOf_10() - number);
		}
		if (typeOfNotes == 1) {
			currency.setBankNoteOf_1(currency.getBankNoteOf_1() - number);
		}

	}

	private static boolean checkString(String input) {
		char currentCharacter;
		boolean upperCasePresent = false;

		for (int i = 0; i < input.length(); i++) {
			currentCharacter = input.charAt(i);
			if (Character.isUpperCase(currentCharacter)) {
				upperCasePresent = true;
			} else {
				return false;
			}

		}

		return upperCasePresent;

	}

}
