/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import Controlador.Controlador;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 *
 * @author josemurillo
 */
public class Enemigo extends Tanque {
    int numJugador;
    String direccion = "";
    public static int count = 0;
    private int rate = 1;
    private int oldX, oldY;
    private Controlador c;
    private static Random r = new Random();
    private int step = r.nextInt(10) + 5;
    private boolean bL = false, bU = false, bR = false, bD = false;
    
    public Enemigo(int x, int y, boolean bueno, String direccion, Controlador pC, int player){
        super();
        this.x = x;
        this.oldX = x;
        this.oldY = y;
        this.y = y;
        this.aliado = bueno;
        this.numJugador = player;
        this.vida = 50;
        this.largo = 35;
        this.ancho = 35;
        this.velocidad = 6;
        this.tk = Toolkit.getDefaultToolkit();
        this.c = pC;
        this.direccion = direccion;
        this.ultimoestado=imagenes[0];
    }
    
    public void colocar(Graphics g){
         if (!vivo){
             c.enemigos.remove(this);
             return;
          }
         
         switch (direccion){
             case "Aba":
                 ultimoestado=imagenes[0];
                 ultimadireccion=direccion;
                 g.drawImage(this.imagenes[0], this.x, this.y, null);
                 break;
            
             case "Arr":
                 ultimoestado=imagenes[1];
                 ultimadireccion=direccion;
                 g.drawImage(this.imagenes[1], this.x, this.y, null);
                 break;
            
             case "Izq":
                 ultimoestado=imagenes[2];
                 ultimadireccion=direccion;
                 g.drawImage(this.imagenes[2], this.x, this.y, null);
                 break;
                 
             case "Der":
                 ultimoestado=imagenes[3];
                 ultimadireccion=direccion;
                 g.drawImage(this.imagenes[3], this.x, this.y, null);
                 break;
             case "":
                 g.drawImage(ultimoestado,this.x,this.y,null);
                 break;
         }
         mover();
     } 
    
      
    public void mover(){
        this.oldX = x;
        this.oldY = y;
        switch (direccion) {  
        case "Izq":
            x -= velocidad;
            break;
        case "Arr":
            y -= velocidad;
            break;
        case "Der":
            x += velocidad;
            break;
        case "Aba":
            y += velocidad;
            break;
        case "":
            break;
        }
        
        if (x < 0){
            x = 0;
        }
        
        if (y < 40){
            y = 40;
        }
        
        if (x + this.ancho > c.frameAncho){
            x = c.frameAncho - this.ancho;
        }
        
        if (y + this.largo > c.frameLargo){
            y = c.frameLargo - this.largo;
        }
        
        String[] direcciones = {"Izq", "Arr", "Der", "Aba", ""};
        if (step == 0){
            step = r.nextInt(12) + 3;
            int mod = r.nextInt(9);
            if (jugadorCerca()){
                    if(this.x == c.jugadores.get(0).x){
                        if(this.y > c.jugadores.get(0).y){
                            direccion=direcciones[1];
                        }else if (this.y < c.jugadores.get(0).y){
                            direccion = direcciones[3];
                        }
                        
                    }else if(this.y == c.jugadores.get(0).y){ 
                        if(this.x > c.jugadores.get(0).x){
                            direccion = direcciones[0];
                        }else if (this.x < c.jugadores.get(0).x){
                            direccion = direcciones[2];
                        }
                    }else{
                        int rn = r.nextInt(direcciones.length);
                        direccion = direcciones[rn]; 
                    }
                    rate = 2;
		}else if (mod == 1){
                    rate=1;
                }else if(1 < mod && mod <=3){
                    rate = 1;
		}else{
                    int rn = r.nextInt(direcciones.length);
                    direccion = direcciones[rn]; 
                    rate=1;
                }    
            }
            step--;
            if(rate == 2){
                if (r.nextInt(40) > 35){
			this.disparar();
                }
            }else if (r.nextInt(40) > 38){
			this.disparar();
            }
    }
    
    boolean jugadorCerca(){
        int rx = this.x - 15;
        int ry = this.y - 15;
        if ((x - 15) < 0){
            rx = 0;
        }
        
        if ((y - 15) < 0){
            ry = 0;
        }
        Rectangle a = new Rectangle(rx, ry, 60, 60);
        if (this.vivo && a.intersects(c.jugadores.get(0).getrect())){
            return true;
        }
        return false;
    }
    
    public int getZona(int x, int y){
        int tempx = x;
        int tempy = y;
        if (tempx < 85 && tempy < 300){
            return 11;
        }else if(tempx>85 && tempx < 140 && tempy >0 && tempy <100){
            return 9;
        }else if(tempx>85 && tempx < 140 && tempy > 254 && tempy < 300){
            return 10;
        }else if(tempx > 0 && tempx < 200 && tempy > 300 && tempy < 715){
            return 12;
        }else if(tempx > 140 && tempx < 400 && tempy > 0 && tempy < 150){
            return 7;
        }else if(tempx > 140 && tempx < 400 && tempy > 210 && tempy < 300){
            return 8;
        }else if(tempx > 400 && tempx < 500 && tempy > 0 && tempy < 300){
            return 6;
        }else if(tempx > 500 && tempy > 0 && tempy < 180){
            return 5;
        }else if(tempx > 500 && tempy > 180 && tempy < 300){
            return 4;
        }else if(tempx > 520 && tempx < 600 && tempy > 3000 && tempy<715){
            return 2;
        }else if(tempx > 600 && tempy > 300 && tempy < 715){
            return 3;
        }
        return 1;
    }
    
    public void cambiarViejaDir(){
        x = this.oldX;
        y = this.oldY;
    }
    
    public void decidirDireccion(){
        direccion = "";
    }
    
    public void keyReleased(KeyEvent e){        
        decidirDireccion();
    }
    
    public Bala disparar(){
        if (!vivo){
            return null;
        }
        int balaX = this.x + this.ancho / 2 - Bala.ancho / 2; 
	int balaY = this.y + this.largo / 2 - Bala.largo / 2;
        Bala b = new Bala(balaX, balaY + 2, false, ultimadireccion, this.c); 
	c.balas.add(b);                                                
	return b; 
    }
    
    public Rectangle getrect(){
        return new Rectangle(this.x, this.y, this.ancho, this.largo);
    }
    
    public boolean isVivo(){
        return this.vivo;
    }
    
    public void setVida(boolean nVida){
        this.vivo = nVida;
    }
    
    public boolean esBueno(){
        return false;
    }
    
    public boolean chocaPared(Ladrillo w){
        if (this.vivo && this.getrect().intersects(w.getRect())){
            this.cambiarViejaDir();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean chocaPared(Metal w){
        if (this.vivo && this.getrect().intersects(w.getRect())){
            this.cambiarViejaDir();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean chocaRio(Rio r){
        if (this.vivo && this.getrect().intersects(r.getRect())){
            this.cambiarViejaDir();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean chocaAguila(Aguila a){
        if (this.vivo && this.getrect().intersects(a.getRect())){
            this.cambiarViejaDir();
            return true;
        }
        return false;
    }
    
    public boolean chocaTanque(java.util.List<Enemigo> enemigos){
        for (int i = 0; i < enemigos.size(); i++){
            Enemigo e = enemigos.get(i);
                if (this.vivo && e.isVivo()){
                   if (this.getrect().intersects(e.getrect())){
                       this.cambiarViejaDir();
                       e.cambiarViejaDir();
                       return true;
                   }
                }
        }
        return false;
    }
    
        public boolean chocaJugador(java.util.List<Jugador> jugador){
        for (int i = 0; i < jugador.size(); i++){
            Jugador e = jugador.get(i);
                if (this.vivo && e.isVivo()){
                   if (this.getrect().intersects(e.getrect())){
                       this.cambiarViejaDir();
                       e.cambiarViejaDir();
                       return true;
                   }
                }
        }
        return false;
    }
    
    public void setVida(int pVida){
        this.vida = pVida;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }  

    public int getVida() {
        return this.vida;
    }

    public void setVivo(boolean b) {
        this.vivo = b;
    }
}
