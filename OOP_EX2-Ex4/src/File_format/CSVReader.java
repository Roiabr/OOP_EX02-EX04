package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;


import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisLayer;
import GIS.GisProject;
import GIS.GisToElement;
import GIS.MetaData;
import GIS.Meta_data;
import javafx.stage.DirectoryChooser;

public class CSVReader {
	File g;
	
	public static GisLayer writeFileKML(String place ) {
		String line = "";
		String cvsSplitBy = ",";
		GisLayer layer = new GisLayer(place);

		try{
			BufferedReader br = new BufferedReader(new FileReader(place));
			br.readLine();
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] userInfo = line.split(cvsSplitBy);
				GisToElement newGis = new GisToElement(userInfo);
				layer.add(newGis);

			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();

		}
		return layer;
	}
	public static GisProject fileProject(String name) throws IOException {
		File Doc = new File(name);
		File [] CsvAll = Doc.listFiles();
		GisProject Pro = new GisProject(name);
		for (int i = 0; i < CsvAll.length; i++) {
			String nameOfFile = CsvAll[i].getAbsolutePath();
			if((nameOfFile.substring(nameOfFile.length()-3, nameOfFile.length()).equals("csv"))) {
				GisLayer lay =  writeFileKML(nameOfFile);
				Layer2Kml(lay,name);
				Pro.add(lay);
			}
		}

		return Pro;
	}
	public static void multicsv(String name) {
		File[] files= Directory.li
	}
	public static void Layer2Kml(GisLayer layer,String output) throws IOException {

		StringBuilder sB = new StringBuilder();
		Iterator<GIS_element> iter = layer.iterator();
		String kmlstart =( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
			"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>");
		sB.append(kmlstart);

		while(iter.hasNext()) {
			GIS_element ele = iter.next();


			sB.append("<Placemark>\n");
			sB.append("<name><![CDATA["+ele.toString()+"]]></name>\n");
			sB.append("<description>");
			sB.append(ele.getData().toString());
			sB.append("</description>");
			sB.append("<Point>\n");
			sB.append("<coordinates>");
			sB.append(ele.getGeom().toString());
			sB.append("</coordinates>");
			sB.append("</Point>\n");
			sB.append("</Placemark>\n");


		}
		sB.append("</Folder>\n");
		sB.append( "</Document>\n");
		sB.append("</kml>");
		PrintWriter pw = null;
		String fileName = layer.getNamePath().substring(0, layer.getNamePath().length()-3)+"kml";
		try {
			pw = new PrintWriter(new FileWriter(fileName));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}
		pw.write(sB.toString());
		pw.close();




		System.out.println("done");


	}

	public static void main(String[] argh) throws IOException {

		GisProject PRO =fileProject("C:\\Users\\Roi Abramovitch\\eclipse-workspace\\OOP_EX02-EX04\\CSV");
	}


}