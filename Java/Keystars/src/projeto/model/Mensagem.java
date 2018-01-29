package projeto.model;

public class Mensagem {
	private int id_mensagem;
	private String dest;
	private String assunto;
	private String mensagem;
	
	
	public Mensagem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mensagem(int id_mensagem, String dest, String assunto, String mensagem) {
		super();
		this.id_mensagem = id_mensagem;
		this.dest = dest;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}

	public int getId_mensagem() {
		return id_mensagem;
	}

	public void setId_mensagem(int id_mensagem) {
		this.id_mensagem = id_mensagem;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
}
