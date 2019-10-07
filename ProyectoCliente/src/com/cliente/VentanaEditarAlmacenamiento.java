package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Local;
import com.entities.Almacenamiento;
import com.exception.ServiciosException;
import com.servicios.LocalesBeanRemote;
import com.servicios.AlmacenamientosBeanRemote;

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


public class VentanaEditarAlmacenamiento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField textFieldVolumen;
	private JTextField textFieldNombre;
	private JTextField textFieldCostoop;
	private JTextField textFieldCapestiba;
	private JTextField textFieldCappeso;

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
	public VentanaEditarAlmacenamiento(Almacenamiento almacenamiento) {
		setTitle("Almacenamiento");
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


		JLabel lblVolumen = new JLabel("Volumen");
		lblVolumen.setBounds(95, 228, 46, 14);
		contentPane.add(lblVolumen);
		
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
		textFieldVolumen.setToolTipText("Volumen del Almacenamiento");
		textFieldVolumen.setText("0.0");
		textFieldVolumen.setColumns(10);
		textFieldVolumen.setBounds(218, 221, 186, 20);
		contentPane.add(textFieldVolumen);
		
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(95, 53, 46, 14);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setToolTipText("Nombre del Almacenamiento");
		textFieldNombre.setText((String) null);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(218, 46, 186, 20);
		contentPane.add(textFieldNombre);
		
		
		JLabel lblCostoop = new JLabel("Costoop");
		lblCostoop.setBounds(95, 116, 46, 14);
		contentPane.add(lblCostoop);
		
		textFieldCostoop = new JTextField();
		textFieldCostoop.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char validar = arg0.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		textFieldCostoop.setToolTipText("Costo Operativo del Almacenamiento");
		textFieldCostoop.setText("0.0");
		textFieldCostoop.setColumns(10);
		textFieldCostoop.setBounds(218, 109, 186, 20);
		contentPane.add(textFieldCostoop);
		
		
		JLabel lblCapestiba = new JLabel("Capestiba");
		lblCapestiba.setBounds(95, 256, 46, 14);
		contentPane.add(lblCapestiba);
		
		textFieldCapestiba = new JTextField();
		textFieldCapestiba.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldCapestiba.setToolTipText("Cantidad Apliable del Almacenamiento");
		textFieldCapestiba.setText("0");
		textFieldCapestiba.setColumns(10);
		textFieldCapestiba.setBounds(218, 249, 186, 20);
		contentPane.add(textFieldCapestiba);

		
		JLabel lblCappeso = new JLabel("Cappeso");
		lblCappeso.setBounds(95, 200, 46, 14);
		contentPane.add(lblCappeso);
		
		textFieldCappeso = new JTextField();
		textFieldCappeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar = e.getKeyChar();
				
				if (Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		textFieldCappeso.setToolTipText("Cappeso del Almacenamiento");
		textFieldCappeso.setText("0.0");
		textFieldCappeso.setColumns(10);
		textFieldCappeso.setBounds(218, 193, 186, 20);
		contentPane.add(textFieldCappeso);
		
		
		textFieldId.setText(almacenamiento.getId()==0?"":String.valueOf(almacenamiento.getId()));
		textFieldVolumen.setText(Integer.toString(almacenamiento.getVolumen()));
		textFieldNombre.setText(almacenamiento.getNombre());
		textFieldCostoop.setText(Double.toString(almacenamiento.getCostoop()));
		textFieldCappeso.setText(Double.toString(almacenamiento.getCappeso()));
		textFieldCapestiba.setText(Double.toString(almacenamiento.getCapestiba()));
		
		JComboBox cmbLocal = new JComboBox();
		cmbLocal.setToolTipText("Lista para seleccionar el Local se asocia al Almacenamiento");
		cmbLocal.setBounds(218, 366, 186, 20);
		List<Local> locales = traigoLocales();
		for (Local F: locales) {
			cmbLocal.addItem(F.getId() + " - " + F.getNombre());	
		}
		contentPane.add(cmbLocal);
		
		
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				almacenamiento.setVolumen(Integer.parseInt(textFieldVolumen.getText()));
				almacenamiento.setNombre(textFieldNombre.getText());
				almacenamiento.setCostoop(Double.parseDouble(textFieldCostoop.getText()));				
				almacenamiento.setCapestiba(Double.parseDouble(textFieldCapestiba.getText()));
				almacenamiento.setCappeso(Double.parseDouble(textFieldCappeso.getText()));
								
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
						almacenamientosBeanRemote.crear(almacenamiento);
					}else {
						almacenamientosBeanRemote.actualizar(almacenamiento);
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
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(95, 372, 91, 14);
		contentPane.add(lblLocal);
					
	}

	public void limpioCajasTexto() {
		textFieldId.setText("");
		textFieldVolumen.setText("");
		textFieldNombre.setText("");
		textFieldCostoop.setText("");
		textFieldCapestiba.setText("");
		textFieldCappeso.setText("");
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

