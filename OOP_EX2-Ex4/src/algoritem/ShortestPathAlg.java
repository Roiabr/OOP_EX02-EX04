package algoritem;

import java.util.ArrayList;
import java.util.Iterator;



import Coords.MyCoords;

public class ShortestPathAlg extends MyCoords {

	ArrayList<Fruit> eat = new ArrayList<Fruit>();

	public ShortestPathAlg() {
		// TODO Auto-generated constructor stub

	}
	public  ShortestPathAlg(Game gamerun) {
		double timeold=1000000;
		int totalFruit =0;
		int fruiteated=0;
		Iterator<Fruit> Tofruit = gamerun.getFruit().iterator();

		while(Tofruit.hasNext()) {
			Fruit f=Tofruit.next();

			totalFruit++;
		}
		double timetotal = 0;



		while(totalFruit!=fruiteated)
		{	

			Iterator<Packman> pack = gamerun.getPack().iterator(); //Iterator for packman

			while(pack.hasNext()) {


				timeold = 10000000;
				Packman P = pack.next();
				Iterator<Fruit> fru = gamerun.getFruit().iterator();//Iterator for fruits

				while(fru.hasNext()) 
				{
					Fruit f=fru.next();
					double timenow = (distance3d(P.pointer_packmen, f.pointer_fruit))/(P.getSpeed()) + P.getTime(); //calculte the time that take to get to the fruit 

					if(timenow<=timeold && f.isLife()==true) {
						timeold=timenow;
						boolean  battlepa=false;
						Iterator<Packman> packmenbattle = gamerun.getPack().iterator();

						//	double timebattleold;
						while(packmenbattle.hasNext()) {
							Packman battlepack = packmenbattle.next();
							double timebattle=(distance3d(battlepack.pointer_packmen, f.pointer_fruit))/(battlepack.getSpeed())+battlepack.getTime();
							if(timeold>timebattle) {
								battlepa=true;
								//timebattleold=timebattle;
							}
							else if(timeold==timebattle) {
								battlepa=false;

							}
						}					

						if(battlepa==false &&f.isLife()==true) {
							P.getFruit().add(f);
							f.setLife(false);
							fruiteated++;
							P.setTime(P.getTime()+timeold); 
							timetotal=+timeold;

							System.out.println(P.getIDpack()+"eat"+f.getIdfruit());

						}


					}
				}
			}
		}
		System.out.println(timetotal);

	}

}


