/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Controlador.Controlador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 *
 * @author josemurillo
 */
public class Aguila extends Objeto implements Inmovible{
    private static Image[] aguilaImagen = null;
    private Controlador c;
    
    public Aguila(int x, int y, Controlador c){
        super();
        this.x = x;
        this.y = y;
        this.c = c;
        this.tk = Toolkit.getDefaultToolkit();
        aguilaImagen = new Image[] {
            tk.getImage(Aguila.class.getResource("Imagenes/home.jpg")),
                
                };
        this.largo = 43;
        this.ancho = 43;
        
    }
    
    public void gameOver( Graphics g){
                for (int i = 0; i < 1; i++){
            c.jugadores.get(i).setVivo(false);
            
        }
        c.enemigos.clear();
        c.jugadores.clear();
        c.metalWall.clear();
        c.otros.clear();
        c.rio.clear();
        c.arboles.clear();
        c.balas.clear();

        Color c = g.getColor();
        g.setColor(Color.green);
        Font f = g.getFont();
        g.setFont(new Font(" ", Font.PLAIN, 40));
        g.setFont(f);
        g.setColor(c);
    }
    
    public void colocar(Graphics g){
        if (vivo){
            g.drawImage(aguilaImagen[0], x, y, null);
            for (int i = 0; i < c.homeLadrillos.size(); i++){
                Ladrillo w = c.homeLadrillos.get(i);
                w.colocar(g);
            }
        }else{
            gameOver(g);
        }
        g.drawImage(aguilaImagen[0], x, y,null);
    }
    
    public boolean isVivo(){
        return this.vivo;
    }
    
    public void setVivo(boolean pVivo){
        this.vivo = pVivo;
    }
    public Rectangle getRect(){
        return new Rectangle(x, y, this.ancho, this.largo);
    }

    

}
