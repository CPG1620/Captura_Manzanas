
package tarea.modelo;

import java.util.Observable;
import org.w3c.dom.Node;

    public class Modelo extends Observable {

    public Modelo(){
    juego=new Jugador(); 
    }
    
    public void agregar(Jugadores jugador){
    juego.Agregar(jugador);
    juego.exportarJugadoresToXML();
    actualizar("se ha cargado");
    }
    
    public void leerXML(Node nodo)
    {
    juego.leerXML(nodo);
    actualizar("se ha cargado");
    }
    
    public void cargar(){
    juego.cargar();
    actualizar("se ha cargado");
    }
    
    public void vidas(int p){
    juego.vidas(p);
    actualizar(juego.vidas(p));
    }
    public void ganador(int p){
    juego.ganador(p);
    actualizar(juego.ganador(p));
    }
    public void actualizar(Object evento){
    setChanged();
    notifyObservers(evento);
    }


//atributos
Jugador juego;
}

