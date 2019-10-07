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

import com.entities.Familia;
import com.entities.Producto;
import com.entities.Usuario;
import com.enumerated.Segmentacion;
import com.exception.ServiciosException;
import com.servicios.ProductosBeanRemote;

public class VentanaProductoB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JTable tablaProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProductoB frame = new VentanaProductoB();
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
	public VentanaProductoB() throws ServiciosException {
		setTitle("Productos");
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
					cargarProductos();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);
		
		tablaProductos = new JTable();
		cargarProductos();
		
		tablaProductos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				int    row        = tablaProductos.getSelectedRow();	
						
				int   idProducto = (int)tablaProductos.getModel().getValueAt(row, 0);
				String nombre     = (String)tablaProductos.getModel().getValueAt(row, 1);
				String lote       = (String)tablaProductos.getModel().getValueAt(row, 2);
				double precio     = (double)tablaProductos.getModel().getValueAt(row, 3);
				Date   felab      = (Date)tablaProductos.getModel().getValueAt(row, 4);
				Date   fven       = (Date)tablaProductos.getModel().getValueAt(row, 5);
				double peso       = (double)tablaProductos.getModel().getValueAt(row, 6);
				double volumen    = (double)tablaProductos.getModel().getValueAt(row, 7);
				int    estiba     = (int)tablaProductos.getModel().getValueAt(row, 8);
				double stkMin     = (double)tablaProductos.getModel().getValueAt(row, 9);
				double stkTotal   = (double)tablaProductos.getModel().getValueAt(row, 10);
				Segmentacion segmentac = (Segmentacion)tablaProductos.getModel().getValueAt(row, 11);
				
				Familia familia = new Familia();
				int aux = (int)tablaProductos.getModel().getValueAt(row, 12);
				familia.setId(aux);
				
				Usuario usuario = new Usuario();
				int auxUsu = (int)tablaProductos.getModel().getValueAt(row, 13);
				usuario.setId(auxUsu);
				
				
				Producto producto = new Producto();
				producto.setId(idProducto);
				producto.setNombre(nombre);
				producto.setLote(lote);
				producto.setPrecio(precio);
				producto.setFelab(felab);
				producto.setFven(fven);				
				producto.setPeso(peso);
				producto.setVolumen(volumen);
				producto.setEstiba(estiba);
				producto.setStkMin(stkMin);
				producto.setStkTotal(stkTotal);
				producto.setSegmentac(segmentac);
				producto.setFamilia(familia);
				producto.setUsuario(usuario);
				
				
				VentanaBMProducto ventanaBMProducto = new VentanaBMProducto(producto);
				ventanaBMProducto.setVisible(true);
			
			}
			});

		contentPane.add(tablaProductos, BorderLayout.CENTER);
		
		/*
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEditarProducto3 VentanaEditarProducto3 = new VentanaEditarProducto3(new Producto());
				VentanaEditarProducto3.setVisible(true);
			}
		});
		contentPane.add(btnCrear, BorderLayout.SOUTH);
		*/
	}
	
	private void cargarProductos() throws ServiciosException{
		try {
			ProductosBeanRemote productosBeanRemote = EJBLocator.getInstance().lookup(ProductosBeanRemote.class);
			List<Producto> productos= new ArrayList<>();
			if(textFieldBuscar.getText().isEmpty()){
				productos = productosBeanRemote.obtenerProductos();
			}else{
				productos = productosBeanRemote.obtenerProductos(textFieldBuscar.getText()+"%");
			}
			String[] columnNames = {"Id","Nombre","Lote","Precio","F.Elab.","F.Venc.","Peso","Volumen","Estiba","Stock Min.","Stock Total","Segmentac.","Familia","Usuario"};
		    DefaultTableModel model = new DefaultTableModel();
		    tablaProductos.setModel(model);

			model.setColumnIdentifiers(columnNames);
			for(Producto producto : productos){
				Object[] fila = new Object[14];
				fila[0]=producto.getId();
				fila[1]=producto.getNombre();
				fila[2]=producto.getLote();
				fila[3]=producto.getPrecio();
				fila[4]=producto.getFelab();
				fila[5]=producto.getFven();
				fila[6]=producto.getPeso();
				fila[7]=producto.getVolumen();
				fila[8]=producto.getEstiba();
				fila[9]=producto.getStkMin();
				fila[10]=producto.getStkTotal();
				fila[11]=producto.getSegmentac();
				fila[12]=producto.getFamilia().getId();
				fila[13]=producto.getUsuario().getId();
				model.addRow(fila);
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
