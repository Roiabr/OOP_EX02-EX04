package Game;




import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import File_format.MultiCsv;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisLayer;
import GUI.MainWindow;
import Geom.Point3D;
import algoritem.ShortestPathAlg;

/**
 * This class represents a the Game.
 * @author Roi Abramovitch && Gal haddida
 */
public class Game extends MultiCsv {

	private ArrayList<Packman> pack= new ArrayList<Packman>();
	private ArrayList<Fruit> Fruit= new ArrayList<Fruit>();

	/**
	 * a constructor for the class and make a the game(layer) from csv file
	 * @param lay - a leyer from a csv file
	 */
	public Game(GIS_layer lay) 
	{
		Iterator<GIS_element> g = lay.iterator();
		while(g.hasNext()) {
			GIS_element ele = g.next();

			if(ele.getData().getType().equals("P")) {
				Packman p = new Packman(ele);
				pack.add(p);
			}
			else if(ele.getData().getType().equals("F"))
			{
				Fruit f = new Fruit(ele);
				Fruit.add(f);
			}
		}
	}
	public Game() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * the method active the game and run it
	 */
	public void runGame()
	{
		ShortestPathAlg algo = new ShortestPathAlg();
		algo.ShortestPathAlgToPath(this);
	
	}

	///////////////////// GETTER&SETTER //////////////////////////////
	
	/**
	 * the method get the array List with the packmans
	 * @return pack - the list
	 */
	public  ArrayList<Packman> getPack() {
		return pack;
	}

	/**
	 * the method get the array List with the fruits
	 * @return Fruit - the list
	 */
	public  ArrayList<Fruit> getFruit() {
		return Fruit;
	}

	////////////////////////test///////////////////////
	public static void main (String[] args ) {

		MainWindow h = new MainWindow();
		h.setVisible(true);
		h.setSize(h.myImage.getWidth(),h.myImage.getHeight());
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.setTitle("MAROCO VasdS TUNIS");
	}



}
