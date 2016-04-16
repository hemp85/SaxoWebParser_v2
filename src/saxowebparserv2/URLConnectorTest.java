package saxowebparserv2;

import static org.junit.Assert.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class URLConnectorTest {
	private Document doc;
	private String html= "http://fxowebtools.saxobank.com/otc.html";
	
	@Before
	public void setUp() throws Exception {
		//doc = Jsoup.connect("http://fxowebtools.saxobank.com/otc.html").get();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConnector() throws Exception {
		URLConnector urlc = new URLConnector(html,"/home/hemp85/SaxoDatabaseTEST/","1230");
		
	}

}
