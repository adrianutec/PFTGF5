package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Perfil;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;
import com.servicios.FamiliasBeanRemote;
import com.servicios.PerfilesBeanRemote;
import com.servicios.ProductosBeanRemote;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.FocusManager;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentanaPerfiles extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPerfiles frame = new VentanaPerfiles(new Perfil());
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
	public VentanaPerfiles(Perfil perfil) {
		setTitle("Perfiles");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setBounds(86, 36, 46, 14);
		contentPane.add(label);
		
		txtID = new JTextField();
		txtID.setText("0");
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(187, 33, 130, 20);
		contentPane.add(txtID);
		
		JLabel lblNombrePerfil = new JLabel("Nombre Perfil");
		lblNombrePerfil.setBounds(86, 67, 79, 14);
		contentPane.add(lblNombrePerfil);
		
		txtNombre = new JTextField();
		txtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			
				String aux = txtNombre.getText();
				if (aux.length()>0) {
					aux = aux.toUpperCase();
				}
				
				if  (aux.equals("ADMINISTRADOR") || aux.equals("OPERARIO") || aux.equals("SUPERVISOR"))  {
				} else	{
					JOptionPane.showMessageDialog(null, "El Perfil debe ser 'ADMINISTRADOR o SUPERVISOR u OPERARIO'");
					txtNombre.setText("");
				}
			
			}
		});
		txtNombre.setText((String) null);
		txtNombre.setColumns(10);
		txtNombre.setBounds(187, 64, 130, 20);
		contentPane.add(txtNombre);
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseReleased(MouseEvent arg0) {
				perfil.setId(Integer.valueOf(txtID.getText()));
				tipoPerfil tipoperf = tipoPerfil.valueOf(txtNombre.getText());
				perfil.setPerfil(tipoperf);
				
				PerfilesBeanRemote perfilesBeanRemote;
				try {
					perfilesBeanRemote = EJBLocator.getInstance().lookup(PerfilesBeanRemote.class);
					if(perfil.getId()==0){
						perfilesBeanRemote.crear(perfil);
					}else {
						perfilesBeanRemote.actualizar(perfil);
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setBounds(97, 113, 100, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCELAR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Window ventana = FocusManager.getCurrentManager().getActiveWindow(); 
				ventana.dispose();
			}
		});
		button_1.setBounds(211, 113, 100, 23);
		contentPane.add(button_1);
	}
}
