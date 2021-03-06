/*
 ***************************************************************************
 * AUTHOR: AUDUN MOSENG & MATS HARWISS LAST EDITED: 26.02.2015 
 * LAST EDITED BY: AUDUN MOSENG
 *
 * CLASS PURPOSE: CREATING LOGIN WINDOW AND CHECK FOR VALID USERNAME AND 
 *                PASSWORD
 ***************************************************************************
 */
package epsilonc;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Audun Moseng
 */
public class LoginWindow extends javax.swing.JFrame {

    /**
     * Creates new form LoginWindow
     */
    public LoginWindow() {
        
        initComponents();
        this.username = "Drift";
        this.password = "Beta486";
        this.setTitle("Login");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int width = gd.getDisplayMode().getWidth() / 2;
        final int height = gd.getDisplayMode().getHeight() / 2;
        
        this.setLocation(width-180,height-40);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        passwordLoginField = new javax.swing.JPasswordField();
        userLoginField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        passwordLoginField.setText("password");
        passwordLoginField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordLoginFieldFocusGained(evt);
            }
        });
        passwordLoginField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordLoginFieldKeyPressed(evt);
            }
        });

        userLoginField.setText("UserName");
        userLoginField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userLoginFieldFocusGained(evt);
            }
        });
        userLoginField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userLoginFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(passwordLoginField, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(userLoginField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userLoginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLoginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (loginAttempt()) {

        }
        //if logged in check for update
    }//GEN-LAST:event_jButton1ActionPerformed

    // HIGHLIGHT USER/PASSWORD JTEXTFIELD WHEN FOCUS GAINED
    private void userLoginFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userLoginFieldFocusGained
        userLoginField.selectAll();
    }//GEN-LAST:event_userLoginFieldFocusGained

    private void passwordLoginFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordLoginFieldFocusGained
        passwordLoginField.selectAll();
    }//GEN-LAST:event_passwordLoginFieldFocusGained

    // IF KEYPRESS = ENTER, TRY LOGIN
    private void userLoginFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userLoginFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (loginAttempt()) {

            }
        }
    }//GEN-LAST:event_userLoginFieldKeyPressed

    private void passwordLoginFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordLoginFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (loginAttempt()) {

            }
        }
    }//GEN-LAST:event_passwordLoginFieldKeyPressed


    private boolean loginAttempt() {
        char[] passStr = passwordLoginField.getPassword();
        String str= "";
        for(char c : passStr){
            str+=c;
        }
        //System.out.println(str);
        if (userLoginField.getText().equals(username) && str.equals(password)) {
            JOptionPane.showMessageDialog(null, "User " + userLoginField.getText() 
                    + " authorized for access", "Access Notification", 
                    JOptionPane.PLAIN_MESSAGE);
            allowEntry = true;
            this.notif();
            
            new Window().setVisible(true);
            this.dispose();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username and/or password is not valid, " 
                    + "please try again, or contact system administrator!", 
                    "Access Notification", JOptionPane.PLAIN_MESSAGE);
            allowEntry = false;
            return false;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPasswordField passwordLoginField;
    private javax.swing.JTextField userLoginField;
    // End of variables declaration//GEN-END:variables
    protected String username;
    protected String password;
    public boolean allowEntry = false;
    public static boolean waitState = true;

    public boolean getWaitState() {
        return waitState;
    }

    public void notif() {
        waitState = false;
        synchronized (this) {
            notifyAll();
        }
    }

}
