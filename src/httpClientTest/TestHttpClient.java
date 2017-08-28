package httpClientTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class TestHttpClient {

	public static void main(String[] args) throws ClientProtocolException, IOException {
//		postMethod();
		getMethod();
	}
	public static void postMethod() throws ClientProtocolException, IOException{
		String url = "http://www.jycinema.com/frontUIWebapp/appserver/commonSaafAreasService/findSaafAreas";
		 //创建httpclient对象  
        CloseableHttpClient client = HttpClients.createDefault();  
        //创建post方式请求对象  
        HttpPost httpPost = new HttpPost(url);  
        //装填参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
        
        JSONObject param = new JSONObject();
        param.put("areaLevel", "1");
        param.put("channelCode", "J0002");
        param.put("channelId", "3");
        nvps.add(new BasicNameValuePair("params",param.toString()));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
        
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");  
//        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        CloseableHttpResponse response = client.execute(httpPost);  
        //获取结果实体  
        HttpEntity entity = response.getEntity();  
        if (entity != null) {  
            //按指定编码转换结果实体为String类型  
            String body = EntityUtils.toString(entity, "utf-8");  
            System.out.println(body);
            JSONObject jsonObject = JSONObject.parseObject(body);
            System.out.println(jsonObject.getString("oldDate"));
        }  
        EntityUtils.consume(entity);  
        //释放链接  
        response.close();  
	}
	public static void getMethod() throws ClientProtocolException, IOException{
		String url = "http://gc.ditu.aliyun.com/geocoding";
		 //创建httpclient对象  
       CloseableHttpClient client = HttpClients.createDefault();  
       //创建post方式请求对象  
       String param = "a=苏州市";
       HttpGet httpGet = new HttpGet(url+"?"+param);  
       CloseableHttpResponse response = client.execute(httpGet);  
       //获取结果实体  
       HttpEntity entity = response.getEntity();  
       if (entity != null) {  
           //按指定编码转换结果实体为String类型  
           String body = EntityUtils.toString(entity, "utf-8");  
           System.out.println(body);
           
       }  
	}
}
