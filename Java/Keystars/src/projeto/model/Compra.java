package projeto.model;

public class Compra {
	private int id_compra;
	private int id_utilizador;
	private String data;
	private String programas;
	
	
	
	public Compra(int id_compra, int id_utilizador, String data, String programas) {
		super();
		this.id_utilizador = id_utilizador;
		this.programas = programas;
		this.data = data;
		this.id_compra = id_compra;
	}
	
	public int getId_compra() {
		return id_compra;
	}

	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getProgramas() {
		return programas;
	}
	public void setProgramas(String programas) {
		this.programas = programas;
	}
	public int getId_utilizador() {
		return id_utilizador;
	}
	public void setId_utilizador(int id_utilizador) {
		this.id_utilizador = id_utilizador;
	}
	
}
