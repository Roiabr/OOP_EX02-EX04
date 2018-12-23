package algoritem;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import Coords.MyCoords;
import Game.Fruit;
import Game.Game;
import Game.Packman;
import Geom.Point3D;
import Map.Map;

/**
 * This class represents an algortiom for eating fruits by packmans in the game.
 * @author Roi Abramovitch & Gal Hadida
 */
public class ShortestPathAlg extends MyCoords {

	/**
	 * a defuelt constructor for the class
	 */
	public ShortestPathAlg() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * the method get a game and calculete the ways all pacman should eat the fruits.
	 * @param gamerun - the game that we run the algo on him
	 */
	public void ShortestPathAlgToPath(Game gamerun) {
		// TODO Auto-generated method stub

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = format.format(cal.getTime());
		Iterator<Packman> packclibarte = gamerun.getPack().iterator(); //Iterator for packman
		while(packclibarte.hasNext()) {//run over the packmans and init the first time
			Packman p = packclibarte.next();
			p.setFirst(p.getFirstPointCor());
			p.setTimeStamp(strDate);
			Point3D point = new Point3D(p.getFirst(),p.getTimeStamp());
			p.getPathOfPacman().add(point);
		}


		double timeold=1000000;
		int fruiteated=0;
		int totalFruit=gamerun.getFruit().size();


		while(totalFruit!=fruiteated)
		{	
			Iterator<Packman> pack = gamerun.getPack().iterator(); //Iterator for packman
			while(pack.hasNext()) 
			{
				Packman P = pack.next();//  Packman 
				Fruit f = WhoIsClosetFruit(P,gamerun);

				if(f!=null)
				{					
					if((P.getIDpack()==WhoIsClosetPackman(f,gamerun).getIDpack()) && (f.isLife()==true))

					{

						double distancenextpackmen =  ((distance3d(P.getFirstPointCor(), f.getPointer_fruit())-P.getRadiuos())/P.getSpeed());
						double totaltime = distancenextpackmen +P.getTime();
						P.getFruiteat().add(f);
						f.setLife(false);
						f.setNatoTime(distancenextpackmen);
						fruiteated++;
						P.setFirstPointCor(f.getPointer_fruit());
						P.setTime(totaltime); 
						System.out.println(P.getIDpack()+"eat"+f.getIdfruit());




						Calendar c = Calendar.getInstance();
						int hd =(int)totaltime;
						c.add(cal.SECOND,hd);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String strDate2 = sdf.format(c.getTime());
						f.setTimeStamp(strDate2);
						Point3D pointFruit = new Point3D(f.getPointer_fruit(), f.getTimeStamp());
						P.getPathOfPacman().add(pointFruit);
					}
				}
			}
		} //end of the while
		Iterator<Packman> packtime = gamerun.getPack().iterator(); //Iterator for packman
		double maxtime =0;
		while(packtime.hasNext()) {
			Packman p = packtime.next();
			if(p.getTime()>maxtime) {
				maxtime=p.getTime();
			}

		}
		
		Iterator<Packman> calibrate = gamerun.getPack().iterator(); //Iterator for packman
		while(calibrate.hasNext()) {
			Packman p = calibrate.next();
			p.setFirstPointCor(p.getPointer_packmen());

		}

	}

	/**
	 * the method check which fruit is the clossest fruit the the packman
	 * @param P - a packman
	 * @param gamerun - the game
	 * @return closerF - the fruit the close to packman 
	 */
	public Fruit WhoIsClosetFruit(Packman P,Game gamerun ) {
		Iterator<Fruit> fru = gamerun.getFruit().iterator();//Iterator for fruits
		double idfruit=-4;
		double distancebest=111111110;
		double distancenew=0;
		while(fru.hasNext()) 
		{
			Fruit f = fru.next();
			distancenew = ((distance3d(P.getFirstPointCor(), f.getPointer_fruit())-P.getRadiuos())/P.getSpeed())+P.getTime();
			if((f.isLife()==true)&& (distancenew<distancebest)) {
				idfruit=f.getIdfruit();
				distancebest=distancenew;
			}
		}
		Iterator<Fruit> findthebest = gamerun.getFruit().iterator();//Iterator for fruits

		while(findthebest.hasNext()) 
		{
			Fruit closerF= findthebest.next();
			if(closerF.getIdfruit()==idfruit)
				return closerF;

		}
		return null;
	}

	/**
	 * the method run over all the packmans and check who is the closses to the fruit
	 * @param f - a fruit
	 * @param gamerun - the game
	 * @return closerp - the closes packman
	 */
	public Packman WhoIsClosetPackman(Fruit f,Game gamerun ) {
		Iterator<Packman> pack = gamerun.getPack().iterator();//Iterator for fruits
		double idpackmen=10000;
		double distancebest=111111110;
		double distancenew=0;
		while(pack.hasNext()) 
		{
			Packman P = pack.next();
			distancenew = ((distance3d(P.getFirstPointCor(), f.getPointer_fruit())-P.getRadiuos())/P.getSpeed())+P.getTime();
			if((f.isLife()==true)&& (distancenew<distancebest)) {
				idpackmen=P.getIDpack();
				distancebest=distancenew;
			}
		}

		Iterator<Packman> findthebest = gamerun.getPack().iterator();//Iterator for fruits

		while(findthebest.hasNext()) 
		{
			Packman closerp= findthebest.next();
			if(closerp.getIDpack()==idpackmen)
				return closerp;

		}
		return null;
	}
	public static void main(String[] arg) {
	
	}
}


