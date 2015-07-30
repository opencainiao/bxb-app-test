package test.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import util.HpptGetUtil;

/****
 * 测试添加客户
 * 
 * @author NBQ
 *
 */
public class ClientFindOne {

	private static String userid = "55b08c1d6a94f8b336a4a43d";

	public static void findOne(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		Map<String, String> params = makeParams();

		String result = HpptGetUtil.doGet(httpClient, url, params, "UTF-8");

		System.out.println(result);
	}

	public static void findAllByUserId() {

		String url = "http://localhost:8080/bxb/app/client/list_by_userid";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		Map<String, String> params = makeParams();

		String result = HpptGetUtil.doGet(httpClient, url, params, "UTF-8");

		System.out.println(result);
	}

	private static Map<String, String> makeParams() {

		Map<String, String> params = new HashMap<String, String>();

		params.put("user_id", userid);

		return params;
	}

	public static void main(String[] args) {

		// String url = "http://182.92.114.61:8080/bxb/app/client/add";
		String url = "http://localhost:8080/bxb/app/client/55b9d36b7327331f641ff278";
		findOne(url);

		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println("用户的所有客户信息如下");
		findAllByUserId();
	}
}
