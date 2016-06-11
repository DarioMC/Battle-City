/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import Controlador.Controlador;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;

/**
 *
 * @author josemurillo
 */
public class Jugador extends Tanque{
    Controlador c;
    int numJugador;
    String direccion = "Arr";
    public static int count = 0;
    private int rate = 1;
    private int oldX, oldY;
    private int podervelocidad=12;
    private boolean bL = false, bU = false, bR = false, bD = false ;
    
    
    
    
    public Jugador(int x, int y, boolean bueno, String direccion, Controlador pC, int player){
        super();
        this.x = x;
        this.oldX = x;
        this.oldY = y;
        this.y = y;
        this.aliado = bueno;
        this.numJugador = player;
        this.vida = 20000;
        this.largo = 35;
        this.ancho = 35;
        this.velocidad = 6;
        this.tk = Toolkit.getDefaultToolkit();
        this.c = pC;
        this.direccion = direccion;
        this.ultimoestado = imagenes[5];
    }
    
    public void colocar(Graphics g){
         if (!vivo){
             c.jugadores.remove(this);
             return;
          }
         
         switch (direccion){
             case "Aba":
                 ultimoestado=imagenes[4];
                 ultimadireccion=direccion;
                 if (numJugador == 1){
                     g.drawImage(imagenes[4], x, y, null);
                 }else if (c.jugador2 && numJugador == 2){
                     g.drawImage(imagenes[8], x, y, null);
                 }
                 break;
            
             case "Arr":
                 ultimoestado=imagenes[5];
                 ultimadireccion=direccion;
                 if (numJugador == 1){
                     g.drawImage(imagenes[5], x, y, null);
                 }else if (c.jugador2 && numJugador == 2){
                     g.drawImage(imagenes[9], x, y, null);
                 }
                 break;
            
             case "Izq":
                 ultimoestado=imagenes[6];
                 ultimadireccion=direccion;
                 if (numJugador == 1){
                     g.drawImage(imagenes[6], x, y, null);
                 }else if (c.jugador2 && numJugador == 2){
                     g.drawImage(imagenes[10], x, y, null);
                 }
                 break;
                 
             case "Der":
                 ultimoestado=imagenes[7];
                 ultimadireccion=direccion;
                 if (numJugador == 1){
                     g.drawImage(imagenes[7], x, y, null);
                 }else if (c.jugador2 && numJugador == 2){
                     g.drawImage(imagenes[11], x , y, null);
                 }
                 
            case "":
                 g.drawImage(ultimoestado, x, y, null);
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
    
    public void keyPressed(KeyEvent e){
        int tecla = e.getKeyCode();
        if (numJugador == 1){
            switch(tecla){
                case KeyEvent.VK_R:
                    /*c.jugadores.clear();
                    c.enemigos.clear();
                    c.balas.clear();
                    c.arboles.clear();
                    c.homeLadrillos.clear();
                    c.otros.clear();
                    c.metalWall.clear();
                    setVida(false);
                    if (c.enemigos.size() == 0){
                        for (int i = 0; i < 20; i++){
                            if (i < 9){
                                c.enemigos.add(new Enemigo(150 + 70 * i, 40, false, "Der", c,0));
                            }else if (i < 15){
                                c.enemigos.add(new Enemigo(700, 140 + 50 * (i -6), false, "Aba", c,0));
                            }else{
                                c.enemigos.add(new Enemigo(10,  50 * (i - 12), false, "Izq", c,0));
                            }
                            
                            c.jugadores.add(new Jugador(300, 560, true, "", c, 1));
                            if (!c.aguila.isVivo()){
                                c.aguila.setVivo(true);
                            }
                               
                        }
                    }*/
                    c.dispose();
                            Controlador abc = new Controlador();

                    
                    break;
                    
                case KeyEvent.VK_D:
                    bR = true;
                    break;
                
                case KeyEvent.VK_A:
                    bL = true;
                    break;
                case KeyEvent.VK_W:
                    bU = true;
                    break;
                case KeyEvent.VK_S:
                    bD = true;
                    break;
            }
        }
        
        if (numJugador == 2){
            switch (tecla){
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
            }
        }
        
        decidirDireccion();
        
    }
    
    public void decidirDireccion(){
        if (!bL && !bU && bR && !bD){
            direccion = "Der";
        }else if (bL && !bU && !bR && !bD){
           direccion = "Izq";
 
        }else if (!bL && bU && !bR && !bD){
            direccion = "Arr";
        }else if (!bL && !bU && !bR && bD){
            direccion = "Aba";
        }else if (!bL && !bU && !bR && !bD){
            direccion = "";
        } 
    }
    
    public void keyReleased(KeyEvent e){
        int tecla = e.getKeyCode();
        if (numJugador == 1){
            switch (tecla){
                case KeyEvent.VK_F:
                    disparar(podervelocidad);
                    break;
                
                case KeyEvent.VK_D:
                    bR = false;
                    break;
                
                case KeyEvent.VK_A:
                    bL = false;
                    break;
                
                case KeyEvent.VK_W:
                    bU = false;
                    break;
                 
                case KeyEvent.VK_S:
                    bD = false;
                    break;
            }
        }
        
        if (numJugador == 2){
            switch (tecla){
                case KeyEvent.VK_SLASH:
                    disparar();
                    break;
				
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
			
		case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
			
		case KeyEvent.VK_UP:
                    bU = false;
                    break;
			
		case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
            }
        }
        decidirDireccion();
    }
    
    public Bala disparar(){
        if (!vivo){
            return null;
        }
        int balaX = this.x + this.ancho / 2 - Bala.ancho / 2; 
	int balaY = this.y + this.largo / 2 - Bala.largo / 2;
        Bala b = new Bala(balaX, balaY + 2, true, this.ultimadireccion, this.c); 
	c.balas.add(b);                                                
	return b; 
    }
    
    public Bala disparar(int velocidad){
        if (!vivo){
            return null;
        }
        int balaX = this.x + this.ancho / 2 - Bala.ancho / 2; 
	int balaY = this.y + this.largo / 2 - Bala.largo / 2;
        Bala b = new Bala(balaX, balaY + 2, true, this.ultimadireccion, this.c,velocidad); 
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
        return true;
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
    
    public boolean chocaPoder(Poderes r){
        if (this.vivo && this.getrect().intersects(r.getRect())){
            r.tanqueupgrade(this);
            this.c.poderes.remove(r);
            
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
    
    public void setVida(int pVida){
        this.vida = pVida;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }  

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    
    
    public Image getimagen()
    {
    return imagenes[1];
    }        

    public int getVida() {
        return this.vida;
    }

    public void setPodervelocidad(int podervelocidad) {
        this.podervelocidad = podervelocidad;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }


    
    
    
}
