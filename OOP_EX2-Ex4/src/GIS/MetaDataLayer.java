package GIS;

import Geom.Point3D;

public class MetaDataLayer implements Meta_data {
	String NameLayer;
	
	public MetaDataLayer(String name) {
		// TODO Auto-generated constructor stub
		this.NameLayer = name;
	}
	@Override
	public String toString() {
		return "MetaDataLayer [NameLayer=" + NameLayer + "]";
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
