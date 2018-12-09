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
	GIS_element gep;
	Point3D pointer_packmen;
	String speed,radiuos,type;
	




	public Packman() {
		// TODO Auto-generated constructor stub
	}
	public Packman(GIS_element gep) {
		String [] ele= ((GisToElement) gep).getGis();
		this.type= ele[0];
		this.IDpack=ele[1];
		this.pointer_packmen =gep.getGeom().getNewpoint();
		this.speed=ele[5];
		this.radiuos=ele[6];

	}
	public int size() {
		int count=0;
		while(this.Fruit.iterator().hasNext()) {
			count++;
		}
		return count;
	}
	
	public void ToEat(Fruit g) {
		this.Fruit.add(g);
	}
	
	public static void main(String []args) {

		//Packman g = new Packman(s);
		//System.out.println(g.speed.toString());
	}
/////////////////////////////////////////////////////
	public Point3D getPointer_packmen() {
		return pointer_packmen;
	}
	public ArrayList<Fruit> getFruit() {
		return Fruit;
	}
	public void setFruit(ArrayList<Fruit> fruit) {
		Fruit = fruit;
	}
	public String getIDpack() {
		return IDpack;
	}
	public void setIDpack(String iDpack) {
		IDpack = iDpack;
	}
	public GIS_element getGep() {
		return gep;
	}
	public void setGep(GIS_element gep) {
		this.gep = gep;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getRadiuos() {
		return radiuos;
	}
	public void setRadiuos(String radiuos) {
		this.radiuos = radiuos;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPointer_packmen(Point3D pointer_packmen) {
		this.pointer_packmen = pointer_packmen;
	}
	
/////////////////////////////////////////////////////
}
