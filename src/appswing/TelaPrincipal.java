package appswing;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal {

	private JFrame frame;
	private JLabel ImagemLabel;
	private JMenuBar menuBar;
	private JMenu mnConta;
	private JMenu mnCorrentista;
	private JMenu mnCaixa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		ImagemLabel = new JLabel("New label");
		ImagemLabel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(ImagemLabel);
		ImageIcon imagem = new ImageIcon(getClass().getResource("/arquivos/Designer.jpeg"));
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(ImagemLabel.getWidth(),ImagemLabel.getHeight(), Image.SCALE_DEFAULT));//		label.setIcon(fotos);
		ImagemLabel.setIcon(imagem);
		frame.getContentPane().add(ImagemLabel);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnConta= new JMenu("Conta");
		mnConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaConta tela = new TelaConta();
			}
		});
		
		mnCorrentista= new JMenu("Correntista");
		mnCorrentista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaCorrentista tela = new TelaCorrentista();
			}
		});
		
		mnCaixa =new JMenu("Caixa");
		
		menuBar.add(mnConta);
		menuBar.add(mnCorrentista);
		menuBar.add(mnCaixa);

		

	}
}
