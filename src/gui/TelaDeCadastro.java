package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dao.UsuarioDAO;
import model.Usuario;

public class TelaDeCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtNome;
	private JPasswordField pswSenha;
	private JPasswordField pswConfSenha;
	protected int xMouseOld;
	protected int yMouseOld;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeCadastro frame = new TelaDeCadastro();
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
	public TelaDeCadastro() {

		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 490);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Atributos da classe para armazenar a posição da janela antes de arrastá-la
				xMouseOld = e.getX();
				yMouseOld = e.getY();
			}
		});

		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xMouseNew = e.getX();
				int yMouseNew = e.getY();
				Frame framePrincipal = (Frame) contentPane.getParent().getParent().getParent().getParent();

				int x = getX() + (xMouseNew - xMouseOld);
				int y = getY() + (yMouseNew - yMouseOld);
				setLocation(x, y);
			}
		});

		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 400, 74);
		panel_1.setBackground(new Color(70, 130, 180));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCadastro = new JLabel("Cadastro de Usu\u00E1rio");
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setForeground(Color.WHITE);
		lblCadastro.setFont(new Font("Times New Roman", Font.BOLD, 36));
		lblCadastro.setBounds(10, 11, 380, 52);
		panel_1.add(lblCadastro);

		JPanel panel = new JPanel();
		panel.setBounds(0, 73, 400, 417);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCredenciais = new JLabel("Informe suas credenciais");
		lblCredenciais.setForeground(Color.GRAY);
		lblCredenciais.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCredenciais.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredenciais.setBounds(10, 11, 380, 31);
		panel.add(lblCredenciais);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// instanciando a classe Usuario do pacote modelo e criando seu objeto usuarios
				Usuario usuarios = new Usuario();
				usuarios.setNome(txtNome.getText());
				usuarios.setEmail(txtEmail.getText());
				usuarios.setSenha(pswSenha.getText());

				// fazendo a validação dos dados
				if ((txtNome.getText().isEmpty()) || (txtEmail.getText().isEmpty()) || (pswSenha.getText().isEmpty())
						|| (pswConfSenha.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
				} 
				
				else {
					// instanciando a classe UsuarioDAO do pacote dao e criando seu objeto dao
					UsuarioDAO dao;
					try {
						dao = new UsuarioDAO();
						dao.adiciona(usuarios);
						JOptionPane.showMessageDialog(null, "Usuário " + txtNome.getText() + " inserido com sucesso! ");
						// apaga os dados preenchidos nos campos de texto
						txtNome.setText("");
						txtEmail.setText("");
						pswSenha.setText("");
						pswConfSenha.setText("");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnCadastrar.setBackground(new Color(58, 65, 84));
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCadastrar.setBounds(20, 316, 340, 39);
		panel.add(btnCadastrar);

		JButton btnSair = new JButton("Sair");
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSair.setBackground(new Color(235, 235, 235));
				btnSair.setForeground(new Color(217, 81, 51));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSair.setBackground(new Color(217, 81, 51));
				btnSair.setForeground(Color.WHITE);
			}
		});

		btnSair.setBackground(new Color(255, 51, 51));
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(20, 366, 340, 39);
		panel.add(btnSair);

		txtNome = new JTextField();

		txtNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNome.setText("");
			}
		});

		if (txtNome.getText().length() == 0) {
			txtNome.setText("Digite seu nome aqui");
		}
		txtNome.setColumns(10);
		txtNome.setBounds(56, 59, 310, 40);
		panel.add(txtNome);

		txtEmail = new JTextField();

		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEmail.setText("");
			}
		});
		if (txtEmail.getText().length() == 0) {
			txtEmail.setText("exemplo@gmail.com");
		}
		txtEmail.setBounds(56, 121, 310, 40);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblIconNome = new JLabel("");
		lblIconNome.setIcon(new ImageIcon(TelaDeCadastro.class.getResource("/resources/user32px.png")));
		lblIconNome.setBounds(20, 59, 32, 40);
		panel.add(lblIconNome);

		JLabel lblIconSenha = new JLabel("");
		lblIconSenha.setIcon(new ImageIcon(TelaDeCadastro.class.getResource("/resources/senha32px.png")));
		lblIconSenha.setBounds(20, 184, 32, 40);
		panel.add(lblIconSenha);

		JLabel lblIconSenha2 = new JLabel("");
		lblIconSenha2.setIcon(new ImageIcon(TelaDeCadastro.class.getResource("/resources/senha32px.png")));
		lblIconSenha2.setBounds(20, 248, 32, 40);
		panel.add(lblIconSenha2);

		JLabel lblIconEmail = new JLabel("");
		lblIconEmail.setIcon(new ImageIcon(TelaDeCadastro.class.getResource("/resources/email32px.png")));
		lblIconEmail.setBounds(20, 121, 32, 40);
		panel.add(lblIconEmail);

		pswSenha = new JPasswordField();
		pswSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pswSenha.setText("");
			}
		});
		if (pswSenha.getText().length() == 0) {
			pswSenha.setText("Digite sua senha.");
		}
		pswSenha.setBounds(56, 184, 304, 40);
		panel.add(pswSenha);

		pswConfSenha = new JPasswordField();
		pswConfSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pswConfSenha.setText("");
			}
		});
		if (pswConfSenha.getText().length() == 0) {
			pswConfSenha.setText("Digite sua senha.");
		}
		pswConfSenha.setBounds(56, 248, 304, 40);
		panel.add(pswConfSenha);

		JLabel lblNome = new JLabel("Nome*");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNome.setBounds(62, 42, 110, 14);
		panel.add(lblNome);

		JLabel lblEmail = new JLabel("Email*");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblEmail.setBounds(62, 103, 110, 14);
		panel.add(lblEmail);

		JLabel lblSenha = new JLabel("Senha*");
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblSenha.setBounds(62, 163, 110, 14);
		panel.add(lblSenha);

		JLabel lblConfSenha = new JLabel("Confirmar Senha*");
		lblConfSenha.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblConfSenha.setBounds(56, 228, 116, 21);
		panel.add(lblConfSenha);
	}
}
