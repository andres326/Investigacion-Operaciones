/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

/**
 *
 * @author Andres
 */
import PL.MetodoGrafico;
import PL.Modelo;
import PL.SimplexDosFases;
import PL.SimplexNormal;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnIniciar;
	private JLabel lblBienvenido;
	private JButton btnInstrucciones;
	private JButton btnInformacion;
        private JButton btnSimplex;
	private Modelo sistema;
	private VentanaDatos datos;
        private VentanaSimplex simplex;

	public VentanaPrincipal(Modelo a) {
		setBackground(new Color(32, 178, 170));
		setForeground(new Color(32, 178, 170));
		setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		setTitle("M\u00E9todo Gr\u00E1fico y Simplex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 350);
                this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
                sistema = a;
		lblBienvenido = new JLabel("Bienvenidos a la aplicaci\u00F3n");
		lblBienvenido.setBounds(0, 23, 331, 24);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setLabelFor(contentPane);
		lblBienvenido.setFont(new Font("Gisha", Font.PLAIN, 20));
		contentPane.add(lblBienvenido);
		
		btnIniciar = new JButton("Iniciar m\u00E9todo gr\u00E1fico");
		btnIniciar.setToolTipText("Iniciar la aplicaci\u00F3n");
		btnIniciar.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnIniciar.setBackground(new Color(102, 205, 170));
		btnIniciar.setBounds(85, 90, 160, 33);
		btnIniciar.addActionListener(this);
		contentPane.add(btnIniciar);
		
		btnInstrucciones = new JButton("Modo de uso");
		btnInstrucciones.setBackground(new Color(102, 205, 170));
		btnInstrucciones.addActionListener(this);
		btnInstrucciones.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnInstrucciones.setBounds(85, 178, 160, 33);
		contentPane.add(btnInstrucciones);
		
		btnInformacion = new JButton("Acerca de");
		btnInformacion.setBackground(new Color(102, 205, 170));
		btnInformacion.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnInformacion.setBounds(85, 220, 160, 33);
		btnInformacion.addActionListener(this);
		contentPane.add(btnInformacion);
                
                btnSimplex = new JButton("Metodo Simplex");
                btnSimplex.setBackground(new Color(102, 205, 170));
		btnSimplex.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnSimplex.setBounds(85, 134, 160, 33);
		btnSimplex.addActionListener(this);
                contentPane.add(btnSimplex);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnIniciar){
			datos = new VentanaDatos(this);
			datos.inicializar();
			datos.setVisible(true);
			this.setVisible(false);
		}else if(e.getSource()==btnInstrucciones){
			JOptionPane.showMessageDialog(null, "Para usar el programa correctamente debe seguir las siguientes instrucciones"
                                + "\n\n1. Escriba la funcion a optimizar\n2. Escriba la restriccion y luego pulse el boton Agregar Restriccion "
                                + "\n3. Debe pulsar el boton Graficar luego de haber terminado de escribir el modelo "
                                + "\n4. Por ultimo Pulse el boton calcular para obtener el Z optimo junto con los puntos\n");
			
		}else if(e.getSource()==btnInformacion){
			JOptionPane.showMessageDialog(null, "Autores:\n\nMaria Fernanda Garcia\nCesar Baquero\nPablo Cubillos"
                                + "\n\nPrograma hecho para la asignatura Investigacion de Operaciones");
		}else if(e.getSource()==btnSimplex){
                    String variables = JOptionPane.showInputDialog("Ingrese el numero de variables");
                    String restricciones = JOptionPane.showInputDialog("Ingrese el numero de restricciones");
                    simplex = new VentanaSimplex(this, Integer.parseInt(variables), Integer.parseInt(restricciones));
                    this.setVisible(false);
                    simplex.setVisible(true);
                }
	}

    public Modelo getSistema() {
        return sistema;
    }
 
    
       
}

