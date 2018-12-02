package GIS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Geom.Point3D;

public class MetaDataProject implements Meta_data {
	String NameProject;
	String time;
	
	public MetaDataProject(String name) {
		// TODO Auto-generated constructor stub
	
		String[] slas = name.split("\\\\");
		this.NameProject = slas[slas.length -1];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		this.time = dtf.format(now);
	}
	@Override
	public String toString() {
		return "MetaDataLayer: [NameLayer:" + NameProject +"\n" + "time:" + time + "]";
	}

	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
}
