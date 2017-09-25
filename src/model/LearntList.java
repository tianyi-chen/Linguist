package model;

import java.util.ArrayList;
import java.util.Random;

public class LearntList {
	
	private ArrayList<Word> learntList;

	public LearntList() {
		learntList = new ArrayList<Word>();  
	}
	
	public void addWord(Word word) {
		for (Word w : learntList) {
			if (word.contentEquals(w)) {
				return;
			}
		}
		if (word.isLearnt()) {
			learntList.add(word);
		}
	}
	
	public void removeWord(String content) {
		int index = 0;
		for (Word w : learntList) {
			if (w.getContent().equals(content)) {
				break;
			}
			index++;
		}
		if (index >= learntList.size()) {
			return;
		}
		learntList.remove(index);
	}
	
	public ArrayList<Word> generateDrillWords(int number) {
		if (number >= learntList.size()) {
			return learntList;
		}
		ArrayList<Word> drillWords = new ArrayList<Word>();
		int indices[] = generateRandomNumber(number);
		for (int i = 0; i < indices.length; i++) {
			drillWords.add(learntList.get(indices[i]));
		}
		return drillWords;
	}
	
	private int[] generateRandomNumber(int number) {
		Random random = new Random ();
		int[] randomNumbers = new int[number];
        boolean[]  bool = new boolean[number];
        int randInt = 0;
        for (int j = 0; j < number ; j++) {
            do {
                randInt = random.nextInt(number);
            } while (bool[randInt]); 
            bool[randInt] = true;
            randomNumbers[j] = randInt;
        }  
		return randomNumbers;
	}
	
	public ArrayList<Word> getLearntWords() {
		return learntList;
	}

}
