
package main;


import controllers.Controllers;
import models.Models;
import views.Views;
/**
 * 
 * @author HP
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Colocamos los componentes 
        Models models = new Models();
        Views viewblocnotas = new Views();
        Controllers controllerblocnotas = new Controllers(models, viewblocnotas); 
        
    }
    
}
