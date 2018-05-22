
package tarea.control;


import java.util.Observer;
import org.w3c.dom.Node;
import tarea.modelo.Modelo;
import tarea.modelo.Jugadores;

public class Control {
        
    public Control(Modelo nuevosDatos){
        datos = nuevosDatos;
    }
    
    public Control(){
        this(new Modelo());
    }
    
    public void registrar(Observer nuevoObservador){
        datos.addObserver(nuevoObservador);
    }
    
    
    public void agregar(Jugadores jugador){
        datos.agregar(jugador);
    }
    public void leerXML(Node nodo){
        datos.leerXML(nodo);
    }
    
    public void cargar(){
         datos.cargar();
    }
    
    public void vidas(int p){
        datos.vidas(p);
    }
    public void ganador(int p){
        datos.ganador(p);
    }
    
    //aributos
    Modelo datos;   
}

