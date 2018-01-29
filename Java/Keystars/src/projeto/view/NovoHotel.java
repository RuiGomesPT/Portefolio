package projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projeto.model.Gestor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class NovoHotel extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtFoto;
	private JTextField txtQuartosPP;
	private JTextField txtPisos;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoHotel frame = new NovoHotel();
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
	public NovoHotel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(123, 104, 351, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		
		
		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(248, 453, 109, 37);
		contentPane.add(btnCancel);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.RED);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 464, 2);
		contentPane.add(separator);
		
		JLabel lblAdicionarNovoHotel = new JLabel("Adicionar Novo Hotel");
		lblAdicionarNovoHotel.setBounds(30, 11, 412, 38);
		contentPane.add(lblAdicionarNovoHotel);
		lblAdicionarNovoHotel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarNovoHotel.setForeground(new Color(210, 105, 30));
		lblAdicionarNovoHotel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdicionarNovoHotel.setBackground(new Color(160, 82, 45));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 0, 461, 2);
		contentPane.add(separator_1);
		
		txtFoto = new JTextField();
		txtFoto.setBounds(121, 318, 353, 20);
		contentPane.add(txtFoto);
		txtFoto.setColumns(10);
		
		JLabel label_11 = new JLabel("Foto de perfil:");
		label_11.setBounds(10, 320, 101, 14);
		contentPane.add(label_11);
		label_11.setHorizontalAlignment(SwingConstants.TRAILING);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JTextPane txtDescricao = new JTextPane();
		txtDescricao.setBounds(123, 138, 351, 173);
		contentPane.add(txtDescricao);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(0, 106, 110, 14);
		contentPane.add(lblNome);
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblDescricao = new JLabel("Descricao:");
		lblDescricao.setBounds(0, 138, 110, 14);
		contentPane.add(lblDescricao);
		lblDescricao.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		txtQuartosPP = new JTextField();
		txtQuartosPP.setColumns(10);
		txtQuartosPP.setBounds(394, 344, 80, 20);
		contentPane.add(txtQuartosPP);
		
		txtPisos = new JTextField();
		txtPisos.setColumns(10);
		txtPisos.setBounds(173, 344, 80, 20);
		contentPane.add(txtPisos);
		
		JLabel lblPisos = new JLabel("Pisos:");
		lblPisos.setHorizontalAlignment(SwingConstants.LEFT);
		lblPisos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPisos.setBounds(124, 347, 61, 14);
		contentPane.add(lblPisos);
		
		JLabel lblQuartosPorPiso = new JLabel("Quartos por Piso:");
		lblQuartosPorPiso.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuartosPorPiso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuartosPorPiso.setBounds(263, 345, 121, 14);
		contentPane.add(lblQuartosPorPiso);
		
		JCheckBox chckBAR = new JCheckBox("Bar");
		chckBAR.setBounds(377, 371, 97, 23);
		contentPane.add(chckBAR);
		
		JCheckBox chckPOOL = new JCheckBox("Piscina");
		chckPOOL.setBounds(377, 397, 97, 23);
		contentPane.add(chckPOOL);
		
		JCheckBox chckGYM = new JCheckBox("Ginasio");
		chckGYM.setBounds(263, 371, 97, 23);
		contentPane.add(chckGYM);
		
		JCheckBox chckMASS = new JCheckBox("Massagens");
		chckMASS.setBounds(263, 397, 97, 23);
		contentPane.add(chckMASS);
		
		JLabel lblExtras = new JLabel("Extras:");
		lblExtras.setHorizontalAlignment(SwingConstants.LEFT);
		lblExtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblExtras.setBounds(124, 375, 61, 14);
		contentPane.add(lblExtras);
		

		JButton btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String extras = "TV";
				if (chckBAR.isSelected()) {
					extras = extras + "BAR,";
				}
				
				if (chckGYM.isSelected()) {
					extras = extras + "GYM,";
				}
				
				if (chckPOOL.isSelected()) {
					extras = extras + "POOL,";
				}
				
				if (chckMASS.isSelected()) {
					extras = extras + "MASS,";
				}
				
				if (extras != null && extras.length() > 0 && extras.charAt(extras.length() - 1) == ',') {
			        extras = extras.substring(0, extras.length() - 1);
			    }
				
				try {
					Gestor.addHotel(txtNome.getText(), txtDescricao.getText(), extras, Integer.parseInt(txtPisos.getText()), Integer.parseInt(txtQuartosPP.getText()), txtFoto.getText());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Hotel adicionado com sucesso");
			}
		});
		btnAdd.setBounds(365, 453, 109, 37);
		contentPane.add(btnAdd);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(0, 128, 0));
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNome.setText("");
				txtDescricao.setText("");
				txtPisos.setText("");
				txtQuartosPP.setText("");
				txtFoto.setText("");
				chckPOOL.setSelected(false);
				chckGYM.setSelected(false);
				chckMASS.setSelected(false);
				chckBAR.setSelected(false);
			}
		});
		btnReset.setBounds(10, 453, 109, 37);
		contentPane.add(btnReset);
		btnReset.setForeground(Color.BLUE);
	}
}
