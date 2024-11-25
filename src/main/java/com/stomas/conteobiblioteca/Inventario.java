/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stomas.conteobiblioteca;

import javax.swing.JLabel;

/**
 *
 * @author parag
 */
public class Inventario extends Thread {

    private Integer total_libros;
    private CuentaPersona persona;
    private SumaLibros libro;
    private Main main;
    private volatile boolean running = true;

    public Inventario() {
    }

    public Inventario(Integer total_libros, CuentaPersona persona, SumaLibros libro, Main main) {
        this.total_libros = total_libros;
        this.persona = persona;
        this.libro = libro;
        this.main = main;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Integer getTotal_libros() {
        return total_libros;
    }

    public void setTotal_libros(Integer total_libros) {
        this.total_libros = total_libros;
    }

    public CuentaPersona getPersona() {
        return persona;
    }

    public void setPersona(CuentaPersona persona) {
        this.persona = persona;
    }

    public SumaLibros getLibro() {
        return libro;
    }

    public void setLibro(SumaLibros libro) {
        this.libro = libro;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void detenerHilo() {
        running = false;  // Cambiar la bandera para detener el hilo
    }

    // Método para actualizar el JLabel en la interfaz gráfica
    public synchronized void modificarLabel(JLabel label) {
        label.setText(total_libros.toString());
    }

    //SINCRONIZAR METODOS QUE MODIFICAN LAS VARIABLES 
    public synchronized void decrementarLibros(int libros) {
        total_libros -= libros;
        modificarLabel(main.getLb_cuenta_lib_inventario());
    }

    @Override
    public void run() {
        if (running) {
            if (persona.getCant_personas() < 100) {
                decrementarLibros(persona.getPiden_libros());
                System.out.println("Se descontaron " + persona.getPiden_libros() + " libros, quedan " + total_libros + " libros.");
                System.out.println("");
            }else{
                detenerHilo();
            }
        }
    }
}
