package Juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import Control.Teclado;

public class Juego extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// Creacion de la ventana
	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	private static final String NOMBRE = "Juego";
	private static JFrame ventana;

	// Crear un Thread - Hilo
	private static Thread thread;

	// Declaro el teclado
	private static Teclado teclado;

	// Comprobar que este abierto el juego.
	private static volatile boolean enFuncionamiento = false;
	// volatile sirve que para solo un metodo pueda modificar la variable mientras
	// se ejecuta un cambio.
	private static int aps = 0;
	private static int fps = 0;

	// Construcctor de la clase donde se inicia la ventana.
	private Juego() {

		// Inicializo el teclado y agrego para que se las teclas funcionen
		// dentro del Canvas.
		teclado = new Teclado();
		addKeyListener(teclado);

		// Inizializo el frame y creo la ventana.
		setPreferredSize(new Dimension(ANCHO, ALTO));
		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setLayout(new BorderLayout());
		ventana.add(this, BorderLayout.CENTER);
		ventana.pack();
		ventana.setVisible(true);
	}

	// metodo main.
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.iniciar();
	}

	/*
	 * inicio el thread synchronized hace que no se pueda usar el metodo al mismo
	 * tiempo que detener()
	 */
	private synchronized void iniciar() {
		enFuncionamiento = true;
		thread = new Thread(this, "Graficos");
		thread.start();
	}

	private synchronized void detener() {
		enFuncionamiento = false;
		try {
			// el thread.join() espera a que se termine las operacioens y despues detiene el
			// hilo
			thread.join();
			// el catch es para que se cuelge el programa por que es jodido terminar un
			// thread.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void actualizar() {

		teclado.actualizar();

		if (teclado.arriba) {
			System.out.println("Arriba");
		}
		if (teclado.abajo) {
			System.out.println("Abajo");
		}
		if (teclado.izquierda) {
			System.out.println("Izquierda");
		}
		if (teclado.derecha) {
			System.out.println("Derecha");
		}
		aps++;
	}

	private void mostrar() {
		fps++;
	}

	public void run() {

		// Pongo en foco la ventana.
		requestFocus();

		/*
		 * Creo una funsion para que se actualize el juego 60 veces por segundo. ms por
		 * segundo es la cantidad de milisegundos que entran en un segundo, y aps
		 * objetivo es la cantidad actualizaciones por segundo que quiero. ns por
		 * actualizacion nos dara la cantidad de ns que necesita para actualizar. Luego
		 * creo la primer referencia para actualizar con system.nanotime(). creo la
		 * variable delta y tiempotranscurrido. Luego inicio el bucle con otra medida de
		 * nanotime. Mientras el juego este en funcionamiento voy contando el tiempo y
		 * cada vez que pasan los nanosegundos obtenidos para una actualizacion delta
		 * llega a 1 y ejecuta el metodo actualizar(), para luego reducir a 0 el delta y
		 * repetir el proceso.
		 */
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		while (enFuncionamiento) {

			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
			while (delta >= 1) {
				actualizar();
				delta--;
			}
			mostrar();
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}
}
