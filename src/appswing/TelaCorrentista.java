package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import modelos.Conta;
import modelos.Correntista;
import regras_de_negocio.Fachada;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class TelaCorrentista {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_Listar;
	private JTextField textField_Nome;
	private JFormattedTextField textField_Senha;
	private JLabel label;
	private JLabel label_cpf;
	private JLabel label_Nome;
	private JLabel label_8;
	private JLabel label_Senha;
	private JButton button_VerContas;
	private JTextField textField_CPF;


	public TelaCorrentista() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.setModal(true);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});
		frame.setTitle("Correntista");
		frame.setBounds(100, 100, 680, 340);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 42,600, 120);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


		button = new JButton("Criar");
		button.setBackground(SystemColor.control);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_CPF.getText().isEmpty() || 
							textField_Nome.getText().isEmpty() ||
							textField_Senha.getText().isEmpty()) 
					{
						label.setText("campo vazio");
						return;
					}
					
					String cpf = textField_CPF.getText();
					String nome = textField_Nome.getText();
					String senha = textField_Senha.getText();
					
					if (!(cpf.matches("^\\d+$") && senha.matches("^\\d+$")))  {
						throw new Exception("Os campos acima devem ser numéricos!");
					}
					
					Fachada.criarCorrentista(cpf, nome, senha);
					label.setText("Correntista Criado!");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(239, 194, 63, 23);
		frame.getContentPane().add(button);

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBackground(Color.RED);
		label.setBounds(10, 276, 644, 14);
		frame.getContentPane().add(label);

		label_cpf = new JLabel("CPF:");
		label_cpf.setHorizontalAlignment(SwingConstants.LEFT);
		label_cpf.setFont(new Font("Dialog", Font.BOLD, 12));
		label_cpf.setBounds(26, 226, 43, 14);
		frame.getContentPane().add(label_cpf);

		label_Nome = new JLabel("Nome:");
		label_Nome.setForeground(new Color(0, 0, 0));
		label_Nome.setHorizontalAlignment(SwingConstants.LEFT);
		label_Nome.setFont(new Font("Dialog", Font.BOLD, 12));
		label_Nome.setBounds(26, 198, 50, 14);
		frame.getContentPane().add(label_Nome);

		textField_CPF = new JTextField();
		textField_CPF.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_CPF.setColumns(10);
		textField_CPF.setBounds(72, 223, 104, 20);
		frame.getContentPane().add(textField_CPF);
		
		textField_Nome = new JTextField();
		textField_Nome.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_Nome.setColumns(10);
		textField_Nome.setBounds(72, 195, 157, 20);
		frame.getContentPane().add(textField_Nome);

		label_8 = new JLabel("selecione");
		label_8.setBounds(26, 163, 561, 14);
		frame.getContentPane().add(label_8);
		
		
		label_Senha = new JLabel("Senha:");
		label_Senha.setHorizontalAlignment(SwingConstants.LEFT);
		label_Senha.setFont(new Font("Dialog", Font.BOLD, 12));
		label_Senha.setBounds(186, 226, 43, 14);
		frame.getContentPane().add(label_Senha);

		try {
		 textField_Senha= new JFormattedTextField(new MaskFormatter("####"));
		} 
		catch (ParseException e1) {}
		
		
		textField_Senha.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_Senha.setColumns(10);
		textField_Senha.setBounds(239, 223, 63, 20);
		frame.getContentPane().add(textField_Senha);

		button_Listar = new JButton("Listar");
		button_Listar.setBackground(SystemColor.control);
		button_Listar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button_Listar.setBounds(274, 8, 95, 23);
		frame.getContentPane().add(button_Listar);

		button_VerContas = new JButton("Ver Contas");
		button_VerContas.setBackground(SystemColor.control);
		button_VerContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String cpf = (String) table.getValueAt(table.getSelectedRow(), 0);
						String id= "Id's das contas relacionadas:";
						
						
						for(Conta c : Fachada.listarContas()) {
							for (Correntista cr : c.getCorrentistas()) {
								if (cr.getCpf().equals(cpf)) {
									id+="\n"+c.getId()+" - "+c.getCorrentistas().getFirst().getNome();
								}
							}
							
						}
						JOptionPane.showMessageDialog(frame,id);
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_VerContas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_VerContas.setBounds(491, 184, 135, 23);
		frame.getContentPane().add(button_VerContas);
		


	

	}

	//*****************************
	public void listagem () {
		try{
			List<Correntista> lista = Fachada.listarCorrentistas();

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			
			//colunas
			model.addColumn("cpf");
			model.addColumn("nome");
			model.addColumn("qntd contas");
			//linhas
			for(Correntista cr : lista) {
				model.addRow(new Object[] {cr.getCpf(),cr.getNome(),cr.getContas().size()});
			}

			table.setModel(model);
			label_8.setText("resultados: "+lista.size()+ " correntistas - selecione uma linha");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}

	}
}
