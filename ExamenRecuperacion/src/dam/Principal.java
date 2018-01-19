package dam;

public class Principal {

	private static Globos globos = new Globos();
	private static final int hilos = 5;
	private static HinchaGlobos[] hinchaGlobos= new HinchaGlobos[hilos];
	private static PinchaGlobos[] pinchaGlobos= new PinchaGlobos[hilos];
	
	public static void main(String[] args) {
		
		for(int i = 0; i < hilos; i++) {
			hinchaGlobos[i] = new HinchaGlobos(globos, "HG"+(i+1));
			hinchaGlobos[i].start();
			pinchaGlobos[i] = new PinchaGlobos(globos, "PG"+(i+1));
			pinchaGlobos[i].start();
		}
	}
}
