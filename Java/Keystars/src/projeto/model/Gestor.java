package projeto.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import projeto.model.Hotel;
import projeto.model.Utilizador;
import projeto.view.MenuPrincipal;
import projeto.model.Programa;

public class Gestor {
	private static ArrayList<Utilizador> utilizadores = new ArrayList<Utilizador>();
	private static ArrayList<Hotel> hoteis = new ArrayList<Hotel>();
	private static ArrayList<Programa> programas = new ArrayList<Programa>();
	private static ArrayList<Compra> compras = new ArrayList<Compra>();
	private static ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
	
	
	
	public static ArrayList<Utilizador> getUtilizadores() {
		return utilizadores;
	}
	public static void setUtilizadores(ArrayList<Utilizador> utilizadores) {
		Gestor.utilizadores = utilizadores;
	}
	public static ArrayList<Hotel> getHoteis() {
		return hoteis;
	}
	public static void setHoteis(ArrayList<Hotel> hoteis) {
		Gestor.hoteis = hoteis;
	}
	public static ArrayList<Programa> getProgramas() {
		return programas;
	}
		
	public static void setProgramas(ArrayList<Programa> programas) {
		Gestor.programas = programas;
	}
	
	public static ArrayList<Compra> getCompras() {
		return compras;
	}
	public static void setCompras(ArrayList<Compra> compras) {
		Gestor.compras = compras;
	}
	
	public static ArrayList<Mensagem> getMensagens() {
		return mensagens;
	}
	public static void setMensagens(ArrayList<Mensagem> mensagens) {
		Gestor.mensagens = mensagens;
	}
	
	public static void saveUtilizadores() throws FileNotFoundException {		
		File file = new File("txtfiles\\listaUtilizadores.txt");		
		PrintWriter out = new PrintWriter(file);
		for (int i = 0; i < utilizadores.size(); i++) {
			out.println(utilizadores.get(i).getId() + "#" + utilizadores.get(i).getEmail() + "#" + utilizadores.get(i).getPassword() + "#" + utilizadores.get(i).getNome() + "#" + utilizadores.get(i).getApelido() + "#" + utilizadores.get(i).getContacto() + "#" + utilizadores.get(i).getTipo() + "#" + utilizadores.get(i).getHotel() + "#" + utilizadores.get(i).getConsumo() + "#" + utilizadores.get(i).getCont() + "#" + utilizadores.get(i).getSexo() + "#" + utilizadores.get(i).getNacionalidade() + "#" + utilizadores.get(i).getCivil() + "#" + utilizadores.get(i).getCheckin()+ "#" + utilizadores.get(i).getCheckout() + "#" + utilizadores.get(i).getProfilePic());
		}
		out.close();
	}
	
	public static void saveMensagens() throws FileNotFoundException {
		File file = new File("txtfiles\\mensagens.txt");		
		PrintWriter out = new PrintWriter(file);
		for (int i = 0; i < mensagens.size(); i++) {
			out.println(mensagens.get(i).getId_mensagem() + "#" + mensagens.get(i).getDest() + "#" + mensagens.get(i).getAssunto() + "#" + mensagens.get(i).getMensagem());
		}
		out.close();
	}
	
	public static void saveCompras() throws FileNotFoundException {
		File file = new File("txtfiles\\compras.txt");		
		PrintWriter out = new PrintWriter(file);
		for (int i = 0; i < compras.size(); i++) {
			compras.get(i).getId_compra();
			compras.get(i).getId_utilizador();
			compras.get(i).getData();
			compras.get(i).getProgramas();
			out.println(compras.get(i).getId_compra() + "#" + compras.get(i).getId_utilizador() + "#" + compras.get(i).getData() + "#" + compras.get(i).getProgramas());
		}
		out.close();
	}
	
	public static void savePacotes() throws FileNotFoundException {		
		File file = new File("txtfiles\\listaPacotes.txt");			
		PrintWriter out = new PrintWriter(file);
		for (int i = 0; i <programas.size(); i++) {
			out.println(programas.get(i).getId() + "#" + programas.get(i).getNome() + "#" + programas.get(i).getTipo() + "#" + programas.get(i).getDescricao() + "#" + programas.get(i).getPreco());
		}
		
		out.close();
		
	}
	
	public static void saveHoteis() throws FileNotFoundException {		
		
		File file = new File("txtfiles\\hoteis.txt");			
		PrintWriter out = new PrintWriter(file);
		for (int i = 0; i <hoteis.size(); i++) {
			out.println(hoteis.get(i).getIdentificador() + "#" + hoteis.get(i).getNome() + "#" + hoteis.get(i).getDescricao() + "#" + hoteis.get(i).getPisos() + "#" + hoteis.get(i).getQuartosPP() + "#" + hoteis.get(i).getImageLink() + "#" + hoteis.get(i).getExtras());
		}
		
		out.close();
		
	}
	
	public static void saveAll() throws FileNotFoundException {
		saveCompras();
		saveUtilizadores();
		savePacotes();
		saveHoteis();
		saveMensagens();
	}
	
	public static void loadUtilizadores() throws FileNotFoundException {
		File file = new File("txtfiles\\listaUtilizadores.txt");		
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] fields = line.split("#");
			utilizadores.add(new Utilizador(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4], Integer.parseInt(fields[5]), fields[6], fields[7], Double.parseDouble(fields[8]), Integer.parseInt(fields[9]), fields[10], fields[11], fields[12], fields[13], fields[14], fields[15]));
		}
		input.close();
	}
	
	public static void loadCompras() throws FileNotFoundException {
		File file = new File("txtfiles\\compras.txt");		
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] fields = line.split("#");
			compras.add(new Compra(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], fields[3]));
		}
		input.close();
	}
	
	public static void loadMensagens() throws FileNotFoundException {
		File file = new File("txtfiles\\mensagens.txt");		
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] fields = line.split("#");
			mensagens.add(new Mensagem(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]));
		}
		input.close();
	}
	
	public static void loadPacotes() throws FileNotFoundException {
		File file = new File("txtfiles\\listaPacotes.txt");			
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] fields = line.split("#");
			programas.add(new Programa(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], Double.parseDouble(fields[4])));
		}
		input.close();
	}
	
	public static void loadHoteis() throws FileNotFoundException {
		File file = new File("txtfiles\\hoteis.txt");			
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] fields = line.split("#");
			hoteis.add(new Hotel(fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), fields[5], fields[6]));
		}
		input.close();
	}
		
	public static void loadAll() throws FileNotFoundException {
		loadCompras();
		loadUtilizadores();
		loadPacotes();
		loadHoteis();
		loadMensagens();
	}
	
	public static Programa getPacote(int id) {
		int x = 0;
		for (int i = 0; i < programas.size(); i++) {
			if (id == programas.get(i).getId()) {
				x = i;
			}
		}
		return programas.get(x);
	}
	
	public static void comprarPacote (Utilizador util, Programa pacote) throws FileNotFoundException {	
		String dataCompra = getData();
		int maxId = -1;
		for (int i = 0; i < compras.size(); i++) {
			if (compras.get(i).getId_compra() >= maxId) {
				maxId = compras.get(i).getId_compra() + 1;
			}
		}
		
		compras.add(new Compra(maxId, util.getId(), dataCompra, Integer.toString(pacote.getId())));
		
		saveCompras();
		saveUtilizadores();
	}
	
	public static void escreverMensagem(String dest, String assunto, String mensagem) throws FileNotFoundException {
		int maxId = 0;
		for (int i = 0; i < mensagens.size(); i++) {
			if (mensagens.get(i).getId_mensagem() >= maxId) {
				maxId = mensagens.get(i).getId_mensagem() + 1;
			}
		}
	
		mensagens.add(new Mensagem(maxId, dest, assunto, mensagem));
		saveMensagens();
	}
	
	public static void eliminarMensagem(int id) throws FileNotFoundException {
		System.out.println(id);
		for (int i = 0; i < Gestor.getMensagens().size(); i++) {
			if (Gestor.getMensagens().get(i).getId_mensagem() == id) {
				System.out.println(i);
				mensagens.remove(i);
			}
		}
		Gestor.saveMensagens();
	}
	
	public static void removerPacote (int id) throws FileNotFoundException {
		for (int i = 0; i < Gestor.getProgramas().size(); i++) {
			if (id == Gestor.getProgramas().get(i).getId()) {
				programas.remove(i);
			}
		}
		Gestor.savePacotes();
		
	}
	
	public static int LogIn(String email, String password) {
		int numUtil = 0;
		for (int i = 0; i < utilizadores.size(); i++) {
			if (email.equals(utilizadores.get(i).getEmail()) && password.equals(utilizadores.get(i).getPassword())) {
				numUtil = utilizadores.get(i).getId();
			}
		}

		return numUtil;
		
	}
	
	public static int addPack(String nome, String tipo, String descricao, String preco) throws FileNotFoundException {
		int maxId = 0;
		String type = "CTV";
		for (int i = 0; i < programas.size(); i++) {
			if (programas.get(i).getId() >= maxId) {
				maxId = programas.get(i).getId() + 1;
			}
		}
		if (tipo.equals("Canais TV")) {
			type = "CTV";
		} else if (tipo.equals("Série")) {
			type = "SER";
		} else if (tipo.equals("Documentário")) {
			type = "DOC";
		} else if (tipo.equals("Piscina")) {
			type = "POOL";
		} else if (tipo.equals("Bar")) {
			type = "BAR";
		} else if (tipo.equals("Ginásio")) {
			type = "GYM";
		} else if (tipo.equals("Massagens")) {
			type = "MASS";
		}
		System.out.println(maxId);
		programas.add(new Programa(maxId, nome, type, descricao, Double.parseDouble(preco)));
		savePacotes();
		return maxId;

		
	}
	
	public static void addUser(String email, String password, String nome, String apelido, int contacto, String tipo, String hotel, double consumo, int contribuinte, String sexo, String nacionalidade, String civil, String checkIn, String checkOut, String profilePic) throws FileNotFoundException {
		int maxId = 0;
		for (int i = 0; i < utilizadores.size(); i++) {
			if (utilizadores.get(i).getId() > maxId) {
				maxId = utilizadores.get(i).getId();
			}
		}
		maxId += 1;
		
		utilizadores.add(new Utilizador(maxId, email, password, nome, apelido, contacto, tipo, hotel, consumo, contribuinte, sexo, nacionalidade, civil, checkIn, checkOut, profilePic));
		saveUtilizadores();
	
	}
	
	public static void addHotel(String nome, String descricao, String extras, int andares, int quartospp, String fotoP) throws FileNotFoundException {
		String idAnt = Gestor.getHoteis().get(Gestor.getHoteis().size() - 1).getIdentificador();
		int idVal = idAnt.charAt(0);
		String id = String.valueOf((char) (idVal + 1));
		String foto = "/hotelImages/" + fotoP; 
		hoteis.add(new Hotel(id, nome, descricao, andares, quartospp, foto, extras));
		Gestor.saveHoteis();
		
	}
	
	public static void editUser(int i, String email, String password, String nome, String apelido, int contacto, String tipo, int contribuinte, String sexo, String nacionalidade, String civil, String profilePic) throws FileNotFoundException {
		utilizadores.get(i).setNome(nome);
		utilizadores.get(i).setApelido(apelido);
		utilizadores.get(i).setEmail(email);
		utilizadores.get(i).setPassword(password);
		utilizadores.get(i).setTipo(tipo);
		utilizadores.get(i).setContacto(contacto);
		utilizadores.get(i).setCont(contribuinte);
		utilizadores.get(i).setSexo(sexo);
		utilizadores.get(i).setNacionalidade(nacionalidade);
		utilizadores.get(i).setCivil(civil);
		utilizadores.get(i).setProfilePic(profilePic);
		saveUtilizadores();
	
	}
	
	public static Hotel configHotel(String nome) {
		int temp = -1;
		for (int i = 0; i < hoteis.size(); i++) {
			if (hoteis.get(i).getNome().equals(nome)) {
				temp = i;
			}
		}
		
		return hoteis.get(temp);
	}
	
	public static String giveRoom (String hotel) {
		String id = Character.toString(hotel.charAt(0));
		String quarto = "";
		int andares = 0;
		int quartosPP = 0;
		
		
		
		for (int i = 0; i < hoteis.size(); i++) {
			if (id.equals(hoteis.get(i).getIdentificador())) {
				andares = hoteis.get(i).getPisos();
				quartosPP = hoteis.get(i).getQuartosPP();
			}
		}
		loop:
		for (int i = 0; i < andares * quartosPP; i++) {
			for (int x = 0; x < utilizadores.size(); x++) {
				String line = utilizadores.get(x).getHotel();
				String fields[] = line.split("_");
				if (fields[0].equals(id)) {
					if (Integer.parseInt(fields[1]) == i) {
						break;
					} else {
						quarto = id + "_" + i; 
						break loop;
					}
				}
			}
		}
		
		return quarto;
	}
		
	public static Hotel checkHotel(Utilizador user) {
		String ident = String.valueOf(user.getHotel().charAt(0));
		int temp = -1;
		for (int i = 0; i < hoteis.size(); i++) {
			if (ident.equals(hoteis.get(i).getIdentificador())) {
				temp = i;
			}
		}
		return hoteis.get(temp);
	}
	
	public static String getData() {
		
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
			
		String data = day + "-" + month + "-" + year;
		return data;
	}
	
	public static Utilizador setUser(int ID) {
		int temp = 0;
		for (int i = 0; i < utilizadores.size(); i++) {
			if (ID == utilizadores.get(i).getId()) {
				temp = i;
			}
		}
		return utilizadores.get(temp);
	}
	
	public static String[] setExtras(String extras) {
		String[] add = extras.split(",");
		for (int i = 0; i < add.length; i++) {
			add[i] = add[i].replaceAll("[^\\w]", "");
		}
		return add;
	}

}
