/*
 * ZProjectsUpdaterView.java
 */
package zprojectsupdater;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import org.jdesktop.application.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import zprojectsupdater.download.DownloadTask;
import zprojectsupdater.models.Actualizacion;
import zprojectsupdater.models.Archivo;

/**
 * The application's main frame.
 */
public class ZProjectsUpdaterView extends FrameView implements PropertyChangeListener {

    public static String urlBase= "http://matiasvarela.com.ar/";
    public ZProjectsUpdaterView(SingleFrameApplication app) {
        super(app);
        initComponents();
        obtenerInfo().execute();
    }

    public void setFileInfo(String name, int size) {
        txtNombre.setText(name);
        lblTamanio.setText(String.valueOf(size) + " bytes.");
    }

    public void finishUpdate(String msg) {
        lblStatus.setText(msg);
        btnCancelar.setEnabled(false);
        btnCerrar.setEnabled(true);
    }

    /**
     * Update the progress bar's state whenever the progress of download changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            jProgressBar.setValue(progress);
            jProgressBar.setStringPainted(true);
        }
    }
    Actualizacion act;

    final void startDownload() {

        try {
            jProgressBar.setValue(0);
            task = new DownloadTask(this, act.getArchivos());
            task.addPropertyChangeListener(this);
            task.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this.getFrame(),
                    "Error executing upload task: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setStatus(String string) {
        lblStatus.setText(string);
    }

    @Action
    public final Task obtenerInfo() {
        return new ObtenerInfoTask(getApplication());
    }

    private class ObtenerInfoTask extends org.jdesktop.application.Task<Object, Void> {
        String msg = "";
        ObtenerInfoTask(org.jdesktop.application.Application app) {
            super(app);
        }

        @Override
        protected Object doInBackground() {
            JSONObject json;
            try {
                json = JsonReader.readJsonFromUrl("http://matiasvarela.com.ar/api/last");
                act = new Actualizacion(json);
                msg = ("Versión: " + act.getVersion() + " (" + act.getObservaciones() + ")");
                JSONArray array = JsonReader.readArrayJsonFromUrl("http://matiasvarela.com.ar/api/files/"
                        + act.getId());
                for (int i = 0; i < array.length(); i++) {
                    act.getArchivos().add(new Archivo(array.getJSONObject(i)));
                }
            } catch (IOException ex) {
                Logger.getLogger(ZProjectsUpdaterView.class.getName()).log(Level.SEVERE, null, ex);
                msg = ("Error al descargar la información...");
                return false;
            } catch (JSONException ex) {
                Logger.getLogger(ZProjectsUpdaterView.class.getName()).log(Level.SEVERE, null, ex);
                msg = ("Error al descargar la información...");
                return false;
            }
            return true;
        }

        @Override
        protected void succeeded(Object result) {
            lblversion.setText(msg);
            if((Boolean) result) {
                lblText.setText("Actualizando a la versión:");
                startDownload();
            } else {
                finishUpdate(msg);
            }     
        }
    }

    /*try {
    
    
    } catch(JSONException e){
    JOptionPane.showMessageDialog(null, "Error al descargar la información.", 
    "Error", JOptionPane.OK_OPTION);
    e.printStackTrace();
    } catch(IOException e) {
    JOptionPane.showMessageDialog(null, "Error al descargar la información.", 
    "Error", JOptionPane.OK_OPTION);
    e.printStackTrace();
    }*/
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new PanelAzul();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        lblTamanio = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        lblversion = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        lblText = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+3));
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(zprojectsupdater.ZProjectsUpdaterApp.class).getContext().getResourceMap(ZProjectsUpdaterView.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        txtNombre.setText(resourceMap.getString("txtNombre.text")); // NOI18N
        txtNombre.setName("txtNombre"); // NOI18N

        lblTamanio.setText(resourceMap.getString("lblTamanio.text")); // NOI18N
        lblTamanio.setName("lblTamanio"); // NOI18N

        jProgressBar.setName("jProgressBar"); // NOI18N

        lblversion.setText(resourceMap.getString("lblversion.text")); // NOI18N
        lblversion.setName("lblversion"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(zprojectsupdater.ZProjectsUpdaterApp.class).getContext().getActionMap(ZProjectsUpdaterView.class, this);
        btnCancelar.setAction(actionMap.get("obtenerInfo")); // NOI18N
        btnCancelar.setText(resourceMap.getString("btnCancelar.text")); // NOI18N
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCerrar.setAction(actionMap.get("quit")); // NOI18N
        btnCerrar.setText(resourceMap.getString("btnCerrar.text")); // NOI18N
        btnCerrar.setName("btnCerrar"); // NOI18N

        lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getSize()+1f));
        lblStatus.setForeground(resourceMap.getColor("lblStatus.foreground")); // NOI18N
        lblStatus.setText(resourceMap.getString("lblStatus.text")); // NOI18N
        lblStatus.setName("lblStatus"); // NOI18N

        lblText.setText(resourceMap.getString("lblText.text")); // NOI18N
        lblText.setName("lblText"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblversion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTamanio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrar))
                    .addComponent(lblStatus))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblText)
                    .addComponent(lblversion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTamanio)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        if (task != null) {
            task.cancel(true);
        }
        while (!task.isDone()) {
        }
        btnCerrar.setEnabled(true);
        btnCancelar.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTamanio;
    private javax.swing.JLabel lblText;
    private javax.swing.JLabel lblversion;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel txtNombre;
    // End of variables declaration//GEN-END:variables
    DownloadTask task;
}
