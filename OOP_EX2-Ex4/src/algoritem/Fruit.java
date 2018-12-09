package algoritem;

import java.util.ArrayList;

import GIS.GIS_element;
import GIS.GisToElement;
import Geom.Point3D;
import javafx.scene.paint.Color;

public class Fruit {
	boolean life=true;
	
	Point3D pointer_fruit;
	String type,idfruit,we;
	Color g;
	GIS_element gep;
	long timeStamp;

	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Fruit() {
		// TODO Auto-generated constructor stub
	}
	public Fruit(GIS_element gep) {
		String [] ele= ((GisToElement) gep).getGis();
		this.type= ele[0];
		this.idfruit=ele[1];
		this.pointer_fruit=gep.getGeom().getNewpoint();
		this.we=ele[5];
		this.timeStamp = 0;

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
/////////////////////////////////////////////////////
	public boolean isLife() {
		return life;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	
	
	public Point3D getPointer_fruit() {
		return pointer_fruit;
	}
	public void setPointer_fruit(Point3D pointer_fruit) {
		this.pointer_fruit = pointer_fruit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdfruit() {
		return idfruit;
	}
	public void setIdfruit(String idfruit) {
		this.idfruit = idfruit;
	}
	public String getWe() {
		return we;
	}
	public void setWe(String we) {
		this.we = we;
	}
	public Color getG() {
		return g;
	}
	public void setG(Color g) {
		this.g = g;
	}
	public GIS_element getGep() {
		return gep;
	}
	public void setGep(GIS_element gep) {
		this.gep = gep;
	}

/////////////////////////////////////////////////////


}
