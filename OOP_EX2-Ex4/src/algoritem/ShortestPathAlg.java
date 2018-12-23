package algoritem;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import Coords.MyCoords;
import Game.Fruit;
import Game.Game;
import Game.Packman;
import Geom.Point3D;


public class ShortestPathAlg extends MyCoords {



	public ShortestPathAlg() {
		// TODO Auto-generated constructor stub

	}

	public void ShortestPathAlgToPath(Game gamerun) {
		// TODO Auto-generated method stub
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = format.format(cal.getTime());
		Iterator<Packman> packclibarte = gamerun.getPack().iterator(); //Iterator for packman
		while(packclibarte.hasNext()) {
			Packman p = packclibarte.next();
			p.setFirst(p.getFirstPointCor());
			p.setTime2(strDate);
			Point3D point = new Point3D(p.getFirst(),p.getTime2());
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
		System.out.println(maxtime);


		///////////
		Iterator<Packman> calibrate = gamerun.getPack().iterator(); //Iterator for packman
		while(calibrate.hasNext()) {
			Packman p = calibrate.next();
			p.setFirstPointCor(p.getPointer_packmen());

		}

	}
	
	
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





}


