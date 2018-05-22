package tarea.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.UtilidadesXML;

public class Jugadores {

    public Jugadores(){
    this.nombre="";
    this.vidas="";
    }
    public Jugadores(String nombre,String vidas){
    this.nombre=nombre;
    this.vidas=vidas;
    }
    
    public Node toXML(Document doc) {
    Node n = doc.createElement(getNodeName());
    n.appendChild(UtilidadesXML.crearNodo(doc, "NOMBRE", nombre));
    n.appendChild(UtilidadesXML.crearNodo(doc, "VIDAS", vidas));
    return n;
    }
    
    
    public void leerXML(Node nodo)
    {
        NodeList arbolEtiquetas = nodo.getChildNodes();
        nombre = ((Element)arbolEtiquetas.item(0)).getTextContent();
        vidas = ((Element)arbolEtiquetas.item(1)).getTextContent();
    }

    public String getNodeName() {
        return DESCRIPCION_XML;
    }

    //Atributos
    public static final String DESCRIPCION_XML = "jugador";
    private String nombre;
    private String vidas;
}

