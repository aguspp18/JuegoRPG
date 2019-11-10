package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {
	protected int ancho;
	protected int alto;

	protected int[] cuadros;

	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		cuadros = new int[ancho * alto];
		generarMapa();
	}

	public Mapa(String ruta) {

		cargarMapa(ruta);

	}

	protected void generarMapa() {
	}

	private void cargarMapa(String ruta) {
	}

	public void actualizar() {

	}

	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {
		int o = compensacionX >> 5;
		int e = (compensacionX + pantalla.obtenAncho()) >> 5;
		int n = compensacionY >> 5;
		int s = (compensacionY + pantalla.obtenAlto()) >> 5;

		for (int y = n; y < s; y++) {
			for (int x = o; x < e; x++) {
				obtenCuadro(x, y).mostrar(x, y, pantalla);
				;
			}
		}
	}

	public Cuadro obtenCuadro(final int x, final int y) {
		switch (cuadros[x + y * ancho]) {
		case 0:
			return Cuadro.PISODUNGEON1;
		case 1:
			return Cuadro.PISODUNGEON1;
		case 2:
			return Cuadro.VACIO;
		case 3:
			return Cuadro.VACIO;
		default:
			return Cuadro.VACIO;
		}
	}
}
