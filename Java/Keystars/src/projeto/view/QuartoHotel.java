package projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projeto.model.Gestor;
import projeto.model.Utilizador;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class QuartoHotel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuartoHotel frame = new QuartoHotel(Gestor.getUtilizadores().get(1));
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
	public QuartoHotel(Utilizador util) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		List<String> arrayHoteis = new ArrayList<String>();
		for (int i = 0; i < Gestor.getHoteis().size(); i++) {
			arrayHoteis.add(Gestor.getHoteis().get(i).getNome());
		}
		
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(arrayHoteis.toArray()));
		comboBox.setBounds(186, 11, 188, 20);
		contentPane.add(comboBox);
		
		JLabel lblHotel = new JLabel("Hotel:");
		lblHotel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHotel.setBounds(10, 14, 99, 14);
		contentPane.add(lblHotel);
		
		JLabel lblQuarto = new JLabel("N\u00FAmero de Quarto:");
		lblQuarto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuarto.setBounds(10, 52, 176, 14);
		contentPane.add(lblQuarto);
		
		textField = new JTextField();
		textField.setBounds(186, 51, 188, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hotel = comboBox.getSelectedItem().toString();
				String ID = "";
				for (int i = 0; i < Gestor.getHoteis().size(); i++) {
					if (Gestor.getHoteis().get(i).getNome().equals(hotel)) {
						ID = Gestor.getHoteis().get(i).getIdentificador();
					}
				}
				if (!textField.getText().equals("")) {
					util.setHotel(ID + "_" + textField.getText());
					try {
						Gestor.saveUtilizadores();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MenuPrincipal.refreshTableGestao("TODOS", "filtAdmin");
					JOptionPane.showMessageDialog(null, "Quarto alterado com sucesso");
					dispose();
				}
				
			}
		});
		btnGuardar.setBounds(186, 97, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(285, 97, 89, 23);
		contentPane.add(btnCancelar);
	}
}
