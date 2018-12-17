package algoritem;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.GisLayer;


public class Path extends MyCoords {



	public Path() {
		// TODO Auto-generated constructor stub
	}
	public Path(Packman p) {
		int[] loctionX = {};
		int[] loctionY = {};
		int i = 1;
		loctionX[0] = (int) p.pointer_packmen.x();
		loctionY[0] = (int) p.pointer_packmen.y();
		Iterator<Fruit> f = p.getFruit().iterator();
		while(f.hasNext()) {
			Fruit d = f.next();
			p.getGep().translate(d.getPointer_fruit());
			loctionX[i] =(int) d.pointer_fruit.x();
			loctionY[i] = (int) d.pointer_fruit.y();
			i++;
		}
	}
	public static void main(String[] args ) {

	}

}
