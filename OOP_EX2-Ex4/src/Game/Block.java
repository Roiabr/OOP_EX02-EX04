package Game;

import GIS.GIS_element;
import GIS.GisToElement;
import GUI.MainWindow;
import Geom.Point3D;
import Map.Map;
/**
 * This class represents a Black blocks in the game.
 * @author Roi Abramovitch & Gal Hadida
 *
 */
public class Block {
	
	private Point3D point_BlockTop,point_BlockDown;
	private Point3D point_BlockStart;
	private int IDBloack;
	private String type;
	
	
	/**
	 * a default constructor for the class
	 */
	public Block() {
		// TODO Auto-generated constructor stub
		this.point_BlockTop = null;
		this.point_BlockDown = null;
		this.setPoint_BlockStart(null);
		this.IDBloack = 0;
		this.type = "";
	}
	
	/**
	 * a constructor for the class and make a Block from csv file
	 * @param element - the element from csv file
	 */

	/**
	 * a constructor for the class and make a Block anther Block
	 * @param Block - the diffrent block
	 */
	public Block(Block bl) {
		this.type = bl.type;
		this.point_BlockTop = new Point3D(bl.point_BlockTop);
		this.point_BlockDown = new Point3D(bl.point_BlockDown);
		this.setPoint_BlockStart(new Point3D(bl.getPoint_BlockTop().y(),bl.getPoint_BlockDown().x()));
		this.IDBloack = bl.IDBloack;
		
	}
	///////////methods/////////////////////////
	public double getwidth(Block bl,MainWindow M){
		Point3D pT = Map.gpsToPix(bl.getPoint_BlockTop().y(),bl.getPoint_BlockTop().x(),M.getHeight(),M.getWidth());
		Point3D pD = Map.gpsToPix(bl.getPoint_BlockDown().y(),bl.getPoint_BlockDown().x(),M.getHeight(),M.getWidth());
		int width = (int)(pT.x() - pD.x());
		return width;
	}
	public double getHeight(Block bl,MainWindow M){
		Point3D pT = (Map.gpsToPix(bl.getPoint_BlockTop().y(),bl.getPoint_BlockTop().x(),M.getHeight(),M.getWidth()));
		Point3D pD = (Map.gpsToPix(bl.getPoint_BlockDown().y(),bl.getPoint_BlockDown().x(),M.getHeight(),M.getWidth()));
		int height = (int)(pD.y() - pT.y());
		return height;
	}
	///////getter and Setter///////////////////
	/**
	 * the method get the cordinates of the block
	 * @return point_BlockStart
	 */
	
	
	public Point3D getPoint_BlockTop() {
		return point_BlockTop;
	}
	

	public Point3D getPoint_BlockDown() {
		return point_BlockDown;
	}

	public void setPoint_BlockTop(Point3D point_BlockTop) {
		this.point_BlockTop = point_BlockTop;
	}

	public void setPoint_BlockDown(Point3D point_BlockEnd) {
		this.point_BlockDown = point_BlockEnd;
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

	public Point3D getPoint_BlockStart() {
		return point_BlockStart;
	}

	public void setPoint_BlockStart(Point3D point_BlockStart) {
		this.point_BlockStart = point_BlockStart;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git
