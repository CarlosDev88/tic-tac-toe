package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TresEnRaya extends JFrame implements Runnable {

	int width = 800;
	int height = 800;
	int tcelda = 150;
	int desplazamiento = 170;
	BufferedImage bi = new BufferedImage(this.width, this.height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
	Graphics2D g2d = (Graphics2D) bi.getGraphics();
	ArrayList<MyRectangle> zonaPulsable = new ArrayList<MyRectangle>();
	Control control;

	Image cross = this.cargarImagen("cross.png");
	Image circle = this.cargarImagen("circle.png");

	public TresEnRaya() {
		this.setSize(this.width, this.height);
		control = new Control();
		this.crearZonaPulsable(g2d);
		control.setZonaPulsable(zonaPulsable);

		this.addMouseListener(control);

		Thread hilo = new Thread(this);
		hilo.start();

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void pintarFondo(Graphics g) {
		for (int i = 0; i < 100; i++) {
			Color c = new Color(136 + i, 120, 150);
			g.setColor(c);
			g.fillRect(0, i * 8, this.width, 8);
		}
	}

	public void pintarTablero(Graphics g) {

	}

	public void pintarSimbolo(Graphics g) {
		for (int i = 0; i < control.tablero[0].length; i++) {
			for (int j = 0; j < control.tablero.length; j++) {

				if (control.tablero[i][j] == 0) {
					g.drawImage(circle, desplazamiento + (i * tcelda), desplazamiento + (j * tcelda), tcelda, tcelda,
							this);
				}

				if (control.tablero[i][j] == 1) {
					g.drawImage(cross, desplazamiento + (i * tcelda), desplazamiento + (j * tcelda), tcelda, tcelda,
							this);
				}
			}
		}
	}

	public void crearZonaPulsable(Graphics g) {
		for (int i = 0; i < control.tablero[0].length; i++) {
			for (int j = 0; j < control.tablero.length; j++) {
				Rectangle2D rect = new Rectangle(desplazamiento + (i * tcelda), desplazamiento + (j * tcelda), tcelda,
						tcelda);
				MyRectangle mrect = new MyRectangle(rect, i, j);
				this.zonaPulsable.add(mrect);
			}
		}
	}

	public Image cargarImagen(String nombre) {
		Image image = new ImageIcon(this.getClass().getResource(nombre)).getImage();
		return image;

	}

	public void pintarCasillas(Graphics g) {
		Graphics2D g2d2 = (Graphics2D) g;

		for (MyRectangle mrect : control.getZonaPulsable()) {
			g2d2.setColor(Color.black);
			g2d2.draw(mrect.getRect());
		}
	}

	public void paint(Graphics g) {
		this.pintarFondo(g2d);
		this.pintarCasillas(g2d);
		this.pintarSimbolo(g2d);
		g.drawImage(bi, 10, 10, this.width, this.height, this);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(150);
			} catch (InterruptedException ex) {

			}
			control.ejecutarFrame();

			repaint();
		}
	}

	public static void main(String[] args) {
		new TresEnRaya();

	}
}
