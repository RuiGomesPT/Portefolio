package projeto.model;

public class Hotel {
	private String Identificador;
	private String nome;
	private String descricao;
	private int pisos;
	private int quartosPP;
	private String imageLink;
	private String extras;
	
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Hotel(String identificador, String nome, String descricao, int pisos, int quartosPP, String imageLink, String extras) {
		super();
		Identificador = identificador;
		this.nome = nome;
		this.descricao = descricao;
		this.pisos = pisos;
		this.quartosPP = quartosPP;
		this.imageLink = imageLink;
		this.extras = extras;
	}
	
	public String getImageLink() {
		return imageLink;
	}


	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}


	public String getIdentificador() {
		return Identificador;
	}
	public void setIdentificador(String identificador) {
		Identificador = identificador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPisos() {
		return pisos;
	}
	public void setPisos(int pisos) {
		this.pisos = pisos;
	}
	public int getQuartosPP() {
		return quartosPP;
	}
	public void setQuartosPP(int quartosPP) {
		this.quartosPP = quartosPP;
	}
	public String getExtras() {
		return extras;
	}
	public void setExtras(String extras) {
		this.extras = extras;
	}

	
}


