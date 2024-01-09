package lecturaescrituradom;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		InterfazLectura v1 = new InterfazLectura();
		InterfazEscritura v2 = new InterfazEscritura();
		v1.setVisible(true);
		v2.setVisible(true);
		// Scanner sc = new Scanner(System.in);
		// DOM doc = new DOM("concesionario.xml");
		// doc.lecturArchivo();
		// doc.raiz();
		// doc.leerCoches();
		// doc.escriturArchivo(sc);
		// sc.close();
	}

}
