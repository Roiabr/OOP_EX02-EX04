package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisLayer;
import GIS.GisProject;
import GIS.GisToElement;


public class CSVReader {
	//	File g;

	public static GisLayer Csv2Layer(String place) {
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


	public static void main(String[] args) throws IOException {
		File currentDir = new File("C:\\Users\\Roi Abramovitch\\eclipse-workspace\\OOP_EX02-EX04 - Copy\\data"); // current directory
		project2kml(currentDir);
	}
	public static void project2kml(File dir) throws IOException {
		GisProject pro = new GisProject(dir.getPath());

		displayDirectoryContents( dir, pro);
		Project2Kml(pro, dir.getPath());
		System.out.println(pro.get_Meta_data());


	}



	public static void displayDirectoryContents(File dir, GisProject PRO) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file,PRO);
				} else if(file.getName().contains("csv")) {
					GIS_layer newLayer = Csv2Layer(file.getAbsoluteFile()+"");
					PRO.add(newLayer);
					System.out.println("convert file to Layer");
				}
				else {
					System.out.println("file:" + file.getCanonicalPath());

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	public static void Project2Kml(GisProject project,String output) throws IOException {

		StringBuilder sB = new StringBuilder();

		String kmlstart =( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>");
		sB.append(kmlstart);

		Iterator<GIS_layer> projIter = project.iterator();

		while(projIter.hasNext())
		{
			GIS_layer layer = projIter.next();

			Iterator<GIS_element> iter = layer.iterator();


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
		}
		sB.append("</Folder>\n");
		sB.append( "</Document>\n");
		sB.append("</kml>");
		PrintWriter pw = null;
		String fileName = output + "ProjectKml" + ".kml";
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


}