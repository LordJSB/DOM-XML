package lecturaescrituradom;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InterfazLectura extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazLectura frame = new InterfazLectura();
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
	public InterfazLectura() {
		setResizable(false);
		setTitle("Lectura de concesionarios");
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
		contentPane.setLayout(null);
		contentPane.add(lblTitulo);

		JTextPane tPFileChooser = new JTextPane();
		tPFileChooser.setBounds(222, 75, 358, 20);
		contentPane.add(tPFileChooser);

		JButton btnJFC = new JButton("Selecciona archivo: ");
		btnJFC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFC = new JFileChooser();
				jFC.showOpenDialog(jFC);
				File archivo = jFC.getSelectedFile();
				tPFileChooser.setText(archivo.getName());
				while (true) {
					if (archivo != null && archivo.getName().toLowerCase().endsWith(".xml")) {
						DOM doc = new DOM(jFC.getSelectedFile().getAbsolutePath());
						DefaultTableModel modelo = (DefaultTableModel) table.getModel();
						modelo.setRowCount(0);
						List<Coche> coches = doc.leerCoches();
						for (Coche coche : coches) {
							modelo.addRow(new Object[] { coche.getConcesionario(), "Coche", coche.getMarca(),
									coche.getModelo(), coche.getCilindrada() });
						}
						break;
					} else {
						JOptionPane.showMessageDialog(null,
								"ERROR. Archivo no válido. Inserte la ruta de un archivo XML.");
					}
				}
			}
		});
		btnJFC.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
		btnJFC.setBackground(Color.PINK);
		btnJFC.setBounds(35, 65, 182, 35);
		contentPane.add(btnJFC);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 137, 545, 266);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] { 
					{ null, null, null, null, null }, 
					{ null, null, null, null, null },
					{ null, null, null, null, null }, },
				new String[] { "Concesionario", "Coche", "Marca", "Modelo", "Cilindrada" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(92);
		table.getColumnModel().getColumn(1).setPreferredWidth(92);
		table.getColumnModel().getColumn(2).setPreferredWidth(92);
		table.getColumnModel().getColumn(3).setPreferredWidth(92);
		table.getColumnModel().getColumn(4).setPreferredWidth(92);
		scrollPane.setColumnHeaderView(table);
	}
}
