package GUI;
import java.awt.Color;
/**
 * Code taken from: https://javatutorial.net/display-text-and-graphics-java-jframe
 * 
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class JFrmaeGraphics extends JPanel{


	public void paint(Graphics g){
		Image image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Roi Abramovitch\\Desktop\\ariel1.png");
			int w = this.getWidth();
			int h = this.getHeight();
			 g.setColor(Color.red);
			
			g.setColor(Color.blue);
			
		    g.drawImage(image, 0,0,this);
		   
		    
	}
	
	public static void main(String[] args){
		JFrame frame= new JFrame("Gui.net");
		
		frame.getContentPane().add(new JFrmaeGraphics());
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
	
		//frame.setName("JFrame example");
	}
}