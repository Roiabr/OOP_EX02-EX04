package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import GIS.GisToElement;
import GIS.MetaData;
import GIS.Meta_data;

public class CSVReader {
	public static ArrayList<String[]> writeFileKML(String place ) {
		ArrayList<String[]> content = new ArrayList<String[]>();
		String line = "";
		String cvsSplitBy = ",";
		try{
			BufferedReader br = new BufferedReader(new FileReader(place));
		//	br.readLine();
			//br.readLine();
			while ((line = br.readLine()) != null) {
				String[] userInfo = line.split(cvsSplitBy);
				content.add(userInfo);
				
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
		return content;
	}
	public static void main(String[] argh) {
		ArrayList<String[]> al  = new ArrayList<String[]>();
		al = writeFileKML("C:\\Users\\Roi Abramovitch\\Desktop\\WigleWifi_20171201110209.csv");
		String[] aq = al.get(0);
		System.out.println(aq);
		MetaData gis = new MetaData(aq);
		GisToElement gis1 = new GisToElement(aq);
		System.out.println(gis1.getName());
		System.out.println(gis1.getData());
		System.out.println(gis1.getGeom().getNewpoint());
		
		
	}
}