package Game;

import GIS.GIS_element;
import GIS.GisToElement;
import Geom.Point3D;
/**
 * This class represents a Black blocks in the game.
 * @author Roi Abramovitch & Gal Hadida
 *
 */
public class Block {
	private String [] ele;
	private Point3D point_BlockStart,point_BlockEnd;
	private int IDBloack;
	private String type;
	
	
	/**
	 * a default constructor for the class
	 */
	public Block() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * a constructor for the class and make a Block from csv file
	 * @param element - the element from csv file
	 */
	public Block(GIS_element element) {
		// TODO Auto-generated constructor stub
		this.ele = ((GisToElement) element).getGis();
		this.point_BlockStart = new Point3D(Double.parseDouble((ele[2])),Double.parseDouble((ele[3])), Double.parseDouble((ele[4])));
		this.point_BlockEnd = new Point3D(Double.parseDouble((ele[5])),Double.parseDouble((ele[6])), Double.parseDouble((ele[7])));
		this.IDBloack = Integer.parseInt(ele[1]);
		this.type = ele[0];
	}
	/**
	 * a constructor for the class and make a Block anther Block
	 * @param Block - the diffrent block
	 */
	public Block(Block bl) {
		this.type = bl.type;
		this.point_BlockStart = new Point3D(bl.point_BlockStart);
		this.point_BlockEnd = new Point3D(bl.point_BlockEnd);
		this.IDBloack = bl.IDBloack;
	}
}
