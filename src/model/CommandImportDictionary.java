package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class CommandImportDictionary extends AbstractCommand {

	private String filePath;
	private String dictionaryName;

	public CommandImportDictionary(ConnectionDB conn, Account account, String dictionaryName, String filePath) {
		super(conn, account);
		this.dictionaryName = dictionaryName;
		this.filePath = filePath;
	}

	/**
	 * used to read in a csv file and add dictionaries & words into account and database
	 * @return false if the file isn't in csv format, there is no data in the file or there isn't enough column
	 */
	@Override
	public boolean execute() {
		// check if the file is not in csv format
		String[] fileName = filePath.split("\\.");
		String fileFormat = fileName[fileName.length - 1];
		if (!fileFormat.equals("csv")) {
			return false;
		}
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(filePath), "UTF-8"); 
			BufferedReader bufferReader = new BufferedReader(read);
			try{
				String buff = bufferReader.readLine();
				// do not write the first line of the file into dictionary
				// check if the file consists of 4 columns
				if (buff != null) {
					String[] line = buff.split(",");
					if(line.length != 4){
						bufferReader.close();
						return false;
					}
				}
				conn.addDictionary(account.getUsername(), dictionaryName);
				account.addDictionary(dictionaryName);
				int counter = 0;
				while ((buff = bufferReader.readLine()) != null) {
					counter++;
					String[] line = new String(buff.getBytes(), "UTF-8").split(",");
					new CommandAddWord(conn, account, dictionaryName, new Word(line[0], line[1], line[2], line[3])).execute();
				}
				bufferReader.close();
				// no data in file
				if (counter == 0) {
					return false;
				}
			} catch (IOException e) {
				return false;
			}
		} catch (FileNotFoundException e) {
			// file doesn't exist
			return false;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}
	
	
}