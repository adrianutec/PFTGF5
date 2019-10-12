package com.cliente;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Movimiento;
import com.entities.Producto;
import com.servicios.MovimientosBeanRemote;
import com.servicios.ProductosBeanRemote;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;


public class ReportePedidos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFecHasta;
	private JTextField txtFecDesde;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportePedidos frame = new ReportePedidos();
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
	public ReportePedidos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFecHasta = new JTextField();
		txtFecHasta.setBounds(158, 39, 186, 20);
		txtFecHasta.setToolTipText("Fecha Hasta");
		txtFecHasta.setText("31/12/2018");
		txtFecHasta.setColumns(10);
		contentPane.add(txtFecHasta);
		
		JLabel lblFechaHasta = new JLabel("Fecha Hasta");
		lblFechaHasta.setBounds(35, 46, 100, 14);
		contentPane.add(lblFechaHasta);
		
		JLabel lblFechaDesde = new JLabel("Fecha Desde");
		lblFechaDesde.setBounds(35, 18, 100, 14);
		contentPane.add(lblFechaDesde);
		
		txtFecDesde = new JTextField();
		txtFecDesde.setBounds(158, 11, 186, 20);
		txtFecDesde.setToolTipText("Fecha Desde");
		txtFecDesde.setText("01/01/2018");
		txtFecDesde.setColumns(10);
		contentPane.add(txtFecDesde);
		
		JTextPane txtPanel = new JTextPane();
		txtPanel.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPanel.setEditable(false);
		txtPanel.setBounds(10, 104, 551, 147);
		contentPane.add(txtPanel);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			
				
				try {

					
					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					//JFormattedTextField Felab = new JFormattedTextField(df);

					java.util.Date FDESDE = new SimpleDateFormat("dd/MM/yy").parse(txtFecDesde.getText());
					java.util.Date FHASTA = new SimpleDateFormat("dd/MM/yy").parse(txtFecHasta.getText());
					
					
					java.sql.Date sd = new java.sql.Date(FDESDE.getTime());
					java.sql.Date sh = new java.sql.Date(FHASTA.getTime());
				
					SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/yy");
				
					MovimientosBeanRemote movimientosBeanRemote = EJBLocator.getInstance().lookup(MovimientosBeanRemote.class);
					List<Movimiento> movimientos = new ArrayList<>();
					movimientos = movimientosBeanRemote.obtenerMovimientosPorFecha(txtFecDesde.getText(), txtFecHasta.getText());

					String texto = "";
					for (Movimiento mov : movimientos){
						java.util.Date date = mov.getFecha();
						String s = df.format(date);
											
						texto = texto + mov.getId() +"\t" + mov.getProducto().getId()  +"\t" + mov.getProducto().getNombre() +"\t" + mov.getDescripcion() + "\t" + s + "\n" ;
					}
					txtPanel.setText(texto);
				
				} catch (Exception e) {
					e.printStackTrace();
				}		
				
				
				
			}
		});
		btnAceptar.setBounds(93, 70, 89, 23);
		contentPane.add(btnAceptar);
	}
}
