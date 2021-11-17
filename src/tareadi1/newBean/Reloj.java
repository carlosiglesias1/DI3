/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadi1.newBean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.*;

import java.beans.PropertyChangeEvent; // event
import java.beans.PropertyChangeListener; // Listen
import java.beans.PropertyChangeSupport; // support
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author carlo
 */
public class Reloj extends JLabel implements Serializable, ActionListener {
    
    private int hora;
    private int minuto;
    private int horaAlarma;
    private int minutoAlarma;
    private Timer timer;
    private boolean isTwentyFour;
    private boolean alarmaActiva;
    private String mensaje;
    private PropertyChangeSupport listeners;
    private LocalTime time;
    
    public Reloj() {
        this.listeners = new PropertyChangeSupport(this);
        this.timer = new Timer(1000, this);
        this.time = LocalTime.now();
        this.hora = time.getHour();
        this.minuto = time.getMinute();
        this.isTwentyFour = false;
        this.alarmaActiva = false;
        this.mensaje = "";
        this.setText(Integer.toString(this.hora) + " : " + Integer.toString(this.minuto) + " : " + this.time.getSecond());
        this.timer.addActionListener(this);
        this.timer.start();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Reloj.this.setIsTwentyFour(true);
            }
        });
    }
    
    public int getHora() {
        return hora;
    }
    
    public int getMinuto() {
        return minuto;
    }
    
    public Timer getTimer() {
        return timer;
    }
    
    public boolean isIsTwentyFour() {
        return isTwentyFour;
    }
    
    public boolean isAlarmaActiva() {
        return alarmaActiva;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public PropertyChangeSupport getPropertySupport() {
        return listeners;
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    public void setHora(int hora) {
        this.hora = hora;
    }
    
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    public void setIsTwentyFour(boolean isTwentyFour) {
        this.isTwentyFour = isTwentyFour;
    }
    
    public void setAlarmaActiva(boolean alarmaActiva) {
        this.alarmaActiva = alarmaActiva;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.listeners = propertySupport;
    }
    
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.time = LocalTime.now();
        this.hora = time.getHour();
        this.minuto = time.getMinute();
        if (this.isIsTwentyFour()) {
            this.setText(this.time.format(DateTimeFormatter.ofPattern("HH : mm : ss")));
        } else {
            this.setText(this.time.format(DateTimeFormatter.ofPattern("hh : mm : ss aa")));
        }
        System.out.println(hora);
        this.repaint();
        this.timer.restart();
    }
}
