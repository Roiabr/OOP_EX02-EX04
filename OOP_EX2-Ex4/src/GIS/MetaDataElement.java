package GIS;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;



import Geom.Point3D;

public class MetaDataElement implements Meta_data {
	String [] CSVfile;
	
	
	/**
	 * Constructor 
	 * @param s - the whole line that holds the information
	 */
	public MetaDataElement(String [] s) {
		CSVfile = s;
	
	}
	/**
	 * this is a method that get a data and change it to a timestamp
	 * @return ts = the timestamp
	 */
	@Override
	public long getUTC() {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(CSVfile[3]);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Timestamp GA= new Timestamp(d.getTime());
		long ts =(long)GA.getTime();
		return ts;
	}

	/**
	 * the method print all the metadata of the element 
	 */
	@Override
	public String toString() {
		MetaDataElement mat = new MetaDataElement(CSVfile);
		return 
				"<b>BSSID: </b>"+CSVfile[0]+"<br></br>"+
				"<b>Capabilities:</b>"+CSVfile[2]+"<br></br>"+"\n"+
				"<b>Frequency: </b>"+CSVfile[4]+"<br></br>"+"\n"+
				"<b>RSSI: </b>"+CSVfile[5]+"<br></br>"+"\n"+
				"<b>Type:</b> " +CSVfile[10]+ "<br></br>" +"\n"+
				"<b>date: </b>" + CSVfile[3]+ "<br></br>"+
				"<b>timeStamp:</b>" + mat.getUTC();	
				
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stu

		return null;
	}

}
