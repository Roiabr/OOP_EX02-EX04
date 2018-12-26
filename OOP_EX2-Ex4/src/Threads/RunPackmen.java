package Threads;



import java.util.Iterator;
import GUI.MainWindow;
import Game.Fruit;
import Game.Packman;
import Geom.Point3D;


/**
 * This class represents a Thread in the game that affect the movment in the gui.
 * @author Roi Abramovitch & Gal Hadida
 */
public class RunPackmen extends Thread {

	/**
	 * a defeult constructor for the class
	 */
	public RunPackmen() {

	}


	/**
	 * the method creats a thread for each packman in the game
	 * @param mw - the gui of the game
	 * @param p - the packman to create thread on him
	 */
	public void runpackmen (MainWindow mw,Packman p) 
	{		
		Thread t = new Thread() 
		{
			public void run() 
			{

				Iterator<Fruit> eatfruit = p.getFruiteat().iterator();

				while(eatfruit.hasNext())
				{	
					Fruit f = eatfruit.next();
					PointOnVector gal = new PointOnVector(p,f);
					Iterator<Point3D> timetoeat = gal.getPoints().iterator();
					while(timetoeat.hasNext()) {

						Point3D ps = timetoeat.next();
						p.setFirstPointCor(ps);

						mw.repaint();
						try {
							Thread.sleep(((long) f.getNatoTime())*3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("exception move packmen");
						}
					}
					eatfruit.remove(); //do inside packman when eats the fruit
				}
			}
		};
		t.start();
	}
}
