package es.ucm.fdi.edd.core.json.utils;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	private static final String UTF_8 = "utf-8";

	private final Gson gson;

	public JsonUtils() {
		this(true);
	}

	public JsonUtils(boolean prettyPrinting) {
		if (prettyPrinting) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			// gsonBuilder.excludeFieldsWithoutExposeAnnotation();
			Gson gson = gsonBuilder.create();
			this.gson = gson;
		} else {
			this.gson = new Gson();
		}
	}

	public Object from(byte[] bytes, Class<?> clazz) throws UnsupportedEncodingException {
		Reader reader = new InputStreamReader(new ByteArrayInputStream(bytes), UTF_8);
		return gson.fromJson(reader, clazz);
	}

	public Object fromFile(String filePath, Class<?> clazz) throws IOException {
		FileReader fileReader = new FileReader(filePath);
		Object fromJson = gson.fromJson(fileReader, clazz);
		return fromJson;
	}
	
	public Object fromString(String content, Class<?> clazz) throws IOException {
		Object fromJson = gson.fromJson(content, clazz);
		return fromJson;
	}

	public void toStream(Object object, OutputStream bytes) throws IOException {
		Writer writer = new OutputStreamWriter(bytes, UTF_8);
		gson.toJson(object, writer);
		writer.close();
	}

	public void toStream(Object object, OutputStream bytes, String variableName) throws IOException {
		Writer writer = new OutputStreamWriter(bytes, UTF_8);
		writer.append(getVarExpresion(variableName));
		gson.toJson(object, writer);
		writer.close();
	}

	private String getVarExpresion(String variableName) {
		return "var " + variableName + " = ";
	}

	public void toFile(Object object, String filePath) throws IOException {
		Writer writer = new FileWriter(filePath);
		gson.toJson(object, writer);
		writer.close();
	}

	public void toFile(Object object, String filePath, String variableName)	throws IOException {
		Writer writer = new FileWriter(filePath);
		writer.append(getVarExpresion(variableName));
		gson.toJson(object, writer);
		writer.close();
	}

	public String toString(Object object) {
		String jsonOutput = gson.toJson(object);
		return jsonOutput;
	}
}
