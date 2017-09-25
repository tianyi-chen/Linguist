package model;

import org.junit.Before;
import org.junit.Test;

public class CommandImportDictionaryTest {
	CommandImportDictionary cmdImport;
	Dictionary dictionary;
//	Controller controller;
	ConnectionDB conn;
	Account account;

	

	@Before
	public void setUp() throws Exception {
		conn= ConnectionDB.getInstance();
		account = Account.getInstance();
		cmdImport= new CommandImportDictionary(conn, account, "importTest" ,
				"/Users/Julius/Downloads/GRP/importTest.csv");
	}

	@Test
	public void test() {
		cmdImport.execute();
//		fail("Not yet implemented");
	}

}
