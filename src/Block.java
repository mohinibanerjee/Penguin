
import java.awt.Color;

public class Block {
	public Color color;               //Color of the block.
	public int gridX, gridY;      //Most top left coordinate of block.
	int[] xCoordinateOffsets;        
	int[] yCoordinateOffsets;
	int dx, dy;
	int random = (int)(Math.random()*3+1);
	public Block(){
		if(random==1){
			dx = -1;
			dy = 1;
		}
		else if(random==2){
			dx = 1;
			dy = -1;
		}
		else if(random==3){
			dx = 1;
			dy = 1;
		}
	}
	public void move(){
		gridX = gridX + dx;
		gridY = gridY + dy;
		boolean violates = false;
		for(int i=0;i<4;i++){
			if(gridX+xCoordinateOffsets[i]<=0){ 
				gridX=0;
				dx=-dx;
				violates = true;
			}
			if(gridX+xCoordinateOffsets[i]>=23){
				gridX=23-xCoordinateOffsets[i]-1;
				dx=-dx;
				violates = true;
			}
			
			if(gridY+yCoordinateOffsets[i]<=0){   
				gridY=0;
				dy=-dy;
				violates = true;
				
			}
			if(gridY+yCoordinateOffsets[i]>=23){ 
				gridY=23-yCoordinateOffsets[i]-1;
				dy=-dy;
				violates = true;
			}
			collide();
			if(violates)
				break;
		}
	}
	
	public void collide(){
		for(int i=0; i<4; i++){
			if((gridX + xCoordinateOffsets[i] == Play.penguin.gridX && gridY + yCoordinateOffsets[i] == Play.penguin.gridY+1) || 
					(gridX == Play.penguin.gridX+1 && gridY == Play.penguin.gridY+1)){
				Main.cards.show(Main.ch, "Game Over");
			}
		}
	}
}
class Square extends Block{
	public Square() {
		xCoordinateOffsets = new int[]{0,0,1,1};
		yCoordinateOffsets = new int[]{0,1,0,1};
	}
}
class LShape extends Block{
	public LShape() {
		xCoordinateOffsets = new int[]{0,0,0,1};
		yCoordinateOffsets = new int[]{0,1,2,2};
	}
}
class JShape extends Block{
	public JShape() {
		xCoordinateOffsets = new int[]{1,1,1,0};
		yCoordinateOffsets = new int[]{0,1,2,2};
	}
}
class UpsideDownTShape extends Block{
	public UpsideDownTShape() {
		xCoordinateOffsets = new int[]{0,1,1,2};
		yCoordinateOffsets = new int[]{1,1,0,1};
	}
}
class SShape extends Block{
	public SShape() {
		xCoordinateOffsets = new int[]{0,1,1,2};
		yCoordinateOffsets = new int[]{0,0,1,1};
	}
}
class TShape extends Block{
	public TShape() {
		xCoordinateOffsets = new int[]{0,1,2,1};
		yCoordinateOffsets = new int[]{0,0,0,1};
	}
}

class VerticalLine extends Block{
	public VerticalLine(){
		xCoordinateOffsets = new int[]{0,0,0,0};
		yCoordinateOffsets = new int[]{0,1,2,3};
	}
}

class SidewaysLShape extends Block{
	public SidewaysLShape(){
		xCoordinateOffsets = new int[]{0,1,2,2};
		yCoordinateOffsets = new int[]{0,0,0,1};
	}
}