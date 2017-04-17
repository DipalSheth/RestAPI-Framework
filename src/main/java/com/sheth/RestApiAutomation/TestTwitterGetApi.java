package com.sheth.RestApiAutomation;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.sheth.base.BaseRestApi;


public class TestTwitterGetApi {
	TwitterRestApi twitter = new TwitterRestApi();

	@Test
	public void testGetData() throws JSONException,IllegalStateException, IOException {

		HttpResponse response = twitter.getData("account/settings.json");
		System.out.println(response.getStatusLine().getStatusCode());

		JSONObject json = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
		System.out.println(json.get("screen_name"));

		assertEquals(200, response.getStatusLine().getStatusCode());
		assertEquals("DipalSheth26", json.get("screen_name"));
		assertEquals("en", json.get("language"));

	}

}
