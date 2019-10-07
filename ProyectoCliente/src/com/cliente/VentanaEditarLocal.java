package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Ciudad;
import com.entities.Local;
import com.enumerated.tipoLocal;
import com.exception.ServiciosException;
import com.servicios.CiudadesBeanRemote;
import com.servicios.LocalesBeanRemote;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;

public class VentanaEditarLocal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldCodigo;
	private JTextField textFieldNombre;
	private JTextField textFieldDireccion;

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
	public VentanaEditarLocal(Local local) {
		setTitle("Local");
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
		textFieldNombre.setToolTipText("Nombre del Local");
		textFieldNombre.setText((String) null);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(218, 46, 186, 20);
		contentPane.add(textFieldNombre);
		
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(95, 85, 46, 14);
		contentPane.add(lblDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setToolTipText("Direccion del Local");
		textFieldDireccion.setText((String) null);
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(218, 78, 186, 20);
		contentPane.add(textFieldDireccion);
		

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(95, 256, 46, 14);
		contentPane.add(lblCodigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldCodigo.setToolTipText("Codigo del Local");
		textFieldCodigo.setText("0");
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(218, 249, 186, 20);
		contentPane.add(textFieldCodigo);
		
		
		JLabel lbltipoLocal = new JLabel("Tipo de Local");
		lbltipoLocal.setBounds(95, 342, 113, 14);
		contentPane.add(lbltipoLocal);
		
		textFieldId.setText(local.getId()==0?"":String.valueOf(local.getId()));
		textFieldNombre.setText(local.getNombre());
		textFieldDireccion.setText(local.getDireccion());
		textFieldCodigo.setText(Integer.toString(local.getCodigo()));
		
		JComboBox<tipoLocal> cmbtipoLocal = new JComboBox<tipoLocal>();
		cmbtipoLocal.setToolTipText("Lista para seleccionar tipo de local");
		cmbtipoLocal.setBounds(218, 336, 186, 20);
		cmbtipoLocal.addItem(tipoLocal.LOCAL);
		cmbtipoLocal.addItem(tipoLocal.REGIONAL);
		cmbtipoLocal.addItem(tipoLocal.PUNTODEVENTA);
		cmbtipoLocal.addItem(tipoLocal.OTRO);
		contentPane.add(cmbtipoLocal);
		
		JComboBox<String> cmbCiudad = new JComboBox<String>();
		cmbCiudad.setToolTipText("Lista para seleccionar la Ciudad que se asocia al Local");
		cmbCiudad.setBounds(218, 366, 186, 20);
		List<Ciudad> ciudades = traigoCiudades();
		for (Ciudad F: ciudades) {
			cmbCiudad.addItem(F.getId() + " - " + F.getNombre());	
		}
		contentPane.add(cmbCiudad);
		
		
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				local.setCodigo(Integer.parseInt(textFieldCodigo.getText()));
				local.setNombre(textFieldNombre.getText());
				local.setDireccion(textFieldDireccion.getText());
				
				String aux = String.valueOf(cmbtipoLocal.getSelectedItem());				
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
						localesBeanRemote.crear(local);
					}else {
						localesBeanRemote.actualizar(local);
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
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(95, 372, 91, 14);
		contentPane.add(lblCiudad);
		
			
	}

	public void limpioCajasTexto() {
		textFieldId.setText("");
		textFieldNombre.setText("");
		textFieldDireccion.setText("");
		textFieldCodigo.setText("");
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

