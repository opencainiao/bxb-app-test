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
 * 测试修改客户工作信息
 * 
 * @author NBQ
 *
 */
public class ClientUpdateWork {

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
		formparams.add(new BasicNameValuePair("part_flg", "2"));

		return formparams;
	}

	/****
	 * 创建客户信息
	 * 
	 * @return
	 */
	private static Client makeClient() {

		Client client = new Client();

		client.setBirth_date("work");
		client.setBirth_ages("work");
		client.setBlood_group("work");
		client.setBoy_num(-1);
		client.setAge_group("work");
		client.setAnnual_income_family(36.30);
		client.setAnnual_income_family_type("work");
		client.setAnnual_income_personal(288);
		client.setAnnual_income_personal_type("work");
		client.setCareer_type("1");
		client.setClient_name("work");
		client.setCompany("xxxx公司");
		client.setCompany_nature("1");
		client.setConstellation("work");
		client.setContact_attention("work");
		client.setContact_type("A");
		client.setEducation_type("work");
		client.setFamily_financial_standing("A");
		client.setFamily_income_feature("work");
		client.setGirl_num(-2);
		client.setIntroducer_closeness("work");
		client.setIntroducer_evaluation("work");
		client.setIntroducer_name("work");
		client.setIntroducer_relationship("work");
		client.setJob_level("1");
		client.setJob_position("1");
		client.setMarital_status("work");
		client.setOwner_user_id(userid);
		client.setPdp_type("work");
		client.setRegion_code("work");
		client.setRegion_type("work");
		client.setRegion_name("work");
		client.setSex("work");
		client.setSource_type("work");
		client.setTrade_type("1");
		client.setWedding_date("work");

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

		System.out.println("修改前----------------------");
		findOne();

		update();

		System.out.println("修改后----------------------");
		findOne();
	}
}
