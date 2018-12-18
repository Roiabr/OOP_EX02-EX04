package algoritem;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.GisLayer;
import GUI.MainWindow;
import Geom.Point3D;


public class Path extends MyCoords {
		
	ArrayList<Point3D> eat = new ArrayList<Point3D>();
	MainWindow window = new MainWindow();
	
	public Path() {
		// TODO Auto-generated constructor stub
	}
	public Path(Packman p) {
		this.startPath(p);
		Iterator<Fruit> f = p.getFruit().iterator();
		while(f.hasNext()) {
			Fruit d = f.next();
			Point3D g = new Point3D(Map.ConvertorToScreen(d.getPointer_fruit().x(),d.getPointer_fruit().y(),window.getHeight(),window.getWidth()));
			//p.getGep().translate(p);
			eat.add(g);
		}
		
	
	}
	public void startPath(Packman p) {
		Point3D g = new Point3D(Map.ConvertorToScreen(p.getPointer_packmen().x(),p.getPointer_packmen().y(),window.getHeight(),window.getWidth()));
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
