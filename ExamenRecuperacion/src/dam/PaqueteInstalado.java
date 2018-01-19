package dam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PaqueteInstalado {

	public static void main(String[] args) throws IOException, InterruptedException{
		
		if(args.length != 1) {
			System.out.println("El proceso debe tener exactamente un parámetro");
		}
		else {
			Process p1 = new ProcessBuilder("dpkg", "--get-selections").start();
			Process p2 = new ProcessBuilder("grep", args[0]).start();
			
			BufferedReader readerP1 = new BufferedReader(new InputStreamReader(p1.getInputStream(), "utf-8"));
			BufferedWriter writerP2 = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream(), "utf-8"));

			String linea = "";
			
			while((linea = readerP1.readLine()) != null) {
				writerP2.write(linea + "\n");
			}
			
			readerP1.close();
			writerP2.close();
			
			BufferedReader readerP2 = new BufferedReader(new InputStreamReader(p2.getInputStream(), "utf-8"));
			
			System.out.println("Resultado del proceso:");
			while((linea = readerP2.readLine()) != null) {
				System.out.println(linea);
			}
			
			readerP2.close();
			
			p1.waitFor();
			p2.waitFor();
			
			System.out.println("Salida del dpkg: " + p1.exitValue() + ", y del grep: " + p2.exitValue());
		}
	}
}