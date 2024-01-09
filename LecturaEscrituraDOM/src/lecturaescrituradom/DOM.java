package lecturaescrituradom;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOM {

	private File archivo;
	private Document documento;
	private Object raizElemento;

	public DOM(String nombreArchivo) {
		this.archivo = new File(nombreArchivo);
		this.documento = initialize();
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
		this.documento = initialize();
	}

	public Document getDocumento() {
		return documento;
	}

	private Document initialize() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			if (archivo.exists()) {
				this.documento = dBuilder.parse(archivo);
			} else {
				System.out.println("El archivo no existe. Se creará uno nuevo.");
				if (archivo.createNewFile()) {
					System.out.println("Se ha creado: " + archivo.getName());
					this.documento = dBuilder.newDocument();
				} else {
					System.out.println("No se pudo crear el archivo.");
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return this.documento;
	}

	public static DOM nuevoXML(Scanner sc) {
		System.out.println("Inserte la ruta del archivo XML a modificar. "
				+ "Si el archivo se encuentra dentro de la carpeta del proyecto de Java, simplemente inserte el nombre:");
		String nombre = sc.nextLine();
		return new DOM(nombre);
	}

	public void lecturArchivo() {
		try {
			if (documento != null) {
				documento.getDocumentElement().normalize();
				leerNodo(documento.getDocumentElement());
			} else {
				System.out.println("El documento no ha sido inicializado correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void leerNodo(Node nodo) {
		if (nodo.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println("Nodo: " + nodo.getNodeName());

			if (nodo.hasAttributes()) {
				NamedNodeMap atributos = nodo.getAttributes();
				for (int i = 0; i < atributos.getLength(); i++) {
					Node atributo = atributos.item(i);
					System.out.println("Atributo: " + atributo.getNodeName() + " = " + atributo.getNodeValue()); // aver donde lo guardo
				}
			}

			String texto = nodo.getTextContent().trim();
			if (!texto.isEmpty()) {
				System.out.println("Texto: " + texto); // aver donde lo guardo
			}
		}

		NodeList hijos = nodo.getChildNodes();
		for (int i = 0; i < hijos.getLength(); i++) {
			Node hijo = hijos.item(i);
			if (hijo.getNodeType() == Node.ELEMENT_NODE) { // aver donde lo guardo
				leerNodo(hijo);
			}
		}
	}

	public void escriturArchivo(Scanner sc) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			while (true) {
				System.out.print("\nInserte el nombre del elemento raíz: ");
				String raiz = sc.nextLine();

				if (!nombreXMLValido(raiz)) {
					System.out.println("ERROR: El nombre del elemento raíz no es válido.");
					continue;
				}

				System.out.print("\nIngrese el número de elementos " + raiz + ": ");
				int numRaices = Integer.parseInt(sc.nextLine());

				if (numRaices >= 1) {
					for (int i = 1; i <= numRaices; i++) {
						Element eRaiz = doc.createElement(raiz);
						doc.appendChild(eRaiz);

						System.out.print("\n¿" + raiz + " tiene elementos hijos? (S/N): ");
						String tieneHijos = sc.nextLine().toUpperCase();

						if (tieneHijos.toLowerCase().equals("s")) {
							insertarElementosHijos(sc, doc, eRaiz);
						}else if(tieneHijos.toLowerCase().equals("n")) {
							System.out.print("\nIngrese el contenido para " + raiz + ": ");
				            String relleno = sc.nextLine();
				            eRaiz.appendChild(doc.createTextNode(relleno));
						}
					}

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource fuente = new DOMSource(doc);
					StreamResult resultado = new StreamResult("resultado.xml");
					transformer.transform(fuente, resultado);

					System.out.println("Archivo guardado exitosamente.");
				} else {
					System.out.println("ERROR: No puede haber menos de 1 elemento raíz.");
				}

				System.out.print("\n¿Desea insertar más nodos? (S/N): ");
				String continuar = sc.nextLine().toUpperCase();
				if (!continuar.equals("S")) {
					break;
				}
			}
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	private boolean nombreXMLValido(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}

		char firstChar = name.charAt(0);
		if (!Character.isLetter(firstChar) && firstChar != '_') {
			return false;
		}

		for (char c : name.toCharArray()) {
			if (!Character.isLetterOrDigit(c) && c != '.' && c != '-' && c != '_') {
				return false;
			}
		}
		return true;
	}

	private void insertarElementosHijos(Scanner sc, Document doc, Element elementoPadre) {
		System.out.print("\nInserte el nombre del elemento hijo: ");
		String hijo = sc.nextLine();
		System.out.print("\nIngrese el número de elementos " + hijo + ": ");
		int numHijos = Integer.parseInt(sc.nextLine());

		for (int j = 1; j <= numHijos; j++) {
			Element eHijo = doc.createElement(hijo);
			elementoPadre.appendChild(eHijo);

			System.out.print("\n¿El elemento tiene un atributo? (S/N): ");
			String tieneAtributo = sc.nextLine().toUpperCase();

			if (tieneAtributo.equals("S")) {
				System.out.print("\nIngrese el nombre del atributo de " + hijo + ": ");
				String nombreAtributo = sc.nextLine();
				System.out.print("\nIngrese el valor del atributo de " + hijo + ": ");
				String valorAtributo = sc.nextLine();
				Attr attr = doc.createAttribute(nombreAtributo);
				attr.setValue(valorAtributo);
				eHijo.setAttributeNode(attr);
			}

			System.out.print("\nIngrese el contenido para " + hijo + ": ");
			String contenido = sc.nextLine();
			eHijo.appendChild(doc.createTextNode(contenido));
		}
	}

	public void raiz() {
		if (documento != null) {
			raizElemento = documento.getDocumentElement();
			aDarVueltas((Node) raizElemento);
		} else {
			System.out.println("El documento no ha sido inicializado correctamente.");
			raizElemento = null;
		}
	}

	private void aDarVueltas(Node node) {
		NodeList NodosL = node.getChildNodes();
		for (int i = 0; i < NodosL.getLength(); i++) {
			Node hoja = NodosL.item(i);
			if (hoja.getNodeName().equals("coche")) {
				getRaizElemento(hoja);
			}
		}
	}

	public void getRaizElemento(Node nodo) {
		System.out.println("Nodo actual: " + nodo.getNodeName());
		NodeList listaHijos = nodo.getChildNodes();
		for (int i = 0; i < listaHijos.getLength(); i++) {
			Node hoja = listaHijos.item(i);
			if (hoja.getNodeType() == Node.ELEMENT_NODE) {
				String nombreNodo = hoja.getNodeName();
				String valorNodo = hoja.getTextContent();
				System.out.println("\t" + nombreNodo + ": " + valorNodo);
			}
		}
	}
}

//public void lecturArchivo() {
//try {
//	if (documento != null) {
//		documento.getDocumentElement().normalize();
//		NodeList nList = documento.getElementsByTagName("coche");
//		for (int i = 0; i < nList.getLength(); i++) {
//			Node node = nList.item(i);
//
//			if (node.getNodeType() == Node.ELEMENT_NODE) {
//				Element eElement = (Element) node;
//
//				if (eElement.hasChildNodes()) {
//					NodeList nl = node.getChildNodes();
//					System.out.print("Elemento: ");
//					for (int j = 0; j < nl.getLength(); j++) {
//						Node nd = nl.item(j);
//						System.out.print(nd.getTextContent());
//					}
//					System.out.println();
//				}
//			}
//		}
//	} else {
//		System.out.println("El documento no ha sido inicializado correctamente.");
//	}
//} catch (Exception e) {
//	e.printStackTrace();
//}
//}

//public void escriturArchivo(Scanner sc) {
//try {
//	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//	DocumentBuilder db = dbf.newDocumentBuilder();
//	Document doc = db.newDocument();
//
//	System.out.print("\nIngrese el número de concesionarios:");
//	int numCon = Integer.parseInt(sc.nextLine());
//
//	if (numCon >= 1) {
//		for (int i = 1; i <= numCon; i++) {
//			Element eRaiz = doc.createElement("concesionario");
//			doc.appendChild(eRaiz);
//
//			System.out.print("\nIngrese el número de coches: ");
//			int numCoche = Integer.parseInt(sc.nextLine());
//			for (int j = 1; j <= numCoche; j++) {
//				Element eCoche = doc.createElement("coche");
//				eRaiz.appendChild(eCoche);
//				System.out.print("\nIngrese el número de id del vehículo:");
//				String id = sc.nextLine();
//				Attr attr = doc.createAttribute("id");
//				attr.setValue(id);
//				eCoche.setAttributeNode(attr);
//
//				System.out.print("\nEscoja la marca de su vehículo:");
//				String marca = sc.nextLine();
//				Element eMarca = doc.createElement("marca");
//				eMarca.appendChild(doc.createTextNode(marca));
//				eCoche.appendChild(eMarca);
//
//				System.out.print("\nEscoja el modelo de su vehículo:");
//				String modelo = sc.nextLine();
//				Element eModelo = doc.createElement("modelo");
//				eModelo.appendChild(doc.createTextNode(modelo));
//				eCoche.appendChild(eModelo);
//
//				System.out.print("\nEscoja la cilindrada de su vehículo:");
//				String cil = sc.nextLine();
//				Element eCilindrada = doc.createElement("cilindrada");
//				eCilindrada.appendChild(doc.createTextNode(cil));
//				eCoche.appendChild(eCilindrada);
//			}
//		}
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource fuente = new DOMSource(doc);
//		StreamResult resultado = new StreamResult("resultado.xml");
//		transformer.transform(fuente, resultado);
//
//		System.out.println("Archivo guardado exitosamente.");
//	} else {
//		System.out.println("ERROR: No puede haber menos de 1 concesionario.");
//	}
//
//} catch (ParserConfigurationException | TransformerException e) {
//	e.printStackTrace();
//}
//}