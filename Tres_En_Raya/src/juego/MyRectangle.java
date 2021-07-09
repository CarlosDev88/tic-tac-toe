package juego;


import java.awt.geom.Rectangle2D;


public class MyRectangle {
	Rectangle2D rect;
	int posx;
	int posy;

	public MyRectangle() {

	}

	public MyRectangle(Rectangle2D rect, int posx, int posy) {
	
		this.rect = rect;
		this.posx = posx;
		this.posy = posy;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	

	
}
