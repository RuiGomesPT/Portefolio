package projeto.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import projeto.model.Compra;
import projeto.model.Gestor;
import projeto.model.Hotel;
import projeto.model.Utilizador;

import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.ComboBoxModel;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, Bottom, Middle;
	private CardLayout cardLayoutB = new CardLayout(), cardLayoutM = new CardLayout();
	private Panel EditarAdmin, ConsultasAdmin, GestaoAdmin, ConfigAdmin, DivulgAdmin;
	private JLabel midDef;
	private String Menu;
	private JTextField txtNome;
	private JTextField txtAndares;
	private JTextField txtQuartosPorAndar;
	private JTextField txtImagePath;
	private JTextField textPacote;
	private JTextField textPreco;
	private JTable tabelaConteudo;
	static DefaultTableModel tableModel;
	static DefaultTableModel tableModelUtil;
	static DefaultTableModel tableModelConsumo;
	static DefaultTableModel tableModelMensagens;
	private JTable table;
	private int rowPick, rowPickCon, rowPickMen;
	private int id, idCon, idMen;
	private String resp;
	private JTable tabelaConsumo;
	private JTextField textQuarto;
	private JTextField textID;
	private JTextField txtId;
	private JTextField txtAssunto;
	private JTable tblMensagens;
	static JLabel lblYa = new JLabel();
	static JLabel lblConsumoTotal = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestor.loadAll();
					MenuPrincipal frame = new MenuPrincipal(Gestor.setUser(1));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal(Utilizador util) {
		
		
	
		Hotel hotelUtil = Gestor.checkHotel(util);
		
		

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel Top = new Panel();
		Top.setBackground(Color.GRAY);
		Top.setBounds(0, 0, 844, 110);
		contentPane.add(Top);
		Top.setLayout(null);

		JLabel lbllogo = new JLabel("");
		lbllogo.setBounds(29, 11, 239, 81);

		ImageIcon imagem = new ImageIcon(MenuPrincipal.class.getResource("/resources/Logotipo Oficial.png"));
		Image imag = imagem.getImage().getScaledInstance(lbllogo.getWidth(), lbllogo.getHeight(), Image.SCALE_DEFAULT);

		lbllogo.setIcon(new ImageIcon(imag));

		Top.add(lbllogo);

		JLabel lblNomeCliente = new JLabel(util.getNome() + " " + util.getApelido());
		lblNomeCliente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNomeCliente.setForeground(Color.WHITE);
		lblNomeCliente.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNomeCliente.setBounds(382, 50, 387, 22);
		Top.add(lblNomeCliente);

		JButton btnLogOut = new JButton("");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login frame = new Login();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/LOGOUTICON.png")));
		btnLogOut.setBounds(779, 42, 30, 30);
		Top.add(btnLogOut);

		Button btnHotel = new Button("Hotel");
		btnHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutB.show(Bottom, "BottomHotel");
			}
		});
		btnHotel.setForeground(new Color(102, 51, 0));
		btnHotel.setBackground(new Color(255, 165, 0));
		btnHotel.setBounds(689, 78, 121, 22);
		Top.add(btnHotel);

		Button btnMensag = new Button("Mensagens");
		btnMensag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomMensagens");
			}
		});
		btnMensag.setForeground(new Color(102, 51, 0));
		btnMensag.setBackground(new Color(255, 165, 0));
		btnMensag.setBounds(544, 78, 121, 22);
		Top.add(btnMensag);

		Button btnPerfil = new Button("Perfil");
		btnPerfil.setForeground(new Color(102, 51, 0));
		btnPerfil.setBackground(new Color(255, 165, 0));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomPerfil");
			}
		});
		btnPerfil.setBounds(400, 78, 121, 22);
		Top.add(btnPerfil);

		JLabel lblUtilHotel = new JLabel(hotelUtil.getNome());
		lblUtilHotel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUtilHotel.setForeground(Color.WHITE);
		lblUtilHotel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblUtilHotel.setBounds(277, 11, 532, 22);
		Top.add(lblUtilHotel);

		Middle = new JPanel();
		Middle.setBackground(new Color(255, 165, 0));
		Middle.setBounds(0, 109, 844, 25);
		contentPane.add(Middle);
		Middle.setLayout(cardLayoutM);

		JPanel MiddleDef = new JPanel();
		Middle.add(MiddleDef, "MiddleDef");
		MiddleDef.setLayout(null);
		MiddleDef.setBackground(new Color(255, 165, 0));

		midDef = new JLabel("Bem vindo ao Sistema de gest\u00E3o de entretenimento de H\u00F3teis");
		midDef.setBackground(Color.ORANGE);
		midDef.setHorizontalAlignment(SwingConstants.CENTER);
		midDef.setBounds(0, 0, 834, 25);
		MiddleDef.add(midDef);
		midDef.setForeground(new Color(139, 69, 19));
		midDef.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setMargin(new Insets(2, 2, 2, 2));
		menuBar.setBackground(new Color(139, 69, 19));
		menuBar.setBounds(0, 0, 211, 25);
		MiddleDef.add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setForeground(new Color(255, 255, 255));
		menuBar.add(mnMenu);
		
		JMenu mnHomepage = new JMenu("Atalhos");
		mnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, Menu);
			}
		});
		mnMenu.add(mnHomepage);
		
		JMenuItem menuRegistarEditar = new JMenuItem("Registar/Editar perfil");
		menuRegistarEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				cardLayoutB.show(Bottom, "BottomRegisto");
			}
		});
		
		JMenuItem menuHomePage = new JMenuItem("P\u00E1gina Principal");
		menuHomePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, Menu);
			}
		});
		mnHomepage.add(menuHomePage);
		mnHomepage.add(menuRegistarEditar);
		
		JMenuItem menuConsultas = new JMenuItem("Consultas");
		menuConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomConsultas");
			}
		});
		
		JMenuItem menuSubs = new JMenuItem("Subscri\u00E7\u00E3o de Cont\u00E9udos");
		menuSubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutB.show(Bottom, "BottomSubscrever");
			}
		});
		mnHomepage.add(menuSubs);
		mnHomepage.add(menuConsultas);
		
		JMenuItem menuConteudo = new JMenuItem("Gest\u00E3o de Cont\u00E9udos");
		menuConteudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomConteudo");
				refreshCont();
			}
		});
		
		JMenuItem menuConsumo = new JMenuItem("Consumo");
		menuConsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomConsumo");
			}
		});
		mnHomepage.add(menuConsumo);
		mnHomepage.add(menuConteudo);
		
		JMenuItem menuDivulgacoes = new JMenuItem("Divulga\u00E7\u00F5es");
		menuDivulgacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomDivulg");
			}
		});
		mnHomepage.add(menuDivulgacoes);
		
		JMenuItem menuConfiguracoes = new JMenuItem("Configura\u00E7\u00F5es");
		menuConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomConfig");
			}
		});
		mnHomepage.add(menuConfiguracoes);

		Bottom = new JPanel();
		Bottom.setBounds(0, 134, 844, 452);
		contentPane.add(Bottom);
		Bottom.setLayout(cardLayoutB);
		
		JPanel BottomRegisto = new JPanel();
		Bottom.add(BottomRegisto, "BottomRegisto");
		BottomRegisto.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(230, 109, 604, 289);
		BottomRegisto.add(scrollPane);
		
		String[] colunas = {"ID", "Nome", "Email", "Quarto", "Check-In", "Check-Out"};
		tableModelUtil = new DefaultTableModel(colunas, 0);
		table = new JTable(tableModelUtil);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowPick = table.getSelectedRow();
				id = (int) table.getValueAt(rowPick, 0);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Novo Utilizador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaRegisto PR = new PaginaRegisto(util.getTipo());
				PR.setVisible(true);
				System.out.println(id);
			}
		});
		btnNewButton.setBounds(10, 109, 210, 49);
		BottomRegisto.add(btnNewButton);
		
		JComboBox comboUsers = new JComboBox();
		comboUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTableGestao(comboUsers.getSelectedItem().toString(), resp);
			}
		});
		
		JCheckBox chckAdmin = new JCheckBox("Filtrar Administracao");
		chckAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckAdmin.isSelected()) {
					resp = "filtAdmin";
				} else {
					resp = "nofiltAdmin";
				}
				refreshTableGestao(comboUsers.getSelectedItem().toString(), resp);
			}
		});
		chckAdmin.setSelected(true);
		chckAdmin.setBounds(230, 75, 172, 23);
		BottomRegisto.add(chckAdmin);
		
		resp = "filtAdmin";

		
		
		
		JButton btnAlterarQuarto = new JButton("Alterar Quarto");
		btnAlterarQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuartoHotel QH = new QuartoHotel(Gestor.getUtilizadores().get(id-1));
				QH.setVisible(true);				
			}
		});
		btnAlterarQuarto.setBounds(10, 349, 210, 49);
		BottomRegisto.add(btnAlterarQuarto);
		
		JButton btnEditarDadosDe = new JButton("Editar dados de Utilizador");
		btnEditarDadosDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaEdicao PE = new PaginaEdicao(util, Gestor.getUtilizadores().get(id-1));
				PE.setVisible(true);
			}
		});
		btnEditarDadosDe.setBounds(10, 169, 210, 49);
		BottomRegisto.add(btnEditarDadosDe);
		
		
		
		JButton btnEfectuarCheckin = new JButton("Efectuar Checkin");
		btnEfectuarCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Gestor.getUtilizadores().get(id - 1).getCheckin().equals("-") && Gestor.getUtilizadores().get(id - 1).getCheckout().equals("-")) {
					JOptionPane.showMessageDialog(null, "Impossivel efectuar Checkin ourta vez antes de fazer checkout");
				} else {
					String checkin = Gestor.getData();
					Gestor.getUtilizadores().get(id - 1).setCheckin(checkin);
					Gestor.getUtilizadores().get(id - 1).setCheckout("-");
					JOptionPane.showMessageDialog(null, "Checkin efectuado com sucesso");
					try {
						Gestor.saveUtilizadores();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					refreshTableGestao(comboUsers.getSelectedItem().toString(), resp);
				}	
			}
		});
		btnEfectuarCheckin.setBounds(10, 229, 210, 49);
		BottomRegisto.add(btnEfectuarCheckin);
		
		JButton btnEfectuarCheckout = new JButton("Efectuar Checkout");
		btnEfectuarCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Gestor.getUtilizadores().get(id - 1).getCheckin().equals("-")) {
					JOptionPane.showMessageDialog(null, "Impossivel efectuar Checkout sem ter feito checkin anteriormente");
				} else {
					String checkout = Gestor.getData();
					Gestor.getUtilizadores().get(id - 1).setCheckout(checkout);
					JOptionPane.showMessageDialog(null, "Checkout feito com sucesso");
					try {
						Gestor.saveUtilizadores();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					refreshTableGestao(comboUsers.getSelectedItem().toString(), resp);
				}
				
			}
		});
		btnEfectuarCheckout.setBounds(10, 289, 210, 49);
		BottomRegisto.add(btnEfectuarCheckout);
		
		List<String> arrayUsers = new ArrayList<String>();
		arrayUsers.add("TODOS");
		for (int i = 0; i < Gestor.getHoteis().size(); i++) {
			arrayUsers.add(Gestor.getHoteis().get(i).getNome());
		}
		
		
		comboUsers.setModel(new DefaultComboBoxModel(arrayUsers.toArray()));
		comboUsers.setBounds(582, 74, 252, 20);
		BottomRegisto.add(comboUsers);
		
		JLabel lblFiltrarPorHotel = new JLabel("Filtrar por Hotel");
		lblFiltrarPorHotel.setBounds(426, 77, 146, 14);
		BottomRegisto.add(lblFiltrarPorHotel);
		
		JLabel lblGestoDeUtilizador = new JLabel("Gest\u00E3o de Utilizadores");
		lblGestoDeUtilizador.setForeground(new Color(139, 69, 19));
		lblGestoDeUtilizador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGestoDeUtilizador.setBounds(320, 18, 209, 25);
		BottomRegisto.add(lblGestoDeUtilizador);
		
		
		refreshTableGestao("TODOS", resp);
		
		
		JPanel BottomDivulg = new JPanel();
		Bottom.add(BottomDivulg, "BottomDivulg");
		BottomDivulg.setLayout(null);
		
		JLabel label = new JLabel("Divulga\u00E7\u00F5es");
		label.setForeground(new Color(139, 69, 19));
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(363, 11, 101, 25);
		BottomDivulg.add(label);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setBounds(24, 57, 54, 14);
		BottomDivulg.add(lblId_1);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(92, 54, 54, 20);
		BottomDivulg.add(txtId);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 114, 778, 181);
		BottomDivulg.add(scrollPane_1);
		
		JTextPane txtMensagem = new JTextPane();
		scrollPane_1.setViewportView(txtMensagem);
		
		txtAssunto = new JTextField();
		txtAssunto.setColumns(10);
		txtAssunto.setBounds(92, 89, 710, 20);
		BottomDivulg.add(txtAssunto);
		
		JLabel label_8 = new JLabel("Assunto");
		label_8.setBounds(24, 92, 54, 14);
		BottomDivulg.add(label_8);
		
		
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBackground(new Color(139, 69, 19));
		panel.setBounds(24, 360, 778, 82);
		BottomDivulg.add(panel);
		
		JLabel lblEnviar = new JLabel("Enviar mensagem");
		lblEnviar.setForeground(Color.WHITE);
		lblEnviar.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblEnviar.setBounds(10, 0, 225, 37);
		panel.add(lblEnviar);
		
		JLabel lblEnvieASua = new JLabel("Envie a sua mensagem");
		lblEnvieASua.setForeground(new Color(255, 140, 0));
		lblEnvieASua.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEnvieASua.setBounds(10, 48, 117, 25);
		panel.add(lblEnvieASua);
		
		
		
		JRadioButton rdnTodos = new JRadioButton("Todos");
		rdnTodos.setBounds(152, 53, 89, 23);
		BottomDivulg.add(rdnTodos);
		
		JButton btnMensagem = new JButton("Nova Mensagem");
		btnMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdnTodos.setSelected(false);
				txtId.setText("");
				txtAssunto.setText("");
				txtMensagem.setText("");
			}
		});
		btnMensagem.setBounds(668, 52, 134, 25);
		BottomDivulg.add(btnMensagem);
		
		JButton btnEnviar = new JButton("");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "";
				if (rdnTodos.isSelected()) {
					id = "TODOS";
				} else if (!txtId.getText().equals("")){
					id = txtId.getText();
				} 
				
				if (!id.equals("")) {
					String assunto = txtAssunto.getText();
					String mensagem = txtMensagem.getText();
					try {
						Gestor.escreverMensagem(id, assunto, mensagem);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso");
					refreshMensagens(Integer.toString(util.getId()), "");
				} else {
					JOptionPane.showMessageDialog(null, "Insira um destinatario");
				}
				
			}
			
		});
		btnEnviar.setBounds(704, 6, 70, 70);
		
		ImageIcon imagemEnv = new ImageIcon(MenuPrincipal.class.getResource("/resources/mailIcon.png"));
		Image iconEnv = imagemEnv.getImage().getScaledInstance(btnEnviar.getWidth(), btnEnviar.getHeight(), Image.SCALE_DEFAULT);
		btnEnviar.setIcon(new ImageIcon(iconEnv));
		panel.add(btnEnviar);
		
		JPanel BottomConsultas = new JPanel();
		Bottom.add(BottomConsultas, "BottomConsultas");
		BottomConsultas.setLayout(null);
		
		List<String> arrayUtil = new ArrayList<String>();
		
		for (int i = 0; i < Gestor.getUtilizadores().size(); i++) {
			arrayUtil.add(Integer.toString(Gestor.getUtilizadores().get(i).getId()));
		}
		
		String[] colunasConsumo = {"ID do Produto", "Nome", "Tipo", "Data da Compra", "Preco"};
		tableModelConsumo = new DefaultTableModel(colunasConsumo, 0);
		tabelaConsumo = new JTable(tableModelConsumo);
		tabelaConsumo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowPick = table.getSelectedRow();
				id = (int) table.getValueAt(rowPick, 0);
			}
		});
		
		refreshTableConsumo(util.getId());
		
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setBounds(10, 42, 812, 348);
		BottomConsultas.add(scrollTable);
		
		tabelaConsumo = new JTable(tableModelConsumo);
		scrollTable.setViewportView(tabelaConsumo);
		
		JLabel lblConsumo = new JLabel("Consumo Total:");
		lblConsumo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsumo.setBounds(634, 401, 106, 26);
		BottomConsultas.add(lblConsumo);
		
		lblConsumoTotal = new JLabel("");
		lblConsumoTotal.setText(Double.toString(util.getConsumo()) + " euros");
		lblConsumoTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsumoTotal.setBounds(750, 401, 72, 26);
		BottomConsultas.add(lblConsumoTotal);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(592, 5, 23, 26);
		BottomConsultas.add(lblId);
		
		JLabel lblQt = new JLabel("Quarto:");
		lblQt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQt.setBounds(413, 5, 57, 26);
		BottomConsultas.add(lblQt);
		
		textQuarto = new JTextField();
		textQuarto.setBounds(480, 11, 78, 20);
		BottomConsultas.add(textQuarto);
		textQuarto.setColumns(10);
		
		textID = new JTextField();
		textID.setColumns(10);
		textID.setBounds(625, 11, 78, 20);
		BottomConsultas.add(textID);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textQuarto.getText().equals("")) {
					for (int i = 0; i < Gestor.getUtilizadores().size(); i++) {
						if (Gestor.getUtilizadores().get(i).getId() == Integer.parseInt(textID.getText())) {
							refreshTableConsumo(Gestor.getUtilizadores().get(i).getId());
							lblConsumoTotal.setText(Double.toString(Gestor.getUtilizadores().get(i).getConsumo()));
						}
					}
				} else {
					for (int i = 0; i < Gestor.getUtilizadores().size(); i++) {
						if (Gestor.getUtilizadores().get(i).getHotel().equals(textQuarto.getText())) {
							refreshTableConsumo(Gestor.getUtilizadores().get(i).getId());
							lblConsumoTotal.setText(Double.toString(Gestor.getUtilizadores().get(i).getConsumo()));
						}
					}
				}
				
			}
		});
		btnFiltrar.setBounds(713, 8, 109, 23);
		BottomConsultas.add(btnFiltrar);

		JPanel BottomRec = new JPanel();
		Bottom.add(BottomRec, "BottomRec");
		BottomRec.setLayout(null);

		Panel panel_7 = new Panel();
		panel_7.setLayout(null);
		panel_7.setBackground(new Color(102, 51, 0));
		panel_7.setBounds(26, 307, 787, 135);
		BottomRec.add(panel_7);

		JLabel label_11 = new JLabel("Aqui mant\u00EAns os teus h\u00F3spedes informados");
		label_11.setForeground(new Color(255, 140, 0));
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_11.setBounds(10, 64, 246, 25);
		panel_7.add(label_11);

		JLabel label_12 = new JLabel("Divulga\u00E7\u00F5es");
		label_12.setForeground(Color.WHITE);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_12.setBounds(10, 11, 479, 37);
		panel_7.add(label_12);

		JButton btnDivulgaRec = new JButton("");
		btnDivulgaRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomDivulg");
			}
		});
		btnDivulgaRec.setBounds(663, 11, 114, 114);
		btnDivulgaRec.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/Icon7.png")));
		panel_7.add(btnDivulgaRec);

		Panel panel_9 = new Panel();
		panel_9.setLayout(null);
		panel_9.setBackground(new Color(139, 69, 19));
		panel_9.setBounds(26, 166, 787, 135);
		BottomRec.add(panel_9);

		JLabel label_15 = new JLabel("Consultas");
		label_15.setForeground(Color.WHITE);
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_15.setBounds(10, 11, 182, 37);
		panel_9.add(label_15);

		JLabel label_16 = new JLabel("Aqui podes consultar os conte\u00FAdos subscritos por h\u00F3spedes");
		label_16.setForeground(new Color(255, 140, 0));
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_16.setBounds(10, 64, 294, 25);
		panel_9.add(label_16);

		JButton btnConsultaRec = new JButton("");
		btnConsultaRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutB.show(Bottom, "BottomConsultas");
			}
		});
		btnConsultaRec.setBounds(662, 11, 114, 114);
		btnConsultaRec.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/icon consulta.png")));
		panel_9.add(btnConsultaRec);

		Panel panel_11 = new Panel();
		panel_11.setLayout(null);
		panel_11.setBackground(new Color(102, 51, 0));
		panel_11.setBounds(26, 25, 787, 135);
		BottomRec.add(panel_11);

		JLabel lblRegistarNovosClientes = new JLabel("Registe clientes no sistema ou edite os seus perfis");
		lblRegistarNovosClientes.setForeground(new Color(255, 140, 0));
		lblRegistarNovosClientes.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRegistarNovosClientes.setBounds(11, 63, 276, 25);
		panel_11.add(lblRegistarNovosClientes);

		JLabel lblRegistareditarPerfis = new JLabel("Registar/Editar Perfis");
		lblRegistareditarPerfis.setForeground(Color.WHITE);
		lblRegistareditarPerfis.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblRegistareditarPerfis.setBounds(11, 10, 376, 37);
		panel_11.add(lblRegistareditarPerfis);

		JButton btnRegistoRec = new JButton("");
		btnRegistoRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutB.show(Bottom, "BottomRegisto");
			}
		});
		btnRegistoRec.setBounds(663, 10, 114, 114);
		btnRegistoRec.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/icon5.png")));
		panel_11.add(btnRegistoRec);

		JPanel BottomCliente = new JPanel();
		BottomCliente.setLayout(null);
		Bottom.add(BottomCliente, "BottomCliente");

		Panel ConsultasCliente = new Panel();
		ConsultasCliente.setLayout(null);
		ConsultasCliente.setBackground(new Color(139, 69, 19));
		ConsultasCliente.setBounds(26, 151, 787, 135);
		BottomCliente.add(ConsultasCliente);

		JLabel label_5 = new JLabel("Consultas");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_5.setBounds(10, 0, 182, 37);
		ConsultasCliente.add(label_5);

		JLabel lblConsultarOSeu = new JLabel("Ver informa\u00E7\u00F5es");
		lblConsultarOSeu.setForeground(new Color(255, 140, 0));
		lblConsultarOSeu.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblConsultarOSeu.setBounds(10, 48, 294, 25);
		ConsultasCliente.add(lblConsultarOSeu);

		JButton btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutB.show(Bottom, "BottomConsultas");
			}
		});
		
		
		btnConsultar.setBounds(664, 11, 113, 113);
		ConsultasCliente.add(btnConsultar);

		btnConsultar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/icon consulta.png")));
		
		Panel GestaoCliente = new Panel();
		GestaoCliente.setLayout(null);
		GestaoCliente.setBackground(new Color(153, 102, 51));
		GestaoCliente.setBounds(26, 292, 787, 135);
		BottomCliente.add(GestaoCliente);

		JLabel label_7 = new JLabel("Aqui geres os conte\u00FAdos e gastos dos h\u00F3spedes");
		label_7.setForeground(new Color(255, 140, 0));
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_7.setBounds(10, 48, 246, 25);
		GestaoCliente.add(label_7);

		JLabel lblGerirOSeu = new JLabel("Gerir o seu Consumo");
		lblGerirOSeu.setForeground(Color.WHITE);
		lblGerirOSeu.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGerirOSeu.setBounds(10, 0, 479, 37);
		GestaoCliente.add(lblGerirOSeu);

		JButton btnGerir = new JButton("   ");
		btnGerir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomConsumo");
			}
		});
		btnGerir.setBounds(664, 11, 113, 113);
		GestaoCliente.add(btnGerir);
		btnGerir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/icon4.png")));

		Panel SubCliente = new Panel();
		SubCliente.setLayout(null);
		SubCliente.setBackground(new Color(102, 51, 0));
		SubCliente.setBounds(26, 10, 787, 135);
		BottomCliente.add(SubCliente);

		JLabel lblSubscreverAosContedos = new JLabel("Subscreva a diferentes fontes de entertenimento");
		lblSubscreverAosContedos.setForeground(new Color(255, 140, 0));
		lblSubscreverAosContedos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSubscreverAosContedos.setBounds(11, 44, 276, 25);
		SubCliente.add(lblSubscreverAosContedos);

		JLabel lblSubscrioDeContedos_1 = new JLabel("Subscri\u00E7\u00E3o de Conte\u00FAdos");
		lblSubscrioDeContedos_1.setForeground(Color.WHITE);
		lblSubscrioDeContedos_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblSubscrioDeContedos_1.setBounds(11, 0, 376, 37);
		SubCliente.add(lblSubscrioDeContedos_1);

		JButton btnSubscrever = new JButton("");
		btnSubscrever.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomSubscrever");
			}
		});
		btnSubscrever.setBounds(664, 11, 113, 113);
		btnSubscrever.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/Icon filmes.png")));
		SubCliente.add(btnSubscrever);

		JPanel BottomConteudo = new JPanel();
		Bottom.add(BottomConteudo, "BottomConteudo");
		BottomConteudo.setLayout(null);

		Panel panelRemover = new Panel();
		panelRemover.setLayout(null);
		panelRemover.setBackground(new Color(255, 165, 0));
		panelRemover.setBounds(10, 360, 400, 82);
		BottomConteudo.add(panelRemover);

		JLabel lblGravar = new JLabel("Remover Pacote");
		lblGravar.setForeground(new Color(0, 0, 0));
		lblGravar.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblGravar.setBounds(10, 0, 280, 37);
		panelRemover.add(lblGravar);

		JLabel lblCrieEditeOu = new JLabel("Remova Pacotes");
		lblCrieEditeOu.setForeground(new Color(0, 0, 0));
		lblCrieEditeOu.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCrieEditeOu.setBounds(10, 48, 294, 25);
		panelRemover.add(lblCrieEditeOu);

		JButton btnRemove = new JButton("");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gestor.removerPacote(idCon);
					refreshCont();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRemove.setBounds(320, 6, 70, 70);
		panelRemover.add(btnRemove);
		
		ImageIcon imagemRemove = new ImageIcon(MenuPrincipal.class.getResource("/resources/minusIcon.png"));
		Image iconRem = imagemRemove.getImage().getScaledInstance(btnRemove.getWidth(), btnRemove.getHeight(), Image.SCALE_DEFAULT);
		btnRemove.setIcon(new ImageIcon(iconRem));
		
		JPanel panelCont = new JPanel();
		panelCont.setBackground(new Color(139, 69, 19));
		panelCont.setBounds(10, 11, 400, 343);
		BottomConteudo.add(panelCont);
		panelCont.setLayout(null);
		
		JScrollPane scrollConteudo = new JScrollPane();
		scrollConteudo.setBounds(10, 11, 380, 321);
		panelCont.add(scrollConteudo);
		String[] columns = {"ID", "Nome", "Tipo", "Preco"};
		tableModel = new DefaultTableModel(columns, 0);
		tabelaConteudo = new JTable(tableModel);
		tabelaConteudo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rowPickCon = tabelaConteudo.getSelectedRow();
				idCon = (int) tabelaConteudo.getValueAt(rowPickCon, 0);
			}
		});
		scrollConteudo.setViewportView(tabelaConteudo);
		
		JPanel panelPack = new JPanel();
		panelPack.setLayout(null);
		panelPack.setBackground(new Color(255, 165, 0));
		panelPack.setBounds(434, 11, 400, 342);
		BottomConteudo.add(panelPack);
		
		JLabel label_13 = new JLabel("Adicionar Pacotes");
		label_13.setBounds(10, 11, 142, 14);
		panelPack.add(label_13);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(10, 36, 48, 14);
		panelPack.add(lblNome_1);
		
		textPacote = new JTextField();
		textPacote.setColumns(10);
		textPacote.setBounds(68, 33, 324, 20);
		panelPack.add(textPacote);
		
		JLabel label_18 = new JLabel("Pre\u00E7o");
		label_18.setBounds(299, 67, 34, 14);
		panelPack.add(label_18);
		
		textPreco = new JTextField();
		textPreco.setColumns(10);
		textPreco.setBounds(340, 64, 48, 20);
		panelPack.add(textPreco);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(102, 67, 46, 14);
		panelPack.add(lblTipo);
	
		Hotel hotelConteudo = new Hotel();
		JComboBox comboTipo = new JComboBox();
		comboTipo.addItem("Canais TV");
		comboTipo.addItem("Série");	
		comboTipo.addItem("Documentário");
		comboTipo.addItem("Bar");
		comboTipo.addItem("Ginásio");
		comboTipo.addItem("Piscina");
		comboTipo.addItem("Massagens");	
		comboTipo.setBounds(157, 65, 133, 20);
		panelPack.add(comboTipo);
		
		JTextPane txtDescricaoProduto = new JTextPane();
		txtDescricaoProduto.setBounds(10, 123, 380, 208);
		panelPack.add(txtDescricaoProduto);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(10, 103, 77, 14);
		panelPack.add(lblDescrio);
		
		Panel panelAdd = new Panel();
		panelAdd.setLayout(null);
		panelAdd.setBackground(new Color(139, 69, 19));
		panelAdd.setBounds(434, 360, 400, 82);
		BottomConteudo.add(panelAdd);
		
		JLabel AddLbl = new JLabel("Adicionar Pacote");
		AddLbl.setForeground(Color.WHITE);
		AddLbl.setFont(new Font("Tahoma", Font.PLAIN, 28));
		AddLbl.setBounds(10, 0, 225, 37);
		panelAdd.add(AddLbl);
		
		JLabel lblCrieOuEdite = new JLabel("Adicione novos Pacotes");
		lblCrieOuEdite.setForeground(new Color(255, 140, 0));
		lblCrieOuEdite.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCrieOuEdite.setBounds(10, 48, 294, 25);
		panelAdd.add(lblCrieOuEdite);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomePacote = textPacote.getText();
				String tipoPacote = comboTipo.getSelectedItem().toString();
				String precoPacote = textPreco.getText();
				String descricaoPacote = txtDescricaoProduto.getText();
				try {
					Gestor.addPack(nomePacote, tipoPacote, descricaoPacote, precoPacote);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
						
				refreshCont();
				
			}
		});
		
		
		btnAdd.setBounds(320, 6, 70, 70);
		ImageIcon imagemAdd = new ImageIcon(MenuPrincipal.class.getResource("/resources/addIcon.png"));
		Image iconAdd = imagemAdd.getImage().getScaledInstance(btnAdd.getWidth(), btnAdd.getHeight(), Image.SCALE_DEFAULT);
		btnAdd.setIcon(new ImageIcon(iconAdd));
		panelAdd.add(btnAdd);

		JPanel BottomAdmin = new JPanel();
		Bottom.add(BottomAdmin, "BottomAdministrador");
		BottomAdmin.setLayout(null);

		DivulgAdmin = new Panel();
		DivulgAdmin.setBounds(26, 268, 787, 80);
		BottomAdmin.add(DivulgAdmin);
		DivulgAdmin.setLayout(null);
		DivulgAdmin.setBackground(new Color(102, 51, 0));

		JLabel lblAquiMantnsOs = new JLabel("Aqui mant\u00EAns os teus h\u00F3spedes informados");
		lblAquiMantnsOs.setForeground(new Color(255, 140, 0));
		lblAquiMantnsOs.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAquiMantnsOs.setBounds(10, 48, 246, 25);
		DivulgAdmin.add(lblAquiMantnsOs);

		JLabel lblDivulgaes = new JLabel("Divulga\u00E7\u00F5es");
		lblDivulgaes.setForeground(Color.WHITE);
		lblDivulgaes.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblDivulgaes.setBounds(10, 0, 479, 37);
		DivulgAdmin.add(lblDivulgaes);

		JButton btnDivulg = new JButton("");
		btnDivulg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomDivulg");
			}
		});
		btnDivulg.setBounds(716, 9, 61, 61);
		DivulgAdmin.add(btnDivulg);
		btnDivulg.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/Icon7.png")));

		ConfigAdmin = new Panel();
		ConfigAdmin.setBounds(26, 354, 787, 80);
		BottomAdmin.add(ConfigAdmin);
		ConfigAdmin.setLayout(null);
		ConfigAdmin.setBackground(new Color(139, 69, 19));

		JLabel lblAquiFazesAs = new JLabel("Aqui fazes as modifica\u00E7\u00F5es necess\u00E1rias");
		lblAquiFazesAs.setForeground(new Color(255, 140, 0));
		lblAquiFazesAs.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAquiFazesAs.setBounds(10, 48, 246, 25);
		ConfigAdmin.add(lblAquiFazesAs);

		JLabel lblConfiguraes = new JLabel("Configura\u00E7\u00F5es de Sistema");
		lblConfiguraes.setForeground(Color.WHITE);
		lblConfiguraes.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblConfiguraes.setBounds(10, 0, 479, 37);
		ConfigAdmin.add(lblConfiguraes);

		JButton btnConfig = new JButton("");
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutB.show(Bottom, "BottomConfig");
			}
		});
		btnConfig.setBounds(716, 9, 61, 61);
		ConfigAdmin.add(btnConfig);
		btnConfig.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/Icon6.png")));

		ConsultasAdmin = new Panel();
		ConsultasAdmin.setBounds(26, 96, 787, 80);
		BottomAdmin.add(ConsultasAdmin);
		ConsultasAdmin.setLayout(null);
		ConsultasAdmin.setBackground(new Color(139, 69, 19));

		JLabel lblConsultas = new JLabel("Consultas");
		lblConsultas.setForeground(Color.WHITE);
		lblConsultas.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblConsultas.setBounds(10, 0, 182, 37);
		ConsultasAdmin.add(lblConsultas);

		JLabel lblAquiPodesConsultar = new JLabel(
				"Aqui podes consultar os conte\u00FAdos subscritos por h\u00F3spedes");
		lblAquiPodesConsultar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAquiPodesConsultar.setForeground(new Color(255, 140, 0));
		lblAquiPodesConsultar.setBounds(10, 48, 294, 25);
		ConsultasAdmin.add(lblAquiPodesConsultar);

		JButton btnConsulta = new JButton("");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomConsultas");
			}
		});
		btnConsulta.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/icon consulta.png")));
		btnConsulta.setBounds(716, 9, 61, 61);
		ConsultasAdmin.add(btnConsulta);

		GestaoAdmin = new Panel();
		GestaoAdmin.setBounds(26, 182, 787, 80);
		BottomAdmin.add(GestaoAdmin);
		GestaoAdmin.setLayout(null);
		GestaoAdmin.setBackground(new Color(153, 102, 51));

		JLabel lblAquiVerificasQuanto = new JLabel("Aqui pode gerir os conteudos de entertenimento disponiveis no Hotel");
		lblAquiVerificasQuanto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAquiVerificasQuanto.setForeground(new Color(255, 140, 0));
		lblAquiVerificasQuanto.setBounds(10, 48, 329, 25);
		GestaoAdmin.add(lblAquiVerificasQuanto);

		JLabel lblSubscrioDeContedos = new JLabel("Configura\u00E7\u00F5es de Cont\u00E9udo");
		lblSubscrioDeContedos.setBounds(10, 0, 479, 37);
		GestaoAdmin.add(lblSubscrioDeContedos);
		lblSubscrioDeContedos.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblSubscrioDeContedos.setForeground(new Color(255, 255, 255));

		JButton btnConteudos = new JButton("");
		btnConteudos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomConteudo");
				refreshCont();
			}
		});
		btnConteudos.setBounds(716, 9, 61, 61);
		GestaoAdmin.add(btnConteudos);
		btnConteudos.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/iconcalculadora.png")));

		EditarAdmin = new Panel();
		EditarAdmin.setBounds(26, 10, 787, 80);
		BottomAdmin.add(EditarAdmin);
		EditarAdmin.setBackground(new Color(102, 51, 0));
		EditarAdmin.setLayout(null);

		JLabel lblNewLabel = new JLabel("Registe clientes no sistema ou edite os seus perfis");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setBounds(11, 44, 276, 25);
		EditarAdmin.add(lblNewLabel);

		JLabel lblConsumos = new JLabel("Registar/Editar Perfis");
		lblConsumos.setBounds(11, 0, 376, 37);
		EditarAdmin.add(lblConsumos);
		lblConsumos.setForeground(Color.WHITE);
		lblConsumos.setFont(new Font("Tahoma", Font.PLAIN, 28));

		JButton btnRegisto = new JButton("");
		btnRegisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutB.show(Bottom, "BottomRegisto");
			}
		});
		btnRegisto.setBounds(716, 9, 61, 61);
		EditarAdmin.add(btnRegisto);
		btnRegisto.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/resources/icon5.png")));

		JPanel BottomPerf = new JPanel();
		Bottom.add(BottomPerf, "BottomPerfil");
		BottomPerf.setLayout(null);

		JLabel lblNome = new JLabel("Nome Completo:");
		lblNome.setBounds(300, 95, 142, 14);
		BottomPerf.add(lblNome);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(300, 130, 142, 14);
		BottomPerf.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setBounds(300, 165, 142, 14);
		BottomPerf.add(lblContacto);
		lblContacto.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblDataCheckin = new JLabel("Data Check-in:");
		lblDataCheckin.setBounds(300, 200, 142, 14);
		BottomPerf.add(lblDataCheckin);
		lblDataCheckin.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblDataCheckout = new JLabel("Data Check-out:");
		lblDataCheckout.setBounds(300, 235, 142, 14);
		BottomPerf.add(lblDataCheckout);
		lblDataCheckout.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel txtContacto = new JLabel();
		txtContacto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtContacto.setText(Integer.toString(util.getContacto()));
		txtContacto.setBounds(460, 165, 143, 20);
		BottomPerf.add(txtContacto);

		JLabel lblNQuarto = new JLabel("N\u00BA Quarto:");
		lblNQuarto.setBounds(300, 60, 142, 14);
		BottomPerf.add(lblNQuarto);
		lblNQuarto.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblQuarto = new JLabel(util.getHotel().substring(2, util.getHotel().length()));
		lblQuarto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuarto.setBounds(460, 60, 143, 14);
		BottomPerf.add(lblQuarto);

		JLabel lblNomeC = new JLabel(util.getNome() + " " + util.getApelido());
		lblNomeC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNomeC.setBounds(460, 95, 143, 14);
		BottomPerf.add(lblNomeC);

		JLabel txtEmail = new JLabel();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setText(util.getEmail());
		txtEmail.setBounds(460, 130, 143, 20);
		BottomPerf.add(txtEmail);

		JLabel lblCheckin = new JLabel(util.getCheckin());
		lblCheckin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCheckin.setBounds(460, 200, 143, 14);
		BottomPerf.add(lblCheckin);

		JLabel lblCheckout = new JLabel(util.getCheckout());
		lblCheckout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCheckout.setBounds(460, 235, 143, 14);
		BottomPerf.add(lblCheckout);

		JLabel lblNHotel = new JLabel("Hotel:");
		lblNHotel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNHotel.setBounds(300, 25, 142, 14);
		BottomPerf.add(lblNHotel);

		JLabel lblHotelCliente = new JLabel(hotelUtil.getNome());
		lblHotelCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHotelCliente.setBounds(460, 25, 143, 14);
		BottomPerf.add(lblHotelCliente);

		JLabel lblContribuinte = new JLabel("N\u00BA Contribuinte:");
		lblContribuinte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContribuinte.setBounds(300, 270, 142, 14);
		BottomPerf.add(lblContribuinte);

		JLabel lblNCont = new JLabel(Integer.toString(util.getCont()));
		lblNCont.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNCont.setBounds(460, 270, 97, 14);
		BottomPerf.add(lblNCont);

		JLabel lblNacionalidade = new JLabel("Nacionalidade:");
		lblNacionalidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNacionalidade.setBounds(300, 305, 142, 14);
		BottomPerf.add(lblNacionalidade);

		JLabel lblNac = new JLabel(util.getNacionalidade());
		lblNac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNac.setBounds(460, 305, 97, 14);
		BottomPerf.add(lblNac);

		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstadoCivil.setBounds(300, 340, 142, 14);
		BottomPerf.add(lblEstadoCivil);

		JLabel lblCivil = new JLabel(util.getCivil());
		lblCivil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCivil.setBounds(460, 340, 97, 14);
		BottomPerf.add(lblCivil);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSexo.setBounds(300, 375, 142, 14);
		BottomPerf.add(lblSexo);

		JLabel lblGenero = new JLabel(util.getSexo());
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenero.setBounds(460, 375, 97, 14);
		BottomPerf.add(lblGenero);

		JLabel fotoPerfil = new JLabel("");
		fotoPerfil.setBounds(10, 27, 200, 250);
		BottomPerf.add(fotoPerfil);

		ImageIcon fotoPerfilImg = new ImageIcon(MenuPrincipal.class.getResource(util.getProfilePic()));
		Image fotoIcon = fotoPerfilImg.getImage().getScaledInstance(fotoPerfil.getWidth(), fotoPerfil.getHeight(),
				Image.SCALE_DEFAULT);

		fotoPerfil.setIcon(new ImageIcon(fotoIcon));

		JPanel BottomHotel = new JPanel();
		Bottom.add(BottomHotel, "BottomHotel");
		BottomHotel.setBorder(null);
		BottomHotel.setLayout(null);

		JLabel nomeLbl = new JLabel("Nome:");
		nomeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nomeLbl.setBounds(332, 42, 64, 25);
		BottomHotel.add(nomeLbl);

		JTextField nomeTxt = new JTextField(hotelUtil.getNome());
		nomeTxt.setEditable(false);
		nomeTxt.setColumns(10);
		nomeTxt.setBounds(416, 46, 343, 20);
		BottomHotel.add(nomeTxt);

		JLabel txtDesc = new JLabel("Descri\u00E7\u00E3o:");
		txtDesc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDesc.setBounds(332, 78, 74, 25);
		BottomHotel.add(txtDesc);

		Boolean POOL = false;
		Boolean GYM = false;
		Boolean BAR = false;
		Boolean MASS = false;

		if (hotelUtil.getExtras().contains("BAR")) {
			BAR = true;
		}

		if (hotelUtil.getExtras().contains("POOL")) {
			POOL = true;
		}

		if (hotelUtil.getExtras().contains("GYM")) {
			GYM = true;
		}

		if (hotelUtil.getExtras().contains("MASS")) {
			MASS = true;
		}

		JCheckBox BarChck = new JCheckBox("Bar");
		BarChck.setEnabled(false);

		if (BAR == true) {
			BarChck.setSelected(true);
		} else {
			BarChck.setSelected(false);
		}

		BarChck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BarChck.setBounds(416, 274, 109, 23);
		BottomHotel.add(BarChck);

		JCheckBox PiscChck = new JCheckBox("Piscina");
		PiscChck.setEnabled(false);

		if (POOL == true) {
			PiscChck.setSelected(true);
		} else {
			PiscChck.setSelected(false);
		}

		PiscChck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PiscChck.setBounds(416, 300, 109, 23);
		BottomHotel.add(PiscChck);

		JCheckBox GinChck = new JCheckBox("Gin\u00E1sio");
		GinChck.setEnabled(false);

		if (GYM == true) {
			GinChck.setSelected(true);
		} else {
			GinChck.setSelected(false);
		}

		GinChck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GinChck.setBounds(416, 326, 109, 23);
		BottomHotel.add(GinChck);

		JCheckBox MassChck = new JCheckBox("Massagens");
		MassChck.setEnabled(false);

		if (MASS == true) {
			MassChck.setSelected(true);
		} else {
			MassChck.setSelected(false);
		}

		MassChck.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MassChck.setBounds(416, 352, 109, 23);
		BottomHotel.add(MassChck);

		JTextField AndaresTxt = new JTextField(Integer.toString(hotelUtil.getPisos()));
		AndaresTxt.setHorizontalAlignment(SwingConstants.TRAILING);
		AndaresTxt.setEditable(false);
		AndaresTxt.setBounds(673, 277, 86, 20);
		BottomHotel.add(AndaresTxt);
		AndaresTxt.setColumns(10);

		JTextField QuartosTxt = new JTextField(Integer.toString(hotelUtil.getQuartosPP()));
		QuartosTxt.setHorizontalAlignment(SwingConstants.TRAILING);
		QuartosTxt.setEditable(false);
		QuartosTxt.setColumns(10);
		QuartosTxt.setBounds(673, 303, 86, 20);
		BottomHotel.add(QuartosTxt);

		JLabel imageHotel = new JLabel("");
		imageHotel.setBounds(10, 42, 309, 255);
		BottomHotel.add(imageHotel);

		ImageIcon ImagemHotel = new ImageIcon(MenuPrincipal.class.getResource(hotelUtil.getImageLink()));
		Image hotelIcon = ImagemHotel.getImage().getScaledInstance(imageHotel.getWidth(), imageHotel.getHeight(),
				Image.SCALE_DEFAULT);

		imageHotel.setIcon(new ImageIcon(hotelIcon));

		JLabel lblAndares = new JLabel("N\u00BA Andares:");
		lblAndares.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAndares.setBounds(577, 273, 86, 25);
		BottomHotel.add(lblAndares);

		JLabel lblQuartosPorAndar = new JLabel("Quartos por Andar:");
		lblQuartosPorAndar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuartosPorAndar.setBounds(531, 299, 132, 25);
		BottomHotel.add(lblQuartosPorAndar);

		JTextArea textArea = new JTextArea(hotelUtil.getDescricao());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		textArea.setBounds(416, 77, 343, 191);
		BottomHotel.add(textArea);

		JPanel BottomSub = new JPanel();
		Bottom.add(BottomSub, "BottomSubscrever");
		BottomSub.setLayout(null);

		JPanel pnlTV = new JPanel();
		pnlTV.setBounds(40, 11, 140, 397);
		BottomSub.add(pnlTV);
		pnlTV.setBackground(new Color(102, 51, 0));
		pnlTV.setLayout(null);

		Button btnSubTV = new Button("Subscrever");
		btnSubTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Subscricao subsTV = new Subscricao(util, "TV");
				subsTV.setVisible(true);
			}
		});
		btnSubTV.setBackground(new Color(255, 140, 0));
		btnSubTV.setBounds(25, 370, 90, 20);
		pnlTV.add(btnSubTV);

		JLabel imgTV = new JLabel("");
		imgTV.setBounds(0, 0, 140, 290);
		pnlTV.add(imgTV);

		ImageIcon imageTV = new ImageIcon(MenuPrincipal.class.getResource("/resources/CanaisTV.png"));
		Image TVIcon = imageTV.getImage().getScaledInstance(imgTV.getWidth(), imgTV.getHeight(), Image.SCALE_DEFAULT);

		imgTV.setIcon(new ImageIcon(TVIcon));
		
		JLabel lblContudoTv = new JLabel("Cont\u00E9udo TV");
		lblContudoTv.setHorizontalAlignment(SwingConstants.CENTER);
		lblContudoTv.setForeground(new Color(255, 255, 255));
		lblContudoTv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContudoTv.setBackground(new Color(255, 140, 0));
		lblContudoTv.setBounds(10, 290, 120, 30);
		pnlTV.add(lblContudoTv);

		JPanel pnlGYM = new JPanel();
		pnlGYM.setBounds(360, 11, 140, 397);
		BottomSub.add(pnlGYM);
		pnlGYM.setBackground(new Color(102, 51, 0));
		pnlGYM.setLayout(null);
		
		JLabel lblGinsio = new JLabel("Gin\u00E1sio");
		lblGinsio.setHorizontalAlignment(SwingConstants.CENTER);
		lblGinsio.setForeground(Color.WHITE);
		lblGinsio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGinsio.setBackground(new Color(255, 140, 0));
		lblGinsio.setBounds(10, 290, 120, 30);
		pnlGYM.add(lblGinsio);
		
		JPanel pnlDispGYM = new JPanel();
		pnlDispGYM.setBackground(new Color(133, 133, 173, 123));
		pnlDispGYM.setBounds(0, 0, 140, 397);
		pnlGYM.add(pnlDispGYM);

		Button btnSubGym = new Button("Subscrever");
		btnSubGym.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subscricao subsGYM = new Subscricao(util, "GYM");
				subsGYM.setVisible(true);
			}
		});
		btnSubGym.setEnabled(false);
		btnSubGym.setBackground(new Color(255, 140, 0));
		btnSubGym.setBounds(25, 370, 90, 20);
		pnlGYM.add(btnSubGym);

		JLabel imgGYM = new JLabel("");
		imgGYM.setBounds(0, 0, 140, 290);
		pnlGYM.add(imgGYM);

		ImageIcon imageGYM = new ImageIcon(MenuPrincipal.class.getResource("/resources/GYM.png"));
		Image GYMIcon = imageGYM.getImage().getScaledInstance(imgGYM.getWidth(), imgGYM.getHeight(),
				Image.SCALE_DEFAULT);

		imgGYM.setIcon(new ImageIcon(GYMIcon));

		JPanel pnlPool = new JPanel();
		pnlPool.setBounds(520, 11, 140, 397);
		BottomSub.add(pnlPool);
		pnlPool.setBackground(new Color(255, 140, 0));
		pnlPool.setLayout(null);
		
		JLabel lblPiscina = new JLabel("Piscina");
		lblPiscina.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiscina.setForeground(Color.WHITE);
		lblPiscina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPiscina.setBackground(new Color(139, 69, 19));
		lblPiscina.setBounds(10, 290, 120, 30);
		pnlPool.add(lblPiscina);
		
		JPanel pnlDispPool = new JPanel();
		pnlDispPool.setBackground(new Color(133, 133, 173, 123));
		pnlDispPool.setBounds(0, 0, 140, 397);
		pnlPool.add(pnlDispPool);

		Button btnSubPool = new Button("Subscrever");
		btnSubPool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subscricao subsPOOL = new Subscricao(util, "POOL");
				subsPOOL.setVisible(true);
			}
		});
		btnSubPool.setEnabled(false);
		btnSubPool.setForeground(new Color(255, 140, 0));
		btnSubPool.setBackground(Color.BLACK);
		btnSubPool.setBounds(25, 370, 90, 20);
		pnlPool.add(btnSubPool);

		JLabel imgPool = new JLabel("");
		imgPool.setBounds(0, 0, 140, 290);
		pnlPool.add(imgPool);

		ImageIcon imagePool = new ImageIcon(MenuPrincipal.class.getResource("/resources/POOL.png"));
		Image PoolIcon = imagePool.getImage().getScaledInstance(imgPool.getWidth(), imgPool.getHeight(),
				Image.SCALE_DEFAULT);

		imgPool.setIcon(new ImageIcon(PoolIcon));

		JPanel pnlMass = new JPanel();
		pnlMass.setBounds(680, 11, 140, 397);
		BottomSub.add(pnlMass);
		pnlMass.setBackground(new Color(102, 51, 0));
		pnlMass.setLayout(null);
		
		JLabel lblMassagens = new JLabel("Massagens");
		lblMassagens.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMassagens.setForeground(new Color(255, 255, 255));
		lblMassagens.setBackground(new Color(255, 140, 0));
		lblMassagens.setHorizontalAlignment(SwingConstants.CENTER);
		lblMassagens.setBounds(10, 290, 120, 30);
		pnlMass.add(lblMassagens);
		
		JPanel pnlDispMass = new JPanel();
		pnlDispMass.setBackground(new Color(133, 133, 173, 123));
		pnlDispMass.setBounds(0, 0, 140, 397);
		pnlMass.add(pnlDispMass);

		Button btnSubMass = new Button("Subscrever");
		btnSubMass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subscricao subsMASS = new Subscricao(util, "MASS");
				subsMASS.setVisible(true);
			}
		});
		btnSubMass.setEnabled(false);
		btnSubMass.setBackground(new Color(255, 140, 0));
		btnSubMass.setBounds(25, 370, 90, 20);
		pnlMass.add(btnSubMass);

		JLabel imgMass = new JLabel("");
		imgMass.setBounds(0, 0, 140, 290);
		pnlMass.add(imgMass);

		ImageIcon imageMass = new ImageIcon(MenuPrincipal.class.getResource("/resources/MASS.png"));
		Image MassIcon = imageMass.getImage().getScaledInstance(imgMass.getWidth(), imgMass.getHeight(),
				Image.SCALE_DEFAULT);

		imgMass.setIcon(new ImageIcon(MassIcon));

		JPanel pnlBar = new JPanel();
		pnlBar.setBounds(200, 11, 140, 397);
		BottomSub.add(pnlBar);
		pnlBar.setBackground(new Color(255, 140, 0));
		pnlBar.setLayout(null);
		
		JLabel lblBar = new JLabel("BAR");
		lblBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBar.setForeground(Color.WHITE);
		lblBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBar.setBackground(new Color(139, 69, 19));
		lblBar.setBounds(10, 288, 120, 30);
		pnlBar.add(lblBar);
		
		JPanel pnlDispBAR = new JPanel();
		pnlDispBAR.setBounds(0, 0, 140, 397);
		pnlBar.add(pnlDispBAR);
		pnlDispBAR.setBackground(new Color (133, 133, 173, 123));

		Button btnSubBar = new Button("Subscrever");
		btnSubBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subscricao subsBAR = new Subscricao(util, "BAR");
				subsBAR.setVisible(true);
			}
		});
		btnSubBar.setEnabled(false);
		btnSubBar.setForeground(new Color(255, 140, 0));
		btnSubBar.setBackground(new Color(0, 0, 0));
		btnSubBar.setBounds(25, 370, 90, 20);
		pnlBar.add(btnSubBar);

		JLabel imgBar = new JLabel("");
		imgBar.setBounds(0, 0, 140, 290);
		pnlBar.add(imgBar);

		ImageIcon imageBar = new ImageIcon(MenuPrincipal.class.getResource("/resources/BAR.png"));
		Image BARIcon = imageBar.getImage().getScaledInstance(imgBar.getWidth(), imgBar.getHeight(),
				Image.SCALE_DEFAULT);

		imgBar.setIcon(new ImageIcon(BARIcon));
		
		JButton btnSubAll = new JButton("Ver Todos");
		btnSubAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subscricao subsALL = new Subscricao(util, "ALL");
				subsALL.setVisible(true);
			}
		});
		btnSubAll.setBackground(new Color(255, 140, 0));
		btnSubAll.setBounds(680, 419, 140, 23);
		BottomSub.add(btnSubAll);
		
		if (hotelUtil.getExtras().contains("BAR")) {
			pnlDispBAR.setVisible(false);
			btnSubBar.setEnabled(true);
		}
		
		if (hotelUtil.getExtras().contains("GYM")) {
			pnlDispGYM.setVisible(false);
			btnSubGym.setEnabled(true);
		}
		
		if (hotelUtil.getExtras().contains("MASS")) {
			pnlDispMass.setVisible(false);
			btnSubMass.setEnabled(true);
		}
		
		if (hotelUtil.getExtras().contains("POOL")) {
			pnlDispPool.setVisible(false);
			btnSubPool.setEnabled(true);
		}

		JPanel BottomConfig = new JPanel();
		Bottom.add(BottomConfig, "BottomConfig");
		BottomConfig.setLayout(null);

		Panel panel_5 = new Panel();
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(102, 51, 0));
		panel_5.setBounds(10, 307, 824, 135);
		BottomConfig.add(panel_5);

		JLabel lblAtualizeORespetivo = new JLabel("Atualize os dados do respetivo hotel");
		lblAtualizeORespetivo.setForeground(new Color(255, 140, 0));
		lblAtualizeORespetivo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAtualizeORespetivo.setBounds(11, 44, 276, 25);
		panel_5.add(lblAtualizeORespetivo);

		JLabel lblAtualizar = new JLabel("Atualizar Hotel");
		lblAtualizar.setForeground(Color.WHITE);
		lblAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblAtualizar.setBounds(11, 0, 608, 37);
		panel_5.add(lblAtualizar);

		

		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(309, 11, 64, 25);
		BottomConfig.add(label_1);
		
		Hotel defaultHotel = Gestor.getHoteis().get(0);

		txtNome = new JTextField(defaultHotel.getNome());
		txtNome.setColumns(10);
		txtNome.setBounds(428, 15, 343, 20);
		BottomConfig.add(txtNome);

		JLabel label_2 = new JLabel("Descri\u00E7\u00E3o:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(309, 43, 74, 25);
		BottomConfig.add(label_2);

		JTextArea txtAreaDesc = new JTextArea(defaultHotel.getDescricao());
		txtAreaDesc.setWrapStyleWord(true);
		txtAreaDesc.setLineWrap(true);
		txtAreaDesc.setBackground(Color.WHITE);
		txtAreaDesc.setBounds(428, 45, 343, 191);
		BottomConfig.add(txtAreaDesc);

		JCheckBox chckBar = new JCheckBox("Bar");
		chckBar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckBar.setBounds(309, 77, 109, 23);
		BottomConfig.add(chckBar);
		if (defaultHotel.getExtras().contains("BAR")) {
			chckBar.setSelected(true);
		}

		JCheckBox chckGinasio = new JCheckBox("Gin\u00E1sio");
		chckGinasio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckGinasio.setBounds(309, 129, 109, 23);
		BottomConfig.add(chckGinasio);
		if (defaultHotel.getExtras().contains("GYM")) {
			chckGinasio.setSelected(true);
		}

		JCheckBox chckPiscina = new JCheckBox("Piscina");
		chckPiscina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckPiscina.setBounds(309, 103, 109, 23);
		BottomConfig.add(chckPiscina);
		if (defaultHotel.getExtras().contains("POOL")) {
			chckPiscina.setSelected(true);
		}

		JCheckBox chckMass = new JCheckBox("Massagens");
		chckMass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckMass.setBounds(309, 155, 109, 23);
		BottomConfig.add(chckMass);
		if (defaultHotel.getExtras().contains("MASS")) {
			chckMass.setSelected(true);
		}

		JLabel label_3 = new JLabel("N\u00BA Andares:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(213, 181, 86, 25);
		BottomConfig.add(label_3);

		JLabel label_4 = new JLabel("Quartos por Andar:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(166, 211, 132, 25);
		BottomConfig.add(label_4);

		txtAndares = new JTextField(Integer.toString(defaultHotel.getPisos()));
		txtAndares.setHorizontalAlignment(SwingConstants.TRAILING);
		txtAndares.setColumns(10);
		txtAndares.setBounds(309, 185, 86, 20);
		BottomConfig.add(txtAndares);

		txtQuartosPorAndar = new JTextField(Integer.toString(defaultHotel.getQuartosPP()));
		txtQuartosPorAndar.setHorizontalAlignment(SwingConstants.TRAILING);
		txtQuartosPorAndar.setColumns(10);
		txtQuartosPorAndar.setBounds(309, 216, 86, 20);
		BottomConfig.add(txtQuartosPorAndar);

		List<String> arrayList = new ArrayList<String>();

		for (int i = 0; i < Gestor.getHoteis().size(); i++) {
			arrayList.add(Gestor.getHoteis().get(i).getNome());
		}
		
		JComboBox comboBoxHotel = new JComboBox();
		comboBoxHotel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				chckBar.setSelected(false);
				chckGinasio.setSelected(false);
				chckPiscina.setSelected(false);
				chckMass.setSelected(false);
				Hotel hotel = Gestor.configHotel(comboBoxHotel.getSelectedItem().toString());
				
				txtNome.setText(hotel.getNome());
				txtAreaDesc.setText(hotel.getDescricao());
				txtImagePath.setText(hotel.getImageLink());
				txtAndares.setText(Integer.toString(hotel.getPisos()));
				txtQuartosPorAndar.setText(Integer.toString(hotel.getQuartosPP()));
				String line = hotel.getExtras();
				if (line.contains("BAR")) {
					chckBar.setSelected(true);
				}

				if (line.contains("GYM")) {
					chckGinasio.setSelected(true);
				}

				if (line.contains("POOL")) {
					chckPiscina.setSelected(true);
				}

				if (line.contains("MASS")) {
					chckMass.setSelected(true);
				}

			}
			
		});
		comboBoxHotel.setModel(new DefaultComboBoxModel(arrayList.toArray()));
		comboBoxHotel.setBounds(10, 47, 227, 20);
		BottomConfig.add(comboBoxHotel);
		
		

		txtImagePath = new JTextField(defaultHotel.getImageLink());
		txtImagePath.setColumns(10);
		txtImagePath.setBounds(428, 247, 233, 20);
		BottomConfig.add(txtImagePath);

		JLabel lblImagem = new JLabel("Imagem:");
		lblImagem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblImagem.setBounds(309, 242, 64, 25);
		BottomConfig.add(lblImagem);

		JButton btnVerImagem = new JButton("Imagem");
		btnVerImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VerImagem preview = new VerImagem(txtImagePath.getText());
				preview.setVisible(true);
			}
		});
		btnVerImagem.setBounds(671, 246, 100, 23);
		BottomConfig.add(btnVerImagem);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NovoHotel NH = new NovoHotel();
				NH.setVisible(true);
			}
		});
		btnNovo.setBounds(10, 14, 109, 23);
		BottomConfig.add(btnNovo);
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hotel = comboBoxHotel.getSelectedItem().toString();
				int posHotel = 0;
				for (int i = 0; i < Gestor.getHoteis().size(); i++) {
					if (Gestor.getHoteis().get(i).getNome().equals(hotel)) {
						posHotel = i;
					}
				}
				
				String extras = "TV";
				if (chckBar.isSelected()) {
					extras = extras + "BAR,";
				}
				
				if (chckGinasio.isSelected()) {
					extras = extras + "GYM,";
				}
				
				if (chckPiscina.isSelected()) {
					extras = extras + "POOL,";
				}
				
				if (chckMass.isSelected()) {
					extras = extras + "MASS,";
				}
				
				if (extras != null && extras.length() > 0 && extras.charAt(extras.length() - 1) == ',') {
			        extras = extras.substring(0, extras.length() - 1);
			    }
				
				Gestor.getHoteis().get(posHotel).setNome(txtNome.getText());
				Gestor.getHoteis().get(posHotel).setDescricao(txtAreaDesc.getText());
				Gestor.getHoteis().get(posHotel).setPisos(Integer.parseInt(txtAndares.getText()));
				Gestor.getHoteis().get(posHotel).setQuartosPP(Integer.parseInt(txtQuartosPorAndar.getText()));
				Gestor.getHoteis().get(posHotel).setExtras(extras);
				Gestor.getHoteis().get(posHotel).setImageLink(txtImagePath.getText());
				
				JOptionPane.showMessageDialog(null, "Hotel alterado com sucesso");
			}
		});
		btnAtualizar.setBounds(701, 11, 113, 113);
		
		ImageIcon imagemRefresh = new ImageIcon(MenuPrincipal.class.getResource("/resources/refreshIcon.png"));
		Image refreshIcon = imagemRefresh.getImage().getScaledInstance(btnAtualizar.getWidth(), btnAtualizar.getHeight(), Image.SCALE_DEFAULT);
		btnAtualizar.setIcon(new ImageIcon(refreshIcon));
		panel_5.add(btnAtualizar);
		
		JPanel BottomConsumo = new JPanel();
		BottomConsumo.setLayout(null);
		Bottom.add(BottomConsumo, "BottomConsumo");
		
		JLabel lblOSeuConsumo = new JLabel("O seu consumo \u00E9 de:");
		lblOSeuConsumo.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblOSeuConsumo.setBounds(123, 196, 331, 32);
		BottomConsumo.add(lblOSeuConsumo);
		
		lblYa = new JLabel(Double.toString(util.getConsumo()) + "€");
		lblYa.setForeground(new Color(205, 133, 63));
		lblYa.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblYa.setBounds(508, 132, 218, 149);
		BottomConsumo.add(lblYa);
		
		JPanel BottomMensagens = new JPanel();
		Bottom.add(BottomMensagens, "BottomMensagens");
		BottomMensagens.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(224, 107, 600, 321);
		BottomMensagens.add(scrollPane_2);
		
		String[] colunasMen = {"ID", "Destinatário", "Assunto"};
		tableModelMensagens = new DefaultTableModel(colunasMen, 0);
		tblMensagens = new JTable(tableModelMensagens);
		tblMensagens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowPickMen = tblMensagens.getSelectedRow();
				idMen = (int) tblMensagens.getValueAt(rowPickMen, 0);
			}
		});
		scrollPane_2.setViewportView(tblMensagens);
		
		JLabel lblMensagens = new JLabel("Mensagens");
		lblMensagens.setForeground(new Color(139, 69, 19));
		lblMensagens.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMensagens.setBounds(354, 31, 101, 25);
		BottomMensagens.add(lblMensagens);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(210, 105, 30));
		panel_1.setBounds(10, 259, 195, 79);
		BottomMensagens.add(panel_1);
		panel_1.setLayout(null);
		
		String numUtil = Integer.toString(util.getId());
		refreshMensagens(numUtil, "");
		
		JButton btnLerMensagem = new JButton("Ler Mensagem");
		btnLerMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Leitor MN = new Leitor(Gestor.getMensagens().get(idMen));
				MN.setVisible(true);
			}
		});
		btnLerMensagem.setBounds(10, 11, 175, 60);
		panel_1.add(btnLerMensagem);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(139, 69, 19));
		panel_2.setLayout(null);
		panel_2.setBounds(10, 349, 195, 79);
		BottomMensagens.add(panel_2);
		
		JButton btnEliminarMensagem = new JButton("Eliminar Mensagem");
		btnEliminarMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(idMen);
				
				int id = 0;
				for (int i = 0; i < Gestor.getMensagens().size(); i++) {
					if (Gestor.getMensagens().get(i).getId_mensagem() == idMen) {
						id = i;
					}
				}
				if (Gestor.getMensagens().get(id).getDest().equals("TODOS")) {
					if (util.getTipo().equals("CLIENTE")) {
						JOptionPane.showMessageDialog(null, "Mensagem global impossivel de eliminiar");
					} else {
						JOptionPane.showMessageDialog(null, "Mensagem eliminada com sucesso");
						try {
							Gestor.eliminarMensagem(idMen);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				} else {
					try {
						
						Gestor.eliminarMensagem(idMen);
						JOptionPane.showMessageDialog(null, "Mensagem eliminada com sucesso");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				refreshMensagens(numUtil, "");
			}
		});
		btnEliminarMensagem.setBounds(10, 11, 175, 60);
		panel_2.add(btnEliminarMensagem);
		
		JCheckBox chckGlo = new JCheckBox("Filtrar mensagens Globais");
		JCheckBox chckDir = new JCheckBox("Filtrar mensagens Diretas");
		chckGlo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filtro = "";
				if (chckGlo.isSelected()) {
					filtro = "NONGLOBAL";
					chckDir.setSelected(false);
				} else {
					filtro = "";
				}
				refreshMensagens(numUtil, filtro);
			}
		});
		chckGlo.setBounds(621, 77, 203, 23);
		BottomMensagens.add(chckGlo);
		
		
		chckDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filtro = "";
				if (chckDir.isSelected()) {
					filtro = "ALLGLOBAL";
					chckGlo.setSelected(false);
				} else {
					filtro = "";
				}
				refreshMensagens(numUtil, filtro);
			}
		});
		chckDir.setBounds(621, 51, 203, 23);
		BottomMensagens.add(chckDir);

		if (util.getTipo().equals("CLIENTE")) {
			lblQt.setVisible(false);
			lblId.setVisible(false);
			btnFiltrar.setVisible(false);
			textQuarto.setVisible(false);
			textID.setVisible(false);
			menuRegistarEditar.setVisible(false);
			menuConteudo.setVisible(false);
			menuDivulgacoes.setVisible(false);
			menuConfiguracoes.setVisible(false);
			Menu = "BottomCliente";
			cardLayoutB.show(Bottom, Menu);
		} else if (util.getTipo().equals("REC")) {
			menuConfiguracoes.setVisible(false);
			menuSubs.setVisible(false);
			menuConsumo.setVisible(false);
			Menu = "BottomRec";
			cardLayoutB.show(Bottom, Menu);
		} else {
			menuSubs.setVisible(false);
			menuConsumo.setVisible(false);
			Menu = "BottomAdministrador";
			cardLayoutB.show(Bottom, Menu);
		}

	}
	
	static void refreshCont() {		
		tableModel.setRowCount(0);
		Gestor.getProgramas();
		for (int i = 0; i < Gestor.getProgramas().size(); i++) {
			tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getTipo(), Gestor.getProgramas().get(i).getPreco()});
		}	
	}
	
	static void refreshTableGestao(String filtro, String resp) {
		tableModelUtil.setRowCount(0);
		Gestor.getUtilizadores();
		for (int i = 0; i < Gestor.getUtilizadores().size(); i++) {
			Utilizador util = Gestor.getUtilizadores().get(i);
			Hotel hotel = Gestor.checkHotel(util);
			if (hotel.getNome().equals(filtro) || filtro.equals("TODOS")) {
				if (resp == "filtAdmin") {
					if (Gestor.getUtilizadores().get(i).getTipo().equals("CLIENTE")) {
						tableModelUtil.addRow(new Object[]{Gestor.getUtilizadores().get(i).getId(), Gestor.getUtilizadores().get(i).getNome(), Gestor.getUtilizadores().get(i).getEmail(), Gestor.getUtilizadores().get(i).getHotel(), Gestor.getUtilizadores().get(i).getCheckin(), Gestor.getUtilizadores().get(i).getCheckout()});
					}
				} else if (resp == "nofiltAdmin") {
					tableModelUtil.addRow(new Object[]{Gestor.getUtilizadores().get(i).getId(), Gestor.getUtilizadores().get(i).getNome(), Gestor.getUtilizadores().get(i).getEmail(), Gestor.getUtilizadores().get(i).getHotel(), Gestor.getUtilizadores().get(i).getCheckin(), Gestor.getUtilizadores().get(i).getCheckout()});
				}
				
			}
		}
	}
	
	static void refreshMensagens(String util, String filter) {		
		tableModelMensagens.setRowCount(0);
		Gestor.getMensagens();
		for (int i = 0; i < Gestor.getMensagens().size(); i++) {
			if (filter.equals("")) {
				if (Gestor.getMensagens().get(i).getDest().equals("TODOS") || Gestor.getMensagens().get(i).getDest().equals(util)) {
					tableModelMensagens.addRow(new Object[]{Gestor.getMensagens().get(i).getId_mensagem(), Gestor.getMensagens().get(i).getDest(), Gestor.getMensagens().get(i).getAssunto()});
				}	
			} else if (filter.equals("NONGLOBAL")) {
				if (Gestor.getMensagens().get(i).getDest().equals(util)) {
					tableModelMensagens.addRow(new Object[]{Gestor.getMensagens().get(i).getId_mensagem(), Gestor.getMensagens().get(i).getDest(), Gestor.getMensagens().get(i).getAssunto()});
				}
			} else if (filter.equals("ALLGLOBAL")) {
				if (Gestor.getMensagens().get(i).getDest().equals("TODOS")) {
					tableModelMensagens.addRow(new Object[]{Gestor.getMensagens().get(i).getId_mensagem(), Gestor.getMensagens().get(i).getDest(), Gestor.getMensagens().get(i).getAssunto()});
				}
			}
		}	
	}
	
	static void refreshTableConsumo(int id) {
		tableModelConsumo.setRowCount(0);
		Gestor.getCompras();
		for (int i = 0; i < Gestor.getCompras().size(); i++) {
			Compra compras = Gestor.getCompras().get(i);
			if (id == compras.getId_utilizador()) {
				tableModelConsumo.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getTipo(), Gestor.getCompras().get(i).getData(), Gestor.getProgramas().get(i).getPreco()});				
			}
		}
		Utilizador util = Gestor.setUser(id);
		lblYa.setText(Double.toString(util.getConsumo()));
		lblConsumoTotal.setText(Double.toString(util.getConsumo()));
	}
}
