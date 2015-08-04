package test.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import model.Client;
import util.JSONHelper;

/****
 * 测试修改客户服务信息
 * 
 * @author NBQ
 *
 */
public class ClientUpdateService {

	private static String userid = "55b08c1d6a94f8b336a4a43d";
	private static String client_id = "55b9d4787327331f64005b2a";

	public static void post(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> formparams = makeParams();

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static List<NameValuePair> makeParams() {

		Client client = makeClient();

		String clientStr = JSONHelper.toJSON(client);
		System.out.println("=========================================");
		System.out.println(clientStr);
		System.out.println("=========================================");

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("user_id", userid));
		formparams.add(new BasicNameValuePair("data", clientStr));
		formparams.add(new BasicNameValuePair("part_flg", "6"));

		return formparams;
	}

	/****
	 * 创建客户信息
	 * 
	 * @return
	 */
	private static Client makeClient() {

		Client client = new Client();
		
		List<String> is = new ArrayList<String>();
		is.add("1");
		is.add("2");

		client.setBirth_date("1990-03-08");
		client.setBirth_ages("service");
		client.setBlood_group("service");
		client.setBoy_num(-1);
		client.setAge_group("service");
		client.setAnnual_income_family(66.66);
		client.setAnnual_income_family_type("service_B");
		client.setAnnual_income_personal(33.33);
		client.setAnnual_income_personal_type("service_A");
		client.setCareer_type("service");
		client.setClient_name("service");
		client.setCompany("service");
		client.setCompany_nature("service");
		client.setConstellation("");
		client.setContact_attention("注意先打电话提前联系");
		client.setContact_type("service_AA");
		client.setEducation_type("service");
		client.setFamily_financial_standing("service_BB");
		client.setFamily_income_feature("service_AA");
		client.setGirl_num(-2);
		client.setIntroducer_closeness("service_QMD");
		client.setIntroducer_evaluation("憨厚 老实");
		client.setIntroducer_name("介绍人_李四");
		client.setIntroducer_relationship("service_B");
		client.setInteresting_service(is);
		client.setJob_level("service");
		client.setJob_position("service");
		client.setMarital_status("service");
		client.setOwner_user_id(userid);
		client.setPdp_type("service");
		client.setRegion_code("service");
		client.setRegion_type("service");
		client.setRegion_name("service");
		client.setSex("service");
		client.setSource_type("service_A");
		client.setTrade_type("service");
		client.setWedding_date("service");

		return client;
	}

	public static void findOne() {
		String url = "http://localhost:8080/bxb/app/client/" + client_id;
		ClientFindOne.findOne(url);
	}

	public static void update() {
		String url = "http://localhost:8080/bxb/app/client/" + client_id + "/update";
		post(url);
	}

	public static void main(String[] args) {

		// String url = "http://182.92.334.61:8080/bxb/app/client/add";
		// String url =
		// "http://localhost:8080/bxb/app/client/8888b85c67caf87373e36f7844/delete";
		// post(url);

		System.out.println("修改前----------------------");
		findOne();

		update();

		System.out.println("修改后----------------------");
		findOne();
	}
}
