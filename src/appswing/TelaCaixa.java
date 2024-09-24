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
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCaixa{
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel labelAvisos;
	private JLabel label_6;
	private JLabel label_1;
	private JTextField textField_PesquisarId;
	private JButton button_Listar;
	private JButton button_Limpar;
	private JButton btnDepositar;
	private JTextField textField_ValorDebitar;
	private JLabel label_Debitar;
	private JLabel label_2;
	private JLabel label_Creditar;
	private JButton btnCreditar;
	private JTextField textField_ValorCreditar;
	private JLabel label_CreditarV;
	private JLabel label_Transferir;
	private JTextField textField_valorTransferir;
	private JLabel label_CreditarV_1;
	private JTextField textField_idTransferir;
	private JLabel label_Id;
	private JTextField textField_SenhaDebitar;
	private JLabel label_SenhaDebitar;
	private JTextField textField_SenhaCreditar;
	private JLabel label_SenhaCreditar;
	private JButton btnTransferir;
	private JTextField textField_SenhaTransferir;
	private JLabel label_SenhaTransferir;
	private JTextField textField_CpfDebitar;
	private JTextField textField_CpfCreditar;
	private JLabel lblNewLabel_1;
	private JTextField textField_cpfTransferir;
	private JLabel lblNewLabel_2;



	public TelaCaixa() {
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
		frame.setBounds(100, 100, 729, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem(Fachada.listarContas());
			}
		});
		
		label_2 = new JLabel("SACAR");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_2.setBounds(64, 215, 69, 23);
		frame.getContentPane().add(label_2);
		
		btnDepositar = new JButton("Debitar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					labelAvisos.setText(null);
					if (table.getSelectedRow() < 0) {
						throw new Exception ("Selecione uma conta");
					}
					if (!(textField_ValorDebitar.getText().matches("^\\d+$") && textField_CpfDebitar.getText().matches("^\\d+$") 
							&& textField_SenhaDebitar.getText().matches("^\\d+$"))) {
						throw new Exception ("Os caracteres de todos os campos devem ser numéricos!");
					}
					
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					String cpf = textField_CpfDebitar.getText();
					String senha = textField_SenhaDebitar.getText();
					Double valor = Double.parseDouble(textField_ValorDebitar.getText());
					
					Fachada.debitarValor(id, cpf, senha, valor);
					listagem(Fachada.listarContas());
					labelAvisos.setText("Saque feito com sucesso!");
				} catch (Exception e1) {
					labelAvisos.setText(e1.getMessage());
				}
				
			}
		});
		
		btnDepositar.setBounds(31, 346, 137, 34);
		frame.getContentPane().add(btnDepositar);

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

		labelAvisos = new JLabel("");
		labelAvisos.setForeground(Color.BLUE);
		labelAvisos.setBounds(21, 416, 674, 14);
		frame.getContentPane().add(labelAvisos);

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
						labelAvisos.setText(null);
						textField_PesquisarId.setText("");
						textField_PesquisarId.requestFocus();
						listagem(Fachada.listarContas());
					}
				}
				);
		button_Limpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Limpar.setBounds(402, 10, 89, 23);
		frame.getContentPane().add(button_Limpar);

		button_Listar = new JButton("Listar");
		button_Listar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_Listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
					labelAvisos.setText(null);
					if (textField_PesquisarId.getText().isEmpty()) {
						listagem(Fachada.listarContas());
		                throw new Exception("Pesquisa vazia, digite um ID!");
					}
		            if (!textField_PesquisarId.getText().matches("^\\d+$")) {
						listagem(Fachada.listarContas());
		                throw new Exception("Pesquisa por ID, digite caracteres numéricos!");
		            }
		           

		            List<Conta> contaPesquisada = new ArrayList<>();
		            for (Conta c : Fachada.listarContas()) {
		                if (c.getId() == Integer.parseInt(textField_PesquisarId.getText())) {
		                    contaPesquisada.add(c);
		                    break;
		                }
		            }
		            listagem(contaPesquisada);
		        } catch (Exception ex) {
		            labelAvisos.setText(ex.getMessage());
		            
		        }
			}
		});
		button_Listar.setBounds(306, 9, 89, 23);
		frame.getContentPane().add(button_Listar);
		
		textField_ValorDebitar = new JTextField();
		textField_ValorDebitar.setBounds(82, 259, 86, 20);
		frame.getContentPane().add(textField_ValorDebitar);
		textField_ValorDebitar.setColumns(10);
		
		label_Debitar = new JLabel("Valor:");
		label_Debitar.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_Debitar.setBounds(31, 262, 46, 14);
		frame.getContentPane().add(label_Debitar);
		
		label_Creditar = new JLabel("DEPOSITAR");
		label_Creditar.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_Creditar.setBounds(274, 215, 128, 23);
		frame.getContentPane().add(label_Creditar);
		
		btnCreditar = new JButton("Creditar");
		btnCreditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					labelAvisos.setText(null);
					if (table.getSelectedRow() < 0) {
						throw new Exception ("Selecione uma conta");
					} 
					if (!(textField_ValorCreditar.getText().matches("^\\d+$") && textField_CpfCreditar.getText().matches("^\\d+$") 
							&& textField_SenhaCreditar.getText().matches("^\\d+$"))) {
						throw new Exception ("Os caracteres de todos os campos devem ser numéricos!");
					}
					
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					String cpf = textField_CpfCreditar.getText();
					String senha = textField_SenhaCreditar.getText();
					Double valor = Double.parseDouble(textField_ValorCreditar.getText());
					
					Fachada.creditarValor(id, cpf, senha, valor);
					listagem(Fachada.listarContas());
					labelAvisos.setText("Depósito feito com sucesso!");
				} catch (Exception e1) {
					labelAvisos.setText(e1.getMessage());
				}
			}
		});
		btnCreditar.setBounds(274, 346, 139, 34);
		frame.getContentPane().add(btnCreditar);
		
		textField_ValorCreditar = new JTextField();
		textField_ValorCreditar.setColumns(10);
		textField_ValorCreditar.setBounds(325, 259, 86, 20);
		frame.getContentPane().add(textField_ValorCreditar);
		
		label_CreditarV = new JLabel("Valor:");
		label_CreditarV.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_CreditarV.setBounds(271, 262, 46, 14);
		frame.getContentPane().add(label_CreditarV);
		
		label_Transferir = new JLabel("TRANSFERIR");
		label_Transferir.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_Transferir.setBounds(504, 215, 134, 23);
		frame.getContentPane().add(label_Transferir);
		
		textField_valorTransferir = new JTextField();
		textField_valorTransferir.setColumns(10);
		textField_valorTransferir.setBounds(555, 259, 86, 20);
		frame.getContentPane().add(textField_valorTransferir);
		
		label_CreditarV_1 = new JLabel("Valor:");
		label_CreditarV_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_CreditarV_1.setBounds(504, 262, 46, 14);
		frame.getContentPane().add(label_CreditarV_1);
		
		textField_idTransferir = new JTextField();
		textField_idTransferir.setBounds(555, 290, 86, 20);
		frame.getContentPane().add(textField_idTransferir);
		textField_idTransferir.setColumns(10);
		
		label_Id = new JLabel("ID:");
		label_Id.setToolTipText("ID da conta destino");
		label_Id.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_Id.setBounds(504, 293, 27, 14);
		frame.getContentPane().add(label_Id);
		
		textField_SenhaDebitar = new JTextField();
		textField_SenhaDebitar.setColumns(10);
		textField_SenhaDebitar.setBounds(82, 315, 86, 20);
		frame.getContentPane().add(textField_SenhaDebitar);
		
		label_SenhaDebitar = new JLabel("Senha:");
		label_SenhaDebitar.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_SenhaDebitar.setBounds(31, 318, 46, 14);
		frame.getContentPane().add(label_SenhaDebitar);
		
		textField_SenhaCreditar = new JTextField();
		textField_SenhaCreditar.setColumns(10);
		textField_SenhaCreditar.setBounds(325, 318, 86, 20);
		frame.getContentPane().add(textField_SenhaCreditar);
		
		label_SenhaCreditar = new JLabel("Senha:");
		label_SenhaCreditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_SenhaCreditar.setBounds(274, 321, 46, 14);
		frame.getContentPane().add(label_SenhaCreditar);
		
		btnTransferir = new JButton("Transferir");
		btnTransferir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					labelAvisos.setText(null);
					if (table.getSelectedRow() < 0) {
						throw new Exception ("Selecione uma conta");
					}
					int idContaOrigem = (int) table.getValueAt(table.getSelectedRow(), 0);
					if (!(textField_idTransferir.getText().matches("^\\d+$") && textField_valorTransferir.getText().matches("^\\d+$")
							&& textField_cpfTransferir.getText().matches("^\\d+$") && textField_SenhaTransferir.getText().matches("^\\d+$"))) {
						throw new Exception ("Os caracteres de todos os campos devem ser numéricos!");
					}
					
					int idContaDestino = Integer.parseInt(textField_idTransferir.getText());
					String cpf = textField_cpfTransferir.getText();
					String senha = textField_SenhaTransferir.getText();
					Double valor = Double.parseDouble(textField_valorTransferir.getText());
					
					Fachada.transferirValor(idContaOrigem, cpf, senha, valor, idContaDestino);
					listagem(Fachada.listarContas());
					labelAvisos.setText("Transerência feita com sucesso!");
				} catch (Exception e1) {
					labelAvisos.setText(e1.getMessage());
				}
				
			}
		});
		btnTransferir.setBounds(504, 377, 137, 34);
		frame.getContentPane().add(btnTransferir);
		
		textField_SenhaTransferir = new JTextField();
		textField_SenhaTransferir.setColumns(10);
		textField_SenhaTransferir.setBounds(555, 346, 86, 20);
		frame.getContentPane().add(textField_SenhaTransferir);
		
		label_SenhaTransferir = new JLabel("Senha:");
		label_SenhaTransferir.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_SenhaTransferir.setBounds(504, 349, 46, 14);
		frame.getContentPane().add(label_SenhaTransferir);
		
		textField_CpfDebitar = new JTextField();
		textField_CpfDebitar.setBounds(82, 287, 86, 20);
		frame.getContentPane().add(textField_CpfDebitar);
		textField_CpfDebitar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(31, 290, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField_CpfCreditar = new JTextField();
		textField_CpfCreditar.setColumns(10);
		textField_CpfCreditar.setBounds(325, 290, 86, 20);
		frame.getContentPane().add(textField_CpfCreditar);
		
		lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(274, 293, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_cpfTransferir = new JTextField();
		textField_cpfTransferir.setColumns(10);
		textField_cpfTransferir.setBounds(555, 318, 86, 20);
		frame.getContentPane().add(textField_cpfTransferir);
		
		lblNewLabel_2 = new JLabel("CPF:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(504, 321, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
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
			model.addColumn("Titular");
			model.addColumn("Cotitulares");

			
			String texto;
			for(Conta c : lista) {
				String titular = c.getCorrentistas().getFirst().getNome();
				
				texto=" ";
				for(Correntista cr : c.getCorrentistas().subList(1, c.getCorrentistas().size())) 
					texto += cr.getNome()+ " " ;
		
				
				//linhas
				if(c instanceof ContaEspecial ce)
					//model.addRow(new Object[]{p.getEmail(), p.getNome(), p.getIdade(), ce.getEmpresa(),p.getPercentual(), texto});
					model.addRow(new Object[]{c.getId(),c.getData(),c.getSaldo(),ce.getLimite(),titular, texto});
				else
					//model.addRow(new Object[]{p.getEmail(), p.getNome(), p.getIdade(), "",p.getPercentual(), texto});
					model.addRow(new Object[]{c.getId(),c.getData(),c.getSaldo(), "",titular,texto});

			}

			table.setModel(model);
			label_6.setText("resultados: "+lista.size()+ " contas   - selecione uma linha");
		}
		catch(Exception erro){
			labelAvisos.setText(erro.getMessage());
		}
	}
}
