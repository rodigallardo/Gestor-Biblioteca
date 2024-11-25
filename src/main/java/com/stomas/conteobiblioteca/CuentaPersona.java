/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stomas.conteobiblioteca;

import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author parag
 */
public class CuentaPersona extends Thread {

    private Integer cant_personas;
    private int llegan_personas;
    private int piden_libros;
    private Main main;
    Random numero = new Random();
    Random numero2 = new Random();
    private volatile boolean running = true;

    public CuentaPersona() {
    }

    public CuentaPersona(Integer cant_personas, int llegan_personas, int piden_libros, Main main) {
        this.cant_personas = cant_personas;
        this.llegan_personas = llegan_personas;
        this.piden_libros = piden_libros;
        this.main = main;
    }

    public Random getNumero() {
        return numero;
    }

    public void setNumero(Random numero) {
        this.numero = numero;
    }

    public Random getNumero2() {
        return numero2;
    }

    public void setNumero2(Random numero2) {
        this.numero2 = numero2;
    }

    public Integer getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(Integer cant_personas) {
        this.cant_personas = cant_personas;
    }

    public int getLlegan_personas() {
        return llegan_personas;
    }

    public void setLlegan_personas(int llegan_personas) {
        this.llegan_personas = llegan_personas;
    }

    public int getPiden_libros() {
        return piden_libros;
    }

    public void setPiden_libros(int piden_libros) {
        this.piden_libros = piden_libros;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public String toString() {
        return "CuentaPersona{" + "cant_personas=" + cant_personas + ", llegan_personas=" + llegan_personas + ", numero=" + numero + '}';
    }

    public boolean isRunning() {
        return running;
    }

    public void detenerHilo() {
        running = false;  // Cambiar la bandera para detener el hilo
    }

    // Método para actualizar el JLabel en la interfaz gráfica
    public synchronized void modificarLabel(JLabel label) {
        label.setText(cant_personas.toString());
    }

    //SINCRONIZAR METODOS QUE MODIFICAN LAS VARIABLES 
    public synchronized void incrementarPersonas(int personas) {
        if (cant_personas + personas > 100) {
            personas = 100 - cant_personas;  // Ajustar si llega al límite
        }
        cant_personas += personas; //cant_personas + llegan_personas
        modificarLabel(main.getLb_cuenta_personas());
    }

    @Override
    public void run() {
        if (running) {
            if (cant_personas < 100) {
                llegan_personas = numero.nextInt(1, 4);  // Simular llegada de personas
                piden_libros = numero2.nextInt(0, 8);    // Simular pedidos de libros
                incrementarPersonas(llegan_personas);
                System.out.println("Llegaron " + llegan_personas + " personas, y pidieron " + piden_libros + " libros.");
            } else {
                this.detenerHilo();
            }
        }
    }

}
