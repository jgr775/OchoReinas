package es.ual.ochoreinas;

import java.awt.*;
//
//Eight Reinas puzzle written in Java
//Written by Tim Budd, January 1996
//revised for 1.3 event model July 2001
//

public class Reina {
	// datos
	protected int fila;
	protected int columna;
	protected Reina vecina;

	// constructor
	public Reina(int c, Reina n) {
		fila = 1;
		columna = c;
		vecina = n;
	}

	public boolean buscaSolucion() {
		while (vecina != null && vecina.puedeAtacar(fila, columna))
			if (!avanza())
				return false;
		return true;
	}

	public boolean avanza() {
		if (fila < 8) {
			fila++;
			return buscaSolucion();
		}
		if (vecina != null) {
			if (!vecina.avanza())
				return false;
			if (!vecina.buscaSolucion())
				return false;
		} else
			return false;
		fila = 1;
		return buscaSolucion();

	}

	private boolean puedeAtacar(int testfila, int testcolumna) {
		int columnaDiferencia = testcolumna - columna;
		if ((fila == testfila) || (fila + columnaDiferencia == testfila) || (fila - columnaDiferencia == testfila))
			return true;
		if (vecina != null)
			return vecina.puedeAtacar(testfila, testcolumna);
		return false;
	}

}