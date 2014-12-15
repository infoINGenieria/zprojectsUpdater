/*
 * ZProjectsUpdaterApp.java
 */

package zprojectsupdater;

import java.util.ArrayList;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ZProjectsUpdaterApp extends SingleFrameApplication {
    public static String version;
    public static ArrayList<String> filelist = new ArrayList<String>();
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new ZProjectsUpdaterView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ZProjectsUpdaterApp
     */
    public static ZProjectsUpdaterApp getApplication() {
        return Application.getInstance(ZProjectsUpdaterApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            version = "Desconocida.";
            filelist.add("http://matiasvarela.com.ar/static/shared/zille/ZilleProjects.jar");
        }
        if(args.length >= 1) {
            version = args[0];
            filelist.add("http://matiasvarela.com.ar/static/shared/zille/ZilleProjects.jar");
        }
        if (args.length > 1) {
            filelist.clear();
            for(int i = 1; i < args.length;i++)
                filelist.add(args[i]);
        }
        launch(ZProjectsUpdaterApp.class, args);
    }
}
