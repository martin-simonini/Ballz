import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Balls {
	
	int x,y,radius;
	double dx,dy;
	Color color;
	boolean isMoving;
	boolean updateGame;
	final int MAXSPEED;
	Point center;
	
	public Balls(int x, int y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
		
		this.dx = 0.0;
		this.dy = -5.0;
		radius = 15;
		isMoving = false;
		updateGame = false;
		MAXSPEED = 5;
		center = new Point(this.x+radius,this.y+radius);
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(x, y, radius*2, radius*2);
	}
	public void update(int width, int height){
		if(isMoving){
			if((y+dy) < 0)
				dy *= -1;
			if(y+(3*radius)+dy > height){
				isMoving = false;
				updateGame = true;
				dy *= -1;
			}
			if(((x+(2*radius))+dx) > width || (x+dx) < 0){
				dx *= -1;
				x += dx;
			}
		
		x += dx;
		y += dy;
		center.move(x+radius, y+radius);
		//System.out.println(x);
	}
	}
	
	public void setDxDy(int dx){
		double hyp = Math.sqrt(Math.pow(dx,2)+Math.pow(this.y, 2));
		this.dx = (dx/hyp)*MAXSPEED;
		this.dy =  ((y/hyp)*MAXSPEED)*-1;
		if(Math.abs(this.dx) > 0 && Math.abs(this.dx) < 1 ){
			if(dx > 0)
				this.dx = 1;
			else
				this.dx = -1;
		}
//		System.out.println("dx = "+dx);
//		System.out.println("y = "+this.y);
//		System.out.println("hyp = "+hyp);
//		System.out.println("this.dx = "+this.dx);
//		System.out.println("dy = "+dy);
	}
}
