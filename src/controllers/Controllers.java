package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Models;
import views.Views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;                             
import javax.swing.filechooser.FileNameExtensionFilter;      
/**
 * 
 * @author HP
 */ 
public class Controllers {

    Models modelos;
    Views vistas;

    
    JFileChooser selector_archivos = new JFileChooser(); 
    FileNameExtensionFilter filtro_extensiones = new FileNameExtensionFilter("Archivos de Texto", "txt"); //Cremos esta condicion para soo extensiones 

    ActionListener actionlistener = new ActionListener() {
        @Override
       
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vistas.ji_archivo) { 
                ji_archivo_action_performed();
            } else if (e.getSource() == vistas.ji_guardar) { 
                ji_guardar_action_performed();
            } else if (e.getSource() == vistas.ji_cifrar) { 
                ji_cifrar_action_performed();
            } else if (e.getSource() == vistas.ji_decifrar) { 
                ji_descifrar_action_performed();
            } 
        }
    };

    /**
     * Componentes
     *
     * @param modelos
     * @param vistas
     */
    public Controllers(Models modelos, Views vista) {
        this.modelos = modelos;
        this.vistas = vista;

        this.vistas.ji_archivo.addActionListener(actionlistener);
        this.vistas.ji_guardar.addActionListener(actionlistener);
        this.vistas.ji_cifrar.addActionListener(actionlistener);
        this.vistas.ji_decifrar.addActionListener(actionlistener);
        
        initComponents();
    }

    
    public void ji_archivo_action_performed() {
        selector_archivos.setFileFilter(filtro_extensiones);
        selector_archivos.showOpenDialog(vistas.jt_archivo);
        File archivo = selector_archivos.getSelectedFile(); 
        String ruta = archivo.getPath(); 

        modelos.setPath(ruta); 

        this.readFile(modelos.getPath()); 
    }

    /**
     * Metodo para Guardar Archivos
     */
    public void ji_guardar_action_performed() {
        selector_archivos.setFileFilter(filtro_extensiones); 
        selector_archivos.showSaveDialog(vistas.jt_archivo); 
        File archivo = selector_archivos.getSelectedFile(); 
        String ruta = archivo.getPath();

        modelos.setPath(ruta);

        modelos.setMessage(vistas.jt_archivo.getText());
        this.writeFile(modelos.getPath(), modelos.getMessage()); 
    }

    
    public void ji_cifrar_action_performed() {
        String texto_area = vistas.jt_archivo.getText();
        String texto_cifrado = "";
         
        for (int i = 0; i < texto_area.length(); i++) { 
            char caracter = texto_area.charAt(i);
            int ascii_char = (int) caracter;
            ascii_char = ascii_char + 7;            
            caracter = (char) ascii_char;
            texto_cifrado += caracter;
        }
        
        vistas.jt_archivo.setText(texto_cifrado); 
    }

    
    public void ji_descifrar_action_performed() {
        String texto_area = vistas.jt_archivo.getText();
        String texto_descifrado = "";
       
        for (int i = 0; i < texto_area.length(); i++) { 
            char caracter = texto_area.charAt(i);
            int ascii_char = (int) caracter;
            ascii_char = ascii_char - 7;                            
            caracter = (char) ascii_char;
            texto_descifrado += caracter; 
        }
        
        vistas.jt_archivo.setText(texto_descifrado);
    }

    
    
    

    /**
     * Método escribir y leer los archivos 
     *
     * @param path: Es la ruta 
     * @return
     */
    public String readFile(String path) {
        try {
            String row; 
            String acumulador_texto = ""; 
            try (FileReader archivo = new FileReader(path)) { 
                BufferedReader bufferedreader = new BufferedReader(archivo); 
                while ((row = bufferedreader.readLine()) != null) {
                    acumulador_texto += row + "\n"; 
                }
                vistas.jt_archivo.setText(acumulador_texto);
            }
        } catch (FileNotFoundException err) { 
            System.err.println(" no encontrado: " + err.getMessage());
        } catch (IOException err) { 
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    }
    
    /**
     * Metodo de Guardar el Nuevo Contenido 
     * @param path: Ruta para del archivo 
     * @param message: Mensaje que contiene el texto 
     */
    public void writeFile(String path, String message) {
        try {
            File archivo = new File(path);                                 
            FileWriter filewriter = new FileWriter(archivo, false);        
            try (PrintWriter printwriter = new PrintWriter(filewriter)) {  
                printwriter.println(message);
                printwriter.close();
            }
        } catch (FileNotFoundException err) { 
            System.err.println(" no encontrado: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
    }

   
    public void initComponents() {
        vistas.setVisible(true);
    }

}