package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Perfil;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;
import com.servicios.PerfilesBeanRemote;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.NamingException;
import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;

public class VentanaEditarPerfil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditarPerfil frame = new VentanaEditarPerfil(new Perfil());
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
	public VentanaEditarPerfil(Perfil perfil) {
		setTitle("Perfil");
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
		
		JLabel lbltipoPerfil = new JLabel("Tipo de Perfil");
		lbltipoPerfil.setBounds(95, 342, 113, 14);
		contentPane.add(lbltipoPerfil);
		
		textFieldId.setText(perfil.getId()==0?"":String.valueOf(perfil.getId()));
		
		JComboBox<tipoPerfil> cmbtipoPerfil = new JComboBox<tipoPerfil>();
		cmbtipoPerfil.setToolTipText("Lista para seleccionar tipo de perfil");
		cmbtipoPerfil.setBounds(218, 336, 186, 20);
		cmbtipoPerfil.addItem(tipoPerfil.ADMINISTRADOR);
		cmbtipoPerfil.addItem(tipoPerfil.OPERARIO);
		cmbtipoPerfil.addItem(tipoPerfil.SUPERVISOR);
		contentPane.add(cmbtipoPerfil);
				
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String aux = String.valueOf(cmbtipoPerfil.getSelectedItem());				
				tipoPerfil tipoPer = null;
				tipoPer = tipoPerfil.valueOf(aux);
				perfil.setPerfil(tipoPer);
								
				
				PerfilesBeanRemote perfilesBeanRemote;
				try {
					perfilesBeanRemote = EJBLocator.getInstance().lookup(PerfilesBeanRemote.class);
					if(perfil.getId()==0){
						perfilesBeanRemote.crear(perfil);
					}else {
						perfilesBeanRemote.actualizar(perfil);
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
		
			
	}

	public void limpioCajasTexto() {
		textFieldId.setText("");
	}
	
}

