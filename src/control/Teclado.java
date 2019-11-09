package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KeyListener es para que el programa reciba los tecleos.
public final class Teclado implements KeyListener {
	// Cantidad de teclas que voy a tener.
	private final static int numeroTeclas = 120;
	// El estado de cada tecla actual.
	private final boolean[] teclas = new boolean[numeroTeclas];

	// Acciones
	public boolean arriba;
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;

	// Asigna cada tecla a cada accion.
	public void actualizar() {
		arriba = teclas[KeyEvent.VK_W];
		abajo = teclas[KeyEvent.VK_S];
		izquierda = teclas[KeyEvent.VK_A];
		derecha = teclas[KeyEvent.VK_D];
	}

	// Cuando se presiona una tecla cambia la variable a true.
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
	}

	// Cuando se suelta la pasa a false.
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
