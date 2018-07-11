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

import com.uxb2b.ecp.exception.CertServerException;


/**
 * 
 * @author steve
 *
 */
@Service
public class HttpService {
	
	private Logger log = LoggerFactory.getLogger(HttpService.class);
	
	/**
	 * 傳資料到CertServer加密
	 * 
	 * @param url CertServer加密
	 * @param stringJson 欲加密的Json電文
	 * @return byte[] CertServer加密後的電文
	 * @throws CertServerException 
	 */
	
	public String httpCert(String url,String stringJson) throws CertServerException{

		  // HTTP POST 請求物件
		PostMethod post = new PostMethod(url);
		
		HttpClient httpclient = new HttpClient();
		byte[] responseByte=null;
		String responseString=null;
		try {
			RequestEntity entity =new ByteArrayRequestEntity(Base64.getEncoder().encode(stringJson.getBytes()));
//			RequestEntity entity =new StringRequestEntity(stringJson);
			post.setRequestHeader("Content-Length", entity.getContentLength() + "");
			post.setRequestEntity(entity);
		    int result = httpclient.executeMethod(post);
		    log.info("Response status code: " + result);
		    if(result==200){
	//		    System.out.println("Response body: ");
	//		    System.out.println(post.getResponseBodyAsString());
	//		    responseByte=post.getResponseBody();
			    BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));  
	
			    StringBuffer stringBuffer = new StringBuffer();  
	
			    String str = "";  
	
			    while((str = reader.readLine())!=null){  
	
			       stringBuffer.append(str);  
	
			    }  	
//			    String ts = stringBuffer.toString();
			    responseString = stringBuffer.toString();
//			    responseByte=ts.getBytes();
		    }else{	    	
		    	throw new RuntimeException("Failed : HTTP error code : "+ result);
		    }
		}catch(Exception e){
			log.error("電文加簽失敗 :"+e);
			throw new CertServerException("電文加簽失敗 ");
		} finally {
		    post.releaseConnection();
		}
		
		
		return responseString;
	}
	
	
}
