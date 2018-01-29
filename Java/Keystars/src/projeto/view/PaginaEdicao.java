package projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projeto.model.Gestor;
import projeto.model.Hotel;
import projeto.model.Utilizador;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

public class PaginaEdicao extends JFrame {

	private JPanel contentPane;
	private JTextField txtContribuinte;
	private JTextField txtNome;
	private JTextField txtApelido;
	private JTextField txtContacto;
	private JTextField txtEmail;
	private JRadioButton rdnMasc;
	private JRadioButton rdnFem;
	private JTextField txtNacionalidade;
	private JTextField txtCivil;
	private JTextField txtPass;
	private JButton btnAdc;
	private JButton btnReset;
	private JButton btnCanc;
	private JTextField txtFotoPerfil;
	private JComboBox cbxHotel;
	private final ButtonGroup Sexo = new ButtonGroup();
	private final ButtonGroup Tipo = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaEdicao frame = new PaginaEdicao(Gestor.getUtilizadores().get(1), Gestor.getUtilizadores().get(2));
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
	public PaginaEdicao(Utilizador util, Utilizador util2) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtContribuinte = new JTextField();
		txtContribuinte.setText(Integer.toString(util2.getCont()));
		txtContribuinte.setBounds(133, 270, 341, 20);
		contentPane.add(txtContribuinte);
		txtContribuinte.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setText(util2.getNome());
		txtNome.setColumns(10);
		txtNome.setBounds(133, 60, 174, 20);
		contentPane.add(txtNome);
		
		txtApelido = new JTextField();
		txtApelido.setText(util2.getApelido());
		txtApelido.setColumns(10);
		txtApelido.setBounds(133, 90, 174, 20);
		contentPane.add(txtApelido);
		
		txtContacto = new JTextField();
		txtContacto.setText(Integer.toString(util2.getContacto()));
		txtContacto.setColumns(10);
		txtContacto.setBounds(133, 180, 341, 20);
		contentPane.add(txtContacto);
		
		txtEmail = new JTextField();
		txtEmail.setText(util2.getEmail());
		txtEmail.setColumns(10);
		txtEmail.setBounds(133, 150, 341, 20);
		contentPane.add(txtEmail);
		
		
		
		JRadioButton rdnCliente = new JRadioButton("Cliente");
		Tipo.add(rdnCliente);
		rdnCliente.setBounds(133, 396, 96, 23);
		contentPane.add(rdnCliente);
		
		JRadioButton rdnRec = new JRadioButton("Rececao");
		Tipo.add(rdnRec);
		rdnRec.setBounds(231, 396, 96, 23);
		contentPane.add(rdnRec);
		
		JRadioButton rdnAdmin = new JRadioButton("Admin");
		Tipo.add(rdnAdmin);
		rdnAdmin.setBounds(329, 396, 96, 23);
		contentPane.add(rdnAdmin);
		
		if (util2.getTipo().equals("CLIENTE")) {
			rdnCliente.setSelected(true);
		}

		if (util2.getTipo().equals("RECECAO")) {
			rdnRec.setSelected(true);
		}

		if (util2.getTipo().equals("ADMIN")) {
			rdnAdmin.setSelected(true);
		}
		
		if (!util.getTipo().equals("ADMIN")) {
			rdnAdmin.setVisible(false);
			rdnAdmin.setFocusable(false);
		}
		
		
		
		rdnMasc = new JRadioButton("Masculino");
		Sexo.add(rdnMasc);
		rdnMasc.setBounds(133, 117, 96, 23);
		contentPane.add(rdnMasc);
		
		rdnFem = new JRadioButton("Feminino");
		Sexo.add(rdnFem);
		rdnFem.setBounds(231, 117, 76, 23);
		contentPane.add(rdnFem);
		
		if (util2.getSexo().equals("Masculino")) {
			rdnMasc.setSelected(true);
		}
		
		
		if (util2.getSexo().equals("Feminino")) {
			rdnFem.setSelected(true);
		}
		
		txtNacionalidade = new JTextField();
		txtNacionalidade.setText(util2.getNacionalidade());
		txtNacionalidade.setColumns(10);
		txtNacionalidade.setBounds(133, 210, 341, 20);
		contentPane.add(txtNacionalidade);
		
		txtCivil = new JTextField();
		txtCivil.setText(util2.getCivil());
		txtCivil.setColumns(10);
		txtCivil.setBounds(133, 240, 341, 20);
		contentPane.add(txtCivil);
		
		txtPass = new JTextField();
		txtPass.setText(util2.getPassword());
		txtPass.setColumns(10);
		txtPass.setBounds(133, 369, 341, 20);
		contentPane.add(txtPass);
		
		List <String> lista = new ArrayList<String>();
		for (int i = 0; i < Gestor.getHoteis().size(); i++) {
			lista.add(Gestor.getHoteis().get(i).getNome());
		}
		
		btnAdc = new JButton("Editar");
		btnAdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				String tipo = "";
				
				if (rdnCliente.isSelected()) {
					tipo = "CLIENTE";
				} else if (rdnRec.isSelected()) {
					tipo = "REC";
				} else if (rdnRec.isSelected()) {
					tipo = "ADMIN";
				}
				
				
				String sexo = "";
				if (rdnMasc.isSelected()) {
					sexo = "Masculino";
				} else if (rdnFem.isSelected()) {
					sexo = "Feminino";
				}
					
				
					
				String foto = "/profilePics/" + txtFotoPerfil.getText();
				try {
					Gestor.editUser(util2.getId() - 1, txtEmail.getText(), txtPass.getText(), txtNome.getText(), txtApelido.getText(), Integer.parseInt(txtContacto.getText()), tipo, Integer.parseInt(txtContribuinte.getText()), sexo, txtNacionalidade.getText(), txtCivil.getText(), foto);
				} catch (NumberFormatException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MenuPrincipal.refreshTableGestao("TODOS", "filtAdmin");
				JOptionPane.showMessageDialog(null, "Edição efectuada com exito");
				dispose();
			}
		});
		btnAdc.setForeground(Color.WHITE);
		btnAdc.setBackground(new Color(0, 128, 0));
		btnAdc.setBounds(365, 453, 109, 37);
		contentPane.add(btnAdc);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtContribuinte.setText(Integer.toString(util2.getCont()));
				txtNome.setText(util2.getNome());
				txtApelido.setText(util2.getApelido());
				txtCivil.setText(util2.getCivil());
				txtPass.setText(util2.getPassword());
				txtEmail.setText(util2.getEmail());
				txtNacionalidade.setText(util2.getNacionalidade());
				txtContacto.setText(Integer.toString(util2.getContacto()));
				txtFotoPerfil.setText(util2.getProfilePic().substring(13, util2.getProfilePic().length()));
				if (util2.getSexo().equals("Masculino")) {
					rdnMasc.setSelected(true);
				} else {
					rdnFem.setSelected(true);
				}
			}
		});
		btnReset.setForeground(new Color(0, 0, 255));
		btnReset.setBounds(13, 453, 109, 37);
		contentPane.add(btnReset);
		
		btnCanc = new JButton("Cancelar");
		btnCanc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operacao cancelada");
				dispose();
			}
		});
		btnCanc.setForeground(Color.WHITE);
		btnCanc.setBackground(Color.RED);
		btnCanc.setBounds(133, 453, 109, 37);
		contentPane.add(btnCanc);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 316, 464, 2);
		contentPane.add(separator);
		
		JLabel lblAdicionarNovoUtilizador = new JLabel("Editar Utilizador");
		lblAdicionarNovoUtilizador.setBackground(new Color(160, 82, 45));
		lblAdicionarNovoUtilizador.setForeground(new Color(210, 105, 30));
		lblAdicionarNovoUtilizador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdicionarNovoUtilizador.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarNovoUtilizador.setBounds(10, 11, 412, 38);
		contentPane.add(lblAdicionarNovoUtilizador);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(13, 60, 110, 14);
		contentPane.add(lblNome);
		
		JLabel lblApelido = new JLabel("Apelido:");
		lblApelido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApelido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApelido.setBounds(13, 90, 110, 14);
		contentPane.add(lblApelido);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSexo.setBounds(13, 120, 110, 14);
		contentPane.add(lblSexo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(13, 150, 110, 14);
		contentPane.add(lblEmail);
		
		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblContacto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContacto.setBounds(13, 180, 110, 14);
		contentPane.add(lblContacto);
		
		JLabel lblNacionalidade = new JLabel("Nacionalidade:");
		lblNacionalidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNacionalidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNacionalidade.setBounds(10, 210, 110, 14);
		contentPane.add(lblNacionalidade);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEstadoCivil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstadoCivil.setBounds(13, 240, 110, 14);
		contentPane.add(lblEstadoCivil);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(13, 426, 461, 2);
		contentPane.add(separator_1);
		
		JLabel lblNContribuinte = new JLabel("N. Contribuinte:");
		lblNContribuinte.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNContribuinte.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNContribuinte.setBounds(13, 270, 110, 14);
		contentPane.add(lblNContribuinte);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(13, 372, 110, 14);
		contentPane.add(lblPassword);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipo.setBounds(13, 400, 110, 14);
		contentPane.add(lblTipo);
		
		txtFotoPerfil = new JTextField();
		txtFotoPerfil.setText(util2.getProfilePic().substring(13, util2.getProfilePic().length()));
		txtFotoPerfil.setColumns(10);
		txtFotoPerfil.setBounds(133, 338, 341, 20);
		contentPane.add(txtFotoPerfil);
		
		JLabel lblFotoDePerfil = new JLabel("Foto de perfil:");
		lblFotoDePerfil.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFotoDePerfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFotoDePerfil.setBounds(13, 340, 110, 14);
		contentPane.add(lblFotoDePerfil);
		
		
	}
}
