package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Almacenamiento;
import com.entities.Movimiento;
import com.entities.Producto;
import com.enumerated.tipoMovimiento;
import com.exception.ServiciosException;
import com.servicios.AlmacenamientosBeanRemote;
import com.servicios.MovimientosBeanRemote;
import com.servicios.ProductosBeanRemote;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;


public class VentanaEditarMovimiento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldFecha;
	private JTextField textFieldCantidad;
	private JTextField textFieldDescripcion;
	private JTextField textFieldCosto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarMovimiento frame = new VentanaEditarMovimiento(new Movimiento());
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
	public VentanaEditarMovimiento(Movimiento movimiento) {
		setTitle("Movimiento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_1 = new JButton("CANCELAR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window ventana = FocusManager.getCurrentManager().getActiveWindow(); 
				ventana.dispose();
			}
		});
		button_1.setBounds(254, 428, 100, 23);
		contentPane.add(button_1);
		
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(95, 22, 46, 14);
		contentPane.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setText("0");
		textFieldId.setEnabled(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(218, 15, 186, 20);
		contentPane.add(textFieldId);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(95, 144, 113, 14);
		contentPane.add(lblFecha);

		textFieldFecha = new JTextField();
		textFieldFecha.setToolTipText("Fecha del Movimiento en el formato dd/mm/aaaa");
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(218, 137, 186, 20);
		contentPane.add(textFieldFecha);
		
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(95, 256, 46, 14);
		contentPane.add(lblCantidad);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldCantidad.setToolTipText("Cantidad del Movimiento");
		textFieldCantidad.setText("0");
		textFieldCantidad.setColumns(10);
		textFieldCantidad.setBounds(218, 249, 186, 20);
		contentPane.add(textFieldCantidad);

		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(95, 85, 46, 14);
		contentPane.add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setToolTipText("Descripcion del Movimiento");
		textFieldDescripcion.setText((String) null);
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(218, 78, 186, 20);
		contentPane.add(textFieldDescripcion);
		
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(95, 116, 46, 14);
		contentPane.add(lblCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char validar = arg0.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		textFieldCosto.setToolTipText("Costo Unitario del Prodcuto");
		textFieldCosto.setText("0.0");
		textFieldCosto.setColumns(10);
		textFieldCosto.setBounds(218, 109, 186, 20);
		contentPane.add(textFieldCosto);
		
		
		JLabel lbltipoMov = new JLabel("Tipo Movimiento");
		lbltipoMov.setBounds(95, 342, 113, 14);
		contentPane.add(lbltipoMov);
		

		
		
		
		
		textFieldId.setText(movimiento.getId()==0?"":String.valueOf(movimiento.getId()));

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		JFormattedTextField Fecha = new JFormattedTextField(df);
		Fecha.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
		      {
		        JOptionPane.showMessageDialog(null, "Ingrese dato v�lido");
		        e.consume();
		      }
		    }
		  });
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
		if  (movimiento.getId()==0) {
			textFieldFecha.setText("");
		}else {
			String fechaComoCadenaE =sdf.format(movimiento.getFecha());
			textFieldFecha.setText(fechaComoCadenaE);
		}
				
		textFieldCantidad.setText(Integer.toString(movimiento.getCantidad()));

		textFieldDescripcion.setText(movimiento.getDescripcion());

		textFieldCosto.setText(Double.toString(movimiento.getCosto()));

		JComboBox<tipoMovimiento> cmbtipoMov = new JComboBox<tipoMovimiento>();
		cmbtipoMov.setToolTipText("Lista para seleccionar el Tipo de Movimiento");
		cmbtipoMov.setBounds(218, 336, 186, 20);
		cmbtipoMov.addItem(tipoMovimiento.M);
		cmbtipoMov.addItem(tipoMovimiento.P);
		contentPane.add(cmbtipoMov);
		
		JComboBox cmbProducto = new JComboBox();
		cmbProducto.setToolTipText("Lista para seleccionar el Producto asociado al Movimiento");
		cmbProducto.setBounds(218, 397, 186, 20);
		List<Producto> producto = traigoProductos();
		for (Producto p : producto) {
			cmbProducto.addItem(p.getId() + " - " + p.getNombre());	
		}
		contentPane.add(cmbProducto);
		
		JComboBox cmbAlmacenamiento = new JComboBox();
		cmbAlmacenamiento.setToolTipText("Lista para seleccionar el Almacenamiento asociado al Movimiento");
		cmbAlmacenamiento.setBounds(218, 366, 186, 20);
		List<Almacenamiento> almacenamientos = traigoAlmacenamientos();
		for (Almacenamiento a: almacenamientos) {
			cmbAlmacenamiento.addItem(a.getId() + " - " + a.getNombre());	
		}
		contentPane.add(cmbAlmacenamiento);
		
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					movimiento.setFecha(new SimpleDateFormat("dd/mm/yyyy").parse(textFieldFecha.getText() ));
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "La fecha est� mal ingresada. El formato debe ser dd/mm/aaaa");
				}

				movimiento.setCantidad(Integer.parseInt(textFieldCantidad.getText()));
				movimiento.setDescripcion(textFieldDescripcion.getText());
				movimiento.setCosto(Double.parseDouble(textFieldCosto.getText()));				
				
				String aux = String.valueOf(cmbtipoMov.getSelectedItem());				
				tipoMovimiento tipoSeg = null;
				tipoSeg = tipoMovimiento.valueOf(aux);
				movimiento.setTipoMov(tipoSeg);
				
						
				String auxPro = String.valueOf(cmbProducto.getItemAt(cmbProducto.getSelectedIndex()));
				String[] partsPro = auxPro.split(" - ");
				int idPro = Integer.valueOf(partsPro[0].replace(" ", ""));
				String nombrePro = partsPro[1].replace(" ", ""); 
				
				Producto proProd = new Producto();
				proProd.setId(idPro);
				proProd.setNombre(nombrePro);
				movimiento.setProducto(proProd);			
				
				String auxAlm = String.valueOf(cmbAlmacenamiento.getItemAt(cmbAlmacenamiento.getSelectedIndex()));
				String[] parts = auxAlm.split(" - ");
				int id = Integer.valueOf(parts[0].replace(" ", ""));
				String nombre = parts[1].replace(" ", ""); 
				
				Almacenamiento almProd = new Almacenamiento();
				almProd.setId(id);
				almProd.setNombre(nombre);
				movimiento.setAlmacenamiento(almProd);

				// controlar que se ingresen los datos requeridos
				if (textFieldFecha.getText().length()>0 &
					textFieldCantidad.getText().length()>0 &
					textFieldDescripcion.getText().length()>0 &
					textFieldCosto.getText().length()>0	) {
				
					MovimientosBeanRemote movimientosBeanRemote;
					try {
						Producto producto = new Producto();
						producto = movimiento.getProducto();
						String nombreProducto;
						nombreProducto = producto.getNombre();
		    		
						ProductosBeanRemote productosBeanRemote = EJBLocator.getInstance().lookup(ProductosBeanRemote.class);
						Producto productoBD = new Producto();
						productoBD	= productosBeanRemote.obtenerProductoPorNombre(nombreProducto);

						if (productoBD.getStkTotal() < movimiento.getCantidad()) {
							JOptionPane.showMessageDialog(null,"Stock insuficiente de Producto para registrar la Perdida, por favor revise sus datos."); 
						}else {

							movimientosBeanRemote = EJBLocator.getInstance().lookup(MovimientosBeanRemote.class);
							if(movimiento.getId()==0){
								movimientosBeanRemote.crear(movimiento);
							}else {
								//movimientosBeanRemote.actualizar(movimiento);
						}
					
					}
					limpioCajasTexto();
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else {
					JOptionPane.showMessageDialog(null, "Es necesario ingresar todos los datos requeridos");
				}

			
			}
		});
		button.setBounds(140, 428, 100, 23);
		contentPane.add(button);
		
		JLabel lblAlmacenamiento = new JLabel("Almacenamiento");
		lblAlmacenamiento.setBounds(95, 372, 91, 14);
		contentPane.add(lblAlmacenamiento);
		
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(95, 403, 91, 14);
		contentPane.add(lblProducto);
		
			
	}

	public void limpioCajasTexto() {
		textFieldId.setText("");
		textFieldFecha.setText("");
		textFieldCantidad.setText("");
		textFieldDescripcion.setText("");
		textFieldCosto.setText("");
	}
	

	public static List<Almacenamiento> traigoAlmacenamientos() {
		AlmacenamientosBeanRemote almacenamientosBeanRemote;
		try {
			almacenamientosBeanRemote = EJBLocator.getInstance().lookup(AlmacenamientosBeanRemote.class);
			return almacenamientosBeanRemote.obtenerAlmacenamientos();

		} catch (NamingException | ServiciosException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Producto> traigoProductos() {
		ProductosBeanRemote productosBeanRemote;
		try {
			productosBeanRemote = EJBLocator.getInstance().lookup(ProductosBeanRemote.class);
			return productosBeanRemote.obtenerProductos();

		} catch (NamingException | ServiciosException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}