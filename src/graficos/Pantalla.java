package graficos;

import mapa.cuadro.Cuadro;

public final class Pantalla {
	private final int ancho;
	private final int alto;
	public final int[] pixeles;

	private int diferenciaX;
	private int diferenciaY;

	// Temporal
//	private final static int LADO_SPRITE = 32;
//	private final static int MASCARA_SPRITE = LADO_SPRITE - 1;
	// Fin temporal

	// Creo la pantalla que voy a utilizar.
	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		this.pixeles = new int[ancho * alto];
	}

	// Limpia la pantalla cada ves que actualiza.
	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			this.pixeles[i] = 0;
		}
	}

	// TEMPORAL
//	public void mostrar(final int compensacionX, final int compensacionY) {
//		for (int y = 0; y < alto; y++) {
//			int posicionY = y + compensacionY;
//			if (posicionY < 0 || posicionY >= alto) {
//				continue;
//			}
//			for (int x = 0; x < ancho; x++) {
//				int posicionX = x + compensacionX;
//				if (posicionX < 0 || posicionX >= ancho) {
//					continue;
//				}
//				// codigo para redibujar.
//				// temporal
//				this.pixeles[posicionX
//						+ posicionY * ancho] = Sprite.PISODUNGEON1.pixeles[(x & MASCARA_SPRITE)
//								+ (y & MASCARA_SPRITE) * LADO_SPRITE];
//			}
//		}
//	}
	// TEMPORAL

	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;
		for (int y = 0; y < cuadro.sprite.obtenLado(); y++) {
			int posicionY = y + compensacionY;

			for (int x = 0; x < cuadro.sprite.obtenLado(); x++) {
				int posicionX = x + compensacionX;

				if (posicionX < 0 || posicionX > ancho || posicionY < 0 || posicionY > alto) {
					break;
				}
				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x
						+ y * cuadro.sprite.obtenLado()];

			}

		}
	}

	public int obtenAncho() {
		return ancho;
	}

	public int obtenAlto() {
		return alto;
	}

	public void establecerDirefencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}
}
