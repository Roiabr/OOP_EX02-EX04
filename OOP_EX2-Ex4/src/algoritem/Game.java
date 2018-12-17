package algoritem;




import java.util.ArrayList;
import java.util.Iterator;

import File_format.MultiCsv;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisLayer;


public class Game extends MultiCsv {
	static int counter;
	ArrayList<Packman> pack= new ArrayList<Packman>();
	ArrayList<Fruit> Fruit= new ArrayList<Fruit>();
	ShortestPathAlg algo;

	public Game() {
		// TODO Auto-generated constructor stub
	}

	public Game(GIS_layer lay) {

		Packman p = new Packman();
		Fruit f = new Fruit();
		Iterator<GIS_element> g = lay.iterator();
		while(g.hasNext()) {
			GIS_element ele = g.next();

			if(ele.getData().getType().equals("P")) {
				p = new Packman(ele);
				pack.add(p);
			}
			else if(ele.getData().getType().equals("F"))
			{
				f = new Fruit(ele);
				Fruit.add(f);
			}
			else 
				System.out.println("warning");
		}


	}
	public  void runGame(GisLayer lay) {
		Game ga = new Game(lay);
		//algo = new ShortestPathAlg(ga);
		ShortestPathAlg g = new ShortestPathAlg();


	}

	public static int getCounter() {
		return counter;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	public  ArrayList<Packman> getPack() {
		return pack;
	}
	public void setPack(ArrayList<Packman> pack) {
		this.pack = pack;
	}
	public  ArrayList<Fruit> getFruit() {
		return Fruit;
	}

	public void setFruit(ArrayList<Fruit> fruit) {
		Fruit = fruit;
	}

	public static void main (String[] args ) {
		GisLayer layer =  MultiCsv.Csv2Layer("C:\\Users\\Roi Abramovitch\\Documents\\לימודים מדעי המחשב\\מדמ''ח שנה ב' סמסטר א\\מונחה עצמים\\מטלות\\מטלה 3\\Ex3\\data\\game_1543684662657.csv");
		Game g = new Game(layer);
		System.out.println(g.getPack().size());
	}

}
