package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	// Declaro las variables.
	private final int ancho;
	private final int alto;
	public final int[] pixeles;

	// Coleccion de las hojas de sprites
	public static HojaSprites desierto = new HojaSprites("/texturas/Desierto.png", 320, 320);
	public static HojaSprites mapeadoUno = new HojaSprites("/texturas/Mapsv1.png", 736, 448);
	// fin de coleccion

	// Constructor
	public HojaSprites(final String ruta, final int ancho, final int alto) {

		// Inicializo las variables con el constructor
		this.alto = alto;
		this.ancho = ancho;

		this.pixeles = new int[ancho * alto];

		/*
		 * Extrae el color de cada pixel de la imagen. El try es por que puede haber
		 * errores jodidos.
		 */
		BufferedImage imagen;
		try {
			imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Getter para el ancho.
	public int obtenAncho() {
		return this.ancho;
	}

}
