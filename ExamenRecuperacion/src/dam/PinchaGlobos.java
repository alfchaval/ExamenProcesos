package dam;

import java.util.Random;

public class PinchaGlobos extends Thread{
	
	private Globos globos;
	private String nombre;
	
	private Globo globoAPinchar;
	
	public PinchaGlobos(Globos globos, String nombre) {
		this.globos = globos;
		this.nombre = nombre;
	}

	public void run() {
		while(!globos.todosPinchados()) {
			globoAPinchar = globos.buscaGloboHinchandose(this);
			try {
				this.sleep((((new Random()).nextInt(10))+1)*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			globoAPinchar.pincha(this);
		}
	}
	
	public void pincha(int i) {
		globos.listaDeGlobos[i].pincha(this);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
}
