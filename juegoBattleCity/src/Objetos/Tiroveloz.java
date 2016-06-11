/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Controlador.Controlador;
import static Objetos.Objeto.tk;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author admin
 */
public class Tiroveloz extends Poderes {

    private static Image[] Poderesimagenes = null;

    
    public Tiroveloz(int x, int y, Controlador pC){
        super();
        this.ancho = 32;
        this.largo = 32;
        this.x = x;
        this.y = y;
        this.c = pC;
        
        Poderesimagenes = new Image[]{tk.getImage(Rio.class.getResource("Imagenes/hpa.png"))
        };
      
    }
    
    @Override
    public Rectangle getRect(){
        return new Rectangle(this.x, this.y, this.ancho, this.largo);
    }
    
    @Override
    public void colocar(Graphics g) {
         g.drawImage(Poderesimagenes[0], this.x, this.y, null);
    }
    
    @Override
    public void tanqueupgrade (Jugador j){
        j.setPodervelocidad(40);
        //j.setVida(j.getVida()+100);
    }
    
}
