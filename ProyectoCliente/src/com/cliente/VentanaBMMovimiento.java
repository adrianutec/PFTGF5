package com.cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entities.Almacenamiento;
import com.entities.Movimiento;
import com.entities.Producto;
import com.enumerated.tipoMovimiento;
import com.exception.ServiciosException;
import com.servicios.AlmacenamientosBeanRemote;
import com.servicios.MovimientosBeanRemote;
import com.servicios.ProductosBeanRemote;

import javax.swing.JComboBox;

public class VentanaBMMovimiento extends JFrame {

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
	private JTextField txttipoMov;
	private JTextField txtProducto;
	private JTextField txtAlmacenamiento;

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
	public VentanaBMMovimiento(Movimiento movimiento) {
		setTitle("Movimiento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_1 = new JButton("CANCELAR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button_1.setBounds(321, 431, 118, 23);
		contentPane.add(button_1);
		
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(106, 17, 46, 14);
		contentPane.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setText("0");
		textFieldId.setEnabled(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(231, 11, 184, 20);
		contentPane.add(textFieldId);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(106, 139, 102, 14);
		contentPane.add(lblFecha);

		textFieldFecha = new JTextField();
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(231, 133, 184, 20);
		contentPane.add(textFieldFecha);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(106, 251, 46, 14);
		contentPane.add(lblCantidad);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setText("0");
		textFieldCantidad.setColumns(10);
		textFieldCantidad.setBounds(231, 245, 184, 20);
		contentPane.add(textFieldCantidad);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(106, 80, 46, 14);
		contentPane.add(lblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setText((String) null);
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(231, 74, 184, 20);
		contentPane.add(textFieldDescripcion);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(106, 111, 46, 14);
		contentPane.add(lblCosto);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setText("0.0");
		textFieldCosto.setColumns(10);
		textFieldCosto.setBounds(231, 105, 184, 20);
		contentPane.add(textFieldCosto);
		
		
		
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
		        JOptionPane.showMessageDialog(null, "Please Enter Valid");
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

		txttipoMov = new JTextField();
		txttipoMov.setColumns(10);
		txttipoMov.setBounds(231, 329, 46, 20);
		contentPane.add(txttipoMov);
		
		txtProducto = new JTextField();
		txtProducto.setColumns(10);
		txtProducto.setBounds(230, 390, 46, 20);
		contentPane.add(txtProducto);
		
		txtAlmacenamiento = new JTextField();
		txtAlmacenamiento.setColumns(10);
		txtAlmacenamiento.setBounds(230, 359, 46, 20);
		contentPane.add(txtAlmacenamiento);
		
		JComboBox<tipoMovimiento> cmbtipoMov = new JComboBox<tipoMovimiento>();
		cmbtipoMov.setToolTipText("Lista para seleccionar el tipo de Movimiento");
		cmbtipoMov.setBounds(286, 329, 129, 20);
		cmbtipoMov.addItem(tipoMovimiento.M);
		cmbtipoMov.addItem(tipoMovimiento.P);
		contentPane.add(cmbtipoMov);		
		
		JComboBox cmbProducto = new JComboBox();
		cmbProducto.setToolTipText("Lista para seleccionar el Producto que ingresa el Movimiento");
		cmbProducto.setBounds(286, 390, 129, 20);
		List<Producto> producto = traigoProductos();
		for (Producto U : producto) {
			cmbProducto.addItem(U.getId() + " - " + U.getNombre());	
		}
		contentPane.add(cmbProducto);

		JComboBox cmbAlmacenamiento = new JComboBox();
		cmbAlmacenamiento.setToolTipText("Lista para seleccionar la Almacenamiento que ingresa el Movimiento");
		cmbAlmacenamiento.setBounds(286, 359, 129, 20);
		List<Almacenamiento> almacenamientos = traigoAlmacenamientos();
		for (Almacenamiento F: almacenamientos) {
			cmbAlmacenamiento.addItem(F.getId() + " - " + F.getNombre());	
		}
		contentPane.add(cmbAlmacenamiento);
		
		tipoMovimiento aux = movimiento.getTipoMov();
		txttipoMov.setText(aux.toString());
		
		txtProducto.setText(String.valueOf(movimiento.getProducto().getId()));
		txtAlmacenamiento.setText(String.valueOf(movimiento.getAlmacenamiento().getId()));
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					movimiento.setFecha(new SimpleDateFormat("dd/mm/yyyy").parse(textFieldFecha.getText() ));
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "La fecha esta mal ingresada. El formato debe ser dd/mm/aaaa");
				}
				
				movimiento.setCantidad(Integer.valueOf(textFieldCantidad.getText()));
				movimiento.setDescripcion(textFieldDescripcion.getText());
				movimiento.setCosto(Double.parseDouble(textFieldCosto.getText()));
				
				String aux = String.valueOf(cmbtipoMov.getSelectedItem());				
				tipoMovimiento tipoMov = null;
				tipoMov = tipoMovimiento.valueOf(aux);
				movimiento.setTipoMov(tipoMov);
				
						
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
				
				// controlar que no sea modificacion de un registro de perdida
				if (!tipoMov.equals(tipoMovimiento.P)) {
					
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
							JOptionPane.showMessageDialog(null, "EL Movimiento no Existe para Modificar'");
							//movimientosBeanRemote.crear(movimiento);
						}else {
							movimientosBeanRemote.actualizar(movimiento);
							JOptionPane.showMessageDialog(null, "Movimiento " + movimiento.getDescripcion() + " Actualizado'");
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
				}else {
					JOptionPane.showMessageDialog(null, "El sistema no permite realizar Modificaciones de Pérdidas, en caso de error se debe Eliminar e ingresar nuevamente la Pérdida.");
				}

			}
		});
		btnModificar.setBounds(63, 431, 118, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			
				MovimientosBeanRemote movimientosBeanRemote;
				try {
					movimientosBeanRemote = EJBLocator.getInstance().lookup(MovimientosBeanRemote.class);
					if(movimiento.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Movimiento no Existe para Eliminar'");
						//movimientosBeanRemote.crear(movimiento);
					}else {
						movimientosBeanRemote.borrar(movimiento.getId());
						JOptionPane.showMessageDialog(null, "Movimiento " + movimiento.getDescripcion() + " fue Eliminado'");
					}
					limpioCajasTexto();
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			}
		});
		btnEliminar.setBounds(193, 431, 118, 23);
		contentPane.add(btnEliminar);
		
		JButton btnBuscar_1 = new JButton("BUSCAR");
		btnBuscar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBuscar_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				VentanaMovimientoB ventanaMovimientoB;
				try {
					ventanaMovimientoB = new VentanaMovimientoB();
					ventanaMovimientoB.setVisible(true);
					ventanaMovimientoB.setTitle("BUSCAR MOVIMIENTO");
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBuscar_1.setBounds(63, 457, 376, 23);
		contentPane.add(btnBuscar_1);
		
		JLabel label = new JLabel("Producto");
		label.setBounds(106, 396, 91, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Almacenamiento");
		label_1.setBounds(106, 365, 91, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("tipoMovi\u00F3n");
		label_2.setBounds(106, 335, 113, 14);
		contentPane.add(label_2);
		
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VentanaMovimientoB ventanaMovimientoB = new VentanaMovimientoB();
					ventanaMovimientoB.setVisible(true);
					ventanaMovimientoB.setTitle("BUSCAR MOVIMIENTO");
				
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		

	}

	public void limpioCajasTexto() {
		textFieldId.setText("");
		textFieldFecha.setText("");
		textFieldCantidad.setText("");
		textFieldDescripcion.setText("");
		textFieldCosto.setText("");
		txttipoMov.setText("");
		txtProducto.setText("");
		txtAlmacenamiento.setText("");
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