package algoritem;

import java.util.ArrayList;
import java.util.Iterator;

import File_format.MultiCsv;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisLayer;
import GIS.GisToElement;
import Geom.Point3D;

public class Game extends MultiCsv {
	int counter;
	ArrayList<Packman> pack= new ArrayList<Packman>();
	ArrayList<Fruit> Fruit= new ArrayList<Fruit>();
	
	


	public int getCounter() {
		return counter;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	public ArrayList<Packman> getPack() {
		return pack;
	}


	public void setPack(ArrayList<Packman> pack) {
		this.pack = pack;
	}


	public ArrayList<Fruit> getFruit() {
		return Fruit;
	}


	public void setFruit(ArrayList<Fruit> fruit) {
		Fruit = fruit;
	}


	public Game() {
		// TODO Auto-generated constructor stub
	}
	public void packmenadd(Packman p) {
		
		p = new Packman();

		pack.add(p);
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
			else 
				System.out.println("warnning");
		}
	}
	
	public static void main (String[] args ) {
		
		
	}

}
