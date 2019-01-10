package Game;


import java.util.ArrayList;

import GUI.MainWindow;
import Geom.Point3D;
import Map.Map;

/**
 * This class represents a Black blocks in the game.
 * @author Roi Abramovitch & Gal Hadida
 *
 */
public class Block {

	private Point3D point_BlockTopRight,point_BlockDownleft,Point_BlockDownRight;
	private Point3D point_BlockTopLeft;
	private int IDBloack;
	private String type;



	/**
	 * a constractur for the  block
	 */
	public Block() {
		// TODO Auto-generated constructor stub
		this.point_BlockTopRight = null;
		this.point_BlockDownleft = null;
		this.setpoint_BlockTopLeft(null);
		this.IDBloack = 0;
		this.type = "";
	}
	/**
	 * a constructor for the class and make a Block anther Block
	 * @param Block - the diffrent block
	 */
	public Block(Block bl) {
		this.type = bl.type;
		this.point_BlockTopRight = new Point3D(bl.point_BlockTopRight);
		this.point_BlockDownleft = new Point3D(bl.point_BlockDownleft);
		this.setpoint_BlockTopLeft(new Point3D(bl.getpoint_BlockTopRight().y(),bl.getpoint_BlockDownleft().x()));
		this.setPoint_BlockDownRight(new Point3D(bl.getpoint_BlockDownleft().y(),bl.getpoint_BlockTopRight().x()));
		this.IDBloack = bl.IDBloack;

	}
	///////////methods/////////////////////////
	/**
	 * the method get a block from the game and the gui and calculte the width of the rectangle
	 * @param bl - the block
	 * @param M - the gui
	 * @return width - the width of the rectangle
	 */
	public double getwidth(Block bl,MainWindow M){
		Point3D pT = Map.gpsToPix(bl.getpoint_BlockTopRight().y(),bl.getpoint_BlockTopRight().x(),M.getHeight(),M.getWidth());
		Point3D pD = Map.gpsToPix(bl.getpoint_BlockDownleft().y(),bl.getpoint_BlockDownleft().x(),M.getHeight(),M.getWidth());
		int width = (int)(pT.x() - pD.x());
		return width;
	}
	/**
	 * the method get a block from the game and the gui and calculte the Height of the rectangle
	 * @param bl - the block
	 * @param M - the gui
	 * @return height - Height of the rectangle
	 */
	public double getHeight(Block bl,MainWindow M){
		Point3D pT = (Map.gpsToPix(bl.getpoint_BlockTopRight().y(),bl.getpoint_BlockTopRight().x(),M.getHeight(),M.getWidth()));
		Point3D pD = (Map.gpsToPix(bl.getpoint_BlockDownleft().y(),bl.getpoint_BlockDownleft().x(),M.getHeight(),M.getWidth()));
		int height = (int)(pD.y() - pT.y());
		return height;
	}
	
	/**
	 * the method get all the vertex of a block
	 * @param bl - a block
	 * @return pivot - an arraylist with the vertexs
	 */
	public ArrayList<Point3D> getVertex(Block bl) {
		ArrayList<Point3D> pivot = new ArrayList<Point3D>();
		pivot.add(bl.getpoint_BlockTopLeft());
		pivot.add(bl.getpoint_BlockTopRight());
		pivot.add(bl.getPoint_BlockDownRight());
		pivot.add(bl.getpoint_BlockDownleft());
		return pivot;
	}
	///////getter and Setter///////////////////
	/**
	 * the method get the cordinates of the block
	 * @return point_BlockStart
	 */
	public Point3D getpoint_BlockTopRight() {
		return point_BlockTopRight;
	}
	

	public Point3D getpoint_BlockDownleft() {
		return point_BlockDownleft;
	}

	public void setpoint_BlockTopRight(Point3D point_BlockTop) {
		this.point_BlockTopRight = point_BlockTop;
	}

	public void setpoint_BlockDownleft(Point3D point_BlockDownleft) {
		this.point_BlockDownleft = point_BlockDownleft;
	}

	public int getIDBloack() {
		return IDBloack;
	}

	public void setIDBloack(int iDBloack) {
		IDBloack = iDBloack;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Point3D getpoint_BlockTopLeft() {
		return point_BlockTopLeft;
	}

	public void setpoint_BlockTopLeft(Point3D point_BlockTopLeft) {
		this.point_BlockTopLeft = point_BlockTopLeft;
	}
	/**
	 * @return the point_BlockDownRight
	 */
	public Point3D getPoint_BlockDownRight() {
		return Point_BlockDownRight;
	}
	/**
	 * @param point_BlockDownRight the point_BlockDownRight to set
	 */
	public void setPoint_BlockDownRight(Point3D point_BlockDownRight) {
		Point_BlockDownRight = point_BlockDownRight;
	}
}
