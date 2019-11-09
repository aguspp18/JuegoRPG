package Graficos;

public final class Sprite {
	// Declaro las variables.
	private final int lado;
	private int x;
	private int y;
	public int[] pixeles;
	private final HojaSprites hoja;

	// Coleccion de sprites
	public static Sprite asfalto = new Sprite(32, 0, 0, HojaSprites.desierto);
	public static Sprite pastoUno = new Sprite(32, 0, 0, HojaSprites.mapeadoUno);
	// Fin de la coleccion

	// Constructor.
	public Sprite(int lado, final int columna, final int fila, final HojaSprites hoja) {

		this.lado = lado;

		pixeles = new int[lado * lado];

		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;

		// Esto es para tomar un Sprite especifico de una hoja de Sprites.
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x)
						+ (y + this.y) * hoja.obtenAncho()];
			}
		}
	}
}
