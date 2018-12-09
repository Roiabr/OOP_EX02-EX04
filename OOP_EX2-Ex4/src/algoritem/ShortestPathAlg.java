package algoritem;

import java.util.ArrayList;

import org.junit.experimental.max.MaxCore;

import Coords.MyCoords;

public class ShortestPathAlg extends MyCoords {
	Game id;
	Packman p;
	Fruit f;
	ArrayList<Fruit> eat = new ArrayList<Fruit>();

	public ShortestPathAlg() {
		// TODO Auto-generated constructor stub

	}
	public ShortestPathAlg(Game gamerun) {
		double timeold=23213212;

		while(gamerun.pack.iterator().hasNext()) 
		{
			Packman pkm = gamerun.pack.iterator().next();
			while(gamerun.Fruit.iterator().hasNext()) 
			{
				Fruit fr = gamerun.Fruit.iterator().next();
				double timenow = (distance3d(p.pointer_packmen, f.pointer_fruit))/Double.parseDouble(pkm.getSpeed()) + pkm.time;
				;

				if(timenow<timeold) {
					timeold=timenow;
					boolean  battlepa=false;
					while(gamerun.pack.iterator().hasNext()) {
						Packman battlepack = gamerun.pack.iterator().next();
						double timebattle=(distance3d(p.pointer_packmen, f.pointer_fruit))/Double.parseDouble(pkm.getSpeed());
						if(timeold>timebattle) {
							battlepa=true;
						}					
						
					}
					if(battlepa==false) {
						pkm.ToEat(fr);
						fr.setLife(false);
						pkm.time = pkm.time + timeold; 
						pkm.fruiteat.add(fr);
						fr.setTimeStamp(fr.gep.getData().getUTC());
					}
				}
			}
		}
		
	}

}
