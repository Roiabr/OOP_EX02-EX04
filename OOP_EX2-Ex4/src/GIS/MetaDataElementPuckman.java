package GIS;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;



import Geom.Point3D;

public class MetaDataElementPuckman implements Meta_data {
	String [] CSVfile;
	double id;
	String type;
	
	double Speed_Weight;
	double Radius;
	
	/**
	 * Constructor 
	 * @param s - the whole line that holds the information
	 */
	public MetaDataElementPuckman(String [] s) {
		CSVfile = s;
		this.type = CSVfile[0];
		this.id = Double.parseDouble(CSVfile[1]);
		this.Speed_Weight = Double.parseDouble(CSVfile[5]);
		this.Radius = Double.parseDouble(CSVfile[6]);
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
		
		return 
				this.type+","+
				this.id+","+
				+this.Speed_Weight+","
				+this.Radius+"\n";
				
	}
	@Override
	public double getRadius() {
		return Radius;
	}
	@Override
	public double getId() {
		return id;
	}
	@Override
	public double getSpeed_Weight() {
		return Speed_Weight;
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stu

		return null;
	}

}
