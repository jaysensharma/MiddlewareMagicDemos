import java.io.*;
import java.net.*;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;

public class TestHttpsClient implements javax.net.ssl.X509TrustManager {

	public static void main(String[] args) throws Exception {
		TestHttpsClient test=new TestHttpsClient();
		String resourceURL="https://localhost:8443/test/index.jsp";
		String UserName = "test";  // this is just dummy credentials we dont need it until WebApplication asks for Basic Auth Credentials
		String Password = "test";
		InputStream InputStream=test.doHttpsUrlConnectionAction(resourceURL, UserName, Password);
	}


	public InputStream doHttpsUrlConnectionAction(String resourceURL,String UserName,String Password) throws Exception {
		URL url;
		int responseCode = 0;
		// ###########
		SSLContext sc = SSLContext.getInstance("SSLv3");
		TrustManager[] tma = { new TestHttpsClient() };
		sc.init(null, tma, null);
		SSLSocketFactory ssf = sc.getSocketFactory();
		HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
		// ###########
		HttpsURLConnection connection = null;

		String nurl = resourceURL;
		System.out.println("\n\t resourceURL = " + resourceURL);
		HostnameVerifier hv = new HostnameVerifier() {
			                  public boolean verify(String urlHostName, SSLSession session) {
				                                System.out.println("Warning: URL Host: " + urlHostName+ " vs. " + session.getPeerHost());
				                                return true;
			                                        }
		                                             };

		HttpsURLConnection.setDefaultHostnameVerifier(hv);
		try {
			url = new URL(nurl);
			connection = (HttpsURLConnection) url.openConnection();

                        //  Following two lines can be uncommented if you want your client to pass Basic Authentication Credentials as well
                        //
			//  String encoding = encodeUsernamePasswordBase64(UserName,Password);
			//  connection.setRequestProperty("Authorization", "Basic " + encoding);
                        //

			System.out.println("Conn established " + connection);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			responseCode = connection.getResponseCode();
			System.out.println("response code : " + responseCode);
			connection.connect();
		} catch (Exception e) {
			System.err.println(e);
		}

		InputStream inputStream = null;
		try {
			inputStream = connection.getInputStream();
			System.out.println("Received Data: as Following:\n\n");
                        StringBuilder sb=new StringBuilder();
                        BufferedInputStream bis = new BufferedInputStream(inputStream);
                        while (bis.available() > 0) 
                          {
                             System.out.print((char)bis.read());
                          }
                        bis.close();
                        inputStream.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		return inputStream;
	}

	public void checkClientTrusted(X509Certificate[] chain, String authType) {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}


        //      Following method can be uncommented if you want to sent the Basic authentication credential as well 

	//public String encodeUsernamePasswordBase64(String UserName,String Password) {
	//	String userPassword = UserName + ":" + Password;
	//	byte[] encodedByte = org.apache.commons.codec.binary.Base64.encodeBase64(userPassword.getBytes());
	//	String encodedBase64String = new String(encodedByte);
	//	return encodedBase64String;
	//}
 }
