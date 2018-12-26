package Game;

import GIS.GIS_element;
import GIS.GisToElement;
import Geom.Point3D;
/**
 * This class represents a Ghost in the game.
 * @author Roi Abramovitch & Gal Hadida
 */
public class Ghost {
	
	private int IDGhost;
	private String [] ele;
	private Point3D point_Ghost,firstPointCor,first;
	private int speed,radiuos;
	private String type;
	private double time;
	private String timeStamp;
	
	/**
	 * a default constructor for the class
	 */
	public Ghost() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * a constructor for the class and make a Ghost from csv file
	 * @param element - the element from csv file
	 */
	public Ghost(GIS_element element) {
		this.ele= ((GisToElement) element).getGis();
		this.type= ele[0];
		this.IDGhost=Integer.parseInt(ele[1]);
		this.point_Ghost = element.getGeom().getNewpoint();
		this.firstPointCor =  element.getGeom().getNewpoint();
		this.speed = Integer.parseInt(ele[5]);
		this.radiuos = Integer.parseInt(ele[6]);
		this.time = 0;
	}
	/**
	 * a constructor for the class and make a packman from pacman
	 * @param gh - the diffrent ghost
	 */
	public Ghost(Ghost gh) {
		// TODO Auto-generated constructor stub

		this.type= gh.type;
		this.IDGhost=gh.IDGhost;
		this.point_Ghost = new Point3D(gh.point_Ghost);
		this.firstPointCor = new Point3D(gh.point_Ghost);
		this.speed = gh.speed;
		this.radiuos = gh.radiuos;
		this.time = 0;
	}
	public Point3D getPoint_Ghost() {
		return point_Ghost;
	}
	public void setPoint_Ghost(Point3D point_Ghost) {
		this.point_Ghost = point_Ghost;
	}
	public Point3D getFirst() {
		return first;
	}
	public void setFirst(Point3D first) {
		this.first = first;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getIDGhost() {
		return IDGhost;
	}
	public Point3D getFirstPointCor() {
		return firstPointCor;
	}
}
