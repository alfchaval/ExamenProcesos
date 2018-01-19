package dam;

public class Globos {
	
	public final int maximoDeGlobos = 10;
	public final int maximoDeGlobosHinchandose = 3;
	public Globo[] listaDeGlobos;
	public int globosHinchandose;
	
	public Globos() {
		listaDeGlobos = new Globo[maximoDeGlobos];
		for (int i = 0; i < listaDeGlobos.length; i++) {
			listaDeGlobos[i] = new Globo(""+(i+1));
		}
		this.globosHinchandose = 0;
	}
	
	public boolean pinchado(int i) {
		return listaDeGlobos[i].estaPinchado();
	}
	
	public boolean todosPinchados() {
		boolean result = true;
		int index = 0;
		while(result && index < maximoDeGlobos) {
			if(!pinchado(0)) {
				result = false;
			}
			index++;
		}
		return result;
	}
	
	public synchronized Globo pideGlobo(HinchaGlobos HG) {
		Globo globo = null;
		if(globosHinchandose < maximoDeGlobosHinchandose) {
			int index = 0;
			while(globo == null && index < maximoDeGlobos) {
				if(!listaDeGlobos[index].estaPinchado() && !listaDeGlobos[index].estaHinchandose()) {
					globo = listaDeGlobos[index];
					globo.empiezaAHincharse();
					this.globosHinchandose++;
					System.out.println("Entregado el globo " + globo.getNombre() + " a " + HG.getNombre());
				}
				index++;
			}
		}
		return globo;
	}
	
	public synchronized Globo buscaGloboHinchandose(PinchaGlobos PG) {
		Globo globo = null;
		int index = 0;
		while(globo == null && index < maximoDeGlobos) {
			if(!listaDeGlobos[index].estaHinchandose()) {
				globo = listaDeGlobos[index];
				System.out.println(PG.getNombre() + " intentará pinchar el globo " + globo.getNombre());
			}
			index++;
		}
		return globo;
	}
	
	public synchronized void globoPinchado() {
		this.globosHinchandose--;
		notifyAll();
	}

}
