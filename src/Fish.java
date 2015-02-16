
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fish {
	public int gridX;//left
	public int gridY;//top
	public Image image;
	public String imageName;
	public Fish(){
		imageName = "fish.png";
		try{
		    image = ImageIO.read(new File(imageName));		
		}catch(IOException e){
		    System.err.println("ERROR: Cannot read image file." + e);
		    System.exit(1);
		}
	}
	public boolean check(){
		if((gridX == Play.penguin.gridX && gridY == Play.penguin.gridY+1) || (gridX == Play.penguin.gridX+1 && gridY == Play.penguin.gridY+1)){
			return true;
		}
		return false;
	}
}
