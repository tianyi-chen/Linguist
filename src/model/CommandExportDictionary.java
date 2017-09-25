package model;

import java.io.FileWriter;
import java.io.IOException;

public class CommandExportDictionary extends AbstractCommand  {

	private String filePath; 
	private String dictionaryName;
	
	public CommandExportDictionary(ConnectionDB conn, Account account, String filePath, String dictionaryName) {
		super(conn, account);
		this.filePath = filePath;
		this.dictionaryName = dictionaryName;
	}
	
	public boolean execute(){
		
		Dictionary dictionary = account.getDictionary(dictionaryName);
		try {
			FileWriter writer = new FileWriter(filePath);
			
			writer.append("Content");
			writer.append("WordClass");
			writer.append("Meaning");
			writer.append("Example");
			writer.append("\n");
			
			int increment = 0;
			
			while(dictionary.getDictionaryContent().get(increment)!= null)
			{
				String content = dictionary.getDictionaryContent().get(increment).getContent();
				String wordClass = dictionary.getDictionaryContent().get(increment).getWordClass();
				String meaning = dictionary.getDictionaryContent().get(increment).getMeaning();
				String example = dictionary.getDictionaryContent().get(increment).getExample();
				
				writer.append(content);
				writer.append(wordClass);
				writer.append(meaning);
				writer.append(example);
				writer.append("\n");
				
				increment++;
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}

		return true;
	}
}
