package es.ucm.fdi.edd.core.json.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JSONParser {

	private static final String ISO_8859_1 = "iso-8859-1";
	private static final String EOL = "\n";

	public JSONParser() {
		//
	}

//	public JSONObject getJSONFromUrl(String url) {
//		InputStream inputStream = getInputStreamFromUrl(url);
//		String json = getJSonAsString(inputStream);
//		return parseStringToJSon(json);
//	}

	private InputStream getInputStreamFromUrl(String urlText) {
		InputStream inputStream = null;
		try {
			// get URL content
			URL url = new URL(urlText);
			URLConnection conn = url.openConnection();
			// open the stream and put it into BufferedReader
			inputStream = conn.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String inputLine;
			
			// save to this filename
			if (false) {
				String fileName = "/users/mkyong/test.html";
				File file = new File(fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
	
				// use FileWriter to write file
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				while ((inputLine = br.readLine()) != null) {
					bw.write(inputLine);
				}
				bw.close();
				br.close();
				System.out.println("Done");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return inputStream;
	}

	private String getJSonAsString(InputStream inputStream) {
		String json = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, ISO_8859_1), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + EOL);
			}
			inputStream.close();
			json = sb.toString();
		} catch (Exception e) {
			System.out.println("Buffer Error" + "Error converting result " + e.toString());
		}

		return json;
	}

//	private JSONObject parseStringToJSon(String json) {
//		JSONObject jSonObj = null;
//		try {
//			jSonObj = new JSONObject(json);
//		} catch (JSONException e) {
//			Log.e("JSON Parser", "Error parsing data " + e.toString());
//		}
//
//		return jSonObj;
//	}
}