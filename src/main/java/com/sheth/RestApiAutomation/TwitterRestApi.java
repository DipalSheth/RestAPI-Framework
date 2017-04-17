package com.sheth.RestApiAutomation;

import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.log4j.Logger;

import com.sheth.base.BaseRestApi;

public class TwitterRestApi extends BaseRestApi {

	private static Logger logger = Logger.getLogger(TwitterRestApi.class);

	public HttpResponse getData(String resource) {
		HttpResponse response = null;
		HttpGet get = get(resource);
		
		get.addHeader("content-type", "application/json");

		try {
			
			response = client.execute(get);
		} catch (ClientProtocolException e) {
			logger.error("There is ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("There is IOException");
			e.printStackTrace();
		} 

		return response;
	}


	public HttpResponse postData(String resource, HttpEntity entity) {
		HttpResponse response = null;
		HttpPost post = post(resource);
		
		try {			
			post.setEntity(entity); 
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			logger.error("There is ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("There is IOException");
			e.printStackTrace();
		} 

		return response;
	}

}
