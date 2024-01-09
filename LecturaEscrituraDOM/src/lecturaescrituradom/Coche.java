package lecturaescrituradom;

public class Coche {

	private int concesionario = 1;
	private String marca;
	private String modelo;
	private double cilindrada;

	public Coche(int concesionario, String marca, String modelo, double cilindrada) {
		this.concesionario = concesionario;
		this.marca = marca;
		this.modelo = modelo;
		this.cilindrada = cilindrada;
	}

	public int getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(int concesionario) {
		this.concesionario = concesionario;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(double cilindrada) {
		this.cilindrada = cilindrada;
	}
}