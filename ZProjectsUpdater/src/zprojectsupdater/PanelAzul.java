/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zprojectsupdater;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author matuuar
 */
public class PanelAzul extends javax.swing.JPanel {
     

        @Override
        public void paintComponent(Graphics g) {
            Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().
                    getResource("/zprojectsupdater/resources/bg.png"));
            g.drawImage(imagenFondo.getImage(), 0, 0,
                    tamanio.width, tamanio.height, null);
            setOpaque(false);

            super.paintComponent(g);
        }
}
