/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Andres
 */
public class VentanaTablaSimplex extends JFrame implements ActionListener{

    private final VentanaSimplex ventana;
    private JTextField[][] tabla;
    private JLabel lblTabla;
    private JButton btnSig;
    private JButton btnVolver;
    private double[][] simplex;
    private int opcion;
    private int filas;
    private int columnas;
    private int restricciones;
    private int contador1=0, contador2=0;

    public VentanaTablaSimplex(VentanaSimplex ventana, int filas, int columnas, double[][] simplex, int opcion) {
        this.ventana = ventana;
        this.opcion=opcion;
        this.filas=filas+2;
        this.columnas=filas+columnas+3;
        this.simplex=simplex;
        this.restricciones=columnas;
        this.setBounds(100, 100, this.columnas*50+200,this.filas*30+190);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(224, 255, 255));
        this.getContentPane().getPreferredSize();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        lblTabla = new JLabel("Tabla simplex");
        lblTabla.setBounds((this.getWidth()/2)-125/2, 30, 125, 23);
        lblTabla.setFont(new Font("Gisha", Font.BOLD, 15));
        add(lblTabla);
        tabla = new JTextField[this.filas][this.columnas];
        for(int i=0; i<tabla.length;i++){
            for(int j=0; j<tabla[0].length;j++){
                tabla[i][j]=new JTextField();
                tabla[i][j].setBounds(100+contador1, 80+contador2, 53, 30);
                tabla[i][j].setEnabled(false);
                add(tabla[i][j]);
                contador1+=53;
            }
            contador1=0;
            contador2+=30;
        }
        btnSig = new JButton("Siguiente Tabla");
        btnSig.setBackground(new Color(176, 224, 230));
        btnSig.setFont(new Font("Gisha", Font.PLAIN, 12));
        btnSig.setBounds(this.getWidth()/5,this.getHeight()-(this.getHeight()/4), 150, 30);
        btnSig.addActionListener(this);
        add(btnSig);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(176, 224, 230));
        btnVolver.setFont(new Font("Gisha", Font.PLAIN, 12));
        btnVolver.setBounds((this.getWidth()/5)+btnSig.getWidth()+10,this.getHeight()-(this.getHeight()/4), 150, 30);
        btnVolver.addActionListener(this);
        add(btnVolver);
        llenado(this.simplex);
        llenadoVariables();
    }
    public void llenadoVariables(){
       int contador=1;
       for(int j=1;j<tabla[0].length-2;j++){ 
           tabla[0][j].setText("x"+contador);
           contador++;
       }
       contador=this.restricciones+1;
       tabla[1][0].setText("Basicas");
       tabla[0][tabla[0].length-1].setText("Relacion");
       tabla[0][tabla[0].length-2].setText("Solucion");
       for(int i=2;i<tabla.length;i++){
           tabla[i][0].setText("x"+contador);
           contador++;
       }
        
    }
    public void cambioVariables(){
        int columna = ventana.getVentana().getSistema().simplexN.getColumnaMinima();
        int fila = ventana.getVentana().getSistema().simplexN.getFilaMinima();
        String variableEntrante = tabla[0][columna+1].getText();
        tabla[fila+1][0].setText(variableEntrante);
        
    }
    public void llenado(double[][] simplex){
        double numero;
        DecimalFormat decimales = new DecimalFormat("0.0");
        for(int i=1; i<tabla.length; i++){
            for(int j=1; j<tabla[0].length; j++){
                numero = simplex[i-1][j-1];
                tabla[i][j].setText(String.valueOf(decimales.format(numero)));
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnVolver){
            this.setVisible(false);
            ventana.setVisible(true);
        }else if(e.getSource()==btnSig){
            if(ventana.getVentana().getSistema().simplexNormal(this.opcion)){
                int columna = ventana.getVentana().getSistema().simplexN.getColumnaMinima();
                int fila = ventana.getVentana().getSistema().simplexN.getFilaMinima();
                String entrante = tabla[0][columna+1].getText();
                String saliente = tabla[fila+1][0].getText();
                JOptionPane.showMessageDialog(null, "Variable Entrante: "+entrante+
                        "\nVariable Saliente: "+saliente+
                        "\nPivote: "+ventana.getVentana().getSistema().simplexN.getPivote());
                this.simplex = ventana.getVentana().getSistema().simplexN.getSimplex();
                llenado(simplex);
                cambioVariables();
            }else{
                btnSig.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Se ha llegado a la solucion final!!!"+
                        "\nPara ver los puntos de solucion debe mirar la columna que dice solucion"+
                        "\nTenga en cuenta que el z optimo es el primer numero que sale");
            }
        }
    }
    
    
}
