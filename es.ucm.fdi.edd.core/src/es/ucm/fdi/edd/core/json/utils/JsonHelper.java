package es.ucm.fdi.edd.core.json.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class JsonHelper {
	
	private static final String ISO_ENCODING = "ISO-8859-1";
	
	public static Object readJson(URL url, Class<?> clazz) {
		try {
			InputStream is = url.openStream();
			byte[] bytes = convertInputStreamToByteArray(is);
			
			JsonUtils jsonUtils = new JsonUtils();
			Object root = jsonUtils.from(bytes, clazz);
			return root;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Object readJsonFromFile(String path, Class<?> clazz) throws IOException {
		JsonUtils jsonUtils = new JsonUtils();
		Object root = jsonUtils.fromFile(path, clazz);
		return root;
	}
	
	public static Object readJsonFromString(String content, Class<?> clazz) throws IOException {
		JsonUtils jsonUtils = new JsonUtils();
		Object root = jsonUtils.fromString(content, clazz);
		return root;
	}

	public static Object readJson(InputStream is, Class<?> clazz) throws UnsupportedEncodingException {
		byte[] bytes = convertInputStreamToByteArray(is);
		JsonUtils jsonUtils = new JsonUtils();
		Object root = jsonUtils.from(bytes, clazz);
		return root;
	}

	private static byte[] convertInputStreamToByteArray(InputStream inputStream) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte data[] = new byte[1024];
			int count;

			while ((count = inputStream.read(data)) != -1) {
				bos.write(data, 0, count);
			}

			bos.flush();
			bos.close();
			inputStream.close();

			bytes = bos.toString(ISO_ENCODING).getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
}
