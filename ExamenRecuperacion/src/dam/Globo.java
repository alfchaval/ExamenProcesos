package dam;

public class Globo {
	
	private final int volumenInicial = 1;
	private final int volumenMaximo = 5;
	
	private int volumen;
	private String nombre;
	private boolean pinchado;
	private boolean hinchandose;
	
	public Globo(String nombre) {
		this.volumen = volumenInicial;
		this.nombre = nombre;
		this.pinchado = false;
		this.hinchandose = false;
	}
	
	public int getVolumen() {
		return this.volumen;
	}
	
	public void increaseVolumen() {
		if(!this.pinchado) {
			this.volumen++;
			if(volumen > volumenMaximo) {
				pincha("El globo " + this.getNombre() + " explotó");
			}
		}
	}
	
	public boolean estaPinchado() {
		return this.pinchado;
	}
	
	public synchronized void pincha(PinchaGlobos PG) {
		pincha(PG.getNombre() + " pinchó el globo " + this.getNombre());
	}
	
	public synchronized void pincha(String mensaje) {
		if(!this.estaPinchado()) {
			this.volumen = 0;
			this.pinchado = true;
			System.out.println(mensaje);
		}
	}
	
	public boolean estaHinchandose() {
		return this.hinchandose;
	}
	
	public void empiezaAHincharse() {
		this.hinchandose = true;
	}
	
	public String getNombre() {
		return this.nombre;
	}

}
