package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
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

import modelos.Conta;
import modelos.ContaEspecial;
import modelos.Correntista;
import regras_de_negocio.Fachada;

public class TelaConta{
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_6;
	private JLabel label_1;
	private JLabel label_Id;
	private JTextField textField_PesquisarId;
	private JTextField textField_Cpf;
	private JButton button_Listar;
	private JButton button_Criar;
	private JLabel label_Limite;
	private JTextField textField_Limite;
	private JButton button_Apagar;
	private JButton button_AddCotitular;
	private JButton button_RmvCotitular;
	private JButton button_Limpar;



	public TelaConta() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Conta");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem(Fachada.listarContas());
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
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

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_6 = new JLabel("selecione");
		label_6.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_6);

		label_1 = new JLabel("Digite parte do nome");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(21, 14, 128, 14);
		frame.getContentPane().add(label_1);

		textField_PesquisarId = new JTextField();
		textField_PesquisarId.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_PesquisarId.setColumns(10);
		textField_PesquisarId.setBackground(Color.WHITE);
		textField_PesquisarId.setBounds(159, 11, 137, 20);
		frame.getContentPane().add(textField_PesquisarId);
		
		button_Limpar = new JButton("Limpar");
		button_Limpar.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						textField_PesquisarId.setText("");
						textField_PesquisarId.requestFocus();
						listagem(Fachada.listarContas());
					}
				}
				);
		button_Limpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Limpar.setBounds(402, 10, 89, 23);
		frame.getContentPane().add(button_Limpar);

		label_Id = new JLabel("CPF:");
		label_Id.setHorizontalAlignment(SwingConstants.LEFT);
		label_Id.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_Id.setBounds(21, 257, 34, 14);
		frame.getContentPane().add(label_Id);

		textField_Cpf = new JTextField();
		textField_Cpf.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_Cpf.setColumns(10);
		textField_Cpf.setBounds(52, 254, 108, 20);
		frame.getContentPane().add(textField_Cpf);

		button_Criar = new JButton("Criar");
		button_Criar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_Cpf.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String cpf = textField_Cpf.getText();
					String limite = textField_Limite.getText();
					
					if (!(limite.matches("^\\d+$") && cpf.matches("^\\d+$")))  {
						throw new Exception("Os campos acima devem ser numéricos!");
					}
					
					if(limite.isEmpty())
						Fachada.criarConta(cpf);
					else
						Fachada.criarContaEspecial(cpf,Double.parseDouble(limite));

					label.setText("Conta criada!");
					listagem(Fachada.listarContas());
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_Criar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Criar.setBounds(170, 254, 107, 20);
		frame.getContentPane().add(button_Criar);

		button_Listar = new JButton("Listar");
		button_Listar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Conta> contaPesquisada = new ArrayList<>();
				for (Conta c: Fachada.listarContas()) {
					if (c.getId() == Integer.parseInt(textField_PesquisarId.getText())) {
						contaPesquisada.add(c);
						break;
					}
				}
				listagem(contaPesquisada);
			}
		});
		button_Listar.setBounds(306, 9, 89, 23);
		frame.getContentPane().add(button_Listar);

		label_Limite = new JLabel("Limite (Conta Especial):");
		label_Limite.setHorizontalAlignment(SwingConstants.LEFT);
		label_Limite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_Limite.setBounds(21, 284, 149, 14);
		frame.getContentPane().add(label_Limite);

		textField_Limite = new JTextField();
		textField_Limite.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_Limite.setColumns(10);
		textField_Limite.setBounds(161, 283, 116, 20);
		frame.getContentPane().add(textField_Limite);

		button_Apagar = new JButton("Apagar"); //APAGAR CONTA
		button_Apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						//String cpf = (String) table.getValueAt( table.getSelectedRow(), 1);
						//int id = (int) table.getValueAt(table.getSelectedRow(), 1);
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);

						Fachada.apagarConta(id);
						label.setText("deletou conta " +id);
						listagem(Fachada.listarContas());
					}
					else
						label.setText("conta nao selecionada");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_Apagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Apagar.setBounds(309, 213, 86, 23);
		frame.getContentPane().add(button_Apagar);

		button_AddCotitular = new JButton("Adicionar Cotitular"); //ADICIONAR COTIULAR A CONTA
		button_AddCotitular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);
						String cpf = JOptionPane.showInputDialog(frame, "Digite o cpf do cotitular");

						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma inserção de cotitular "+id, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.inserirCorrentistaConta(id, cpf);
							label.setText("Correntista "+cpf+" adicionado em "+id );
							listagem(Fachada.listarContas());
						}
						else
							label.setText("nao adicionou cotitular " +cpf );

					}
					else
						label.setText("conta nao selecionada");
				}
				catch(NumberFormatException ex) {
					label.setText("formato do id invalido");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_AddCotitular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_AddCotitular.setBounds(411, 213, 137, 23);
		frame.getContentPane().add(button_AddCotitular);

		button_RmvCotitular = new JButton("Remover Cotitular"); //APAGAR COTITULAR DE CONTA
		button_RmvCotitular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);
						String cpf = JOptionPane.showInputDialog(frame, "Digite o cpf do cotitular");

						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma remocao do cotitular "+cpf, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							//Fachada.removerParticipanteEvento(nome, Integer.parseInt(id));
							Fachada.removerCorrentistaConta(id, cpf);
							label.setText("vc removeu "+cpf+ "da conta "+id);
							listagem(Fachada.listarContas());
						}
						else
							label.setText("nao removeu cotitular " +cpf );

					}
					else
						label.setText("Conta nao selecionada");
				}
				catch(NumberFormatException ex) {
					label.setText("formato do id invalido");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_RmvCotitular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_RmvCotitular.setBounds(558, 213, 137, 23);
		frame.getContentPane().add(button_RmvCotitular);
	}

	public void listagem(List<Conta> lista) {
		try{
			//List<Conta> lista = Fachada.listarContas(textField_PesquisarNome.getText());
			//List<Conta> lista = Fachada.listarContas();

			DefaultTableModel model = new DefaultTableModel();

			//colunas
			model.addColumn("id");
			model.addColumn("data");
			model.addColumn("saldo");
			model.addColumn("limite");
			model.addColumn("Correntistas");

			//linhas
			String texto;
			for(Conta c : lista) {
				texto=" ";
				for(Correntista cr : c.getCorrentistas()) 
					texto += cr.getNome()+ " " ;
				

				if(c instanceof ContaEspecial ce)
					//model.addRow(new Object[]{p.getEmail(), p.getNome(), p.getIdade(), ce.getEmpresa(),p.getPercentual(), texto});
					model.addRow(new Object[]{c.getId(),c.getData(),c.getSaldo(),ce.getLimite(), texto});
				else
					//model.addRow(new Object[]{p.getEmail(), p.getNome(), p.getIdade(), "",p.getPercentual(), texto});
					model.addRow(new Object[]{c.getId(),c.getData(),c.getSaldo(), "",texto});

			}

			table.setModel(model);
			label_6.setText("resultados: "+lista.size()+ " contas   - selecione uma linha");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}


}
