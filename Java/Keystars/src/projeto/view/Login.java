package projeto.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import projeto.model.Gestor;
import projeto.model.Utilizador;

import javax.swing.JTextPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean checkLog = false;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestor.loadAll();
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 490);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(424, 55, 2, 341);
		contentPane.add(separator);
		
		ImageIcon backgroundImg = new ImageIcon(Login.class.getResource("/resources/loginBackground.jpg"));
		Image background = getScaledImage(backgroundImg.getImage(), 650, 450);
		backgroundImg = new ImageIcon(background);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setFont(new Font("Arial", Font.PLAIN, 31));
		lblLogIn.setBounds(478, 68, 122, 63);
		contentPane.add(lblLogIn);
		
		JTextField txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtEmail.setBounds(454, 167, 167, 37);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(454, 142, 167, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Palavra-Passe");
		lblPassword.setBounds(454, 215, 167, 14);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(454, 240, 167, 37);
		contentPane.add(txtPassword);

		
		JLabel errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		errorLbl.setBounds(454, 286, 167, 26);
		contentPane.add(errorLbl);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				int numUtil = Gestor.LogIn(email, password);
				
				if (numUtil == 0) {
					errorLbl.setText("Utilizador não encontrado");
				} else {
					JOptionPane.showMessageDialog(null, "Log in efectuado com sucesso");
					MenuPrincipal menu = new MenuPrincipal(Gestor.setUser(numUtil));
					menu.setVisible(true);
					dispose();
				}
				
			}
		});
		btnEntrar.setBounds(478, 349, 89, 23);
		contentPane.add(btnEntrar);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		txtEmail.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		btnEntrar.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		ImageIcon logoImg = new ImageIcon(Login.class.getResource("/resources/logoImg.png"));
		Image logo = getScaledImage(logoImg.getImage(), 372, 123);
		logoImg = new ImageIcon(logo);
		
		JLabel lblLogo = new JLabel("", logoImg, JLabel.CENTER);
		lblLogo.setBounds(21, 25, 393, 158);
		contentPane.add(lblLogo);
		
		JLabel lblBackground = new JLabel("", backgroundImg, JLabel.CENTER);
		lblBackground.setBounds(0, 0, 644, 461);
		contentPane.add(lblBackground);

		

	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
