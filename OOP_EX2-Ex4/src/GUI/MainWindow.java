package GUI;


import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import Coords.MyCoords;
import File_format.*;
import Geom.*;
import Map.Map;
import Robot.Play;
import algoritem.AlgoDijxestra;
import GIS.*;
import Game.Block;
import Game.Fruit;
import Game.Game;
import Game.Ghost;
import Game.Packman;


public class MainWindow extends JFrame implements MouseListener 
{

	private BufferedImage myImage ,myImage1,myImage2,myImage3,myImage4;
	private Game GuiGame;
	private Play play1;
	private boolean BeforeTheGameStarted=false;
	private boolean PlaceMyPackmen=false;
	private double angle;
	private boolean ManualRun=false;
	private boolean AutoRunMode=false;
	private boolean AftertheAutoModeStart=false;
	public  int IntMove=0;
	public static Point3D tempoint;


	public MainWindow() 
	{
		GuiGame = new Game();
		initGUI();		
		this.addMouseListener(this); 
	}


	private void initGUI() 
	{
		///////////MenuBar/////////////
		MenuBar menuBar = new MenuBar(); 

		/////////////Menu///////////////
		Menu menu1 = new Menu("File");
		Menu Player = new Menu("Player");
		Menu Game = new Menu("Game");

		/////////////////MenuItem//////////////////
		MenuItem newgame = new MenuItem("NewGame");
		MenuItem save = new MenuItem("Save Game");
		MenuItem load = new MenuItem("Load Game");
		MenuItem placeme = new MenuItem("Place me");
		MenuItem runserver = new MenuItem("Run manual");
		MenuItem AutoRun = new MenuItem("Auto Run");
		MenuItem end = new MenuItem("end");



		menu1.add(newgame);
		menu1.add(save);
		menu1.add(load);
		Player.add(placeme);
		Game.add(runserver);
		Game.add(AutoRun);
		Game.add(end);
		menuBar.add(menu1);
		menuBar.add(Player);
		menuBar.add(Game);

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

		load.addActionListener(new ActionListener() {  //reset all the arraylist and loadfile csv
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("load game"); 
				loadFile();
				repaint();
			}
		});

		placeme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Place The player");

				PlaceMyPackmen=true;
				BeforeTheGameStarted=true;
				repaint();

			}
		});
		end.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play1.stop();
				String info = play1.getStatistics();
			}
		});
		runserver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play1.setIDs(311505481,205516321);
				play1.start();
				ManualRun=true;
				repaint();
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("save game");
				writeFileDialog();
			}
		});
		AutoRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AftertheAutoModeStart = true;
				AutoRunMode=true;
				repaint();
			}
		});
		this.setMenuBar(menuBar);

		try {
			setMyImage(ImageIO.read(new File("Ariel1.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	int x = -1;
	int y = -1;
	double idi=0;
	/**
	 *paint the background and create 
	 *convert coordinates to pixel and draws packmen and fruit on the screen
	 */
	public void paint(Graphics g)
	{
		g.drawImage(getMyImage(), 0, 0,getWidth(),getHeight(), this);

		Iterator<Ghost> iterGhost =  GuiGame.getGhost().iterator();
		Iterator<Block> iterBlock =  GuiGame.getBlock().iterator();
		while(iterBlock.hasNext()) {
			Block bl = iterBlock.next();
			Point3D start = (Map.gpsToPix(bl.getpoint_BlockTopLeft().y(),bl.getpoint_BlockTopLeft().x(),this.getHeight(),this.getWidth()));
			g.fillRect(start.ix()+5, start.iy()+10, (int)bl.getwidth(bl, this), (int) bl.getHeight(bl, this));

		}


		Iterator<Fruit> iterFruit =  GuiGame.getFruit().iterator();
		Iterator<Packman> iterPackman = GuiGame.getPack().iterator();

		while(iterFruit.hasNext()) {		//All the time paint the fruit
			Fruit f = iterFruit.next();
			Point3D p = new Point3D(Map.gpsToPix(f.getPointer_fruit().y(),f.getPointer_fruit().x(),this.getHeight(),this.getWidth()));
			g.drawImage(f.getMyImage(), (int)p.x(), (int)p.y(), 20,20,this);
		}
		while(iterPackman.hasNext()) {		//All the time paint the fruit
			Packman pac = iterPackman.next();
			Point3D p = new Point3D(Map.gpsToPix(pac.getFirstPointCor().y(),pac.getFirstPointCor().x(),this.getHeight(),this.getWidth()));
			g.drawImage(pac.getMyImage1(), (int)p.x(), (int)p.y(), 20,20,this);
		}
		while(iterGhost.hasNext()) {
			Ghost ghost = iterGhost.next();
			Point3D p = new Point3D(Map.gpsToPix(ghost.getPoint_Ghost().y(),ghost.getPoint_Ghost().x(),this.getHeight(),this.getWidth()));
			g.drawImage(ghost.getImage(), (int)p.x(), (int)p.y(), 50,50,this);
		}

		if(BeforeTheGameStarted==true) 
		{
			Point3D p = new Point3D(Map.gpsToPix(GuiGame.getPlayer().getFirstPointCor().y(),GuiGame.getPlayer().getFirstPointCor().x(),this.getHeight(),this.getWidth()));
			g.drawImage(GuiGame.getPlayer().getMyImage(), (int)p.x(), (int)p.y(), 30,30,this);

		}
		if(ManualRun==true) // for manual mode 
		{
			Point3D p = new Point3D(Map.gpsToPix(GuiGame.getPlayer().getFirstPointCor().y(),GuiGame.getPlayer().getFirstPointCor().x(),this.getHeight(),this.getWidth()));
			g.drawImage(GuiGame.getPlayer().getMyImage(), (int)p.x(), (int)p.y(), 30,30,this);
			play1.rotate(angle);
			if(play1.isRuning()) 
			{
				String[] info = play1.getStatistics().split(",");
				update();

			}
			else {
				String info = play1.getStatistics();
				System.out.println(info);	
			}

		}
		if(AftertheAutoModeStart) { //just for the begining
			play1.setIDs(311505481,205516321);
			play1.start();
			GuiGame = new Game(play1);
			new AlgoDijxestra(GuiGame);
			//tempoint=GuiGame.getPlayer().getFirstPointCor();
			//System.out.println("start");
			AftertheAutoModeStart=false;
			repaint();
		}
		if(AutoRunMode) {
			if(GuiGame.getPlayer().getFirstPointCor()!=tempoint) 
			{ 
				play1.rotate(angle);
				GuiGame = new Game(play1);
				Ghost g1 = new Ghost();
				for(int i = IntMove;i<AlgoDijxestra.getPath().size();i++)
				{

					Point3D point = AlgoDijxestra.getPath().get(i);
					g.drawImage(g1.getImage(), (int)point.x(), (int)point.y(), 30,30,this);

				}
				NextNode();
			}
			else if(GuiGame.getPlayer().getFirstPointCor()==tempoint)
			{
				//NextNode();

			}


			if(GuiGame.getFruit().isEmpty()) {
				play1.stop();
				System.out.println(play1.getStatistics());
			}
			else {
				Point3D p = new Point3D(Map.gpsToPix(GuiGame.getPlayer().getFirstPointCor().y(),GuiGame.getPlayer().getFirstPointCor().x(),this.getHeight(),this.getWidth()));
				g.drawImage(GuiGame.getPlayer().getMyImage(), (int)p.x(), (int)p.y(), 30,30,this);
				repaint();

			}



		}
	}
	public void update() {
		GuiGame = new Game(play1);
		try {

			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
	public void NextNode()
	{
		if(IntMove==AlgoDijxestra.getPath().size()) {
			IntMove=0;
			//System.out.println("algo again ");
			AlgoDijxestra.getPath().clear();
			new AlgoDijxestra(GuiGame);
		}

		for(int i = IntMove;i<AlgoDijxestra.getPath().size();i++)
		{

			Point3D point = AlgoDijxestra.getPath().get(i);
			IntMove++;
			tempoint = new Point3D(point);
			//	System.out.println("the next stop in the path"+tempoint);
			MyCoords gal = new MyCoords();
			//	System.out.println(point.x());
			angle = gal.azimuth_elevation_dist(GuiGame.getPlayer().getFirstPointCor(), point)[0];
			play1.rotate(angle);
			GuiGame = new Game(play1);
			//AftertheAutoModeStart=true;
//			if(IntMove==i) {
//				IntMove=0;
//				new AlgoDijxestra(GuiGame);
//			}

			repaint();	
		}
	}




	/**
	 * get pixel for x,y and convert to coordinates for packmen or fruit.
	 * add to arraylist of the Game.
	 */
	public void mouseClicked(MouseEvent arg) { 
		x = arg.getX();
		y = arg.getY();
		Point3D p = new Point3D(x, y);
		p = Map.pixToGps(x, y,this.getHeight(),this.getWidth());
		if(PlaceMyPackmen==true)
		{
			play1.setInitLocation(p.x(),p.y());
			GuiGame.getPlayer().setFirstPointCor(p);
			PlaceMyPackmen=false;
			repaint();
		}

		if(play1.isRuning() && ManualRun==true )
		{
			MyCoords gal = new MyCoords();
			angle=gal.azimuth_elevation_dist(GuiGame.getPlayer().getFirstPointCor(), p)[0];
			repaint();
		}
	}
	/**
	 * read all the elements from the .csv file .
	 * Inserts to the current list of packmen || fruit.
	 */
	public void loadFile() {
		play1 = readFileDialogString();
		GuiGame = new Game(play1);
		repaint();
	}
	/**
	 * read csv file 
	 * @return gis_layer that include list of elements 
	 * any elements is packmen or fruit 
	 */

	public GisLayer readFileDialog() {
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
		GisLayer layer =  MultiCsv.Csv2Layer(folder + fileName);
		System.out.println(layer.get_Meta_data());
		return layer;

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