package Game;


import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import File_format.MultiCsv;
import GIS.GIS_element;
import GIS.GIS_layer;
import GUI.MainWindow;
import Geom.Point3D;
import Robot.Play;
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
	ArrayList<String> GameServer = new ArrayList<String>();

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
	 * this constrctur get a play object and build the game from the database in the server
	 * @param p - a play object
	 */
	public Game(Play p) 
	{
		GameServer = p.getBoard();
		String line[] = {};
		String cvsSplitBy = ",";
		Iterator<String> gis = GameServer.iterator();
		pack.removeAll(pack);
		Fruit.removeAll(Fruit);
		ghost.removeAll(ghost);

		while(gis.hasNext()) {
			String All  = gis.next();
			line = All.split(cvsSplitBy);
			if(line[0].equals("P")) {

				Packman p1 = new Packman();
				p1.setType(line[0]);
				p1.setIDpack(Integer.parseInt(line[1]));
				Point3D point = new Point3D(Double.parseDouble(line[2]),(Double.parseDouble(line[2])), 0.0);
				p1.setFirstPointCor(point);
				p1.setRadiuos(Integer.parseInt(line[6]));
				p1.setSpeed(Integer.parseInt(line[5]));
				pack.add(p1);
			}
			else if(line[0].equals("F"))
			{
				Fruit f1 = new Fruit();
				f1.setType(line[0]);
				f1.setIdfruit(Integer.parseInt(line[1]));
				Point3D point = new Point3D(Double.parseDouble(line[2]),(Double.parseDouble(line[2])), 0.0);
				f1.setPointer_fruit(point);
				f1.setSpeed(Integer.parseInt(line[5]));
				Fruit.add(f1);
			}
			else if(line[0].equals("G")) {
				Ghost g1 = new Ghost();
				g1.setType(line[0]);
				g1.setIDGhost(Integer.parseInt(line[1]));
				Point3D point = new Point3D(Double.parseDouble(line[2]),(Double.parseDouble(line[2])), 0.0);
				g1.setPoint_Ghost(point);
				g1.setSpeed(Integer.parseInt(line[5]));
				g1.setRadiuos(Integer.parseInt(line[6]));
				ghost.add(g1);
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
	/**
	 * the method get the array List with the Blocks in the game
	 * @return block - the list
	 */
	public ArrayList<Block> getBlock() {
		return block;
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
