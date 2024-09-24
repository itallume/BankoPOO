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
	private JLabel label;
	private JLabel label_6;
	private JLabel label_1;
	private JTextField textField_PesquisarId;
	private JButton button_Listar;
	private JButton button_Limpar;
	private JButton button_Debitar;
	private JTextField textField_ValorDebitar;
	private JLabel label_Debitar;
	private JLabel label_2;
	private JLabel label_Creditar;
	private JButton button_Creditar;
	private JTextField textField_ValorCreditar;
	private JLabel label_CreditarV;
	private JLabel label_Transferir;
	private JTextField textField;
	private JLabel label_CreditarV_1;
	private JTextField textField_Id;
	private JLabel label_Id;
	private JTextField textField_SenhaDebitar;
	private JLabel label_SenhaDebitar;
	private JTextField textField_SenhaCreditar;
	private JLabel label_SenhaCreditar;
	private JButton button_Transferir;
	private JTextField textField_SenhaTransferir;
	private JLabel label_SenhaTransferir;



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
		frame.setBounds(100, 100, 729, 385);
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
		label_2.setBounds(80, 215, 69, 23);
		frame.getContentPane().add(label_2);
		
		button_Debitar = new JButton("Ir");
		button_Debitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		button_Debitar.setBounds(138, 287, 46, 20);
		frame.getContentPane().add(button_Debitar);

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
		label.setBounds(21, 321, 457, 14);
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
		
		textField_ValorDebitar = new JTextField();
		textField_ValorDebitar.setBounds(98, 259, 86, 20);
		frame.getContentPane().add(textField_ValorDebitar);
		textField_ValorDebitar.setColumns(10);
		
		label_Debitar = new JLabel("Valor:");
		label_Debitar.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_Debitar.setBounds(47, 262, 46, 14);
		frame.getContentPane().add(label_Debitar);
		
		label_Creditar = new JLabel("DEPOSITAR");
		label_Creditar.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_Creditar.setBounds(267, 215, 128, 23);
		frame.getContentPane().add(label_Creditar);
		
		button_Creditar = new JButton("Ir");
		button_Creditar.setBounds(356, 287, 45, 20);
		frame.getContentPane().add(button_Creditar);
		
		textField_ValorCreditar = new JTextField();
		textField_ValorCreditar.setColumns(10);
		textField_ValorCreditar.setBounds(315, 259, 86, 20);
		frame.getContentPane().add(textField_ValorCreditar);
		
		label_CreditarV = new JLabel("Valor:");
		label_CreditarV.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_CreditarV.setBounds(264, 262, 46, 14);
		frame.getContentPane().add(label_CreditarV);
		
		label_Transferir = new JLabel("TRANSFERIR");
		button_Transferir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					int id_Destino = Integer.parseInt(textField_Id.getText());
					
					Double valor = Double.parseDouble(textField_ValorDebitar.getText());
					String senhaDigitada = textField_SenhaTransferir.getText();
					
					Conta cDestino = Fachada.procurarConta(id_Destino);
					if (cDestino == null) {
						throw new Exception ("Conta destino não existe!");
					}
					
					Conta c = Fachada.procurarConta(id);
//					String cpf = c.getCorrentistas().getFirst().getCpf();
//					String senhaCorrentista = c.getCorrentistas().getFirst().getSenha();
					
					Correntista cr = Fachada.procurarCorrentista(cpf);
					
					if (!senhaDigitada.equals(senhaCorrentista)) {
						
					}
					
					Fachada.debitarValor(id, cpf, senhaCorrentista, valor);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		label_Transferir.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_Transferir.setBounds(504, 215, 134, 23);
		frame.getContentPane().add(label_Transferir);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(555, 259, 86, 20);
		frame.getContentPane().add(textField);
		
		label_CreditarV_1 = new JLabel("Valor:");
		label_CreditarV_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_CreditarV_1.setBounds(504, 262, 46, 14);
		frame.getContentPane().add(label_CreditarV_1);
		
		textField_Id = new JTextField();
		textField_Id.setBounds(555, 287, 86, 20);
		frame.getContentPane().add(textField_Id);
		textField_Id.setColumns(10);
		
		label_Id = new JLabel("ID:");
		label_Id.setToolTipText("ID da conta destino");
		label_Id.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_Id.setBounds(504, 290, 27, 14);
		frame.getContentPane().add(label_Id);
		
		textField_SenhaDebitar = new JTextField();
		textField_SenhaDebitar.setColumns(10);
		textField_SenhaDebitar.setBounds(98, 287, 38, 20);
		frame.getContentPane().add(textField_SenhaDebitar);
		
		label_SenhaDebitar = new JLabel("Senha:");
		label_SenhaDebitar.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_SenhaDebitar.setBounds(47, 290, 46, 14);
		frame.getContentPane().add(label_SenhaDebitar);
		
		textField_SenhaCreditar = new JTextField();
		textField_SenhaCreditar.setColumns(10);
		textField_SenhaCreditar.setBounds(318, 287, 38, 20);
		frame.getContentPane().add(textField_SenhaCreditar);
		
		label_SenhaCreditar = new JLabel("Senha:");
		label_SenhaCreditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_SenhaCreditar.setBounds(264, 290, 46, 14);
		frame.getContentPane().add(label_SenhaCreditar);
		
		button_Transferir = new JButton("Ir");
		button_Transferir.setBounds(595, 315, 46, 20);
		frame.getContentPane().add(button_Transferir);
		
		textField_SenhaTransferir = new JTextField();
		textField_SenhaTransferir.setColumns(10);
		textField_SenhaTransferir.setBounds(555, 315, 38, 20);
		frame.getContentPane().add(textField_SenhaTransferir);
		
		label_SenhaTransferir = new JLabel("Senha:");
		label_SenhaTransferir.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_SenhaTransferir.setBounds(504, 318, 46, 14);
		frame.getContentPane().add(label_SenhaTransferir);
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
