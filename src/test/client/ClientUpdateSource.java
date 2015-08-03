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
 * 测试修改客户来源信息
 * 
 * @author NBQ
 *
 */
public class ClientUpdateSource {

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
		formparams.add(new BasicNameValuePair("part_flg", "4"));

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
		client.setBirth_ages("source");
		client.setBlood_group("source");
		client.setBoy_num(-1);
		client.setAge_group("source");
		client.setAnnual_income_family(66.66);
		client.setAnnual_income_family_type("source_B");
		client.setAnnual_income_personal(33.33);
		client.setAnnual_income_personal_type("source_A");
		client.setCareer_type("source");
		client.setClient_name("source");
		client.setCompany("source");
		client.setCompany_nature("source");
		client.setConstellation("32");
		client.setContact_attention("source");
		client.setContact_type("source");
		client.setEducation_type("source");
		client.setFamily_financial_standing("source_BB");
		client.setFamily_income_feature("source_AA");
		client.setGirl_num(-2);
		client.setIntroducer_closeness("SOURCE_QMD");
		client.setIntroducer_evaluation("憨厚 老实");
		client.setIntroducer_name("介绍人_李四");
		client.setIntroducer_relationship("SOURCE_B");
		client.setJob_level("source");
		client.setJob_position("source");
		client.setMarital_status("source");
		client.setOwner_user_id(userid);
		client.setPdp_type("source");
		client.setRegion_code("source");
		client.setRegion_type("source");
		client.setRegion_name("source");
		client.setSex("source");
		client.setSource_type("SOURCE_A");
		client.setTrade_type("source");
		client.setWedding_date("source");

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
