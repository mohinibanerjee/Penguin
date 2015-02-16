

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;





import java.util.*;

public class Play extends JPanel implements KeyListener{
	int [] xGrid = new int[24];       //The x positions of each grid line. 
	int [] yGrid = new int[24];       //The y positions of each grid line. 
	boolean [][] isFilled = new boolean[24][24];     
	Color [][] colors = new Color[24][24];  
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public int width = this.getWidth();
	public int height = this.getHeight();
	public Timer timer = new Timer();
	public static Penguin penguin = new Penguin();
	public static ArrayList<Fish> fish = new ArrayList<Fish>();
	public int coincount = 0;
	public Image background;
	public String imageName1;
	public String imageName2;
	public Image win;
	public Play(){
		addKeyListener(this);
		imageName1 = "winterbackground.jpg";
		try{

		    background = ImageIO.read(new File(imageName1));		
		}catch(IOException e){
		    System.err.println("ERROR: Cannot read image file." + e);
		    System.exit(1);
		}
		imageName2 = "portal.gif";
		try{

		    win = ImageIO.read(new File(imageName2));		
		}catch(IOException e){
		    System.err.println("ERROR: Cannot read image file." + e);
		    System.exit(1);
		}
	}
	public void paintComponent(Graphics g){
		width = this.getWidth();
		height = this.getHeight();
		super.paintComponent(g);
		g.drawImage(background, 0, 0, 616, 688, this);
		g.drawImage(win, xGrid[20], yGrid[20], 100, 100, this);
		for(int i=0; i<24; i++){           //Drawing the grid. 
			yGrid[i] = (int)(height*i/24);
			xGrid[i] = (int)(width*i/24);
		}
		for(int i=0; i<blocks.size(); i++){
			blocks.get(i).move();
		}
		for(int i=0;i<blocks.size();i++){
			g.setColor(blocks.get(i).color);
			for(int j=0; j<4; j++){
				g.fillRect(xGrid[blocks.get(i).xCoordinateOffsets[j] + blocks.get(i).gridX], yGrid[blocks.get(i).yCoordinateOffsets[j] + blocks.get(i).gridY],25,25);
			}
		}
		requestFocus();
		g.drawImage(penguin.finalimage, xGrid[penguin.gridX], yGrid[penguin.gridY], 50, 50, this); 
		for(int i=0; i<fish.size(); i++){
			if(fish.get(i).check()){
				fish.remove(i);
				coincount++;
			}
		}
		for(int i=fish.size()-1;i>=0;i--){
			g.drawImage(fish.get(i).image, xGrid[fish.get(i).gridX], yGrid[fish.get(i).gridY], 25, 25, this);
		}
		g.setColor(Color.blue);
		g.setFont(new Font("SanSerif", Font.BOLD, 30));
		g.drawString("Fish: "+coincount, 460, 40);
		repaint();
		try{//pause for 1/10 of a second so screen will not be too fast
			Thread.sleep(100);
		}catch(InterruptedException ex){}
	}

	public void initializeGrid(){
		penguin.gridX = 0;
		penguin.gridY = 0;
		addBlocks();
		addfish();
	}

	public void addfish(){
		for(int i=0;i<15;i++){
			fish.add(new Fish());
			fish.get(i).gridX = (int)(Math.random()*22+2);
			fish.get(i).gridY = (int)(Math.random()*21+3);
		}
	}
	
	public void addBlocks(){
		for(int i=0;i<8;i++){
			int j = (int)(Math.random()*9);
			Block shape =  new Block();
			switch(j){
			case 0: shape = new Square(); break;
			case 1: shape = new LShape(); break;
			case 2: shape = new JShape(); break;
			case 3: shape = new TShape(); break;
			case 4: shape = new SShape(); break;
			case 5: shape = new UpsideDownTShape(); break;
			case 6: shape = new Square(); break;
			case 7: shape = new VerticalLine(); break;
			case 8: shape = new SidewaysLShape(); break;
			}
			shape.gridX = (int)(Math.random()*17+4);
			shape.gridY = (int)(Math.random()*17+4);
			shape.color = getRandomColor();
			blocks.add(shape);
		}
	}

	public Color getRandomColor(){         //This method decides on the color of the falling block. 
		Random random = new Random();
		float hue = random.nextFloat();
		float saturation = 0.9f;
		float luminance = 1.0f; 
		Color color = Color.getHSBColor(hue, saturation, luminance);
		return color;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		//System.out.println(true);
		if(e.getKeyCode()==e.VK_LEFT){//if user pressed left key
			penguin.updatePosition(1);
		}

		else if(e.getKeyCode()==e.VK_RIGHT){//if user pressed right key
			penguin.updatePosition(2);
		}

		else if(e.getKeyCode()==e.VK_UP){//if user pressed up key
			penguin.updatePosition(3);
		}

		else if(e.getKeyCode()==e.VK_DOWN){//if user pressed down key
			penguin.updatePosition(4);
		}
		repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

}
