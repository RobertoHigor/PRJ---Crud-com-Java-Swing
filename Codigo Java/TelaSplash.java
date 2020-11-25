import java.awt.*;
import javax.swing.*;

public class TelaSplash extends JWindow {
    
    private int duration;
    
    public TelaSplash(int d) {
        duration = d;
    }
    
    public void mostrar(){
    	setSize(300, 280);
    	setLocationRelativeTo(null);
    	
    	JLabel label = new JLabel(new ImageIcon("faeterj.jpg"));
    	JLabel autor = new JLabel("Autores: Roberto Higor e Gabriel Oliveira");
    	
    	add(label, BorderLayout.CENTER);
    	add(autor,  BorderLayout.SOUTH);
    	setVisible(true);
    	
    	//Esconder depois do tempo
    	try { 
    		Thread.sleep(duration);
    	}catch (Exception e) {}   
    	
    	//Esconder splash
    	setVisible(false);        
      }
   }
