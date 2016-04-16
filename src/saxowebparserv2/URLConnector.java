package saxowebparserv2;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class URLConnector {
	private Document doc;
	private String path;
	private String hour;
	private Elements tables;
	private File[] files;
	
	public URLConnector(String html, String path, String hour) throws Exception{
		doc = Jsoup.connect(html).get();
		this.hour = hour;
		this.path = path;
		checkDates(doc);
	}
	
	private void checkDates(Document document)throws Exception{
		String currentDate = document.getElementsByClass("blogTitle").text();
		if(!(currentDate.contains(hour))){
			System.out.println("system exit 1");
		} else {
			checkFiles(currentDate, path);
		}
	}
	
	private void checkFiles(String currentDate, String path)throws Exception{
		String[] extractedDate = currentDate.split(",");
		DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
		Date date = df.parse(extractedDate[0]);
		df = new SimpleDateFormat("dd-MM-yyyy");
		String stringdate = df.format(date);
		String[] paths = {path+"ATM VOLATILITIES/",path+"25-DELTA RISK REVERSAL/"};
		
		
		
		boolean[] test= new boolean[2];
		String[] vols = {paths[0]+stringdate+"\nATM VOLATILITIES.xml",paths[1]+stringdate+ "\n25-DELTA RISK REVERSAL.xml"};
		files = new File[2];
		for(int i = 0;i<files.length;i++){
			files[i] = new File(paths[i]);
			files[i].mkdirs();
			files[i] = new File(vols[i]);
			test[i]=files[i].createNewFile();
		}
		
		if(test[0]==false && test[1]==false){
			System.out.println("system exit 2");
		} else {
			saveTables(test);
		}
	}
	
	private void saveTables(boolean[] test)throws Exception{
		tables = doc.getElementsByClass("volTable");
		
		for(int i=0;i<test.length;i++){
			if(test[i]==true){
				String table = tables.get(i).toString();
				FileWriter fw = new FileWriter(files[i]);
				fw.write(table);
				fw.close();
			}
		}
		
	}
	
}
