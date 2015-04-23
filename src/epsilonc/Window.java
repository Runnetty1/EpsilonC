package epsilonc;

/*
 **************************************************************************
 * AUTHOR: AUDUN MOSENG & MATS HARWISS LAST EDITED: 15.12.2014 
 * LAST EDITED BY: MATS HARWISS 
 * CLASS PURPOSE: TO CREATE THE USER-INTERFACE OF EDIT WINDOW.
 * 
 * TODO:
 * - Display info from xml in secondary Scrollpane.
 **************************************************************************
 */
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Window extends javax.swing.JFrame {

    public Window() {
        this.fh = new FileHandler();

        //MainProgram.loginWindow.allowEntry = false;
        initComponents();
        this.setTitle("Login");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int width = gd.getDisplayMode().getWidth() / 4;
        final int height = gd.getDisplayMode().getHeight() / 7;

        this.setLocation(width - 180, height - 40);
    }

    private void initComponents() {
        System.out.println("----------------------");
        System.out.println("|Begining initializing.");
        //Initial object population
        System.out.println("|=== Populating Objects.");
        jScrollPane2 = new javax.swing.JScrollPane();
        root = new DefaultMutableTreeNode(MainProgram.currXMLFilePath);
        jScrollPane3 = new javax.swing.JScrollPane();
        textConsole = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuNew = new javax.swing.JMenuItem();
        jMenuSave = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        startFix = true;
        ///////////////////////////////////////////////////////////////////////
        //Add nodes to Tree
        AddNodesToRoot();
        ///////////////////////////////////////////////////////////////////////
        //MENUITEMS
        System.out.println("|=== Adding MenuItems.");
        jMenuFile.setText("File");
        //jMenuNew.setText("New");
        //jMenuSave.setText("Save");
        jMenuFile.add(jMenuNew);
        jMenuFile.add(jMenuSave);
        jMenuFile.add(jSeparator1);
        menuExit.setText("Exit");
        jMenuFile.add(menuExit);
        jMenuBar1.add(jMenuFile);
        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);

        ///////////////////////////////////////////////////////////////////////
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ///////////////////////////////////////////////////////////////////////
        //Create Listeners
        System.out.println("|=== Creating Listeners.");

        treeRoot.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeRoot.getLastSelectedPathComponent();

                // if nothing is selected
                if (node == null) {
                    return;
                }

                // retrieve the node that was selected
                Object nodeInfo = node.getUserObject();

                System.out.println("Clicked on: " + nodeInfo.toString());
                System.out.println("Get Info and Display on screen");

                if (!textConsole.getText().equalsIgnoreCase(lastSaved) && !startFix) {
                    int n = JOptionPane.showConfirmDialog(null, "Do you want to save "
                            + "before you open a new file?", "Save Notification", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        save(currentFile);
                        //Overwrite current open file
                        currentFile = nodeInfo.toString();
                    } else if (n == JOptionPane.NO_OPTION) {
                    }
                    load(nodeInfo.toString());
                } else {
                    load(nodeInfo.toString());
                }
                if (startFix) {
                    System.out.println("setting currentfile to " + nodeInfo.toString());
                    currentFile = nodeInfo.toString();
                    startFix = false;
                }
            }
        });

        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //save();
            }
        });
        ///////////////////////////////////////////////////////////////////////
        //Add text in the textconsole
        System.out.println("|=== Adding Debug Text.");

        textConsole.setEditable(true);
        textConsole.setColumns(20);
        textConsole.setRows(5);
        textConsole.setLineWrap(true);
        textConsole.setFocusable(true);
        System.out.println("|=== Adding Debug Text.");
        textConsole.setText(str);
        jScrollPane3.setViewportView(textConsole);

        ///////////////////////////////////////////////////////////////////////
        //LAYOUT
        System.out.println("|=== Setting layout.");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 313, Short.MAX_VALUE)))
                        .addContainerGap()));
        ///////////////////////////////////////////////////////////////////////
        //FINISH INITZ - pack window
        System.out.println("|End of initializing.");
        System.out.println("----------------------\n");
        pack();
    }

    public DefaultMutableTreeNode nodesfromXMLDoc(Node node) {

        //System.out.println(node.getNodeName());
        DefaultMutableTreeNode a = new DefaultMutableTreeNode(node.getNodeName());
        NodeList nodeList = node.getChildNodes();

        if (1 > nodeList.getLength()) {
            //System.out.println("This is a end branch so add the node to root");
            root.add(a);
        }

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                //calls this method for all the children which is Element
                a.add(nodesfromXMLDoc(currentNode));
            }
        }
        return a;
    }

    private void AddNodesToRoot() {
        System.out.println("|=== Adding nodes to tree.");
        root.add(nodesfromXMLDoc(MainProgram.currentXMLDoc.getXMLDoc().getDocumentElement()));
        treeRoot = new javax.swing.JTree(root);
        jScrollPane2.setViewportView(treeRoot);
    }

    public void save(String name) {

        fh.writeFileContent(fh.getDefaultFilePath() + fh.sep + "NodeInfo" + fh.sep + name + ".txt", encrypt(textConsole.getText(), MainProgram.salt));
        //File f = new File(fh.getDefaultFilePath() + fh.sep + "NodeInfo" + fh.sep + name + ".txt");
        //fh.writeFileAsUTF8(f, encrypt(textConsole.getText(), MainProgram.salt));
        lastSaved = textConsole.getText();
    }

    private void load(String name) {
        File f = new File(fh.getDefaultFilePath() + fh.sep + "NodeInfo" + fh.sep + name + ".txt");
        String string = fh.readFile(f);
        
        //String string = fh.readFileAsUTF8(f);

        string = decrypt(string, MainProgram.salt);

        textConsole.setText(string);
        currentFile = name;
        lastSaved = textConsole.getText();

    }

    public String encrypt(String enc, String sa) {
        String encrypted = "";
        String ss = enc;
        System.out.println("encrypt: " + enc);
        char[] arr = ss.toCharArray();
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

    public String decrypt(String dec, String sa) {
        String decrypted = "";
        String ss = dec;
        System.out.println("decrypt: " + dec);
        char[] arr = ss.toCharArray();
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

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    // Variables declaration - do not modify                     
    //private JTreeTest itemTree;
    private DefaultMutableTreeNode root;
    private javax.swing.JTree treeRoot;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuNew;
    private javax.swing.JMenuItem jMenuSave;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JTextArea textConsole;
    private String lastSaved;
    private String currentFile;
    private String str;
    private boolean startFix;
    private FileHandler fh;
}
