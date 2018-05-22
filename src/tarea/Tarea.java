
package tarea;

import javax.swing.JOptionPane;
import tarea.Vista.VentanaPrincipal;
import tarea.control.Control;

/**
 *Manzanas gana vidas y el resto pierde vidas
 * @author caleb perez
 * @author andres barrantes
 * @author alex baltodano
 */
public class Tarea {

    public static void main(String[] args) {
        Control control=new Control();
        String nombre=JOptionPane.showInputDialog("Digite su nombre: "); 
        VentanaPrincipal ventana = new VentanaPrincipal(nombre,control);
        ventana.iniciar();
    }
    
}
