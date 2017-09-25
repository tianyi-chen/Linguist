/**
 * This class handles the command add word
 */
package model;

public class CommandAddWord extends AbstractCommand {

	private String dictionaryName;
	private Word word;
	
	public CommandAddWord(ConnectionDB conn, Account account, String dictionaryName, Word word) {
		super(conn, account);
		this.dictionaryName = dictionaryName;
		this.word = word;
	}
	
	/**
	 * used to add a new word into the account and database
	 * @return false if the word already exists
	 */
	@Override
	public boolean execute() {
		if (account.getDictionary(dictionaryName).addWord(word)) {
			String content = word.getContent();
			String wordClass = word.getWordClass();
			String meaning = word.getMeaning();
			String example = word.getExample();
			Boolean isLearnt = word.isLearnt();
			double proficiency = word.getProficiency();
			int correctGuesses = word.getCorrectGuesses();
			int incorrectGuesses = word.getIncorrectGuesses();
//			System.out.println(content + " " + wordClass + " " + meaning + " " + example + " " + isLearnt + " " + proficiency);
			ConnectionDB.getInstance().addWordtoDB(account.getUsername(), dictionaryName, content, wordClass, meaning, example, isLearnt, proficiency, correctGuesses, incorrectGuesses);
			return true;
		} else {
			return false;
		}
	}

}
