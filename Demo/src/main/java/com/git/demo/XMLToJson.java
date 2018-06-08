package com.git.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.json.XML;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import net.sf.json.JSONException;

@Controller
@EnableAutoConfiguration
public class XMLToJson {
	@SuppressWarnings("deprecation")
	public static String toJson() throws IOException,JSONException {
		InputStream in = XMLToJson.class.getResourceAsStream("/TestXML.xml");
		String str = "";
			str = IOUtils.toString(in);
			in.close();
		return str;
	}
	
	@RequestMapping("/print")
	@ResponseBody
	public String print() {
		/*StringBuilder arg0 = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		arg0.append("<note>\r\n").append("<to>Tove</to>\r\n").append("<from>Jani</from>\r\n")
				.append("<heading>Reminder</heading>\r\n").append("<body>Don't forget me this weekend!</body>\r\n")
				.append("</note>");
		
		String str2 = XML.toJSONObject(arg0.toString()).toString();*/
		try {
			File file = new File(new File("").getAbsolutePath()+ "/src/main/resources/outJson.txt");
			OutputStream out = new FileOutputStream(file);
			String printStr = toJson();
			byte[] outByte = XML.toJSONObject(printStr).toString().getBytes("UTF-8");
			out.write(outByte);
			out.close();
			return new String(outByte,"UTF-8");
		} catch (JSONException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(XMLToJson.class, args);
	}
}
