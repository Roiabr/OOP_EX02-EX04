package Game;

import java.awt.image.BufferedImage;

import GIS.GIS_element;
import GIS.GisToElement;
import Geom.Point3D;
/**
 * This class represents a Ghost in the game.
 * @author Roi Abramovitch & Gal Hadida
 */
public class Ghost {
	
	private int IDGhost;
	private BufferedImage image;
	private String [] ele;
	private Point3D point_Ghost,firstPointCor,first;
	private double speed,radiuos;
	private String type;
	private double time;
	private String timeStamp;
	int check;
	
	/**
	 * a default constructor for the class
	 */
	public Ghost() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * a constructor for the class and make a packman from pacman
	 * @param gh - the diffrent ghost
	 */
	public Ghost(Ghost gh) {

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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getRadiuos() {
		return radiuos;
	}
	public void setRadiuos(double radiuos) {
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
	public void setIDGhost(int iDGhost) {
		IDGhost = iDGhost;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
