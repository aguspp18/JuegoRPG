package graficos;

public final class Sprite {
	// Declaro las variables.
	private final int lado;
	private int x;
	private int y;
	public int[] pixeles;
	private HojaSprites hoja;

	// Coleccion de sprites
	public static Sprite VACIO = new Sprite(32, 0);
	public static Sprite ASFALTO = new Sprite(32, 0, 0, HojaSprites.DESIERTO);
	public static Sprite PASTOUNO = new Sprite(32, 0, 0, HojaSprites.MAPEADO1);
	public static Sprite PISODUNGEON1 = new Sprite(32, 8, 4, HojaSprites.DUNGEON);
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

	public Sprite(final int lado, final int color) {
		this.lado = lado;
		pixeles = new int[lado * lado];

		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}

	public int obtenLado() {
		return this.lado;
	}

}
