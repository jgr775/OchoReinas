package es.ual.ochoreinas;
//Eight Reinas puzzle written in Java

//Written by Tim Budd, January 1996
//revised for 1.3 event model July 2001
//
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaReinas extends Frame {

	private ReinaPaint ultimaReina = null;

	public VentanaReinas() {
		super();
		setTitle("Problema de las ocho reinas");
		setSize(600, 500);
		for (int i = 1; i <= 8; i++) {
			ultimaReina = new ReinaPaint(i, ultimaReina);
			ultimaReina.buscaSolucion();
		}
		addMouseListener(new MouseKeeper());
		addWindowListener(new CloseQuit());
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		dibujarTablero(g);
		g.drawString("Pulse con para una nueva solución", 20, 470);
		// draw queens
		ultimaReina.paint(g);
	}

	private void dibujarTablero(Graphics g) {
		for (int i = 0; i <= 8; i++) {
			g.drawLine(50 * i + 10, 40, 50 * i + 10, 440);
			g.drawLine(10, 50 * i + 40, 410, 50 * i + 40);
		}
	}

	private class CloseQuit extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			e.getWindow().dispose();
		}
	}

	private class MouseKeeper extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			ultimaReina.avanza();
			repaint();
		}
	}

	public static void main(String[] args) {
		VentanaReinas world = new VentanaReinas();
		world.setVisible(true);
	}
}
