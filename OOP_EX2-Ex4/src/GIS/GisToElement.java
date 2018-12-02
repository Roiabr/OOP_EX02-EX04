package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
import Geom.geomElement;

public class GisToElement implements GIS_element {
	String[] Gis;
	geomElement Point;
	MetaDataElement data;
	String name;

	public GisToElement(String[] a) {
		Gis = a;
		data = new MetaDataElement(Gis);
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

	public String toString() {
		return name;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
		MyCoords c1 = new MyCoords();
		Point3D p1 = this.getGeom().getNewpoint();
		Point3D p2 = c1.add(p1, vec);
		this.getGeom().setNewpoint(p2); 

	}	
	
}
