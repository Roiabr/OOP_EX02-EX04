package algoritem;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.GisLayer;
import Geom.Point3D;


public class Path extends MyCoords {
		
	ArrayList<Point3D> eat = new ArrayList<Point3D>();

	
	public Path() {
		// TODO Auto-generated constructor stub
	}
	public Path(Packman p) {
		this.startPath(p);
		Iterator<Fruit> f = p.getFruit().iterator();
		while(f.hasNext()) {
			Fruit d = f.next();
			Point3D G = new Point3D(Map.gpsToPix(d.getPointer_fruit().y(),d.getPointer_fruit().x()));
			//p.getGep().translate(p);
			eat.add(G);
		}
	
	}
	public void startPath(Packman p) {
		Point3D g = new Point3D(Map.gpsToPix(p.getPointer_packmen().y(),p.getPointer_packmen().x()));
		eat.add(g);
	}
	public ArrayList<Point3D> getEat() {
		return eat;
	}
	public void setEat(ArrayList<Point3D> eat) {
		this.eat = eat;
	}
	public static void main(String[] args ) {
		
	}

}
