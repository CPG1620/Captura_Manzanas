
package tarea.Vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tarea.control.Control;

public class Juego extends JPanel implements Runnable,Observer{
    
    public Juego(Control control){
    controlJP=new Control();
    this.controlJP=control;
    randomPos= new ArrayList<>();
    llenaJuego= new ArrayList<>();
    
    }
    
    public void iniciar(){
     juego = true;
     llenaArrayList();
     if(hiloObjetos == null)
     hiloObjetos = new Thread(this);
     if(hiloObjetos != null)
     hiloObjetos.start();
    }
    
    //llena un arraylist con las pos que puede tomar el objeto
    public void llenaArrayList(){
    for(int i=0;i<16;i++){
    if(banderaAL==1){
    numero+=50;
    randomPos.add(numero);
    }
    if(banderaAL==0){
    randomPos.add(numero);
    banderaAL=1;
    }
    }
    }
    //metodo que genera numeros random sin repetir
    public int devuelveX(){
    Random rand=new Random();
    if(llenaJuego.size() < 8){ 
    int numeroR=(int)(rand.nextDouble() * 16 + 0); 
    if(llenaJuego.isEmpty()){ 
    llenaJuego.add(numeroR); 
    return numeroR;
    }
    else{ 
    if(llenaJuego.contains(numeroR)){ 
    return devuelveX(); 
    }
    else{ 
    llenaJuego.add(numeroR); 
    return numeroR;
    } 
    }
    
    } else {
    return -1;
    }
}
    
    
   //RUN 
    
    
    @Override
    public void run() {
    
        while (juego)
        {
            try{
             if (posy7==410){
                if(posJugador==posx7){
                controlJP.vidas(2);
                }
            }
             if (posy6==410){
                if(posJugador==posx6){
                controlJP.vidas(2);
                }
            }
             if (posy5==410){
                if(posJugador==posx5){
                controlJP.vidas(2);
                }
            }
             if (posy4==410){
                if(posJugador==posx4){
                controlJP.vidas(2);
                }
            }
             if (posy3==410){
                if(posJugador==posx3){
                controlJP.vidas(2);
                }
            }
             if (posy2==410){
                if(posJugador==posx2){
                controlJP.vidas(2);
                }
            }
             if (posy1==410){
                if(posJugador==posx1){
                controlJP.vidas(1);
                }
            }
            
             
              //tiempo
            
              if (segundos != 59)
            {
                segundos = aumentaSegundos();
            }else{
                segundos = 0;
                ReseteaSegundos();
                if (minutos != 59)
                {
                    minutos = aumentaMinutos();
                }else{
                    minutos = 0;
                    ReseteaMinutos();
                }
            }
            
             
             
             
             
             
            if(posy1 != 550){    
            posy1 += 10;}
            else{
            posx1=randomPos.get(devuelveX());
             posy1 = -100;
            }
            if(posy2 != 550){
            posy2 += 10;}
            else{
            posx2=randomPos.get(devuelveX());
             posy2 = -150;
            }
            
            if(posy3 != 550){
            posy3 += 10;}
            else{
            posx3=randomPos.get(devuelveX());
            posy3 = -190;
            }
            
            if(posy4 != 550){
            posy4 += 10;}
            else{
            posx4=randomPos.get(devuelveX());
            posy4 = -230;
            }
            
            if(posy5 != 550){
            posy5 += 10;}
            else{
            posx5=randomPos.get(devuelveX());
            posy5 = -260;
            }
            
            if(posy6 != 550){
            posy6 += 10;}
            else{
            posx6=randomPos.get(devuelveX());
            posy6 = -300;
            
            }
            
            if(posy7 != 550){
            posy7 += 10;}
            else{
            posx7=randomPos.get(devuelveX());
            posy7 = -330;
            
            }
            
            
            
             
         
            
            llenaJuego.clear();
            
            if(bandera>=2000){
            juego=false;
            reiniciar();
            controlJP.ganador(1);
            }
            if(bandera>=1000 &&bandera<2000){
            Thread.sleep(18); 
            bandera++;
            }
            if(bandera<1000){
            Thread.sleep(20);
            bandera++;
            }
            
       
            
           
            repaint();
            }catch(InterruptedException ex)
            {
                System.out.print("Ocurrio un error.... :(");
            }
        }
    }
    
   
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        dibujarEscenario(g);
    }
    
    private void dibujarEscenario(Graphics g){
        
        Graphics2D g2d =(Graphics2D)g;
        
        //Imagen Fondo
        Image imagenFondo = new ImageIcon(getClass().getResource("../Imagenes/fondo.jpg")).getImage();
        g2d.drawImage(imagenFondo, 0, 0, 900, 600, this);
       
       //Jugador
       if(banderaPaso==0){
       Image jugador = new ImageIcon(getClass().getResource("../Imagenes/jugadorFrente.png")).getImage();
       g.drawImage(jugador, posJugador, 410, this); 
       }
       if(banderaPaso==1){
       Image jugadorPasoI1 = new ImageIcon(getClass().getResource("../Imagenes/paso1I.png")).getImage();
       g.drawImage(jugadorPasoI1, posJugador, 410, this); 
       }
       if (banderaPaso==2){
       Image jugadorPasoI2 = new ImageIcon(getClass().getResource("../Imagenes/paso2I.png")).getImage();
       g.drawImage(jugadorPasoI2, posJugador, 410,this); 
       }
       if (banderaPaso==3){
       Image jugadorPasoD1 = new ImageIcon(getClass().getResource("../Imagenes/paso1D.png")).getImage();
       g.drawImage(jugadorPasoD1, posJugador, 410,this); 
       }
       if(banderaPaso==4){
       Image jugadorPasoD2 = new ImageIcon(getClass().getResource("../Imagenes/paso2D.png")).getImage();
       g.drawImage(jugadorPasoD2, posJugador, 410,this); 
       }
       
       //Objetos
       obj1 = new ImageIcon(getClass().getResource("../Imagenes/manzana.png"));
       g.drawImage(obj1.getImage(), posx1, posy1, 36, 36, this);
       
       obj2 = new ImageIcon(getClass().getResource("../Imagenes/uvas.png"));
       g.drawImage(obj2.getImage(), posx2, posy2, 36, 36, this);
       
       obj3 = new ImageIcon(getClass().getResource("../Imagenes/cereza.png"));
       g.drawImage(obj3.getImage(), posx3, posy3, 36, 36, this);
       
       obj4 = new ImageIcon(getClass().getResource("../Imagenes/sandia.png"));
       g.drawImage(obj4.getImage(), posx4, posy4, 36, 36, this);
       
       obj5 = new ImageIcon(getClass().getResource("../Imagenes/pera.png"));
       g.drawImage(obj5.getImage(), posx5, posy5, 36, 36, this);
       
       obj6 = new ImageIcon(getClass().getResource("../Imagenes/coco.png"));
       g.drawImage(obj6.getImage(), posx6, posy6, 36, 36, this);
       
       obj7 = new ImageIcon(getClass().getResource("../Imagenes/bomba.png"));
       g.drawImage(obj7.getImage(), posx7, posy7, 36, 36, this);
       
       //Ubicacion de los minutos y segundos en pantalla
       g.drawString(StrMinutos, 840, 40);
       g.drawString(new String(":"), 847, 40);
       g.drawString(StrSegundos, 850, 40);
       
       
       
       
    }
    //verifica los pasos del jugador
    public void movimientoJugador(int pos){
        if(posJugador!=850&&posJugador!=0){
           if (pos == 1)
           {
           
            if(verificaPasoI==0){
            verificaPasoI=2;
            }
            if(verificaPasoI==1){
            if(posJugador>850){
            posJugador=850;
            } else {
            posJugador -= 50;
            }
            banderaPaso=2;
            verificaPasoI--;
            }
            if(verificaPasoI==2){
            if(posJugador>850){
            posJugador=850;
            } else {
            posJugador -= 50;
            }
            banderaPaso=1;
            verificaPasoI--;
            }
           }
           if (pos == 2)
           {
            if(verificaPasoD==0){
            verificaPasoD=2;
            }
            if(verificaPasoD==1){
            posJugador += 50;
            banderaPaso=4;
            verificaPasoD--;
            }
            if(verificaPasoD==2){
            posJugador += 50;
            banderaPaso=3;
            verificaPasoD--;
            }
           }
        }
        
        if(posJugador==850){
        posJugador=800;
        }
        if(posJugador==0){
        posJugador=50;
        }
        
    }
    //reiniciar el juego   
    public void reiniciar(){
    posJugador = 400;
    posy1 = -100;
    posy2 = -150;
    posy3 = -190;
    posy4 = -230;
    posy5 = -260;
    posy6 = -300;
    posy7 = -330;
    
    posx1 = 30;
    posx2 = 100;
    posx3 = 240;
    posx4 = 360;
    posx5 = 490;
    posx6 = 545;
    posx7 = 630;
    banderaPaso=0;
    bandera=0;
    controlJP.cargar();
    ReseteaMinutos();
    ReseteaSegundos();
    hiloObjetos = null;
    juego = false;
    
    repaint();
    }
    
    
    //detiene el juego
    public void detener() {
        juego = false;
        hiloObjetos = null;
    }
    
      //Metodo que aumenta los segundos
    public synchronized int aumentaSegundos()
    {
        segundos++;
        StrSegundos = String.valueOf(segundos);
        return segundos;
    }
       
    //Metodo que aumenta los minutos
    public synchronized int aumentaMinutos()
    {
        minutos++;
        StrMinutos = String.valueOf(minutos);
        return minutos;
    }
    
    //Resetea los valores de los segundos
    public void ReseteaSegundos()
    {
        StrSegundos = String.valueOf(0);
        segundos = 0;
    }
    
    //Resetea los valores de los minutos
    public void ReseteaMinutos()
    {
        StrMinutos = String.valueOf(0);
        minutos = 0;
    }
    
    public synchronized int ObtenerSegundos()
    {
        return segundos;
    }
    
    public synchronized int ObtenerMinutos()
    {
        return minutos;
    }
    
    public synchronized void Seguir()
    {
        continuar = true;
    }
    
    public synchronized void Detener()
    {
        continuar = false;
    }
    
    public synchronized boolean IsSeguir()
    {
        return continuar;
    }
       
    
 
    //Atributos
    private Thread hiloObjetos;
    private ImageIcon obj1;
    private ImageIcon obj2;
    private ImageIcon obj3;
    private ImageIcon obj4;
    private ImageIcon obj5;
    private ImageIcon obj6;
    private ImageIcon obj7;
   
    private int posJugador = 400;
    private int posy1 = -100;
    private int posy2 = -150;
    private int posy3 = -190;
    private int posy4 = -230;
    private int posy5 = -260;
    private int posy6 = -300;
    private int posy7 = -330;
    
    private int posx1 = 0;
    private int posx2 = 100;
    private int posx3 = 250;
    private int posx4 = 350;
    private int posx5 = 650;
    private int posx6 = 550;
    private int posx7 = 400;
    
    
   
    private int banderaPaso=0;
    private int verificaPasoI=0;
    private int verificaPasoD=0;
    private boolean juego=false;
    Control controlJP;
    
    ArrayList<Integer> llenaJuego;
    ArrayList<Integer> randomPos;
    private int numero=50;
    private int banderaAL=0;
    private int bandera=0;
    
    
    private String StrSegundos = new String(String.valueOf(0));
    private String StrMinutos = new String(String.valueOf(0));
    
    private Random rand;

    private int segundos;
    private int minutos;
    private boolean continuar = true;
    
    
    @Override
    public void update(Observable o, Object arg) {
        //SE ACTUALIZA EN EL UPDATE DE VENTANA PRINCIPAL
    }
    
}
