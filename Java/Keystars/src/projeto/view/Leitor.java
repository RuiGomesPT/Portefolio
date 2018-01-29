package projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projeto.model.Gestor;
import projeto.model.Mensagem;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Leitor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Leitor frame = new Leitor(Gestor.getMensagens().get(0));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public Leitor(Mensagem mensagem) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setBounds(551, 327, 89, 23);
		contentPane.add(btnFechar);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(mensagem.getMensagem());
		textPane.setEditable(false);
		textPane.setBounds(43, 90, 597, 226);
		contentPane.add(textPane);
		
		textField = new JTextField();
		textField.setText(mensagem.getAssunto());
		textField.setEditable(false);
		textField.setBounds(139, 33, 310, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAssunto.setBounds(41, 34, 88, 14);
		contentPane.add(lblAssunto);
		
		JLabel lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMensagem.setBounds(43, 69, 88, 23);
		contentPane.add(lblMensagem);
	}

}
