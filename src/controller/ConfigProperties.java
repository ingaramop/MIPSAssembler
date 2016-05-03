/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */

package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;

import view.MsgBox;

public class ConfigProperties {

	//variable names
	private final static String USE_LATEST_DIR_NAME = "useLatestDir";
	private final static String LATEST_DIR_NAME = "latestDir";
	private final static String DEFAULT_DIR_NAME = "defaultDir";
	//hardcoded default values
	private final static String USE_LATEST_DIR_DEFAULT = "true";
	private final static String LATEST_DIR_DEFAULT = "C:/";
	private final static String DEFAULT_DIR_DEFAULT = "C:/";
	
	
	private Properties inMemoryProperties;
	//config file directory
	private String configFileDir;


	
	/**
	 * The constructor must be called when the program starts. 
	 * It opens the properties files set in CONFIG_FILE_DIR, loads the properties file into memory and closes the file.
	 * If this is not possible, loads a default property.
	 */
	public ConfigProperties(){

		configFileDir=System.getProperty("user.home")+File.separator+"LFWconfig.properties";
		//Creates a default properties, in case there is a problem loading an old properties from the input stream		
		inMemoryProperties = new Properties(this.createDefaultProperties());
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(configFileDir);
			inMemoryProperties.load(inputStream);
			inputStream.close();
		} catch (FileNotFoundException e) {
			//MsgBox.info("FileNotFoundException: Error loading Config.properties file. Loading default values");
			e.printStackTrace();
		} catch (IOException e) {
			//MsgBox.info("IOException: Error loading Config.properties file. Loading default values");
			e.printStackTrace();
		}		

	}
	
	/**
	 * @return A new properties object with the default values hardcoded.
	 */
	private static Properties createDefaultProperties() {
		Properties prop = new Properties();
		prop.setProperty(USE_LATEST_DIR_NAME, USE_LATEST_DIR_DEFAULT);
		prop.setProperty(LATEST_DIR_NAME, LATEST_DIR_DEFAULT);
		prop.setProperty(DEFAULT_DIR_NAME, DEFAULT_DIR_DEFAULT);
		return prop;
	}


	/**
	 * Saves properties into file described in CONFIG_FILE_DIR
	 * TODO: Check if this method could be moved to Controller
	 */
	public void savePropertiesToDisk() throws IOException{
		FileOutputStream outputStream = new FileOutputStream(configFileDir);
		inMemoryProperties.store(outputStream, "ok");
		outputStream.close();
	}

	//GETTERS
	public String getLatestDir() {
		return inMemoryProperties.getProperty(LATEST_DIR_NAME);
	}

	public String getDefaultDir() {
		return inMemoryProperties.getProperty(DEFAULT_DIR_NAME);
	}

	public boolean getUseLatestDir() {
		return Boolean.valueOf(inMemoryProperties.getProperty(USE_LATEST_DIR_NAME));
	}

	public String getInUseDir(){
		if(getUseLatestDir()==true)
			return getLatestDir();
		else
			return getDefaultDir();
	}
	
	//SETTERS
	public void setDefaultDir(String dir) {
		inMemoryProperties.setProperty(DEFAULT_DIR_NAME, dir);		
	}

	public void setLatestDir(String dir) {
		inMemoryProperties.setProperty(LATEST_DIR_NAME, dir);		
	}

	public void setUseLatestDir(String value) {
		inMemoryProperties.setProperty(USE_LATEST_DIR_NAME, value);
		
	}
	

	
}
