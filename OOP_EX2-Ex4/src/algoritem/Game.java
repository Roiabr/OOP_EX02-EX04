package algoritem;




import java.util.ArrayList;
import File_format.MultiCsv;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisLayer;


public class Game extends MultiCsv {
	int counter;
	ArrayList<Packman> pack= new ArrayList<Packman>();
	ArrayList<Fruit> Fruit= new ArrayList<Fruit>();

	public Game() {
		// TODO Auto-generated constructor stub
	}

	public Game(GIS_layer lay) {

		Packman p = new Packman();
		Fruit f = new Fruit();
		while(lay.iterator().hasNext()) {
			GIS_element ele = lay.iterator().next();
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





	public static void main (String[] args ) {

	}

}
