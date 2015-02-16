
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Title extends JPanel implements ActionListener{
	public JButton b1;
	public JButton b2;
	public JLabel title;//jlabel for title
	public Image background;//image
	public String imageName;
	Title(){
		imageName = "snowstorm.png";
		try{
		    background = ImageIO.read(new File(imageName));		
		}catch(IOException e){
		    System.err.println("ERROR: Cannot read image file." + e);
		    System.exit(1);
		}
		setLayout(new FlowLayout(FlowLayout.CENTER,500,100));//set layout
		//instantiate label
		title=new JLabel();
		title.setFont(new Font("SanSerif",Font.BOLD,40));
		title.setText("Find Your Way");
		//add label
		add(title);
		//instantiate buttons
		b1=new JButton("Instructions");
		b2=new JButton("Play");
		//add action listener
		b1.addActionListener(this);
		b2.addActionListener(this);
		//add buttons to the screen
		add(b1);
		add(b2);
	
	}
	public void actionPerformed(ActionEvent e){
		//see which button was pressed and determine level
		if(e.getActionCommand().equals("Instructions")){
			Main.cards.show(Main.ch, "Instructions");
		}
		else if(e.getActionCommand().equals("Play")){
			Main.cards.show(Main.ch, "Play");
			Main.game.initializeGrid();
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, 616, 688, this);//draw image
	}
}
