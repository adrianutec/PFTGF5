package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.entities.Almacenamiento;
import com.entities.Movimiento;
import com.entities.Producto;
import com.enumerated.tipoMovimiento;
import com.exception.ServiciosException;
import com.servicios.MovimientosBeanRemote;

public class VentanaMovimientoB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JTable tablaMovimientos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMovimientoB frame = new VentanaMovimientoB();
					frame.setVisible(true);

					TITULO = frame.getTitle();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String TITULO;

	/**
	 * Create the frame.
	 * @throws ServiciosException 
	 */
	public VentanaMovimientoB() throws ServiciosException {
		setTitle("Movimientos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		textFieldBuscar = new JTextField();
		panel.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cargarMovimientos();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);
		
		tablaMovimientos = new JTable();
		cargarMovimientos();
		
		tablaMovimientos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				int    row        = tablaMovimientos.getSelectedRow();	
						
				int    idMovimiento = (int)tablaMovimientos.getModel().getValueAt(row, 0);
				Date   fecha      = (Date)tablaMovimientos.getModel().getValueAt(row, 1);
				int    cantidad     = (int)tablaMovimientos.getModel().getValueAt(row, 2);
				String descipcion       = (String)tablaMovimientos.getModel().getValueAt(row, 3);
				double costo     = (double)tablaMovimientos.getModel().getValueAt(row, 4);
				tipoMovimiento tipoMov = (tipoMovimiento)tablaMovimientos.getModel().getValueAt(row, 5);
				
				Producto producto = new Producto();
				int auxUsu = (int)tablaMovimientos.getModel().getValueAt(row, 6);
				producto.setId(auxUsu);

				Almacenamiento almacenamiento = new Almacenamiento();
				int aux = (int)tablaMovimientos.getModel().getValueAt(row, 7);
				almacenamiento.setId(aux);
				
				
				
				Movimiento movimiento = new Movimiento();
				movimiento.setId(idMovimiento);
				movimiento.setFecha(fecha);
				movimiento.setCantidad(cantidad);
				movimiento.setDescripcion(descipcion);
				movimiento.setCosto(costo);
				movimiento.setTipoMov(tipoMov);
				movimiento.setAlmacenamiento(almacenamiento);
				movimiento.setProducto(producto);
				
				
				VentanaBMMovimiento ventanaBMMovimiento = new VentanaBMMovimiento(movimiento);
				ventanaBMMovimiento.setVisible(true);
			
			}
			});

		contentPane.add(tablaMovimientos, BorderLayout.CENTER);
		
		/*
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEditarMovimiento3 VentanaEditarMovimiento3 = new VentanaEditarMovimiento3(new Movimiento());
				VentanaEditarMovimiento3.setVisible(true);
			}
		});
		contentPane.add(btnCrear, BorderLayout.SOUTH);
		*/
	}
	
	private void cargarMovimientos() throws ServiciosException{
		try {
			MovimientosBeanRemote movimientosBeanRemote = EJBLocator.getInstance().lookup(MovimientosBeanRemote.class);
			List<Movimiento> movimientos= new ArrayList<>();
			if(textFieldBuscar.getText().isEmpty()){
				movimientos = movimientosBeanRemote.obtenerMovimientos();
			}else{
				movimientos = movimientosBeanRemote.obtenerMovimientos(textFieldBuscar.getText()+"%");
			}
			String[] columnNames = {"Id","Fecha","Cantidad","Descripcion","Costo","Tipo Mov.","Producto","Almacenamiento"};
		    DefaultTableModel model = new DefaultTableModel();
		    tablaMovimientos.setModel(model);

			model.setColumnIdentifiers(columnNames);
			for(Movimiento movimiento : movimientos){
				Object[] fila = new Object[8];
				fila[0]=movimiento.getId();
				fila[1]=movimiento.getFecha();
				fila[2]=movimiento.getCantidad();
				fila[3]=movimiento.getDescripcion();
				fila[4]=movimiento.getCosto();
				fila[5]=movimiento.getTipoMov();
				fila[6]=movimiento.getProducto().getId();
				fila[7]=movimiento.getAlmacenamiento().getId();
				model.addRow(fila);
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
