package model;

import java.util.ArrayList;
 

public class Dictionary {

	private String name;
	private ArrayList<Word> dictionary;
	private UnlearntList unlearntList;
	private LearntList learntList;
	
	public Dictionary(String name) {
		this.name = name;
		dictionary = new ArrayList<Word>();
		unlearntList = new UnlearntList();
		learntList = new LearntList();
	}
	
	public boolean addWord(Word word) {
		for (Word w : dictionary) {
			if (word.contentEquals(w)) {
				return false;
			}
		}
		dictionary.add(word);
		if (word.isLearnt()) {
			learntList.addWord(word);
		} else {
			unlearntList.addWord(word);
		}
		return true;
	}
	
	public void removeWord(String content) {
		int index = 0;
		for (Word w : dictionary) {
			if (w.getContent().equals(content)) {
				break;
			}
			index++;
		}
		if (index >= dictionary.size()) {
			return;
		}
		dictionary.remove(index);
		unlearntList.removeWord(content);
		learntList.removeWord(content);
	}

	public ArrayList<Word> getDictionaryContent() {
		return dictionary;
	}
	
	public void setDictionaryContent(ArrayList<Word> words) {
		dictionary = words;
		for (Word w : words) {
			unlearntList.addWord(w);
			learntList.addWord(w);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UnlearntList getUnlearntList() {
		return unlearntList;
	}

	public LearntList getLearntList() {
		return learntList;
	}
	
	public Word getWord(String content) {
		for (Word w : dictionary) {
			if (w.getContent().equals(content)) {
				return w;
			}
		}
		return null;
	}
	

	
}
