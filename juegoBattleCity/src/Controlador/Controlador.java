/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
import Objetos.*;



/**
 *
 * @author josemurillo
 */
public class Controlador extends Frame implements ActionListener {
    public static final int frameAncho = 800; 
    public static final int frameLargo = 600;
    public static boolean printable = true;

    MenuBar jmb = null;
    Menu menu1 = null, menu2 = null, menu3 = null, menu4 = null,menu5=null;
    MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null,
    jmi6 = null, jmi7 = null, jmi8 = null, jmi9 = null,jmi10=null;  
    Image imagenPantalla = null;
    
    public FactoryPoderes FactoryPoderes = new FactoryPoderes();
    public boolean jugador2 = false;
    public Aguila aguila = new Aguila(373, 557, this);
    public HPup poder = new HPup (073,257,this);
    public Tiroveloz poder2 = new Tiroveloz (173,257,this);
    public VelocidadMov poder3 = new VelocidadMov (273,257,this);
    public boolean gano = false;
    public boolean perdio = false;
    public List<Rio> rio = new ArrayList<Rio>();
    public List<Jugador> jugadores = new ArrayList<Jugador>();
    public List<Enemigo> enemigos = new ArrayList<Enemigo>();        
    public List<Bala> balas = new ArrayList<Bala>();
    public List<Arbol> arboles = new ArrayList<Arbol>();
    public List<Ladrillo> homeLadrillos = new ArrayList<Ladrillo>();
    public List<Ladrillo> otros = new ArrayList<Ladrillo>();
    public List<Metal> metalWall = new ArrayList<Metal>();
    public List<Poderes> poderes = new ArrayList<Poderes>();
    Jugador tank = new Jugador(500,500,true,"",this,1);    
    Enemigo tank2 = new Enemigo(85,50,false,"",this,0);
    Enemigo tank3 = new Enemigo(300,300,false,"",this,0);
    
    
            
    @Override
    public void update(Graphics g){
        imagenPantalla = this.createImage(frameAncho, frameLargo);
        
        Graphics gps = imagenPantalla.getGraphics();
        Color c = gps.getColor();
        gps.setColor(Color.blue);
        gps.fillRect(0, 0, frameAncho, frameLargo);
        gps.setColor(c);
        framPaint(gps);
        g.drawImage(imagenPantalla, 0, 0, null);
    }
    
    public void framPaint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        jugadores.get(0).colocar(g);
        aguila.colocar(g);
        //impresion de mapa
        
        //impresion informativa
        
        g.drawString("Vida: ", 380, 70);
        g.drawString(""+jugadores.get(0).getVida(), 650, 70);
        
        //impresion mapa
        
        for (int i = 0; i < homeLadrillos.size(); i++){
            homeLadrillos.get(i).colocar(g);
        }
        for (int i = 0; i < metalWall.size(); i++){
            metalWall.get(i).colocar(g);
        }
        for (int i = 0; i < arboles.size(); i++){
            arboles.get(i).colocar(g);
        }
        for (int i = 0; i < poderes.size(); i++){
            poderes.get(i).colocar(g);
        }
        aguila.colocar(g);
        
        
        //validacion del hitbox de los tanques con el entorno y otros tanques
        //hitbox de jugador con enemigos y entorno
        
        
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador aux = jugadores.get(i);
            aux.chocaTanque(enemigos);
            aux.chocaAguila(aguila);
            for (int j = 0; j < otros.size(); j++) {
                aux.chocaPared(otros.get(j));
            }
            for (int k = 0; k < rio.size(); k++) {
                aux.chocaRio(rio.get(k));
            }
            for (int l = 0; l < metalWall.size(); l++) {
                aux.chocaPared(metalWall.get(l));
            }
            for (int l = 0; l < homeLadrillos.size(); l++) {
                aux.chocaPared(homeLadrillos.get(l));
            }
            for (int k = 0; k < poderes.size(); k++) {
                aux.chocaPoder(poderes.get(k));
               } 
        }
        //hitbox de enemigos contra entorno
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo aux = enemigos.get(i);
            //aux.chocaTanque(enemigos); esto es hitbox entre ellos
            aux.chocaAguila(aguila);
            for (int j = 0; j < otros.size(); j++) {
                aux.chocaPared(otros.get(j));
            }
            for (int k = 0; k < rio.size(); k++) {
                aux.chocaRio(rio.get(k));
            }
            for (int l = 0; l < metalWall.size(); l++) {
                aux.chocaPared(metalWall.get(l));
            }
            for (int l = 0; l < homeLadrillos.size(); l++) {
                aux.chocaPared(homeLadrillos.get(l));
            }
            
        }
        
       
        for (int i = 0; i < rio.size(); i++) {
			Rio r = rio.get(i);
			r.colocar(g);
        }
        
        //balas histbox e impresion
        for (int i = 0; i < balas.size(); i++) { 
			Bala b = balas.get(i);
                        b.hitTanques(enemigos);//recorrer todos enemigos a ver si le pega
                        b.hitTanqueAliado(jugadores.get(0));//ver si le pega a uno mismo
                        b.chocaAguila();
			b.colocar(g); 
                        for (int j = 0; j < homeLadrillos.size(); j++) { 
				Ladrillo mw = homeLadrillos.get(j);
				b.chocaLadrillo(mw);
			}
                        for (int j = 0; j < otros.size(); j++) { 
				Ladrillo mw = otros.get(j);
				b.chocaLadrillo(mw);
			}
                        for (int j = 0; j < metalWall.size(); j++) { 
				Metal mw = metalWall.get(j);
				b.chocaMetal(mw);
			}
	}
        //balas hitbox e impresion

        
       

        
        //fin validacion hitbox
        //impresion de tanques
        
        for (int i = 0; i < enemigos.size(); i++) {
            enemigos.get(i).colocar(g);
        }

        
        
        
    }
        

    public Controlador(){
        jmb = new MenuBar();
		menu1 = new Menu("Game");
		menu2 = new Menu("Pause/Continue");
		menu3 = new Menu("Help");
		menu4 = new Menu("Level");
		menu5 = new Menu("Addition");
		menu1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menu2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menu3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menu4.setFont(new Font("Times New Roman", Font.BOLD, 15));

		jmi1 = new MenuItem("Nuevo Juego");
		jmi2 = new MenuItem("Salir");
		jmi3 = new MenuItem("Pausa");
		jmi4 = new MenuItem("Continuar");
		jmi5 = new MenuItem("Ayuda");
		jmi6 = new MenuItem("Nivel 1");
		jmi7 = new MenuItem("Nivel 2");
		jmi8 = new MenuItem("Nivel 3");
		jmi9 = new MenuItem("Nivel 4");
		jmi10=new MenuItem("Agregar jugador 2");
		jmi1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jmi2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jmi3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jmi4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jmi5.setFont(new Font("Times New Roman", Font.BOLD, 15));

		menu1.add(jmi1);
		menu1.add(jmi2);
		menu2.add(jmi3);
		menu2.add(jmi4);
		menu3.add(jmi5);
		menu4.add(jmi6);
		menu4.add(jmi7);
		menu4.add(jmi8);
		menu4.add(jmi9);
		menu5.add(jmi10);

		jmb.add(menu1);
		jmb.add(menu2);

		jmb.add(menu4);
		jmb.add(menu5);
		jmb.add(menu3);
		

		jmi1.addActionListener(this);
		jmi1.setActionCommand("JuegoNuevo");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("Salir");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("Detener");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("Continuar");
		jmi5.addActionListener(this);
		jmi5.setActionCommand("Ayuda");
		jmi6.addActionListener(this);
		jmi6.setActionCommand("Nivel 1");
		jmi7.addActionListener(this);
		jmi7.setActionCommand("Nivel 2");
		jmi8.addActionListener(this);
		jmi8.setActionCommand("Nivel 3");
		jmi9.addActionListener(this);
		jmi9.setActionCommand("Nivel 4");
		jmi10.addActionListener(this);
		jmi10.setActionCommand("Jugador 2");
                

		this.setMenuBar(jmb);
		this.setVisible(true);
                
                 
                //Aca es donde debemos meter cuantos muros hay, cuantos bosques, etc, para que luego el repaint se encarge de dibujarlo
                
                
                jugadores.add(tank);
                
                //dibujado de enemigos nivel 1
		for (int i = 0; i < 20; i++) {
			if (i < 9) 
				enemigos.add(new Enemigo(150 + 70 * i, 40, false, "Aba", this,0));
			else if (i < 15)
				enemigos.add(new Enemigo(700, 140 + 50 * (i - 6), false, "Aba",
						this,0));
			else
				enemigos.add(new Enemigo(10, 50 * (i - 12), false, "Aba",
								this,0));
		}
                
                
                //creacion de homeladrillos nivel 1
                for (int i = 0; i < 10; i++){
                    if (i < 4){
                        homeLadrillos.add(new Ladrillo(350, 580 - 21 * i, this));
                    }
                    			else if (i < 7)
				homeLadrillos.add(new Ladrillo(372 + 22 * (i - 4), 517, this));
			else
				homeLadrillos.add(new Ladrillo(416, 538 + (i - 7) * 21, this));
                }
                
                //creacion de rios nivel 1
                
                rio.add(new Rio(85, 100, this));
                
                
                //creacion de metal
                
                		for (int i = 0; i < 20; i++) { 
			if (i < 10) {
				metalWall.add(new Metal(140 + 30 * i, 150, this));
				metalWall.add(new Metal(600, 400 + 20 * (i), this));
			} else if (i < 20)
				metalWall.add(new Metal(140 + 30 * (i - 10), 180, this));
			
		}
                
                for (int i = 0; i < 4; i++) { 
			if (i < 4) {
				arboles.add(new Arbol(0 + 30 * i, 360, this));
				arboles.add(new Arbol(220 + 30 * i, 360, this));
				arboles.add(new Arbol(440 + 30 * i, 360, this));
				arboles.add(new Arbol(660 + 30 * i, 360, this));
			}

		}
                poderes.add(FactoryPoderes.crearpoder(1, 150, 50, this));
                poderes.add(FactoryPoderes.crearpoder(2, 250, 50, this));
                poderes.add(FactoryPoderes.crearpoder(3, 450, 150, this));
                poderes.add(FactoryPoderes.crearpoder(1, 650, 50, this));
                //poderes.add()
                                
                                
                // fin edicion de mapa
                
                this.setSize(frameAncho, frameLargo);
		this.setLocation(280, 50); 
		this
				.setTitle("Battle City    La peor version ever");

		this.addWindowListener(new WindowAdapter() { 
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
		this.setResizable(false);
		this.setBackground(Color.RED);
		this.setVisible(true);

		this.addKeyListener(new KeyMonitor());
		new Thread(new PaintThread()).start(); 


                
                
    
    }
    
    public void actionPerformed(ActionEvent e){
    if (e.getActionCommand().equals("JuegoNuevo")) {
        printable=false;
        this.arboles.clear();
        
    }
    if (e.getActionCommand().equals("Nivel 2")) {
        this.jugadores.get(0).setPodervelocidad(40);
    }
    if (e.getActionCommand().equals("Nivel 1")) {
        this.jugadores.get(0).setVida(this.jugadores.get(0).getVida()+100);

    }
    
    
    }
    
        
    
         
    
    
    
        //se supone que es el que hace que se mueva (al hacer printable y no printable) los 50 es lo que evita que se mueva a velocidad luz
    	private class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			while (printable) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
                }
    
    
            	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) { 
			//homeTank.keyReleased(e);// Jugador 1 del otro codigo
                        tank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			//homeTank.keyPressed(e); Jugador 1 del otro codigo
                        tank.keyPressed(e);
		}
                }

    
    
}
