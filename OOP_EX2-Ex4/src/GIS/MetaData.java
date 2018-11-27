package GIS;



import Geom.Point3D;

public class MetaData implements Meta_data {
	String [] CSVfile;
	

	/**
	 * counstrctor 
	 * @param s - the whole line that holds the infromtion
	 */
	public MetaData(String [] s) {
		CSVfile = s;

	}

	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * print all the metadata of the line 
	 */
	@Override
	public String toString() {
		return "MetaData:" +
				"BSSID: "+CSVfile[0]+"\n"+
				"Capabilities: "+CSVfile[2]+"\n"+
				"Frequency: "+CSVfile[4]+"\n"+
				"RSSI: "+CSVfile[5]+"\n"+
				"Type: " +CSVfile[10];	

	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stu

		return null;
	}

}
