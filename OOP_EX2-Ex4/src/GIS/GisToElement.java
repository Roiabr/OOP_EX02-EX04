package GIS;







import Geom.Geom_element;
import Geom.Point3D;
import Geom.geomElement;

public class GisToElement implements GIS_element {
	String[] Gis;
	geomElement Point;
	MetaData data;
	String name;
	public GisToElement(String[] a) {
		Gis = a;
		data = new MetaData(Gis);
		Point = new geomElement(Gis);
		name = Gis[1];
		
	}
	
	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		
		return Point;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return data;
	}

	public String getName() {
		return name;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}

}
