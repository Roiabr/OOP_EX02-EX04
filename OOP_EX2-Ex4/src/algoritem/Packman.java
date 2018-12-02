package algoritem;

import java.util.ArrayList;

import File_format.MultiCsv;
import GIS.GIS_element;
import GIS.GisToElement;
import Geom.Point3D;
import javafx.scene.paint.Color;

public class Packman {

	ArrayList<Fruit> Fruit= new ArrayList<Fruit>();
	String IDpack;
	GisToElement gep;
	Point3D pointer_fruit;
	String speed,radiuos,type;




	public Packman() {
		// TODO Auto-generated constructor stub
	}
	public Packman(GisToElement gep) {
		String [] ele= gep.getGis();
		this.type= ele[0];
		this.IDpack=ele[1];
		this.pointer_fruit=gep.getGeom().getNewpoint();
		this.speed=ele[5];
		this.radiuos=ele[6];

	}
	public static void main(String []args) {

		//Packman g = new Packman(s);
		//System.out.println(g.speed.toString());
	}

}
