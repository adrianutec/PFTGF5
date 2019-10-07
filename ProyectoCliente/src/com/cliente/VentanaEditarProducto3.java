package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Familia;
import com.entities.Producto;
import com.entities.Usuario;
import com.enumerated.Segmentacion;
import com.exception.ServiciosException;
import com.servicios.FamiliasBeanRemote;
import com.servicios.ProductosBeanRemote;
import com.servicios.UsuariosBeanRemote;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


public class VentanaEditarProducto3 extends JFrame {

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
	public VentanaEditarProducto3(Producto producto) {
		setTitle("Producto");
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
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(95, 53, 46, 14);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setToolTipText("Nombre del Producto");
		textFieldNombre.setText((String) null);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(218, 46, 186, 20);
		contentPane.add(textFieldNombre);
		
		
		textFieldLote = new JTextField();
		textFieldLote.setToolTipText("Lote del Producto");
		textFieldLote.setText((String) null);
		textFieldLote.setColumns(10);
		textFieldLote.setBounds(218, 78, 186, 20);
		contentPane.add(textFieldLote);
		
		
		JLabel lblLote = new JLabel("Lote");
		lblLote.setBounds(95, 85, 46, 14);
		contentPane.add(lblLote);
		
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char validar = arg0.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		textFieldPrecio.setToolTipText("Precio Unitario del Prodcuto");
		textFieldPrecio.setText("0.0");
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(218, 109, 186, 20);
		contentPane.add(textFieldPrecio);
		
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(95, 116, 46, 14);
		contentPane.add(lblPrecio);
		
		textFieldFelab = new JTextField();
		textFieldFelab.setToolTipText("Fecha de Elaboraci\u00F3n del Producto en el formato dd/mm/aaaa");
		textFieldFelab.setColumns(10);
		textFieldFelab.setBounds(218, 137, 186, 20);
		contentPane.add(textFieldFelab);
		
		JLabel lblFelab = new JLabel("F.Elaboraci\u00F3n");
		lblFelab.setBounds(95, 144, 113, 14);
		contentPane.add(lblFelab);
		
		textFieldFven = new JTextField();
		textFieldFven.setToolTipText("Fecha de Vencimiento del Producto en el formato dd/mm/aaaa");
		textFieldFven.setColumns(10);
		textFieldFven.setBounds(218, 165, 186, 20);
		contentPane.add(textFieldFven);
		
		
		JLabel lblFven = new JLabel("F.Vencimiento");
		lblFven.setBounds(95, 172, 113, 14);
		contentPane.add(lblFven);
		
		
		textFieldPeso = new JTextField();
		textFieldPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldPeso.setToolTipText("Peso del Producto");
		textFieldPeso.setText("0.0");
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(218, 193, 186, 20);
		contentPane.add(textFieldPeso);
		
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(95, 200, 46, 14);
		contentPane.add(lblPeso);
		
		textFieldVolumen = new JTextField();
		textFieldVolumen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldVolumen.setToolTipText("Volumen del Producto");
		textFieldVolumen.setText("0.0");
		textFieldVolumen.setColumns(10);
		textFieldVolumen.setBounds(218, 221, 186, 20);
		contentPane.add(textFieldVolumen);
		
		JLabel lblVolumen = new JLabel("Volumen");
		lblVolumen.setBounds(95, 228, 46, 14);
		contentPane.add(lblVolumen);

		JLabel lblEstiba = new JLabel("Estiba");
		lblEstiba.setBounds(95, 256, 46, 14);
		contentPane.add(lblEstiba);
		
		textFieldEstiba = new JTextField();
		textFieldEstiba.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldEstiba.setToolTipText("Cantidad Apliable del Producto");
		textFieldEstiba.setText("0");
		textFieldEstiba.setColumns(10);
		textFieldEstiba.setBounds(218, 249, 186, 20);
		contentPane.add(textFieldEstiba);
		
		textFieldStkMin = new JTextField();
		textFieldStkMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldStkMin.setToolTipText("Stock M\u00EDnimo para emitir aviso del Producto");
		textFieldStkMin.setText("0.0");
		textFieldStkMin.setColumns(10);
		textFieldStkMin.setBounds(218, 277, 186, 20);
		contentPane.add(textFieldStkMin);
		
		JLabel lblStkMin = new JLabel("Stk. M\u00EDnimo");
		lblStkMin.setBounds(95, 284, 75, 14);
		contentPane.add(lblStkMin);
		
		JLabel lblStkTotal = new JLabel("Stk. Total");
		lblStkTotal.setBounds(95, 312, 75, 14);
		contentPane.add(lblStkTotal);
		
		textFieldStkTotal = new JTextField();
		textFieldStkTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldStkTotal.setToolTipText("Stock Total del Producto");
		textFieldStkTotal.setText("0.0");
		textFieldStkTotal.setColumns(10);
		textFieldStkTotal.setBounds(218, 305, 186, 20);
		contentPane.add(textFieldStkTotal);
		
		
		JLabel lblSegmentac = new JLabel("Segmentaci\u00F3n");
		lblSegmentac.setBounds(95, 342, 113, 14);
		contentPane.add(lblSegmentac);
		
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
		        JOptionPane.showMessageDialog(null, "Ingrese dato v�lido");
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
		        JOptionPane.showMessageDialog(null, "Ingrese dato V�lido");
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
		
		JComboBox<Segmentacion> cmbSegmentac = new JComboBox<Segmentacion>();
		cmbSegmentac.setToolTipText("Lista para seleccionar la Familia que ingresa el Producto");
		cmbSegmentac.setBounds(218, 336, 186, 20);
		cmbSegmentac.addItem(Segmentacion.N);
		cmbSegmentac.addItem(Segmentacion.S);
		contentPane.add(cmbSegmentac);
		
		JComboBox cmbFamilia = new JComboBox();
		cmbFamilia.setToolTipText("Lista para seleccionar la Familia que ingresa el Producto");
		cmbFamilia.setBounds(218, 366, 186, 20);
		List<Familia> familias = traigoFamilias();
		for (Familia F: familias) {
			cmbFamilia.addItem(F.getId() + " - " + F.getNombre());	
		}
		contentPane.add(cmbFamilia);
		
		
		JComboBox cmbUsuario = new JComboBox();
		cmbUsuario.setToolTipText("Lista para seleccionar el Usuario que ingresa el Producto");
		cmbUsuario.setBounds(218, 397, 186, 20);
		List<Usuario> usuario = traigoUsuarios();
		for (Usuario U : usuario) {
			cmbUsuario.addItem(U.getId() + " - " + U.getNombre() + " " + U.getApellido());	
			System.out.println(U.getId() + " - " + U.getNombre() + " " + U.getApellido());
		}
		contentPane.add(cmbUsuario);
		
		
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
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

				producto.setPeso(Double.parseDouble(textFieldPeso.getText()));
				producto.setVolumen(Double.parseDouble(textFieldVolumen.getText()));
				producto.setEstiba(Integer.parseInt(textFieldEstiba.getText()));
				producto.setStkMin(Double.parseDouble(textFieldStkMin.getText()));
				producto.setStkTotal(Double.parseDouble(textFieldStkTotal.getText()));
				producto.setPrecio(Double.parseDouble(textFieldPrecio.getText()));				
				
				String aux = String.valueOf(cmbSegmentac.getSelectedItem());				
				Segmentacion tipoSeg = null;
				tipoSeg = tipoSeg.valueOf(aux);
				producto.setSegmentac(tipoSeg);

				
				
				String auxFam = String.valueOf(cmbFamilia.getItemAt(cmbFamilia.getSelectedIndex()));
				String[] parts = auxFam.split(" - ");
				
				int id = Integer.valueOf(parts[0].replace(" ", ""));
				String nombre = parts[1].replace(" ", ""); 
				
				Familia famProd = new Familia();
				famProd.setId(id);
				famProd.setNombre(nombre);
				producto.setFamilia(famProd);

				
				//cmbUsuario.addItem(U.getId() + " - " + U.getNombre() + " " + U.getApellido());	
						
				String auxUsu = String.valueOf(cmbUsuario.getItemAt(cmbUsuario.getSelectedIndex()));
				String[] partsUsu = auxUsu.split(" - ");
				
				int idUsu = Integer.valueOf(partsUsu[0].replace(" ", ""));
				//String nombreUsu = parts[1].replace(" ", ""); 
				
				Usuario usuProd = new Usuario();
				usuProd.setId(idUsu);
				producto.setUsuario(usuProd);
				
				
				ProductosBeanRemote productosBeanRemote;
				try {
					productosBeanRemote = EJBLocator.getInstance().lookup(ProductosBeanRemote.class);
					if(producto.getId()==0){
						productosBeanRemote.crear(producto);
					}else {
						productosBeanRemote.actualizar(producto);
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
		button.setBounds(140, 428, 100, 23);
		contentPane.add(button);
		
		JLabel lblFamilia = new JLabel("Familia");
		lblFamilia.setBounds(95, 372, 91, 14);
		contentPane.add(lblFamilia);
		
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(95, 403, 91, 14);
		contentPane.add(lblUsuario);
		
			
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

