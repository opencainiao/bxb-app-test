package test.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.Client;
import model.Email;
import model.Phone;

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

import util.JSONHelper;

/****
 * 测试修改客户基本信息
 * 
 * @author NBQ
 *
 */
public class ClientUpdateBase {

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
					System.out
							.println("--------------------------------------");
					System.out.println("Response content: "
							+ EntityUtils.toString(entity, "UTF-8"));
					System.out
							.println("--------------------------------------");
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
		formparams.add(new BasicNameValuePair("part_flg", "0"));

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
		client.setBirth_ages("222222");
		client.setBlood_group("222222");
		client.setBoy_num(222222);
		client.setAge_group("A");
		client.setAnnual_income_family(36.30);
		client.setAnnual_income_family_type("222222");
		client.setAnnual_income_personal(288);
		client.setAnnual_income_personal_type("222222");
		client.setCareer_type("222222");
		client.setClient_name("测试修改用户88");
		client.setCompany("中国农业银行xxxx");
		client.setCompany_nature("222222");
		client.setConstellation("32");
		client.setContact_attention("mmmmmmmmmmmmm");
		client.setContact_type("A");
		client.setEducation_type("222222");
		client.setFamily_financial_standing("A");
		client.setFamily_income_feature("222222");
		client.setGirl_num(222222);
		client.setIntroducer_closeness("222222");
		client.setIntroducer_evaluation("mmmmmmmmmmmmm");
		client.setIntroducer_name("mmmmmmmmmmmmm");
		client.setIntroducer_relationship("222222");
		client.setJob_level("222222");
		client.setJob_position("222222");
		client.setMarital_status("222222");
		client.setOwner_user_id(userid);
		client.setPdp_type("222222");
		client.setRegion_code("3_33");
		client.setRegion_type("222222");
		client.setRegion_name("wfwfwfwfwfwf");
		client.setSex("222222");
		client.setSource_type("222222");
		client.setTrade_type("222222");
		client.setWedding_date("06-30");

		createAddress(client);
		createPhone(client);
		createEmails(client);

		return client;
	}

	/****
	 * 地址
	 * 
	 * @param client
	 */
	private static void createPhone(Client client) {

		List<Phone> phones = new ArrayList<Phone>();

		Phone phone = new Phone();
		phone.setType_value("1");
		phone.setType_name("个人1111");
		phone.setPhone_number("18801020721111111");

		phones.add(phone);

		phone = new Phone();
		phone.setType_value("222222");
		phone.setType_name("公司1111");
		phone.setPhone_number("010-68822xxxx111111");

		phones.add(phone);

		client.setPhone_info(phones);
	}

	/****
	 * 地址
	 * 
	 * @param client
	 */
	private static void createAddress(Client client) {

		List<Address> addresses = new ArrayList<Address>();

		Address address = new Address();
		address.setType_value("1");
		address.setType_name("个人111");
		address.setProvince(1);
		address.setProvince_name("北京111");
		address.setCity(13);
		address.setCity_name("市辖区111");
		address.setDistrict(388);
		address.setDistrict_name("东城111");
		address.setDetail_address("xx街xx号111");

		addresses.add(address);

		address = new Address();
		address.setType_value("222222");
		address.setType_name("公司111");
		address.setProvince(1);
		address.setProvince_name("北京111");
		address.setCity(13);
		address.setCity_name("市辖区111");
		address.setDistrict(388);
		address.setDistrict_name("西城111");
		address.setDetail_address("xx街xx号111");

		addresses.add(address);

		client.setAddress_info(addresses);
	}

	/****
	 * 邮箱
	 * 
	 * @param client
	 */
	private static void createEmails(Client client) {

		List<Email> emails = new ArrayList<Email>();

		Email email = new Email();
		email.setType_value("1");
		email.setType_name("个人111");
		email.setEmail("288662xxx@qq.com11");

		emails.add(email);

		email = new Email();
		email.setType_value("222222");
		email.setType_name("公司111");
		email.setEmail("298980981@qq.com11");

		emails.add(email);

		client.setEmail_info(emails);
	}

	public static void findOne() {
		String url = "http://localhost:8080/bxb/app/client/" + client_id;
		ClientFindOne.findOne(url);
	}

	public static void update() {
		String url = "http://localhost:8080/bxb/app/client/" + client_id
				+ "/update";
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
