package com.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Local;
import com.exception.ServiciosException;
import com.servicios.LocalesBeanRemote;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLocales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtCodigo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLocales frame = new VentanaLocales(new Local());
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
	public VentanaLocales(Local local) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setBounds(92, 27, 46, 14);
		contentPane.add(label);
		
		txtID = new JTextField();
		txtID.setText("0");
		txtID.setEnabled(false);
		txtID.setColumns(10);
		txtID.setBounds(193, 24, 127, 20);
		contentPane.add(txtID);
		
		JLabel label_1 = new JLabel("Codigo");
		label_1.setBounds(92, 58, 46, 14);
		contentPane.add(label_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setText((String) null);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(193, 55, 127, 20);
		contentPane.add(txtCodigo);
		
		JButton button = new JButton("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseReleased(MouseEvent arg0) {
			
				if (txtCodigo.getText().length()>0 ) {
					local.setId(Integer.valueOf(txtID.getText()));
					local.setCodigo(Integer.valueOf(txtCodigo.getText()));
												
					LocalesBeanRemote localBeanRemote;
					try {
						localBeanRemote = EJBLocator.getInstance().lookup(LocalesBeanRemote.class);
						if(local.getId()==0){
							localBeanRemote.crear(local);
						}else {
							localBeanRemote.actualizar(local);
						}
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ServiciosException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Deben ingresarse todos los datos de la Local");
					txtCodigo.setText("");
				}
					
			}
		});
		button.setBounds(92, 157, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("CANCELAR");
		button_1.setBounds(206, 157, 89, 23);
		contentPane.add(button_1);
		
	}
}
