package com.cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entities.Local;
import com.entities.Almacenamiento;
import com.exception.ServiciosException;
import com.servicios.LocalesBeanRemote;
import com.servicios.AlmacenamientosBeanRemote;

import javax.swing.JComboBox;

public class VentanaBMAlmacenamiento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldVolumen;
	private JTextField textFieldNombre;
	private JTextField textFieldCostoOp;
	private JTextField textFieldCapEstiba;
	private JTextField textFieldCapPeso;
	private JTextField txtLocal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarAlmacenamiento frame = new VentanaEditarAlmacenamiento(new Almacenamiento());
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
	public VentanaBMAlmacenamiento(Almacenamiento almacenamiento) {
		setTitle("Almacenamiento");
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
		
		JLabel lblVolumen = new JLabel("Volumen");
		lblVolumen.setBounds(106, 223, 46, 14);
		contentPane.add(lblVolumen);
		
		textFieldVolumen = new JTextField();
		textFieldVolumen.setColumns(10);
		textFieldVolumen.setBounds(231, 217, 184, 20);
		contentPane.add(textFieldVolumen);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(106, 48, 46, 14);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setText((String) null);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(231, 42, 184, 20);
		contentPane.add(textFieldNombre);
		
		JLabel lblCostoOp = new JLabel("CostoOp");
		lblCostoOp.setBounds(106, 111, 46, 14);
		contentPane.add(lblCostoOp);		

		textFieldCostoOp = new JTextField();
		textFieldCostoOp.setText("0.0");
		textFieldCostoOp.setColumns(10);
		textFieldCostoOp.setBounds(231, 105, 184, 20);
		contentPane.add(textFieldCostoOp);
		
		JLabel lblCapEstiba = new JLabel("CapEstiba");
		lblCapEstiba.setBounds(106, 251, 46, 14);
		contentPane.add(lblCapEstiba);
		
		textFieldCapEstiba = new JTextField();
		textFieldCapEstiba.setText("0");
		textFieldCapEstiba.setColumns(10);
		textFieldCapEstiba.setBounds(231, 245, 184, 20);
		contentPane.add(textFieldCapEstiba);

		JLabel lblCapPeso = new JLabel("CapPeso");
		lblCapPeso.setBounds(106, 195, 46, 14);
		contentPane.add(lblCapPeso);
		
		textFieldCapPeso = new JTextField();
		textFieldCapPeso.setText("0.0");
		textFieldCapPeso.setColumns(10);
		textFieldCapPeso.setBounds(231, 189, 184, 20);
		contentPane.add(textFieldCapPeso);
		
		
		textFieldId.setText(almacenamiento.getId()==0?"":String.valueOf(almacenamiento.getId()));
		textFieldVolumen.setText(Integer.toString(almacenamiento.getVolumen()));
		textFieldNombre.setText(almacenamiento.getNombre());
		textFieldCostoOp.setText(Double.toString(almacenamiento.getCostoop()));
		textFieldCapEstiba.setText(Double.toString(almacenamiento.getCapestiba()));
		textFieldCapPeso.setText(Double.toString(almacenamiento.getCappeso()));
		
		txtLocal = new JTextField();
		txtLocal.setColumns(10);
		txtLocal.setBounds(230, 359, 46, 20);
		contentPane.add(txtLocal);
		
		
		JComboBox cmbLocal = new JComboBox();
		cmbLocal.setToolTipText("Lista para seleccionar la Local que ingresa el Almacenamiento");
		cmbLocal.setBounds(286, 359, 129, 20);
		List<Local> locales = traigoLocales();
		for (Local F: locales) {
			cmbLocal.addItem(F.getId() + " - " + F.getNombre());	
		}
		contentPane.add(cmbLocal);
		
		txtLocal.setText(String.valueOf(almacenamiento.getLocal().getId()));
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				almacenamiento.setVolumen(Integer.valueOf(textFieldVolumen.getText()));
				almacenamiento.setNombre(textFieldNombre.getText());
				almacenamiento.setCostoop(Double.parseDouble(textFieldCostoOp.getText()));
				almacenamiento.setCappeso(Double.parseDouble(textFieldCapPeso.getText()));
				almacenamiento.setCapestiba(Double.valueOf(textFieldCapEstiba.getText()));
				
				String auxLoc = String.valueOf(cmbLocal.getItemAt(cmbLocal.getSelectedIndex()));
				String[] parts = auxLoc.split(" - ");
				
				int id = Integer.valueOf(parts[0].replace(" ", ""));
				String nombre = parts[1].replace(" ", ""); 

				Local locProd = new Local();
				locProd.setId(id);
				locProd.setNombre(nombre);
				almacenamiento.setLocal(locProd);
				
				
				AlmacenamientosBeanRemote almacenamientosBeanRemote;
				try {
					almacenamientosBeanRemote = EJBLocator.getInstance().lookup(AlmacenamientosBeanRemote.class);
					if(almacenamiento.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Almacenamiento no Existe para Modificar'");
						//almacenamientosBeanRemote.crear(almacenamiento);
					}else {
						almacenamientosBeanRemote.actualizar(almacenamiento);
						JOptionPane.showMessageDialog(null, "Almacenamiento " + almacenamiento.getNombre() + " Actualizado'");
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
			
				AlmacenamientosBeanRemote almacenamientosBeanRemote;
				try {
					almacenamientosBeanRemote = EJBLocator.getInstance().lookup(AlmacenamientosBeanRemote.class);
					if(almacenamiento.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Almacenamiento no Existe para Eliminar'");
						//almacenamientosBeanRemote.crear(almacenamiento);
					}else {
						almacenamientosBeanRemote.borrar(almacenamiento.getId());
						JOptionPane.showMessageDialog(null, "Almacenamiento " + almacenamiento.getNombre() + " fue Eliminado'");
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
				VentanaAlmacenamientoB ventanaAlmacenamientoB;
				try {
					ventanaAlmacenamientoB = new VentanaAlmacenamientoB();
					ventanaAlmacenamientoB.setVisible(true);
					ventanaAlmacenamientoB.setTitle("BUSCAR ALMACENAMIENTO");
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
		
		JLabel label_1 = new JLabel("Local");
		label_1.setBounds(106, 365, 91, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Segmentaci\u00F3n");
		label_2.setBounds(106, 335, 113, 14);
		contentPane.add(label_2);
		
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VentanaAlmacenamientoB ventanaAlmacenamientoB = new VentanaAlmacenamientoB();
					ventanaAlmacenamientoB.setVisible(true);
					ventanaAlmacenamientoB.setTitle("BUSCAR ALMACENAMIENTO");
				
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		

	}

	public void limpioCajasTexto() {
		textFieldId.setText("");
		textFieldVolumen.setText("");
		textFieldNombre.setText("");
		textFieldCostoOp.setText("");
		textFieldCapPeso.setText("");
		textFieldCapEstiba.setText("");
		txtLocal.setText("");
	}


	public static List<Local> traigoLocales() {
		LocalesBeanRemote localesBeanRemote;
		try {
			localesBeanRemote = EJBLocator.getInstance().lookup(LocalesBeanRemote.class);
			return localesBeanRemote.obtenerLocales();

		} catch (NamingException | ServiciosException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
