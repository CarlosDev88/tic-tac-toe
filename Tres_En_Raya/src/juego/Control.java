package juego;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Control implements MouseListener {
	final int size = 3;
	int[][] tablero = new int[size][size];
	ArrayList<MyRectangle> zonaPulsable = new ArrayList<MyRectangle>();
	Point punto;
	int nBotonPulsado;

	boolean turno = false;

	public Control() {
		this.crearTablero();
	}

	public void crearTablero() {
		for (int i = 0; i < tablero[0].length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				tablero[i][j] = -1;
			}
		}
	}

	public int calcularTablero() {
		if ((tablero[0][0] != -1) && (tablero[0][0] == tablero[1][1]) && (tablero[1][1] == tablero[2][2])) {

			return tablero[1][1];
		}

		if ((tablero[2][0] != -1) && (tablero[2][0] == tablero[1][1]) && (tablero[1][1] == tablero[0][2])) {

			return tablero[1][1];
		}

		int n = this.calcularLinea();
		int m = this.calcularColumna();

		if (n != -1) {
			return n;
		}

		if (m != -1) {
			return m;
		}

		return -1;
	}

	public int calcularLinea() {

		for (int i = 0; i < tablero.length; i++) {
			if ((tablero[0][i] != -1) && (tablero[0][i] == tablero[1][i]) && (tablero[1][i] == tablero[2][i])) {

				return tablero[0][i];
			}

		}

		return -1;
	}

	public int calcularColumna() {

		for (int i = 0; i < tablero.length; i++) {
			if ((tablero[i][0] != -1) && (tablero[i][0] == tablero[i][1]) && (tablero[i][1] == tablero[i][2])) {

				return tablero[i][0];
			}

		}

		return -1;
	}

	public void ejecutarFrame() {
		int n = this.calcularTablero();
		if (n != -1) {
			if (n == 0) {
				System.out.println("han ganado los circulos");
				JOptionPane.showMessageDialog(null, "ha ganado los circulos");
			} else {
				JOptionPane.showMessageDialog(null, "ha ganado las cruces");
			}
		}
	}

	public boolean fin() {
		return (isCompleto()) || (this.calcularTablero() != -1);

	}

	public boolean isCompleto() {
		boolean condicion = false;
		for (int i = 0; i < tablero[0].length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if (tablero[i][j] == -1) {
					condicion = true;
				}

			}
		}
		return condicion;

	}

	public void getCasillas(Point punto) {
		Point2D p2d = punto;
		for (MyRectangle mrect : this.getZonaPulsable()) {
			if (mrect.getRect().contains(p2d)) {
				System.out.println("he pulsado sobre la casilla (" + mrect.getPosx() + " , " + mrect.getPosy() + ")");

				if (nBotonPulsado == 1) {
					tablero[mrect.getPosx()][mrect.getPosy()] = 0;
				}

				if (nBotonPulsado == 3) {
					tablero[mrect.getPosx()][mrect.getPosy()] = 1;
				}

			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		punto = e.getPoint();
		nBotonPulsado = e.getButton();
		// System.out.println(punto);
		this.getCasillas(punto);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public ArrayList<MyRectangle> getZonaPulsable() {
		return zonaPulsable;
	}

	public void setZonaPulsable(ArrayList<MyRectangle> zonaPulsable) {
		this.zonaPulsable = zonaPulsable;
	}

}
