/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byronortizrojastarea22018;

import GUI.VentanaPrincipal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author byron
 */
public class ByronOrtizRojasTarea22018 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ByronOrtizRojasTarea22018.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
