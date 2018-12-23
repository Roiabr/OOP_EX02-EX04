package Game;


import java.util.ArrayList;


import GIS.GIS_element;
import GIS.GisToElement;
import Geom.Point3D;

/**
 *  This class represents a Fruit in the game.
 * @author Roi Abramovitch & Gal Hadida
 */
public class Packman {

	private ArrayList<Fruit> fruiteat= new ArrayList<Fruit>();
	private ArrayList<Point3D> pathOfPacman = new ArrayList<Point3D>();
	private int IDpack;
	private String [] ele;
	private Point3D pointer_packmen,firstPointCor,first;
	private int speed,radiuos;
	private String type;
	private double time;
	private String timeStamp;
	
	public Packman() {
		
	}
	
	/**
	 * a constructor for the class and make a packman from csv file
	 * @param element - the element from csv file
	 */
	public Packman(GIS_element element) {
		this.ele= ((GisToElement) element).getGis();
		this.type= ele[0];
		this.IDpack=Integer.parseInt(ele[1]);
		this.pointer_packmen = element.getGeom().getNewpoint();
		firstPointCor =  element.getGeom().getNewpoint();
		this.speed = Integer.parseInt(ele[5]);
		this.radiuos = Integer.parseInt(ele[6]);
		this.time = 0;
	}
	/**
	 * a constructor for the class and make a packman from pacman
	 * @param next - the packman
	 */
	public Packman(Packman next) {
		// TODO Auto-generated constructor stub

		this.type= next.type;
		this.IDpack=next.IDpack;
		this.pointer_packmen = new Point3D(next.pointer_packmen);
		firstPointCor = new Point3D(next.pointer_packmen);
		this.speed = next.speed;
		this.radiuos = next.radiuos;
		this.time = 0;
	}
	

	

	///////////////////// GETTER&SETTER //////////////////////////////
	/**
	 * the method get the id of the packman
	 * @return IDpack - the id
	 */
	public int getIDpack() {
		return IDpack;
	}
	/**
	 * the method set the id of the packman
	 */
	public void setIDpack(int iDpack) {
		IDpack = iDpack;
	}
	/**
	 * the method get the list Points that the packman ate by fruits
	 * @return pathOfPacman - the list
	 */
	public ArrayList<Point3D> getPathOfPacman() {
		return pathOfPacman;
	}
	/**
	 * the method get the coordinates of the packman
	 * @return pointer_packmen - the coordinates of the packman
	 */
	public Point3D getPointer_packmen() {
		return pointer_packmen;
	}
	/**
	 * the method set the coordinates of the packman
	 * @param pointer_packmen - the new coordinates
	 */
	public void setPointer_packmen(Point3D pointer_packmen) {
		this.pointer_packmen = pointer_packmen;
	}
	/**
	 * the method get the speed of a pacman
	 * @return speed 
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * the method set the speed of a packman
	 * @param speed - the speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * the method get the Radius of a packman
	 * @param radiuos - the Radius
	 */
	public int getRadiuos() {
		return radiuos;
	}
	/**
	 * the method set the Radius of a packman
	 */
	public void setRadiuos(int radiuos) {
		this.radiuos = radiuos;
	}
	/**
	 * the method get t
	 * @return time - the time
	 */
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	/**
	 * the method get the name of the object
	 * @return type - the name
	 */
	public String getType() {
		return type;
	}
	/**
	 * the method get the name of the object
	 * @param type - the name
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTime2() {
		return timeStamp;
	}
	public void setTime2(String time2) {
		this.timeStamp = time2;
	}
	
	public void setFirstPointCor(Point3D firstPointCor) {
		this.firstPointCor = firstPointCor;
	}
	public Point3D getFirstPointCor() {
		return firstPointCor;
		
	}
	public ArrayList<Fruit> getFruiteat() {
		return fruiteat;
	}

	public Point3D getFirst() {
		return first;
	}

	public void setFirst(Point3D first) {
		this.first = first;
	}

	
}
