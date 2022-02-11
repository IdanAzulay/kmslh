package com.kms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kms.services.ATMCommandsImpl;

@SpringBootTest
class KMSTest {

	@Autowired
	private ATMCommandsImpl atmCommandsImpl;

	
	
	@Test
	void test_1() {
		atmCommandsImpl.addNotes("USs", 1000, 2);
		atmCommandsImpl.addNotes("dSS", 1000, 2);
		atmCommandsImpl.addNotes("sdA", 1000, 2);
		atmCommandsImpl.addNotes("asd", 1000, 2);
		atmCommandsImpl.addNotes("USDS", 5, 2);
		atmCommandsImpl.addNotes("USD", 50, 13);
		atmCommandsImpl.addNotes("USD", 500, 7);
		atmCommandsImpl.addNotes("USD", 5000, 2);
		atmCommandsImpl.addNotes("USD", 1, 2);
		atmCommandsImpl.addNotes("CHF", 10, 5);
		atmCommandsImpl.addNotes("FFG", 100, 6);
		atmCommandsImpl.addNotes("USD", 1000, 2);

		




		atmCommandsImpl.printCash();
		atmCommandsImpl.getCash("USD", 10551);
		atmCommandsImpl.getCash("CHF", 50);
		atmCommandsImpl.getCash("FFG", 500);


	}
	

	
	@Test
	void test_2() {

		atmCommandsImpl.addNotes("asds", 5, 2);
		atmCommandsImpl.addNotes("USSs", 50, 13);
		atmCommandsImpl.addNotes("CHG", 500, 7);
		atmCommandsImpl.addNotes("cHG", 5000, 2);
		atmCommandsImpl.addNotes("CHG", 1, 2);
		atmCommandsImpl.addNotes("USS", 10, 4);
		atmCommandsImpl.printCash();
		atmCommandsImpl.getCash("CHG", 30500);
		atmCommandsImpl.getCash("USD", 30550);
		atmCommandsImpl.addNotes("ASDD", 100, 6);
		atmCommandsImpl.addNotes("fSs", 1000, 2);

		atmCommandsImpl.printCash();
		atmCommandsImpl.getCash("USD", 10551);

	}
	
	
	
}
