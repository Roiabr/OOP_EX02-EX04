package Game;



import java.awt.image.BufferedImage;

import Geom.Point3D;

/**
 * This class represents a Fruit in the game.
 */
public class Fruit {
	private boolean life=true;
	private Point3D pointer_fruit;
	private String type;
	private int idfruit;
	private double speed;
	private double NatoTime;
	private String timeStamp;
	private BufferedImage myImage;
	
	/**
	 * a constructor for the class and make a full copy of fruit
	 */
	public Fruit() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * a constructor for the class and make a full copy of fruit
	 * @param f - a diffrent fruit 
	 */
	public Fruit(Fruit f) {
		this.type = f.getType();
		this.idfruit = f.getIdfruit();
		this.pointer_fruit = new Point3D(f.getPointer_fruit());
		this.speed = f.getSpeed();
		this.timeStamp = f.getTimeStamp();
	}
	
	
	///////////////////// GETTER&SETTER //////////////////////////////

	/**
	 * the method check if the fruit is alive
	 * @return - alive or died
	 */
	public boolean isLife() {
		return life;
	}
	/**
	 * the method change alive fruit to dead
	 * @param life = boolean to dicide if alive or not
	 */
	public void setLife(boolean life) {
		this.life = life;
	}
	/**
	 * the method get the time the fruit was eated
	 * @return timestamp - the time
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	/**
	 * the method set the time fruit was eated
	 * @param strDate - the time
	 */
	public void setTimeStamp(String strDate) {
		this.timeStamp = strDate;
	}
	/**
	 * the method get the coordinats of the fruit
	 * @return pointer_fruit - the coordinates
	 */
	public Point3D getPointer_fruit() {
		return pointer_fruit;
	}
	/**
	 * the method set the coordinats of the fruit
	 */
	public void setPointer_fruit(Point3D pointer_fruit) {
		this.pointer_fruit = pointer_fruit;
	}
	/**
	 * the method get the the type
	 * @return type - fruit
	 */
	public String getType() {
		return type;
	}
	/**
	 * the method set the the type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * the method get the the id  of the fruit
	 * @return idfruit - fruit
	 */
	public int getIdfruit() {
		return idfruit;
	}
	/**
	 * the method set the the id  of the fruit
	 */
	public void setIdfruit(int idfruit) {
		this.idfruit = idfruit;
	}
	/**
	 * the method get the the speed of the fruit
	 * @return idfruit - fruit
	 */
	public double getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}
	/**
	 * the method set the the speed of the fruit
	 */
	public void setSpeed(double d) {
		this.speed = d;
	}
	/**
	 * the method get the natoTime of fruit
	 * @return NatoTime - the time
	 */
	public double getNatoTime() {
		return NatoTime;
	}
	/**
	 * the method set the natoTime of fruit
	 * @param natoTime - the time
	 */
	public void setNatoTime(double natoTime) {
		NatoTime = natoTime;
	}

	/**
	 * get the image of the object
	 * @return the myImage
	 */
	public BufferedImage getMyImage() {
		return myImage;
	}

	/**
	 * @param myImage - to set the image of the object
	 */
	public void setMyImage(BufferedImage myImage) {
		this.myImage = myImage;
	}

}