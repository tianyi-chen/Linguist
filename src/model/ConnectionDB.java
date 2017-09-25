package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB {

	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/DIC?characterEncoding=utf8";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "";
	// private final static int max = (int) Math.pow(2, 63);
	// private final static String max_connection = "SET GLOBAL max_connections
	// = "+max ;

	static Connection conn = null;

	static Statement st;

	private static ConnectionDB instance;

	public static ConnectionDB getInstance() {
		if (instance == null) {
			instance = new ConnectionDB();
		}
		return instance;
	}

	/**
	 * Used to connect with Dictionaries table.
	 * 
	 * @return Connection object
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DBDRIVER);

			con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);

		} catch (Exception e) {
			System.out.println("Connection failed!" + e.getMessage());
		}
		return con;
	}

	public void addDictionary(String username, String dictionaryName) {
		conn = getConnection();
		try {
			st = (Statement) conn.createStatement();

			String ssql = "INSERT INTO Dictionary_info VALUES('" + username + "', '" + username + " " + dictionaryName
					+ "')";
			st.executeUpdate(ssql);
			ssql = "CREATE TABLE IF NOT EXISTS " + username + dictionaryName
					+ " (content varchar(50) CHARACTER SET utf8 NOT NULL,"
					+ "wordClass varchar(20) CHARACTER SET utf8 NOT NULL,"
					+ "meaning varchar(200) CHARACTER SET utf8 NOT NULL,"
					+ "example varchar(200) CHARACTER SET utf8,"
					+ "isLearnt int NOT NULL DEFAULT 0,"
					+ "proficiency double(4,2) NOT NULL DEFAULT 0,"
					+ "correct int NOT NULL DEFAULT 0,"
					+ "incorrect int NOT NULL DEFAULT 0,"
					+ "PRIMARY KEY(content));";
			st.executeUpdate(ssql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeDictionary(String username, String dictionaryName) {
		conn = getConnection();
		try {
			st = (Statement) conn.createStatement();

			String ssql = "DELETE FROM Dictionary_info WHERE dictionaryName = '" + username + " " + dictionaryName + "'";
			st.executeUpdate(ssql);
			ssql = "DROP TABLE " + username + dictionaryName;
			st.executeUpdate(ssql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getDictionaryNames(String username) {
		conn = getConnection();
		ArrayList<String> dictionaryList = new ArrayList<String>();
		try {
			st = (Statement) conn.createStatement();
			String ssql = "SELECT dictionaryName FROM Dictionary_info WHERE username = '" + username + "'";
			ResultSet rs = st.executeQuery(ssql);
			while (rs.next()) {
				String string = null;
				string = rs.getString("dictionaryName").split(" ")[1];
				dictionaryList.add(string);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dictionaryList;
	}

	public Dictionary getDictionary(String dictionaryName) {
		conn = getConnection();
		Dictionary dictionary = new Dictionary(dictionaryName);
		dictionary.setName(dictionaryName);
		try {
			st = (Statement) conn.createStatement();
			String ssql = "SELECT * FROM " + dictionaryName;
			ResultSet rs = st.executeQuery(ssql);
			while (rs.next()) {
				Word word = new Word(rs.getString("content"), rs.getString("wordClass"), rs.getString("meaning"), rs.getString("example"),
						(rs.getInt("isLearnt") != 0), rs.getDouble("proficiency"), rs.getInt("correct"), rs.getInt("incorrect"));
				dictionary.addWord(word);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dictionary;
	}

	/**
	 * used to add a word to a specified dictionary
	 * 
	 * @param username
	 * @param dictionaryName
	 * @param content
	 * @param meaning
	 * @param example
	 * @param isLearnt
	 * @param proficiency
	 */
	public void addWordtoDB(String username, String dictionaryName, String content, String wordClass, String meaning, String example,
			boolean isLearnt, double proficiency, int correctGuesses, int incorrectGuesses) {
		conn = getConnection();
		try {
			st = (Statement) conn.createStatement();

			String ssql = "INSERT INTO " + username + dictionaryName + " VALUES('" + content + "', '" + wordClass + "', '" + meaning + "', '"
					+ example + "', " + ((isLearnt) ? 1 : 0) + ", " + proficiency + ", " + correctGuesses + ", " + incorrectGuesses + ")";
			st.executeUpdate(ssql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * used to remove a word from a specified dictionary
	 * 
	 * @param username
	 * @param dictionaryName
	 * @param content
	 */
	public void removeWordfromDB(String username, String dictionaryName, String content) {
		conn = getConnection();
		try {
			st = (Statement) conn.createStatement();

			String ssql = "DELETE FROM " + username + dictionaryName + " WHERE content = '" + content + "'";
			st.executeUpdate(ssql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setWordClass(String username, String dictionaryName, String content, String wordClass) {
		conn = getConnection();
    	try {
        	st = (Statement) conn.createStatement();
        	
        	String ssql = "UPDATE " + username + dictionaryName + " SET wordClass = '" + wordClass + "' WHERE content = '" + content + "'";
        	st.executeUpdate(ssql);
        } catch (SQLException e) {
        	e.printStackTrace();
		}
	}
    
    public void setMeaning(String username, String dictionaryName, String content, String meaning) {
    	conn = getConnection();
    	try {
        	st = (Statement) conn.createStatement();
        	
        	String ssql = "UPDATE " + username + dictionaryName + " SET meaning = '" + meaning + "' WHERE content = '" + content + "'";
        	st.executeUpdate(ssql);
        } catch (SQLException e) {
        	e.printStackTrace();
		}
    }
    
    public void setExample(String username, String dictionaryName, String content, String example) {
    	conn = getConnection();
    	try {
        	st = (Statement) conn.createStatement();
        	
        	String ssql = "UPDATE " + username  + dictionaryName + " SET example = '" + example + "' WHERE content = '" + content + "'";
        	st.executeUpdate(ssql);
        } catch (SQLException e) {
        	e.printStackTrace();
		}
	}

	/**
	 * used to check if the username matches with the password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean matchUsernamePassword(String username, String password) {
		conn = getConnection();
		try {
			st = (Statement) conn.createStatement();

			String ssql = "SELECT * FROM user_info WHERE username = '" + username + "' AND password = '" + password
					+ "'";
			ResultSet rs = st.executeQuery(ssql);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * used to change the password of a user
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean changePassword(String username, String password) {
		conn = getConnection();
		try {
			st = (Statement) conn.createStatement();
			String ssql = "UPDATE user_info SET password =	'" + password + "' WHERE username = '" + username + "'";
			st.executeUpdate(ssql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public void  setCorrectGuesses(String username, String dictionaryName, String content, int correct) {
		conn = getConnection();
    	try {
        	st = (Statement) conn.createStatement();
        	
        	String ssql = "UPDATE " + username  + dictionaryName + " SET correct = '" + correct + "' WHERE content = '" + content + "'";
        	st.executeUpdate(ssql);
        } catch (SQLException e) {
        	e.printStackTrace();
		}
	}
	
	public void  setIncorrectGuesses(String username, String dictionaryName, String content, int correct) {
		conn = getConnection();
    	try {
        	st = (Statement) conn.createStatement();
        	
        	String ssql = "UPDATE " + username  + dictionaryName + " SET incorrect = '" + correct + "' WHERE content = '" + content + "'";
        	st.executeUpdate(ssql);
        } catch (SQLException e) {
        	e.printStackTrace();
		}
	}
	
	public void createNewUser(String username, String password) {
		conn = getConnection();
		try {
			st = (Statement) conn.createStatement();
			String ssql = "INSERT INTO User_info VALUES('"+username+"', '"+password+"');" ;
			st.executeUpdate(ssql);
					
			ssql = "CREATE TABLE "+ username +"defaultEE AS (select * from dictionaryDefaultEE);";
			st.executeUpdate(ssql);
			
			ssql = "CREATE TABLE "+ username +"defaultEC AS (select * from dictionaryDefaultEC);";
			st.executeUpdate(ssql);
			
			ssql = "INSERT INTO Dictionary_info VALUES('" + username + "', '" + username + " " + "defaultEE')";
			st.executeUpdate(ssql);

			ssql = "INSERT INTO Dictionary_info VALUES('" + username + "', '" + username + " " + "defaultEC')";
			st.executeUpdate(ssql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
