/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zprojectsupdater.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * 
 * @author m4tuu
 */
public class DownloadTask extends SwingWorker<Void, Void> {

    private static final int BUFFER_SIZE = 4096;	
	private ArrayList<String> downloadURLs;
	private String saveDirectory;
	private zprojectsupdater.ZProjectsUpdaterView gui;
	
	public DownloadTask(zprojectsupdater.ZProjectsUpdaterView gui, ArrayList<String> downloadURL, String saveDirectory) {
		this.gui = gui;
		this.downloadURLs = downloadURL;
		this.saveDirectory = saveDirectory;
	}
	
	/**
	 * Executed in background thread
	 */	
	@Override
	protected Void doInBackground() throws Exception {
            int i = 0;
            for(String url : this.downloadURLs) {
                try {
			HTTPDownloadUtil util = new HTTPDownloadUtil();
			util.downloadFile(url);
			
			// set file information on the GUI
			gui.setFileInfo(util.getFileName(), util.getContentLength());
			
			String saveFilePath = saveDirectory + File.separator + util.getFileName();

			InputStream inputStream = util.getInputStream();
			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			long totalBytesRead = 0;
			int percentCompleted = 0;
			long fileSize = util.getContentLength();
                        if (fileSize>0){
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                    outputStream.write(buffer, 0, bytesRead);
                                    totalBytesRead += bytesRead;
                                    percentCompleted = (int) (totalBytesRead * 100 / fileSize);

                                    setProgress(percentCompleted);			
                            }
                        }
			outputStream.close();

			util.disconnect();
                        gui.setStatus(util.getFileName() + " descargado correctamente.");
                        i++;
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(gui.getFrame(), "Error downloading file: " + ex.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);			
			ex.printStackTrace();
			setProgress(0);
			cancel(true);			
		}
            }
            if (i == this.downloadURLs.size()) {
                gui.finishUpdate("Todos los archivos descargados.");
            }
	    return null;
	}

	/**
	 * Executed in Swing's event dispatching thread
	 */
	@Override
	protected void done() {
            if (!isCancelled()) {
                    
            }
	}	
    
}
