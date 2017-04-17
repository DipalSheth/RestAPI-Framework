package com.sheth.RestApiAutomation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import static org.testng.Assert.assertEquals;



import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sheth.helper.Constants;
import com.sheth.helper.ExcelHelper;


public class TestTwitterPostApi {
	TwitterRestApi twitter = new TwitterRestApi();

	@DataProvider(name = "postdata")
	public Object[][] testPostData() {
		return ExcelHelper.getData(Constants.PATH+"/rest-data/restdata.xlsx","post");
	}

	@Test(dataProvider = "postdata")
	public void testPostAccountSetting(String resource, String name,String location, String expectedName, String expectedLocation)
			throws JSONException, IllegalStateException, IOException {

		/*
		 * JSONObject postObject=new JSONObject(); postObject.put("name", name);
		 * postObject.put("location", location); StringEntity entity=new
		 * StringEntity(postObject.toString());
		 */

		List<NameValuePair> entity = new ArrayList<NameValuePair>();
		entity.add(new BasicNameValuePair("name", name));
		entity.add(new BasicNameValuePair("location", location));
		HttpEntity reqentity = new UrlEncodedFormEntity(entity);

		HttpResponse response = twitter.postData(resource, reqentity);
		//System.out.println(response.getStatusLine().getStatusCode());

		JSONObject jsonresponse = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
		System.out.println(jsonresponse.toString());
		System.out.println(jsonresponse.get("screen_name"));
		System.out.println(jsonresponse.get("name"));
		System.out.println(jsonresponse.get("location"));
		assertEquals(200, response.getStatusLine().getStatusCode());
		assertEquals(expectedName, jsonresponse.get("name"));
		assertEquals("DipalSheth26", jsonresponse.get("screen_name"));
		assertEquals(expectedLocation, jsonresponse.get("location"));

	}
}
