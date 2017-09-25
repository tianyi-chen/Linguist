package model;

public class CommandDeleteWord extends AbstractCommand {
	
	private String dictionaryName;
	private String content;

	public CommandDeleteWord(ConnectionDB conn, Account account, String dictionaryName, String content) {
		super(conn, account);
		this.dictionaryName = dictionaryName;
		this.content = content;
	}

	/**
	 * used to delete a word from the account and database
	 * @return true
	 */
	@Override
	public boolean execute() {
		account.getDictionary(dictionaryName).removeWord(content);
		conn.removeWordfromDB(account.getUsername(), dictionaryName, content);
		return true;
	}

}
