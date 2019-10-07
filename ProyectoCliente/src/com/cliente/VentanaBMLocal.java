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

import com.entities.Ciudad;
import com.entities.Local;
import com.enumerated.tipoLocal;
import com.exception.ServiciosException;
import com.servicios.CiudadesBeanRemote;
import com.servicios.LocalesBeanRemote;

import javax.swing.JComboBox;

public class VentanaBMLocal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldCodigo;
	private JTextField textFieldNombre;
	private JTextField textFieldDireccion;
	private JTextField txttipoLoc;
	private JTextField txtCiudad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarLocal frame = new VentanaEditarLocal(new Local());
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
	public VentanaBMLocal(Local local) {
		setTitle("Local");
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
		
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setText((String) null);
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(231, 74, 184, 20);
		contentPane.add(textFieldDireccion);
		
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(106, 80, 46, 14);
		contentPane.add(lblDireccion);
		
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(106, 251, 46, 14);
		contentPane.add(lblCodigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setText("0");
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(231, 245, 184, 20);
		contentPane.add(textFieldCodigo);
		
		
		
		textFieldId.setText(local.getId()==0?"":String.valueOf(local.getId()));
		
		textFieldNombre.setText(local.getNombre());
		textFieldDireccion.setText(local.getDireccion());
		textFieldCodigo.setText(Integer.toString(local.getCodigo()));

		txttipoLoc = new JTextField();
		txttipoLoc.setColumns(10);
		txttipoLoc.setBounds(231, 329, 46, 20);
		contentPane.add(txttipoLoc);
		
		txtCiudad = new JTextField();
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(230, 359, 46, 20);
		contentPane.add(txtCiudad);
				
		
		JComboBox<tipoLocal> cmbtipoLoc = new JComboBox<tipoLocal>();
		cmbtipoLoc.setToolTipText("Lista para seleccionar el tipo de local");
		cmbtipoLoc.setBounds(286, 329, 129, 20);
		cmbtipoLoc.addItem(tipoLocal.LOCAL);
		cmbtipoLoc.addItem(tipoLocal.REGIONAL);
		cmbtipoLoc.addItem(tipoLocal.PUNTODEVENTA);
		cmbtipoLoc.addItem(tipoLocal.OTRO);
		contentPane.add(cmbtipoLoc);
		
		
		JComboBox cmbCiudad = new JComboBox();
		cmbCiudad.setToolTipText("Lista para seleccionar la Ciudad asociada al Local");
		cmbCiudad.setBounds(286, 359, 129, 20);
		List<Ciudad> ciudades = traigoCiudades();
		for (Ciudad F: ciudades) {
			cmbCiudad.addItem(F.getId() + " - " + F.getNombre());	
		}
		contentPane.add(cmbCiudad);

		
		tipoLocal aux = local.getTipoloc();
		txttipoLoc.setText(aux.toString());
		
		txtCiudad.setText(String.valueOf(local.getCiudad().getId()));
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				local.setNombre(textFieldNombre.getText());
				local.setDireccion(textFieldDireccion.getText());
				local.setCodigo(Integer.valueOf(textFieldCodigo.getText()));
				
				String aux = String.valueOf(cmbtipoLoc.getSelectedItem());				
				tipoLocal tipoLoc = null;
				tipoLoc = tipoLocal.valueOf(aux);
				local.setTipoloc(tipoLoc);
				
				String auxCiu = String.valueOf(cmbCiudad.getItemAt(cmbCiudad.getSelectedIndex()));
				String[] parts = auxCiu.split(" - ");
				
				int id = Integer.valueOf(parts[0].replace(" ", ""));
				String nombre = parts[1].replace(" ", ""); 

				Ciudad ciuProd = new Ciudad();
				ciuProd.setId(id);
				ciuProd.setNombre(nombre);
				local.setCiudad(ciuProd);
				
				
				LocalesBeanRemote localesBeanRemote;
				try {
					localesBeanRemote = EJBLocator.getInstance().lookup(LocalesBeanRemote.class);
					if(local.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Local no Existe para Modificar'");
						//localesBeanRemote.crear(local);
					}else {
						localesBeanRemote.actualizar(local);
						JOptionPane.showMessageDialog(null, "Local " + local.getNombre() + " Actualizado'");
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
			
				LocalesBeanRemote localesBeanRemote;
				try {
					localesBeanRemote = EJBLocator.getInstance().lookup(LocalesBeanRemote.class);
					if(local.getId()==0){
						JOptionPane.showMessageDialog(null, "EL Local no Existe para Eliminar'");
						//localesBeanRemote.crear(local);
					}else {
						localesBeanRemote.borrar(local.getId());
						JOptionPane.showMessageDialog(null, "Local " + local.getNombre() + " fue Eliminado'");
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
				VentanaLocalB ventanaLocalB;
				try {
					ventanaLocalB = new VentanaLocalB();
					ventanaLocalB.setVisible(true);
					ventanaLocalB.setTitle("BUSCAR LOCAL");
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
		
		JLabel label_1 = new JLabel("Ciudad");
		label_1.setBounds(106, 365, 91, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("tipoLoci\u00F3n");
		label_2.setBounds(106, 335, 113, 14);
		contentPane.add(label_2);
		
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					VentanaLocalB ventanaLocalB = new VentanaLocalB();
					ventanaLocalB.setVisible(true);
					ventanaLocalB.setTitle("BUSCAR LOCAL");
				
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
		textFieldDireccion.setText("");
		textFieldCodigo.setText("");
		txttipoLoc.setText("");
		txtCiudad.setText("");
	}


	public static List<Ciudad> traigoCiudades() {
		CiudadesBeanRemote ciudadesBeanRemote;
		try {
			ciudadesBeanRemote = EJBLocator.getInstance().lookup(CiudadesBeanRemote.class);
			return ciudadesBeanRemote.obtenerCiudades();

		} catch (NamingException | ServiciosException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
