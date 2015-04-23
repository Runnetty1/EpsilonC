package epsilonc;

/*
 ***************************************************************************
 * AUTHOR: AUDUN MOSENG & MATS HARWISS LAST EDITED: 15.12.2014 
 * LAST EDITED BY: MATS HARWISS
 *
 * PROGRAM PURPOSE: TO ENCRYPT SERVER INFORMATION/PASSWORDS FOR USE IN DOMAIN
 * NETWORKS.
 ***************************************************************************
 */

import java.io.File;

public class MainProgram {

    public static Window window;
    public static LoginWindow loginWindow;
    public static XMLReader currentXMLDoc;
    public static String currXMLFilePath = "domain.xml";
    public static String clientVersion = "0";
    protected static String salt = "quarterOfAMillion19922344566";

    public static void main(String[] args) {
        
        FileHandler fh = new FileHandler();
        File clientVersionFile = new File(fh.getDefaultFilePath() + fh.sep + "Patch" + fh.sep + "clientVersion.txt");
        fh.generateFolder(fh.getDefaultFilePath()+fh.sep+"NodeInfo"+fh.sep);
        fh.generateFolder(fh.getDefaultFilePath()+fh.sep+"Patch"+fh.sep);
        
        //check for update
        UpdateCheck uCheck = new UpdateCheck();
        if(uCheck.serverHasNewerVersion()){
            uCheck.getUpdate();
        }

        clientVersion = fh.readFile(clientVersionFile);
        System.out.println("ClientVersion: " + clientVersion);
        currentXMLDoc = new XMLReader(currXMLFilePath);
        startLogin();
    }

    public static void startLogin() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }
}
