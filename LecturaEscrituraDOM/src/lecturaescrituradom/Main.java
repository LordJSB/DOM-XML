package lecturaescrituradom;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		DOM doc = new DOM("concesionario.xml");
		doc.lecturArchivo();
		//doc.raiz();
		doc.escriturArchivo(sc);
		sc.close();
	}

}
