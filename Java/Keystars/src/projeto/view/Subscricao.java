package projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import projeto.model.Gestor;
import projeto.model.Hotel;
import projeto.model.Programa;
import projeto.model.Utilizador;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Subscricao extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNomeProduto;
	private JLabel lblPreo;
	private JLabel lblPrecoProduto;
	private JLabel lblDescricao;
	private int rowPick;
	private int id;
	private Programa pacote = new Programa();
	static DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subscricao frame = new Subscricao(Gestor.setUser(2), "ALL");
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
	public Subscricao(Utilizador util, String tipo) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 730, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblContudosDisponveis = new JLabel("Cont\u00E9udos dispon\u00EDveis");
		lblContudosDisponveis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContudosDisponveis.setBounds(382, 72, 218, 14);
		contentPane.add(lblContudosDisponveis);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(10, 70, 46, 14);
		contentPane.add(lblNome);
		
		lblNomeProduto = new JLabel("");
		
		lblNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomeProduto.setBounds(66, 70, 209, 14);
		contentPane.add(lblNomeProduto);
		
		lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPreo.setBounds(10, 95, 46, 14);
		contentPane.add(lblPreo);
		
		lblPrecoProduto = new JLabel("");
		lblPrecoProduto.setText("");
		lblPrecoProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecoProduto.setBounds(66, 95, 209, 14);
		contentPane.add(lblPrecoProduto);
		
		lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescricao.setBounds(10, 120, 95, 14);
		contentPane.add(lblDescricao);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPane.setBounds(10, 145, 300, 175);
		contentPane.add(textPane);
		
		JLabel lblConsumoAtual = new JLabel("Consumo atual:");
		lblConsumoAtual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsumoAtual.setBounds(10, 331, 123, 14);
		contentPane.add(lblConsumoAtual);
		
		JLabel lblConsumo = new JLabel("");
		lblConsumo.setText(Double.toString(util.getConsumo()));
		lblConsumo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsumo.setBounds(123, 331, 75, 14);
		contentPane.add(lblConsumo);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					util.setConsumo(util.getConsumo() + Double.parseDouble(lblPrecoProduto.getText()));
					lblConsumo.setText(Double.toString(util.getConsumo()));
					Gestor.comprarPacote(util, pacote);
					MenuPrincipal.refreshTableConsumo(util.getId());
					JOptionPane.showMessageDialog(null, "Compra efectuada com sucesso");
					dispose();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnComprar.setBounds(208, 327, 102, 23);
		contentPane.add(btnComprar);
		
		JLabel lblComprarProduto = new JLabel("Comprar Produto");
		lblComprarProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprarProduto.setForeground(new Color(210, 105, 30));
		lblComprarProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComprarProduto.setBackground(new Color(160, 82, 45));
		lblComprarProduto.setBounds(145, 11, 412, 38);
		contentPane.add(lblComprarProduto);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(382, 97, 332, 263);
		contentPane.add(scrollPane);
		String[] columnsALL = {"ID", "Nome", "Tipo", "Preco"};
		String[] columnsSPEC = {"ID", "Nome", "Preco"};
		System.out.println(tipo);
		if (tipo == "TV" || tipo == "ALL") {
			tableModel = new DefaultTableModel(columnsALL, 0);
		} else {
			tableModel = new DefaultTableModel(columnsSPEC, 0);
		}	
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowPick = table.getSelectedRow();
				id = (int) table.getValueAt(rowPick, 0);
				
				pacote = Gestor.getPacote(id);
				lblNomeProduto.setText(pacote.getNome());
				lblPrecoProduto.setText(Double.toString(pacote.getPreco()));
				textPane.setText(pacote.getDescricao());
			}
		});
		scrollPane.setViewportView(table);
		refreshCont(util, tipo);
	}
	
	private void refreshCont(Utilizador util, String filter) {		
		tableModel.setRowCount(0);
		Gestor.getProgramas();
		for (int i = 0; i < Gestor.getProgramas().size(); i++) {
			if (filter.equals("ALL")) {
				Hotel hotel = Gestor.checkHotel(util);
				System.out.println(hotel.getExtras());
				if (hotel.getExtras().contains("BAR") && Gestor.getProgramas().get(i).getTipo().equals("BAR")) {
					tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getTipo (), Gestor.getProgramas().get(i).getPreco()});
				} else if (hotel.getExtras().contains("POOL") && Gestor.getProgramas().get(i).getTipo().equals("POOL")) {
					tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getTipo (), Gestor.getProgramas().get(i).getPreco()});
				} else if (hotel.getExtras().contains("GYM") && Gestor.getProgramas().get(i).getTipo().equals("GYM")) {
					tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getTipo (), Gestor.getProgramas().get(i).getPreco()});
				} else if (hotel.getExtras().contains("MASS") && Gestor.getProgramas().get(i).getTipo().equals("MASS")) {
					tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getTipo (), Gestor.getProgramas().get(i).getPreco()});
				} else if (Gestor.getProgramas().get(i).getTipo().equals("CTV") || Gestor.getProgramas().get(i).getTipo().equals("DOC") || Gestor.getProgramas().get(i).getTipo().equals("SER")) {
					tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getTipo (), Gestor.getProgramas().get(i).getPreco()});
				}
				
			} else if (filter.equals("TV") && (Gestor.getProgramas().get(i).getTipo().equals("DOC") || Gestor.getProgramas().get(i).getTipo().equals("SER") || Gestor.getProgramas().get(i).getTipo().equals("CTV") )) {
				tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getTipo (), Gestor.getProgramas().get(i).getPreco()});
			} else if (filter.equals("BAR") && Gestor.getProgramas().get(i).getTipo().equals("BAR")) {
				tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getPreco()});
			} else if (filter.equals("MASS") && Gestor.getProgramas().get(i).getTipo().equals("MASS")) {
				tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getPreco()});
			} else if (filter.equals("POOL") && Gestor.getProgramas().get(i).getTipo().equals("POOL")) {
				tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getPreco()});
			} else if (filter.equals("GYM") && Gestor.getProgramas().get(i).getTipo().equals("GYM")) {
				tableModel.addRow(new Object[]{Gestor.getProgramas().get(i).getId(), Gestor.getProgramas().get(i).getNome(), Gestor.getProgramas().get(i).getPreco()});
			}
		}	
	}
}
