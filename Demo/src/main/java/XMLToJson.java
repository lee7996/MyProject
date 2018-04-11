
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.json.XML;

import net.sf.json.JSONException;


public class XMLToJson {
	@SuppressWarnings("deprecation")
	public static String toJson() throws IOException,JSONException {
		InputStream in = XMLToJson.class.getResourceAsStream("/TestXML.xml");
		String str = "";
			str = IOUtils.toString(in);
			in.close();
		return str;
	}
	public static void main(String[] args) {
		StringBuilder arg0 = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		arg0.append("<note>\r\n").append("<to>Tove</to>\r\n").append("<from>Jani</from>\r\n")
				.append("<heading>Reminder</heading>\r\n").append("<body>Don't forget me this weekend!</body>\r\n")
				.append("</note>");
		
		String str2 = XML.toJSONObject(arg0.toString()).toString();
		try {
			File file = new File(new File("").getAbsolutePath()+ "/src/main/resources/outJson.txt");
			OutputStream out = new FileOutputStream(file);
			byte[] outByte = XML.toJSONObject(toJson()).toString().getBytes("UTF-8");
			out.write(outByte);
			System.out.println(str2);
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
