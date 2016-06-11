/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
 *
 * @author josemurillo
 */
public abstract class Tanque extends Objeto implements Movible{
    protected boolean aliado;
    protected int velocidad;
    protected Image ultimoestado =null;
    protected String ultimadireccion ="";
    protected Image[] imagenes = {
                        tk.getImage(Tanque.class.getResource("Imagenes/tankD.gif")),
			tk.getImage(Tanque.class.getResource("Imagenes/tankU.gif")),
			tk.getImage(Tanque.class.getResource("Imagenes/tankL.gif")),
			tk.getImage(Tanque.class.getResource("Imagenes/tankR.gif")), 
			tk.getImage(Tanque.class.getResource("Imagenes/HtankD.gif")), 
			tk.getImage(Tanque.class.getResource("Imagenes/HtankU.gif")), 
			tk.getImage(Tanque.class.getResource("Imagenes/HtankL.gif")),
			tk.getImage(Tanque.class.getResource("Imagenes/HtankR.gif")), 
			tk.getImage(Tanque.class.getResource("Imagenes/HtankD2.gif")),
			tk.getImage(Tanque.class.getResource("Imagenes/HtankU2.gif")),
			tk.getImage(Tanque.class.getResource("Imagenes/HtankL2.gif")),
			tk.getImage(Tanque.class.getResource("Imagenes/HtankR2.gif")),
			};
    
    public Tanque(){
        super();
    }
    
    @Override
    public void mover(){
    }
    
    

    
}
