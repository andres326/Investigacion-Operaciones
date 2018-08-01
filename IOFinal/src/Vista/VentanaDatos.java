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
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaDatos extends JFrame implements ActionListener {

	private JLabel LblRestriccion;
	private JLabel LblX1;
	private JLabel LblX2;
	private JTextField TxtX1;
	private JTextField TxtX2;
	private JComboBox CmbSigno;
	private JTextField TxtRespuesta;
	private JLabel lblFuncionObjetivo;
	private JLabel lblZ;
	private JTextField txtX1Z;
	private JTextField txtX2Z;
	private JLabel lblX1Z;
	private JLabel lblX2Z;
	private JLabel lblPorFavorIngresa;
        private JLabel lblModelo;
	private JButton btnAnalisis;
	private JButton btnGraficar;
	private JButton btnCalcular;
	private JButton btnVolver;
	private JButton btnAgregar;
        private JRadioButton btnMax;
        private TextArea txtModelo;
        private VentanaPrincipal ventana;
        private VentanaGrafica ventanaGrafica;
	int i;

    public VentanaDatos(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }
        

	public void inicializar() {

		LblX1 = new JLabel();
		LblX2 = new JLabel();
		TxtX1 = new JTextField();
		TxtX2 = new JTextField();
		CmbSigno = new JComboBox<>();
		LblRestriccion = new JLabel();
		TxtRespuesta = new JTextField();
                txtModelo = new TextArea();
                txtModelo.setEditable(false);
                lblModelo = new JLabel("Modelo Matematico");
		String[] opciones = { "...", "<", ">", "<=", ">=", "=" };

		this.setTitle("Recolecci\u00F3n de datos");
		this.setBounds(100, 100, 800, 400);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(224, 255, 255));
		this.getContentPane().getPreferredSize();
                this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                this.setLocationRelativeTo(null);

		lblFuncionObjetivo = new JLabel("Funci\u00F3n Objetivo:");
		lblFuncionObjetivo.setFont(new Font("Gisha", Font.BOLD, 15));
		lblFuncionObjetivo.setBounds(50, 70, 125, 23);
		add(lblFuncionObjetivo);

		lblZ = new JLabel("Z = ");
		lblZ.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblZ.setBounds(180, 70, 36, 23);
		add(lblZ);

		txtX1Z = new JTextField();
		txtX1Z.setBounds(207, 70, 36, 23);
		add(txtX1Z);

		lblX1Z = new JLabel("x1 +");
		lblX1Z.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblX1Z.setHorizontalAlignment(SwingConstants.CENTER);
		lblX1Z.setBounds(250, 70, 36, 23);
		add(lblX1Z);

		txtX2Z = new JTextField();
		txtX2Z.setBounds(290, 70, 36, 23);
		add(txtX2Z);

		lblX2Z = new JLabel("x2");
		lblX2Z.setFont(new Font("Gisha", Font.PLAIN, 15));
		lblX2Z.setHorizontalAlignment(SwingConstants.CENTER);
		lblX2Z.setBounds(330, 70, 25, 23);
		add(lblX2Z);

		lblPorFavorIngresa = new JLabel("Por favor ingresa los datos");
		lblPorFavorIngresa.setFont(new Font("Gisha", Font.BOLD, 15));
		lblPorFavorIngresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorFavorIngresa.setBounds(70, 22, 252, 23);
		add(lblPorFavorIngresa);

		LblX1.setBounds(new Rectangle(170, 120, 50, 23));
		LblX1.setText(" x1 + ");
		LblX1.setFont(new Font("Gisha", Font.PLAIN, 15));
		LblX1.setHorizontalAlignment(SwingConstants.CENTER);

		LblX2.setBounds(new Rectangle(245, 120, 36, 23));
		LblX2.setText(" x2 ");
		LblX2.setFont(new Font("Gisha", Font.PLAIN, 15));
		LblX2.setHorizontalAlignment(SwingConstants.CENTER);

                lblModelo.setBounds(new Rectangle(420, 10, 252, 23));
                lblModelo.setFont(new Font("Gisha", Font.BOLD, 15));
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
                add(lblModelo);
                
		TxtX1.setBounds(new Rectangle(130, 120, 36, 23));

		TxtX2.setBounds(new Rectangle(215, 120, 36, 23));

		TxtRespuesta.setBounds(new Rectangle(330, 120, 36, 23));
                
                txtModelo.setBounds(new Rectangle(410, 45, 300,300));
                
		LblRestriccion.setText("Restricción ");
		LblRestriccion.setBounds(new Rectangle(20, 120, 100, 23));
		LblRestriccion.setFont(new Font("Gisha", Font.BOLD, 15));
		LblRestriccion.setHorizontalAlignment(SwingConstants.CENTER);

		CmbSigno.addItem("...");
		CmbSigno.addItem("<");
		CmbSigno.addItem(">");
		CmbSigno.addItem("<=");
		CmbSigno.addItem(">=");
		CmbSigno.addItem("=");
		CmbSigno.setBounds(new Rectangle(277, 120, 45, 23));
                CmbSigno.addActionListener(this);

		add(LblRestriccion, null);
		add(TxtX1, null);
		add(LblX1, null);
		add(TxtX2, null);
		add(LblX2, null);
		add(CmbSigno, null);
		add(TxtRespuesta, null);
                add(txtModelo, null);

		btnAgregar = new JButton("Agregar restricción");
		btnAgregar.setBackground(new Color(176, 224, 230));
		btnAgregar.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnAgregar.setBounds(130, 160, 150, 30);
		btnAgregar.addActionListener(this);
		add(btnAgregar);

		btnCalcular = new JButton("Calcular");
		btnCalcular.setBackground(new Color(176, 224, 230));
		btnCalcular.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnCalcular.setBounds(70, 200, 108, 30);
		btnCalcular.addActionListener(this);
		add(btnCalcular);

		btnGraficar = new JButton("Graficar");
		btnGraficar.setBackground(new Color(176, 224, 230));
		btnGraficar.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnGraficar.setBounds(230, 200, 108, 30);
		btnGraficar.addActionListener(this);
		add(btnGraficar);

		btnAnalisis = new JButton("Ver análisis de sensiblidad");
		btnAnalisis.setBackground(new Color(176, 224, 230));
		btnAnalisis.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnAnalisis.setBounds(105, 240, 200, 30);
		btnAnalisis.addActionListener(this);
                btnAnalisis.setVisible(false);
		add(btnAnalisis);

		btnVolver = new JButton("Volver");
		btnVolver.setBackground(new Color(176, 224, 230));
		btnVolver.setFont(new Font("Gisha", Font.PLAIN, 12));
		btnVolver.setBounds(105, 280, 200, 30);
		btnVolver.addActionListener(this);
		add(btnVolver);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

                if(e.getSource()==getBtnAgregar()){
                    double x1 = Double.parseDouble(getTxtX1().getText());
                    double x2 = Double.parseDouble(getTxtX2().getText());
                    double c = Double.parseDouble(getTxtRespuesta().getText());
                    ventana.getSistema().getMetodo().agregarPuntos(x1, x2, c);
                    if(x2<0){
                        this.txtModelo.append(x1+"X1 -"+x2+"X2"+(String)this.CmbSigno.getSelectedItem()+c+"\n");
                    }else{
                        this.txtModelo.append(x1+"X1 +"+x2+"X2"+(String)this.CmbSigno.getSelectedItem()+c+"\n");
                    }
                    getTxtX1().setText("");
                    getTxtX2().setText("");
                    getTxtRespuesta().setText("");
                    getTxtX1().requestFocus();
                }
                if(e.getSource()==getBtnGraficar()){
                    try {
                        ventanaGrafica = new VentanaGrafica(this);
                        ventanaGrafica.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaDatos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(e.getSource()==getBtnCalcular()){
                    double zX1 = Double.parseDouble(getTxtX1Z().getText());
                    double zX2 = Double.parseDouble(getTxtX2Z().getText());
                    double zOptimo = ventana.getSistema().getMetodo().zOptimo(zX1, zX2);
                    ventana.getSistema().getMetodo().getPuntos().clear();
                    getTxtX1Z().setText("");
                    getTxtX2Z().setText("");
                    getTxtX1Z().requestFocus();
                    this.txtModelo.setText("");
                    JOptionPane.showMessageDialog(null, "El Z optimo es: "+ zOptimo + "\nEl punto X1: "+ ventana.getSistema().getMetodo().getPuntoX1()+
                            "\nEl punto X2: "+ ventana.getSistema().getMetodo().getPuntoX2());
                }
                if(e.getSource()==getBtnVolver()){
                    this.setVisible(false);
                    ventana.setVisible(true);
                }

	}

    public JTextField getTxtX1() {
        return TxtX1;
    }

    public JTextField getTxtX2() {
        return TxtX2;
    }

    public JTextField getTxtRespuesta() {
        return TxtRespuesta;
    }

    public JTextField getTxtX1Z() {
        return txtX1Z;
    }

    public JTextField getTxtX2Z() {
        return txtX2Z;
    }

    public JButton getBtnAnalisis() {
        return btnAnalisis;
    }

    public JButton getBtnGraficar() {
        return btnGraficar;
    }

    public JButton getBtnCalcular() {
        return btnCalcular;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public VentanaPrincipal getVentana() {
        return ventana;
    }
        
}
