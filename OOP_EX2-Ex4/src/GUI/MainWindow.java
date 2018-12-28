package GUI;





import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Coords.Cords;
import Coords.MyCoords;
import File_format.*;
import Geom.*;
import Map.Map;
import Robot.Play;
import Threads.RunPackmen;
<<<<<<< HEAD
import jdk.jfr.internal.OldObjectSample;
=======
import jdk.nashorn.internal.ir.WithNode;
>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git
import GIS.*;
import Game.Block;
import Game.Fruit;
import Game.Game;
import Game.Ghost;
import Game.Packman;
import Game.Packmen_me;


public class MainWindow extends JFrame implements MouseListener 
{

	private BufferedImage myImage ,myImage1,myImage2,myImage3;
	private Game GuiGame;
	private Play play1;
	private MyCoords my;
	private Point3D oldclick;
	private int packmen_furit=0;//packmen or fruit 
<<<<<<< HEAD
	private boolean PlacePlayerFirstTime=false;
	private boolean AfterPlaceThePlayer=false;
	private double angle;
	private boolean afterrun=false;

=======
	private boolean run=false; //for run the game 
>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git






	public MainWindow() 
	{
		GuiGame = new Game();
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{

		try {
			myImage1 = ImageIO.read(new File("packman.png"));
			myImage2 = ImageIO.read(new File("apple.png"));
			myImage3 = ImageIO.read(new File("ghost.png"));
			//myImage4 = ImageIO.read(new File("apple.png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		MenuBar menuBar = new MenuBar(); 
		Menu menu1 = new Menu("File");
		Menu menu2 = new Menu("Input");
		Menu menu3 = new Menu("Edit");
		Menu Player = new Menu("Player");

		MenuItem newgame = new MenuItem("NewGame");
		MenuItem save = new MenuItem("Save Game");
		MenuItem load = new MenuItem("Load Game");
		MenuItem Packman = new MenuItem("Add Packman");
		MenuItem fruit = new MenuItem("Add Fruit");
		MenuItem item6 = new MenuItem("Run");
		MenuItem item7 = new MenuItem("kml");
		MenuItem placeme = new MenuItem("Place me");
		MenuItem runserver = new MenuItem("Run server");


		//  menuItem /////
		menu1.add(newgame);
		menu1.add(save);
		menu1.add(load);
		menu2.add(Packman);
		menu2.add(fruit);
		menu3.add(item6);
		menu3.add(item7);
		menu3.add(runserver);
		Player.add(placeme);
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(Player);

		newgame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Game");
				GuiGame.getPack().removeAll(GuiGame.getPack());
				GuiGame.getFruit().removeAll(GuiGame.getFruit());
				GuiGame.getGhost().removeAll(GuiGame.getGhost());
				GuiGame.getBlock().removeAll(GuiGame.getBlock());
				repaint();

			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				System.out.println("Save game");
				writeFileDialog();
			}
		});
		load.addActionListener(new ActionListener() {  //reset all the arraylist and loadfile csv
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("load game"); 
				loadFile();
				repaint();
			}


		});
		Packman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				packmen_furit = 1;
				System.out.println("packman choose");

			}
		});

		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				packmen_furit = 2;
				System.out.println("Fruit choose");
			}
		});
		item6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiGame.runGame();
				packmen_furit=0;
				repaint();
			}
		});
		item7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MultiCsv.Game2Kml(GuiGame);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		placeme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Place The player");

				PlacePlayerFirstTime=true;


			}
		});
		runserver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play1.start();
				ArrayList<String> board_data = play1.getBoard();
				AfterPlaceThePlayer=true;
				afterrun=true;

//				for(int i=0;i<board_data.size();i++) {
////					System.out.println(board_data.get(i)+"sad");
//				}

				System.out.println("start");

			}
		});

		this.setMenuBar(menuBar);
		try {
			setMyImage(ImageIO.read(new File("Ariel1.png")));
				myImage1 = ImageIO.read(new File("packman.png"));
				myImage2 = ImageIO.read(new File("apple.png"));
				myImage3 = ImageIO.read(new File("ghost.png"));
				myImage4 = ImageIO.read(new File("apple.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}		
	}

	int x = -1;
	int y = -1;
<<<<<<< HEAD
double idi=0;

=======
>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git
	/**
	 *paint the background and create 
	 *convert coordinates to pixel and draws packmen and fruit on the screen
	 */
	public void paint(Graphics g)
	{
		
		g.drawImage(getMyImage(), 0, 0,getWidth(),getHeight(), this);
<<<<<<< HEAD
		Iterator<Ghost> iterGhost =  GuiGame.getGhost().iterator();
		Iterator<Block> iterBlock =  GuiGame.getBlock().iterator();
		Iterator<Fruit> iterFruit =  GuiGame.getFruit().iterator();
		Iterator<Packman> iterPackman = GuiGame.getPack().iterator();
		while(iterFruit.hasNext()) {		//All the time paint the fruit
			Fruit f = iterFruit.next();
			Point3D p = new Point3D(Map.gpsToPix(f.getPointer_fruit().y(),f.getPointer_fruit().x(),this.getHeight(),this.getWidth()));
			g.drawImage(myImage2, (int)p.x(), (int)p.y(), 20,20,this);
		}
		while(iterPackman.hasNext()) {		//All the time paint the fruit
			Packman pac = iterPackman.next();
			Point3D p = new Point3D(Map.gpsToPix(pac.getFirstPointCor().y(),pac.getFirstPointCor().x(),this.getHeight(),this.getWidth()));
			g.drawImage(myImage1, (int)p.x(), (int)p.y(), 20,20,this);
		}
		while(iterGhost.hasNext()) {
			Ghost ghost = iterGhost.next();
			Point3D p = new Point3D(Map.gpsToPix(ghost.getPoint_Ghost().y(),ghost.getPoint_Ghost().x(),this.getHeight(),this.getWidth()));
			g.drawImage(myImage3, (int)p.x(), (int)p.y(), 50,50,this);
		}
		while(iterBlock.hasNext()) {
			Block bl = iterBlock.next();
			Point3D start = (Map.gpsToPix(bl.getPoint_BlockStart().y(),bl.getPoint_BlockStart().x(),this.getHeight(),this.getWidth()));
			g.fillRect(start.ix(), start.iy(), (int)bl.getwidth(bl, this), (int) bl.getHeight(bl, this));
		}
		if(AfterPlaceThePlayer==true ||PlacePlayerFirstTime==true) {
			System.out.println("succses ");
			Point3D p = new Point3D(Map.gpsToPix(GuiGame.getPlayer().getFirstPointCor().y(),GuiGame.getPlayer().getFirstPointCor().x(),this.getHeight(),this.getWidth()));

			g.drawImage(myImage1, (int)p.x(), (int)p.y(), 30,30,this);
			if(afterrun==true) {
				System.out.println(idi++);
				play1.rotate(angle);
				update();
				
			}
		
		}
	
	

		
		



	}
	public void update() {
		GuiGame = new Game(play1);
		try {
		
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
=======
		if(GuiGame.getBlock() != null) {
			Iterator<Block> iterBlock =  GuiGame.getBlock().iterator();
			while(iterBlock.hasNext()) {
				Block bl = iterBlock.next();
				
				System.out.println("Hught: " + bl.getHeight(bl, this));
				System.out.println("width: " +bl.getwidth(bl,this));
				Point3D start = (Map.gpsToPix(bl.getPoint_BlockStart().y(),bl.getPoint_BlockStart().x(),this.getHeight(),this.getWidth()));
				System.out.println("pointStarrt:" +start);
				g.fillRect(start.ix(), start.iy(), (int)bl.getwidth(bl, this), (int) bl.getHeight(bl, this));
			}
			Iterator<Fruit> iterFruit =  GuiGame.getFruit().iterator();
			while(iterFruit.hasNext()) {	//All the time paint the fruit
				Fruit f = iterFruit.next();
				Point3D p = new Point3D(Map.gpsToPix(f.getPointer_fruit().y(),f.getPointer_fruit().x(),this.getHeight(),this.getWidth()));
				g.drawImage(myImage2, p.ix(), (int)p.iy(), 20,20,this);
			}
			Iterator<Ghost> iterGhost =  GuiGame.getGhost().iterator();
			while(iterGhost.hasNext()) {
				Ghost ghost = iterGhost.next();
				Point3D p = new Point3D(Map.gpsToPix(ghost.getPoint_Ghost().y(),ghost.getPoint_Ghost().x(),this.getHeight(),this.getWidth()));
				g.drawImage(myImage3,p.ix(),p.iy(),40,40,this);
			}

			if(run==false) ///for any position change the packman by the real location before the game starts
			{
				Iterator<Packman> iterPac = GuiGame.getPack().iterator();
				while(iterPac.hasNext()) {
					Packman pac = iterPac.next();
					Point3D p = new Point3D(Map.gpsToPix(pac.getFirstPointCor().y(),pac.getFirstPointCor().x(),this.getHeight(),this.getWidth()));
					g.drawImage(myImage1, (int)p.x(), (int)p.y(), 20,20,this);
				}


			}

			if(run==true)//just for create thread one time 
			{
				Iterator<Packman> timetoeat = GuiGame.getPack().iterator();
				while(timetoeat.hasNext()) {
					Packman p = timetoeat.next();
					RunPackmen threadrun= new RunPackmen();
					threadrun.runpackmen(this, p);
				}


				run=false;
			}

>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git
		}
<<<<<<< HEAD
		repaint();

		
=======
>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git
	}
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git

	/**
	 * get pixel for x,y and convert to coordinates for packmen or fruit.
	 * add to arraylist of the Game.
	 */
	public void mouseClicked(MouseEvent arg) { 
		int idPackman = 100;
		int idFruit = 100;
		x = arg.getX();
		y = arg.getY();
		Point3D p = new Point3D(x, y);
		p = Map.pixToGps(x, y,this.getHeight(),this.getWidth());
		System.out.println(p.x()+"asdsad"+p.y());
		if(PlacePlayerFirstTime==true)
		{
			
			play1.setInitLocation(p.x(),p.y());
			oldclick=new Point3D(p);
			GuiGame.getPlayer().setFirstPointCor(p);
			PlacePlayerFirstTime=false;	
			AfterPlaceThePlayer=true;

<<<<<<< HEAD
=======


		if(packmen_furit == 1) { //create packman by click on the screen
			x = arg.getX();
			y = arg.getY();
			System.out.println(x);
			System.out.println(y);
			Point3D p = new Point3D(x, y);
			p = Map.pixToGps(x, y,this.getHeight(),this.getWidth());
			System.out.println(p);
			Packman packi = new Packman();
			packi.setPointer_packmen(p);
			packi.setFirstPointCor(p);
			packi.setType("P");
			packi.setIDpack(idPackman);
			idPackman++;
			packi.setRadiuos(1);
			packi.setSpeed(1);
			GuiGame.getPack().add(packi);
>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git
			repaint();
		}
		if(AfterPlaceThePlayer==true)
		{
			System.out.println(p.x()+"nowww"+p.y());
			System.out.println(oldclick.x()+" old click  "+oldclick.y());
			MyCoords gal = new MyCoords();
			angle=gal.azimuth_elevation_dist(p, oldclick)[0];
			System.out.println(angle+"angle");
			oldclick=new Point3D(p);
			repaint();
			
		}
//		if(packmen_furit == 1) {
		//create packman by click on the screen
//			x = arg.getX();
//			y = arg.getY();
//			Point3D p = new Point3D(x, y);
//			p = Map.pixToGps(x, y,this.getHeight(),this.getWidth());
//			Packman packi = new Packman();
//			packi.setPointer_packmen(p);
//			packi.setFirstPointCor(p);
//			packi.setType("P");
//			packi.setIDpack(idPackman);
//			idPackman++;
//			packi.setRadiuos(1);
//			packi.setSpeed(1);
//			GuiGame.getPack().add(packi);
//			repaint();
//		}
//		else if(packmen_furit == 2) { //create fruit by click on the screen
//			x = arg.getX();
//			y = arg.getY();
//			Point3D p = new Point3D(x, y);
//			p = Map.pixToGps(x, y,this.getHeight(),this.getWidth());
//			Fruit fruti = new Fruit();
//			fruti.setType("F");
//			fruti.setIdfruit(idFruit);
//			idFruit++;
//			fruti.setPointer_fruit(p);
//			fruti.setLife(true);
//			fruti.setSpeed(1);
//			GuiGame.getFruit().add(fruti);
//			repaint();
//		}

	}
	/**
	 * read all the elements from the .csv file .
	 * Inserts to the current list of packmen || fruit.
	 */
	public void loadFile() {
<<<<<<< HEAD
		play1 = readFileDialogString();
		GuiGame = new Game(play1);
=======
		GuiGame = new Game(readFileDialogString());
		//		Iterator<Packman> iterPac = GuiGame.getPack().iterator();
		//		Iterator<Fruit> iterFruit =  GuiGame.getFruit().iterator();
		//		Iterator<Ghost> iterGhsot = GuiGame.getGhost().iterator();
		//		Iterator<Block> iterBlock = GuiGame.getBlock().iterator();
		//		while(iterPac.hasNext()) {
		//			Packman f = iterPac.next();
		//			GuiGame.getPack().add(f);
		//		}
		//		while(iterFruit.hasNext()) {
		//			Fruit f = iterFruit.next();
		//			GuiGame.getFruit().add(f);
		//
		//		}
		//		while(iterGhsot.hasNext()) {
		//			Ghost g = iterGhsot.next();
		//			GuiGame.getGhost().add(g);
		//
		//		}
		//		while(iterBlock.hasNext()) {
		//			Block b = iterBlock.next();
		//			GuiGame.getBlock().add(b);
		//
		//		}
		//

>>>>>>> branch 'master' of https://github.com/Roiabr/OOP_EX02-EX04---Copy.git
		repaint();
	}
	public Play readFileDialogString() {
		Play play ;
		//		try read from the file
		FileDialog fd = new FileDialog(this, "Open text file", FileDialog.LOAD);
		fd.setFile("*.csv");
		fd.setDirectory("C:\\Users\\Roi Abramovitch\\eclipse-workspace\\OOP_EX02-EX04 - Copy\\data");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		try {
			FileReader fr = new FileReader(folder + fileName);
			BufferedReader br = new BufferedReader(fr);
			br.close();
			fr.close();
		} catch (IOException ex) {
			System.out.print("Error reading file " + ex);
			System.exit(2);
		}
		String All = folder + fileName;
		play = new Play(All);
		return play;

	}

	/**
	 * read csv file and return the file in a String
	 * @return All - the String diraction for the big file
	 */
	public Play readFileDialogString() {
		Play play;
		//		try read from the file
		FileDialog fd = new FileDialog(this, "Open text file", FileDialog.LOAD);
		fd.setFile("*.csv");
		fd.setDirectory("C:\\Users\\Roi Abramovitch\\eclipse-workspace\\OOP_EX02-EX04 - Copy\\data");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		try {
			FileReader fr = new FileReader(folder + fileName);
			BufferedReader br = new BufferedReader(fr);
			br.close();
			fr.close();
		} catch (IOException ex) {
			System.out.print("Error reading file " + ex);
			System.exit(2);
		}
		String All = folder + fileName;
		play = new Play(All);
		return play;

	}
	/**
	 * convert the list of packmen && fruit to list elements 
	 * and return layer==csv that include elements
	 */
	public void writeFileDialog() {
		//		 try write to the file
		FileDialog fd = new FileDialog(this, "Save the text file", FileDialog.SAVE);
		fd.setFile("*.csv");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		try {
			FileWriter fw = new FileWriter(folder + fileName);
			PrintWriter outs = new PrintWriter(MultiCsv.Game2csv(GuiGame,folder + fileName));

			outs.close();
			fw.close();
		} catch (IOException ex) {
			System.out.print("Error writing file  " + ex);
		}


	}


	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	public BufferedImage getMyImage() {
		return myImage;
	}

	public void setMyImage(BufferedImage myImage) {
		this.myImage = myImage;
	}


}
