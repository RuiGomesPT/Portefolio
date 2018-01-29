package projeto.model;

public class Programa {
	private int id;
	private String nome;
	private String tipo;
	private String descricao;
	private double preco;
	

	public Programa(int id, String nome, String tipo, String descricao, double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	

	public Programa() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
