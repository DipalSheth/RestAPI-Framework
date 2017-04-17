package com.sheth.base;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import com.sheth.helper.Constants;
import com.sheth.helper.HelperConfig;

public class BaseRestApi {
	public HttpClient client = HttpClientBuilder.create().build();
	final OAuthConsumer consumer;

public BaseRestApi(){
	HelperConfig.getConfig(Constants.PATH + "config.properties");

	consumer = new CommonsHttpOAuthConsumer(HelperConfig.consumerKey,HelperConfig.consumerSecret);
	consumer.setTokenWithSecret(HelperConfig.tokenkey,HelperConfig.tokensecret);
}
	public HttpGet get(String resource)  {
		HttpGet get = new HttpGet(HelperConfig.endpoint + resource);
		try{
		consumer.sign(get);
		}catch(Exception e){
			e.printStackTrace();
		}
		return get;
	}

	public HttpPost post(String resource)  {
		HttpPost post = new HttpPost(HelperConfig.endpoint + resource);
		try{
			consumer.sign(post);
			}catch(Exception e){
				e.printStackTrace();
			}
		return post;
	}

}
