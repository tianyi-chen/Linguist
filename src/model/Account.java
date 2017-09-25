package model;

import java.util.ArrayList;

public class Account {

	private static Account instance;

	public static Account getInstance() {
		if (instance == null) {
			instance = new Account();
		}   	
	    return instance;
	}
	
	private String username;
	private ArrayList<Dictionary> dictionaryList;
	
	public Account() {
		this.username = new String();
		new String();
		dictionaryList = new ArrayList<Dictionary>();
	}
	
	public void addDictionary(String dictionaryName) {
		dictionaryList.add(new Dictionary(dictionaryName));
	}
	
	public void removeDictionary(String dictionaryName) {
		int i = 0;
		for (Dictionary dictionary : dictionaryList) {
			if (dictionary.getName().equals(dictionaryName)) {
				dictionaryList.remove(i);
				return;
			} else {
				i++;
			}
		}
	}
	
	public ArrayList<Dictionary> getDictionaryList() {
		return dictionaryList;
	}
	
	public void setDictionary(String dictionaryName, Dictionary dictionary) {
		for (Dictionary dic : dictionaryList) {
			if (dic.getName().equals(dictionaryName)) {
//				dic = dictionary;
				dic.setDictionaryContent(dictionary.getDictionaryContent());
			}
		}
	}

	public Dictionary getDictionary(String dictionaryName) {
		for (Dictionary dic : dictionaryList) {
			if (dic.getName().equals(dictionaryName)) {
				return dic;
			}
		}
		return null;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
	}
	
	public void logout(){
		this.username = new String();
		new String();
		dictionaryList = new ArrayList<Dictionary>();
	}

}
