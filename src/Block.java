import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Block {
	final int WIDTH;
	Color color;
	int hitpoints,x,y;
	boolean isDead;
	Point center;
	public Block(int hitpoints){
		WIDTH = 50;
		this.hitpoints = hitpoints;
		color = Color.MAGENTA; //TODO: choose color by hitpoints
		x = 100;
		y = 10;
		isDead = false;
		center = new Point(x+(WIDTH/2),y+(WIDTH/2));
	}
	
	public void draw(Graphics g){
		if(!isDead){
			g.setColor(this.color);
			g.fillRect(this.x, this.y, WIDTH, WIDTH);
			
			g.setColor(Color.white);
			g.drawString(""+hitpoints, (int)center.getX(), (int)center.getY());
		}
	}
	
	public void collision(Balls b){
		int distance = (int) Math.sqrt(Math.pow(b.center.getX()-this.center.getX(), 2)+Math.pow(b.center.getY()-this.center.getY(), 2));
		if(distance < b.radius + (WIDTH/2)){
			hitpoints--;
			b.dx *= -1;
			b.dy *= -1;
		}
		if(hitpoints < 1)
			isDead = true;
			
	}
	
	public void update(){
		this.y += 50;
		this.center.move(this.x,this.y);
	}
}
