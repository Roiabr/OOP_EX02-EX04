package Game;




import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import File_format.MultiCsv;
import GIS.GIS_element;
import GIS.GIS_layer;
import GUI.MainWindow;
import algoritem.ShortestPathAlg;

/**
 * This class represents a Game.
 * @author Roi Abramovitch && Gal Hadida
 */
public class Game extends MultiCsv {

	private ArrayList<Packman> pack= new ArrayList<Packman>();
	private ArrayList<Fruit> Fruit= new ArrayList<Fruit>();
	private ArrayList<Ghost> ghost= new ArrayList<Ghost>();
	private ArrayList<Block> block= new ArrayList<Block>();


	/**
	 * a default constructor for the class
	 */
	public Game() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * a constructor for the class and make a the game(layer) from csv file
	 * @param lay - a leyer from a csv file
	 */
	public Game(GIS_layer lay) 
	{
		Iterator<GIS_element> gis = lay.iterator();
		while(gis.hasNext()) {
			GIS_element ele = gis.next();

			if(ele.getData().getType().equals("P")) {
				Packman p = new Packman(ele);
				pack.add(p);
			}
			else if(ele.getData().getType().equals("F"))
			{
				Fruit f = new Fruit(ele);
				Fruit.add(f);
			}
			else if(ele.getData().getType().equals("G")) {
				Ghost g = new Ghost(ele);
				ghost.add(g);
			}
			else {
				Block bl = new Block(ele);
				block.add(bl);
			}
		}
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
	/**
	 * the method get the array List with the Ghost
	 * @return Ghost - the list
	 */
	public ArrayList<Ghost> getGhost() {
		return ghost;
	}


	////////////////////////test///////////////////////
	public static void main (String[] args ) {

		MainWindow h = new MainWindow();
		h.setVisible(true);
		h.setSize(h.getMyImage().getWidth(),h.getMyImage().getHeight());
		h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		h.setTitle("Packmans in ariel");
	}
}
