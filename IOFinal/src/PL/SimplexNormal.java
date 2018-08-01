/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

/**
 *
 * @author estudiantes
 */
public class SimplexNormal implements SimplexModulo{
    private int tamanoFilas, tamanoColumnas; 
    private double [][] simplex;
    private int columnaMinima, filaMinima;
    private double pivote, nuevoPivote;
    private Modelo modelo;
    
    public SimplexNormal(int tamanoFilas, int tamanoColumnas, double[][] restricciones, double[] z, double [][] holgura, Modelo a) {
        this.tamanoFilas = tamanoFilas;
        this.tamanoColumnas = tamanoColumnas;
        modelo = a;
        simplex = new double[this.tamanoColumnas+1][this.tamanoColumnas+this.tamanoFilas+2];
        llenadoFilaZ(this.simplex, z);
        llenadoBasicas(this.simplex, restricciones, holgura);
    }
    
    @Override
    public boolean simplexMaximizar() {     
        columnaMinima = columnaMinimaMax(this.simplex);
        System.out.println(columnaMinima);
        if(paradaMax(this.simplex)){
            return false;
        }else{
            llenadoRelacion(columnaMinima, this.simplex);
            filaMinima = filaMinima(this.simplex);
            System.out.println(filaMinima);
            pivote=this.simplex[filaMinima][columnaMinima];
            System.out.println(pivote);
            dividirFilaMinima(this.simplex, pivote, filaMinima);
            nuevoPivote=this.simplex[filaMinima][columnaMinima];
            gaussEnSimplex(this.simplex, filaMinima, columnaMinima, nuevoPivote);
            return true;
        }
        
    }    
    private void llenadoFilaZ(double [][] simplex, double []z){
        int columnas = simplex[0].length;
        for (int j=0;j<columnas-1; j++){
             if(z[j]!=0)simplex[0][j]=z[j]*-1;
        }
        
    }
    private void llenadoBasicas(double[][] simplex, double[][] restricciones, double [][] holgura) {
        int filas=simplex.length;
        int columnas =simplex[0].length;
        int restriccion = restricciones[0].length;
        int holgur = holgura[0].length;
        for(int i=1; i<filas;i++){
            for(int j=0; j<restriccion-1;j++){
                simplex[i][j]=restricciones[i-1][j];
            }
        }
        for(int i=1; i<filas;i++){
            for(int j=restriccion-1; j<columnas-2;j++){
                simplex[i][j]=holgura[i-1][j-(restriccion-1)];
            }
        }
        for(int i=1; i<filas;i++){
                simplex[i][columnas-2]=restricciones[i-1][restriccion-1];
            }
    }
    private int columnaMinimaMax(double[][] simplex) {
        int columnas = simplex[0].length;
        double valor=0;
        int i, minima=0;
        for (i=0; i<columnas-1;i++){
            if(valor>simplex[0][i] && simplex[0][i]<0){
                valor=simplex[0][i];
                minima=i;
            }
        }
        return minima;
    }
    private boolean paradaMax(double[][] simplex){
        int columnas=simplex[0].length;
        boolean parada=false;
        for(int i=0; i<columnas-1;i++){
            if(simplex[0][i]<0){
                parada=false;
                break;
            }else{
                parada=true;
            }
        }
        return parada;
    }
    private void llenadoRelacion(int columnaMinima, double[][] simplex) {
        int filas=simplex.length;
        double pivote, solucion;
        int tamano=simplex[0].length;;
        for(int i=1; i<filas; i++){
            pivote = simplex[i][columnaMinima];
            solucion = simplex[i][tamano-2];
            simplex[i][tamano-1]=solucion/pivote;
        }
    }

    private int filaMinima(double[][] simplex) {
        int filas=simplex.length;
        int minima=0;
        int columna=simplex[0].length;
        double numRelacion=10000000;
        for(int i=1;i<filas;i++){
            if(numRelacion>simplex[i][columna-1] && simplex[i][columna-1]>0){
                numRelacion=simplex[i][columna-1];
                minima=i;
            }
        }
        return minima;
    }

    private void dividirFilaMinima(double[][] simplex, double pivote, int filaMinima) {
        int columnas =simplex[0].length;
        for(int i=0;i<columnas-1;i++){
            simplex[filaMinima][i]=simplex[filaMinima][i]/pivote;
        }
    }

    public double[][] getSimplex() {
        return simplex;
    }
    

    private void gaussEnSimplex(double[][] simplex, int filaMinima, int columnaMinima, double pivote) {
        int columnas = simplex[0].length;
        int filas=simplex.length;
        double constante;
        for(int i=0;i<filas;i++){
            if(filaMinima!=i){
                constante = simplex[i][columnaMinima]/pivote;
                for(int j=0; j<columnas-1; j++){
                    simplex[i][j]=simplex[i][j]-constante*simplex[filaMinima][j];
                }
            }
        }
    }
    
    

    @Override
    public boolean simplexMinimizar() {
        columnaMinima = columnaMinimaMin(this.simplex);
        System.out.println(columnaMinima);
        if(paradaMin(this.simplex)){
            return false;
        }else{
            llenadoRelacion(columnaMinima, this.simplex);
            filaMinima = filaMinima(this.simplex);
            System.out.println(filaMinima);
            pivote=this.simplex[filaMinima][columnaMinima];
            System.out.println(pivote);
            dividirFilaMinima(this.simplex, pivote, filaMinima);
            nuevoPivote=this.simplex[filaMinima][columnaMinima];
            gaussEnSimplex(this.simplex, filaMinima, columnaMinima, nuevoPivote);
            return true;
        }
    }

    private int columnaMinimaMin(double[][] simplex) {
        int columnas = simplex[0].length;
        double valor=0;
        int i, minima=0;
        for (i=0; i<columnas-1;i++){
            if(valor<simplex[0][i] && simplex[0][i]>0){
                valor=simplex[0][i];
                minima=i;
            }
        }
        return minima;
    }
    private boolean paradaMin(double[][] simplex){
        int columnas=simplex[0].length;
        boolean parada=false;
        for(int i=0; i<columnas-1;i++){
            if(simplex[0][i]>0){
                parada=false;
                break;
            }else{
                parada=true;
            }
        }
        return parada;
    }

    public int getColumnaMinima() {
        return columnaMinima;
    }

    public int getFilaMinima() {
        return filaMinima;
    }

    public double getPivote() {
        return pivote;
    }
    
    
    
    
}
