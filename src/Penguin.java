

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Penguin {
	//global variables for position and size
		public int gridX, gridY;
		public int width;
		public int height;
		public Image finalimage;
		public String imageName;
		
		Penguin(){
			imageName = "Penguin .png";
			try{
			    finalimage = ImageIO.read(new File(imageName));		
			}catch(IOException e){
			    System.err.println("ERROR: Cannot read image file." + e);
			    System.exit(1);
			}
		}
		public void updatePosition(int direction){//to see which key was pressed
			if(direction == 1){ //left
				if(Play.penguin.gridX<=0){//stop panda if it reached the left wall so x of left arm is at zero
					Play.penguin.gridX=0;
				}
				else{//update position
					Play.penguin.gridX-=1;
				}
			}
			else if(direction == 2){ //right
				if(Play.penguin.gridX+1>=23){//stop panda if it reached the right wall so right corner of right arm is at 600
					Play.penguin.gridX=22;
				}
				else{//update position
					Play.penguin.gridX+=1;
				}
			}
			else if(direction == 3){ //up
				if(Play.penguin.gridY<=0){//stop panda if it reached the left wall so x of left arm is at zero
					Play.penguin.gridY=0;
				}
				else{//update position
					Play.penguin.gridY-=1;
				}
			}
			else{
				if(Play.penguin.gridY+1>=23){//stop panda if it reached the left wall so x of left arm is at zero
					Play.penguin.gridY=22;
				}
				else{//update position
					Play.penguin.gridY+=1;
				}
			}
			
			if(wins())
				Main.cards.show(Main.ch, "Win");
		}
		
		public boolean wins(){
			if(Play.penguin.gridX >=20 && Play.penguin.gridY >= 20){
				System.out.println("entered");
				return true;
			}
			return false;
		}
}

