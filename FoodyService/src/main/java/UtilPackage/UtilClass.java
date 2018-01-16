package UtilPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class UtilClass {
	
	public static JsonObject parseStream2Json(InputStream incomingData ){
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject=null;
		try {
			jsonObject = (JsonObject) jsonParser.parse(new InputStreamReader(incomingData, "UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	public static String encodeImageToBase64(File file){
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String encodeString =null;
		byte[] bytes = new byte[(int)file.length()];
		try {
			in.read(bytes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		encodeString = DatatypeConverter.printBase64Binary(bytes);
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodeString;
	}
}
