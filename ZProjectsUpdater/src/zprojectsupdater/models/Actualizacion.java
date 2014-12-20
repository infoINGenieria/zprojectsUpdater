/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zprojectsupdater.models;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author m4tuu
 */
public class Actualizacion {
    private int id;
    private String version, observaciones;
    private ArrayList<Archivo> archivos;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Actualizacion() {
        this.archivos = new ArrayList<Archivo>();
    }

    public Actualizacion(int id, String version, String observaciones) {
        this.id = id;
        this.version = version;
        this.observaciones = observaciones;
        this.archivos = new ArrayList<Archivo>();
    }

    public ArrayList<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(ArrayList<Archivo> archivos) {
        this.archivos = archivos;
    }
    
    public Actualizacion(JSONObject j) throws JSONException {
        this.id = j.getInt("id");
        this.version = j.getString("version");
        this.observaciones = j.getString("observacion");
        this.archivos = new ArrayList<Archivo>();
    }
}
