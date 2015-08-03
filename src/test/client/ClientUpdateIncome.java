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
 * 测试修改客户收入信息
 * 
 * @author NBQ
 *
 */
public class ClientUpdateIncome {

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
		formparams.add(new BasicNameValuePair("part_flg", "3"));

		return formparams;
	}

	/****
	 * 创建客户信息
	 * 
	 * @return
	 */
	private static Client makeClient() {

		Client client = new Client();

		client.setBirth_date("1990-03-08");
		client.setBirth_ages("income");
		client.setBlood_group("income");
		client.setBoy_num(-1);
		client.setAge_group("income");
		client.setAnnual_income_family(66.66);
		client.setAnnual_income_family_type("INCOME_B");
		client.setAnnual_income_personal(33.33);
		client.setAnnual_income_personal_type("INCOME_A");
		client.setCareer_type("income");
		client.setClient_name("income");
		client.setCompany("income");
		client.setCompany_nature("income");
		client.setConstellation("32");
		client.setContact_attention("income");
		client.setContact_type("income");
		client.setEducation_type("income");
		client.setFamily_financial_standing("INCOME_BB");
		client.setFamily_income_feature("INCOME_AA");
		client.setGirl_num(-2);
		client.setIntroducer_closeness("income");
		client.setIntroducer_evaluation("income");
		client.setIntroducer_name("income");
		client.setIntroducer_relationship("income");
		client.setJob_level("income");
		client.setJob_position("income");
		client.setMarital_status("income");
		client.setOwner_user_id(userid);
		client.setPdp_type("income");
		client.setRegion_code("income");
		client.setRegion_type("income");
		client.setRegion_name("income");
		client.setSex("income");
		client.setSource_type("income");
		client.setTrade_type("income");
		client.setWedding_date("income");

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
