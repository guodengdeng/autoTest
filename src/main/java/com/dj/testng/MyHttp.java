package com.dj.testng;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class MyHttp {

	private static final CloseableHttpClient httpclient;

	public static final String DEFAULT_CHARSET = "UTF-8";

	static {

		RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(3000).build();

		httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

	}

	public static Result sendGet(String url, Map<String, Object> params)
			throws ParseException, UnsupportedEncodingException, IOException {

		if (params != null && !params.isEmpty()) {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
			for (String key : params.keySet()) {
				pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
			}
			url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs), DEFAULT_CHARSET);
		}

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity, DEFAULT_CHARSET);

		return new Result(statusCode, content);

	}

	public static void main(String[] args) {
		try {
			Result result = sendGet("http://www.weather.com.cn/data/cityinfo/101010100.html", null);
			//Result result = sendGet("http://10.105.7.118:8080/BackProgect/ESB5810", null);
			System.out.println(result.getHttpStatus());
			System.out.println(result.getContent());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
