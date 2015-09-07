package pinguo.rocket.mq.comm;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class HttpHelper {
	
	public static String post(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();

		HttpGet httpget = new HttpGet(url);
		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(50).setConnectTimeout(50)
				.setSocketTimeout(50).build();
		httpget.setConfig(requestConfig);

		CloseableHttpResponse response = httpclient.execute(httpget);
		
		int status = response.getStatusLine().getStatusCode();
		if(status != 200){
			throw new Exception();
		}

		HttpEntity entity = response.getEntity();
		String jsonStr = EntityUtils.toString(entity, "utf-8");
		System.out.println(jsonStr);

		httpget.releaseConnection();
		return jsonStr;
	}
	
	public static String get(String url, Map<String, String> params) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();

		HttpPost httppost = new HttpPost(url);
		// httppost.setEntity(new UrlEncodedFormEntity());

		CloseableHttpResponse response = httpclient.execute(httppost);
		System.out.println(response.toString());

		HttpEntity entity = response.getEntity();
		String jsonStr = EntityUtils.toString(entity, "utf-8");
		System.out.println(jsonStr);

		httppost.releaseConnection();
		return jsonStr;
	}
}
