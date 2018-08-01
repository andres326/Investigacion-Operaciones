/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Andres
 */
public class VentanaSimplex extends JFrame implements ActionListener{

    private VentanaPrincipal ventana;
    private JLabel lblfObjetivo;
    private JLabel lblrestriccion;
    private JLabel lblZ;
    private JLabel[] lblObjetivo;
    private JLabel[] lblRestricciones;
    private JTextField[] txtObjetivo;
    private JTextField[] txtRestricciones;
    private JButton btnAgregar;
    private JButton btnCalcular;
    private JButton btnAnalisis;
    private JButton btnVolver;
    private JRadioButton maximizar;
    private JRadioButton minimizar;
    private ButtonGroup grupo;
    private int restricciones, variables;
    private final JComboBox<Object> CmbSigno;
    private final TextArea txtModelo;
    private int contador=1;
    private int numeral=0;
    private int artificial=0;
    private int mayor=0;
    private int normal=0;
    private boolean fases=false;
    private VentanaTablaSimplex ventanaS;
    private VentanaSimplexDosFases ventanaDos;
    private ArrayList<Integer> filas;
    private double[][] restriccion;
    private double[][] holgura;
    private double[][] artificiales;
    private double[] filaz;
    private double[][] simplex;
    private double[][] simplexFase;
    
    public VentanaSimplex(VentanaPrincipal aThis, int parseInt, int parseInt0) {
        ventana = aThis;
        this.restricciones=parseInt0;
        this.variables=parseInt;
        int x=0, contador=1;
        this.setBounds(100, 100, 800, 700);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(224, 255, 255));
        this.getContentPane().getPreferredSize();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        CmbSigno = new JComboBox<>();
        
        lblfObjetivo = new JLabel("Funci\u00F3n Objetivo:");
        lblfObjetivo.setFont(new Font("Gisha", Font.BOLD, 15));
        lblfObjetivo.setBounds(50, 70, 125, 23);
        add(lblfObjetivo);
        
        maximizar = new JRadioButton("Maximizar", true);
        maximizar.setFont(new Font("Gisha", Font.BOLD, 15));
        maximizar.setBounds(530, 160, 110, 50);
        add(maximizar);
        
        minimizar = new JRadioButton("Minimizar", false);
        minimizar.setFont(new Font("Gisha", Font.BOLD, 15));
        minimizar.setBounds(650, 160, 110, 50);
        add(minimizar);
        
        grupo = new ButtonGroup();
        grupo.add(maximizar);
        grupo.add(minimizar);
        
        lblrestriccion = new JLabel("Restricciones:");
        lblrestriccion.setFont(new Font("Gisha", Font.BOLD, 15));
        lblrestriccion.setBounds(50, 110, 125, 23);
        add(lblrestriccion);
        
        lblZ = new JLabel("Z = ");
        lblZ.setFont(new Font("Gisha", Font.PLAIN, 15));
        lblZ.setBounds(180, 70, 36, 23);
        add(lblZ);
        
        
        lblObjetivo = new JLabel[variables];
        txtObjetivo = new JTextField[variables];
        for(int i=0; i<lblObjetivo.length;i++){
            lblObjetivo[i] = new JLabel("x"+contador);
            lblObjetivo[i].setFont(new Font("Gisha", Font.PLAIN, 15));
            lblObjetivo[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblObjetivo[i].setBounds(250+x, 70, 36, 23);
            txtObjetivo[i] = new JTextField();
            txtObjetivo[i].setBounds(220+x, 70, 36, 23);
            add(txtObjetivo[i]);
            add(lblObjetivo[i]);
            x+=80;
            contador++;
        }
        contador=1;
        x=0;
        lblRestricciones = new JLabel[variables];
        txtRestricciones = new JTextField[variables+1];
        for (int i=0;i<lblRestricciones.length;i++){
            lblRestricciones[i] = new JLabel("x"+contador);
            lblRestricciones[i].setFont(new Font("Gisha", Font.PLAIN, 15));
            lblRestricciones[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblRestricciones[i].setBounds(250+x, 110, 36, 23);
            add(lblRestricciones[i]);
            x+=80;
            contador++;
        }x=0;
        for(int i=0; i<txtRestricciones.length-1;i++){
            txtRestricciones[i] = new JTextField();
            txtRestricciones[i].setBounds(220+x, 110, 36, 23);
            add(txtRestricciones[i]);
            x+=80;
        }
        CmbSigno.addItem("...");
        CmbSigno.addItem("<=");
        CmbSigno.addItem(">=");
        CmbSigno.addItem("=");
        CmbSigno.setBounds(new Rectangle(220+x, 110, 45, 23));
        x+=80;
        CmbSigno.addActionListener(this);
        add(CmbSigno);
        txtRestricciones[txtRestricciones.length-1] = new JTextField();
        txtRestricciones[txtRestricciones.length-1].setBounds(220+x, 110, 36, 23);
        add(txtRestricciones[txtRestricciones.length-1]);
        
        btnAgregar = new JButton("Agregar restricción");
        btnAgregar.setBackground(new Color(176, 224, 230));
        btnAgregar.setFont(new Font("Gisha", Font.PLAIN, 12));
        btnAgregar.setBounds(130, 160, 150, 30);
        btnAgregar.addActionListener(this);
        add(btnAgregar);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setBackground(new Color(176, 224, 230));
        btnCalcular.setFont(new Font("Gisha", Font.PLAIN, 12));
        btnCalcular.setBounds(130, 200, 150, 30);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        btnAnalisis = new JButton("Ver análisis de sensiblidad");
        btnAnalisis.setBackground(new Color(176, 224, 230));
        btnAnalisis.setFont(new Font("Gisha", Font.PLAIN, 12));
        btnAnalisis.setBounds(320, 160, 200, 30);
        btnAnalisis.addActionListener(this);
        btnAnalisis.setVisible(false);
        add(btnAnalisis);

        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(176, 224, 230));
        btnVolver.setFont(new Font("Gisha", Font.PLAIN, 12));
        btnVolver.setBounds(320, 200, 200, 30);
        btnVolver.addActionListener(this);
        add(btnVolver);

        txtModelo = new TextArea();
        txtModelo.setEditable(false);
        txtModelo.setBounds(new Rectangle(130, 300, 500,300));
        txtModelo.append("Restricciones: \n");
        add(txtModelo);
        
        filaz = new double[this.restricciones+this.variables+2];
        restriccion = new double[this.restricciones][this.variables+1];
        holgura = new double[this.restricciones][this.restricciones];
        filas = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnVolver){
            this.setVisible(false);
            ventana.setVisible(true);
        }else if(e.getSource()==btnAgregar){
            matrizRestricciones(numeral);
            matrizHolgura((String)CmbSigno.getSelectedItem(), numeral);
            contador++;
            numeral++;
            modeloMatematico();
            if(contador>restricciones){
                escritura();
            }
        }else if(e.getSource()==btnCalcular){
            int opcion = opcion();
            filaZ();
            
            if(fases){
                artificiales = new double[artificial+normal][artificial];
                for(int i=0;i<artificial+normal;i++){
                    for(int j=0;j<artificial;j++){
                        if(i==j){
                            artificiales[i][j]=1;
                        }else{
                            artificiales[i][j]=0;
                        }
                    }
                }
                simplexFase = ventana.getSistema().getSimplexM(variables, normal, artificial, normal,mayor, restriccion, filaz, holgura, artificiales).getSimplexFase();
                this.setVisible(false);
                ventanaDos = new VentanaSimplexDosFases(this, this.restricciones,this.variables, artificial, normal, mayor, simplexFase, opcion);
                ventanaDos.setVisible(true);
            }else{
                simplex = ventana.getSistema().getSimplexN(variables, restricciones, getRestriccion(), getFilaz(), getHolgura()).getSimplex();
                this.setVisible(false);
                ventanaS=new VentanaTablaSimplex(this, this.restricciones, this.variables, simplex, opcion);
                ventanaS.setVisible(true);
            }
        }else if(e.getSource()== btnAnalisis){
            
        }
    }
    
    public void matrizHolgura(String opcion, int contador){
        int fila;
        if(opcion.equals(">=")){
            artificial++;
            mayor++;
            fila = contador;
            filas.add(fila);
            fases=true;
            for(int i=0;i<restricciones;i++){
                if(contador==i){
                    holgura[contador][i]=-1;
                }else{
                    holgura[contador][i]=0;
                }
            }
        }else if(opcion.equals("<=")){
            normal++;
            for(int i=0;i<restricciones;i++){
                if(contador==i){
                    holgura[contador][i]=1;
                }else{
                    holgura[contador][i]=0;
                }
            }
        }else{
            artificial++;
            fila = contador;
            filas.add(fila);
            fases=true;
            for(int i=0;i<restricciones;i++){
                holgura[contador][i]=0;
            }
        }
    }
    
    public void matrizRestricciones(int contador){
        double numero;
        for(int i=0; i<this.variables+1; i++){
            numero = Double.parseDouble(txtRestricciones[i].getText());
            restriccion[contador][i]=numero;
        }
    }
    
    public void filaZ(){
        double numero;
        for(int i=0;i<variables;i++){
            numero = Double.parseDouble(txtObjetivo[i].getText());
            filaz[i]=numero;
        }
    }

    public double[][] getRestriccion() {
        return restriccion;
    }

    public double[][] getHolgura() {
        return holgura;
    }

    public double[] getFilaz() {
        return filaz;
    }

    public VentanaPrincipal getVentana() {
        return ventana;
    }

    public int opcion(){
        if(maximizar.isSelected()){
            return 1;
        }else{
            return 0;
        }
    }

    private void escritura() {
        btnAgregar.setEnabled(false);
        for(int i=0; i<txtRestricciones.length;i++){
            txtRestricciones[i].setEnabled(false);
        }
        CmbSigno.setEnabled(false);
        if(maximizar.isSelected()){
            txtModelo.append(maximizar.getText()+":\nZ=");
            for(int i=0;i<lblObjetivo.length;i++){
                int numero = Integer.parseInt(txtObjetivo[i].getText());
                if(numero<0){
                    txtModelo.append("-"+txtObjetivo[i].getText()+lblObjetivo[i].getText());
                }else{
                    txtModelo.append("+"+txtObjetivo[i].getText()+lblObjetivo[i].getText());
                }
            }
            txtModelo.append("\nSe deben agregar las variables de exceso o holgura ya sea el caso\n");
            int numero = this.variables+1;
            for(int i=0;i<this.restricciones;i++){
                txtModelo.append("x"+numero+"\n");
                numero++;
            }
        }
        
    }

    private void modeloMatematico() {
        for(int i=0; i<txtRestricciones.length-1; i++){
                int numero = Integer.parseInt(txtRestricciones[i].getText());
                if(numero<0){
                    txtModelo.append("-"+txtRestricciones[i].getText()+lblRestricciones[i].getText());
                }else{
                    txtModelo.append("+"+txtRestricciones[i].getText()+lblRestricciones[i].getText());
                }
                txtRestricciones[i].setText("");
            }
            txtRestricciones[0].requestFocus();
            txtModelo.append((String)CmbSigno.getSelectedItem()+txtRestricciones[txtRestricciones.length-1].getText());
            txtModelo.append("\n");
            txtRestricciones[txtRestricciones.length-1].setText("");
    }
}
