/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PL;

import Vista.VentanaPrincipal;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Andres
 */
public class MetodoGrafico {
    
    private ArrayList<Puntos> puntos;
    private Puntos puntosI;
    private JFreeChart grafica;
    private XYSeriesCollection series;
    private XYSeries datos;
    private double puntoX1;
    private double puntoX2;
    private Modelo modelo;
    
    public MetodoGrafico(Modelo a){
        puntos = new ArrayList<Puntos>();       
        modelo = a;
    }
    
    public JFreeChart grafica(double zX1, double zX2) throws IOException{
        series = new XYSeriesCollection();
        for(int i=0; i<puntos.size(); i++){
            XYSeries datos = new XYSeries("Restriccion: "+(int)(i+1));
            double x1=getPuntos().get(i).getPuntoX();
            double x2=getPuntos().get(i).getPuntoY();
            
            if(x1==0){
                datos.add(puntos.get(i).getCoeficiente(),0);
                datos.add(puntos.get(i).getCoeficiente(),25);
               
                
            }
            else if (x1<0){
                double x=puntos.get(i).getCoeficiente()+5;
                datos.add((puntos.get(i).getCoeficiente()/puntos.get(i).getPuntoY()),0);
                datos.add(puntos.get(i).getCoeficiente()+5,x);
                             
                         }
            else if(x2==0){
                datos.add(0,puntos.get(i).getCoeficiente());
                datos.add(25,puntos.get(i).getCoeficiente());
                
            }
            else{
                datos.add(puntos.get(i).getCoeficiente()/puntos.get(i).getPuntoY(),0);
                datos.add(0,puntos.get(i).getCoeficiente()/puntos.get(i).getPuntoX());
            }
            
            series.addSeries(datos);
        }
        XYSeries datos = new XYSeries("Funcion Objetivo");
        datos.add(0, zX2);
        datos.add(zX1, 0);
        series.addSeries(datos);
        grafica = ChartFactory.createXYLineChart("Optimizacion", JOptionPane.showInputDialog("Ingrese el Rotulo del eje X2"), JOptionPane.showInputDialog("Ingrese el Rotulo del eje X1"), 
                series, PlotOrientation.HORIZONTAL, true, true, false);
        ChartUtilities.saveChartAsPNG(new File("Grafica IO.png"), grafica, 600, 600);
        return grafica;
    }
    
    public void agregarPuntos(double puntoX, double puntoY, double coeficiente){
        puntosI = new Puntos();
        puntosI.setPuntoX(puntoX);
        puntosI.setCoeficiente(coeficiente);
        puntosI.setPuntoY(puntoY);
        puntos.add(puntosI);
    }

    public ArrayList<Puntos> getPuntos() {
        return puntos;
    }
    
    public double zOptimo(double zX1,double zX2 ){
        double z=0;
        for(int i=0; i<(getPuntos().size()-1);i++){
            double a1 = getPuntos().get(i).getPuntoX();
            double b1 = getPuntos().get(i).getPuntoY();
            double c1 = getPuntos().get(i).getCoeficiente();
            for(int j=i+1;j<getPuntos().size();j++){
                double a2 = getPuntos().get(j).getPuntoX();
                double b2 = getPuntos().get(j).getPuntoY();
                double c2 = getPuntos().get(j).getCoeficiente();
                z = calculoZ(zX1,zX2, a1, b1, c1, a2, b2, c2, z);
            }
        }
        return z;
    } 

    private double calculoZ(double zX1, double zX2, double a1, double b1, double c1, double a2, double b2, double c2, double z) {
        double punX2 = (c2*a1-a2*c1)/(b2*a1-a2*b1);
        double punX1 = (c1-b1*punX2)/a1;
        double zOptimo = zX1*punX1+zX2*punX2;
        if(zOptimo>z){
            this.puntoX1=punX1;
            this.puntoX2=punX2;
            return zOptimo;
        }
        return z;
    }
    public void analisisDeSensibilidad(double zX1, double zX2 ){
        
    }
    public double getPuntoX1() {
        return puntoX1;
    }

    public double getPuntoX2() {
        return puntoX2;
    }

    
   
}
