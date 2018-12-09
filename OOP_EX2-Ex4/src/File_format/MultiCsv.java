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
import GUI.gi;


public class MultiCsv {

	/**
	 * this method get a csv file and formet it to a leyer object
	 * @param place - the path of the csv file
	 * @return layer - the layer object from the csv file
	 */
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


	/**
	 * the method get a project object with couple of layers and convert it to kml
	 * @param dir - the project
	 * @throws IOException - if cant find any folder
	 */
	public static void RecusiveFile(File dir) throws IOException {
		GisProject pro = new GisProject(dir.getPath());
		displayDirectoryContents( dir, pro);
		Project2Kml(pro, dir.getPath());
		System.out.println(pro.get_Meta_data());


	}


	/**
	 * the method run over the folder and find all the layers and add it to project object
	 * @param dir - the folder with files and subFolders
	 * @param PRO - the project object 
	 */
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



	/**
	 * the method get a project with layer and covert it to one kml file
	 * @param project - the project with layers
	 * @param output - the place that will create a new kml file
	 * @throws IOException - cant create the file
	 */
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
	public static void layer2csv(GisLayer layer,String output) {
		String fileName = output + ".csv";
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		StringBuilder sb = new StringBuilder();
		Iterator <GIS_element> iter = layer.iterator();
		sb.append("id");
		sb.append(',');
		sb.append("Name");
		sb.append(',');
		sb.append("lat");
		sb.append(',');
		sb.append("lon");
		sb.append(',');
		sb.append("alt");
		sb.append(',');
		sb.append("Speed/Weight");
		sb.append(',');
		sb.append("Radius");
		sb.append('\n');

		while(iter.hasNext()) {
			GIS_element ele = iter.next();

		
			
			sb.append(ele.toString());
			sb.append(',');
			sb.append(ele.toString());
			sb.append(',');
			sb.append(ele.getGeom().getNewpoint().x());
			sb.append(',');
			sb.append(ele.getGeom().getNewpoint().y());
			sb.append(',');
			sb.append(ele.getGeom().getNewpoint().z());
			sb.append(',');
			sb.append('\n');
		}
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
	}

	public static void main(String[] args) throws IOException {
		//File currentDir = new File("C:\\Users\\Gal\\Desktop\\nykv nubjv\\data\\game_1543684662657"); // current directory
		GisLayer layer = Csv2Layer("C:\\Users\\Gal\\Desktop\\nykv nubjv\\data\\game_1543684662657.csv");
		layer2csv(layer, "C:\\Users\\Gal\\Desktop\\nykv nubjv\\data"); 
	}


}