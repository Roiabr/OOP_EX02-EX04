package algoritem;

import java.util.ArrayList;

import GIS.GisToElement;
import Geom.Point3D;
import javafx.scene.paint.Color;

public class Fruit {
	boolean life=true;
	Packman whoEat;
	Point3D pointer_fruit;
	String type,idfruit,we;
	Color g;

	public Fruit() {
		// TODO Auto-generated constructor stub
	}
	public Fruit(GisToElement gep) {
		String [] ele= gep.getGis();
		this.type= ele[0];
		this.idfruit=ele[1];
		this.pointer_fruit=gep.getGeom().getNewpoint();
		this.we=ele[5];

	}
	public Fruit(Point3D pf) {
		this.pointer_fruit=pf;
		//id=id++;
	}
	public boolean Alive_Fruit(Fruit f) {
		if(life==false) {
			return false;
		}
		else {
			return true;
		}
	}

}
