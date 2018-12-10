package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.awt.RepaintArea;


public class MainWindow extends JFrame implements MouseListener , MouseMotionListener
{
	   private Container window;
		private JPanel _panel;
		private Graphics _paper;
	        private int h, w;
	        private boolean isGamer;
	
	public BufferedImage myImage;
	ArrayList<Graphics> allDot = new ArrayList<Graphics>();
	public MainWindow() 
	{
		initGUI();		
		this.addMouseMotionListener(this); 
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("file"); 
		MenuItem item1 = new MenuItem("newGame");
		MenuItem item2 = new MenuItem("Save");
		MenuItem item3 = new MenuItem("load");
		Button button = new Button("New button");
		//menu.add(button);


		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		this.setMenuBar(menuBar);
		this.setCursor(getCursor());
		try {
			myImage = ImageIO.read(new File("C:\\Users\\Gal\\Desktop\\nykv nubjv\\data\\ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0, this);
	}
	 public BufferedImage getMyImage() {
		return myImage;
	}

	@Override
//	    public void paintComponent(Graphics G) {
//	        super.paintComponent(G);
//	        G.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
//	    }

	public void mouseClicked(MouseEvent arg) {
		Graphics g = getGraphics();
		g.setColor(Color.blue);
		g.fillOval(arg.getX(), arg.getY(), 10, 10);
	}




	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public static void main(String[] args)
	{
		MainWindow window = new MainWindow();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}






}
