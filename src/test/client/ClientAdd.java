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
 * 测试添加客户
 * 
 * @author NBQ
 *
 */
public class ClientAdd {

	private static String userid = "55b08c1d6a94f8b336a4a43d";

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
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
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

		List<Client> clients = makeClients(1);

		String clientsStr = JSONHelper.toJSON(clients);
		System.out.println("=========================================");
		System.out.println(clientsStr);
		System.out.println("=========================================");

		// Client[] clientsParsed = JSONHelper.parseArray(clientsStr,
		// Client.class);
		// System.out.println(JSONHelper.toJSON(clientsParsed));
		//

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("userid", userid));
		formparams.add(new BasicNameValuePair("data", clientsStr));

		return formparams;
	}

	/****
	 * 创建客户信息
	 * 
	 * @return
	 */
	private static List<Client> makeClients(int num) {

		List<Client> clients = new ArrayList<Client>();

		for (int i = 0; i < num; ++i) {
			Client client = new Client();
			client.setBirth_date("1983-01-08");
			client.setBirth_ages("1");
			client.setBlood_group("1");
			client.setBoy_num(2);
			client.setAge_group("A");
			client.setAnnual_income_family(36.10);
			client.setAnnual_income_family_type("1");
			client.setAnnual_income_personal(25);
			client.setAnnual_income_personal_type("2");
			client.setCareer_type("1");
			client.setClient_name("测试用户_" + i);
			client.setCompany("中国农业银行xxxx");
			client.setCompany_nature("1");
			client.setConstellation("12");
			client.setContact_attention("注意着装");
			client.setContact_type("A");
			client.setEducation_type("1");
			client.setFamily_financial_standing("A");
			client.setFamily_income_feature("1");
			client.setGirl_num(1);
			client.setIntroducer_closeness("1");
			client.setIntroducer_evaluation("人不错 热情");
			client.setIntroducer_name("张三丰");
			client.setIntroducer_relationship("1");
			client.setJob_level("1");
			client.setJob_position("1");
			client.setMarital_status("1");
			client.setOwner_user_id(userid);
			client.setPdp_type("1");
			client.setRegion_code("1_31");
			client.setRegion_type("1");
			client.setRegion_name("北京_东城");
			client.setSex("1");
			client.setSource_type("1");
			client.setTrade_type("1");
			client.setWedding_date("06-10");

			createAddress(client);
			createPhone(client);
			createEmails(client);
			clients.add(client);

		}

		return clients;
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
		phone.setType_name("个人");
		phone.setPhone_number("15010207211");

		phones.add(phone);

		phone = new Phone();
		phone.setType_value("2");
		phone.setType_name("公司");
		phone.setPhone_number("010-6522xxxx");

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
		address.setType_name("个人");
		address.setProvince(1);
		address.setProvince_name("北京");
		address.setCity(13);
		address.setCity_name("市辖区");
		address.setDistrict(35);
		address.setDistrict_name("东城");
		address.setDetail_address("xx街xx号");

		addresses.add(address);

		address = new Address();
		address.setType_value("2");
		address.setType_name("公司");
		address.setProvince(1);
		address.setProvince_name("北京");
		address.setCity(13);
		address.setCity_name("市辖区");
		address.setDistrict(35);
		address.setDistrict_name("西城");
		address.setDetail_address("xx街xx号");

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
		email.setType_name("个人");
		email.setEmail("25662xxx@qq.com");

		emails.add(email);

		email = new Email();
		email.setType_value("2");
		email.setType_name("公司");
		email.setEmail("298980981@qq.com");

		emails.add(email);

		client.setEmail_info(emails);
	}

	public static void main(String[] args) {

		String url = "http://182.92.114.61:8080/bxb/app/client/add";
		// String url = "http://localhost:8080/bxb/app/client/add";
		post(url);
	}
}
