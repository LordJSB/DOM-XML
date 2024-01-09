package lecturaescrituradom;

public class Coche {

	private String concesionario;
	private String marca;
	private String modelo;
	private double cilindrada;

	public Coche(String concesionario, String marca, String modelo, double cilindrada) {
		this.concesionario = concesionario;
		this.marca = marca;
		this.modelo = modelo;
		this.cilindrada = cilindrada;
	}

	public String getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(String concesionario) {
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