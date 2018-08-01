/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PL;

import Vista.VentanaPrincipal;

/**
 *
 * @author Andres
 */
public class Modelo {
    
    private MetodoGrafico metodo;
    public SimplexNormal simplexN;
    public SimplexDosFases simplexM;
    private VentanaPrincipal ventana;

    public Modelo() {
        metodo = new MetodoGrafico(this);
        ventana = new VentanaPrincipal(this);
        ventana.setVisible(true);
    }

    public MetodoGrafico getMetodo() {
        return metodo;
    }
    
    public boolean simplexNormal(int opcion){
        boolean simplex=true;
        if(opcion==1){
            simplex = simplexN.simplexMaximizar();
        }else{
            simplex = simplexN.simplexMinimizar();
        }
        return simplex;
    }
    
    public boolean simplexDosFases(int opcion){
        boolean simplex=true;
        if(opcion==1){
            simplex = simplexM.simplexMaximizar();
        }else{
            simplex = simplexM.simplexMinimizar();
        }
        return simplex;
    }

    public SimplexNormal getSimplexN(int tamanoFilas, int tamanoColumnas, double[][] restricciones, double[] z, double [][] holgura) {
        if(simplexN==null){
            simplexN=new SimplexNormal(tamanoFilas, tamanoColumnas, restricciones, z, holgura, this);
        }
        return simplexN;
    }
    
    public SimplexDosFases getSimplexM(int tamanoFilas, int tamanoColumnas, int artificiales,int normal,int mayor, double[][] restricciones, double[]z, double[][] holgura,double[][]artificial) {
        if(simplexM==null){
            simplexM=new SimplexDosFases(tamanoFilas, tamanoColumnas, artificiales, normal,mayor, restricciones, z, holgura, artificial, this);
        }
        return simplexM;
    }
    
    
}
