package algoritem;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisLayer;
import GIS.GisToElement;
import Geom.Point3D;

public class Game {
	int counter;
	ArrayList<Packman> pack= new ArrayList<Packman>();
	ArrayList<Fruit> Fruit= new ArrayList<Fruit>();

	
	public Game() {
		// TODO Auto-generated constructor stub
	}
	public Game(GIS_layer gl) {
		Packman p = new Packman();
		Fruit f = new Fruit();

		while(gl.iterator().hasNext()) {
			GIS_element ele = gl.iterator().next();
			if(ele.toString().equals("p")) {
				p = new Packman(ele);
				pack.add(p);
			}
			else if(ele.toString().equals("f"))
			{
				f = new Fruit(ele);
				Fruit.add(f);
			}
		}
		
		
	}

}
