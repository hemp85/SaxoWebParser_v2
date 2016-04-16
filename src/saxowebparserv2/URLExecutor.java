package saxowebparserv2;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class URLExecutor {
	
	private final static Logger LOGGER = Logger.getLogger(URLExecutor.class.getName());
	private static FileHandler fh;
	private static String url = "http://fxowebtools.saxobank.com/otc.html";
	private static String logpath;
	
	public static void main(String[] args)  {
	
		try{
			//checks or creates log in working directory
			String workingDir = System.getProperty("user.dir");
			logpath = workingDir+"/saxo.log";
			File loger = new File(logpath);
			loger.createNewFile();
			
			//creates logger
			fh = new FileHandler(logpath, true);
			fh.setFormatter(new SimpleFormatter());
			fh.setLevel(Level.WARNING);
			LOGGER.addHandler(fh);
			
			//checks or creates folder in path to store files
			Properties props = new Properties();
			FileInputStream isr = new FileInputStream(workingDir+"/config.properties");
			props.load(isr);
			File database = new File(props.getProperty("path"));
			database.mkdirs();
			String path = props.getProperty("path");
			String hour = props.getProperty("hour");
			
			URLConnector parser = new URLConnector(url, path, hour);
			
			
		
		} catch (Exception e){
			LOGGER.log(Level.WARNING,"an exception was thrown", e);
			
		}
	
	} 

}
