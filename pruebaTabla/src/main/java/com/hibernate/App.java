package com.hibernate;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import javax.swing.JButton;
import javax.swing.JTable;


import com.hibernate.dao.SerieDAO;

import com.hibernate.model.Serie;
import com.hibernate.util.HibernateUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {

	private JFrame frmCrudSeries;
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldTemp;
	private JTextField textFieldCap;
	private JTable table;
	private JTable table_1;
	
	void limpiarTexto() {
		textFieldId.setText("");
		textFieldNombre.setText("");
		textFieldTemp.setText("");
		textFieldCap.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmCrudSeries.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		SerieDAO serieDAO = new SerieDAO();
		
		frmCrudSeries = new JFrame();
		frmCrudSeries.setTitle("CRUD Series");
		frmCrudSeries.setBounds(100, 100, 801, 556);
		frmCrudSeries.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudSeries.getContentPane().setLayout(null);
		
		JLabel lblSerieId = new JLabel("ID: ");
		lblSerieId.setFont(new Font("Arial", Font.BOLD, 15));
		lblSerieId.setBounds(42, 230, 45, 13);
		frmCrudSeries.getContentPane().add(lblSerieId);
		
		JLabel lblSerieNom = new JLabel("NOMBRE:");
		lblSerieNom.setFont(new Font("Arial", Font.BOLD, 15));
		lblSerieNom.setBounds(42, 277, 111, 13);
		frmCrudSeries.getContentPane().add(lblSerieNom);
		
		JLabel lblSerieTemp = new JLabel("TEMPORADAS:");
		lblSerieTemp.setFont(new Font("Arial", Font.BOLD, 15));
		lblSerieTemp.setBounds(42, 328, 149, 13);
		frmCrudSeries.getContentPane().add(lblSerieTemp);
		
		JLabel lblSerieCap = new JLabel("CAPÍTULOS:");
		lblSerieCap.setFont(new Font("Arial", Font.BOLD, 15));
		lblSerieCap.setBounds(42, 376, 149, 13);
		frmCrudSeries.getContentPane().add(lblSerieCap);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(212, 228, 149, 19);
		frmCrudSeries.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(212, 271, 242, 19);
		frmCrudSeries.getContentPane().add(textFieldNombre);
		
		textFieldTemp = new JTextField();
		textFieldTemp.setColumns(10);
		textFieldTemp.setBounds(212, 322, 149, 19);
		frmCrudSeries.getContentPane().add(textFieldTemp);
		
		textFieldCap = new JTextField();
		textFieldCap.setColumns(10);
		textFieldCap.setBounds(212, 370, 149, 19);
		frmCrudSeries.getContentPane().add(textFieldCap);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Serie serie = new Serie(textFieldNombre.getText(), Integer.parseInt(textFieldTemp.getText()),
						Integer.parseInt(textFieldCap.getText()));
				serieDAO.insertSerie(serie);
				JOptionPane.showMessageDialog(null, "Serie añadida");
				limpiarTexto();
			}
		});
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 15));
		btnGuardar.setBounds(72, 447, 161, 21);
		frmCrudSeries.getContentPane().add(btnGuardar);
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Serie serieActualizar = serieDAO.selectSerieById(Integer.parseInt(textFieldId.getText()));
				serieActualizar.setNomserie(textFieldNombre.getText());
				serieActualizar.setTemporadas(Integer.parseInt(textFieldTemp.getText()));
				serieActualizar.setCapitulos(Integer.parseInt(textFieldCap.getText()));
				serieDAO.updateSerie(serieActualizar);
				JOptionPane.showMessageDialog(null, "Serie actualizada");
				limpiarTexto();
			}
		});
		btnActualizar.setFont(new Font("Arial", Font.BOLD, 15));
		btnActualizar.setBounds(293, 448, 161, 21);
		frmCrudSeries.getContentPane().add(btnActualizar);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				serieDAO.deleteSerie(Integer.parseInt(textFieldId.getText()));
				JOptionPane.showMessageDialog(null, "Serie borrada");
				limpiarTexto();
			}
		});
		btnBorrar.setFont(new Font("Arial", Font.BOLD, 15));
		btnBorrar.setBounds(529, 448, 161, 21);
		frmCrudSeries.getContentPane().add(btnBorrar);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Título");
		model.addColumn("Nºtemporadas");
		model.addColumn("Total episodios");
		
		List<Serie> series = serieDAO.selectAllSerie();
		for (Serie s : series) {
		    Object[] row = new Object[4];
		    row[0] = s.getId();
		    row[1] = s.getNomserie();
		    row[2] = s.getTemporadas();
		    row[3] = s.getCapitulos();
		    model.addRow(row);
		}
		
		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				textFieldId.setText(model.getValueAt(index, 0).toString());
				textFieldNombre.setText(model.getValueAt(index, 1).toString());
				textFieldTemp.setText(model.getValueAt(index, 2).toString());
				textFieldCap.setText(model.getValueAt(index, 3).toString());
			}
		});
		table_1.setBounds(92, 165, 586, -126);
		frmCrudSeries.getContentPane().add(table_1);
		
		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(92, 165, 586, -126);

		frmCrudSeries.getContentPane().add(scrollPane);
	}
}
