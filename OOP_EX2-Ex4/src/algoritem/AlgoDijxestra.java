package algoritem;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import Game.Block;
import Game.Fruit;
import Game.Game;
import Game.Packmen_me;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
import Geom.Point3D;

/**
 * this class represent the navigation algorithm to build the most efficient track
 * @author Roi Abramovitch && Gal Hadida & sapir gofshtein
 *
 */
public class AlgoDijxestra  {

	public static ArrayList<Block> BlockTOuse= new ArrayList<Block>();
	public static ArrayList<Line2D> LINE= new ArrayList<Line2D>();
	private static ArrayList<Point3D> path = new ArrayList<Point3D>();
	private static int SizeNode;
	private static Graph Kosher;
	private static Point3D[]p3d;
	private boolean freeOrGraph = false;
	private int flag;

	/**
	 * this is a constrctur for the class
	 * @param game - receiving a game every time packman eats a fruit for a new calculation
	 */
	public AlgoDijxestra(Game game) {
		MakeLine(game);
		Kosher = new Graph();
		int id = 0;
		double dis = 0;
		double bestDis = 100000000;
		int TempId = 0;
		Iterator<Fruit> fru = game.getFruit().iterator();
		Node b=null;
		while(fru.hasNext()) {
			Fruit f = fru.next();
			if(freeLine(game.getPlayer(), f)) {
				dis = game.getPlayer().getFirstPointCor().distance2D(f.getPointer_fruit());
				TempId = f.getIdfruit();
				freeOrGraph = false;
			}
			else {
				MakeGraph(game,game.getPlayer(),f);
				int ind = Kosher.getNodeIndexByName("M");
				Graph_Algo.dijkstra(Kosher, "M");
				b = Kosher.getNodeByName(f.getType());
				dis = b.getDist();

				freeOrGraph  = true;
				TempId = f.getIdfruit();
			}
			if(dis<bestDis) {
				bestDis = dis;
				id = TempId;
				if(freeOrGraph) {
					flag = 1;
				}
				else {
					flag = 2;
				}
			}
		}
		if(flag == 1) {
			ArrayList<String> shortestPath = b.getPath();
			for(int i=1;i<shortestPath.size();i++) {
				int y=Kosher.getNodeIndexByName(shortestPath.get(i));
				path.add(p3d[y]);
				System.out.print(shortestPath.get(i)+",");
			}
		}
		else
		{
			Iterator<Fruit> fru2 = game.getFruit().iterator();
			while(fru2.hasNext()) {
				Fruit f = fru2.next();
				if(f.getIdfruit() == id) {
					path.add(f.getPointer_fruit());
				}
			}
		}
	}

	/**
	 * this function make the line from all the blocks in the game
	 * @param game - the game that holds the blocks
	 */
	public void MakeLine(Game game) {
		BlockTOuse= new ArrayList<Block>(game.getBlock());
		Iterator<Block>gal = BlockTOuse.iterator();
		while(gal.hasNext()) {
			Block sd = gal.next();
			Line2D templine  = new Line2D.Double(sd.getpoint_BlockTopLeft().x(),sd.getpoint_BlockTopLeft().y(),sd.getpoint_BlockTopRight().x(),sd.getpoint_BlockTopRight().y());
			Line2D templine1  = new Line2D.Double(sd.getpoint_BlockTopLeft().x(),sd.getpoint_BlockTopLeft().y(),sd.getpoint_BlockDownleft().x(),sd.getpoint_BlockDownleft().y());
			Line2D templine2  = new Line2D.Double(sd.getpoint_BlockTopLeft().x(),sd.getpoint_BlockTopLeft().y(),sd.getPoint_BlockDownRight().x(),sd.getPoint_BlockDownRight().y());
			Line2D templine3  = new Line2D.Double(sd.getpoint_BlockTopRight().x(),sd.getpoint_BlockTopRight().y(),sd.getpoint_BlockDownleft().x(),sd.getpoint_BlockDownleft().y());
			Line2D templine4  = new Line2D.Double(sd.getpoint_BlockTopRight().x(),sd.getpoint_BlockTopRight().y(),sd.getPoint_BlockDownRight().x(),sd.getPoint_BlockDownRight().y());
			Line2D templine5  = new Line2D.Double(sd.getpoint_BlockDownleft().x(),sd.getpoint_BlockDownleft().y(),sd.getPoint_BlockDownRight().x(),sd.getPoint_BlockDownRight().y());
			LINE.add(templine);
			LINE.add(templine1);
			LINE.add(templine2);
			LINE.add(templine3);
			LINE.add(templine4);
			LINE.add(templine5);
		}
	}

	/**
	 * this function tells us if their is a direct line between packman and fruit so we will not need to send the game to the algorithm for no cause
	 * @param me packman
	 * @param g fruit
	 * @return true  or false if their is a dircet line  between packman and fruit
	 */
	public static boolean freeLine(Packmen_me me,Fruit g) {
		Line2D templine = new Line2D.Double(me.getFirstPointCor().x(), me.getFirstPointCor().y(),g.getPointer_fruit().x(), g.getPointer_fruit().y());
		Iterator<Line2D>RectangleLines =LINE.iterator();
		while(RectangleLines.hasNext()) {
			Line2D fs = RectangleLines.next();
			if(fs.intersectsLine(templine)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * this function creating graph  
	 * @param game game
	 * @param me packman
	 * @param f fruit
	 */
	public static void MakeGraph(Game game,Packmen_me me,Fruit f) {
		BlockTOuse= new ArrayList<Block>(game.getBlock());
		p3d= new Point3D[BlockTOuse.size()*4+2];
		String source ="M";
		String target = "F";
		Kosher.add(new Node(source)); // Node "a" (0)
		p3d[0]=me.getFirstPointCor();
		Iterator<Block>BlockNode = BlockTOuse.iterator();
		int i =0;

		while(BlockNode.hasNext())
		{
			Block sd = BlockNode.next();
			ArrayList<Point3D> pivots=sd.getVertex(sd);

			for(int j=0;j<4;j++) {
				i++;

				p3d[i] = new Point3D(pivots.get(j));
				Node d = new Node(""+i);
				Kosher.add(d);
			}
		}
		i++;
		SizeNode=i;
		p3d[i] = f.getPointer_fruit();
		Kosher.add(new Node(target)); 
		AddEdges();
	}	
	
	/**
	 * this method make the edges in the graph
	 */
	public static void AddEdges() {
		for(int i=0;i<Kosher.size();i++) {
			for (int j = i+1; j < Kosher.size(); j++) {
				Line2D templine = new Line2D.Double(p3d[i].x(), p3d[i].y(),p3d[j].x(), p3d[j].y());
				boolean flag= DirectLine(templine);
				if(flag && (i!=j)) {
					if(i==0) {
						Kosher.addEdge("M", ""+ j, p3d[i].distance2D(p3d[j]));

					}

					else if(j==SizeNode) {
						Kosher.addEdge(""+ i, ""+ "F", p3d[i].distance2D(p3d[j]));

					}
					else {
						Kosher.addEdge(""+ i, ""+ j, p3d[i].distance2D(p3d[j]));
					}
				}
			}
		}
	}

	/**
	 * this function check if the line intersect anther line
	 * @param t - a line
	 * @return true - if intersect false if not
	 */
	public static boolean DirectLine(Line2D t) {
		Iterator<Line2D>RectangleLines =LINE.iterator();
		while(RectangleLines.hasNext()) 
		{
			Line2D g = RectangleLines.next();
			if(g.intersectsLine(t))
			{
				if((((g.getX1() == t.getX2()) || (g.getX1() == t.getX1()))&& ((g.getY1() == t.getY2()) ||( g.getY1() == t.getY1()))))
				{

				}
				else if ((((g.getX2() == t.getX2()) || (g.getX2() == t.getX1()))&& ((g.getY2() == t.getY2()) ||( g.getY2() == t.getY1()))))
				{

				}
				else {
					return false;
				}
			}

		}
		return true;
	}
	//////////////////GETTERS AND SETTER///////////
	public ArrayList<Line2D> getLINE() {
		return LINE;

	}
	public void setLINE(ArrayList<Line2D> lINE) {
		LINE = lINE;
	}
	public ArrayList<Block> getBlock() {
		return BlockTOuse;
	}
	public void setBlock(ArrayList<Block> block) {
		this.BlockTOuse = block;
	}
	public static ArrayList<Point3D> getPath() {
		return path;
	}
}