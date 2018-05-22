
package tarea.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import tarea.control.Control;
import tarea.modelo.Jugadores;
import xml.UtilidadesXML;


//Andres Barrantes
//Caleb Perez
//Alex Baltodano



public class VentanaPrincipal extends JFrame implements KeyListener, Observer{
    
    public VentanaPrincipal(String p, Control control)
    {
        this.controlVP=control;
        juego=new Juego(control);
        nombre=p;
        ajustarConfiguracionInicial();
        ajustarComponentes(this.getContentPane());
        eventos();
    }
    //el documento xml tiene que estar creado
    private void leerPersonas(String nombreArchivo){
        File file = new File(nombreArchivo);    
        Document documentoXML = UtilidadesXML.crearXMLDocumento(file);
        Node raiz = documentoXML.getDocumentElement();
        controlVP.leerXML(raiz);
    }
    public void iniciar(){
        controlVP.registrar(this);
        controlVP.cargar();
       leerPersonas("Registro.xml");
        this.setVisible(true);
    }
    
    private void ajustarConfiguracionInicial(){
        this.setTitle("Juego");
        this.setSize(900, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    private void ajustarComponentes(Container c){
        Insets margen=new Insets(0,0,0,0);
        panelOpciones = new JPanel();
        panelOpciones.setBackground(Color.darkGray);
        btnIniciar = new JButton("  Iniciar  ");
        btnIniciar.setMargin(margen);
        btnPausa = new JButton("Detener");
        btnPausa.setMargin(margen);
        btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setMargin(margen);
        nombreLabel=new JLabel(nombre);
        nombreLabel.setForeground(Color.white);
        vidas=new JLabel("Vidas 3");
        vidas.setForeground(Color.white);
        panelOpciones.add(nombreLabel);
        panelOpciones.add(new JLabel("        "));
        panelOpciones.add(btnIniciar);
        panelOpciones.add(btnPausa);
        panelOpciones.add(btnReiniciar);
        panelOpciones.add(new JLabel("        "));
        
        panelOpciones.add(vidas);
        
         
        
        
        c.add(juego, BorderLayout.CENTER); 
        c.add(panelOpciones, BorderLayout.PAGE_END);
        
        btnIniciar.addKeyListener(this);
    }
    public void eventos(){
    btnIniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            juego.iniciar();
            }
            });
    btnReiniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            juego.reiniciar();
            }
            });
    btnPausa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            juego.detener();
            }
            });
    
    }
    
   

    
    @Override
    public void keyPressed(KeyEvent e) {
       if (KeyEvent.VK_LEFT == e.getKeyCode())
       {
           juego.movimientoJugador(1);
           repaint();
       }
       
       if (KeyEvent.VK_RIGHT == e.getKeyCode())
       {
           juego.movimientoJugador(2);
           repaint();
       }
    }
     @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
   //Atributos 
   private JPanel panelOpciones;
   private JButton btnIniciar,btnPausa,btnReiniciar;
   private Juego juego;
   private JLabel vidas,nombreLabel;
   private String nombre;
   Control controlVP=new Control();
   private int auxVidas=3;
   
    @Override
    public void update(Observable modelo, Object evento) {
    if(evento instanceof Integer)
    {
    int vida=(Integer)evento;
    if(vida>auxVidas){
    JOptionPane.showMessageDialog(null, "HA GANADO UNA VIDA");
    vidas.setText(String.valueOf("Vidas "+vida));
    } else {
    JOptionPane.showMessageDialog(null, "HA PERDIDO UNA VIDA");
    vidas.setText(String.valueOf("Vidas "+vida));
    }
    if(auxVidas==0){
    JOptionPane.showMessageDialog(null, "HA PERDIDO EL JUEGO");
    juego.reiniciar();
    }
    
    auxVidas=vida;
    }
    
    if (evento instanceof Boolean){
    boolean ganador=(Boolean)evento;
    if(ganador==true){
    JOptionPane.showMessageDialog(null, "FELICIDADES "+nombre+" HA GANADO EL JUEGO");
    vidas.setText(String.valueOf("Vidas 3"));
    Jugadores xml=new Jugadores(nombre, String.valueOf(auxVidas));
    controlVP.agregar(xml);
    juego.reiniciar();
    }
    
    }
    }
   
}
