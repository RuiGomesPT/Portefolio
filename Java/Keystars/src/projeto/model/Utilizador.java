package projeto.model;

public class Utilizador {
	private int id;
	private String email;
	private String password;
	private String nome;
	private String apelido;
	private int contacto;
	private String tipo;
	private String hotel;
	private double consumo;
	private int cont;
	private String sexo;
	private String nacionalidade;
	private String civil;
	private String checkin;
	private String checkout;
	private String profilePic;
	
	
	
	
	
	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCivil() {
		return civil;
	}

	public void setCivil(String civil) {
		this.civil = civil;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public Utilizador(int id, String email, String password, String nome, String apelido, int contacto, String tipo,
			String hotel, double consumo, int cont, String sexo, String nacionalidade, String civil, String checkin,
			String checkout, String profilePic) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.apelido = apelido;
		this.contacto = contacto;
		this.tipo = tipo;
		this.hotel = hotel;
		this.consumo = consumo;
		this.cont = cont;
		this.sexo = sexo;
		this.nacionalidade = nacionalidade;
		this.civil = civil;
		this.checkin = checkin;
		this.checkout = checkout;
		this.profilePic = profilePic;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public Utilizador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public int getContacto() {
		return contacto;
	}
	public void setContacto(int contacto) {
		this.contacto = contacto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
