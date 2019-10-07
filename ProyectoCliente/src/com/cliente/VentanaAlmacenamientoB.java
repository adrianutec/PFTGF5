package com.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.entities.Local;
import com.entities.Almacenamiento;
import com.exception.ServiciosException;
import com.servicios.AlmacenamientosBeanRemote;

public class VentanaAlmacenamientoB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JTable tablaAlmacenamientos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAlmacenamientoB frame = new VentanaAlmacenamientoB();
					frame.setVisible(true);

					TITULO = frame.getTitle();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static String TITULO;

	/**
	 * Create the frame.
	 * @throws ServiciosException 
	 */
	public VentanaAlmacenamientoB() throws ServiciosException {
		setTitle("Almacenamientos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		textFieldBuscar = new JTextField();
		panel.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cargarAlmacenamientos();
				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBuscar);
		
		tablaAlmacenamientos = new JTable();
		cargarAlmacenamientos();
		
		tablaAlmacenamientos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				int    row        = tablaAlmacenamientos.getSelectedRow();	
						
				int   idAlmacenamiento = (int)tablaAlmacenamientos.getModel().getValueAt(row, 0);
				int volumen    = (int)tablaAlmacenamientos.getModel().getValueAt(row, 1);
				String nombre     = (String)tablaAlmacenamientos.getModel().getValueAt(row, 2);
				double costoop     = (double)tablaAlmacenamientos.getModel().getValueAt(row, 3);
				double cappeso       = (double)tablaAlmacenamientos.getModel().getValueAt(row, 4);
				double capestiba     = (double)tablaAlmacenamientos.getModel().getValueAt(row, 5);
				
				Local local = new Local();
				int aux = (int)tablaAlmacenamientos.getModel().getValueAt(row, 6);
				local.setId(aux);
								
				
				Almacenamiento almacenamiento = new Almacenamiento();
				almacenamiento.setId(idAlmacenamiento);
				almacenamiento.setVolumen(volumen);
				almacenamiento.setNombre(nombre);
				almacenamiento.setCostoop(costoop);
				almacenamiento.setCapestiba(capestiba);
				almacenamiento.setCappeso(cappeso);
				almacenamiento.setLocal(local);
				
				
				VentanaBMAlmacenamiento ventanaBMAlmacenamiento = new VentanaBMAlmacenamiento(almacenamiento);
				ventanaBMAlmacenamiento.setVisible(true);
			
			}
			});

		contentPane.add(tablaAlmacenamientos, BorderLayout.CENTER);
		
		/*
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaEditarAlmacenamiento3 VentanaEditarAlmacenamiento3 = new VentanaEditarAlmacenamiento3(new Almacenamiento());
				VentanaEditarAlmacenamiento3.setVisible(true);
			}
		});
		contentPane.add(btnCrear, BorderLayout.SOUTH);
		*/
	}
	
	private void cargarAlmacenamientos() throws ServiciosException{
		try {
			AlmacenamientosBeanRemote almacenamientosBeanRemote = EJBLocator.getInstance().lookup(AlmacenamientosBeanRemote.class);
			List<Almacenamiento> almacenamientos= new ArrayList<>();
			if(textFieldBuscar.getText().isEmpty()){
				almacenamientos = almacenamientosBeanRemote.obtenerAlmacenamientos();
			}else{
				almacenamientos = almacenamientosBeanRemote.obtenerAlmacenamientos(textFieldBuscar.getText()+"%");
			}
			String[] columnNames = {"Id","Volumen","Nombre","Costoop","Capestiba","Cappeso","Local"};
		    DefaultTableModel model = new DefaultTableModel();
		    tablaAlmacenamientos.setModel(model);

			model.setColumnIdentifiers(columnNames);
			for(Almacenamiento almacenamiento : almacenamientos){
				Object[] fila = new Object[7];
				fila[0]=almacenamiento.getId();
				fila[1]=almacenamiento.getVolumen();
				fila[2]=almacenamiento.getNombre();
				fila[3]=almacenamiento.getCostoop();
				fila[4]=almacenamiento.getCapestiba();
				fila[5]=almacenamiento.getCappeso();
				fila[6]=almacenamiento.getLocal().getId();
				model.addRow(fila);
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
