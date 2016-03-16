package com.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	public static void fromPost(String... parm) {
		try {
			URL postUrl = new URL(parm[0]);
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			String content = "phone=" + parm[1] + "&vcode=" + parm[2];
			out.writeBytes(content);

			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			System.out.println("=============================");
			System.out.println("Contents of post request");
			System.out.println("=============================");
			while ((line = reader.readLine()) != null) {
				System.out.println("结果==>"+line);
			}
			System.out.println("=============================");
			System.out.println("Contents of post request ends");
			System.out.println("=============================");
			reader.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		HttpUtil.fromPost("Http://192.168.1.106:8080/sms","18543145223","123456");
	}
}
