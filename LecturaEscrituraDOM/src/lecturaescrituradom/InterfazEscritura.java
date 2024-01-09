package lecturaescrituradom;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InterfazEscritura extends JFrame {

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
					InterfazEscritura frame = new InterfazEscritura();
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
	public InterfazEscritura() {
		setResizable(false);
		setTitle("Escritura de concesionarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 655, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228, 152, 190));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("Concesionario");
		lblTitulo.setBounds(200, 16, 267, 48);
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setFont(new Font("Malgun Gothic", Font.BOLD, 29));

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(112, 161, 59, 19);
		lblModelo.setFont(new Font("Ebrima", Font.BOLD, 14));

		textField_Marca = new JTextField();
		textField_Marca.setColumns(10);
		textField_Marca.setBounds(243, 191, 155, 20);
		contentPane.add(textField_Marca);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Ebrima", Font.BOLD, 14));
		lblMarca.setBounds(298, 161, 59, 19);
		contentPane.add(lblMarca);

		JButton btnSiguienteCoche = new JButton("Siguiente coche");
		btnSiguienteCoche.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {

	        }
	    });
		btnSiguienteCoche.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		btnSiguienteCoche.setBackground(Color.PINK);
		btnSiguienteCoche.setBounds(10, 380, 196, 35);
		contentPane.add(btnSiguienteCoche);

		JButton btnCreaXML = new JButton("Finalizar concesionario");
		btnCreaXML.setBounds(372, 380, 257, 35);
		 btnCreaXML.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {

		        }
		    });
		btnCreaXML.setBackground(Color.PINK);
		btnCreaXML.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		JLabel lblCilindrada = new JLabel("Cilindrada");
		lblCilindrada.setFont(new Font("Ebrima", Font.BOLD, 14));
		lblCilindrada.setBounds(471, 161, 78, 19);
		contentPane.add(lblCilindrada);

		textField_Cilindrada = new JTextField();
		textField_Cilindrada.setColumns(10);
		textField_Cilindrada.setBounds(427, 191, 155, 20);
		contentPane.add(textField_Cilindrada);

		textField_Modelo = new JTextField();
		textField_Modelo.setBounds(61, 191, 155, 20);
		textField_Modelo.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblTitulo);
		contentPane.add(lblModelo);
		contentPane.add(textField_Modelo);
		contentPane.add(btnCreaXML);
	}
}
