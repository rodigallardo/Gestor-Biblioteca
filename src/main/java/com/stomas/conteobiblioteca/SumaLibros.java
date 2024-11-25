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
public class SumaLibros extends Thread {

    private Integer cant_libros;
    private CuentaPersona persona;
    private Main main;
    private volatile boolean running = true;

    public SumaLibros() {
    }

    public SumaLibros(Integer cant_libros, CuentaPersona persona, Main main) {
        this.cant_libros = cant_libros;
        this.persona = persona;
        this.main = main;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Integer getCant_libros() {
        return cant_libros;
    }

    public void setCant_libros(Integer cant_libros) {
        this.cant_libros = cant_libros;
    }

    public CuentaPersona getPersona() {
        return persona;
    }

    public void setPersona(CuentaPersona persona) {
        this.persona = persona;
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

    public boolean isRunning() {
        return running;
    }

    // Método para actualizar el JLabel en la interfaz gráfica
    public synchronized void modificarLabel(JLabel label) {
        label.setText(cant_libros.toString());
    }

    //SINCRONIZAR METODOS QUE SON LLAMADOS DE MULTIPLES HILOS    
    public synchronized void incrementarLibros(int libros) {
        cant_libros += libros;
        modificarLabel(main.getLb_cuenta_lib_pedidos());
    }

    @Override
    public void run() {
        if (running) {
            if (persona.getCant_personas() < 100) {
                incrementarLibros(persona.getPiden_libros());
                System.out.println("Se llevan pedidos " + cant_libros + " libros.");
            }else{
                detenerHilo();
            }
        }
    }

}
