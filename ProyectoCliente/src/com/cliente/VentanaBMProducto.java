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

import com.entities.Familia;
import com.entities.Producto;
import com.entities.Usuario;
import com.enumerated.Segmentacion;
import com.exception.ServiciosException;
import com.servicios.FamiliasBeanRemote;
import com.servicios.ProductosBeanRemote;
import com.servicios.UsuariosBeanRemote;

import javax.swing.JComboBox;

public class VentanaBMProducto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldStkTotal;
	private JTextField textFieldStkMin;
	private JTextField textFieldEstiba;
	private JTextField textFieldVolumen;
	private JTextField textFieldPeso;
	private JTextField textFieldFven;
	private JTextField textFieldFelab;
	private JTextField textFieldPrecio;
	private JTextField textFieldLote;
	private JTextField textFieldNombre;
	private JTextField textFieldId;
	private JTextField txtSegmentac;
	private JTextField txtFamilia;
	private JTextField txtUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarProducto3 frame = new VentanaEditarProducto3(new Producto());
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
	public VentanaBMProducto(Producto producto) {
		setTitle("Producto");
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
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(106, 48, 46, 14);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setText((String) null);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(231, 42, 184, 20);
		contentPane.add(textFieldNombre);
		
		
		textFieldLote = new JTextField();
		textFieldLote.setText((String) null);
		textFieldLote.setColumns(10);
		textFieldLote.setBounds(231, 74, 184, 20);
		contentPane.add(textFieldLote);
		
		
		JLabel lblLote = new JLabel("Lote");
		lblLote.setBounds(106, 80, 46, 14);
		contentPane.add(lblLote);
		
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setText("0.0");
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(231, 105, 184, 20);
		contentPane.add(textFieldPrecio);
		
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(106, 111, 46, 14);
		contentPane.add(lblPrecio);
		
		textFieldFelab = new JTextField();
		textFieldFelab.setColumns(10);
		textFieldFelab.setBounds(231, 133, 184, 20);
		contentPane.add(textFieldFelab);
		
		JLabel lblFelab = new JLabel("F.Elaboraci\u00F3n");
		lblFelab.setBounds(106, 139, 102, 14);
		contentPane.add(lblFelab);
		
		textFieldFven = new JTextField();
		textFieldFven.setColumns(10);
		textFieldFven.setBounds(231, 161, 184, 20);
		contentPane.add(textFieldFven);
		
		
		JLabel lblFven = new JLabel("F.Vencimiento");
		lblFven.setBounds(106, 167, 102, 14);
		contentPane.add(lblFven);
		
		
		textFieldPeso = new JTextField();
		textFieldPeso.setText("0.0");
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(231, 189, 184, 20);
		contentPane.add(textFieldPeso);
		
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(106, 195, 46, 14);
		contentPane.add(lblPeso);
		
		textFieldVolumen = new JTextField();
		textFieldVolumen.setColumns(10);
		textFieldVolumen.setBounds(231, 217, 184, 20);
		contentPane.add(textFieldVolumen);
		
		JLabel lblVolumen = new JLabel("Volumen");
		lblVolumen.setBounds(106, 223, 46, 14);
		contentPane.add(lblVolumen);

		JLabel lblEstiba = new JLabel("Estiba");
		lblEstiba.setBounds(106, 251, 46, 14);
		contentPane.add(lblEstiba);
		
		textFieldEstiba = new JTextField();
		textFieldEstiba.setText("0");
		textFieldEstiba.setColumns(10);
		textFieldEstiba.setBounds(231, 245, 184, 20);
		contentPane.add(textFieldEstiba);
		
		textFieldStkMin = new JTextField();
		textFieldStkMin.setColumns(10);
		textFieldStkMin.setBounds(231, 273, 184, 20);
		contentPane.add(textFieldStkMin);
		
		JLabel lblStkMin = new JLabel("Stk. M\u00EDnimo");
		lblStkMin.setBounds(106, 279, 75, 14);
		contentPane.add(lblStkMin);
		
		JLabel lblStkTotal = new JLabel("Stk. Total");
		lblStkTotal.setBounds(106, 307, 75, 14);
		contentPane.add(lblStkTotal);
		
		textFieldStkTotal = new JTextField();
		textFieldStkTotal.setColumns(10);
		textFieldStkTotal.setBounds(231, 301, 184, 20);
		contentPane.add(textFieldStkTotal);
		
		
		
		textFieldId.setText(producto.getId()==0?"":String.valueOf(producto.getId()));
		
		textFieldNombre.setText(producto.getNombre());
		textFieldLote.setText(producto.getLote());
		textFieldPrecio.setText(Double.toString(producto.getPrecio()));

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		JFormattedTextField Felab = new JFormattedTextField(df);
		Felab.addKeyListener(new KeyAdapter() {
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
		
		JFormattedTextField Fven = new JFormattedTextField(df);
		Fven.addKeyListener(new KeyAdapter() {
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
		
		if  (producto.getId()==0) {
			textFieldFven.setText("");
		}else {
			String fechaComoCadena = sdf.format(producto.getFven());
			textFieldFven.setText(fechaComoCadena);
		}
			
		if  (producto.getId()==0) {
			textFieldFelab.setText("");
		}else {
			String fechaComoCadenaE =sdf.format(producto.getFelab());
			textFieldFelab.setText(fechaComoCadenaE);
		}
		
		
		
		textFieldPeso.setText(Double.toString(producto.getPeso()));
		textFieldVolumen.setText(Double.toString(producto.getVolumen()));
		textFieldEstiba.setText(Integer.toString(producto.getEstiba()));
		textFieldStkMin.setText(Double.toString(producto.getStkMin()));
		textFieldStkTotal.setText(Double.toString(producto.getStkTotal()));

		txtSegmentac = new JTextField();
		txtSegmentac.setColumns(10);
		txtSegmentac.setBounds(231, 329, 46, 20);
		contentPane.add(txtSegmentac);
		
		txtFamilia = new JTextField();
		txtFamilia.setColumns(10);
		txtFamilia.setBounds(230, 359, 46, 20);
		contentPane.add(txtFamilia);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(230, 390, 46, 20);
		contentPane.add(txtUsuario);
		
		
		JComboBox<Segmentacion> cmbSegmentac = new JComboBox<Segmentacion>();
		cmbSegmentac.setToolTipText("Lista para seleccionar la Familia que ingresa el Producto");
		cmbSegmentac.setBounds(286, 329, 129, 20);
		cmbSegmentac.addItem(Segmentacion.N);
		cmbSegmentac.addItem(Segmentacion.S);
		contentPane.add(cmbSegmentac);
		
		
		JComboBox cmbFamilia = new JComboBox();
		cmbFamilia.setToolTipText("Lista para seleccionar la Familia que ingresa el Producto");
		cmbFamilia.setBounds(286, 359, 129, 20);
		List<Familia> familias = traigoFamilias();
		for (Familia F: familias) {
			cmbFamilia.addItem(F.getId() + " - " + F.getNombre());	
		}
		contentPane.add(cmbFamilia);
		
		
		JComboBox cmbUsuario = new JComboBox();
		cmbUsuario.setToolTipText("Lista para seleccionar el Usuario que ingresa el Producto");
		cmbUsuario.setBounds(286, 390, 129, 20);
		List<Usuario> usuario = traigoUsuarios();
		for (Usuario U : usuario) {
			cmbUsuario.addItem(U.getId() + " - " + U.getNombre() + " " + U.getApellido());	
			System.out.println(U.getId() + " - " + U.getNombre() + " " + U.getApellido());
		}
		contentPane.add(cmbUsuario);

		
		Segmentacion aux = producto.getSegmentac();
		txtSegmentac.setText(aux.toString());
		
		txtFamilia.setText(String.valueOf(producto.getFamilia().getId()));
		txtUsuario.setText(String.valueOf(producto.getUsuario().getId()));
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				producto.setNombre(textFieldNombre.getText());
				producto.setLote(textFieldLote.getText());
				
				try {
					producto.setFelab(new SimpleDateFormat("dd/mm/yyyy").parse(textFieldFelab.getText() ));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
				try {
					producto.setFven(new SimpleDateFormat("dd/mm/yyyy").parse(textFieldFven.getText() ));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				producto.setPrecio(Double.parseDouble(textFieldPrecio.getText()));
				producto.setPeso(Double.parseDouble(textFieldPeso.getText()));
				producto.setVolumen(Double.parseDouble(textFieldVolumen.getText()));
				producto.setEstiba(Integer.valueOf(textFieldEstiba.getText()));
				producto.setStkMin(Double.parseDouble(textFieldStkMin.getText()));
				producto.setStkTotal(Double.parseDouble(textFieldStkTotal.getText()));
				
				
				String aux = String.valueOf(cmbSegmentac.getSelectedItem());				
				Segmentacion tipoSeg = null;
				tipoSeg = Segmentacion.valueOf(aux);
				producto.setSegmentac(tipoSeg);
				
				String auxFam = String.valueOf(cmbFamilia.getItemAt(cmbFamilia.getSelectedIndex()));
				String[] parts = auxFam.split(" - ");
				
				int id = Integer.valueOf(parts[0].replace(" ", ""));
				String nombre = parts[1].replace(" ", ""); 

				Familia famProd = new Familia();
				famProd.setId(id);
				famProd.setNombre(nombre);
				producto.setFamilia(famProd);
						
				String auxUsu = String.valueOf(cmbUsuario.getItemAt(cmbUsuario.getSelectedIndex()));
				String[] partsUsu = auxUsu.split(" - ");
				
				int idUsu = Integer.valueOf(partsUsu[0].replace(" ", ""));
				
				Usuario usuProd = new Usuario();
				usuProd.setId(idUsu);
				producto.setUsuario(usuProd);				
				
				
				ProductosBeanRemote productosBeanRemote;
				try {
					productosBeanRemote = EJBLocator.getInstance().lookup(ProductosBeanRemote.class);
					if(producto.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Producto no Existe para Modificar'");
						//productosBeanRemote.crear(producto);
					}else {
						productosBeanRemote.actualizar(producto);
						JOptionPane.showMessageDialog(null, "Producto " + producto.getNombre() + " Actualizado'");
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
		btnModificar.setBounds(63, 431, 118, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			
				ProductosBeanRemote productosBeanRemote;
				try {
					productosBeanRemote = EJBLocator.getInstance().lookup(ProductosBeanRemote.class);
					if(producto.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Producto no Existe para Eliminar'");
						//productosBeanRemote.crear(producto);
					}else {
						productosBeanRemote.borrar(producto.getId());
						JOptionPane.showMessageDialog(null, "Producto " + producto.getNombre() + " fue Eliminado'");
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
				VentanaProductoB ventanaProductoB;
				try {
					ventanaProductoB = new VentanaProductoB();
					ventanaProductoB.setVisible(true);
					ventanaProductoB.setTitle("BUSCAR PRODUCTO");
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnBuscar_1.setBounds(63, 457, 376, 23);
		contentPane.add(btnBuscar_1);
		
		JLabel label = new JLabel("Usuario");
		label.setBounds(106, 396, 91, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Familia");
		label_1.setBounds(106, 365, 91, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Segmentaci\u00F3n");
		label_2.setBounds(106, 335, 113, 14);
		contentPane.add(label_2);
		
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VentanaProductoB ventanaProductoB = new VentanaProductoB();
					ventanaProductoB.setVisible(true);
					ventanaProductoB.setTitle("BUSCAR PRODUCTO");
				
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		

	}

	public void limpioCajasTexto() {
		textFieldId.setText("");
		textFieldNombre.setText("");
		textFieldLote.setText("");
		textFieldPrecio.setText("");
		textFieldFelab.setText("");
		textFieldFven.setText("");
		textFieldPeso.setText("");
		textFieldVolumen.setText("");
		textFieldEstiba.setText("");
		textFieldStkMin.setText("");
		textFieldStkTotal.setText("");
		txtSegmentac.setText("");
		txtFamilia.setText("");
		txtUsuario.setText("");
	}


	public static List<Familia> traigoFamilias() {
		FamiliasBeanRemote familiasBeanRemote;
		try {
			familiasBeanRemote = EJBLocator.getInstance().lookup(FamiliasBeanRemote.class);
			return familiasBeanRemote.obtenerFamilias();

		} catch (NamingException | ServiciosException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Usuario> traigoUsuarios() {
		UsuariosBeanRemote usuariosBeanRemote;
		try {
			usuariosBeanRemote = EJBLocator.getInstance().lookup(UsuariosBeanRemote.class);
			return usuariosBeanRemote.obtenerUsuarios();

		} catch (NamingException | ServiciosException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
