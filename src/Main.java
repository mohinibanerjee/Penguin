

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;


public class Main extends JApplet {
    public static CardLayout cards;
    Title title;
    public static Play game;
    Instructions in=new Instructions();
    Over gameover = new Over();
    Win wins = new Win();
    public static CardLayoutHolder ch;

	public void init(){
		setSize(616, 688);
	}
	
    public Main(){
        //instantiate classes
        title = new Title();
        game = new Play();
        ch= new CardLayoutHolder();
        cards.show(ch,"Title");// show initial screen
        add(ch);//add cardholder to screen
        setContentPane(ch);
    }


    class Instructions extends JPanel implements ActionListener{
        JButton b;
        JLabel l;
        String imageName;
        Image image;
        Instructions(){
        	imageName = "snowflake.jpg";
    		try{
    		    image = ImageIO.read(new File(imageName));		
    		}catch(IOException e){
    		    System.err.println("ERROR: Cannot read image file." + e);
    		    System.exit(1);
    		}
            setBackground(Color.WHITE);
           setLayout(new FlowLayout(FlowLayout.CENTER,100,50));
            b=new JButton("Click to go back to initial screen");
            b.addActionListener(this);
            add(b);
            l=new JLabel("<html><div style=\"text-align: center;\">This is a simple game where the objective is to move the penguin to the  portal <br> at the bottom right corner using the arrow keys. However, you must avoid the <br> blocks, or else the game will automatically be over since the penguin wil crash <br>and die. As a bonus, the penguin can eat the fish by moving the penguin over the <br>fish. This game was developed over a weekend at a hackathon at Paypal <br>(http://www.hshacks.com/) Have fun!</html>");//show instruction
            //this html text breaking idea for a JLabel and it works because JLabel has some basic HTML features and it is from this forum: http://stackoverflow.com/questions/1090098/newline-in-jlabel citation #16
            l.setFont(new Font("SanSerif",Font.BOLD,16));
            l.setForeground(new Color(0,0,255));//set purple color
            add(l);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("Click to go back to initial screen")){
                cards.show(ch,"Title");
            }
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image, 80, 270, 440, 440, this);
        }
    }

    class Over extends JPanel implements ActionListener{
        JButton b;
        String imageName;
        Image image;
        
        Over(){
        	b=new JButton("Click to go back to initial screen.");
        	b.addActionListener(this);
        	add(b);
        	imageName = "gameover.jpg";
    		try{
    		    image = ImageIO.read(new File(imageName));		
    		}catch(IOException e){
    		    System.err.println("ERROR: Cannot read image file." + e);
    		    System.exit(1);
    		}
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 616, 688, this);// draw image
        }
        public void actionPerformed(ActionEvent e){
        	Play.blocks.clear();
        	Play.fish.clear();
            cards.show(ch,"Title");
        }
        
    }
    
    class Win extends JPanel implements ActionListener{
        JButton b;
        String imageName;
        Image image;
        
        Win(){
        	b=new JButton("Click to go back to initial screen.");
        	b.addActionListener(this);
        	add(b);
        	imageName = "youwin.gif";
    		try{
    		    image = ImageIO.read(new File(imageName));		
    		}catch(IOException e){
    		    System.err.println("ERROR: Cannot read image file." + e);
    		    System.exit(1);
    		}
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 616, 688, this);// draw image
        }
        public void actionPerformed(ActionEvent e){
        	Play.blocks.clear();
        	Play.fish.clear();
            cards.show(ch,"Title");
        }
        
    }
    
    class CardLayoutHolder extends JPanel {// parent for cardlayout
        CardLayoutHolder() {
            cards = new CardLayout();//card layout instantiated
            setLayout(cards);// add card layout
            add(title, "Title");
            add(in, "Instructions");//add instruction panel
            add(game, "Play");
            add(gameover, "Game Over");
            add(wins, "Win");
        }
    }
}