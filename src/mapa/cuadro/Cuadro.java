package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public abstract class Cuadro {
	public int x;
	public int y;

	public Sprite sprite;

	// Coleccion de cuadros
	public static final Cuadro PISODUNGEON1 = new CuadroPisoDungeon1(Sprite.PISODUNGEON1);
	public static final Cuadro VACIO = new CuadroVacio(Sprite.VACIO);
	// Fin de la coleccion de cuadros

	public Cuadro(Sprite sprite) {
		this.sprite = sprite;
	}

	public void mostrar(int x, int y, Pantalla pantalla) {
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}

	public boolean solido() {
		return false;
	}

}
