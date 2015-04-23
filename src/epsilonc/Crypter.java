package epsilonc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author Runnetty
 */
public class Crypter {

    
    

    public static String encrypt(String enc, String sa) {
        String encrypted = "";
        char[] arr = enc.toCharArray();
        char[] sarr = sa.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char temp = 0;
            for (int j = 0; j < sarr.length; j++) {
                temp = (char) ((arr[i] * sarr[j]));
            }
            encrypted += temp;
        }
        return encrypted;
    }

    public static String decrypt(String dec, String sa) {
        String decrypted = "";
        char[] arr = dec.toCharArray();
        char[] sarr = sa.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char temp = 0;
            for (int j = 0; j < sarr.length; j++) {
                temp = (char) ((arr[i] / sarr[j]));
            }
            decrypted += temp;
        }
        return decrypted;
    }
    
}
