/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zprojectsupdater.models;

import org.json.JSONObject;

/**
 *
 * @author m4tuu
 */
public class Archivo {
    private int id;
    private String url, ruta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Archivo(int id, String url, String ruta) {
        this.id = id;
        this.url = url;
        this.ruta = ruta;
    }

    public Archivo() {
    }
    public Archivo(JSONObject json) {
        try {
            this.id = json.getInt("id");
            this.ruta = json.getString("ruta");
            this.url = json.getString("url");
        } catch (Exception e) {
        }
    }
}
