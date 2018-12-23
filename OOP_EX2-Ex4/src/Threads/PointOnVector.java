package Threads;

import java.util.ArrayList;
import GUI.MainWindow;
import Game.Fruit;
import Game.Packman;
import Geom.Point3D;

/**
 * This class represents the Point on vector between packman and fruit in the game.
 * @author Roi Abramovitch & Gal Hadida
 */
public class PointOnVector {
	private ArrayList<Point3D> points= new ArrayList<>();

	/**
	 * a constructor for the class that put all the point on the vector in the array List 
	 * @param P - a Packman	
	 * @param F - a Fruit
	 */
	public PointOnVector(Packman P ,Fruit F) {
		this.points=parts(P,F);
	}


	/**
	 * The method return packman and a fruit that he ate and return an array list of point that 
	 *  on the vector between the packman and the fruit
	 * @param P - the packman
	 * @param F - the fruit
	 * @return points -  array list of point that 
	 *  on the vector between the packman and the fruit
	 */
	public ArrayList<Point3D> parts(Packman P ,Fruit F)
	{
		double time = 0.05;
		double distancex = F.getPointer_fruit().x()-P.getFirstPointCor().x();
		double distancey = F.getPointer_fruit().y()-P.getFirstPointCor().y();
		double distance = Math.sqrt(distancex*distancex+distancey*distancey);
		double x=   P.getFirstPointCor().x();
		double y =	P.getFirstPointCor().y();

		double distancetemp=0;
		while(distance>distancetemp) {
			x =x+(time*distancex);
			y =y+(time*distancey);
			Point3D pnts = new Point3D(x, y);
			double disx = pnts.x()-P.getFirstPointCor().x();
			double disy = pnts.y()-P.getFirstPointCor().y();
			distancetemp = Math.sqrt(disx*disx+disy*disy);
			if(distance>distancetemp) {
				points.add(pnts);
			}
		}
		if(distancetemp>distance) {
			Point3D lastpoint = new Point3D(F.getPointer_fruit());
			points.add(lastpoint);
		}
		return points;
	}
	///////////////////// GETTER&SETTER //////////////////////////////
	/**
	 * the method get the array list with the point on the vector
	 * @return points - the list
	 */
	public ArrayList<Point3D> getPoints() {
		return points;
	}
}
