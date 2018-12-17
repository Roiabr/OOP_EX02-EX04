package algoritem;

import java.util.ArrayList;
import GIS.GIS_element;
import GIS.GisToElement;
import Geom.Point3D;


public class Packman {

	ArrayList<Fruit> fruiteat= new ArrayList<Fruit>();
	int IDpack;
	GIS_element gep;
	Point3D pointer_packmen;
	int speed,radiuos;
	String type;
	double time;
	
	

	public Packman() {
		// TODO Auto-generated constructor stub
	}
	public Packman(GIS_element gep) {
		String [] ele= ((GisToElement) gep).getGis();
		this.type= ele[0];
		this.IDpack=Integer.parseInt(ele[1]);
		this.pointer_packmen = gep.getGeom().getNewpoint();
		this.speed = Integer.parseInt(ele[5]);
		this.radiuos = Integer.parseInt(ele[6]);
		time = 0;

	}
	public int size() {
		int count=0;
		while(this.fruiteat.iterator().hasNext()) {
			count++;
		}
		return count;
	}
	
	public void ToEat(Fruit g) {
		
		this.fruiteat.add(g);
	}
	
	public int getIDpack() {
		return IDpack;
	}
	public void setIDpack(int iDpack) {
		IDpack = iDpack;
	}

/////////////////////////////////////////////////////
	public Point3D getPointer_packmen() {
		return pointer_packmen;
	}
	public ArrayList<Fruit> getFruit() {
		return fruiteat;
	}
	public void setFruit(ArrayList<Fruit> fruit) {
		fruiteat = fruit;
	}

	public GIS_element getGep() {
		return gep;
	}
	public void setGep(GIS_element gep) {
		this.gep = gep;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getRadiuos() {
		return radiuos;
	}
	public void setRadiuos(int radiuos) {
		this.radiuos = radiuos;
	}
	
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public ArrayList<Fruit> getFruiteat() {
		return fruiteat;
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
	public static void main(String []args) {

		//Packman g = new Packman(s);
		//System.out.println(g.speed.toString());
	}
/////////////////////////////////////////////////////
}
