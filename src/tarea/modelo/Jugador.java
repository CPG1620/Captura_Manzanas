
package tarea.modelo;


import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.UtilidadesXML;

public class Jugador {

    public Jugador() {
    }

    //carga las variables del juego
    public void cargar(){
    vidas=3;
    ganador=false;
    }
    
    //devuelve lo que contiene el arraylist
    public Jugadores recuperar(int p){
        return jugadores.get(p);
    }
    //agrega al arraylist
    public void Agregar(Jugadores jugador){
        jugadores.add(jugador);
    }
    
     
    //Guarda los jugadores registrados en el documento XML
    public void exportarJugadoresToXML(){
        try{
        Document d = UtilidadesXML.crearDocumento();
            Node r = d.createElement("Partida");
            
        for (int i = 0; i < jugadores.size(); i++){
            r.appendChild(recuperar(i).toXML(d));
        }
        d.appendChild(r);
        UtilidadesXML.guardarArchivoXML(d, "Registro.xml");
        }catch (ParserConfigurationException ex) {}
    }
    
    //lee los datos registrados en el documento, tiene que estar creado
    public void leerXML(Node nodo)
    {
       NodeList arbolEtiquetas = nodo.getChildNodes();
       int numJugadores = arbolEtiquetas.getLength();
       for (int i = 0; i < numJugadores; i++)
       {
           Node etiqueta = arbolEtiquetas.item(i);
           Jugadores lectura = new Jugadores();
           lectura.leerXML(etiqueta);
           jugadores.add(lectura);
       }
    }
    //devuelve las vidas del jugador
    public int vidas(int p){
    if(bandera==2){
    bandera=0;
    }
    if(bandera==1){
    if(p==1){
    vidas++;
    bandera=2;
    }
    if(p==2){
    vidas--;
    bandera=2;
    }
    }
    if(bandera==0){
    bandera=1;
    }
    
    return vidas;
    }
    //devuelve si gano el jugador
    public boolean ganador(int p){
    if(p==1){
    ganador=true;
    } 
    return ganador;
    }
    //atributos 
    private int vidas;
    private boolean ganador;
    private int bandera=0;
    private ArrayList<Jugadores> jugadores=new ArrayList<>();
   
    }
