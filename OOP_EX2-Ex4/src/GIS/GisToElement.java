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
	
	/**
	 * a constractur that get an array of string for one element
	 * @param a - an array of string
	 */
	public GisToElement(String[] a) {
		Gis = a;
		data = new MetaDataElement(Gis);
		Point = new geomElement(Gis);
		name = Gis[1];

	}
	
	/**
	 * the method return the geomtry things of the element
	 */
	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub

		return Point;
	}
	
	/**
	 * the method return the metadata things of the element
	 */
	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return data;
	}

	public String toString() {
		return name;
	}
	
	/**
	 * the method get a point and change his place by the vector method(mycoords)
	 * @param vec - the vector to move the point
	 */
	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub
		MyCoords c1 = new MyCoords();
		Point3D p1 = this.getGeom().getNewpoint();
		Point3D p2 = c1.add(p1, vec);
		this.getGeom().setNewpoint(p2); 

	}	
	
}
