package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisLayer;
import GIS.GisProject;
import GIS.GisToElement;
import Geom.Point3D;
import algoritem.Fruit;
import algoritem.Game;
import algoritem.Packman;



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
	/**
	 * the method get a game and make from him a csv file
	 * @param game - the game we played
	 * @param fileName - the name for the game
	 * @return pw - a print Writer
	 */
	public static PrintWriter Game2csv(Game game,String fileName) {

		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();

		}
		StringBuilder sb = new StringBuilder();
		Iterator <Packman> iter = game.getPack().iterator();
		Iterator <Fruit> iter2 = game.getFruit().iterator();
		sb.append("Type");
		sb.append(',');
		sb.append("id");
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
			Packman p1 = iter.next();
			sb.append(p1.getType());
			sb.append(',');
			sb.append(p1.getIDpack());
			sb.append(',');
			sb.append(p1.getPointer_packmen().x());
			sb.append(',');
			sb.append(p1.getPointer_packmen().y());
			sb.append(',');
			sb.append(p1.getPointer_packmen().z());
			sb.append(',');
			sb.append(p1.getSpeed());
			sb.append(',');
			sb.append(p1.getRadiuos());
			sb.append('\n');
		}

		while(iter2.hasNext()) {
			Fruit f1 = iter2.next();
			sb.append(f1.getType());
			sb.append(',');
			sb.append(f1.getIdfruit());
			sb.append(',');
			sb.append(f1.getPointer_fruit().x());
			sb.append(',');
			sb.append(f1.getPointer_fruit().y());
			sb.append(',');
			sb.append(f1.getPointer_fruit().z());
			sb.append(',');
			sb.append(f1.getSpeed());
			sb.append('\n');
		}
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
		return pw;

	}

	/**
	 * the method turning a game to kml file
	 * @param game - the game 
	 * @throws IOException
	 */
	public static void Game2Kml(Game game) throws IOException
	{

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+
				"<Document>\n"+"<Style id=\"yellow\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/ylw-stars.png</href>" 
				+"</Icon></IconStyle></Style>" + "\n"
				+ "<Style id=\"red\"><IconStyle><Icon><href>" 
				+"http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>"
				+ "</Icon></IconStyle></Style>" + "\n"
				+"<Style id=\"pac\"><IconStyle><Icon><href>"
				+" C:\\Users\\Roi Abramovitch\\eclipse-workspace\\OOP_EX02-EX04---Copy\\packman.png "+"</href>"
				+"</Icon></IconStyle></Style>");


		Iterator<Fruit>  fruit = game.getFruit().iterator();
		while(fruit.hasNext())
		{

			Fruit fru = new Fruit(fruit.next());
			sb.append("<Placemark>\n"
					+"<name>" + fru.getType() +"</name>\n"
					+"<description>" + "ID: " + fru.getIdfruit() +"\n"+
					fru.getSpeed() + "\n"+
					"</description>"
					+"<styleUrl>#red</styleUrl>\n"+"<Point>\n"+
					"<coordinates>"+fru.getPointer_fruit().y()+","+fru.getPointer_fruit().x()+"</coordinates></Point>\n"+
					"</Placemark>\n");	
		}	

		sb.append("<Folder>");
		Iterator <Packman> packman = game.getPack().iterator();
		while(packman.hasNext()) {
			Packman p1 = packman.next();
			Iterator <Point3D> Path = p1.getPathOfPacman().iterator();
			Point3D pointPath1 = Path.next();
			sb.append("<Placemark>"+"<styleUrl>#pac</styleUrl>\n"+"<TimeStamp><when>"+Time2Kml(pointPath1.getTime())+"</when></TimeStamp>"
					+
					"<Point><coordinates>"+p1.getFirstPointCor().y()+","+p1.getFirstPointCor().x()+"</coordinates></Point>"+
					" </Placemark>");


			while(Path.hasNext())
			{
				Point3D pointPath = Path.next();
				sb.append("<Placemark>"+"<styleUrl>#pac</styleUrl>\n"+"<TimeStamp><when>"+Time2Kml(pointPath.getTime())+"</when></TimeStamp>"
						+
						"<Point><coordinates>"+pointPath.y()+","+pointPath.x()+"</coordinates></Point>"+
						" </Placemark>");
				System.out.println(pointPath.getTime());
				System.out.println(Time2Kml(pointPath.getTime()));
			}
		}

		sb.append("</Folder></Document></kml>");
		PrintWriter pw = null;
		String fileName ="GameKml.kml";
		try {
			pw = new PrintWriter(new FileWriter(fileName));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}
		pw.write(sb.toString());
		pw.close();
		System.out.println("done");
	}

	/**
	 * the method get a layer and make from him a csv file
	 * @param layer - the layer
	 * @param output - the palce we write the csv file
	 */
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

		}
		StringBuilder sb = new StringBuilder();
		Iterator <GIS_element> iter = layer.iterator();
		sb.append("Type");
		sb.append(',');
		sb.append("id");
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
			if(ele.getData().getType().equals("P")){
				sb.append(ele.toString());
				sb.append(',');
				sb.append(ele.getData().getId());
				sb.append(',');
				sb.append(ele.getGeom().getNewpoint().x());
				sb.append(',');
				sb.append(ele.getGeom().getNewpoint().y());
				sb.append(',');
				sb.append(ele.getGeom().getNewpoint().z());
				sb.append(',');
				sb.append(ele.getData().getSpeed_Weight());
				sb.append(',');
				sb.append(ele.getData().getRadius());
				sb.append('\n');
			}
			else {
				sb.append(ele.toString());
				sb.append(',');
				sb.append(ele.getData().getId());
				sb.append(',');
				sb.append(ele.getGeom().getNewpoint().x());
				sb.append(',');
				sb.append(ele.getGeom().getNewpoint().y());
				sb.append(',');
				sb.append(ele.getGeom().getNewpoint().z());
				sb.append(',');
				sb.append(ele.getData().getSpeed_Weight());
				sb.append('\n');
			}
		}
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");

	}
	/**
	 * the method get a string of time and change it to timestamp kml format
	 * @param time - the time
	 * @return time - the timestamp kml format
	 */
	public static String Time2Kml(String time) {
		time = time.substring(0, 10) + "T" + time.substring(11,19) + "Z";
		return time;
	}

}