/*
 ***************************************************************************
 * AUTHOR: AUDUN MOSENG & MATS HARWISS LAST EDITED: 15.12.2014 
 * LAST EDITED BY: MATS HARWISS
 *
 * CLASS PURPOSE: READ AND GET XML NODES FROM EXTERNAL FILE
 ***************************************************************************
 */
package epsilonc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {

    public String sep = System.getProperty("file.separator");

    ;
    
     public String getDefaultFilePath() {
        String filePath = null;
        filePath = System.getProperty("user.home") + sep + "Documents" + sep + "EpsilonC";
        return filePath;
    }

    public String getWorkingPath() {
        String filePath = null;
        try {
            filePath = new File(MainProgram.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).toString();
            filePath = filePath.substring(0, filePath.length() - 13);
        } catch (URISyntaxException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filePath;
    }

    protected void generateFolder(String newFolderLocation) {
        //create output directory is not exists

        File folder = new File(newFolderLocation);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public int getFileLength(String readFile) {
        int fileLength = 0;
        File textFile = new File(readFile);
        //Scanner in = new Scanner(textFile);

        return 0;
    }

    public String readFile(File f) {
        String text = "";
        int read, N = 1024 * 1024;
        char[] buffer = new char[N];
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                read = br.read(buffer, 0, N);
                text += new String(buffer, 0, read);
                if (read < N) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return text;
    }

    public String readFileAsUTF8(File f) {
        String str = "";
        try {
            File fileDir = f;

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF8"));

            while ((str = in.readLine()) != null) {
                System.out.println(str);
                str+= in.readLine();
            }

            in.close();
            return str;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public boolean writeFileAsUTF8(File f, String content) {
        Writer out = null;
        try {
            File fileDir = f;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileDir), "UTF8"));
            out.append(content);
            out.flush();
            out.close();
            return true;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean writeFileContent(String fileName, String content) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            System.err.println(e);
        }
        return false;
    }

}
