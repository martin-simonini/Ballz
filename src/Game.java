import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Game extends JPanel implements KeyListener{
	
	final int WIDTH,HEIGHT;
    int aimerStart;
	boolean endGame;
	Balls masterBall;
	int numBalls;
	int aimerX;
	Block testBlock;
	
	public Game(){
		//splash screen here
		//Var initialize here
		WIDTH = 500;
		HEIGHT = 800;
		endGame = false;
		masterBall = new Balls((WIDTH-60)/2,700,Color.WHITE);
		numBalls = 0;
		aimerStart = masterBall.x;
		aimerX = 0;
		testBlock = new Block(5);
		setupGUI();
		gameLoop();
	}
	private void setupGUI(){
		JFrame jf = new JFrame("Ballz");
		Container cp = jf.getContentPane();
		jf.setSize(WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.addKeyListener(this);
		
		cp.add(this);
		jf.setVisible(true);
		
		this.setBackground(new Color(29,31,36));
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		masterBall.draw(g);
		testBlock.draw(g);
		
		if(!masterBall.isMoving){
			aimerStart = masterBall.x;
			g.setColor(Color.BLUE);
			g.drawLine((int)masterBall.center.getX(),(int) masterBall.center.getY(), (aimerStart+aimerX), 0);
		}
	}
	
	private void blockUpdate(){
		masterBall.updateGame = false;
		testBlock.update();
		//TODO: add new blocks
	}
	
    //keyListener stuff
	public void keyPressed(KeyEvent e){
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 38){
			if(!masterBall.isMoving){
				masterBall.isMoving = true;
				masterBall.setDxDy(aimerX);
				aimerX = 0;
			}
		}
		if(e.getKeyCode() == 39){
			if(aimerStart+aimerX > 1000){
				aimerX += 30;
			}else if(aimerStart+aimerX > 2500){
				aimerX += 45;
			}
			else
			aimerX += 15;
		}
		if(e.getKeyCode() == 37){
			if(Math.abs(aimerStart+aimerX) > 1000){
				aimerX -= 30;
			}else if(Math.abs(aimerStart+aimerX) > 3000){
				aimerX -= 45;
			}else
			aimerX -= 15;
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	public void gameLoop(){
		while(!endGame){
			try{
				Thread.sleep(10);
			}catch(Exception e){e.printStackTrace();}
			masterBall.update(WIDTH,HEIGHT);
			testBlock.collision(masterBall);
			if(masterBall.updateGame)
				blockUpdate();
			repaint();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
	}

}
