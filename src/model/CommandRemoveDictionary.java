package model;

public class CommandRemoveDictionary extends AbstractCommand {

	private String dictionaryName;
	
	public CommandRemoveDictionary(ConnectionDB conn, Account account, String dictionaryName) {
		super(conn, account);
		this.dictionaryName = dictionaryName;
	}

	/**
	 * used to remove a dictionary from account and database
	 * @return true
	 */
	@Override
	public boolean execute() {
		account.removeDictionary(dictionaryName);
		conn.removeDictionary(account.getUsername(), dictionaryName);
		return true;
	}

}
