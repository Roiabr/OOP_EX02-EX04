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
	private double speed,radiuos;
	private String type;
	private double time;
	private String timeStamp;

	/**
	 * a default constructor for the class
	 */
	public Packman() {
		// TODO Auto-generated constructor stub
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
//		this.speed = Integer.parseInt(ele[5]);
//		this.radiuos = Integer.parseInt(ele[6]);
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
	public double getSpeed() {
		return speed;
	}
	/**
	 * the method set the speed of a packman
	 * @param speed - the speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * the method get the Radius of a packman
	 * @param radiuos - the Radius
	 */
	public double getRadiuos() {
		return radiuos;
	}
	/**
	 * the method set the Radius of a packman
	 */
	public void setRadiuos(double radiuos) {
		this.radiuos = radiuos;
	}
	/**
	 * the method get time in double 
	 * @return time - the time
	 */
	public double getTime() {
		return time;
	}
	/**
	 * the method set time in double 
	 * @param time - a time in double
	 */
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

	/**
	 * the method set the tempory point for the calculete
	 * @param firstPointCor - the point in 3d
	 */
	public void setFirstPointCor(Point3D firstPointCor) {
		this.firstPointCor = firstPointCor;
	}
	/**
	 * the method get the tempory point for the calculete
	 * @return firstPointCor - a point 3d
	 */
	public Point3D getFirstPointCor() {
		return firstPointCor;

	}
	/**
	 * the method return an array list with the fruits the packman ate
	 * @return fruiteat - the array list
	 */
	public ArrayList<Fruit> getFruiteat() {
		return fruiteat;
	}
	/**
	 * the method get the first coordinates of packman in the bigining of the game
	 * @return first - the first coordinates of packman
	 */
	public Point3D getFirst() {
		return first;
	}
	/**
	 * the method set the first coordinates of packman in the bigining of the game
	 * @param first - a point 3d
	 */
	public void setFirst(Point3D first) {
		this.first = first;
	}
	/**
	 * the method get the time stamp of a packman
	 * @return timeStamp - the time stamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	/**
	 * the method set the time stamp of a packman
	 * @param timeStamp - a time stamp in string
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
}
