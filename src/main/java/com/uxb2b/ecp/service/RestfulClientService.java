package com.uxb2b.ecp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.uxb2b.ecp.exception.RestfulException;


/**
 * 
 * @author steve
 *
 */
@Service
public class RestfulClientService {

	private Logger log = LoggerFactory.getLogger(RestfulClientService.class);
	
	/**
	 * 用RESTFUL傳送電文至GW
	 * 
	 * @param message 上行電文
	 * @param url GW的url
	 * @return String 下行電文
	 * @throws Exception
	 */
	public String resfulClient(String message,String url)throws Exception{
			
		PostMethod post = new PostMethod(url);
		
		HttpClient httpclient = new HttpClient();
		String output = "";
		try {
					
//			Client client = Client.create();
//			WebResource webResource = client.resource(url);
//			ClientResponse response = webResource.type("application/json").post(ClientResponse.class,new String(message));
//
//			if (response.getStatus() != 200) {
//				output = response.getEntity(String.class);
//				log.error("傳送電文失敗:"+output);
//				throw new Exception("Failed : HTTP error code : "+ response.getStatus());
//			}
//		
//			output = response.getEntity(String.class);
			
			RequestEntity entity =new StringRequestEntity(message);
			post.setRequestHeader("Content-Length", entity.getContentLength() + "");
			post.setRequestEntity(entity);
			//httpclient.setTimeout(60000);
		    int result = httpclient.executeMethod(post);
		    log.info("Response status code: " + result);
		    if(result==200){
			    BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(),"UTF-8"));  
	
			    StringBuffer stringBuffer = new StringBuffer();  
	
			    String str = "";  
	
			    while((str = reader.readLine())!=null){  
	
			       stringBuffer.append(str);  
	
			    }  	
			    output = stringBuffer.toString();  

			
		    }
		} catch (Exception e) {
				log.error("電文傳送失敗"+e);
				e.printStackTrace();
				throw new RestfulException("電文傳送失敗");
		} finally {
		    post.releaseConnection();
		}

		return output;
	}
		
	
	
	
	 public static void main(String[] args) {

			try {

				Client client = Client.create();

				WebResource webResource = client
				   .resource("http://192.168.200.82:9487/TestReceive/Receive.do");

				String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

				ClientResponse response = webResource.type("application/json")
				   .post(ClientResponse.class, input);

				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus());
				}

				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				
				System.out.println(output);

			  } catch (Exception e) {

				e.printStackTrace();

			  }

			}
	
}
