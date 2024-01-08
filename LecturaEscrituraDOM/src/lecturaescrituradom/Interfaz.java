package lecturaescrituradom;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Interfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Modelo;
	private JTextField textField_Marca;
	private JTextField textField_Cilindrada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setResizable(false);
		setTitle("Concesionario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228, 152, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Concesionario");
		lblNewLabel.setBounds(200, 16, 267, 48);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 29));
		
		JButton btnNewButton = new JButton("Anterior coche");
		btnNewButton.setBounds(10, 380, 182, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(110, 96, 59, 19);
		lblModelo.setFont(new Font("Ebrima", Font.BOLD, 14));
		
		textField_Modelo = new JTextField();
		textField_Modelo.setBounds(59, 126, 155, 20);
		textField_Modelo.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblModelo);
		contentPane.add(textField_Modelo);
		contentPane.add(btnNewButton);
		
		textField_Marca = new JTextField();
		textField_Marca.setColumns(10);
		textField_Marca.setBounds(241, 126, 155, 20);
		contentPane.add(textField_Marca);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Ebrima", Font.BOLD, 14));
		lblMarca.setBounds(296, 96, 59, 19);
		contentPane.add(lblMarca);
		
		JButton btnSiguienteCoche = new JButton("Siguiente coche");
		btnSiguienteCoche.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		btnSiguienteCoche.setBackground(Color.PINK);
		btnSiguienteCoche.setBounds(200, 380, 196, 35);
		contentPane.add(btnSiguienteCoche);
		
		JButton btnInsertaCoche = new JButton("Inserta coche");
		btnInsertaCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInsertaCoche.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		btnInsertaCoche.setBackground(Color.PINK);
		btnInsertaCoche.setBounds(447, 380, 182, 35);
		contentPane.add(btnInsertaCoche);
		
		JLabel lblCilindrada = new JLabel("Cilindrada");
		lblCilindrada.setFont(new Font("Ebrima", Font.BOLD, 14));
		lblCilindrada.setBounds(469, 96, 78, 19);
		contentPane.add(lblCilindrada);
		
		textField_Cilindrada = new JTextField();
		textField_Cilindrada.setColumns(10);
		textField_Cilindrada.setBounds(425, 126, 155, 20);
		contentPane.add(textField_Cilindrada);
	}
}
