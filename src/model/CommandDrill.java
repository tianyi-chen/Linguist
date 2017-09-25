package model;

import java.util.ArrayList;

public class CommandDrill extends AbstractCommand {
	
	private int pace;
	private String dictionaryName;
	
	private ArrayList<Word> quizList;
	
	public CommandDrill(ConnectionDB conn, Account account, String dictionaryName, int amount) {
		super(conn, account);
		this.dictionaryName = dictionaryName;
		this.pace = amount;
		quizList = new ArrayList<Word>();
	}
	
	/**
	 * used to create a list of words for drill
	 * @return false if the dictionary is empty
	 */
	@Override
	public boolean execute() {
		Dictionary dictionary= account.getDictionary(dictionaryName);
		if (dictionary.getDictionaryContent().size() == 0) {
			return false;
		}
		
		int number = (int) (pace * 0.2);
		UnlearntList unlearntList = dictionary.getUnlearntList();
		LearntList learntList = dictionary.getLearntList();
		quizList = learntList.generateDrillWords(number);
		quizList.addAll(unlearntList.generateDrillWords(pace - number));
		return true;
	}
	
	public ArrayList<Word> getQuizList(){
		return quizList;
	}

}
