package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Cvs2Kml {
	public static void Csv2Kml(String csvFile,String output) throws IOException {
		ArrayList<String[] > al = new ArrayList<String[] >();
		ArrayList<String> content = new ArrayList<String>();
		al=CSVReader.writeFileKML(csvFile); 
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";
		content.add(kmlstart);

		String kmlend = "</kml>";

		try {
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 1; i < al.size(); i++) {
				String[] s = al.get(i);
				
				String kmlelement ="<name><![CDATA["+s[1]+"]]></name>\n" +
						"<description>"+"<![CDATA[BSSID: <b>"+s[0]+"\n"+
						"</b><br/>Capabilities: <b>"+s[2]+"\n"+
						"</b><br/>Frequency: <b>"+s[4]+"\n"+
						"</b><br/>Date: <b>"+s[3] + "</b>]]></description><styleUrl>#red</styleUrl>" +
					"<Point>\n" +
						"<coordinates>"+s[7]+","+s[6]+"</coordinates>" +
					"</Point>\n" +
					"</Placemark>\n";
				content.add(kmlelement);
			}
			content.add(kmlend);
			String csv = content.toString().replaceAll(",", "").replace("[", "").replace("]", "");
			bw.write(csv);
			bw.close();
			System.out.println("done");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		}




	}



}
