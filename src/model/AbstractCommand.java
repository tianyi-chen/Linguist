package model;

public abstract class AbstractCommand {
	
	protected ConnectionDB conn;
	protected Account account;

	public AbstractCommand(ConnectionDB conn, Account account) {
		this.conn = conn;
		this.account = account;
	}
	
	public abstract boolean execute();

}
