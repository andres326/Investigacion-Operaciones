/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.io.IOException;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Andres
 */
public class VentanaGrafica extends JFrame{
    private final VentanaDatos ventana;
    private JFreeChart grafica;
    private XYSeriesCollection series;
    
    public VentanaGrafica(VentanaDatos a) throws IOException{
        ventana = a; 
        ChartPanel panel = new ChartPanel(ventana.getVentana().getSistema().getMetodo().grafica(Double.parseDouble(ventana.getTxtX1Z().getText()),Double.parseDouble(ventana.getTxtX2Z().getText())));
        this.getContentPane().add(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }   
    public VentanaDatos getVentana() {
        return ventana;
    }
    
    
}
