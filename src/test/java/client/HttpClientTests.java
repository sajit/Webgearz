package client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;
/**
 * These tests need the server to be up and running
 * @author Sajit Mathew Kunnumkal
 *
 */
public class HttpClientTests {
	private HttpClient client;
	@Before
	public void setup(){
		client = new DefaultHttpClient();
		
	}
	@Test
	public void testGetDictFile() throws URISyntaxException, HttpException, IOException{
		
		String str = "http://localhost:8085/tb/resources/aloha/i18n/en.dict";
		URI uri = new URI(str);
		HttpGet httpget = new HttpGet(uri);
		httpget.setHeader("Accept", "text/plain");
		for(Header header : httpget.getAllHeaders()){
			System.out.println("Header name "+ header.getName()  + " header value" + header.getValue());
		}
		HttpResponse response = client.execute(httpget);
		Assert.assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testGetImageFile()throws URISyntaxException, HttpException, IOException {
		String str = "http://localhost:8085/tb/resources/aloha/i18n/arsenalme.jpg";
		HttpGet httpget = new HttpGet(str);
		for(Header header : httpget.getAllHeaders()){
			System.out.println("Header name "+ header.getName()  + " header value" + header.getValue());
		}
		HttpResponse response = client.execute(httpget);
		Assert.assertEquals(200, response.getStatusLine().getStatusCode());
		Assert.assertNotNull(response.getEntity().getContent());
	}
	
	@Test
	public void testGetPluginDict() throws IOException, URISyntaxException, HttpException{
		//resources/aloha/plugins/com.gentics.aloha.plugins.Format/plugin.js
		String str = "http://localhost:8085/tb/resources/aloha/plugins/com.gentics.aloha.plugins.Format/i18n/en.dict";
		//resources/aloha/plugins/com.genetics.aloha.plugins.{pluginName}/i18n/{language}.dict
		URI uri = new URI(str);
		HttpGet httpget = new HttpGet(uri);
		httpget.setHeader("Accept", "text/plain");
		for(Header header : httpget.getAllHeaders()){
			System.out.println("Header name "+ header.getName()  + " header value" + header.getValue());
		}
		HttpResponse response = client.execute(httpget);
		Assert.assertEquals(200, response.getStatusLine().getStatusCode());
	}

}
